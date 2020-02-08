package com.disease.demo.service.impl;

import com.disease.demo.common.enums.ResultEnum;
import com.disease.demo.mapper.QuestionMapper;
import com.disease.demo.model.dto.ResultDTO;
import com.disease.demo.model.entity.Question;
import com.disease.demo.service.QuestionService;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: wjy
 * @date: 2020/2/3 21:32
 * @description: 题目表服务层实现类
 */
@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    
    @Autowired
    private QuestionMapper questionMapper;
    
    @Override
    public ResultDTO getQuestion() {
        
        Map<String, Map<String, String>> returnMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 15; i++) {
            Optional<Question> question = questionMapper.findQuestion();
            if (question.isPresent()) {
                if (set.contains(question.get().getId())) {
                    i--;
                }
                else {
                    set.add(question.get().getId());
                    String a = question.get().getAOption(), b = question.get().getBOption(),
                            c = question.get().getCOption(), d = question.get().getDOption();
                    Map<String, String> map = new HashMap<>();
                    map.put("question", question.get().getName());
                    map.put("answer", question.get().getAnswer());
                    if (!StringUtils.isNullOrEmpty(a)) {
                        map.put("A", a);
                    }
                    if (!StringUtils.isNullOrEmpty(b)) {
                        map.put("B", b);
                    }
                    if (!StringUtils.isNullOrEmpty(c)) {
                        map.put("C", c);
                    }
                    if (!StringUtils.isNullOrEmpty(d)) {
                        map.put("D", d);
                    }
                    returnMap.put(String.valueOf(i), map);
                }
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
