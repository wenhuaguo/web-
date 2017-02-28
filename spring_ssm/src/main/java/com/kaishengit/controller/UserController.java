package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserRole;
import com.kaishengit.service.RoleService;
import com.kaishengit.service.UserService;
import com.kaishengit.utile.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Acer on 2017/1/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "1") Integer p,
                       @RequestParam(required = false,defaultValue = "",name = "q_name") String queryName,
                       @RequestParam(required = false,defaultValue = "",name = "q_role") String queryRole,
                       Model model) throws UnsupportedEncodingException {
        //获得请求的参数，通过RequestParam(required=（true/false）该参数是否是必须的,defaultValue=""是否有默认值,name=值得是传递过来的参数名称)注解获得参数可以用别名但注解中必须与name属性
        //解决get请求中文乱码的问题
        if(StringUtils.isNotEmpty(queryName)){
            queryName = new String(queryName.getBytes("ISO8859-1"),"UTF-8");
        }
        logger.debug("pageNo:{}",p);
        Page<User> page = userService.findUserBySearchParam(queryName,queryRole,p);

        //List<User> userList =userService.findAll();
        List<Role> roleList = roleService.findAll();
        //model.addAttribute("userList",userList);
        model.addAttribute("roleList",roleList);
        model.addAttribute("page",page);
        model.addAttribute("queryName",queryName);
        model.addAttribute("queryRole",queryRole);
        return "user/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model){
        //查询出所有的角色并返回给客户端
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList",roleList);
        return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @Transactional
    public String add(Integer[] roleIds,User user,RedirectAttributes redirectAttributes){
        System.out.println(user.getPassword());
        //添加账户
        userService.add(user,roleIds);
        System.out.println(user.getId());
        //System.out.println(roleIds.length);
//            Integer userId = user.getId();
//            //添加账户的同时给账户添加到对应角色关系表
//            roleService.add(userId, roleIds);
        redirectAttributes.addFlashAttribute("message","保存成功");
        return "redirect:/user";
    }

    @RequestMapping(value = "/{userId:\\d+}/del",method = RequestMethod.GET)
    @Transactional
    public String del(@PathVariable Integer userId,RedirectAttributes redirectAttributes){
        //删除对应账户
        userService.del(userId);
        //删除账户对应的角色
        roleService.delByUserId(userId);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/user";
    }

    @GetMapping("/{userId:\\d+}/edit")
    public String edit(@PathVariable Integer userId,Model model){
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList",roleList);
        User user = userService.findById(userId);
        if(user == null){
            throw new NotFoundException();
        }else {
            System.out.println(user.getRoleList());
            model.addAttribute("roleList",roleList);
            model.addAttribute("user", user);
            return "user/edit";
        }
    }

    @PostMapping("/{userId:\\d+}/edit")
    @Transactional
    public String edit(Integer[] roleIds,User user,RedirectAttributes redirectAttributes){
        userService.update(user,roleIds);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/user";
    }
}
