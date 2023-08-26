package com.colson.dal.dao;

import com.colson.dal.dto.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hurw on 2017/8/14.
 */

@Repository
public interface QuestionMainDao {

    @Insert({
            " INSERT INTO `t_question_main` (`code`, `source_type`, `question_type`,content_type, `question_id`, `score`, `knowledge_tree_id`, ",
            " `main_node_id`, `vice_node_id_1`, `vice_node_id_2`, `difficulty_value`, `exam_province`, `exam_session`, exam_tag, `current_version`, ",
            " `parent_question_id`, `sequence`, `invalid_flag`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag`) ",
            " VALUES (#{code,jdbcType=VARCHAR},#{sourceType,jdbcType=VARCHAR},#{questionType,jdbcType=VARCHAR},#{contentType,jdbcType=VARCHAR}, ",
            " #{questionId,jdbcType=INTEGER},#{score,jdbcType=DECIMAL},#{knowledgeTreeId,jdbcType=INTEGER}, ",
            " #{mainNodeId,jdbcType=INTEGER},#{viceNodeId1,jdbcType=INTEGER},#{viceNodeId2,jdbcType=INTEGER}, ",
            " #{difficultyValue,jdbcType=INTEGER},#{examProvince,jdbcType=INTEGER},#{examSession,jdbcType=INTEGER}, #{examTag}, ",
            " #{currentVersion,jdbcType=INTEGER},#{parentQuestionId,jdbcType=INTEGER},#{sequence,jdbcType=INTEGER}, ",
            " #{invalidFlag,jdbcType=INTEGER},now(),#{creator,jdbcType=VARCHAR}, ",
            " now(),#{operator,jdbcType=VARCHAR},0) "
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insert(QuestionMain insertQuestionMain);

    /**
     * 批量新增
     *
     * @param questionMainList
     * @return
     */
    @Insert({
            "<script>",
            " INSERT INTO t_question_main (`code`, `source_type`, `question_type`,content_type, `question_id`, `score`, `knowledge_tree_id`, ",
            " `main_node_id`, `vice_node_id_1`, `vice_node_id_2`, `difficulty_value`, `exam_province`, `exam_session`, exam_tag, `current_version`, ",
            " `parent_question_id`, `sequence`, `invalid_flag`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag`) VALUES ",
            "<foreach collection=\"questionMainList\" item=\"item\"  separator=\",\">",
            " (#{item.code,jdbcType=VARCHAR},#{item.sourceType,jdbcType=VARCHAR},#{item.questionType,jdbcType=VARCHAR},#{item.contentType,jdbcType=VARCHAR}, ",
            " #{item.questionId,jdbcType=INTEGER},#{item.score,jdbcType=DECIMAL},#{item.knowledgeTreeId,jdbcType=INTEGER}, ",
            " #{item.mainNodeId,jdbcType=INTEGER},#{item.viceNodeId1,jdbcType=INTEGER},#{item.viceNodeId2,jdbcType=INTEGER}, ",
            " #{item.difficultyValue,jdbcType=INTEGER},#{item.examProvince,jdbcType=INTEGER},#{item.examSession,jdbcType=INTEGER}, #{examTag},",
            " #{item.currentVersion,jdbcType=INTEGER},#{item.parentQuestionId,jdbcType=INTEGER},#{item.sequence,jdbcType=INTEGER}, ",
            " #{item.invalidFlag,jdbcType=INTEGER},now(),#{item.creator,jdbcType=VARCHAR}, ",
            " now(),#{item.operator,jdbcType=VARCHAR},0) ",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("questionMainList") List<QuestionMain> questionMainList);

    /**
     * 根据主键批量查询
     *
     * @param idList 试题id list
     * @return
     */
    @Select({"<script>",
            "SELECT ques.id, ques.`code`, ques.source_type sourceType, ques.question_type questionType,ques.content_type contentType,",
            "ques.question_id questionId,ques.score, ques.knowledge_tree_id knowledgeTreeId, ques.main_node_id mainNodeId,",
            "ques.vice_node_id_1 viceNodeId1, ques.vice_node_id_2 viceNodeId2, ques.difficulty_value difficultyValue,",
            "ques.exam_province examProvince,ques.exam_session examSession,",
            "ques.current_version currentVersion, ques.parent_question_id parentQuestionId, ques.sequence, ",
            "ques.invalid_flag invalidFlag ",
            "from t_question_main ques ",
            "WHERE delete_flag = 0 and id in",
            "<foreach item=\"item\" index=\"index\" collection=\"idList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</script>"})
    List<QuestionMain> listQuestionMainByMainIds(@Param("idList") List<Integer> idList);

    /**
     * 根据主键查询
     *
     * @param questionMainId
     * @return
     */
    @Select({"SELECT ques.id, ques.`code`, ques.source_type sourceType, ques.question_type questionType,ques.content_type contentType,",
            "ques.question_id questionId,ques.score, ques.knowledge_tree_id knowledgeTreeId, ques.main_node_id mainNodeId,",
            "ques.vice_node_id_1 viceNodeId1, ques.vice_node_id_2 viceNodeId2, ques.difficulty_value difficultyValue,",
            "ques.exam_province examProvince,ques.exam_session examSession,",
            "ques.current_version currentVersion, ques.parent_question_id parentQuestionId, ques.sequence, ",
            "ques.invalid_flag invalidFlag ",
            "from t_question_main ques ",
            "WHERE delete_flag = 0 and id = #{id,jdbcType=INTEGER} "})
    QuestionMain selectByPrimary(@Param("id") Integer questionMainId);

    /**
     * 根据试题code查询该试题当前版本
     *
     * @param code
     * @return
     */
    @Select({"SELECT ques.id, ques.`code`, ques.source_type sourceType, ques.question_type questionType,ques.content_type contentType,",
            "ques.question_id questionId,ques.score, ques.knowledge_tree_id knowledgeTreeId, ques.main_node_id mainNodeId,",
            "ques.vice_node_id_1 viceNodeId1, ques.vice_node_id_2 viceNodeId2, ques.difficulty_value difficultyValue,",
            "ques.exam_province examProvince,ques.exam_session examSession,",
            "ques.current_version currentVersion, ques.parent_question_id parentQuestionId, ques.sequence, ",
            "ques.invalid_flag invalidFlag ",
            "from t_question_main ques ",
            "WHERE ques.delete_flag = 0 and ques.code = #{code} and ques.current_version = 1 limit 1"})
    QuestionMain selectByCode(@Param("code") String code);

    /**
     * 根据知识点列表获取试题（非综合题）,智能组卷用
     *
     * @param nodeIdList
     * @param sourceTypeList
     * @param examSessionList
     * @param examProvinceList
     * @return
     */
    @Select({
            "<script>",
            "SELECT DISTINCT",
            "q.id,",
            "q.question_type    AS questionType,",
            "q.difficulty_value AS difficultyValue,",
            "q.score,",
            "q.main_node_id     AS mainNodeId,",
            "q.vice_node_id_1   AS viceNodeId1,",
            "q.vice_node_id_2   AS viceNodeId2,",
            "q.parent_question_id AS parentQuestionId",
            "FROM t_question_main q",
            "LEFT JOIN t_question_main_exam_session_province as a ON a.t_question_main_id = q.id AND a.delete_flag = 0",
            "WHERE q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q.knowledge_tree_id=#{treeId}",
            "<if test=\"nodeIdList != null and nodeIdList.size()>0 \">",
            "AND (q.main_node_id IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "OR q.vice_node_id_1 IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "OR q.vice_node_id_2 IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            ")",
            "</if>",
            "<if test=\"sourceTypeList != null and sourceTypeList.size()>0 \">",
            "AND q.source_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"sourceTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"examSessionList != null and examSessionList.size()>0 \">",
            "AND a.exam_session IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"examSessionList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"examProvinceList != null and examProvinceList.size()>0 \">",
            "AND a.exam_province IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"examProvinceList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "AND q.parent_question_id = 0",
            "AND q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "<if test=\"excludePaperTypeList != null and excludePaperTypeList.size > 0\">",
            "AND (q.code not in (SELECT DISTINCT pq.question_code FROM t_paper_code pc ",
            "LEFT JOIN t_paper_question_code_rel pq ON pc.`code`=pq.paper_code ",
            "WHERE pc.type IN ",
            "<foreach collection=\"excludePaperTypeList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "AND pc.invalid_flag=0 AND pc.delete_flag=0 AND pq.delete_flag=0 )",
            ")",
            "</if>",
            "</script>"
    })
    List<QuestionForGeneratePaper> queryQuestionForAutoGenPaper(@Param("nodeIdList") List<Integer> nodeIdList,
                                                                @Param("treeId") Integer treeId,
                                                                @Param("sourceTypeList") List<String> sourceTypeList,
                                                                @Param("examSessionList") List<Integer> examSessionList,
                                                                @Param("examProvinceList") List<Integer> examProvinceList,
                                                                @Param("questionTypeList") List<String> questionTypeList,
                                                                @Param("excludePaperTypeList") List<String> excludePaperTypeList);

    /**
     * 根据知识点列表获取试题（非综合题）,系统组卷用
     *
     * @param treeId
     * @param sourceTypeList
     * @param questionTypeList
     * @return
     */
    @Select({
            "<script>",
            "SELECT COUNT(1) FROM (",
            "SELECT DISTINCT",
            "q.id",
            "FROM t_question_main q",
            "JOIN t_question_main_exam_session_province as a ON a.t_question_main_id = q.id AND a.delete_flag = 0",
            "<if test='examSession!=null'>",
            "JOIN t_exam_session es ON es.id = a.exam_session AND es.session >#{examSession}",
            "</if>",
            "WHERE q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q.knowledge_tree_id=#{treeId}",
            "AND q.source_type IN ",
            "('REAL_QUESTION')",
            "AND q.parent_question_id = 0",
            "AND q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "UNION",
            "SELECT DISTINCT",
            "q.id",
            "FROM t_question_main q",
            "WHERE q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q.knowledge_tree_id=#{treeId}",
            "AND q.source_type IN ",
            "('MOCK_QUESTION')",
            "AND q.parent_question_id = 0",
            "AND q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            ")t",
            "</script>"
    })
    int queryQuestionCount(@Param("treeId") Integer treeId, @Param("sourceTypeList") List<String> sourceTypeList,
                                                               @Param("questionTypeList") List<String> questionTypeList, @Param("examSession")Integer examSession);

