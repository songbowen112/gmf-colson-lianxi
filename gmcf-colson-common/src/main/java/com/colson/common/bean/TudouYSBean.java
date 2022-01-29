package com.colson.common.bean;

public class TudouYSBean {

    /**
     * 词头
     */
    private String chars;

    /**
     * 音标
     */
    private String phoneticSymbol;

    /**
     * 词性+词义
     */
    private String explanationWithPrefix;

    /**
     * 发音
     */
    private String audioUrl;

    /**
     * 搭配
     */
    private String pair;

    /**
     * 搭配翻译
     */
    private String pairZh;

    /**
     * 搭配发音
     */
    private String pairAudioUrl;

    /**
     * 例句
     */
    private String sentence;

    /**
     * 例句翻译
     */
    private String sentenceZh;

    /**
     * 测试题类型
     */
    private Integer testType;

    /**
     * 选项A
     */
    private String optionA;

    /**
     * 选项B
     */
    private String optionB;

    /**
     * 选项C
     */
    private String optionC;

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    public String getPhoneticSymbol() {
        return phoneticSymbol;
    }

    public void setPhoneticSymbol(String phoneticSymbol) {
        this.phoneticSymbol = phoneticSymbol;
    }

    public String getExplanationWithPrefix() {
        return explanationWithPrefix;
    }

    public void setExplanationWithPrefix(String explanationWithPrefix) {
        this.explanationWithPrefix = explanationWithPrefix;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getPairZh() {
        return pairZh;
    }

    public void setPairZh(String pairZh) {
        this.pairZh = pairZh;
    }

    public String getPairAudioUrl() {
        return pairAudioUrl;
    }

    public void setPairAudioUrl(String pairAudioUrl) {
        this.pairAudioUrl = pairAudioUrl;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentenceZh() {
        return sentenceZh;
    }

    public void setSentenceZh(String sentenceZh) {
        this.sentenceZh = sentenceZh;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
}
