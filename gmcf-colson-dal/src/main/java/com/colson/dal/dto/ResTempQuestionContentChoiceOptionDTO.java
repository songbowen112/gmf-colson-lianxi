package com.colson.dal.dto;

/**
 * 边池试题表-选择选项内容
 */
public class ResTempQuestionContentChoiceOptionDTO {

    /**
     * 选项id
     */
    private Integer id;

    /**
     * 序号
     */
    private Integer sequence;

    /**
     * 选项头 A D C
     */
    private String optionTitle;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否正确
     */
    private Integer isCorrect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
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

    @Override
    public String toString() {
        return "ResTempQuestionContentChoiceOptionDTO{" +
                "id=" + id +
                ", sequence=" + sequence +
                ", optionTitle='" + optionTitle + '\'' +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
