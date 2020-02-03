package com.disease.demo.controller;

import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 题目表控制层
 */
@RestController
@RequestMapping("/")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;
    
    @GetMapping(value = "/question")
    public ResultDTO getQuestion() {
        
        return questionService.getQuestion();
    }
}
