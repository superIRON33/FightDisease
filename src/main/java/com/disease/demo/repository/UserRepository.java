package com.disease.demo.repository;

import com.disease.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wjy
 * @date: 2020/2/3 22:02
 * @description: User表对应的操作接口
 */
public interface UserRepository extends JpaRepository<String, User> {
}
