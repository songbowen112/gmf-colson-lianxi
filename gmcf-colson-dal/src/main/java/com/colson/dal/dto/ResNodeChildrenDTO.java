package com.colson.dal.dto;


/**
 * 知识点内容 + 末级知识点id列表
 *
 * addby chenqiuxia 20180821
 */

public class ResNodeChildrenDTO {
	private Integer id;  //知识点ID
	private String serialNumber; //知识点编号
	private String nodeName; // 知识点名称
	private Integer level;	//层级
	private String lastNodes;

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

	public String getLastNodes() {
		return lastNodes;
	}

	public void setLastNodes(String lastNodes) {
		this.lastNodes = lastNodes;
	}

}
