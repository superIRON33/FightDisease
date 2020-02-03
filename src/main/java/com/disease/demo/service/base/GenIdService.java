package com.disease.demo.service.base;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wjy
 * @Date: 2019/11/14 12:31
 * @Description: 雪花算法生成ID
 */
@Slf4j
@EnableScheduling
@Service
public class GenIdService {
    
    /**
     * 起始的时间戳
     */
    private final static long START_STAMP = 1560439044000L;

    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BIT = 12;
   
    /**
     * 机器标识占用的位数
     */
    private final static long MACHINE_BIT = 5;
    
    /**
     * 数据中心占用的位数
     */
    private final static long DATACENTER_BIT = 5;
    
    /**
     * 最小保持id数量
     */
    private static final int ID_MIN_NUM = 1000;
    
    /**
     * 最大保持id数量
     */
    private static final int ID_MAX_NUM = 3000;
    
    private static ArrayBlockingQueue<Long> idList = new ArrayBlockingQueue<>(ID_MAX_NUM);

    /**
     * 每一部分的最大值
     */
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    
    private final static long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    /**
     * 数据中心
     */
    private long datacenterId = 1L;
    
    /**
     * 机器标识
     */
    private long machineId = 1L;
    
    /**
     * 序列号
     */
    private long sequence = 0L;
    
    /**
     * 上一次时间戳
     */
    private long lastStamp = -1L;

    static public long getId() {
        
        Long id;
        try {
            id = idList.poll(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("poll id error", e);
            throw new ServiceException("获取分布式id失败");
        }
        if (null == id) {
            throw new ServiceException("poll id error");
        }
        return id;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    private void requestGenId() {
        
        if (idList.size() >= ID_MIN_NUM) {
            return;
        }
        for (int i = 0; i < 1000; ++i) {
            long id = nextId();
            try {
                idList.put(id);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }

    /**
     * 功能描述: 产生下一个ID
     *
     * @param: []
     * @return: long
     * @author: wjy
     * @date: 2019/11/14 12:31
     */
    private synchronized long nextId() {
        
        long currStamp = getNewstamp();
        
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        } else if (currStamp == lastStamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStamp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStamp;
        return (currStamp - START_STAMP) << TIMESTAMP_LEFT | datacenterId << DATACENTER_LEFT | machineId << MACHINE_LEFT | sequence;
    }

    private long getNextMill() {
        
        long mill = getNewstamp();
        while (mill <= lastStamp) {
            mill = getNewstamp();
        }
        return mill;
    }

    private long getNewstamp() {
        
        return System.currentTimeMillis();
    }
}
