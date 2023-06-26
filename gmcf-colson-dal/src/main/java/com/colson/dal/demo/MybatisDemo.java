package com.colson.dal.demo;

import com.colson.dal.dao.PaymentEntityMapper;
import com.colson.dal.model.PaymentEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author song
 * @description: mybatis源码解析
 * @date 2021/12/28 上午10:58
 */
public class MybatisDemo {

    public static void main(String[] args) {
        SqlSession session = null;
        try {
            //第一步：读取mybatis-config.xml配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            //第二步：构建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //第三步：打开SqlSession
            session = sqlSessionFactory.openSession();

            //第四步：获取Mapper接口对象
            PaymentEntityMapper paymentEntityMapper = session.getMapper(PaymentEntityMapper.class);

            //第五步：调用Mapper接口对象的方法操作数据库
            PaymentEntity paymentEntity = paymentEntityMapper.selectByPrimaryKey(1);

            //第六步：业务处理
            System.out.println("-----------查询结果1：" + paymentEntity.getId() + "--" + paymentEntity.getSerial());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }
}
