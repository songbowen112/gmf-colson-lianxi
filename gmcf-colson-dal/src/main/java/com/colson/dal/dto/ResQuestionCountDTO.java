package com.colson.dal.dto;

/**
 * @Author: 郭梦丽
 * @Date: 2018/8/10 11:32
 * @Description:
 */
public class ResQuestionCountDTO {

    private Integer singChoiceQuestions = 0;    //单选题数量
    private Integer multiChoiceQuestions = 0;   //多选题数量
    private Integer judgeChoiceQuestions = 0;   //判断题数量
    private Integer blankQuestions = 0; //填空题数量
    private Integer judgeEssayQuestions = 0;    //判断论述题数量
    private Integer essayQuestions = 0; //文字题数量
    private Integer comprehensiveQuestions = 0; //综合题数量
    private Integer readingCompQuestions = 0;   //完形填空题数量
    private Integer manyToMany = 0; //多选多数量

    public Integer getSingChoiceQuestions() {
        return singChoiceQuestions;
    }

    public void setSingChoiceQuestions(Integer singChoiceQuestions) {
        this.singChoiceQuestions = singChoiceQuestions;
    }

    public Integer getMultiChoiceQuestions() {
        return multiChoiceQuestions;
    }

    public void setMultiChoiceQuestions(Integer multiChoiceQuestions) {
        this.multiChoiceQuestions = multiChoiceQuestions;
    }

    public Integer getJudgeChoiceQuestions() {
        return judgeChoiceQuestions;
    }

    public void setJudgeChoiceQuestions(Integer judgeChoiceQuestions) {
        this.judgeChoiceQuestions = judgeChoiceQuestions;
    }

    public Integer getBlankQuestions() {
        return blankQuestions;
    }

    public void setBlankQuestions(Integer blankQuestions) {
        this.blankQuestions = blankQuestions;
    }

    public Integer getJudgeEssayQuestions() {
        return judgeEssayQuestions;
    }

    public void setJudgeEssayQuestions(Integer judgeEssayQuestions) {
        this.judgeEssayQuestions = judgeEssayQuestions;
    }

    public Integer getEssayQuestions() {
        return essayQuestions;
    }

    public void setEssayQuestions(Integer essayQuestions) {
        this.essayQuestions = essayQuestions;
    }

    public Integer getComprehensiveQuestions() {
        return comprehensiveQuestions;
    }

    public void setComprehensiveQuestions(Integer comprehensiveQuestions) {
        this.comprehensiveQuestions = comprehensiveQuestions;
    }

    public Integer getReadingCompQuestions() {
        return readingCompQuestions;
    }

    public void setReadingCompQuestions(Integer readingCompQuestions) {
        this.readingCompQuestions = readingCompQuestions;
    }

    public Integer getManyToMany() {
        return manyToMany;
    }

    public void setManyToMany(Integer manyToMany) {
        this.manyToMany = manyToMany;
    }

    @Override
    public String toString() {
        return "ResQuestionCountDTO{" +
                "singChoiceQuestions=" + singChoiceQuestions +
                ", multiChoiceQuestions=" + multiChoiceQuestions +
                ", judgeChoiceQuestions=" + judgeChoiceQuestions +
                ", blankQuestions=" + blankQuestions +
                ", judgeEssayQuestions=" + judgeEssayQuestions +
                ", essayQuestions=" + essayQuestions +
                ", comprehensiveQuestions=" + comprehensiveQuestions +
                ", readingCompQuestions=" + readingCompQuestions +
                ", manyToMany=" + manyToMany +
                '}';
    }
}
