package com.disease.demo.controller;

import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.service.UserService;
import com.disease.demo.service.base.ScheduledTask;
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

//    @Autowired
//    private ScheduledTask scheduledTask;
    
    @GetMapping(value = "/userInfo")
    public ResultDTO getUserInfo(@RequestParam Integer id,
                                 @RequestParam String encryptedData,
                                 @RequestParam String iv,
                                 @RequestParam String session) {
    
        return userService.getUserInfo(id, encryptedData, iv, session);
    }
    
    @GetMapping(value = "/epidemic")
    public ResultDTO getEpidemic(@RequestParam String cityName) {
        
        return userService.getEpidemic(cityName);
    }
    
    @PostMapping(value = "/integral")
    public ResultDTO updateIntegral(@RequestParam Integer id,
                                    @RequestParam Integer integral,
                                    @RequestParam Integer mode) {

        return userService.updateIntegral(id, integral, mode);
    }
    
    @GetMapping(value = "/honour")
    public ResultDTO getHonour(@RequestParam Integer id) {
        
        return userService.getHonour(id);
    }

//    @GetMapping(value = "/test")
//    public void test() {
//        scheduledTask.updateCityConfirmedCount();
//    }
}
