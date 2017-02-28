package com.kaishengit.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.kaishengit.dto.wx.TextMessage;
import com.kaishengit.dto.wx.User;
import com.kaishengit.exception.ServiceException;

import com.kaishengit.shiro.HttpClientUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Acer on 2017/2/24.
 */
@Service
public class WxService {
    @Value("${wx.token}")
    private  String token;

    @Value("${wx.aesKey}")
    private  String aesKey;

    @Value("${wx.corpId}")
    private String corpId;

    @Value("${wx.secret}")
    private String secret;

    private static  Logger logger = LoggerFactory.getLogger(WxService.class);
    //写成常量必须从0开始
    private static final String ACCESSTOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
    private static final String CREATE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token={0}";
    private static final String EDIT_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token={0}";
    private static final String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token={0}";
    private static final String CODE_TO_USERID_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token={0}&code={1}";
    /**
     * 匿名局部内部类
     */
    private LoadingCache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(7200, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    //键无所谓只要返回正确的值就可以
                    //不用进行字符串的连加了参数值从0开始
                    String url = MessageFormat.format(ACCESSTOKEN_URL,corpId,secret);
                    OkHttpClient okHttpClient = new OkHttpClient();
                    //链式调用
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    //链式调用
                    Response response = okHttpClient.newCall(request).execute();
                    String result = response.body().string();//string将内容转换为字符串tostring()把对象转换为字符串
                    response.close();
                    Map<String,Object> map = new Gson().fromJson(result,HashMap.class);
                    //将json对象转换为字符串
                    if(map.containsKey("errcode")){
                        logger.error("获取AccessToken异常：{}",map.get("errcode"));
                        throw new ServiceException("获取AccessToken异常：" + map.get("errcode"));
                    }else {
                        return map.get("access_token").toString();
                    }
                }
            });
    public  String init(String msg_signature,String timestamp,String nonce,String echostr){


        try {
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token,aesKey,corpId);
            return wxBizMsgCrypt.VerifyURL(msg_signature,timestamp,nonce,echostr);
        } catch (AesException e) {
            e.printStackTrace();
            throw new ServiceException("微信初始化异常",e);
        }
    }

    //获取accessToken方法
    public String getAccessToken(){
        try {
            return cache.get("");
        }catch (ExecutionException e){
            throw new ServiceException("获取AccessToken异常");
        }
    }

    /**
     * 微信创建用户
     * @param user
     */
    public void save(User user){
        String url = MessageFormat.format(CREATE_USER_URL,getAccessToken());
        OkHttpClient okHttpClient = new OkHttpClient();
        String json = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String resultJson = response.body().string();
            response.close();
            Map<String,Object> resultMap = new Gson().fromJson(resultJson,HashMap.class);
            Object errcode = resultMap.get("errcode");
            if("0".equals(errcode.toString())){
                logger.error("微信创建用户异常{}",resultJson);
                throw new ServiceException("微信创建用户异常：" + resultJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改微信用户
     * @param user
     */
    public void changeUser(User user) {
        //获得URL
        String url = MessageFormat.format(EDIT_USER_URL,getAccessToken());
        OkHttpClient okHttpClient = new OkHttpClient();
        //将对象转换为json对象
        String json = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application;charset=UTF-8"),json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();//用了链式调用
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();//将返回的body里面内容转换为字符串
            response.close();//需要关闭响应
            Map<String,Object> resultMap = new Gson().fromJson(result,HashMap.class);
            String errorCode = resultMap.get("errcode").toString();
            System.out.println(errorCode);
            if(!"0.0".equals(errorCode)){
                logger.error("微信修改用户异常{}",result);
                throw new ServiceException("微信修改用户异常:" + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送文本消息
     * @param textMessage
     */
    public void sendMessage(TextMessage textMessage){
        System.out.println(textMessage);
        //获得请求的URL
        String url = MessageFormat.format(SEND_MESSAGE_URL,getAccessToken());
        String json = new Gson().toJson(textMessage);//将Java对象转换json对象通过Google的Gson类
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),json);
            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String resultJson = response.body().string();
            //将字符串转换为Map集合
            Map<String,Object> resultMap = new Gson().fromJson(resultJson,HashMap.class);
            String errorCode = resultMap.get("errcode").toString();
            System.out.println(resultMap);
            if(!"0.0".equals(errorCode)){
                logger.error("消息发送失败{}",resultJson);
                throw new ServiceException("消息发送失败" + resultJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过code返回userId
     * @param code
     * @return
     */
    public String codeToUserId(String code){
        String url = MessageFormat.format(CODE_TO_USERID_URL,getAccessToken(),code);
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            String resultJson = response.body().string();
            //将json对象转换为集合
            Map<String,String> resultMap = new Gson().fromJson(resultJson,HashMap.class);
            if(resultMap.containsKey("UserId")){
                return resultMap.get("UserId");
            }else {
                return null;
            }
        } catch (IOException e) {
            logger.error("通过code返回userId异常{}",e);
            throw new ServiceException("通过code返回userId异常",e);
        }
    }
}



