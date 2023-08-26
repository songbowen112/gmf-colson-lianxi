package com.colson.dal.dto;

/**
 * Created by hurw on 2017/8/9.
 * 选择题-选项内容表
 */
public class ReqOptionDTO {
    private Integer sequence;                                       	//序号（选择题）
    private String  optionTitle;                                    	//选项头，A B  C  D 等（选择题）
    private String  content;                                        	//题干内容（选择题）
    private Integer  isCorrect;                                      	//1 是正确答案， 0不是正确答案（选择题）
    private Integer questionId;                                         //父表选择题id
    private String correctAnswer;                                 //正确答案

    @Override
    public String toString() {
        return "ReqOptionDTO{" +
                "sequence=" + sequence +
                ", optionTitle='" + optionTitle + '\'' +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionId=" + questionId +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getOptionTitle() {
        return optionTitle.toUpperCase();
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle.toUpperCase();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

}
