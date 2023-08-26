package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;

@Data
public class SunlandsUser {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 内部263邮箱
     */
    private String email263;
    /**
     * ImUserId
     */
    private Integer imUserId;
    /**
     * 性别 0：女 1： 男
     */
    private Integer sex;
    /**
     * 注册时间，可能为空
     */
    private String registerTime;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 头像url
     */
    private String imageUrl;
    /**
     * 用户签名
     */
    private String signature;
    /**
     * 是否是vip 该值>0则是vip
     */
    private Integer isVip;
    private String certNo;
    private String certType;
    private String city;
    private String province;
    /**
     * 等级名称
     */
    private String gradeName;
    private String gradeCode;
    private Integer gradeId;
    /**
     * 尚德元
     */
    private BigDecimal sunlandAmount;
    private BigDecimal goldAmount;
    private BigDecimal experience;
    /**
     * 勋章列表
     */
    private Collection<String> medalList;
    /**
     * 用户状态
     */
    private Integer state;
}
