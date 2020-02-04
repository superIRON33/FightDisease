package com.disease.demo.controller;

import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.service.HeadquarterService;
import com.disease.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 问答表控制层
 */
@RestController
@RequestMapping("/")
public class HeadquarterController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private HeadquarterService headquarterService;
    
    @PostMapping(value = "/headQuarter")
    public ResultDTO updateStatus(@RequestParam Integer id) {
        
        return userService.updateStatus(id);
    }
    
    @GetMapping(value = "/headQuarter")
    public ResultDTO getHeadQuarter(@RequestParam Integer id) {
        
        return headquarterService.getHeadQuarter(id);
    }
}
