package com.colson.dal.dto;


/**
 * 试题审核页面实时查重返回结果
 *
 * @author Gao leilei
 * @date 2018-05-22 15:44:25
 **/
public class ResSimilarityResultAuditDTO {

    private String repeatQuestionMainId;

    private Float repeatPercent;

    private ResQuestionMainDTO repeatQuestionMainDTO;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRepeatQuestionMainId() {
        return repeatQuestionMainId;
    }

    public void setRepeatQuestionMainId(String repeatQuestionMainId) {
        this.repeatQuestionMainId = repeatQuestionMainId;
    }

    public Float getRepeatPercent() {
        return repeatPercent;
    }

    public void setRepeatPercent(Float repeatPercent) {
        this.repeatPercent = repeatPercent;
    }

    public ResQuestionMainDTO getRepeatQuestionMainDTO() {
        return repeatQuestionMainDTO;
    }

    public void setRepeatQuestionMainDTO(ResQuestionMainDTO repeatQuestionMainDTO) {
        this.repeatQuestionMainDTO = repeatQuestionMainDTO;
    }
}
