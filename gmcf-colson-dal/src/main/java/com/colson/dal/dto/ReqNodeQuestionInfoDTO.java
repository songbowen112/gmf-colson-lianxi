package com.colson.dal.dto;

/**
 * 末级知识点id - 试题数量rel
 * addby chenqiuxia 20180911
 *
 */
public class ReqNodeQuestionInfoDTO {
    private Integer nodeId;
    private Integer questionNum;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

}
