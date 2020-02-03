package com.disease.demo.repository;

import com.disease.demo.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wjy
 * @date: 2020/2/3 22:02
 * @description: Question表对应的操作接口
 */
public interface QuestionRepository extends JpaRepository<String, Question> {
}
