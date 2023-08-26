package com.colson.dal.dto;

public class ReqChangeQuestionDTO {
	private Integer sourceQuestionId; //最开始需要换题的试题id
	private String changeSource; //“AUTOGENERATE”(智能换题)，“OTHER”(其他)
	private Integer difficultValue;
	private String questionType; //“COMPREHENSIVE”(综合题)“OTHER”(其他)
	private String questionSource; //“REAL_QUESTION”(真题)“OTHER”(其他)
	private Integer knowledgeTreeId;
	private String nodeIdList; //只有综合题需要此字段，需要所有小题的知识点列表，用逗号分隔
	private Integer currentIndex; //当前试题在循环列表中的位置
	private String questionList; //试卷中同类型的所有题的列表

	public String getQuestionSource() {
		return questionSource;
	}

	public void setQuestionSource(String questionSource) {
		this.questionSource = questionSource;
	}

	public Integer getSourceQuestionId() {
		return sourceQuestionId;
	}

	public void setSourceQuestionId(Integer sourceQuestionId) {
		this.sourceQuestionId = sourceQuestionId;
	}

	public String getChangeSource() {
		return changeSource;
	}

	public void setChangeSource(String changeSource) {
		this.changeSource = changeSource;
	}

	public Integer getDifficultValue() {
		return difficultValue;
	}

	public void setDifficultValue(Integer difficultValue) {
		this.difficultValue = difficultValue;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Integer getKnowledgeTreeId() {
		return knowledgeTreeId;
	}

	public void setKnowledgeTreeId(Integer knowledgeTreeId) {
		this.knowledgeTreeId = knowledgeTreeId;
	}

	public String getNodeIdList() {
		return nodeIdList;
	}

	public void setNodeIdList(String nodeIdList) {
		this.nodeIdList = nodeIdList;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public String getQuestionList() {
		return questionList;
	}

	public void setQuestionList(String questionList) {
		this.questionList = questionList;
	}

}
