package com.colson.web.test;

import com.alibaba.fastjson.JSON;
import com.colson.common.dto.AccountQueryDTO;
import com.colson.common.dto.AccountQueryResultDTO;
import com.colson.common.dto.ApiResponseDTO;
import com.colson.common.utils.OkHttp3Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class AccountTest {

    @Autowired
    private OkHttp3Util okHttp3Util;


    private static final String accountBaseUrl = "https://test-tianliang-amount.ronjusk.cn";

    /**
     * 查询账户额度 url
     */
    private static final String QUERY_ACCOUNT_QUOT_URL = "/tf/self/amount/queryQuotByUserId";

    @Test
    public void testQueryAccount() {
        String brand = "test";
        String uid = "12345";

        // 查询自营额度账户
        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder()
                .brand(brand)//品牌
                .uid(uid)//userId
                .build();
        ApiResponseDTO response = null;
        try {
            response = okHttp3Util.postForEntity(accountBaseUrl + QUERY_ACCOUNT_QUOT_URL,
                    JSON.toJSONString(accountQueryDTO), ApiResponseDTO.class);
        } catch (Exception e) {
        }
        System.out.println(JSON.toJSONString(response));
    }

}