    /**
     * 根据知识点列表获取试题（非综合题）,系统组卷用
     *
     * @param treeId
     * @param sourceTypeList
     * @param questionTypeList
     * @return
     */
    @Select({
            "<script>",
            "SELECT DISTINCT",
            "q.id,",
            "q.question_type    AS questionType,",
            "q.difficulty_value AS difficultyValue,",
            "q.score,",
            "q.main_node_id     AS mainNodeId,",
            "q.vice_node_id_1   AS viceNodeId1,",
            "q.vice_node_id_2   AS viceNodeId2,",
            "q.parent_question_id AS parentQuestionId,",
            "q.code,",
            "q.sequence,",
            "q.source_type sourceType",
            "FROM t_question_main q",
            "JOIN t_question_main_exam_session_province as a ON a.t_question_main_id = q.id AND a.delete_flag = 0",
            "<if test='examSession!=null'>",
            "JOIN t_exam_session es ON es.id = a.exam_session AND es.session >#{examSession}",
            "</if>",
            "WHERE q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q.knowledge_tree_id=#{treeId}",
            "AND q.source_type IN ",
            "('REAL_QUESTION')",
            "AND q.parent_question_id = 0",
            "AND q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "UNION",
            "SELECT DISTINCT",
            "q.id,",
            "q.question_type    AS questionType,",
            "q.difficulty_value AS difficultyValue,",
            "q.score,",
            "q.main_node_id     AS mainNodeId,",
            "q.vice_node_id_1   AS viceNodeId1,",
            "q.vice_node_id_2   AS viceNodeId2,",
            "q.parent_question_id AS parentQuestionId,",
            "q.code,",
            "q.sequence,",
            "q.source_type sourceType",
            "FROM t_question_main q",
            "WHERE q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q.knowledge_tree_id=#{treeId}",
            "AND q.source_type IN ",
            "('MOCK_QUESTION')",
            "AND q.parent_question_id = 0",
            "AND q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<QuestionForGeneratePaper> queryQuestionForSysGenPaper(@Param("treeId") Integer treeId, @Param("sourceTypeList") List<String> sourceTypeList,
                                                                @Param("questionTypeList") List<String> questionTypeList, @Param("examSession")Integer examSession);


    /**
     * 系统组卷用，查找试题列表（大题:综合,完型,多选多）
     *
     * @return
     */
    @Select({
            "<script>",
            "SELECT COUNT(1)",
            "FROM (",
            "SELECT ",
            "distinct q.id",
            "FROM t_question_main q",
            "INNER JOIN t_question_main q1 on q.id = q1.parent_question_id",
            "JOIN t_question_main_exam_session_province as a ON a.t_question_main_id = q.id AND a.delete_flag = 0",
            "<if test='examSession!=null'>",
            "JOIN t_exam_session es ON es.id = a.exam_session AND es.session >#{examSession}",
            "</if>",
            "WHERE",
            "q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "AND q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q1.delete_flag=0 AND q1.invalid_flag=0 AND q1.current_version=1",
            "AND q.knowledge_tree_id=#{treeId}",
            "AND q.source_type IN ",
            "('REAL_QUESTION')",
            "UNION",
            "SELECT ",
            "distinct q.id",
            "FROM t_question_main q",
            "INNER JOIN t_question_main q1 on q.id = q1.parent_question_id",
            "WHERE",
            "q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "AND q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q1.delete_flag=0 AND q1.invalid_flag=0 AND q1.current_version=1",
            "AND q.knowledge_tree_id=#{treeId}",
            "AND q.source_type IN ",
            "('MOCK_QUESTION')",
            ")t",
            "</script>"
    })
    int queryBigQuestionCount(@Param("treeId") Integer treeId, @Param("sourceTypeList") List<String> sourceTypeList,
                                                                  @Param("questionTypeList") List<String> questionTypeList, @Param("examSession") Integer examSession);


    /**
     * 系统组卷用，查找试题列表（大题:综合,完型,多选多）
     *
     * @return
     */
    @Select({
            "<script>",
            "SELECT ",
            "distinct q.id,",
            "q.question_type    AS questionType,",
            "q.difficulty_value AS difficultyValue,",
            "q.score,",
            "q.parent_question_id AS parentQuestionId,",
            "q.code,",
            "q.sequence,",
            "q.source_type sourceType",
            "FROM t_question_main q",
            "INNER JOIN t_question_main q1 on q.id = q1.parent_question_id",
            "JOIN t_question_main_exam_session_province as a ON a.t_question_main_id = q.id AND a.delete_flag = 0",
            "<if test='examSession!=null'>",
            "JOIN t_exam_session es ON es.id = a.exam_session AND es.session >#{examSession}",
            "</if>",
            "WHERE",
            "q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "AND q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q1.delete_flag=0 AND q1.invalid_flag=0 AND q1.current_version=1",
            "AND q.knowledge_tree_id=#{treeId}",
            "AND q.source_type IN ",
            "('REAL_QUESTION')",
            "UNION",
            "SELECT ",
            "distinct q.id,",
            "q.question_type    AS questionType,",
            "q.difficulty_value AS difficultyValue,",
            "q.score,",
            "q.parent_question_id AS parentQuestionId,",
            "q.code,",
            "q.sequence,",
            "q.source_type sourceType",
            "FROM t_question_main q",
            "INNER JOIN t_question_main q1 on q.id = q1.parent_question_id",
            "WHERE",
            "q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "AND q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q1.delete_flag=0 AND q1.invalid_flag=0 AND q1.current_version=1",
            "AND q.knowledge_tree_id=#{treeId}",
            "AND q.source_type IN ",
            "('MOCK_QUESTION')",
            "</script>"
    })
    List<QuestionForGeneratePaper> queryBigQuestionForSysGenPaper(@Param("treeId") Integer treeId, @Param("sourceTypeList") List<String> sourceTypeList,
                                                                   @Param("questionTypeList") List<String> questionTypeList, @Param("examSession") Integer examSession);

    @Select({
            "SELECT s.`name`",
            "FROM t_knowledge_tree t",
            "JOIN ent_subject s ON t.subject_id = s.id",
            "WHERE t.id = #{treeId}"
    })
    String getSujectName(@Param("treeId") Integer treeId);
    /**
     * 智能换题功能使用：查找试题列表(非综合题)
     *
     * @return
     */
    @Select({
            "<script>",
            "SELECT q.id, q.difficulty_value as difficultyValue, q.main_node_id as mainNodeId,",
            "q.vice_node_id_1 as viceNodeId1, q.vice_node_id_2 as viceNodeId2",
            "FROM t_question_main q",
            "WHERE q.knowledge_tree_id=#{knowledgeTreeId}",
            "AND q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1",
            "<if test=\"questionType == 'DISORDER_FILL_BLANK' or questionType == 'ORDER_FILL_BLANK'\">",
            "AND q.question_type IN ('ORDER_FILL_BLANK','DISORDER_FILL_BLANK')",
            "</if>",
            "<if test=\"questionType != 'DISORDER_FILL_BLANK' and questionType != 'ORDER_FILL_BLANK'\">",
            "AND q.question_type=#{questionType}",
            "</if>",
            "AND (q.main_node_id = #{mainNodeId} OR q.vice_node_id_1 = #{mainNodeId} OR q.vice_node_id_2=#{mainNodeId})",
            "<if test=\"sourceType == 'REAL_QUESTION'\"> ",
            "AND q.source_type = 'REAL_QUESTION'",
            "</if>",
            "AND q.parent_question_id = 0",
            "</script>"
    })
    List<QuestionMain> queryQuestionForChange(@Param("knowledgeTreeId") int knowledgeTreeId,
                                              @Param("questionType") String questionType,
                                              @Param("mainNodeId") int mainNodeId,
                                              @Param("sourceType") String sourceType);


    /**
     * 智能换题用，查找试题列表（综合类型的题:综合题,完型填空,多选多）
     *
     * @return
     */
    @Select({
            "<script>",
            "SELECT q.id, q.difficulty_value as difficultValue",
            "FROM t_question_main q INNER JOIN t_question_main q1 on q.id = q1.parent_question_id",
            "WHERE",
            "q.knowledge_tree_id=#{treeId}",
            "AND q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1",
            "AND q1.delete_flag=0 AND q1.invalid_flag=0 AND q1.current_version=1 ",
            "AND q.question_type=#{questionType}",
            "<if test=\"sourceType == 'REAL_QUESTION'\"> ",
            "AND q.source_type = 'REAL_QUESTION'",
            "</if>",
            "AND (q1.main_node_id IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "or q1.vice_node_id_1 IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "or q1.vice_node_id_2 IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            ")",
            "</script>"
    })

