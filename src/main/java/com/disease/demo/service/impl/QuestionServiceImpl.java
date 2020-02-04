package com.disease.demo.service.impl;

import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.mapper.QuestionMapper;
import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.model.entity.Question;
import com.disease.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 题目表服务层实现类
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    
    @Autowired
    private QuestionMapper questionMapper;
    
    @Override
    public ResultDTO getQuestion() {
        
        Map<String, Map<String, String>> returnMap = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            Optional<Question> question = questionMapper.findQuestion();
            if (question.isPresent()) {
                Map<String, String> map = new HashMap<>();
                map.put(question.get().getName(), question.get().getAnswer());
                map.put("A", question.get().getAOption());
                map.put("B", question.get().getBOption());
                map.put("C", question.get().getCOption());
                map.put("D", question.get().getDOption());
                returnMap.put(String.valueOf(i), map);
            }
            else {
                i--;
            }
        }
        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
        resultDTO.setData(returnMap);
        return resultDTO;
    }
}
