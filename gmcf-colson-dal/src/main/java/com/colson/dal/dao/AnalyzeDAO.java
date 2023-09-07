package com.sunlands.analyze.dao;

import com.sunlands.analyze.dto.AnalyzeKnowledgeNodeDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyzeDAO {

	@Select({"<script>",
			"SELECT",
			"	kn.serial_number serialNumber,",
			"	kn.name nodeName,",
			"	kn.level,",
			"	kn.outline_requirement outlineRequirement,",
			"	CASE WHEN parent.question_type IN ('COMPREHENSIVE', 'READING_COMPREHENSION', 'MANY_TO_MANY')",
			"		THEN parent.question_type",
			"		WHEN q.question_type LIKE '%FILL_BLANK'",
			"		THEN 'FILL_BLANK'",
			"		ELSE q.question_type END AS questionType,",
			"	count(rel.t_question_main_id) `count`,",
			"	ifnull(sum(if(rel.id is null,0,q.score)),0) score",
			"FROM t_knowledge_node k",
			"LEFT JOIN t_question_main q 	ON k.id = q.main_node_id AND q.source_type = 'REAL_QUESTION' AND q.delete_flag = 0",
			"	AND q.invalid_flag = 0 AND q.current_version = 1",
			"LEFT JOIN t_question_main_exam_session_province rel ON rel.t_question_main_id = q.id AND rel.delete_flag = 0",
			"	AND rel.exam_province = #{examProvinceId} ",
			"	<if test='examSession != null and examSession.size() != 0'>",
			"		AND rel.exam_session IN (",
			"			SELECT id FROM t_exam_session WHERE session IN",
			"		<foreach collection='examSession' open='(' close=')' separator=',' item ='session'>",
			"			#{session}",
			"		</foreach>",
			"		)",
			"	</if>",
			"LEFT JOIN t_question_main parent ON q.parent_question_id = parent.id",
			"LEFT JOIN t_knowledge_node kn ON k.knowledge_tree_id = kn.knowledge_tree_id",
			"AND substring_index(k.serial_number, '.', #{targetLevel}) = kn.serial_number AND kn.delete_flag = 0",
			"WHERE k.knowledge_tree_id = #{knowTreeId} AND k.delete_flag = 0 AND k.invalid_flag = 0",
			"	AND (parent.question_type is NULL or parent.question_type	in ('COMPREHENSIVE', 'READING_COMPREHENSION', 'MANY_TO_MANY') )",
			"GROUP BY kn.serial_number,questionType",
			"</script>"})
	List<AnalyzeKnowledgeNodeDTO> analyzeExam(@Param("knowTreeId") int knowTreeId,
											  @Param("examProvinceId") Integer examProvinceId,
											  @Param("targetLevel") int targetLevel,
											  @Param("examSession") List<String> examSession);

	@Select({
			"SELECT s.session",
			"FROM t_question_main q ",
			"INNER JOIN t_question_main_exam_session_province rel ON rel.t_question_main_id = q.id AND rel.delete_flag = 0",
			"LEFT JOIN t_exam_session s ON rel.exam_session = s.id",
			"WHERE q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 AND s.delete_flag=0",
			"AND q.source_type = 'REAL_QUESTION'",
			"AND rel.exam_province = #{examProvinceId}",
			"AND q.knowledge_tree_id = #{knowTreeId}",
			"GROUP BY rel.exam_session",
			"ORDER BY s.`session` + 0 DESC"
	})
	List<String> queryExamSession(@Param("knowTreeId")int knowTreeId, @Param("examProvinceId") Integer examProvinceId);
}
