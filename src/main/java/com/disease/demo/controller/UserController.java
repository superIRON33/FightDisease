package com.disease.demo.controller;

import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 用户表控制层
 */
@RestController
@RequestMapping("")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/userInfo")
    public ResultDTO getUserInfo(@RequestParam String id) {
    
        return userService.getUserInfo(id);
    }
    
    @GetMapping(value = "/epidemic")
    public ResultDTO getEpidemic(@RequestParam String cityName) {
        
        return userService.getEpidemic(cityName);
    }
    
    @PostMapping(value = "/integral")
    public ResultDTO updateIntegral(@RequestParam String id,
                                    @RequestParam Integer integral,
                                    @RequestParam Integer mode) {

        return userService.updateIntegral(id, integral, mode);
    }
    
    @GetMapping(value = "/honour")
    public ResultDTO getHonour(@RequestParam String id) {
        
        return userService.getHonour(id);
    }
}