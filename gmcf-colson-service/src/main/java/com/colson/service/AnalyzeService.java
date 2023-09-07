package com.sunlands.analyze.service;

import com.sunlands.analyze.dto.AnalyzeKnowledgeNodeDTO;
import com.sunlands.common.datasource.DataSource;
import com.sunlands.common.datasource.DataSourceEnum;
import com.sunlands.common.dto.ValuableBookFileInfoDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AnalyzeService {

	@DataSource(DataSourceEnum.READ)
	public List<AnalyzeKnowledgeNodeDTO> analyzeKnowledgeNode(int knowTreeId, Integer provinceId, int targetLevel, List<String> examSession);

	@DataSource(DataSourceEnum.READ)
	public List<String> queryExamSession(int knowTreeId, Integer provinceId);

	/**
	 * 生成考情分析Word文档, 输出到Ftp服务器上
	 *
	 * @param knowTreeId	知识树ID
	 * @param provinceId	考试省ID
	 * @param examProvinceName	考试省名称
	 * @param response		web 响应对象, 可以为null值
	 * @throws Exception
	 */
	void generateAnalyzeKnowledgeWordDoc(int knowTreeId, Integer provinceId, String examProvinceName, HttpServletResponse response) throws Exception;

	String generateAnalyzeKnowledgeWordDoc(int knowTreeId, Integer provinceId, String examProvinceName) throws Exception;

	Integer queryExistsBySubjectAndProvince(Integer knowTreeId);

	@DataSource(DataSourceEnum.READ)
	ValuableBookFileInfoDTO queryValuableBookBySubjectAndProvince(Integer subjectId, Integer provinceId);

	@DataSource(DataSourceEnum.READ)
	ValuableBookFileInfoDTO queryValuableBookByKnowledgeTreeId(Integer knowledgeTreeId);

	@DataSource(DataSourceEnum.READ)
	ValuableBookFileInfoDTO getValuableBookBySubjectIdAndProvinceId(Integer subjectId, Integer provinceId);

	@DataSource(DataSourceEnum.READ)
	ValuableBookFileInfoDTO getValuableBookBySubjectAndProvinceAndKnowledgeTree(Integer subjectId, Integer provinceId, Integer knowledgeTreeId);

	@DataSource(DataSourceEnum.READ)
	ValuableBookFileInfoDTO getValuableBookByKnowledgeTreeId(Integer knowledgeTreeId);

	Integer insertValuableBookFileInfoDTO(ValuableBookFileInfoDTO valuableBookFileInfoDTO);
}
