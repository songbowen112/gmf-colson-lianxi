package com.colson.dal.dto;

public class UnqualifiedInfoDTO {

    private Integer id;

    private String userId;

    private Integer questionMainId;

    private String	description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuestionMainId() {
        return questionMainId;
    }

    public void setQuestionMainId(Integer questionMainId) {
        this.questionMainId = questionMainId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
