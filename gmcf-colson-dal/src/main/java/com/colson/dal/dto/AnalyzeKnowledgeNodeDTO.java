package com.colson.dal.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * edit by gaolei
 */
@Data
public class AnalyzeKnowledgeNodeDTO {
	private String serialNumber; //知识点编号
	private String nodeName; //知识点名称
	private Integer level;	//层级
	private String outlineRequirement; //大纲要求
	private String questionType; //试题类型, 注:此字段值为null表示所有题型的统计
	private Integer count; //考察次数
	private String countPercent; //考察总占比
	private BigDecimal score; //考察总分
	private String scorePercent; //考察总分占比
	private List<AnalyzeKnowledgeNodeDTO> questionTypeStatistic; //题型统计详情

	//按照类型和分数排序
	public static class CompareByCountAndScore implements Comparator<AnalyzeKnowledgeNodeDTO> {
		@Override
		public int compare(AnalyzeKnowledgeNodeDTO o1, AnalyzeKnowledgeNodeDTO o2) {
			if (o1.count.compareTo(o2.count) == 0){
				return o2.score.compareTo(o1.score);
			}else {
				return o2.count - o1.count;
			}
		}
	}

	//按照编号排序
	public static class CompareBySerialNumber implements Comparator<AnalyzeKnowledgeNodeDTO>{
		@Override
		public int compare(AnalyzeKnowledgeNodeDTO o1, AnalyzeKnowledgeNodeDTO o2) {
			String[] serialNumber1 = o1.getSerialNumber().split("\\.");
			String[] serialNumber2 = o2.getSerialNumber().split("\\.");
			int length = Math.min(serialNumber1.length, serialNumber2.length);
			for (int i = 0; i < length; i++){
				int num1 = Integer.parseInt(serialNumber1[i]);
				int num2 = Integer.parseInt(serialNumber2[i]);
				if ( num1 != num2){
					return num1 - num2;
				}
			}
			return serialNumber1.length - serialNumber2.length;
		}
	}
}
