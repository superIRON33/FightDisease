package com.disease.demo.controller;

import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.service.WXLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Bob
 * @Date: 2020/2/4 08:54
 * @Description: 微信登录Controller
 */
@RestController
@RequestMapping("/")
public class WXLoginController {

    @Autowired
    private WXLoginService wxLoginService;

    @PostMapping(value = "/login")
    public ResultDTO Login(@RequestParam(required = false) String id,
                           @RequestParam String code,
                           @RequestParam String name,
                           @RequestParam String avatar) {

        return wxLoginService.login(id, code, name, avatar);
    }
}
