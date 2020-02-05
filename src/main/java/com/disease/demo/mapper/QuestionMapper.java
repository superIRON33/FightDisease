package com.disease.demo.mapper;

import com.disease.demo.model.entity.Question;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: wjy
 * @date: 2020/2/4 21:00
 * @description: 问题dao映射
 */
@Repository
public interface QuestionMapper {
    
    /**
     * 功能描述: 随机返回一道问题
     *
     * @param: []
     * @return: java.util.Optional<com.disease.demo.model.entity.Question>
     * @auther: wjy
     * @date: 2020/2/5 16:31
     */
    @Select("SELECT * FROM question WHERE id >= ((SELECT MAX(id) FROM question) - (SELECT MIN(id) FROM question)) * RAND() + (SELECT MIN(id) FROM question) LIMIT 1")
    Optional<Question> findQuestion();
}
