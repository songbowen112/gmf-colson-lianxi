package com.colson.common.docx4j;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 16:30
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class CrossDictHomePage {

    /**
     * 构造函数
     * @param subject           科目
     * @param adjustProvince    适用省份
     * @param examProvince      考试省份
     * @param realCoverDate     真题覆盖考期
     */
    public CrossDictHomePage(String subject, String adjustProvince, String examProvince, String realCoverDate) {
        this.subject = subject;
        this.adjustProvince = adjustProvince;
        this.examProvince = examProvince;
        this.realCoverDate = realCoverDate;
    }

    /**
     * 构造函数
     * @param subject           科目
     * @param adjustProvince    适用省份
     * @param realCoverDate     真题覆盖考期
     */
    public CrossDictHomePage(String subject, String adjustProvince, String realCoverDate) {
        this.subject = subject;
        this.adjustProvince = adjustProvince;
        this.realCoverDate = realCoverDate;
        this.examProvince = "全国";
    }

    /**
     * 科目
     */
    private String subject;

    /**
     * 适用省份
     */
    private String adjustProvince;

    /**
     * 考试省份
     */
    private String examProvince;

    /**
     * 真题覆盖考期
     */
    private String realCoverDate;

    public String getSubject() {
        return subject;
    }

    public String getAdjustProvince() {
        return adjustProvince;
    }

    public String getExamProvince() {
        return examProvince;
    }

    public String getRealCoverDate() {
        return realCoverDate;
    }

    @Override
    public String toString() {
        return "CrossDictHomePage{" +
                "subject='" + subject + '\'' +
                ", adjustProvince='" + adjustProvince + '\'' +
                ", examProvince='" + examProvince + '\'' +
                ", realCoverDate='" + realCoverDate + '\'' +
                '}';
    }
}
