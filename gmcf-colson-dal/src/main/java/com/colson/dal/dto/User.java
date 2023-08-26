package com.colson.dal.dto;

import lombok.Data;
import org.dom4j.Element;

import java.io.Serializable;
import java.util.Set;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5618005626875415377L;

    private Integer userId;

    private String name;// 用户名称

    private String sn;// 用户名称

    private String fax;// 传真

    private String telephoneNumber;// 固定电话

    private String userPassword;// 用户密码

    private String objectclass;// 对象类型

    private String office;// 行政级别(例如:项目经理)

    private String officeCode;// 行政级别code

    private String uid;// 用户登陆名ID

    private String phone;// 手机

    private String cn;

    private String departmentId;// 子节点
    private String departmentName;// 子节点名称

    private String departmentIds;// 用户所有的节点路径

    private Integer projectId;// 产品线id

    private String projectName;// 产品线名称

    private String roleName;// 角色名称

    private String priviledgeRoleName;

    private String collegeName;//学院名字

    private String system;

    private Set<String> rightCodeSet;

    private Set<String> groupCodeSet;  // 人员角色/组

    public User(String uid){
        this.uid = uid;
    }
    public User(){
    }
    @Override
    public String toString() {

        return "Person [departmentIds=" + departmentIds + ",name=" + name
                + ", sn=" + sn + ", fax=" + fax + ", telephoneNumber="
                + telephoneNumber + ", userPassword=" + userPassword
                + ", objectclass=" + objectclass + ", office=" + office
                + ", uid=" + uid + ", phone=" + phone + ", cn=" + cn
                + ", departmentId=" + departmentId + "]";
    }

    public static User toPo(Element rootElt) {

        User user = new User();
        String userid = rootElt.elementTextTrim("userid");
        user.setUid(userid);
        String name = rootElt.elementTextTrim("name");
        user.setName(name);
        String departmentid = rootElt.elementTextTrim("departmentid");
        user.setDepartmentId(departmentid);
        String office = rootElt.elementTextTrim("office");
        user.setOffice(office);
        String mobile = rootElt.elementTextTrim("mobile");
        user.setTelephoneNumber(mobile);
        String phone = rootElt.elementTextTrim("phone");
        user.setPhone(phone);
        String fax = rootElt.elementTextTrim("fax");
        user.setFax(fax);
        user.setCn(userid);
        user.setSn(name);
        // 给密码赋一个默认值
        user.setUserPassword("unknow");
        return user;

    }

}
