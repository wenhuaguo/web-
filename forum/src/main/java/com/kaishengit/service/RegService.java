package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kaishengit.dao.LoginLogDao;
import com.kaishengit.dao.NotifyDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.LoginLog;
import com.kaishengit.entity.Notify;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.userVo.UserVo;
import com.kaishengit.util.EmailUtils;
import com.kaishengit.util.Page;
import com.kaishengit.util.readconfig.ReadConfig;
import org.apache.commons.codec.digest.DigestUtils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Acer on 2016/12/15.
 */
public class RegService {
    /**
     * 通过guava里面的get(key,callable<v>)方法进行简单的实现缓存
     * expireAfterWrite方法的意思的是写入缓存之后过期的时间
     * 发送激活邮件的token缓存
     */
    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(6, TimeUnit.HOURS)
            .build();
    //限制操作频率过快的缓存
    private static Cache<String,String> id = CacheBuilder.newBuilder()
            .expireAfterWrite(1,TimeUnit.MINUTES)
            .build();
    //发送找回密码的token缓存
    private static Cache<String,String> passwordCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30,TimeUnit.MINUTES)
            .build();
    private UserDao user = new UserDao();
    private LoginLogDao loginLogDao = new LoginLogDao();
    private NotifyDao notifyDao = new NotifyDao();
    //登录日志
    private static Logger logger = LoggerFactory.getLogger(RegService.class);
    /**
     * 查看账号是否被占用
     * @param username
     */
    public Boolean findByName(String username) {
        //首先判断禁用名库里面是否有是被禁用的用户名（名人、政治家等）
        String name = ReadConfig.get("user.username");
        List<String> nameList = Arrays.asList(name.split(","));
        if(nameList.contains(username)) {
            return false;
        }
        //如果未存在相同的名称则返回true否则返回false
        return user.findByName(username) == null;
    }

    /**
     * 注册用户进行保存
     * @param username
     * @param password
     * @param email
     * @param phone
     */
    public void save(String username, String password, String email, String phone) {
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(DigestUtils.md5Hex(ReadConfig.get("user.password.salt")+ password));
        user1.setEmail(email);
        user1.setPhone(phone);
        user1.setState(User.UNACTIVE);
        user1.setAvatar(User.DEFAULT_AVATAR_NAME);
        System.out.println("用户注册时密码产生的加密：" +user1.getPassword());
        user.save(user1);
        //用户注册成功的同时给用户邮箱发送激活文件,通过添加一个子线程将用户注册时间缩短的同时给用户发送邮件
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //给用户发送激活邮件
                String uuid = UUID.randomUUID().toString();
                String url = "http://forum.com/user/active?_=" + uuid;
                //放入缓存等待6个小时
                cache.put(uuid, username);
                String html = "<h3>亲爱的" + username + ":</h3>请点击该<a href='" + url + "'>链接</a>去激活你的账号<br>凯盛软件";
                EmailUtils.sendEmail(email, "邮箱激活连接", html);
            }
        });
        thread.start();//启动线程
    }




    /**
     * 查看输入的邮箱是否已被注册
     * @param email
     */
    public User findByEamil(String email) {
        return user.findByEmail(email);
    }

    /**
     * 根据token用以激活用户
     */
    public void active(String uuid) {
        //根据之前放入缓存的键值对（uuid和用户名的对于关系去激活对应的用户）
        //进行双重判断首先判断token里面的对于的值是否存在如果不存在说明token已经失效或过期
        //如果token存在，再根据账号判断对于用户是否存在，如果不存在抛出异常，并给予提示信息（“该账号不存在”）
        //如果用户存在那么就进行修改
        String username = cache.getIfPresent(uuid);

        if(username == null){
            throw new ServiceException("token无效或已过期");//注意提示用于的产生的原因进行提示抛出异常的信息
        }else{
            User user2 = user.findByName(username);
            if(user2 == null){
                throw  new ServiceException("该账号不存在");
            }else {
                user2.setState(User.ACTIVE);
                user.update(user2);
                //最后将缓存中键值对进行删除根据键值
                cache.invalidate(uuid);
            }

        }

    }

    /**
     * 进行登录验证
     * @param s
     * @param username
     * @param password
     */
    public User login(String ip, String username, String password) {
        System.out.println("username:" + username + " password" + password);
        User loginUser = user.findByName(username);
        System.out.println("用户输入密码加密：" + DigestUtils.md5Hex(password + ReadConfig.get("user.password.salt")));
        System.out.println("用户名查看是否存在" + loginUser);
        //因数据库密码进行加密所有用户输入的密码也需要解密后和数据库密码进行比对看是否成功
        if(loginUser != null && (DigestUtils.md5Hex(ReadConfig.get("user.password.salt")+ password)).equals(loginUser.getPassword())){
            //如果账号和密码正确那么需要验证账号的状态（是否陪激活、以及是否被禁用）
            if (loginUser.getState().equals(User.UNACTIVE)){
                throw new ServiceException("未被激活，请去激活后登录");
            }else if(loginUser.getState().equals(User.ACTIVE)){
                //如果登录成功记录登录的日志
                LoginLog loginLog = new LoginLog();
                loginLog.setIp(ip);
                loginLog.setT_user_id(loginUser.getId());
                loginLogDao.save(loginLog);
                //记录登录日志
                logger.info("账号:{}进行登录,ip:{}",loginUser.getUsername(),loginLog.getId());
                return loginUser;
            }else{
                throw new ServiceException("你已被禁止登录");
            }
        }else {
            throw new ServiceException("账号或密码错误");
        }
    }

    /**
     * 用户找回密码
     * @param id
     * @param sessionId
     * @param value
     */
    public void foundPassword(String sessionId,String type,String value) {
        //首先判断缓存当中是否存在sessionId如果不存在允许用户操作，如果存在那么就是同一用户给用户提示不能操作频率过快
        //缓存中的键就是sessionid为键任意值为值
        if(id.getIfPresent(sessionId) == null) {
            //将sessionid放入到缓存空间中，限制用户频繁提交
            if ("phone".equals(type)) {
                //TODO 根据手机号码找回密码
            } else if ("email".equals(type)) {
                User foundPassword = user.findByEmail(value);
                if (foundPassword != null) {
                    //开启一个线程发送电子邮件
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //将token放入到缓存当中进行后期密码找回判断,token的值是UUID
                            String uuid = UUID.randomUUID().toString();
                            String url = "http://forum.com/foundpassword/newpassword?token=" + uuid;
                            //该缓存存放token与哪个要修改的用户进行对应，用于用户找回密码
                            passwordCache.put(uuid, foundPassword.getUsername());
                            String html = foundPassword.getUsername() + "<br>请点击该<a href='"+ url+ "'>链接</a>找回密码,链接在30分钟内有效";
                            EmailUtils.sendEmail(value,"密码找回邮件",html);
                        }
                    });
                    //线程启动
                    thread.start();
                    logger.info("{}进行了找回邮箱操作",foundPassword.getUsername());
                }
            }
            //如果缓存中不存在sessionId那么就将sessionId作为键，任意值作为值放入缓存当中用于判断是否是同一用户进行操作
            id.put(sessionId,"xxxx");
        }else {
            throw new ServiceException("操作频率过快");
        }
    }

    /**
     * 修改密码
     * @param s
     * @param id
     * @param password
     */
    public void changePassword(String password, String id, String token) {
        //传递token并没有在找回连接的时候将token清楚实际上是为了安全考虑不让有目的的人得逞
        if(passwordCache.getIfPresent(token) != null) {
            User foundUser = user.findById(Integer.valueOf(id));
            if(foundUser != null){
                foundUser.setPassword(DigestUtils.md5Hex(ReadConfig.get("user.password.salt") + password));
                user.update(foundUser);
                passwordCache.invalidate(token);
                logger.info("{}重置了密码",foundUser.getUsername());
            }
        }else {
            throw new ServiceException("token已失效或错误");
        }
    }

    /**
     * 通过token找对的账号并对token值的有效性进行判断
     * @return
     * @param token
     */
    public User findUserByToken(String token) {
        //判断token是否有效
        String username = passwordCache.getIfPresent(token);
        if(StringUtils.isNotEmpty(username)){
            //如果有效就查询对应的用户
            User foundUser = user.findByName(username);
            if(foundUser != null){
                return foundUser;
            }else {
                throw new ServiceException("该账号不存在");
            }
        }else {
            //如果无效就返回对象消息
            throw new ServiceException("token已失效或已过期");
        }

    }

    /**
     * 修改用户邮箱
     * @param user
     * @param email
     */
    public void changeEmail(String email, User seesionUser) {
            seesionUser.setEmail(email);
            user.update(seesionUser);
    }

    /**
     * 根据原密码重新设置新密码
     * @param user
     * @param oldpassword
     * @param newpassword
     */
    public void resetPassword(User curruser, String oldpassword, String newpassword) {
      String salt = ReadConfig.get("user.password.salt");
            //判断输入的原始密码和sessionID中存储的用户密码是否相等
            if(curruser.getPassword().equals(DigestUtils.md5Hex(salt+oldpassword))){
                curruser.setPassword(DigestUtils.md5Hex(salt + newpassword));
                user.update(curruser);
                System.out.println("密码修改成功");
        }else {
                throw new ServiceException("原始密码错误");
            }
    }

    /**
     * 修改用户头像将名字进行存储
     * @param user
     * @param avatar
     */
    public void changeAvatar(User userAvatar, String avatar) {
                userAvatar.setAvatar(avatar);
                user.update(userAvatar);
    }

    /**
     * 统计信息中心的消息的集合
     * @param id
     */
    public List<Notify> findAllNotifyList(User user) {
       return notifyDao.findAllNotify(user);
    }
    //根据id更新通知消息表的状态
    public void updateNotifyStateById(String ids) {
        String[] idArray = ids.split(",");
        for(int i= 0; i< idArray.length;i++){
            System.out.println(idArray[i]);
            Notify notify = notifyDao.findNotifyById(idArray[i]);
            if(notify != null){
                notify.setState(Notify.READ_STATE);
                notify.setReadtime(new Timestamp(new DateTime().getMillis()));
                notifyDao.updateNotify(notify);
            }
        }
    }
    //显示用户登录的时间地址
    public Page<UserVo> userList(String pageNo) {
        //查询总条数用户计算总页数
        Integer count = user.count();

            Page<UserVo> page = new Page<>(count,Integer.valueOf(pageNo));
            List<User> userList = user.findAllUser(page);
            List<UserVo> userVos = new ArrayList<>();
            for(User userV : userList){
                //通过sql语句进行封装
                UserVo userVo = user.findUserVo(userV.getId());
                System.out.println("userid:"+userVo.getUserId() + "userName:" + userVo.getUsername());
                userVos.add(userVo);
            }
            page.setItems(userVos);
            return page;
        }

    //更新用户状态（0,1,2）
    public void updatUserState(String userId, Integer userState) {
        if(StringUtils.isNotEmpty(userId) && StringUtils.isNumeric(userId)){
            User userUpadate = user.findById(Integer.valueOf(userId));
                    userUpadate.setState(userState);
                    user.update(userUpadate);
    }else {
            throw new ServiceException("参数异常");
    }
    }
}
