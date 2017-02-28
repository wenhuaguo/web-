package com.kaishengit.controller;

import com.kaishengit.pojo.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Acer on 2017/1/11.
 */
@Controller
public class AccountController {
    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public Account showAccount(@PathVariable Integer id,
                               @RequestHeader("User-Agent") String userAgent,
                               @CookieValue(value = "name") String name ){

        RestTemplate restTemplate = new RestTemplate();
        String result =restTemplate.getForObject("http://www.baidu.com",String.class);
        System.out.println(result);

        System.out.println(id);
        System.out.println(userAgent);
        System.out.println(name);
        System.out.println("userAgent:" +userAgent);
        System.out.println("name:" + name);
        Account account = new Account("李四",123);
        return account;
    }
}
