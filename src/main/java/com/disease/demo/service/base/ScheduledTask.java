package com.disease.demo.service.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.disease.demo.common.enums.VariableEnum;
import com.disease.demo.common.utils.DXDiseaseStatisticUtil;
import com.disease.demo.mapper.CityMapper;
import com.disease.demo.mapper.UserMapper;
import com.disease.demo.model.entity.City;
import com.disease.demo.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author: wjy
 * @date: 2020/2/5
 * @description: 定时器
 */
@Slf4j
@Service
public class ScheduledTask {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private CityMapper cityMapper;
    
    @Transactional(rollbackOn = Exception.class)
    @Scheduled(cron = "0 50 23 * * ?")
    public void updateDays() {
        
        log.info("每天23:59:00更新用户登录天数");
        Integer status = userMapper.updateDays();
        if (status.equals(VariableEnum.OK.getValue())) {
            log.info("更新登录天数失败/没有满足的用户");
        } else {
            log.info("更新登录天数成功");
        }
    }
    
    @Transactional(rollbackOn = Exception.class)
    @Scheduled(cron = "0 52 23 * * ?")
    public void updateIntegralByStepNumber() {
        
        log.info("每天23:59:10判断: 若用户今日步数<=1000步且今日登录过，积分加10分");
        List<User> userList = userMapper.findAll();
        userList.forEach(user -> {
            if (user.getIntegralLogin().equals(VariableEnum.DELETE.getValue()) && user.getStepNumber() <= 1000) {
                Integer status = userMapper.updateIntegral(user.getId(), 10 + user.getIntegral());
                if (status.equals(VariableEnum.OK.getValue())) {
                    log.info("更新步数积分失败");
                }
            }
        });
    }
    
    @Transactional(rollbackOn = Exception.class)
    @Scheduled(cron = "0 54 23 * * ?")
    public void updateUserSingleIntegral() {
        
        log.info("每天23:59:20初始化用户的单人模式积分上限");
        Integer status = userMapper.initializeSingleIntegral();
        if (status.equals(VariableEnum.OK.getValue())) {
            log.info("初始化单人模式积分失败/没有满足的用户");
        } else {
            log.info("初始化单人模式积分成功");
        }
    }
    
    @Transactional(rollbackOn = Exception.class)
    @Scheduled(cron = "0 56 23 * * ?")
    public void updateIntegralLogin() {
        
        log.info("每天23:59:30初始化integralLogin");
        Integer status = userMapper.initializeIntegralLogin();
        if (status.equals(VariableEnum.OK.getValue())) {
            log.info("初始化integralLogin失败/没有满足的用户");
        } else {
            log.info("初始化integralLogin成功");
        }
    }
    
    @Transactional(rollbackOn = Exception.class)
    @Scheduled(cron = "0 58 23 * * ?")
    public void updateIntegralShare() {
        
        log.info("每天23:59:40初始化integralShare");
        Integer status = userMapper.initializeIntegralShare();
        if (status.equals(VariableEnum.OK.getValue())) {
            log.info("初始化integralShare失败/没有满足的用户");
        } else {
            log.info("初始化integralShare成功");
        }
    }
    
    @Transactional(rollbackOn = Exception.class)
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateStepNumber() {
        
        log.info("每天23:59:50初始化今日步数");
        Integer status = userMapper.initializeStepNumber();
        if (status.equals(VariableEnum.OK.getValue())) {
            log.info("初始化今日步数失败/没有满足的用户");
        } else {
            log.info("初始化今日步数成功");
        }
    }
    
    @Transactional(rollbackOn = Exception.class)
    @Scheduled(cron = "0 0 6,16 * * ?")
    public void updateCityConfirmedCount() {
        
        String result = DXDiseaseStatisticUtil.getAreaStat();
        JSONArray array = JSONArray.parseArray(result);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(array.getString(i));
            map.put(jsonObject.getString("provinceName"), jsonObject.getString("confirmedCount"));
            String cities = jsonObject.getString("cities");
            JSONArray cityList = JSONArray.parseArray(cities);
            for (int j = 0; j < cityList.size(); j++) {
                JSONObject city = JSONObject.parseObject(cityList.getString(j));
                map.put(city.getString("cityName"), city.getString("confirmedCount"));
            }
        }
        
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            Optional<City> city = cityMapper.getCity(entry.getKey());
            if (city.isPresent()) {
                cityMapper.updateCount(entry.getKey(), entry.getValue());
            } else {
                City city1 = new City(entry.getKey(), entry.getValue());
                cityMapper.addCity(city1);
            }
        }
    }
}