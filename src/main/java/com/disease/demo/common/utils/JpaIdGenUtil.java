//package com.disease.demo.common.utils;
//
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentityGenerator;
//
//import java.io.Serializable;
//
///**
// * @Auther: wjy
// * @Date: 2019/11/14 12:31
// * @Description: JPA ID生成策略
// */
//public class JpaIdGenUtil extends IdentityGenerator {
//
//    /**
//     * 功能描述: 生成String类型的随机id。
//     *
//     * @param: [s, obj]
//     * @return: java.io.Serializable
//     * @auther: wjy
//     * @date: 2019/11/14 16:14
//     */
//    @Override
//    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
//
//        return String.valueOf(com.cstm.demo.service.base.GenIdService.getId());
//    }
//}
