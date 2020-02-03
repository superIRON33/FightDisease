package com.disease.demo.repository;

import com.disease.demo.model.entity.Headquarter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wjy
 * @date: 2020/2/3 22:02
 * @description: Headquarter表对应的操作接口
 */
public interface HeadquarterRepository extends JpaRepository<String, Headquarter> {
}