    @Results({
            @Result(column = "id", property = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "difficultValue", property = "difficultValue", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
    })
    List<Map> queryComQuestionForChange(@Param("treeId") Integer treeId,
                                        @Param("nodeIdList") Set nodeIdList,
                                        @Param("sourceType") String sourceType,
                                        @Param("questionType") String questionType);

    /**
     * 根据条件查询试题表总表t_question_main
     *
     * @param reqQuestionMainDTO
     * @param start
     * @param pageSize
     * @return
     */
    @Select({
            "<script>",
            " select a.`id`, a.`code`, a.`source_type`, a.`question_type`,a.content_type, a.`question_id`, a.`score`, a.`knowledge_tree_id`, ",
            "a.`main_node_id`, ",
            "CASE WHEN a.main_node_id IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = a.main_node_id)",
            "ELSE null END as mainNodeName,",
            "CASE WHEN a.main_node_id IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = a.main_node_id)",
            "ELSE null END as mainNodeSerialNumber,",
            "a.`vice_node_id_1`, a.`vice_node_id_2`, ",
            "CASE WHEN vice_node_id_1 IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = vice_node_id_1)",
            "ELSE null END as viceNodeName1,",
            "CASE WHEN vice_node_id_1 IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = vice_node_id_1)",
            "ELSE null END as viceNodeSerialNumber1,",
            "CASE WHEN vice_node_id_2 IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = vice_node_id_2)",
            "ELSE null END as viceNodeName2,",
            "CASE WHEN vice_node_id_2 IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = vice_node_id_2)",
            "ELSE null END as viceNodeSerialNumber2,",
            "CASE WHEN exam_session IS NOT NULL THEN (SELECT s.session FROM t_exam_session s WHERE id = exam_session)",
            "ELSE NULL END as examSessionName,",
            "CASE WHEN exam_province IS NOT NULL THEN (SELECT province_name FROM sch_local_province WHERE id = exam_province)",
            "ELSE NULL END as examProvinceName,",
            "a.`difficulty_value`,a.`difficulty_type`, a.`exam_province`, a.`exam_session`, a.`current_version`, a.`parent_question_id`,",
            " a.`sequence`, a.`invalid_flag`, a.`create_time`, a.`creator`, a.`update_time`, a.`operator`, a.`delete_flag`,  ",
            " case when a.content_type='ESSAY' then (select analysis_useful from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis_useful from t_question_content_choice where id=a.question_id) else '' end analysisUseful, ",
            " case when a.content_type='ESSAY' then (select analysis_useless from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis_useless from t_question_content_choice where id=a.question_id) else '' end analysisUseless, ",
            " case when a.content_type='ESSAY' then (select content from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select content from t_question_content_choice where id=a.question_id) else '' end content, ",
            " case when a.content_type='ESSAY' then (select correct_answer from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select correct_answer from t_question_content_choice where id=a.question_id) else '' end correct_answer, ",
            " case when a.content_type='ESSAY' then (select analysis from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis from t_question_content_choice where id=a.question_id) else '' end analysis, ",
            " ifnull((select used_times from t_question_compose_paper_info where question_code=a.code and delete_flag=0 limit 1),0) as composeCount, ",
            " node.outline_requirement ",
            " from t_question_main a   ",
            " left join t_knowledge_node node on node.id=a.main_node_id and node.delete_flag=0",
            "<if test=\"item.condition != null and item.condition != ''\">",
            " left join t_question_content_essay ess on ess.id=a.question_id and ess.delete_flag=0 and a.content_type = 'ESSAY' ",//用于题干筛选
            " left join t_question_content_choice ess2 on a.question_id=ess2.id and ess2.delete_flag=0 and a.content_type = 'CHOICE' ",//用于题干筛选
            "</if>",
            " where a.delete_flag=0 and a.parent_question_id=0 ",
            "<if test=\"item.currentVersion == null || item.currentVersion != 2\"> and a.current_version=1 </if>",//2为忽略状态
            "<if test=\"item.invalidFlag == null\"> and a.invalid_flag=0 </if>",
            "<if test=\"item.invalidFlag != null and item.invalidFlag != 2\"> and a.invalid_flag=#{item.invalidFlag,jdbcType=INTEGER} </if>",//2为全部
            "<if test='item.id != null'> and a.id=#{item.id,jdbcType=INTEGER} </if>",
            "<if test='item.code != null'> and a.code=#{item.code,jdbcType=VARCHAR} </if>",
            "<if test=\"item.idList != null and item.idList.size()>0\"> and a.id in ( ",
            "<foreach collection=\"item.idList\" item=\"mainId\" index=\"index\" separator=\",\">#{mainId,jdbcType=INTEGER}</foreach>",
            " )</if>",
            "<if test='item.knowledgeTreeId != null'> and a.knowledge_tree_id=#{item.knowledgeTreeId,jdbcType=INTEGER} </if>",
            "<if test=\"item.reqKnowledgeNodeList!=null and item.reqKnowledgeNodeList.size()>0\">",
            " AND (a.main_node_id in ( ",
            "<foreach collection=\"item.reqKnowledgeNodeList\" item=\"dto\" index=\"index\" separator=\",\">#{dto.id,jdbcType=INTEGER}</foreach>",
            " ) or exists (select 1 from t_question_main where parent_question_id=a.id and delete_flag=0 AND invalid_flag=0 AND current_version=1 and main_node_id in ( ",
            "<foreach collection=\"item.reqKnowledgeNodeList\" item=\"dto\" index=\"index\" separator=\",\">#{dto.id,jdbcType=INTEGER}</foreach>",
            " ))) ",
            "</if>",
            "<if test=\"item.sourceType != null and item.sourceType != '' \"> and a.source_type=#{item.sourceType,jdbcType=VARCHAR} </if>",
            /* 真题多考期省份注销
            "<if test='item.examSession != null'> and a.exam_session=#{item.examSession,jdbcType=INTEGER} </if>",
            "<if test='item.examProvince != null'> and a.exam_province=#{item.examProvince,jdbcType=INTEGER} </if>",*/
            "<if test=\"item.questionType != null and item.questionType != ''\"> and a.question_type=#{item.questionType,jdbcType=VARCHAR} </if>",
            "<if test=\"item.outlineRequirement != null and item.outlineRequirement != ''\">",
            " AND (ifnull(node.outline_requirement,'NOT_REQUIRED')=#{item.outlineRequirement,jdbcType=VARCHAR} or exists (select 1 from t_question_main sub,t_knowledge_node subNode where parent_question_id=a.id and subNode.id=sub.main_node_id and subNode.delete_flag=0 and sub.delete_flag=0 AND sub.invalid_flag=0 AND sub.current_version=1 and ifnull(subNode.outline_requirement,'NOT_REQUIRED')=#{item.outlineRequirement,jdbcType=VARCHAR})) ",
            "</if>",
            "<if test='item.difficultyValue != null'> and a.difficulty_value=#{item.difficultyValue,jdbcType=INTEGER} </if>",
            "<if test=\"item.beginTime != null and item.beginTime != ''\"> and date_format(a.create_time,'%Y-%m-%d') <![CDATA[>=]]> #{item.beginTime,jdbcType=TIMESTAMP} </if>",
            "<if test=\"item.endTime != null and item.endTime != ''\"> and date_format(a.create_time,'%Y-%m-%d') <![CDATA[<=]]> #{item.endTime,jdbcType=TIMESTAMP} </if>",
            "<if test=\"item.condition != null and item.condition != ''\">",
            "<if test=\"item.conditionCode == 'id'\"> ",
            "and (a.code='${item.condition}')",
            "</if>",
            "<if test=\"item.conditionCode == 'content'\">",
            "and (ess.content like '%${item.condition}%' or ess2.content like '%${item.condition}%')",
            "</if>",
            "<if test=\"item.conditionCode == 'mainNode'\">",
            "and ((node.name LIKE '%${item.condition}%' ) ",
            "or (exists (select 1 from t_question_main sub,t_knowledge_node subNode where parent_question_id=a.id and subNode.id=sub.main_node_id and subNode.delete_flag=0 and sub.delete_flag=0 AND ", " sub.current_version=1 and subNode.name LIKE '%${item.condition}%')))",
            "</if>",
            "<if test=\"item.conditionCode == 'viceNode'\">",
            "and ((a.main_node_id > 0 AND (exists (select 1 from t_knowledge_node subNode where subNode.id=a.vice_node_id_1 and subNode.delete_flag=0 and  subNode.name LIKE '%${item.condition}%'))",
            "or (exists (select 1 from t_knowledge_node subNode where subNode.id=a.vice_node_id_2 and subNode.delete_flag=0 and  subNode.name LIKE '%${item.condition}%')))",
            "or (a.main_node_id = 0 AND (exists (select 1 from t_question_main sub,t_knowledge_node subNode where parent_question_id=a.id and subNode.id=sub.vice_node_id_1 and  subNode.delete_flag=0 and sub.delete_flag=0 AND ", " sub.current_version=1 and subNode.name LIKE '%${item.condition}%'))",
            "or (exists (select 1 from t_question_main sub,t_knowledge_node subNode where parent_question_id=a.id and subNode.id=sub.vice_node_id_2 and  subNode.delete_flag=0 and sub.delete_flag=0 AND ", " sub.current_version=1 and subNode.name LIKE '%${item.condition}%'))))",
            "</if>",
            "<if test=\"item.conditionCode == ''\">",
            "and (a.code='${item.condition}' or ess.content like '%${item.condition}%' or ess2.content like '%${item.condition}%' OR node.name LIKE '%${item.condition}%' or exists (select 1 from t_question_main sub,t_knowledge_node subNode where parent_question_id=a.id and subNode.id=sub.main_node_id and subNode.delete_flag=0 and sub.delete_flag=0 AND sub.invalid_flag=0 AND sub.current_version=1 and subNode.name LIKE '%${item.condition}%'))",
            "</if>",
            "</if>",
            " order by ",
            "<if test = 'item.reqOrderList != null and item.reqOrderList.size()>0'>",
            "<foreach collection=\"item.reqOrderList\" item=\"ord\" separator=\",\">",
            "<if test=\"ord.code =='examSession' and ord.value != null and ord.value != ''\"> a.exam_session ${ord.value} </if>",
            "<if test=\"ord.code =='composeCount' and ord.value != null and ord.value != ''\"> composeCount ${ord.value} </if>",
            "<if test=\"ord.code =='difficultyValue' and ord.value != null and ord.value != ''\"> difficulty_value ${ord.value} </if>",
            "</foreach>",
            "</if>",
            "<if test=\"item.orderFlag == 1\"> , </if>",
            " a.update_time desc ",
            "<if test = 'start != null and pageSize != null'> limit #{start,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}</if>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "source_type", property = "sourceType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_type", property = "questionType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content_type", property = "contentType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_id", property = "questionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
            @Result(column = "knowledge_tree_id", property = "knowledgeTreeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "main_node_id", property = "mainNodeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "mainNodeName", property = "mainNodeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vice_node_id_1", property = "viceNodeId1", jdbcType = JdbcType.INTEGER),
            @Result(column = "viceNodeName1", property = "viceNodeName1", jdbcType = JdbcType.VARCHAR),
            @Result(column = "vice_node_id_2", property = "viceNodeId2", jdbcType = JdbcType.INTEGER),
            @Result(column = "viceNodeName2", property = "viceNodeName2", jdbcType = JdbcType.VARCHAR),
            @Result(column = "difficulty_value", property = "difficultyValue", jdbcType = JdbcType.INTEGER),
            @Result(column = "exam_province", property = "examProvince", jdbcType = JdbcType.INTEGER),
            @Result(column = "exam_session", property = "examSession", jdbcType = JdbcType.INTEGER),
            @Result(column = "current_version", property = "currentVersion", jdbcType = JdbcType.INTEGER),
            @Result(column = "parent_question_id", property = "parentQuestionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "sequence", property = "sequence", jdbcType = JdbcType.INTEGER),
            @Result(column = "invalid_flag", property = "invalidFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delete_flag", property = "deleteFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "correct_answer", property = "correctAnswer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "analysis", property = "analysis", jdbcType = JdbcType.VARCHAR),
            @Result(column = "analysisUseful", property = "analysisUseful", jdbcType = JdbcType.INTEGER),
            @Result(column = "analysisUseless", property = "analysisUseless", jdbcType = JdbcType.INTEGER),
            @Result(column = "difficulty_type", property = "difficultyType", jdbcType = JdbcType.INTEGER),
            @Result(column = "outline_requirement", property = "outlineRequirement"),
    })
    List<ResQuestionMainDTO> selectByCondition(@Param("item") ReqQuestionMainDTO reqQuestionMainDTO, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Select({"<script>",
            "SELECT question_id questionId,correct_rate correctRate FROM t_question_main_correct_rate t WHERE t.question_id IN",
            "<foreach item=\"item\" index=\"index\" collection=\"questionList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item.id}",
            "</foreach>",
            " AND delete_flag = 0 AND id = (SELECT MAX(id) FROM t_question_main_correct_rate WHERE question_id = t.question_id )",
            "</script>"})
    @MapKey("questionId")
    Map<Integer, QuestionCorrectRateDTO> selectByCorrectRate(@Param("questionList")List<ResQuestionMainDTO> questionList);

    @Select({"<script>",
            "SELECT question_id questionId,correct_rate correctRate FROM t_question_main_correct_rate t WHERE t.question_id IN",
            "<foreach item=\"item\" index=\"index\" collection=\"questionList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item.id}",
            "</foreach>",
            " AND delete_flag = 0 AND id = (SELECT MAX(id) FROM t_question_main_correct_rate WHERE question_id = t.question_id )",
            "</script>"})
    @MapKey("questionId")
    Map<Integer,QuestionCorrectRateDTO> selectByCorrectRateSys(@Param("questionList")List<QuestionForGeneratePaper> questionList);

    @Select({"<script>",
            "SELECT DISTINCT pq.question_code FROM t_paper_code pc ",
            "LEFT JOIN t_paper_question_code_rel pq ON pc.`code`=pq.paper_code ",
            "WHERE pc.type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"paperTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "AND pc.invalid_flag=0 AND pc.delete_flag=0 AND pq.delete_flag=0 ",
            "AND pq.question_code IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item.code}",
            "</foreach>",
            "</script>"})
    List<String> selectCodeByPaperType(@Param("questionList") List<ResQuestionMainDTO> questionList,
                                       @Param("paperTypeList") List<String> paperTypeList);

    /**
     * 查询大题的知识点列表
     *
     * @param idList
     * @return
     */
    @Select({
            "<script>",
            "SELECT parent_question_id as id,group_concat(concat_ws(',',main_node_id,vice_node_id_1,vice_node_id_2) SEPARATOR ',') as nodeId",
            "FROM t_question_main",
            "WHERE delete_flag=0 AND invalid_flag=0",
            "AND parent_question_id IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"idList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "GROUP BY parent_question_id;",
            "</script>"
    })
    List<Map> queryBigQuestionNodeIdList(@Param("idList") List<Integer> idList);

    /**
     * 根据条件查询试题表总表t_question_main
     *
     * @param reqQuestionMainDTO
     * @return
     */
    @Select({
            "<script>",
            " select count(a.id) ",
            " from t_question_main a   ",
            " where a.delete_flag=0 and a.parent_question_id=0 ",
            "<if test=\"item.currentVersion == null || item.currentVersion != 2\"> and a.current_version=1 </if>",//2为忽略状态
            "<if test=\"item.invalidFlag == null\"> and a.invalid_flag=0 </if>",
            "<if test=\"item.invalidFlag != null and item.invalidFlag != 2\"> and a.invalid_flag=#{item.invalidFlag,jdbcType=INTEGER} </if>",//2为全部
            " and a.knowledge_tree_id=#{item.knowledgeTreeId,jdbcType=INTEGER}",
            "</script>"
    })
    int selectTotalByKnowledgeTree(@Param("item") ReqQuestionMainDTO reqQuestionMainDTO);

    /**
     * 根据父试题总表ID查询子表列表
     *
     * @param parentMainId
     * @return
     */
    @Select({
            "<script>",
            " select a.`id`, a.`code`, a.`source_type`, a.`question_type`,a.content_type, a.`question_id`, a.`score`, a.`knowledge_tree_id`, a.`main_node_id`, a.`vice_node_id_1`, a.`vice_node_id_2`, ",
            "CASE WHEN a.`main_node_id` IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = main_node_id)",
            "ELSE null END as mainNodeName,",
            "CASE WHEN a.`main_node_id` IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = main_node_id)",
            "ELSE null END as mainNodeSerialNumber,",
            "CASE WHEN a.`vice_node_id_1` IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = vice_node_id_1)",
            "ELSE null END as viceNodeName1,",
            "CASE WHEN a.`vice_node_id_1` IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = vice_node_id_1)",
            "ELSE null END as viceNodeSerialNumber1,",
            "CASE WHEN a.`vice_node_id_2` IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = vice_node_id_2)",
            "ELSE null END as viceNodeName2,",
            "CASE WHEN a.`vice_node_id_2` IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = vice_node_id_2)",
            "ELSE null END as viceNodeSerialNumber2,",
            "CASE WHEN a.exam_session IS NOT NULL THEN (SELECT s.session FROM t_exam_session s WHERE id = a.exam_session)",
            "ELSE NULL END as examSessionName,",
            "CASE WHEN a.exam_province IS NOT NULL THEN (SELECT province_name FROM sch_local_province WHERE id = a.exam_province)",
            "ELSE NULL END as examProvinceName,",
            "(  SELECT GROUP_CONCAT( CONCAT(year,LPAD(month,2,0), d.province_name) SEPARATOR '、')",
            "   FROM t_question_main_exam_session_province as b",
            "   INNER JOIN t_exam_session as c ON c.id = b.exam_session",
            "   INNER JOIN sch_local_province as d ON d.id = b.exam_province",
            "   WHERE t_question_main_id = a.id",
            ") as examSessionAndProvinces,",
            " a.`difficulty_value`, a.`exam_province`, a.`exam_session`, a.`current_version`, a.`parent_question_id`, a.`sequence`, a.`invalid_flag`, a.`create_time`, a.`creator`, a.`update_time`, a.`operator`, a.`delete_flag`,  ",
            " case when a.content_type='ESSAY' then (select content from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select content from t_question_content_choice where id=a.question_id) else '' end content, ",
            " case when a.content_type='ESSAY' then (select analysis_useful from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis_useful from t_question_content_choice where id=a.question_id) else '' end analysisUseful, ",
            " case when a.content_type='ESSAY' then (select analysis_useless from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis_useless from t_question_content_choice where id=a.question_id) else '' end analysisUseless, ",
            " case when a.content_type='ESSAY' then (select correct_answer from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select correct_answer from t_question_content_choice where id=a.question_id) else '' end correct_answer, ",
            " case when a.content_type='ESSAY' then (select analysis from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis from t_question_content_choice where id=a.question_id) else '' end analysis, ",
            " (select used_times from t_question_compose_paper_info where question_code=a.code and delete_flag=0 limit 1) composeCount ",
            " from t_question_main a   ",
            " where a.parent_question_id=#{parentMainId,jdbcType=INTEGER} and a.delete_flag=0 ",
            "<if test=\"currentVersion == null || currentVersion != 2\"> and a.current_version=1 </if>",//2为忽略状态
            "<if test=\"invalidFlag == null\"> and a.invalid_flag=0 </if>",
            "<if test=\"invalidFlag != null and invalidFlag != 2\"> and a.invalid_flag=#{invalidFlag,jdbcType=INTEGER} </if>",//2为全部
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "source_type", property = "sourceType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_type", property = "questionType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content_type", property = "contentType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_id", property = "questionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
            @Result(column = "knowledge_tree_id", property = "knowledgeTreeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "main_node_id", property = "mainNodeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "vice_node_id_1", property = "viceNodeId1", jdbcType = JdbcType.INTEGER),
            @Result(column = "vice_node_id_2", property = "viceNodeId2", jdbcType = JdbcType.INTEGER),
            @Result(column = "mainNodeName", property = "mainNodeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "viceNodeName1", property = "viceNodeName1", jdbcType = JdbcType.VARCHAR),
            @Result(column = "viceNodeName2", property = "viceNodeName2", jdbcType = JdbcType.VARCHAR),
            @Result(column = "difficulty_value", property = "difficultyValue", jdbcType = JdbcType.INTEGER),
            @Result(column = "exam_province", property = "examProvince", jdbcType = JdbcType.INTEGER),
            @Result(column = "exam_session", property = "examSession", jdbcType = JdbcType.INTEGER),
            @Result(column = "current_version", property = "currentVersion", jdbcType = JdbcType.INTEGER),
            @Result(column = "parent_question_id", property = "parentQuestionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "sequence", property = "sequence", jdbcType = JdbcType.INTEGER),
            @Result(column = "invalid_flag", property = "invalidFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delete_flag", property = "deleteFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "correct_answer", property = "correctAnswer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "analysis", property = "analysis", jdbcType = JdbcType.VARCHAR),
            @Result(column = "examSessionName", property = "examSessionName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "examProvinceName", property = "examProvinceName", jdbcType = JdbcType.VARCHAR)
    })
    List<ResQuestionMainDTO> selectByParentMainId(@Param("parentMainId") Integer parentMainId,
                                                  @Param("invalidFlag") Integer invalidFlag,
                                                  @Param("currentVersion") Integer currentVersion);

    @Update({
            "<script>",
            " UPDATE t_question_main SET current_version=0,operator=#{operator,jdbcType=VARCHAR} where code in ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionMainCodeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    int updateParentAndChildVersionByCode(@Param("questionMainCodeList") List<String> questionMainCodeList, @Param("operator") String operator);

    /**
     * 根据code删除试卷表总表
     *
     * @param code
     * @return
     */
    @Update({
            " UPDATE t_question_main SET current_version=0 where code=#{code,jdbcType=VARCHAR} ",
    })
    int updateByCode(@Param("code") String code);

    @Select({
            "SELECT DISTINCT d.`code` from ( ",
            " SELECT a.`code` from t_question_main a where a.id = #{questionMainId} and a.delete_flag = 0 ",
            " UNION ALL ",
            " SELECT b.`code` from t_question_main b where b.parent_question_id = #{questionMainId} and b.delete_flag = 0 ",
            " UNION ALL ",
            " SELECT c.`code`  ",
            " from t_question_main b ",
            " INNER JOIN t_question_main c on c.parent_question_id = b.id and c.delete_flag = 0 ",
            " where b.delete_flag = 0 and b.parent_question_id = #{questionMainId} ",
            ") d"
    })
    List<String> selectQuestionMainCodeByParentQuestionId(@Param("questionMainId") Integer questionMainId);

    /**
     * 根据大题id列表查询
     * @param ids
     * @return
     */
    @Select({
            "<script>",
            "SELECT d.* from ( ",
            " SELECT a.id,a.code,a.score,a.difficulty_value difficultyValue,a.parent_question_id parentQuestionId,a.question_type questionType,0 sequence",
            " from t_question_main a where a.delete_flag = 0 and a.id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"ids\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            " UNION ALL ",
            " SELECT b.id,b.code,b.score,b.difficulty_value difficultyValue,b.parent_question_id parentQuestionId,b.question_type questionType,0 sequence",
            " from t_question_main b where b.delete_flag = 0 and b.parent_question_id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"ids\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            " UNION ALL ",
            " SELECT c.id,c.code,c.score,c.difficulty_value difficultyValue,c.parent_question_id parentQuestionId,c.question_type questionType,0 sequence",
            " from t_question_main b ",
            " INNER JOIN t_question_main c on c.parent_question_id = b.id and c.delete_flag = 0 ",
            " where b.delete_flag = 0 and b.parent_question_id in",
            "<foreach item=\"item\" index=\"index\" collection=\"ids\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            ") d GROUP BY d.id",
            "</script>"
    })
    List<ReqPaperQuestionDTO> selectQuestionMainByIds(@Param("ids") List<Integer> ids);

    /**
     * 启用/禁用试题
     *
     * @param childrenMainIdList
     * @param username
     * @return
     */
    @Update({
            "<script>",
            " UPDATE t_question_main SET invalid_flag=#{invalidFlag,jdbcType=INTEGER},operator=#{username,jdbcType=INTEGER} ",
            " WHERE id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"childrenMainIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    int updateParentAndChildInvalidFlagByMainId(@Param("childrenMainIdList") List<Integer> childrenMainIdList, @Param("invalidFlag") Integer invalidFlag, @Param("username") String username);


    /**
     * 智能组卷用，查找试题列表（大题:综合,完型,多选多）
     *
     * @return
     */
    @Select({
            "<script>",
            "SELECT ",
            "distinct q.id,",
            "q.question_type    AS questionType,",
            "q.difficulty_value AS difficultyValue,",
            "q.score,",
            "q.parent_question_id AS parentQuestionId",
            "FROM t_question_main q",
            "INNER JOIN t_question_main q1 on q.id = q1.parent_question_id",
            "LEFT JOIN t_question_main_exam_session_province as a ON a.t_question_main_id = q.id AND a.delete_flag = 0",
            "WHERE",
            "q.question_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "AND q.delete_flag=0 AND q.invalid_flag=0 AND q.current_version=1 ",
            "AND q1.delete_flag=0 AND q1.invalid_flag=0 AND q1.current_version=1",
            "AND q.knowledge_tree_id=#{treeId}",

            "<if test=\"sourceTypeList != null and sourceTypeList.size()>0 \">",
            "AND q.source_type IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"sourceTypeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",

            "<if test=\"examSessionList != null and examSessionList.size()>0 \">",
            "AND a.exam_session IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"examSessionList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",

            "<if test=\"examProvinceList != null and examProvinceList.size()>0 \">",
            "AND a.exam_province IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"examProvinceList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",

            "<if test=\"nodeIdList != null and nodeIdList.size()>0 \">",
            "AND (q1.main_node_id IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "or q1.vice_node_id_1 IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "or q1.vice_node_id_2 IN",
            "<foreach item=\"item\" index=\"index\" collection=\"nodeIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            ")",
            "</if>",

            "<if test=\"excludePaperTypeList != null and excludePaperTypeList.size > 0\">",
            "AND (q.code not in (SELECT DISTINCT pq.question_code FROM t_paper_code pc ",
            "LEFT JOIN t_paper_question_code_rel pq ON pc.`code`=pq.paper_code ",
            "WHERE pc.type IN ",
            "<foreach collection=\"excludePaperTypeList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "AND pc.invalid_flag=0 AND pc.delete_flag=0 AND pq.delete_flag=0 )",
            ")",
            "</if>",
            "</script>"
    })
    List<QuestionForGeneratePaper> queryBigQuestionForAutoGenPaper(@Param("nodeIdList") List<Integer> nodeIdList,
                                                                   @Param("treeId") Integer treeId,
                                                                   @Param("sourceTypeList") List<String> sourceTypeList,
                                                                   @Param("examSessionList") List<Integer> examSessionList,
                                                                   @Param("examProvinceList") List<Integer> examProvinceList,
                                                                   @Param("questionTypeList") List<String> questionTypeList,
                                                                   @Param("excludePaperTypeList") List<String> excludePaperTypeList);

    /**
     * 根据id列表查询所有子题目id列表
     *
     * @param mainIds
     * @return
     */
    @Select({
            "<script>",
            "SELECT id ",
            "FROM t_question_main",
            "WHERE delete_flag=0 AND current_version=1",
            "<if test=\"invalidFlag != null and invalidFlag != ''\"> AND invalid_flag!=#{invalidFlag}</if>",
            "AND parent_question_id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"mainIds\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<Integer> selectChildrenMainIdById(@Param("mainIds") List<Integer> mainIds, @Param("invalidFlag") Integer invalidFlag);

    /**
     * 根据试题id查询所有直属和非直属小题
     *
     * @param mainId
     * @return
     */
    @Select({
            "SELECT a.`code`, a.parent_question_id parentQuestionId,b.code parentCode,a.score",
            "from t_question_main a ",
            "inner join t_question_main b on a.parent_question_id = b.id and b.delete_flag = 0",
            "where a.delete_flag = 0 and a.parent_question_id = #{mainId}  ",
            "UNION ALL ",
            "SELECT b.`code`,b.parent_question_id parentQuestionId,a.code parentCode,b.score",
            "from t_question_main a ",
            "INNER JOIN t_question_main b on b.parent_question_id = a.id ",
            "where a.delete_flag = 0 and b.delete_flag = 0 and a.parent_question_id = #{mainId}"
    })
    List<ResQuestionParamDTO> selectAllChildrenQuestionById(@Param("mainId") Integer mainId);

    @Select({
            "SELECT a.`code`, a.parent_question_id parentQuestionId,b.code parentCode,a.score",
            "from t_question_main a ",
            "inner join t_question_main b on a.parent_question_id = b.id and b.delete_flag = 0",
            "where a.delete_flag = 0 and a.parent_question_id = #{mainId}  ",
            "UNION ALL ",
            "SELECT b.`code`,b.parent_question_id parentQuestionId,a.code parentCode,b.score",
            "from t_question_main a ",
            "INNER JOIN t_question_main b on b.parent_question_id = a.id ",
            "where a.delete_flag = 0 and b.delete_flag = 0 and a.parent_question_id = #{mainId}"
    })
    List<ResQuestionParamDTO> selectAllChildrenById(@Param("mainId") Integer mainId);

    @Select({
            "SELECT b.`code` ",
            "from t_paper_question_code_rel a ",
            "INNER JOIN t_paper_code b on a.paper_code = b.`code` ",
            "where a.delete_flag = 0 and b.delete_flag = 0 and a.question_code = #{questionCode} ",
            "GROUP BY b.`code`"
    })
    List<String> selectPaperCodeByQuestionCode(@Param("questionCode") String questionCode);

    @Update({
            "UPDATE t_paper_code a ",
            "INNER JOIN t_paper_question_code_rel b on a.`code` = b.paper_code ",
            "INNER JOIN t_paper_code_head c on c.paper_code = a.`code` ",
            "SET a.total_score = a.total_score + #{scoreDiff},a.operator = #{operator}, c.`value` = c.`value` + #{scoreDiff},c.operator = #{operator}", // ,b.score = b.score + #{scoreDiff},b.operator = #{operator} 试题在试卷内总分在前面操作中完成
            "where a.delete_flag = 0 and b.delete_flag = 0 and b.question_code = #{questionCode}  ",
            "and c.delete_flag = 0 and c.`code` = 'PAPER_HEAD_TOTAL_SCORE' and a.type = 'REAL_EXAM' "
    })
    int updatePaperQuestionScore(@Param("questionCode") String questionCode, @Param("scoreDiff") BigDecimal scoreDiff, @Param("operator") String oprator);

    /**
     * 更新真题试卷内试题分值
     *
     * @param questionCode
     * @param score
     * @param operator
     * @return
     */
    @Update({
            "UPDATE t_paper_question_code_rel a ",
            "INNER JOIN t_paper_code b on b.`code` = a.paper_code ",
            "SET a.score = #{score},a.operator = #{operator}  ",
            "where a.question_code = #{questionCode} and a.delete_flag = 0 ",
            "AND b.type = 'REAL_EXAM' and b.delete_flag = 0"
    })
    int updatePaperQuestionRelationScore(@Param("questionCode") String questionCode, @Param("score") BigDecimal score, @Param("operator") String operator);

    @Update({
            "UPDATE t_paper_question_code_rel a ",
            "SET a.delete_flag = 1 ",
            "where a.delete_flag = 0 and a.question_code = #{questionCode}"
    })
    int delPaperQuestionRelation(@Param("questionCode") String questionCode);

    @Select({
            "SELECT ifnull(COUNT(DISTINCT a.`code`),0) ",
            "FROM t_paper_code a ",
            "INNER JOIN t_paper_question_code_rel b on a.`code` = b.paper_code ",
            "where a.delete_flag = 0 AND b.delete_flag = 0 and b.question_code = #{quesitonCode} "
    })
    int selectPaperCountByQuestionCode(@Param("quesitonCode") String questionCode);

    /**
     * 删除小题时，对应更新试卷内该小题直属大题分值
     *
     * @param questionCode
     * @param operator
     * @return
     */
    @Update({
            "update t_paper_question_code_rel a ",
            "INNER JOIN t_question_main b on b.`code` = a.question_code ",
            "INNER JOIN t_question_main c on c.parent_question_id = b.id ",
            "INNER JOIN t_paper_question_code_rel d on c.`code` = d.question_code and d.paper_code = a.paper_code",// 同一试卷内
            "SET a.score = a.score - d.score, a.operator = #{operator} ",
            "WHERE a.delete_flag = 0 and b.delete_flag = 0 and c.delete_flag = 0 and d.delete_flag = 0 ",
            "AND d.question_code = #{littleQuestionCode}"
    })
    int updateDeletePaperQuestionScore(@Param("littleQuestionCode") String questionCode, @Param("operator") String operator);

    /**
     * 删除小题时，对应更新试卷内该小题非直属大题分值
     *
     * @param questionCode
     * @param operator
     * @return
     */
    @Update({
            "update t_paper_question_code_rel a ",
            "INNER JOIN t_question_main b on b.`code` = a.question_code ",
            "INNER JOIN t_question_main c on c.parent_question_id = b.id ",
            "INNER JOIN t_question_main e on e.parent_question_id = c.id ",
            "INNER JOIN t_paper_question_code_rel d on e.`code` = d.question_code and d.paper_code = a.paper_code",// 同一试卷内
            "SET a.score = a.score - d.score, a.operator = #{operator} ",
            "WHERE a.delete_flag = 0 and b.delete_flag = 0 and c.delete_flag = 0 and d.delete_flag = 0 and e.delete_flag = 0",
            "AND d.question_code = #{littleQuestionCode}"
    })
    int updateDeletePaperQuestionScore1(@Param("littleQuestionCode") String questionCode, @Param("operator") String operator);

    /**
     * 新增小题时，更新其直属大题分值
     *
     * @param questionMainId
     * @param score
     * @param operator
     * @return
     */
    @Update({
            "update t_paper_question_code_rel a ",
            "INNER JOIN t_question_main b on b.`code` = a.question_code ",
            "SET a.score = a.score + #{scoreDiff}, a.operator = #{operator} ",
            "where a.delete_flag = 0 and b.id = #{questionMainId} and b.delete_flag = 0"
    })
    int updateAddPaperQuestionScore(@Param("questionMainId") Integer questionMainId, @Param("scoreDiff") BigDecimal score, @Param("operator") String operator);

    /**
     * 有增删小题时，更新所有非真题试卷的总分
     *
     * @param paperCode
     * @param operator
     * @return
     */
    @Update({
            "UPDATE t_paper_code a ",
            "INNER JOIN t_paper_code_head b on a.`code` = b.paper_code and b.delete_flag = 0 and b.`code` = 'PAPER_HEAD_TOTAL_SCORE' ",
            "INNER JOIN ( ",
            " SELECT c.paper_code,SUM(c.score) total ",
            "  from t_paper_question_code_rel c  ",
            "  INNER JOIN t_question_main d on d.`code` = c.question_code and d.delete_flag = 0 and d.parent_question_id = 0 and d.current_version = 1", // 根据code取当前版本数据
            "  where c.delete_flag = 0 and c.paper_code = #{paperCode} ",
            ") e on e.paper_code = a.code",
            "SET a.total_score = e.total,a.operator = #{operator},b.`value` = e.total, b.operator = #{operator} ",
            "WHERE a.delete_flag = 0 and a.`code` = #{paperCode} and a.type != 'REAL_EXAM'"
    })
    int updatePaperTotalScore(@Param("paperCode") String paperCode, @Param("operator") String operator);

    /**
     * 有增删小题时，如对题量有影响，更新题量；
     *
     * @param questionCode
     * @param operator
     * @return
     */
    @Update({
            "UPDATE t_paper_code a ",
            "inner join t_paper_question_code_rel b on a.code = b.paper_code and b.question_code = #{questionCode} ",
            "SET a.question_amount = a.question_amount + #{questionAmountDifff},a.operator = #{operator} ",
            "where a.delete_flag = 0 and b.delete_flag = 0" //  AND a.type != 'REAL_EXAM' 更新所有试卷的题量
    })
    int updatePaperQuestionAmount(@Param("questionCode") String questionCode, @Param("questionAmountDifff") int questionAmount, @Param("operator") String operator);

    @Select({
            "select a.sequence,a.paper_code paperCode,a.question_code questionCode,a.score ",
            "from t_paper_question_code_rel a",
            "inner join t_paper_code b on b.code = a.paper_code and b.delete_flag = 0",
            "where a.delete_flag = 0 and a.question_code = #{questionCode}"
    })
    List<PaperQuestionRelation> selectPaperQuestionRelation(@Param("questionCode") String questionCode);

    @Insert({
            "<script>",
            "INSERT INTO t_paper_question_code_rel (sequence,question_code,paper_code,score,operator) VALUES",
            "<foreach collection=\"insertRelations\" item=\"item\"  separator=\",\">",
            "(#{item.sequence},#{item.questionCode},#{item.paperCode},#{item.score},#{item.operator})",
            "</foreach>",
            "</script>"
    })
    int insertPaperQuestionRelations(@Param("insertRelations") List<PaperQuestionRelation> insertRelations);

    @Select({
            "SELECT IFNULL(max(a.sequence),0)  ",
            "from t_paper_question_code_rel a ",
            "INNER JOIN t_question_main b on b.`code` = a.question_code and b.delete_flag = 0 ",
            "INNER JOIN t_question_main c on c.id = b.parent_question_id and c.delete_flag = 0 ",
            "INNER JOIN t_paper_question_code_rel d on d.question_code = c.`code` and d.paper_code = a.paper_code and d.delete_flag = 0 ",
            "WHERE a.delete_flag = 0 and a.paper_code = #{paperCode} and c.`code` = #{questionCode}"
    })
    int selectMaxSequenceByCondition(@Param("paperCode") String paperCode, @Param("questionCode") String questionCode);

    @Select({
            "SELECT a.`code`,a.score from t_question_main a where a.delete_flag = 0 and a.parent_question_id = #{questionMainId}"
    })
    List<QuestionMain> selectByParentQuestionId(@Param("questionMainId") Integer questionMainId);

    @Update({
            "update t_paper_question_code_rel ",
            "SET sequence = sequence + #{questionAmountDiff},operator =#{operator} ",
            "where delete_flag = 0 and paper_code = #{paperCode} and sequence > #{sequence}"
    })
    int updateOtherQuestionSequence(@Param("paperCode") String paperCode, @Param("sequence") Integer sequence, @Param("questionAmountDiff") int questionAmountDiff, @Param("operator") String username);

    @Update({
            "update t_paper_question_code_rel a ",
            "INNER JOIN t_question_main b on a.question_code = b.`code` and b.delete_flag = 0 ",
            "INNER JOIN t_question_main c on c.id = b.parent_question_id and c.delete_flag = 0  ",
            "INNER JOIN t_paper_question_code_rel d on d.question_code = c.`code` and d.paper_code = a.paper_code and d.delete_flag = 0 ",
            "SET a.sequence = a.sequence - 1,a.operator =#{operator}   ",
            "where a.delete_flag = 0 and a.paper_code = #{paperCode} and a.sequence > #{sequence} and c.id = #{parentQuestionId}"
    })
    int updateBrotherQuestionSequence(@Param("paperCode") String paperCode, @Param("sequence") Integer sequence, @Param("operator") String username, @Param("parentQuestionId") Integer parentQuestionId);

    @Select({
            "SELECT b.correct_answer answer  ",
            "from t_question_main a  ",
            "INNER JOIN t_question_content_choice b on a.question_id = b.id  ",
            "WHERE a.delete_flag = 0 and b.delete_flag = 0  ",
            "AND a.id = #{questionId}"
    })
    String queryChoiceQuestionAnswer(@Param("questionId") Integer questionId);

    @Select({
            "SELECT b.answer  ",
            "from t_question_main a  ",
            "INNER JOIN t_question_content_fill_blank b on a.question_id = b.id  ",
            "where a.delete_flag = 0 and b.delete_flag = 0  ",
            "and a.id = #{questionId}"
    })
    String queryOrderBlankQuestionAnswer(@Param("questionId") Integer questionId);

    @Select({
            "SELECT b.correct_answer answer  ",
            "from t_question_main a  ",
            "INNER JOIN t_question_content_essay b on a.question_id = b.id ",
            "where a.delete_flag = 0 and b.delete_flag = 0  ",
            "AND a.id = #{questionId}"
    })
    String queryEssayQuestionAnswer(@Param("questionId") Integer questionId);

    /**
     * 查询文字题得分点数量
     *
     * @param questionId
     * @return
     */
    @Select({
            "SELECT count(a.id) from t_question_content_essay_score_point a where a.delete_flag = 0 and a.essay_id = #{questionId}"
    })
    int hasScorePoint(@Param("questionId") Integer questionId);

    /**
     * 根据试题id查询文字题得分点数量
     *
     * @param questionMainId
     * @return
     */
    @Select({
            "SELECT count(a.id) ",
            "from t_question_content_essay_score_point a ",
            "inner join t_question_main b on a.essay_id = b.question_id and b.delete_flag = 0",
            "where a.delete_flag = 0 and b.id = #{questionMainId}"
    })
    int queryScorePointList(@Param("questionMainId") Integer questionMainId);

    @Select({
            "SELECT count(a.id)  ",
            "from t_question_content_essay_score_point a  ",
            "inner join t_question_main b on a.essay_id = b.question_id and b.delete_flag = 0 ",
            "where a.delete_flag = 0 and b.parent_question_id = #{questionMainId} and b.question_type = 'ESSAY'"
    })
    int queryScorePointCountByParentQuestionMainId(@Param("questionMainId") Integer questionMainId);

    /**
     * 根据试题Code批量查询关联的试卷
     *
     * @param codeList
     * @return
     */
    @Select({
            "<script>",
            "SELECT b.`code` ",
            "from t_paper_question_code_rel a ",
            "INNER JOIN t_paper_code b on a.paper_code = b.`code` ",
            "where a.delete_flag = 0 and b.delete_flag = 0 and a.question_code IN ('-1', ",
            "<foreach collection=\"codeList\" item=\"item\"  separator=\",\">",
            "#{item}",
            "</foreach>",
            ")",
            "GROUP BY b.`code`",
            "</script>",
    })
    List<String> selectPaperCodeByQuestionCodeList(@Param("codeList") List<String> codeList);

    @Select({
            "<script>",
            "SELECT COUNT(DISTINCT if(m.question_type = 'SINGLE_CHOICE',m.id,NULL)) singChoiceQuestions,",
            "COUNT(DISTINCT if(m.question_type = 'MULTI_CHOICE',m.id,NULL)) multiChoiceQuestions,",
            "COUNT(DISTINCT if(m.question_type = 'JUDGE_CHOICE',m.id,NULL)) judgeChoiceQuestions,",
            "COUNT(DISTINCT if(m.question_type='ORDER_FILL_BLANK' or m.question_type='DISORDER_FILL_BLANK',m.id, NULL)) blankQuestions,",
            "COUNT(DISTINCT if(m.question_type = 'JUDGE_ESSAY',m.id,NULL)) judgeEssayQuestions,",
            "COUNT(DISTINCT if(m.question_type = 'ESSAY',m.id,NULL)) essayQuestions",
            "FROM t_question_main m ",
            "<if test=\"(examSessionList != null and examSessionList.size > 0) or (examProvinceList != null and examProvinceList.size > 0)\">",
            "INNER JOIN t_question_main_exam_session_province sp ON sp.t_question_main_id = m.id AND sp.delete_flag = 0",
            "<if test=\"examSessionList != null and examSessionList.size > 0\">",
            "AND sp.exam_session in ",
            "<foreach collection=\"examSessionList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"examProvinceList != null and examProvinceList.size > 0\">",
            "AND sp.exam_province in ",
            "<foreach collection=\"examProvinceList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "</if>",
            "</if>",
            "WHERE m.knowledge_tree_id = #{knowledgeTreeId} ",
            "<if test=\"nodeIdList != null and nodeIdList.size > 0\">",
            "AND (m.main_node_id in ",
            "<foreach collection=\"nodeIdList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "OR m.vice_node_id_1 in ",
            "<foreach collection=\"nodeIdList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "OR m.vice_node_id_2 in ",
            "<foreach collection=\"nodeIdList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            ")",
            "</if>",
            "<if test=\"sourceTypeList != null and sourceTypeList.size > 0\">",
            "AND m.source_type in ",
            "<foreach collection=\"sourceTypeList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"difficult != null and difficult != 0\">",
            "AND m.difficulty_value=#{difficult}",
            "</if>",
            "<if test=\"excludePaperTypeList != null and excludePaperTypeList.size > 0\">",
            "AND (m.code not in (SELECT DISTINCT pq.question_code FROM t_paper_code pc ",
            "LEFT JOIN t_paper_question_code_rel pq ON pc.`code`=pq.paper_code ",
            "WHERE pc.type IN ",
            "<foreach collection=\"excludePaperTypeList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "AND pc.invalid_flag=0 AND pc.delete_flag=0 AND pq.delete_flag=0 )",
            ")",
            "</if>",
            "and m.delete_flag = 0 and m.invalid_flag = 0 and m.current_version = 1 and m.parent_question_id = 0",
            "</script>"
    })
    ResQuestionCountDTO selectNormalQuestionCount(@Param("knowledgeTreeId") Integer knowledgeTreeId,
                                                  @Param("nodeIdList") List<Integer> nodeIdList,
                                                  @Param("sourceTypeList") List<String> sourceTypeList,
                                                  @Param("examSessionList") List<Integer> examSessionList,
                                                  @Param("examProvinceList") List<Integer> examProvinceList,
                                                  @Param("difficult") Integer difficult,
                                                  @Param("excludePaperTypeList")List<String> excludePaperTypeList);

    @Select({
            "<script>",
            "select count(DISTINCT IF(m.question_type = 'COMPREHENSIVE', m.id, NULL)) comprehensiveQuestions,",
            "count(DISTINCT IF(m.question_type = 'READING_COMPREHENSION', m.id, NULL)) readingCompQuestions,",
            "count(DISTINCT IF(m.question_type = 'MANY_TO_MANY', m.id, NULL)) manyToMany ",
            "FROM t_question_main m inner join t_question_main c on m.id = c.parent_question_id ",
            "and c.delete_flag = 0 and c.invalid_flag = 0 and c.current_version = 1",
            "<if test=\"(examSessionList != null and examSessionList.size > 0) or (examProvinceList != null and examProvinceList.size > 0)\">",
            "INNER JOIN t_question_main_exam_session_province sp ON sp.t_question_main_id = m.id AND sp.delete_flag = 0",
            "<if test=\"examSessionList != null and examSessionList.size > 0\">",
            "AND sp.exam_session in ",
            "<foreach collection=\"examSessionList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"examProvinceList != null and examProvinceList.size > 0\">",
            "AND sp.exam_province in ",
            "<foreach collection=\"examProvinceList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "</if>",
            "</if>",
            "WHERE m.knowledge_tree_id = #{knowledgeTreeId} ",
            "<if test=\"nodeIdList != null and nodeIdList.size > 0\">",
            "AND (c.main_node_id in ",
            "<foreach collection=\"nodeIdList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "OR c.vice_node_id_1 in ",
            "<foreach collection=\"nodeIdList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "OR c.vice_node_id_2 in ",
            "<foreach collection=\"nodeIdList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            ")",
            "</if>",
            "<if test=\"sourceTypeList != null and sourceTypeList.size > 0\">",
            "AND m.source_type in ",
            "<foreach collection=\"sourceTypeList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"difficult != null and difficult != 0\">",
            "AND m.difficulty_value=#{difficult}",
            "</if>",
            "<if test=\"excludePaperTypeList != null and excludePaperTypeList.size > 0\">",
            "AND (m.code not in (SELECT DISTINCT pq.question_code FROM t_paper_code pc ",
            "LEFT JOIN t_paper_question_code_rel pq ON pc.`code`=pq.paper_code ",
            "WHERE pc.type IN ",
            "<foreach collection=\"excludePaperTypeList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "AND pc.invalid_flag=0 AND pc.delete_flag=0 AND pq.delete_flag=0 )",
            ")",
            "</if>",
            "and m.delete_flag = 0 and m.invalid_flag = 0 and m.current_version = 1 and m.parent_question_id = 0 ",
            "</script>"
    })
    ResQuestionCountDTO selectBigQuestionCount(@Param("knowledgeTreeId") Integer knowledgeTreeId,
                                               @Param("nodeIdList") List<Integer> nodeIdList,
                                               @Param("sourceTypeList") List<String> sourceTypeList,
                                               @Param("examSessionList") List<Integer> examSessionList,
                                               @Param("examProvinceList") List<Integer> examProvinceList,
                                               @Param("difficult") Integer difficult,
                                               @Param("excludePaperTypeList")List<String> excludePaperTypeList);

    /**
     * 根据主键批量查询其中为当前版本的题目
     *
     * @param idList 试题idList
     * @return
     */
    @Select({"<script>",
            "SELECT ques.id, ques.`code`, ques.source_type sourceType, ques.question_type questionType,ques.content_type contentType,",
            "ques.question_id questionId,ques.score, ques.knowledge_tree_id knowledgeTreeId, ques.main_node_id mainNodeId,",
            "ques.vice_node_id_1 viceNodeId1, ques.vice_node_id_2 viceNodeId2, ques.difficulty_value difficultyValue,",
            "ques.exam_province examProvince,ques.exam_session examSession,",
            "ques.current_version currentVersion, ques.parent_question_id parentQuestionId, ques.sequence, ",
            "ques.invalid_flag invalidFlag ",
            "from t_question_main ques ",
            "WHERE delete_flag = 0 and current_version = 1 and id in",
            "<foreach item=\"item\" index=\"index\" collection=\"idList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</script>"})
    List<QuestionMain> selectCurrentListAmongQuestions(@Param("idList") List<Integer> idList);

    @Select({
            "<script>",
            "select t_question_main_id questionMainId, exam_session examSessionId, exam_tag examTagId, exam_province examProvinceId ",
            "from t_question_main_exam_session_province ",
            "where delete_flag = 0 and t_question_main_id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"idList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<QuestionMainExamSessionAndProvince> selectQuestionMainExamSessionAndProvinces(@Param("idList") List<Integer> idList);

    @Insert({
            "<script>",
            "Insert ignore t_question_main_exam_session_province (t_question_main_id, exam_session, exam_province, exam_tag, creator, operator) values",
            "<foreach item=\"item\" index=\"index\" collection=\"examSessionAndProvinces\" separator=\",\" >",
            "(#{item.newQuestionMainId}, #{item.examSessionId}, #{item.examProvinceId}, #{item.examTagId}, #{username}, #{username})",
            "</foreach>",
            "</script>"
    })
    int batchInsertQuestionMainExamSessionAndProvinces(@Param("examSessionAndProvinces") List<QuestionMainExamSessionAndProvince> examSessionAndProvinces,
                    @Param("username") String username);

    /**
     * 根据试题code获取试题解析
     * @param idList
     * @return
     */
    @Select({
            "<script>",
            "SELECT a.id id, a.code code, ",
            "CASE WHEN a.content_type = 'ESSAY' THEN ",
            "(SELECT analysis FROM t_question_content_essay ",
            "WHERE id = a.question_id ) ",
            "WHEN a.content_type = 'CHOICE' THEN ",
            "(SELECT analysis FROM  t_question_content_choice WHERE id = a.question_id ) ",
            "ELSE '' END analysis, ",
            "CASE WHEN a.content_type = 'ESSAY' THEN ",
            "(SELECT analysis_useful FROM t_question_content_essay ",
            "WHERE id = a.question_id ) ",
            "WHEN a.content_type = 'CHOICE' THEN ",
            "(SELECT analysis_useful FROM  t_question_content_choice WHERE id = a.question_id ) ",
            "ELSE '' END analysisUseful, ",
            "CASE WHEN a.content_type = 'ESSAY' THEN ",
            "(SELECT analysis_useless FROM t_question_content_essay ",
            "WHERE id = a.question_id ) ",
            "WHEN a.content_type = 'CHOICE' THEN ",
            "(SELECT analysis_useless FROM  t_question_content_choice WHERE id = a.question_id ) ",
            "ELSE '' END analysisUseless ",
            "FROM t_question_main a ",
            "WHERE a.id in ",
            "<foreach collection=\"idList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<QuestionAnalysisContentDTO> selectAnalysisContent(@Param("idList") Collection<Integer> idList);

    /**
     * 根据老试题Id查找对应解析统计表中的数据以及新试题id
     * @param oldQuestionIds
     * @return
     */
    @Select({
            "<script>",
            "SELECT s.id id, s.question_id oldQuestionId, m.id newQuestionId, s.stu_id stuId",
            "FROM t_tiku_user_question_analysis_statistics s ",
            "INNER JOIN t_question_main m1 ON m1.id = s.question_id ",
            "INNER JOIN t_question_main m ON m.code = m1.`code` ",
            "AND m.current_version = 1 AND m.invalid_flag=0 AND m.delete_flag = 0 ",
            "WHERE s.question_id in ",
            "<foreach collection=\"oldQuestionIds\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<QuestionAnalysisDTO> selectQuestionAnalysis(@Param("oldQuestionIds") Collection<Integer> oldQuestionIds);

    /**
     * 根据修改前的试题id更新试题解析数据统计表
     * @param list
     */
    @Select({
            "<script>",
            "INSERT INTO t_tiku_user_question_analysis_statistics (id, question_id, stu_id) VALUES",
            "<foreach collection=\"list\" item=\"item\" separator=\",\" >",
            "(#{item.id}, #{item.newQuestionId}, #{item.stuId})",
            "</foreach>",
            " ON DUPLICATE KEY UPDATE question_id=VALUES(question_id),stu_id = VALUES(stu_id) ",
            "</script>"
    })
    void updateAnalysisStat(@Param("list")List<QuestionAnalysisDTO> list);

    @Select({
            "<script>",
            "SELECT IF(m1.main_node_id = 0,m2.main_node_id, m1.main_node_id) mainNodeId ",
            "FROM t_question_main m1 LEFT JOIN t_question_main m2 ON m2.parent_question_id = m1.id ",
            "AND m2.current_version = 1 AND m2.delete_flag = 0 ",
            "WHERE m1.id IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"ids\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "AND m1.current_version = 1 AND m1.delete_flag = 0 ",
            "</script>"
    })
    List<QuestionMain> getNodeIdListByMainId(@Param("ids") List<Integer> questionMainId);

    /**
     * 获取知识点下所有大题id
     *
     * @param nodeId
     * @return
     */
    @Select({"SELECT DISTINCT IF(d.id IS NULL, IF(c.parent_question_id = 0, c.id, c.parent_question_id), IF(d.parent_question_id = 0, d.id,d.parent_question_id)) as questionId",
            "FROM t_knowledge_node as a",
            "INNER JOIN t_knowledge_node as b ON b.knowledge_tree_id = a.knowledge_tree_id AND SUBSTRING_INDEX(b.serial_number,'.',a.`level`) = a.serial_number AND b.invalid_flag = 0 AND b.delete_flag = 0",
            "INNER JOIN t_question_main as c ON c.main_node_id = b.id AND c.current_version = 1 AND c.invalid_flag = 0 AND c.delete_flag = 0",
            "LEFT JOIN t_question_main as d ON d.id = c.parent_question_id AND d.current_version = 1 AND d.invalid_flag = 0 AND d.delete_flag = 0",
            "WHERE a.id = #{nodeId} AND a.invalid_flag = 0 AND a.delete_flag = 0",
            "ORDER BY questionId ASC"})
    Set<Integer> selectAllQuestionByNodeId(@Param("nodeId") Integer nodeId);

    /**
     * 查找大题列表所有的小题id
     *
     * @param questionMainIdList
     * @return
     */
    @Select({"<script>",
            "SELECT IFNULL(c.id, IFNULL(b.id, a.id))",
            "FROM t_question_main as a",
            "LEFT JOIN t_question_main as b ON b.parent_question_id = a.id AND b.invalid_flag = 0 AND b.delete_flag = 0 AND b.current_version = 1",
            "LEFT JOIN t_question_main as c ON c.parent_question_id = b.id AND c.invalid_flag = 0 AND c.delete_flag = 0 AND c.current_version = 1",
            "WHERE a.id IN",
            "<foreach item=\"questionId\" collection=\"questionMainIdList\" open=\"(\" separator=\",\" close=\")\" >",
            "#{questionId}",
            "</foreach>",
            "</script>"
    })
    Set<Integer> selectQuestionSubIdByMainIdList(@Param("questionMainIdList") Collection<Integer> questionMainIdList);

    /**
     * 获取小题集合的大题id
     *
     * @param questionIdList
     * @return
     */
    @Select({"<script>",
            "SELECT a.id, IF(b.id IS NULL, IF(a.parent_question_id = 0, a.id, a.parent_question_id), IF(b.parent_question_id = 0, b.id, b.parent_question_id)) as questionId",
            "FROM t_question_main as a",
            "LEFT JOIN t_question_main as b ON b.id = a.parent_question_id AND b.delete_flag = 0",
            "WHERE a.delete_flag = 0 AND a.id IN",
            "<foreach collection='questionIdList' item='questionId' open='(' close=')' separator=','> #{questionId} </foreach>",
            "ORDER BY a.id ASC",
            "</script>"})
    @MapKey("id")
    Map<Integer, ResQuestionMainDTO> selectMainQuestionId(@Param("questionIdList") Collection<Integer> questionIdList);

    /**
     * 查询试题题干等详情
     *
     * @param questionIds
     * @param paperId
     * @return
     */
    @Select({
            "<script>",
            " select a.`id`, a.`code`, a.`source_type`, a.`question_type`,a.content_type, a.`question_id`, r.score, a.`knowledge_tree_id`, ",
            "CASE WHEN exam_session IS NOT NULL THEN (SELECT s.session FROM t_exam_session s WHERE id = exam_session)",
            "ELSE NULL END as examSessionName,",
            "CASE WHEN exam_province IS NOT NULL THEN (SELECT province_name FROM sch_local_province WHERE id = exam_province)",
            "ELSE NULL END as examProvinceName,",
            "a.`difficulty_value`,a.`difficulty_type`, a.`exam_province`, a.`exam_session`, a.`current_version`, a.`parent_question_id`,",
            " r.`sequence`, a.`invalid_flag`, a.`create_time`, a.`creator`, a.`update_time`, a.`operator`, a.`delete_flag`,  ",
            " case when a.content_type='ESSAY' then (select content from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select content from t_question_content_choice where id=a.question_id) else '' end content, ",
            " case when a.content_type='ESSAY' then (select correct_answer from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select correct_answer from t_question_content_choice where id=a.question_id) else '' end correct_answer, ",
            " case when a.content_type='ESSAY' then (select analysis from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis from t_question_content_choice where id=a.question_id) else '' end analysis, ",
            " node.outline_requirement ",
            " from t_question_main a ",
            "INNER JOIN t_paper_question_rel r ON r.paper_id = #{paperId} AND r.question_main_id = a.id ",
            " left join t_knowledge_node node on node.id=a.main_node_id and node.delete_flag=0",
            " where a.id in ",
            "<foreach item=\"questionId\" collection=\"questionIds\" open=\"(\" separator=\",\" close=\")\" >",
            "#{questionId}",
            "</foreach>",
            "and a.delete_flag=0 and a.parent_question_id=0 ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "source_type", property = "sourceType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_type", property = "questionType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content_type", property = "contentType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_id", property = "questionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
            @Result(column = "knowledge_tree_id", property = "knowledgeTreeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "difficulty_value", property = "difficultyValue", jdbcType = JdbcType.INTEGER),
            @Result(column = "exam_province", property = "examProvince", jdbcType = JdbcType.INTEGER),
            @Result(column = "exam_session", property = "examSession", jdbcType = JdbcType.INTEGER),
            @Result(column = "current_version", property = "currentVersion", jdbcType = JdbcType.INTEGER),
            @Result(column = "parent_question_id", property = "parentQuestionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "sequence", property = "sequence", jdbcType = JdbcType.INTEGER),
            @Result(column = "invalid_flag", property = "invalidFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delete_flag", property = "deleteFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "correct_answer", property = "correctAnswer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "analysis", property = "analysis", jdbcType = JdbcType.VARCHAR),
            @Result(column = "difficulty_type", property = "difficultyType", jdbcType = JdbcType.INTEGER),
            @Result(column = "outline_requirement", property = "outlineRequirement"),
            @Result(column = "examSessionName", property = "examSessionName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "examProvinceName", property = "examProvinceName", jdbcType = JdbcType.VARCHAR)
    })
    List<ResQuestionMainDTO> selectQuestionDetailByIds(@Param("questionIds") List<Integer> questionIds, @Param("paperId") Integer paperId);



    /**
     * 根据父试题总表ID查询子表列表
     *
     * @param parentMainId
     * @return
     */
    @Select({
            "<script>",
            " select a.`id`, a.`code`, a.`source_type`, a.`question_type`,a.content_type, a.`question_id`, r.`score`, a.`knowledge_tree_id`, a.`main_node_id`, a.`vice_node_id_1`, a.`vice_node_id_2`, ",
            "CASE WHEN a.`main_node_id` IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = main_node_id)",
            "ELSE null END as mainNodeName,",
            "CASE WHEN a.`main_node_id` IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = main_node_id)",
            "ELSE null END as mainNodeSerialNumber,",
            "CASE WHEN a.`vice_node_id_1` IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = vice_node_id_1)",
            "ELSE null END as viceNodeName1,",
            "CASE WHEN a.`vice_node_id_1` IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = vice_node_id_1)",
            "ELSE null END as viceNodeSerialNumber1,",
            "CASE WHEN a.`vice_node_id_2` IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = vice_node_id_2)",
            "ELSE null END as viceNodeName2,",
            "CASE WHEN a.`vice_node_id_2` IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = vice_node_id_2)",
            "ELSE null END as viceNodeSerialNumber2,",
            "CASE WHEN a.exam_session IS NOT NULL THEN (SELECT s.session FROM t_exam_session s WHERE id = a.exam_session)",
            "ELSE NULL END as examSessionName,",
            "CASE WHEN a.exam_province IS NOT NULL THEN (SELECT province_name FROM sch_local_province WHERE id = a.exam_province)",
            "ELSE NULL END as examProvinceName,",
            " a.`difficulty_value`, a.`exam_province`, a.`exam_session`, a.`current_version`, a.`parent_question_id`, r.`sequence`, a.`invalid_flag`, a.`create_time`, a.`creator`, a.`update_time`, a.`operator`, a.`delete_flag`,  ",
            " case when a.content_type='ESSAY' then (select content from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select content from t_question_content_choice where id=a.question_id) else '' end content, ",
            " case when a.content_type='ESSAY' then (select analysis_useful from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis_useful from t_question_content_choice where id=a.question_id) else '' end analysisUseful, ",
            " case when a.content_type='ESSAY' then (select analysis_useless from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis_useless from t_question_content_choice where id=a.question_id) else '' end analysisUseless, ",
            " case when a.content_type='ESSAY' then (select correct_answer from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select correct_answer from t_question_content_choice where id=a.question_id) else '' end correct_answer, ",
            " case when a.content_type='ESSAY' then (select analysis from t_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis from t_question_content_choice where id=a.question_id) else '' end analysis, ",
            " (select used_times from t_question_compose_paper_info where question_code=a.code and delete_flag=0 limit 1) composeCount ",
            " from t_question_main a",
            "INNER JOIN t_paper_question_rel r ON r.paper_id = #{paperId} AND r.question_main_id = a.id ",
            " where a.parent_question_id=#{parentMainId,jdbcType=INTEGER} and a.delete_flag=0 ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "source_type", property = "sourceType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_type", property = "questionType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content_type", property = "contentType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_id", property = "questionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "score", property = "score", jdbcType = JdbcType.DECIMAL),
            @Result(column = "knowledge_tree_id", property = "knowledgeTreeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "main_node_id", property = "mainNodeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "vice_node_id_1", property = "viceNodeId1", jdbcType = JdbcType.INTEGER),
            @Result(column = "vice_node_id_2", property = "viceNodeId2", jdbcType = JdbcType.INTEGER),
            @Result(column = "mainNodeName", property = "mainNodeName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "viceNodeName1", property = "viceNodeName1", jdbcType = JdbcType.VARCHAR),
            @Result(column = "viceNodeName2", property = "viceNodeName2", jdbcType = JdbcType.VARCHAR),
            @Result(column = "difficulty_value", property = "difficultyValue", jdbcType = JdbcType.INTEGER),
            @Result(column = "exam_province", property = "examProvince", jdbcType = JdbcType.INTEGER),
            @Result(column = "exam_session", property = "examSession", jdbcType = JdbcType.INTEGER),
            @Result(column = "current_version", property = "currentVersion", jdbcType = JdbcType.INTEGER),
            @Result(column = "parent_question_id", property = "parentQuestionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "sequence", property = "sequence", jdbcType = JdbcType.INTEGER),
            @Result(column = "invalid_flag", property = "invalidFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delete_flag", property = "deleteFlag", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "correct_answer", property = "correctAnswer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "analysis", property = "analysis", jdbcType = JdbcType.VARCHAR),
            @Result(column = "examSessionName", property = "examSessionName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "examProvinceName", property = "examProvinceName", jdbcType = JdbcType.VARCHAR)
    })
    List<ResQuestionMainDTO> selectByParentMainIdWithPapperScore(@Param("parentMainId") Integer parentMainId, @Param("paperId") Integer paperId);

    /**
     * 获取试题各个维度数据
     * @param questionIds
     * @return java.util.List<com.sunlands.question.dto.QuestionAllAnalysisDTO>
     * @author suntenghao
     * @date 2020-04-07
     */
    @Select({"<script>",
            "SELECT qm.id questionId, qm.`code` questionCode, qm.question_type typeCode, " ,
            "qm.difficulty_value difficult, qm.main_node_id knowledgeNodeId, " ,
            "kn.`name` knowledgeNodeName, lkn.knowledge_node_frequentness frequencyLevel " ,
            "FROM t_question_main qm " ,
            "LEFT JOIN t_knowledge_node kn ON qm.main_node_id=kn.id " ,
            "LEFT JOIN t_last_knowledge_node lkn ON qm.main_node_id=lkn.knowledge_node_id AND qm.delete_flag=0 " ,
            "WHERE qm.id IN ",
            "<foreach item=\"questionId\" collection=\"questionIds\" open=\"(\" separator=\",\" close=\")\" >",
            "#{questionId}",
            "</foreach>",
            "</script>"})
    List<QuestionAllAnalysisDTO> getQuestionAllAnalysis(@Param("questionIds")Collection<Integer> questionIds);

    /**
     * 试卷中题目是否在其他试卷中出现过 （限定模考和考前 5 套卷）
     * @param questionIds
     * @param knowledgeTreeId
     * @param paperCode
     * @return java.util.List<com.sunlands.question.dto.QuestionPaperDetailDTO>
     * @author suntenghao
     * @date 2020-04-07
     */
    @Select({"<script>",
            "SELECT qm.id questionId, pc.`code` paperCode, pc.`name` paperName, pc.type paperTypeCode",
            "FROM t_question_main qm ",
            "LEFT JOIN t_paper_question_code_rel pqcr ON qm.code=pqcr.question_code ",
            "LEFT JOIN t_paper_code pc ON pc.code=pqcr.paper_code ",
            "WHERE qm.id IN  ",
            "<foreach item=\"questionId\" collection=\"questionIds\" open=\"(\" separator=\",\" close=\")\" >",
            "#{questionId}",
            "</foreach>",
            "AND pqcr.delete_flag=0 ",
            "AND pc.invalid_flag=0 AND pc.delete_flag=0 AND pc.type IN ('MODEL_EXAM','MOCK_EXAM') ",
            "AND pc.knowledge_tree_id=#{knowledgeTreeId} ",
            "<if test=\"paperCode != null \">",
            "AND pc.`code`!=#{paperCode}",
            "</if>",
            "</script>"})
    List<QuestionPaperDetailDTO> getQuestionPaperAnalysis(@Param("questionIds") Collection<Integer> questionIds,
                                          @Param("knowledgeTreeId") Integer knowledgeTreeId,
                                          @Param("paperCode") String paperCode);

    @Select({"<script>",
            "SELECT question_main_id",
            "FROM t_current_question_main",
            "WHERE invalid_flag = 0",
            "<if test=\"level == 1\"> AND node_first_id = #{nodeId} </if>",
            "<if test=\"level == 2\"> AND node_second_id = #{nodeId} </if>",
            "<if test=\"level == 3\"> AND node_third_id = #{nodeId} </if>",
            "<if test=\"level == 4\"> AND node_fourth_id = #{nodeId} </if>",
            "ORDER BY question_main_id ASC",
            "</script>"})
    List<Integer> listCurrentQuestionMainIdListByNode(@Param("nodeId") int nodeId,
                                                      @Param("level") int level);

    @Select({
            "<script>",
            "select rel1.t_question_main_id id,",
            "   GROUP_CONCAT(DISTINCT CONCAT(c.session, d.province_name, ifnull(e.name, '')) ORDER BY c.`session` desc,d.province_name, e.id ASC SEPARATOR '、') examSessionAndProvinces",
            "from t_question_main_exam_session_province rel1",
            "INNER JOIN t_exam_session as c ON c.id = rel1.exam_session",
            "INNER JOIN sch_local_province as d ON d.id = rel1.exam_province",
            "LEFT JOIN t_exam_tag as e ON e.id = rel1.exam_tag AND e.delete_flag = 0",
            "<if test=\"examSession != null or examProvince != null or examTag != null\">",
            "INNER JOIN t_question_main_exam_session_province rel2 on rel1.t_question_main_id = rel2.t_question_main_id",
            "<if test=\"examSession != null and examSession.size() > 0\"> and rel2.exam_session IN",
            "<foreach collection='examSession' item='session' open='(' close=')' separator=','> #{session} </foreach>",
            "</if>",
            "<if test=\"examProvince != null and examProvince.size() > 0\"> and rel2.exam_province IN",
            "<foreach collection='examProvince' item='province' open='(' close=')' separator=','> #{province} </foreach>",
            "</if>",
            "<if test=\"examTag != null and examTag.size() > 0\"> and rel2.exam_tag IN",
            "<foreach collection='examTag' item='tag' open='(' close=')' separator=','> #{tag} </foreach>",
            "</if>",
            "and rel2.delete_flag=0",
            "</if>",
            "WHERE rel1.t_question_main_id in ",
            "<foreach item=\"item\" collection=\"questionMainList\" open=\"(\" separator=\",\" close=\")\" >",
            "#{item}",
            "</foreach>",
            "and rel1.delete_flag=0",
            "GROUP BY rel1.t_question_main_id",
            "</script>"
    })
    @MapKey("id")
    Map<Integer, ResQuestionMainDTO> selectQuestionIdsByExamSessionAndProvince(@Param("examProvince") List<Integer> examProvinceList,
                                                                               @Param("examSession") List<Integer> examSessionList,
                                                                               @Param("examTag") List<Integer> examTagList,
                                                                               @Param("questionMainList") List<Integer> questionMainIdList);

    @Select({"SELECT source_type",
            "FROM t_question_main",
            "WHERE id = #{id}"})
    String selectSourceTypeById(@Param("id") Integer id);

    @Select({"SELECT a.id as mainId, b.id as secondMainId, c.id as lastMainId",
            "FROM t_question_main as a ",
            "LEFT JOIN t_question_main as b ON b.parent_question_id = a.id ",
            "LEFT JOIN t_question_main as c ON c.parent_question_id = b.id ",
            "WHERE a.id = #{id}"})
    List<QuestionMainIdDTO> selectAllQuestionMainId(@Param("id") Integer id);

    @Insert({"<script>",
            "INSERT INTO t_question_main_exam_session_province(exam_session, exam_province, exam_tag, t_question_main_id, operator, creator) VALUES",
            "<foreach collection=\"questionIdList\" item=\"questionId\" separator=\",\">",
            "   <foreach collection=\"examLabelList\" item=\"label\" separator=\",\">",
            "       (#{label.examSession}, #{label.examProvince}, #{label.examTag}, #{questionId}, #{username}, #{username})",
            "   </foreach>",
            "</foreach>",
            "</script>"})
    void insertExamSessionAndProvince(@Param("questionIdList") List<Integer> questionIdList,
                                      @Param("examLabelList") List<ExamLabelDTO> examLabelList,
                                      @Param("username") String username);

    @Select({
            "SELECT",
            " a.`code` paperCode, b.score,b.question_code questionCode,b.sequence ",
            "FROM t_paper_code a",
            "INNER JOIN t_paper_question_code_rel b ON a.`code` = b.paper_code",
            "AND b.delete_flag = 0",
            "WHERE",
            " a.delete_flag = 0",
            "AND b.question_code = #{questionMainCode}",
            "AND a.knowledge_tree_id = #{treeId}",
            "AND a.exam_session = #{examSession}",
            "AND a.exam_province = #{examProvince}",
            "and a.exam_tag = #{examTag}"
    })
    PaperQuestionRelation queryRealExamPaperCodeByCondition(@Param("questionMainCode") String questionMainCode,
                                             @Param("treeId") Integer treeId,
                                             @Param("examSession") Integer examSession,
                                             @Param("examProvince") Integer examProvince,
                                             @Param("examTag") Integer examTag);

    @Update({
            "update t_paper_code a",
            "INNER JOIN t_paper_code_head b on a.`code` = b.paper_code",
            "SET a.question_amount = a.question_amount - 1, a.total_score = a.total_score - #{score}, a.operator = #{username}, ",
            "b.`value` = b.`value` - #{score}, b.operator = #{username}",
            "where a.delete_flag = 0 and a.`code` = #{paperCode} and b.`code` = 'PAPER_HEAD_TOTAL_SCORE' and b.delete_flag = 0"
    })
    int updatePaperTotalScoreByCondition(@Param("score") BigDecimal score,
                                         @Param("paperCode") String paperCode,
                                         @Param("username") String username);

    @Update({
            "<script>",
            "UPDATE t_paper_question_code_rel ",
            "SET delete_flag = 1, operator = #{username} ",
            "WHERE paper_code = #{paperCode} AND question_code in ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionCodeList\"  open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "and delete_flag = 0",
            "</script>"
    })
    int delPaperQuestionCodeRel(@Param("paperCode") String paperCode,
                                @Param("questionCodeList") List<String> questionCodeList,
                                @Param("username") String username);
    @Update({
            "update t_paper_code a ",
            "inner join t_paper_code_head b on a.code = b.paper_code",
            "set a.delete_flag = 1,a.operator = #{username},b.delete_flag = 1,b.operator = #{username}",
            "where a.code = #{paperCode} and a.total_score = 0"
    })
    int delPaperCodeByCondition(@Param("paperCode") String paperCode, @Param("username") String username);

    @Select({"SELECT b.id as examSession, b.`session` as examSessionName, ",
            "c.id as examProvince, c.province_name as examProvinceName,",
            "ifnull(d.id,0) as examTag, ifnull(d.name,'') as examTagName",
            "FROM t_question_main_exam_session_province as a ",
            "INNER JOIN t_exam_session as b ON b.id = a.exam_session AND b.delete_flag = 0",
            "INNER JOIN sch_local_province as c ON c.id = a.exam_province AND c.delete_flag = 0",
            "LEFT JOIN t_exam_tag as d on d.id = a.exam_tag and d.delete_flag = 0",
            "WHERE a.t_question_main_id = #{questionMainId} AND a.delete_flag = 0"})
    List<ExamLabelDTO> listQuestionExamLabelById(@Param("questionMainId") Integer questionMainId);

    @Select({"<script>",
            "select a.id, a.code, a.source_type sourceType, a.question_type questionType,a.content_type contentType, a.question_id questionId, a.score score, a.knowledge_tree_id knowledgeTreeId, a.main_node_id mainNodeId,",
            "CASE WHEN a.main_node_id IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = a.main_node_id)",
            "ELSE null END as mainNodeName,",
            "CASE WHEN a.main_node_id IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = a.main_node_id)",
            "ELSE null END as mainNodeSerialNumber,",
            "a.`difficulty_value` difficultyValue,a.`difficulty_type` difficultyType, a.`exam_province` examProvince, a.`exam_session` examSession, a.`current_version` currentVersion, a.`parent_question_id` parentQuestionId,",
            "a.`sequence`, a.`invalid_flag` invalidFlag, a.`create_time` createTime, a.`creator`, a.`update_time` updateTime, a.`operator`, a.`delete_flag` deleteFlag, ",
            "case when a.content_type='ESSAY' then (select content from t_question_content_essay where id=a.question_id) ",
            "when a.content_type='CHOICE' then (select content from t_question_content_choice where id=a.question_id) else '' end content, ",
            "case when a.content_type='ESSAY' then (select correct_answer from t_question_content_essay where id=a.question_id) ",
            "when a.content_type='CHOICE' then (select correct_answer from t_question_content_choice where id=a.question_id) else '' end correctAnswer",
            "from t_question_main a",
            "WHERE",
            "a.code IN",
            "<foreach item=\"item\" index=\"index\" collection=\"codeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "AND a.current_version = 1 AND a.delete_flag=0 AND a.parent_question_id = 0",
            "UNION",
            "select a.id, a.code, a.source_type sourceType, a.question_type questionType,a.content_type contentType, a.question_id questionId, a.score score, a.knowledge_tree_id knowledgeTreeId, a.main_node_id mainNodeId,",
            "CASE WHEN a.main_node_id IS NOT NULL THEN (SELECT n.name from t_knowledge_node n WHERE n.id = a.main_node_id)",
            "ELSE null END as mainNodeName,",
            "CASE WHEN a.main_node_id IS NOT NULL THEN (SELECT n.serial_number from t_knowledge_node n WHERE n.id = a.main_node_id)",
            "ELSE null END as mainNodeSerialNumber,",
            "a.`difficulty_value` difficultyValue,a.`difficulty_type` difficultyType, a.`exam_province` examProvince, a.`exam_session` examSession, a.`current_version` currentVersion, a.`parent_question_id` parentQuestionId,",
            "a.`sequence`, a.`invalid_flag` invalidFlag, a.`create_time` createTime, a.`creator`, a.`update_time` updateTime, a.`operator`, a.`delete_flag` deleteFlag, ",
            "case when a.content_type='ESSAY' then (select content from t_question_content_essay where id=a.question_id) ",
            "when a.content_type='CHOICE' then (select content from t_question_content_choice where id=a.question_id) else '' end content, ",
            "case when a.content_type='ESSAY' then (select correct_answer from t_question_content_essay where id=a.question_id) ",
            "when a.content_type='CHOICE' then (select correct_answer from t_question_content_choice where id=a.question_id) else '' end correctAnswer",
            "from t_question_main a",
            "WHERE",
            "a.parent_question_id IN",
            "(",
            "SELECT b.id FROM t_question_main b",
            "WHERE b.code IN",
            "<foreach item=\"item\" index=\"index\" collection=\"codeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            ")",
            "AND a.current_version = 1 AND a.delete_flag=0",
            "</script>"})
    List<ResQuestionMainDTO> selectQuestionsByCodes(@Param("codeList") List<String> codeList);
}
