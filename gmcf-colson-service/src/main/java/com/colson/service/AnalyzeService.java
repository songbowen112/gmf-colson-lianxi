package com.colson.service;

import com.colson.dal.dto.AnalyzeKnowledgeNodeDTO;
import com.colson.dal.dto.ValuableBookFileInfoDTO;

import java.util.List;

public interface AnalyzeService {

	ValuableBookFileInfoDTO queryValuableBookByKnowledgeTreeId(Integer knowledgeTreeId);

	ValuableBookFileInfoDTO getValuableBookBySubjectAndProvinceAndKnowledgeTree(Integer subjectId, Integer provinceId, Integer knowledgeTreeId);

	ValuableBookFileInfoDTO getValuableBookByKnowledgeTreeId(Integer knowledgeTreeId);

 	List<AnalyzeKnowledgeNodeDTO> analyzeKnowledgeNode(int knowTreeId, Integer provinceId, int targetLevel, List<String> examSession);

}
