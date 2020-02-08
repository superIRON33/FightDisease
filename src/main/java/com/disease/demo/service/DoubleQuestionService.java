package com.disease.demo.service;

import com.disease.demo.model.dto.ResultDTO;

/**
 * @author: wjy
 * @date: 2020/2/7 23:05
 * @description: 双人答题服务层
 */
public interface DoubleQuestionService {
    
    ResultDTO buildRoom(Integer id);
    
    ResultDTO enterRoom(Integer id, String roomNumber);
    
    ResultDTO result(Integer id, String roomNumber, Integer count);
}
