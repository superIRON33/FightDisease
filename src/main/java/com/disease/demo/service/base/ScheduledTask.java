package com.disease.demo.service.base;

import com.disease.demo.common.enums.VariableEnum;
import com.disease.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Slf4j
@Service
public class ScheduledTask {
    
    @Autowired
    private UserMapper userMapper;
    
    @Transactional(rollbackOn = Exception.class)
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateUserSingleIntegral() {
        
        log.info("每天凌晨12点初始化用户的单人模式积分上限");
        Integer status = userMapper.updateSingleIntegral(VariableEnum.OK.getValue());
        if (status.equals(VariableEnum.OK.getValue())) {
            log.info("更新失败");
        }
        else {
            log.info("更新成功");
        }
    }
}
