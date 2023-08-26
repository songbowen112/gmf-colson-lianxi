package com.colson.dal.dao;

import com.colson.dal.dto.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface UploadQuestionDAO {

    /**
     * add by maxiao 2018.01.25
     * 根据用户id查询上传试题的统计信息
     *
     * @param userName
     * @return
     */
    @Select({
            "SELECT",
            " total_questions totalQuestions,",
            " not_audit_questions notAuditQuestions,",
            " qualified_questions qualifiedQuestions,",
            " unqualified_times unqualifiedTimes",
            " FROM t_temp_question_main_statistic_info",
            " WHERE user_id = #{userName} "
    })
    QuestionDetailsDTO getQuestionDetailsByUserId(@Param("userName") String userName);

    /**
     * 根据userName修改上传总试题数
     *
     * @param userName
     * @param totalQuestions
     */
    @Update({
            "insert into t_temp_question_main_statistic_info(user_id, total_questions)",
            "VALUES (#{userName}, #{totalQuestions} )",
            "ON DUPLICATE KEY UPDATE total_questions = total_questions + #{totalQuestions} ",
    })
    void updateTotalQuestionsByUserId(@Param("userName") String userName, @Param("totalQuestions") Integer totalQuestions);

    /**
     * 根据userName修改待审核试题数
     * @param userName
     * @param notAuditQuestions
     */
    @Update({
            "insert into t_temp_question_main_statistic_info(user_id, not_audit_questions)",
            "VALUES (#{userName}, #{notAuditQuestions} )",
            "ON DUPLICATE KEY UPDATE not_audit_questions = not_audit_questions + #{notAuditQuestions} ",
    })
    void updateNotAuditQuestionsByUserId(@Param("userName") String userName, @Param("notAuditQuestions") Integer notAuditQuestions);

    @Update({
            " UPDATE t_temp_question_main_statistic_info",
            " SET qualified_questions = qualified_questions + 1",
            " WHERE user_id=#{userName}"
    })
    void incrQualifiedQuestionsByUserId(@Param("userName") String userName);

    @Update({
            " UPDATE t_temp_question_main_statistic_info",
            " SET unqualified_times = unqualified_times + 1",
            " WHERE user_id=#{userName}"
    })
    void incrUnqualifiedTimesByUserId(@Param("userName") String userName);

    @Insert({
            " INSERT INTO `t_temp_question_content_essay` (`content`, `correct_answer`, `analysis`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag`) ",
            " VALUES (#{content,jdbcType=VARCHAR},#{correctAnswer,jdbcType=VARCHAR},#{analysis,jdbcType=VARCHAR},now(),#{creator,jdbcType=VARCHAR},now(),#{operator,jdbcType=VARCHAR},0) "
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insertEssayContent(QuestionContentEssay questionContentEssay);


    @Insert({
            " INSERT INTO `t_temp_question_main` (question_source, question_status, `source_type`, `question_type`,content_type, `question_id`, `score`, `knowledge_tree_id`, ",
            " `main_node_id`, `vice_node_id_1`, `vice_node_id_2`, `difficulty_value`, `exam_province`, `exam_session`, exam_tag,",
            " `parent_question_id`, `sequence`, `invalid_flag`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag`, most_similar_question_id) ",
            " VALUES ('UPLOAD_QUESTION', 'NOT_AUDIT', #{sourceType,jdbcType=VARCHAR},#{questionType,jdbcType=VARCHAR},#{contentType,jdbcType=VARCHAR}, ",
            " #{questionId,jdbcType=INTEGER},#{score,jdbcType=DECIMAL},#{knowledgeTreeId,jdbcType=INTEGER}, ",
            " #{mainNodeId,jdbcType=INTEGER},#{viceNodeId1,jdbcType=INTEGER},#{viceNodeId2,jdbcType=INTEGER}, ",
            " #{difficultyValue,jdbcType=INTEGER},#{examProvince,jdbcType=INTEGER},#{examSession,jdbcType=INTEGER}, #{examTag}, ",
            " #{parentQuestionId,jdbcType=INTEGER},#{sequence,jdbcType=INTEGER}, ",
            " #{invalidFlag,jdbcType=INTEGER},now(),#{creator,jdbcType=VARCHAR}, ",
            " now(),#{operator,jdbcType=VARCHAR},0, -1) "
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insertQuestionMain(QuestionMain insertQuestionMain);
    
    @Update({"UPDATE t_temp_question_main as a ",
            "INNER JOIN t_knowledge_tree as b ON b.id = a.knowledge_tree_id ",
            "SET a.subject_id = b.subject_id ",
            "WHERE a.id = #{questionMainId} "})
    int updateTempQuestionMainSubjectId(@Param("questionMainId")Integer quesitonMainId);

    @Insert({
            " INSERT INTO t_temp_question_content_choice (content, correct_answer, analysis, ",
            " create_time, creator, update_time, operator, delete_flag) ",
            " VALUES (#{content},#{correctAnswer},#{analysis}, ",
            " now(),#{creator},now(),#{operator}, 0) "
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insertChoiceContent(QuestionContentChoice insertQuestionContentChoice);

    /**
     * 批量新增
     * @param questionContentChoiceOptionList
     * @return
     */
    @Insert({
            "<script>",
            " INSERT INTO `t_temp_question_content_choice_option` (`question_id`, `sequence`, `option_title`, `content`, ",
            " `is_correct`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag`) VALUES ",
            "<foreach collection=\"questionContentChoiceOptionList\" item=\"item\"  separator=\",\">",
            " (#{item.questionId,jdbcType=INTEGER},#{item.sequence,jdbcType=INTEGER},#{item.optionTitle,jdbcType=VARCHAR},#{item.content,jdbcType=VARCHAR}, ",
            " #{item.isCorrect,jdbcType=INTEGER},now(),#{item.creator,jdbcType=VARCHAR},now(),#{item.operator,jdbcType=VARCHAR},0) ",
            "</foreach>",
            "</script>"
    })
    int batchInsertContentChoiceOption(@Param("questionContentChoiceOptionList") List<QuestionContentChoiceOption> questionContentChoiceOptionList);

    @Insert({
            "<script>",
            "INSERT INTO t_temp_question_content_essay_score_point(essay_id,content,score,creator,operator) VALUES ",
            "<foreach collection=\"scorePointList\" item=\"item\" separator=\",\">  ",
            "(#{item.essayId},#{item.content},#{item.score},#{item.creator},#{item.operator})",
            "</foreach>",
            "</script>"
    })
    int batchInsertScorePoint(@Param("scorePointList") List<QuestionContentEssayScorePoint> scorePointList);

    @Insert({
            "INSERT INTO `t_temp_question_content_fill_blank` (`sequence`, `score`, `answer`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag`) ",
            " VALUES (#{sequence,jdbcType=INTEGER},#{score,jdbcType=DECIMAL},#{answer,jdbcType=VARCHAR},now(),#{creator,jdbcType=VARCHAR},now(),#{operator,jdbcType=VARCHAR},0) "
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insertContentFillBlank(QuestionContentFillBlank questionContentFillBlank);

    @Select({"<script>SELECT a.id, a.question_status as questionStatus, ",
            /*"IFNULL(( ",
            "SELECT d.description FROM t_temp_question_main_unqualified_info as d ",
            "WHERE d.question_main_id = a.id AND d.user_id = a.creator ",
            "), '-') ",
            "as `desc`, ",*/
            "a.question_type as questionType, ",
            "IF(a.content_type = 'CHOICE', ",
            "(SELECT b.content FROM t_temp_question_content_choice as b ",
            "WHERE b.id = a.question_id AND b.delete_flag = 0 ",
            "), ",
            "( ",
            "SELECT c.content FROM t_temp_question_content_essay as c ",
            "WHERE c.id = a.question_id AND c.delete_flag = 0 ",
            ")) as content, ",
            "a.source_type as sourceType, ",
            "a.operator, ",
            "date_format(a.update_time, '%Y-%m-%d %H:%i:%s') as updateTime, ",
            "IFNULL(a.auditor, '-') as auditor, ",
            "IFNULL(date_format(a.audit_time, '%Y-%m-%d %H:%i:%s'), '-') as auditTime ",
            "FROM  ",
            "`t_temp_question_main` as a  ",
            "WHERE a.creator = #{req.userId} AND a.question_source = 'UPLOAD_QUESTION' AND a.delete_flag = 0 AND a.question_status != 'ABANDON' AND a.invalid_flag = 0 AND a.parent_question_id = 0 ",
            "<if test=\"req.status != null and req.status != ''\"> AND a.question_status = #{req.status} </if>",
            "<if test=\"req.sourceType != null and req.sourceType != ''\"> AND a.source_type = #{req.sourceType} </if>",
            "<if test=\"req.questionType != null and req.questionType != ''\"> AND a.question_type = #{req.questionType} </if>",
            "ORDER BY a.update_time DESC, FIELD(a.question_status, 'NOT_AUDIT', 'QUALIFIED', 'COMBINED', 'UNQUALIFIED') DESC ",
            "LIMIT ${start}, ${req.pageSize}</script>"
    })
    List<ResBufferPoolQuestionInfoDTO> queryUploadQuestionByCondition(@Param("req") QueryBufferPoolQuestionsConditionDTO req, @Param("start") int start);

    @Select({"<script>SELECT count(*) ",
            "FROM  ",
            "`t_temp_question_main` as a  ",
            "WHERE a.creator = #{userId} AND a.question_source = 'UPLOAD_QUESTION' AND a.delete_flag = 0 AND a.question_status != 'ABANDON' AND a.invalid_flag = 0 AND a.parent_question_id = 0 ",
            "<if test=\"status != null and status != ''\"> AND a.question_status = #{status} </if>",
            "<if test=\"sourceType != null and sourceType != ''\"> AND a.source_type = #{sourceType} </if>",
            "<if test=\"questionType != null and questionType != ''\"> AND a.question_type = #{questionType} </if> </script>",
    })
    int queryUploadQuestionCountByCondition(QueryBufferPoolQuestionsConditionDTO req);

    @Select({"<script>SELECT a.id, a.question_status as questionStatus, a.operator,",
            /*"IFNULL(( ",
            "SELECT d.description FROM t_temp_question_main_unqualified_info as d ",
            "WHERE d.question_main_id = a.id AND d.user_id = a.creator ",
            "), '-') ",
            "as `desc`, ",*/
            "a.question_type as questionType, ",
            "IF(a.content_type = 'CHOICE', b.content, c.content) as content, ",
            "a.source_type as sourceType, ",
            "a.operator, ",
            "date_format(a.update_time, '%Y-%m-%d %H:%i:%s') as updateTime, ",
            "IFNULL(a.auditor, '-') as auditor, ",
            "IFNULL(date_format(a.audit_time, '%Y-%m-%d %H:%i:%s'), '-') as auditTime ",
            "FROM  ",
            "`t_temp_question_main` as a  ",
            "LEFT JOIN t_temp_question_content_choice b on b.id = a.question_id and b.delete_flag = 0 AND a.content_type = 'CHOICE'",
            "LEFT JOIN t_temp_question_content_essay c on c.id = a.question_id and c.delete_flag = 0 AND a.content_type = 'ESSAY'",
            "WHERE a.question_source = 'TIKU' AND a.delete_flag = 0 AND a.question_status != 'ABANDON' AND a.invalid_flag = 0 AND a.parent_question_id = 0 ",
            "<if test=\"req.status != null and req.status != ''\"> AND a.question_status = #{req.status} </if>",
            "<if test=\"req.sourceType != null and req.sourceType != ''\"> AND a.source_type = #{req.sourceType} </if>",
            "<if test=\"req.questionType != null and req.questionType != ''\"> AND a.question_type = #{req.questionType} </if>",
            "<if test=\"req.subjectId != null and req.subjectId != ''\"> AND a.subject_id = #{req.subjectId} </if>",
            "<if test=\"req.conditionCode != null and req.fuzzy != null and req.fuzzy != ''\">",
            "<if test=\"req.conditionCode == 1\"> AND a.id LIKE concat('%', #{req.fuzzy}, '%') </if>",
            "<if test=\"req.conditionCode == 2\"> AND (b.content LIKE concat('%', #{req.fuzzy}, '%') OR c.content LIKE concat('%', #{req.fuzzy}, '%'))</if>",
            "</if>",
            "ORDER BY a.update_time DESC, FIELD(a.question_status, 'NOT_AUDIT', 'QUALIFIED','COMBINED', 'UNQUALIFIED') DESC ",
            "LIMIT ${start}, ${req.pageSize}</script>"
    })
    List<ResBufferPoolQuestionInfoDTO> queryTransferQuestionByCondition(@Param("req") QueryBufferPoolQuestionsConditionDTO req, @Param("start") int start);

    @Select({"<script>",
            "SELECT COUNT(*)",
            "FROM `t_temp_question_main` as a",
            "LEFT JOIN t_temp_question_content_choice b on b.id = a.question_id and b.delete_flag = 0 AND a.content_type = 'CHOICE'",
            "LEFT JOIN t_temp_question_content_essay c on c.id = a.question_id and c.delete_flag = 0 AND a.content_type = 'ESSAY'",
            "WHERE a.question_source = 'TIKU' AND a.delete_flag = 0 AND a.question_status != 'ABANDON' AND a.invalid_flag = 0 AND a.parent_question_id = 0 ",
            "<if test=\"req.status != null and req.status != ''\"> AND a.question_status = #{req.status} </if>",
            "<if test=\"req.sourceType != null and req.sourceType != ''\"> AND a.source_type = #{req.sourceType} </if>",
            "<if test=\"req.questionType != null and req.questionType != ''\"> AND a.question_type = #{req.questionType} </if>",
            "<if test=\"req.subjectId != null and req.subjectId != ''\"> AND a.subject_id = #{req.subjectId} </if>",
            "<if test=\"req.conditionCode != null and req.fuzzy != null and req.fuzzy != ''\">",
            "<if test=\"req.conditionCode == 1\"> AND a.id LIKE concat('%', #{req.fuzzy}, '%') </if>",
            "<if test=\"req.conditionCode == 2\"> AND (b.content LIKE concat('%', #{req.fuzzy}, '%') OR c.content LIKE concat('%', #{req.fuzzy}, '%'))</if>",
            "</if>",
            "</script>"
    })
    int queryTransferQuestionCountByCondition(@Param("req") QueryBufferPoolQuestionsConditionDTO req);

    @Select({"<script>SELECT a.id, a.question_status as questionStatus, a.operator,",
            /*"IFNULL(( ",
            "SELECT d.description FROM t_temp_question_main_unqualified_info as d ",
            "WHERE d.question_main_id = a.id AND d.user_id = a.creator ",
            "), '-') ",
            "as `desc`, ",*/
            "a.question_type as questionType, ",
            "IF(a.content_type = 'CHOICE', b.content, c.content) as content, ",
            "a.source_type as sourceType, ",
//            "a.operator, ",
            "date_format(a.update_time, '%Y-%m-%d %H:%i:%s') as updateTime, ",
            "IFNULL(a.auditor, '-') as auditor, ",
            "IFNULL(date_format(a.audit_time, '%Y-%m-%d %H:%i:%s'), '-') as auditTime ",
            "FROM `t_temp_question_main` as a",
            "LEFT JOIN t_temp_question_content_choice b on b.id = a.question_id and b.delete_flag = 0 AND a.content_type = 'CHOICE'",
            "LEFT JOIN t_temp_question_content_essay c on c.id = a.question_id and c.delete_flag = 0 AND a.content_type = 'ESSAY'",
            "WHERE a.delete_flag = 0 AND a.knowledge_tree_id = #{req.knowledgeTreeId} AND a.invalid_flag = 0 AND a.parent_question_id = 0 ",
            "<if test=\"req.status == null\"> AND a.question_status in('NOT_AUDIT', 'UNQUALIFIED') </if>",
            "<if test=\"req.status != null and req.status != ''\"> AND a.question_status = #{req.status} </if>",
            "<if test=\"req.sourceType != null and req.sourceType != ''\"> AND a.source_type = #{req.sourceType} </if>",
            "<if test=\"req.questionType != null and req.questionType != ''\"> AND a.question_type = #{req.questionType} </if>",
            "<if test=\"req.questionSource != null and req.questionSource != ''\"> AND a.question_source = #{req.questionSource} </if>",
            "<if test=\"req.conditionCode != null and req.fuzzy != null and req.fuzzy != ''\">",
            "<if test=\"req.conditionCode == 1\"> AND a.id LIKE concat('%', #{req.fuzzy}, '%') </if>",
            "<if test=\"req.conditionCode == 2\"> AND (b.content LIKE concat('%', #{req.fuzzy}, '%') OR c.content LIKE concat('%', #{req.fuzzy}, '%'))</if>",
            "</if>",
            "ORDER BY a.update_time, FIELD(a.question_status, 'NOT_AUDIT', 'UNQUALIFIED') DESC ",
            "LIMIT ${start}, ${req.pageSize} </script>"
    })
    List<ResBufferPoolQuestionInfoDTO> queryAuditQuestionByCondition(@Param("req") QueryBufferPoolQuestionsConditionDTO req, @Param("start") int start);

    /**
     * 查询启用状态下知识点的待审核试题列表
     * @param req
     * @param start
     * @return
     */
    @Select({
            "<script>",
            "SELECT a.id, a.question_status questionStatus,",
            "a.question_type as questionType, ",
            "IF(a.content_type = 'CHOICE', b.content, c.content) as content, ",
            "a.source_type as sourceType, ",
            "a.operator, ",
            "date_format(a.update_time, '%Y-%m-%d %H:%i:%s') as updateTime, ",
            "IFNULL(a.auditor, '-') as auditor, ",
            "IFNULL(date_format(a.audit_time, '%Y-%m-%d %H:%i:%s'), '-') as auditTime ",
            "FROM t_temp_question_main a ",
            "INNER JOIN (",
            "SELECT ",
            " IF (",
            " d.id IS NULL,",
            " c.id,",
            "",
            " IF (",
            " d.parent_question_id = 0,",
            " d.id,",
            " d.parent_question_id",
            " )",
            " ) tempQuestionMainId",
            "FROM ",
            " t_knowledge_node AS a ",
            "INNER JOIN t_knowledge_node AS b ON b.knowledge_tree_id = a.knowledge_tree_id ",
            "AND SUBSTRING_INDEX( ",
            " a.serial_number, ",
            " '.', ",
            " b.`level` ",
            ") = b.serial_number ",
            "<if test=\"req.nodeInvalidFlag != null and (req.nodeInvalidFlag == 0 or req.nodeInvalidFlag == 1)\"> AND b.invalid_flag = #{req.nodeInvalidFlag} </if>",
            "AND b.delete_flag = 0 ",
            "INNER JOIN t_temp_question_main c on c.knowledge_tree_id = a.knowledge_tree_id ",
            "AND c.main_node_id = a.id ",
            "AND c.invalid_flag = 0 ",
            "AND c.delete_flag = 0 ",
            "LEFT JOIN t_temp_question_main AS d ON d.id = c.parent_question_id ",
            "AND d.invalid_flag = 0 ",
            "AND d.delete_flag = 0 ",
            "WHERE ",
            " a.knowledge_tree_id = #{req.knowledgeTreeId}",
            "<if test=\"req.nodeInvalidFlag != null and (req.nodeInvalidFlag == 0 or req.nodeInvalidFlag == 1)\"> AND a.invalid_flag = #{req.nodeInvalidFlag} </if>",
            "AND a.delete_flag = 0 ",
            "AND b.id = #{req.knowledgeNodeId} ", //-- 查询指定知识点下待审核试题
            "GROUP BY tempQuestionMainId",
            ") d on a.id = d.tempQuestionMainId",
            "LEFT JOIN t_temp_question_content_choice b on b.id = a.question_id and b.delete_flag = 0 AND a.content_type = 'CHOICE'",
            "LEFT JOIN t_temp_question_content_essay c on c.id = a.question_id and c.delete_flag = 0 AND a.content_type = 'ESSAY'",
            "WHERE a.delete_flag = 0 AND a.knowledge_tree_id = #{req.knowledgeTreeId} AND a.invalid_flag = 0 AND a.parent_question_id = 0 ",
            "<if test=\"req.status == null\"> AND a.question_status in('NOT_AUDIT', 'UNQUALIFIED') </if>",
            "<if test=\"req.status != null and req.status != ''\"> AND a.question_status = #{req.status} </if>",
            "<if test=\"req.sourceType != null and req.sourceType != ''\"> AND a.source_type = #{req.sourceType} </if>",
            "<if test=\"req.questionType != null and req.questionType != ''\"> AND a.question_type = #{req.questionType} </if>",
            "<if test=\"req.questionSource != null and req.questionSource != ''\"> AND a.question_source = #{req.questionSource} </if>",
            "<if test=\"req.conditionCode != null and req.fuzzy != null and req.fuzzy != ''\">",
            "<if test=\"req.conditionCode == 1\"> AND a.id LIKE concat('%', #{req.fuzzy}, '%') </if>",
            "<if test=\"req.conditionCode == 2\"> AND (b.content LIKE concat('%', #{req.fuzzy}, '%') OR c.content LIKE concat('%', #{req.fuzzy}, '%'))</if>",
            "</if>",
            "ORDER BY a.update_time, FIELD(a.question_status, 'NOT_AUDIT', 'UNQUALIFIED') DESC",
            "LIMIT ${start}, ${req.pageSize} ",
            "</script>"
    })
    List<ResBufferPoolQuestionInfoDTO> queryAuditQuestionByNode(@Param("req") QueryBufferPoolQuestionsConditionDTO req, @Param("start") int start);

    // TODO: 2019/12/18 查询待审核试题条数
    /**
     * 查询有待审核试题的知识点信息
     * @param treeId
     */
    @Select({
            "<script>",
            "SELECT b.id,b.serialNumber,b.nodeName, b.`level`, count(b.tempQuestionMainId) questionNum",
            " from t_temp_question_main a",
            "INNER JOIN (",
            "SELECT ",
            " b.id, ",
            " b.serial_number AS serialNumber, ",
            " b.`name` AS nodeName, ",
            " b.`level`, ",
            " IF ( ",
                " d.id IS NULL, ",
                " c.id, ",
                "IF ( ",
                " d.parent_question_id = 0, ",
                " d.id, ",
                " d.parent_question_id ",
                " ) ",
            ") tempQuestionMainId",
            "FROM ",
            " t_knowledge_node AS a ",
            "INNER JOIN t_knowledge_node AS b ON b.knowledge_tree_id = a.knowledge_tree_id ",
            "AND SUBSTRING_INDEX( ",
            " a.serial_number, ",
            " '.', ",
            " b.`level` ",
            ") = b.serial_number ",
            "<if test=\"invalidFlag != null and (invalidFlag == 0 or invalidFlag == 1)\"> AND b.invalid_flag = #{invalidFlag} </if>",
            "AND b.delete_flag = 0 ",
            "INNER JOIN t_temp_question_main c on c.knowledge_tree_id = a.knowledge_tree_id ",
            "AND c.main_node_id = a.id ",
            "AND c.invalid_flag = 0 ",
            "AND c.delete_flag = 0 ",
            "LEFT JOIN t_temp_question_main AS d ON d.id = c.parent_question_id ",
            "AND d.invalid_flag = 0 ",
            "AND d.delete_flag = 0 ",
            "WHERE ",
            " a.knowledge_tree_id = #{treeId} ",
            "<if test=\"invalidFlag != null and (invalidFlag == 0 or invalidFlag == 1)\"> AND a.invalid_flag = #{invalidFlag} </if>",
            "AND a.delete_flag = 0 ",
            "GROUP BY b.id ,tempQuestionMainId",
            ") b on a.id = b.tempQuestionMainId",
            "WHERE a.parent_question_id = 0 AND a.question_status in('NOT_AUDIT', 'UNQUALIFIED')",
            "GROUP by b.id ",
            "ORDER BY b.serialNumber",
            " </script>"
    })
    List<ResNodeQuestionNumDTO> selectNodeByCondition(@Param("treeId") Integer treeId, @Param("invalidFlag") Integer invalidFlag);

    @Select({"<script> SELECT count(*) ",
            "FROM  ",
            "`t_temp_question_main` as a  ",
            "LEFT JOIN t_temp_question_content_choice b on b.id = a.question_id and b.delete_flag = 0 AND a.content_type = 'CHOICE'",
            "LEFT JOIN t_temp_question_content_essay c on c.id = a.question_id and c.delete_flag = 0 AND a.content_type = 'ESSAY'",
            "WHERE a.delete_flag = 0 AND a.knowledge_tree_id = #{knowledgeTreeId} AND a.invalid_flag = 0 AND a.parent_question_id = 0 AND a.most_similar_question_id >= 0 ",
            "<if test=\"status == null\"> AND a.question_status in('NOT_AUDIT', 'UNQUALIFIED') </if>",
            "<if test=\"status != null and status != ''\"> AND a.question_status = #{status} </if>",
            "<if test=\"sourceType != null and sourceType != ''\"> AND a.source_type = #{sourceType} </if>",
            "<if test=\"questionType != null and questionType != ''\"> AND a.question_type = #{questionType} </if>",
            "<if test=\"questionSource != null and questionSource != ''\"> AND a.question_source = #{questionSource} </if>",
            "<if test=\"conditionCode != null and fuzzy != null and fuzzy != ''\">",
            "<if test=\"conditionCode == 1\"> AND a.id LIKE concat('%', #{fuzzy}, '%') </if>",
            "<if test=\"conditionCode == 2\"> AND (b.content LIKE concat('%', #{fuzzy}, '%') OR c.content LIKE concat('%', #{fuzzy}, '%'))</if>",
            "</if>",
            " </script>"
    })
    int queryAuditQuestionCountByCondition(QueryBufferPoolQuestionsConditionDTO req);

    @Select({
            "<script>",
            "SELECT count(a.id) ",
            "FROM t_temp_question_main a ",
            "INNER JOIN (",
            "SELECT ",
            " IF (",
            " d.id IS NULL,",
            " c.id,",
            "",
            " IF (",
            " d.parent_question_id = 0,",
            " d.id,",
            " d.parent_question_id",
            " )",
            " ) tempQuestionMainId",
            "FROM ",
            " t_knowledge_node AS a ",
            "INNER JOIN t_knowledge_node AS b ON b.knowledge_tree_id = a.knowledge_tree_id ",
            "AND SUBSTRING_INDEX( ",
            " a.serial_number, ",
            " '.', ",
            " b.`level` ",
            ") = b.serial_number ",
            "<if test=\"req.nodeInvalidFlag != null and (req.nodeInvalidFlag == 0 or req.nodeInvalidFlag == 1)\"> AND b.invalid_flag = #{req.nodeInvalidFlag} </if>",
            "AND b.delete_flag = 0 ",
            "INNER JOIN t_temp_question_main c on c.knowledge_tree_id = a.knowledge_tree_id ",
            "AND c.main_node_id = a.id ",
            "AND c.invalid_flag = 0 ",
            "AND c.delete_flag = 0 ",
            "LEFT JOIN t_temp_question_main AS d ON d.id = c.parent_question_id ",
            "AND d.invalid_flag = 0 ",
            "AND d.delete_flag = 0 ",
            "WHERE ",
            " a.knowledge_tree_id = #{req.knowledgeTreeId}",
            "<if test=\"req.nodeInvalidFlag != null and (req.nodeInvalidFlag == 0 or req.nodeInvalidFlag == 1)\"> AND a.invalid_flag = #{req.nodeInvalidFlag} </if>",
            "AND a.delete_flag = 0 ",
            "AND b.id = #{req.knowledgeNodeId} ", //-- 查询指定知识点下待审核试题
            "GROUP BY tempQuestionMainId",
            ") d on a.id = d.tempQuestionMainId",
            "LEFT JOIN t_temp_question_content_choice b on b.id = a.question_id and b.delete_flag = 0 AND a.content_type = 'CHOICE'",
            "LEFT JOIN t_temp_question_content_essay c on c.id = a.question_id and c.delete_flag = 0 AND a.content_type = 'ESSAY'",
            "WHERE a.delete_flag = 0 AND a.knowledge_tree_id = #{req.knowledgeTreeId} AND a.invalid_flag = 0 AND a.parent_question_id = 0 ",
            "<if test=\"req.status == null\"> AND a.question_status in('NOT_AUDIT', 'UNQUALIFIED') </if>",
            "<if test=\"req.status != null and req.status != ''\"> AND a.question_status = #{req.status} </if>",
            "<if test=\"req.sourceType != null and req.sourceType != ''\"> AND a.source_type = #{req.sourceType} </if>",
            "<if test=\"req.questionType != null and req.questionType != ''\"> AND a.question_type = #{req.questionType} </if>",
            "<if test=\"req.questionSource != null and req.questionSource != ''\"> AND a.question_source = #{req.questionSource} </if>",
            "<if test=\"req.conditionCode != null and req.fuzzy != null and req.fuzzy != ''\">",
            "<if test=\"req.conditionCode == 1\"> AND a.id LIKE concat('%', #{req.fuzzy}, '%') </if>",
            "<if test=\"req.conditionCode == 2\"> AND (b.content LIKE concat('%', #{req.fuzzy}, '%') OR c.content LIKE concat('%', #{req.fuzzy}, '%'))</if>",
            "</if>",
            "</script>"
    })
    int queryAuditQuestionCountByNode(@Param("req") QueryBufferPoolQuestionsConditionDTO req);

    /**
     * 根据条件查询试题表总表t_question_main
     *
     * @param
     * @param
     * @param
     * @return
     */
    @Select({
            " select a.`id`, a.question_status, a.question_source, a.`source_type`, a.`question_type`,a.content_type, a.`question_id`, a.`score`, a.`knowledge_tree_id`, " ,
            "a.`main_node_id`, node.`name` as mainNodeName, node.serial_number mainNodeSerialNumber, a.`vice_node_id_1`, a.`vice_node_id_2`, " ,
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
            "CASE WHEN exam_tag IS NOT NULL THEN (SELECT name FROM t_exam_tag WHERE id = exam_tag)",
            "ELSE NULL END as examTagName,",
            "a.`difficulty_value`, a.`exam_province`, a.`exam_session`, a.exam_tag as examTag, a.`parent_question_id`," ,
            " a.`sequence`, a.`invalid_flag`, a.`create_time`, a.`creator`, a.`update_time`, a.`operator`, a.`delete_flag`,  ",
            " case when a.content_type='ESSAY' then (select content from t_temp_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select content from t_temp_question_content_choice where id=a.question_id) else '' end content, ",
            " case when a.content_type='ESSAY' then (select correct_answer from t_temp_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select correct_answer from t_temp_question_content_choice where id=a.question_id) else '' end correct_answer, ",
            " case when a.content_type='ESSAY' then (select analysis from t_temp_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis from t_temp_question_content_choice where id=a.question_id) else '' end analysis, ",
            " node.outline_requirement, ",
            "s.id as subjectId, s.name as subjectName, ",
            "(SELECT GROUP_CONCAT(b.province_name ORDER BY b.id) FROM t_knowledge_tree_province_proj2nd_rel as rel ",
            "INNER JOIN sch_local_province as b ON b.id = rel.province_id ",
            "WHERE rel.knowledge_tree_id = a.knowledge_tree_id AND rel.delete_flag = 0 ",
            "GROUP BY a.knowledge_tree_id) as knowledgeTreeBelongProvince ",
            " from t_temp_question_main a   ",
            " left join ent_subject as s ON s.id = a.subject_id",
            " left join t_knowledge_node node on node.id=a.main_node_id and node.invalid_flag=0 and node.delete_flag=0 ",
            " where a.delete_flag=0 and a.parent_question_id=0  and a.invalid_flag=0  and a.id=#{id,jdbcType=INTEGER} ",
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "question_status", property = "questionStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "question_source", property = "questionSource", jdbcType = JdbcType.VARCHAR),
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
            @Result(column = "subjectId", property = "subjectId", jdbcType = JdbcType.INTEGER),
            @Result(column = "subjectName", property = "subjectName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "knowledgeTreeBelongProvince", property = "knowledgeTreeBelongProvince", jdbcType = JdbcType.VARCHAR)
    })
    ResBufferPoolQuestionMainDTO selectBufferPoolQuestionMainById(@Param("id")Integer id);

    /**
     * 根据父试题总表ID查询子表列表
     *
     * @param parentMainId
     * @return
     */
    @Select({
            "<script>",
            " select a.`id`, a.`source_type`, a.`question_type`,a.content_type, a.`question_id`, a.`score`, a.`knowledge_tree_id`, a.`main_node_id`, a.`vice_node_id_1`, a.`vice_node_id_2`, ",
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
            " a.`difficulty_value`, a.`exam_province`, a.`exam_session`,  a.`parent_question_id`, a.`sequence`, a.`invalid_flag`, a.`create_time`, a.`creator`, a.`update_time`, a.`operator`, a.`delete_flag`,  ",
            " case when a.content_type='ESSAY' then (select content from t_temp_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select content from t_temp_question_content_choice where id=a.question_id) else '' end content, ",
            " case when a.content_type='ESSAY' then (select correct_answer from t_temp_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select correct_answer from t_temp_question_content_choice where id=a.question_id) else '' end correct_answer, ",
            " case when a.content_type='ESSAY' then (select analysis from t_temp_question_content_essay where id=a.question_id) ",
            " when a.content_type='CHOICE' then (select analysis from t_temp_question_content_choice where id=a.question_id) else '' end analysis ",
            " from t_temp_question_main a   ",
            " where a.parent_question_id=#{parentMainId,jdbcType=INTEGER} and a.delete_flag=0  and a.invalid_flag=0 </script>",
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
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
    List<ResQuestionMainDTO> selectFromBufferPoolByParentMainId(@Param("parentMainId") Integer parentMainId);

    /**
     * 根据父试题ID查找子填空题内容
     * @param questionMainId
     * @return
     */
    @Select({
            " select b.id as questionMainId, a.`id`, a.`sequence`, a.`score`, a.`answer`, a.`create_time`, a.`creator`, a.`update_time` as updateTime, a.`operator`, a.`delete_flag` as deleteFlag ",
            " from t_temp_question_content_fill_blank a, t_temp_question_main b ",
            " where b.question_id=a.id and b.parent_question_id=#{questionMainId,jdbcType=INTEGER} ",
            " and b.delete_flag=0 and a.delete_flag=0 "
    })
    List<ResBlankDTO> selectBlankFromBufferPoolByQuestionMainId(Integer questionMainId);

    /**
     * 根据试题id查询选择题选项集合
     * @param questionId
     * @return
     */
    @Select({
            " select `id`, `question_id`, `sequence`, `option_title`, `content`, `is_correct`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag` ",
            " from `t_temp_question_content_choice_option` a ",
            " where a.question_id=#{questionId,jdbcType=INTEGER} and a.delete_flag=0 "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "question_id", property = "questionId", jdbcType = JdbcType.INTEGER),
            @Result(column = "sequence", property = "sequence", jdbcType = JdbcType.INTEGER),
            @Result(column = "option_title", property = "optionTitle", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_correct", property = "isCorrect", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delete_flag", property = "deleteFlag", jdbcType = JdbcType.INTEGER),
    })
    List<ResQuestionContentChoiceOptionDTO> selectChoiceOptionFromBufferPoolByQuestionId(Integer questionId);

    @Select({
            "SELECT content,score from t_temp_question_content_essay_score_point where essay_id = #{questionMainId} and delete_flag = 0"
    })
    List<ResQuestionContentEssayScorePointDTO> selectScorePointFromBufferPoolByQuestionId(@Param("questionMainId") Integer questionMainId);

    /**
     * edit by chenqiuxia 20191205 原边池历史记录表数据下掉，改为取t_question_main_edit_detail
     * @param questionMainId
     * @return
     */
    @Select({
            "SELECT ",
            " IFNULL( ",
            " date_format( ",
            " b.update_time, ",
            " '%Y-%m-%d %H:%i:%s' ",
            " ), ",
            " '-' ",
            " ) AS createTime, ",
            "b.behavior, b.operator, c.description unqualifiedDescription, GROUP_CONCAT(d.unqualified_label) unqualifiedLabel ",
            "FROM ",
            " t_question_main_edit_detail b ",
            "LEFT JOIN t_temp_question_main_unqualified_info c on c.question_main_id = b.temp_question_main_id and c.delete_flag = 0 AND c.create_time = b.update_time ",
            "LEFT JOIN t_temp_question_main_unqualified_label_rel d on d.unqualified_info_id = c.id  ",
            "WHERE ",
            " temp_question_main_id = #{questionMainId}",
            "GROUP BY b.id"
    })
    List<BufferPoolQuestionEditLogDTO> getEditLogFromBufferPoolByQuestionId(@Param("questionMainId") Integer questionMainId);

    @Update({"UPDATE t_temp_question_main ",
            "SET question_status = #{status}, auditor = #{auditor}, audit_time = NOW(), t_question_main_id = #{mainPoolQuestionMainId} ",
            "WHERE id = #{bufferPoolQuestionMainId}"})
    int updateBufferPoolQuestionAuditStatus(@Param("bufferPoolQuestionMainId")Integer bufferPoolQuestionMainId, @Param("status")String status, @Param("auditor")String auditor, @Param("mainPoolQuestionMainId") Integer mainPoolQuestionMainId);

    @Insert({"insert into t_temp_question_main_unqualified_info(`user_id`, `question_main_id`, `description`) VALUES (#{userId}, #{questionMainId}, #{description})"})
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insertUnqualifiedInfo(UnqualifiedInfoDTO unqualifiedRecordInfo);

    @Insert({"<script> INSERT INTO t_temp_question_main_unqualified_label_rel (unqualified_info_id, unqualified_label) VALUES ",
            "<foreach collection=\"labelList\" item=\"item\" separator=\",\">  ",
            "(#{infoId}, #{item}) </foreach> </script>"})
    int batchInsertUnqualifiedLabels(@Param("infoId") Integer infoId, @Param("labelList") List<String> labelList);

    /**
     * 逻辑删除t_temp_question_content_choice中的记录
     *
     * @param id
     * @param operator
     * @return
     */
    @Update({
            "update t_temp_question_content_choice set delete_flag =1 ,operator=#{operator} where id =#{id} "
    })
    int deleteContentChoice(@Param("id") Integer id, @Param("operator") String operator);


    /**
     * 逻辑删除t_temp_question_content_choice_option中的记录
     *
     * @param questionId questionId
     * @param operator
     * @return
     */
    @Update({
            "update t_temp_question_content_choice_option set delete_flag =1 ,operator=#{operator} where question_id =#{questionId} ",
    })
    int deleteContentChoiceOption(@Param("questionId") Integer questionId, @Param("operator") String operator);


    /**
     * 逻辑删除t_temp_question_content_essay中的记录
     *
     * @param id
     * @param operator
     * @return
     */
    @Update({
            "update t_temp_question_content_essay set delete_flag =1 ,operator=#{operator} where id =#{id}"
    })
    int deleteContentEssay(@Param("id") Integer id, @Param("operator") String operator);


    /**
     * 逻辑删除t_temp_question_content_essay_score_point中的记录
     *
     * @param essayId
     * @param operator
     * @return
     */
    @Update({
            "update t_temp_question_content_essay_score_point set delete_flag =1 ,operator=#{operator} where essay_id = #{essayId} "
    })
    int deleteContentEssayScorePoint(@Param("essayId") Integer essayId, @Param("operator") String operator);


    /**
     * 逻辑删除t_temp_question_content_fill_blank中的记录
     *
     * @param id
     * @param operator
     * @return
     */
    @Update({
            "update t_temp_question_content_fill_blank set delete_flag =1 ,operator=#{operator} where id = #{id}",
    })
    int deleteContentFillBlack(@Param("id") Integer id, @Param("operator") String operator);



    /**
     * @param operator           操作人
     * @param reqQuestionMainDTO 边池试题表对象
     * @return 是否更新成功
     */
    @Update({
            "<script>",
            " UPDATE `t_temp_question_main` ",
            " set ",
            "<if test=\"reqQuestionMainDTO.sourceType != null and reqQuestionMainDTO.sourceType != ''\"> ",
            " source_type = #{reqQuestionMainDTO.sourceType} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.questionStatus != null and reqQuestionMainDTO.questionStatus != ''\"> ",
            " ,question_status = #{reqQuestionMainDTO.questionStatus} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.questionType != null and reqQuestionMainDTO.questionType != ''\"> ",
            " ,question_type = #{reqQuestionMainDTO.questionType} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.contentType != null and reqQuestionMainDTO.contentType != ''\"> ",
            " ,content_type = #{reqQuestionMainDTO.contentType} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.questionId != null \"> ",
            " ,question_id = #{reqQuestionMainDTO.questionId} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.score != null  \"> ",
            " ,score = #{reqQuestionMainDTO.score} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.subjectId != null  \"> ",
            " ,subject_id = #{reqQuestionMainDTO.subjectId} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.knowledgeTreeId != null  \"> ",
            " ,knowledge_tree_id = #{reqQuestionMainDTO.knowledgeTreeId} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.mainNodeId != null  \"> ",
            " ,main_node_id = #{reqQuestionMainDTO.mainNodeId} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.viceNodeId1 != null  \"> ",
            " ,vice_node_id_1 = #{reqQuestionMainDTO.viceNodeId1} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.viceNodeId2 != null  \"> ",
            " ,vice_node_id_2 = #{reqQuestionMainDTO.viceNodeId2} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.difficultyValue != null \"> ",
            " ,difficulty_value = #{reqQuestionMainDTO.difficultyValue} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.examProvince != null \"> ",
            " ,exam_province = #{reqQuestionMainDTO.examProvince} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.examSession != null \"> ",
            " ,exam_session = #{reqQuestionMainDTO.examSession} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.parentQuestionId != null \"> ",
            " ,parent_question_id = #{reqQuestionMainDTO.parentQuestionId} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.sequence != null \"> ",
            " ,sequence = #{reqQuestionMainDTO.sequence} ",
            "</if> ",
            "<if test=\"reqQuestionMainDTO.invalidFlag != null \"> ",
            " ,invalid_flag = #{reqQuestionMainDTO.invalidFlag} ",
            "</if> ",
            "<if test=\" operator != null and operator != ''\"> ",
            " ,operator = #{operator} ,most_similar_question_id= -1 ",
            "</if> ",
            " WHERE  id = #{reqQuestionMainDTO.id} and parent_question_id = 0 ",
            "</script>"
    })
    int updateTempQuestionMainById(@Param("reqQuestionMainDTO") ReqQuestionMainDTO reqQuestionMainDTO, @Param("operator") String operator);


    /**
     * @param parentQuestionIds 根据题的patentId查询id
     * @return
     */
    @Select({
            "SELECT GROUP_CONCAT(a.id) FROM t_temp_question_main a where a.delete_flag = 0 and a.parent_question_id in (${parentQuestionIds})"
    })
    String getSubQuestionIds(@Param("parentQuestionIds") String parentQuestionIds);

    /**
     * 逻辑删除直属小题和非直属小题
     *
     * @param parentQuestionIds
     * @param operator
     * @return
     */
    @Update({
            "update t_temp_question_main set delete_flag =1 ,operator = #{operator} where delete_flag = 0 and parent_question_id in (${parentQuestionIds}) "
    })
    int clearTempQuestionSubQuestion(@Param("parentQuestionIds") String parentQuestionIds, @Param("operator") String operator);

    @Update({"update t_question_main set audit_time = NOW(), auditor = #{auditor} where id = #{mainPoolQuestionId}"})
    int updateMainPoolQuestionAuditTime(@Param("mainPoolQuestionId") Integer mainPoolQuestionId, @Param("auditor") String auditor);
    
    @Select({"SELECT a.id, a.user_number as stuId, a.`status`, a.wrong_content as errorDetail FROM t_temp_wrong_question as a ",
            "INNER JOIN t_temp_question_main as b ON b.old_tiku_question_id = a.t_question_id ",
            "WHERE b.id = #{bufferPoolQuestionMainId} "})
    List<MainPoolReportWrongQuestionDTO> getReportWrongInfoList(@Param("bufferPoolQuestionMainId") Integer bufferPoolQuestionMainId);

    @Insert({
            "insert into t_tiku_user_report_wrong_question(stu_id, error_detail, question_main_id, status) ",
            "values(#{wrongQuestionDTO.stuId}, #{wrongQuestionDTO.errorDetail}, #{wrongQuestionDTO.questionId}, #{wrongQuestionDTO.status})"
    })
    @SelectKey(before = false, keyProperty = "wrongQuestionDTO.id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    Integer insertMainPoolWrongQuestionRecord(@Param("wrongQuestionDTO") MainPoolReportWrongQuestionDTO wrongQuestionDTO);

    @Insert({"<script>",
            "insert into t_tiku_user_report_wrong_question_label_rel(report_wrong_question_id, error_label)",
            "SELECT #{mainPoolReportWrongInfoId}, wrong_type_code FROM t_temp_question_wrong_type WHERE t_wrong_question_id = #{bufferPoolReportWrongInfoId}",
            "</script>"})
    Integer insertMainPoolWrongQuestionLabelRel(@Param("mainPoolReportWrongInfoId") Integer mainPoolReportWrongInfoId,
                                         @Param("bufferPoolReportWrongInfoId") Integer bufferPoolReportWrongInfoId);

    @Select({"SELECT b.id as repeatQuestionMainId, b.code as repeatQuestionMainCode, a.similarity as repeatPercent, ",
            " b.source_type sourceType",
            "FROM t_temp_question_main as a ",
            "INNER JOIN t_question_main as b ON b.id = a.most_similar_question_id ",
            "WHERE a.id = #{bufferPoolQuestionMainId} "})
    RepeatQuestionDTO getBufferPoolRepeatQuestion(@Param("bufferPoolQuestionMainId") Integer bufferPoolQuestionMainId);

    @Select({"select count(*) from t_temp_question_main where id = #{id} AND parent_question_id = 0 AND delete_flag = 0 AND invalid_flag = 0 AND question_status ='UNQUALIFIED' AND question_source = 'UPLOAD_QUESTION' "})
    int checkNeedChangeToNotAudit(@Param("id") Integer id);

    @Select({"select name from ent_subject where id = #{subjectId}"})
    String getSubjectNameById(@Param("subjectId") Integer subjectId);

    @Select({
            "select b.id,b.`name`",
            "from t_knowledge_tree a",
            "INNER JOIN ent_subject b on a.subject_id = b.id and b.delete_flag = 0",
            "WHERE a.id = #{treeId}",
            "LIMIT 1"
    })
    ResSubjectDTO getSubjectByTreeId(@Param("treeId") Integer treeId);

    @Select({"select session from t_exam_session where id = #{examSessionId}"})
    String getExamSessionById(@Param("examSessionId") Integer examSessionId);

    @Select({"select province_name from sch_local_province where id = #{provinceId}"})
    String getProvinceNameById(@Param("provinceId") Integer provinceId);

    /**
     * 获取试题总数（主池）
     * @return
     */
    @Select({
            "SELECT count(m.id) totalQuestions",
            "FROM t_question_main m",
            "LEFT JOIN t_question_main_exam_session_province rel ON rel.t_question_main_id = m.id",
            "AND rel.delete_flag = 0 ",
            "WHERE m.delete_flag = 0 AND m.invalid_flag = 0 AND m.parent_question_id = 0 and m.current_version = 1"
    })
    Integer getTotalQuestions();

    /**
     * 获取待审核试题数（边池）
     * @return
     */
    @Select({
            "SELECT count(1) notAuditQuestions",
            "FROM t_temp_question_main",
            "WHERE question_status = 'NOT_AUDIT' AND delete_flag = 0 AND invalid_flag = 0 AND parent_question_id = 0"
    })
    Integer getNotAuditQuestions();

    /**
     * 获取真题总数（主池）
     * @return
     */
    @Select({
            "SELECT count(1) realQuestions",
            "FROM t_question_main m",
            "LEFT JOIN t_question_main_exam_session_province rel ON rel.t_question_main_id = m.id AND rel.delete_flag = 0",
            "WHERE m.exam_session is not null AND m.exam_province is not null AND m.delete_flag = 0 AND m.invalid_flag = 0 AND m.parent_question_id = 0 and m.current_version = 1"
    })
    Integer getRealQuestions();

    /**
     * 获取普通题总数（主池） 真题有考期和考试省份
     * @return
     */
    @Select({
            "SELECT count(*) normalQuestions",
            "FROM t_question_main",
            "WHERE exam_session is null AND exam_province is null AND delete_flag = 0 AND invalid_flag = 0 AND parent_question_id = 0 and current_version = 1"
    })
    Integer getNormalQuestions();

    /**
     * 获取最近更新时间
     * @return
     */
    @Select({
            "SELECT max(endTime) endDate",
            "FROM (",
            "SELECT max(update_time) endTime",
            "FROM t_question_main",
            "WHERE  delete_flag = 0 AND invalid_flag = 0",
            "UNION",
            "SELECT max(update_time) endTime",
            "FROM t_temp_question_main",
            "WHERE delete_flag = 0 AND invalid_flag = 0",
            ") q"
    })
    Date getEndDate();

    /**
     * 知识树下待审核试题数（边池）
     * @param knowledgeTreeId
     * @param name
     * @return
     */
    @Select({
            "SELECT count(*) notAuditQuestionCount",
            "FROM t_temp_question_main",
            "WHERE question_status = 'NOT_AUDIT' AND knowledge_tree_id = #{knowledgeTreeId} AND parent_question_id = 0",
            "AND delete_flag = 0 AND invalid_flag = 0"
    })
    Integer getNotAuditQuestionCountByKnowledgeTreeId(@Param("knowledgeTreeId") Integer knowledgeTreeId, @Param("name") String name);

    /**
     * 知识树下不合格试题
     * @param knowledgeTreeId
     * @param name
     * @return
     */
    @Select({
            "SELECT count(*) failAuditQuestionCount",
            "FROM t_temp_question_main",
            "WHERE question_status = 'UNQUALIFIED' AND knowledge_tree_id = #{knowledgeTreeId} AND parent_question_id = 0",
            "AND delete_flag = 0 AND invalid_flag = 0"
    })
    Integer getFailAuditQuestionCountByKnowledgeTreeId(@Param("knowledgeTreeId") Integer knowledgeTreeId, @Param("name") String name);

    @Update({
            "update t_temp_question_main set operator = #{userName}, question_status = 'ABANDON' ",
            "where delete_flag = 0 and id in (${questionIds})"
    })
    void abandonTempQuestion(@Param("questionIds") String questionIds, @Param("userName") String userName);


    /** 判分标准相关 **/
    @Select({
            "select id, question_id as questionId, question_parent_id as questionParentId, score_point_group as scorePointGroup, create_time as createTime, update_time as updateTime, creator, operator, delete_flag as deleteFlag",
            "FROM t_temp_question_score_point",
            "WHERE  question_parent_id = #{questionParentId} AND delete_flag = 0 "
    })
    List<QuestionScorePoint> getQuestionScorePointTempByParentId(@Param("questionParentId") Integer questionParentId);
    @Select({
            "select id, question_id as questionId, question_parent_id as questionParentId, score_point_group as scorePointGroup, create_time as createTime, update_time as updateTime, creator, operator, delete_flag as deleteFlag",
            "FROM t_question_score_point",
            "WHERE  question_parent_id = #{questionParentId} AND delete_flag = 0 "
    })
    List<QuestionScorePoint> getQuestionScorePointByParentId(@Param("questionParentId") Integer questionParentId);

    @Select({
            "select id, question_id as questionId, question_parent_id as questionParentId, score_point_group as scorePointGroup, create_time as createTime, update_time as updateTime, creator, operator, delete_flag as deleteFlag",
            "FROM t_temp_question_score_point",
            "WHERE  question_id = #{questionId} AND delete_flag = 0 "
    })
    QuestionScorePoint getQuestionScorePointTempById(@Param("questionId") Integer questionId);
    @Select({
            "select id, question_id as questionId, question_parent_id as questionParentId, score_point_group as scorePointGroup, create_time as createTime, update_time as updateTime, creator, operator, delete_flag as deleteFlag",
            "FROM t_question_score_point",
            "WHERE  question_id = #{questionId} AND delete_flag = 0 "
    })
    QuestionScorePoint getQuestionScorePointById(@Param("questionId") Integer questionId);

    @Insert({
            "<script>",
            "insert into t_temp_question_score_point(question_id, question_parent_id, score_point_group, creator, operator) values ",
            "<foreach collection=\"pointList\" item=\"item\" separator=\",\"> ",
            " (#{item.questionId},#{item.questionParentId}, #{item.scorePointGroup}, #{item.creator}, #{item.operator})",
            "</foreach>",
            "</script>"
    })
    Integer insertQuestionScorePointTemp(@Param("pointList") List<QuestionScorePoint> pointList);
    @Insert({
            "<script>",
            "insert into t_question_score_point(question_id, question_parent_id, score_point_group, creator, operator) values ",
            "<foreach collection=\"pointList\" item=\"item\" separator=\",\"> ",
            " (#{item.questionId},#{item.questionParentId}, #{item.scorePointGroup}, #{item.creator}, #{item.operator})",
            "</foreach>",
            "</script>"
    })
    Integer insertQuestionScorePoint(@Param("pointList") List<QuestionScorePoint> pointList);


    @Update({
            "<script>",
            "update  t_question_score_point set delete_flag = 1 where question_parent_id in (",
            "<foreach collection=\"parentQuestionIds\" item=\"item\" separator=\",\"> ",
            " #{item} ",
            "</foreach>",
            ")",
            "</script>"
    })
    void delQuestionScorePoint(@Param("parentQuestionIds") Collection<Integer> parentQuestionIds);
    @Update({
            "<script>",
            "update  t_temp_question_score_point set delete_flag = 1 where question_parent_id in (",
            "<foreach collection=\"parentQuestionIds\" item=\"item\" separator=\",\"> ",
            " #{item} ",
            "</foreach>",
            ")",
            "</script>"
    })
    void delQuestionScorePointTemp(@Param("parentQuestionIds") Collection<Integer> parentQuestionIds);

    @Select({
            "SELECT count(1) ",
            "from t_question_score_point WHERE  question_parent_id = #{questionParentId} AND delete_flag = 0 "
    })
    int countScorePointByParentId (@Param("questionParentId") Integer questionParentId);

    @Select({"SELECT c.language_type FROM t_knowledge_tree as c ",
            "LEFT JOIN t_question_main as a ON a.knowledge_tree_id = c.id ",
            "WHERE a.id = #{questionId}"
    })
    Integer getLanguageTypeByQuestionId(@Param("questionId")Integer questionId);

    @Select({"SELECT c.language_type FROM t_knowledge_tree as c ",
            "LEFT JOIN t_temp_question_main as a ON a.knowledge_tree_id = c.id ",
            "WHERE a.id = #{questionId}"
    })
    Integer getLanguageTypeByQuestionIdTemp(@Param("questionId")Integer questionId);

    @Select({
            "SELECT a.exam_session examSession,a.exam_province examProvince,b.province_name examProvinceName,c.`session` examSessionName, d.id as examTag, d.name as examTagName",
            "from t_question_main_exam_session_province a",
            "INNER JOIN sch_local_province b on a.exam_province = b.id and b.delete_flag = 0",
            "INNER JOIN t_exam_session c on c.id = a.exam_session and c.delete_flag = 0",
            "LEFT JOIN t_exam_tag as d ON d.id = a.exam_tag AND d.delete_flag = 0",
            "where a.t_question_main_id = #{id} and a.delete_flag = 0"
    })
    List<ExamLabelDTO> queryLabelListByQuestionMainId(Integer id);

    @Select({"SELECT name FROM t_exam_tag WHERE id = #{id} AND delete_flag = 0"})
    String getExamTagNameById(@Param("id") Integer id);
}
