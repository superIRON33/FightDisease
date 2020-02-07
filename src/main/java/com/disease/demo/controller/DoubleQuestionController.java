package com.disease.demo.controller;

import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.service.DoubleQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wjy
 * @date: 2020/2/7 23:12
 * @description: 双人答题控制层
 */
@RestController
@RequestMapping("/double")
public class DoubleQuestionController {
    
    @Autowired
    private DoubleQuestionService doubleQuestionService;
    
    @PostMapping("/buildRoom")
    public ResultDTO buildRoom(@RequestParam Integer id) {
        
        return doubleQuestionService.buildRoom(id);
    }
    
    @PostMapping("/enterRoom")
    public ResultDTO enterRoom(@RequestParam Integer id,
                               @RequestParam String roomNumber) {

        return doubleQuestionService.enterRoom(id, roomNumber);
    }
    
    @GetMapping("/result")
    public ResultDTO result(@RequestParam Integer id,
                            @RequestParam String roomNumber,
                            @RequestParam Integer count) {
        
        return doubleQuestionService.result(id, roomNumber, count);
    }
}
