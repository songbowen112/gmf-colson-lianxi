package com.colson.dal.dto;

import lombok.Data;

@Data
public class ExamLabelDTO implements Comparable<ExamLabelDTO> {
    private Integer questionMainId;
    /**
     * 考期
     */
    private Integer examSession;
    private String examSessionName;
    /**
     * 省份
     */
    private Integer examProvince;
    private String examProvinceName;
    /**
     * AB卷tag标签
     */
    private Integer examTag;
    private String examTagName;

    @Override
    public int compareTo(ExamLabelDTO o) {
        if (this.getExamSessionName().equals(o.getExamSessionName())) {
            if ("全国".equals(this.getExamProvinceName())) {
                return -1;
            } else if ("全国".equals(o.getExamProvinceName())) {
                return 1;
            } else {
                return 0;
            }
        }
        return Integer.valueOf(o.getExamSessionName()).compareTo(Integer.valueOf(this.getExamSessionName()));
    }
}
