package com.disease.demo.mapper;

import com.disease.demo.model.entity.Question;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Repository
public interface QuestionMapper {
    
    @Select("SELECT * FROM question WHERE id >= ((SELECT MAX(id) FROM question) - (SELECT MIN(id) FROM question)) * RAND() + (SELECT MIN(id) FROM question) LIMIT 1")
    Optional<Question> findQuestion();
}
