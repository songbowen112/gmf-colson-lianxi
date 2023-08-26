package com.colson.dal.dto;

import java.math.BigDecimal;

/**
 * Created by hurw on 2017/8/9.
 * 填空题表
 */
public class ReqBlankDTO {
    private String code; //试题code，上传试题时code为空或者0
    private Integer sequence	;//序号(填空题)
    private BigDecimal score	;//分值(填空题)
    private String answer	;//答案(填空题)

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "ReqBlankDTO{" +
                "sequence=" + sequence +
                ", score=" + score +
                ", code=" + code +
                ", answer='" + answer + '\'' +
                '}';
    }
}
