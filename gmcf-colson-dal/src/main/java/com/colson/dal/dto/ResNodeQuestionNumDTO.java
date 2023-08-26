package com.colson.dal.dto;


/**
 * 知识点内容 + 试题数
 *
 * addby chenqiuxia 20180821
 */

public class ResNodeQuestionNumDTO {
	private Integer id;  //知识点ID
	private String serialNumber; //知识点编号
	private String nodeName; // 知识点名称
	private Integer level;	//层级
	private Integer questionNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

}
