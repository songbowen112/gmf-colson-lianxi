package com.colson.dal.dto;


import java.io.Serializable;

public class SimilarityResultDTO implements Serializable{
    private String flag;
    private String message;
    private Integer questionMainId;
    private String code;
    private Double similarity;
    private String similarityString;

    public String getSimilarityString() {
        return similarityString;
    }

    public void setSimilarityString(String similarityString) {
        this.similarityString = similarityString;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getQuestionMainId() {
        return questionMainId;
    }

    public void setQuestionMainId(Integer questionMainId) {
        this.questionMainId = questionMainId;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    @Override
    public String toString() {
        return "SimilarityResultDTO:{" +
                "flag=" + flag +
                ", message=" + message +
                ", questionMainId=" + questionMainId +
                ", similarity=" + similarity +
                ", code=" + code +
                ", similarityString=" + similarityString +
                "}";
    }

}
