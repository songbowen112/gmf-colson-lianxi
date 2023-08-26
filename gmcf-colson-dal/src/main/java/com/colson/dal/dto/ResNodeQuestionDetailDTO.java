package com.colson.dal.dto;


/**
 * 知识点 + 知识点下试题类型和试题数量
 */

public class ResNodeQuestionDetailDTO {

	private String questionType; // 试题类型

	private Integer questionCount; // 试题数量

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Integer getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}
}
