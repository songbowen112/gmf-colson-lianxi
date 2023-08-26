package com.colson.dal.dao;

import com.colson.dal.dto.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lm on 2017/8/8.
 */
@Repository
public interface TeachPaperManageDAO {


    @Select({"<script>",
            "SELECT count(DISTINCT paper.id) ",
            "FROM t_paper_code as paper ",
            "WHERE paper.delete_flag = 0 ",
            "<if test=\"knowledgeTreeId != null and knowledgeTreeId != ''\">and paper.knowledge_tree_id = #{knowledgeTreeId} </if>",
            "<if test=\"paperNameOrCode != null and paperNameOrCode != ''\">and (paper.code = #{paperNameOrCode} or paper.name like CONCAT('%', #{paperNameOrCode}, '%')) </if>",
            "<if test=\"paperType != null and paperType != ''\">and paper.type = #{paperType} </if>",
            "<if test=\"examSessionId != null and examSessionId != ''\">and paper.exam_session = #{examSessionId} </if>",
            "<if test=\"examProvinceId != null and examProvinceId != ''\">and paper.exam_province = #{examProvinceId} </if>",
            "<if test=\"examTagId != null and examTagId != ''\">and paper.exam_tag = #{examTagId} </if>",
            "<if test=\"difficultyValue != null and difficultyValue != ''\">and paper.avg_difficulty_value BETWEEN #{minDifficulty} and #{maxDifficulty} </if>",
            "<if test=\"invalidFlag != null\">and paper.invalid_flag = #{invalidFlag} </if>",
            "<if test=\"source != null\">and paper.source = #{source} </if>",
            "<if test=\"operator != null \">and paper.creator = #{operator} </if>",
            "</script>"
    })
    Integer queryPaperCountByCondition(PaperSearchingDTO condition);

    @Select({"<script>",
            "SELECT count(DISTINCT paper.id) ",
            "FROM t_paper_code as paper ",
            "WHERE paper.knowledge_tree_id IN (select tree.id from t_knowledge_tree tree where tree.subject_id = #{subjectId}) ",
            "AND paper.delete_flag = 0 ",
            "<if test=\"paperNameOrCode != null and paperNameOrCode != ''\">and (paper.code = #{paperNameOrCode} or paper.name like CONCAT('%', #{paperNameOrCode}, '%')) </if>",
            "<if test=\"difficultyValue != null and difficultyValue != ''\">and paper.avg_difficulty_value BETWEEN #{minDifficulty} and #{maxDifficulty} </if>",
            "<if test=\"invalidFlag != null\">and paper.invalid_flag = #{invalidFlag} </if>",
            "<if test=\"source != null\">and paper.source = #{source} </if>",
            "<if test=\"operator != null \">and paper.creator = #{operator} </if>",
            "</script>"
    })
    Integer queryPaperCountBySubjectId(PaperSearchingDTO condition);

    @Select({"<script>",
            "SELECT paper.id as paperId, paper.type as paperType, paper.name as paperName, paper.exam_province as examProvinceId, slp.province_name as examProvinceName,  ",
            "paper.exam_session as examSessionId, es.session as examSessionName, paper.exam_tag as examTagId, IFNULL(tag.name,'') as examTagName, paper.knowledge_tree_id as knowledgeTreeId, ",
            "paper.avg_difficulty_value as avgDifficultyValue, paper.total_score as totalScore, paper.question_amount as questionAmount, paper.code as code, paper.subject_id as subjectId  ",
            "FROM t_paper_code as paper  ",
            "LEFT JOIN t_exam_session es on es.id = paper.exam_session  ",
            "LEFT JOIN sch_local_province slp on slp.id = paper.exam_province  ",
            "LEFT JOIN t_exam_tag tag on tag.id = paper.exam_tag AND tag.delete_flag = 0  ",
            "WHERE paper.delete_flag = 0 ",
            "<if test=\"knowledgeTreeId != null and knowledgeTreeId != ''\">and paper.knowledge_tree_id = #{knowledgeTreeId} </if>",
            "<if test=\"knowledgeTreeIds != null and knowledgeTreeIds.size > 0 \"> ",
            "and paper.knowledge_tree_id in",
            "<foreach item=\"item\" index=\"index\" collection=\"knowledgeTreeIds\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"paperType != null and paperType != ''\">and paper.type = #{paperType} </if>",
            "<if test=\"examSessionId != null and examSessionId != ''\">and paper.exam_session = #{examSessionId} </if>",
            "<if test=\"examSessionIds != null and examSessionIds.size > 0 \"> ",
            "and paper.exam_session in",
            "<foreach item=\"item\" index=\"index\" collection=\"examSessionIds\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"examProvinceId != null and examProvinceId != ''\">and paper.exam_province = #{examProvinceId}  </if>",
            "<if test=\"examTagId != null\">and paper.exam_tag = #{examTagId} </if>",
            "<if test=\"difficultyValue != null and difficultyValue != ''\">and paper.avg_difficulty_value BETWEEN #{minDifficulty} and #{maxDifficulty} </if>",
            "<if test=\"invalidFlag != null \">and paper.invalid_flag = #{invalidFlag} </if>",
            "<if test=\"source != null \">and paper.source = #{source} </if>",
            "<if test=\"operator != null \">and paper.creator = #{operator} </if>",
            "order by paperId desc",
            "limit #{pageIndex}, #{pageSize}",
            "</script>"
    })
    List<PaperInfoDTO> getPaperListByCondition(PaperSearchingDTO condition);

    @Select({"<script>",
            "SELECT paper.code as code, paper.id as paperId, paper.type as paperType, paper.name as paperName, paper.exam_province as examProvinceId, slp.province_name as examProvinceName,  ",
            "paper.exam_session as examSessionId, es.session as examSessionName, paper.exam_tag as examTagId, IFNULL(tag.name,'') as examTagName, paper.knowledge_tree_id as knowledgeTreeId, ",
            "paper.avg_difficulty_value as avgDifficultyValue, paper.total_score as totalScore, paper.question_amount as questionAmount, paper.subject_id as subjectId  ",
            "FROM t_paper_code as paper  ",
            "LEFT JOIN t_exam_session es on es.id = paper.exam_session  ",
            "LEFT JOIN sch_local_province slp on slp.id = paper.exam_province  ",
            "LEFT JOIN t_exam_tag tag on tag.id = paper.exam_tag AND tag.delete_flag = 0  ",
            "WHERE paper.delete_flag = 0 ",
            "and paper.code in",
            "<foreach item=\"item\" index=\"index\" collection=\"paperCodes\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "group by paper.code",
            "</script>"
    })
    List<PaperInfoDTO> getPaperListByPaperCodes(@Param("paperCodes") List<String> paperCodes);

    @Select({"<script>",
            "SELECT paper.id as paperId, paper.type as paperType, paper.name as paperName, paper.exam_province as examProvinceId, slp.province_name as examProvinceName,  ",
            "paper.exam_session as examSessionId, es.session as examSessionName, paper.exam_tag as examTagId, IFNULL(tag.name,'') as examTagName, ",
            "paper.avg_difficulty_value as avgDifficultyValue, paper.total_score as totalScore, paper.question_amount as questionAmount, paper.code as code,  ",
            "paper.create_time as createTime, paper.creator as creator, paper.update_time as updateTime, paper.operator as operator, paper.invalid_flag as invalidFlag ", // , paper.couldBeusedByStudents
            "FROM t_paper_code as paper  ",
            "LEFT JOIN t_exam_session es on es.id = paper.exam_session  ",
            "LEFT JOIN sch_local_province slp on slp.id = paper.exam_province  ",
            "LEFT JOIN t_exam_tag tag on tag.id = paper.exam_tag AND tag.delete_flag = 0  ",
            "WHERE paper.delete_flag = 0 ",
            "<if test=\"knowledgeTreeId != null and knowledgeTreeId != ''\">and paper.knowledge_tree_id = #{knowledgeTreeId} </if>",
            "<if test=\"paperNameOrCode != null and paperNameOrCode != ''\">and (paper.code = #{paperNameOrCode} or paper.name like CONCAT('%',#{paperNameOrCode},'%')) </if>",
            "<if test=\"paperType != null and paperType != ''\">and paper.type = #{paperType} </if>",
            "<if test=\"examSessionId != null and examSessionId != ''\">and paper.exam_session = #{examSessionId} </if>",
            "<if test=\"examProvinceId != null and examProvinceId != ''\">and paper.exam_province = #{examProvinceId}  </if>",
            "<if test=\"examTagId != null\">and paper.exam_tag = #{examTagId} </if>",
            "<if test=\"difficultyValue != null and difficultyValue != ''\">and paper.avg_difficulty_value BETWEEN #{minDifficulty} and #{maxDifficulty} </if>",
            "<if test=\"invalidFlag != null \">and paper.invalid_flag = #{invalidFlag} </if>",
            "<if test=\"source != null \">and paper.source = #{source} </if>",
            "<if test=\"operator != null \">and paper.creator = #{operator} </if>",
            "order by paperId desc",
            "limit #{pageIndex},#{pageSize}",
            "</script>"
    })
    List<PaperInfoDTO> getPaperList(PaperSearchingDTO condition);

    @Select({"<script>",
            "SELECT paper.id as paperId, paper.type as paperType, paper.name as paperName, paper.exam_province as examProvinceId, slp.province_name as examProvinceName,  ",
            "paper.exam_session as examSessionId, es.session as examSessionName,  ",
            "paper.avg_difficulty_value as avgDifficultyValue, paper.total_score as totalScore, paper.question_amount as questionAmount, paper.code as code,  ",
            "paper.create_time as createTime, paper.creator as creator, paper.update_time as updateTime, paper.operator as operator, paper.invalid_flag as invalidFlag ", // , paper.couldBeusedByStudents
            "FROM t_paper_code as paper  ",
            "LEFT JOIN t_exam_session es on es.id = paper.exam_session  ",
            "LEFT JOIN sch_local_province slp on slp.id = paper.exam_province  ",
            "WHERE paper.knowledge_tree_id IN (select tree.id from t_knowledge_tree tree where tree.subject_id = #{subjectId}) ",
            "AND paper.delete_flag = 0",
            "<if test=\"paperNameOrCode != null and paperNameOrCode != ''\">and (paper.code = #{paperNameOrCode} or paper.name like CONCAT('%',#{paperNameOrCode},'%')) </if>",
            "<if test=\"paperType != null and paperType != ''\">and paper.type = #{paperType} </if>",
            "<if test=\"difficultyValue != null and difficultyValue != ''\">and paper.avg_difficulty_value BETWEEN #{minDifficulty} and #{maxDifficulty} </if>",
            "<if test=\"invalidFlag != null \">and paper.invalid_flag = #{invalidFlag} </if>",
            "<if test=\"source != null \">and paper.source = #{source} </if>",
            "<if test=\"operator != null \">and paper.creator = #{operator} </if>",
            "order by paperId desc",
            "</script>"
    })
    List<PaperInfoDTO> getPaperListBySubjectId(PaperSearchingDTO condition);

    @Select({"<script>",
            "SELECT DISTINCT pa.id, pa.code, pa.subject_id as subjectId, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount, ",
            "pa.total_score as totalScore, pa.video_url as videoUrl, pa.current_version as currentVersion, pa.source, pa.knowledge_tree_id as knowledgeTreeId, " ,
            "pa.exam_province as examProvince, pa.exam_session as examSession, pa.avg_difficulty_value as avgDifficultyValue, ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag ",
            "from t_paper pa ",
            "WHERE pa.id = #{id} and pa.delete_flag = 0 limit 1",
            "</script>"
    })
    TeachPaper selectPaperById(Integer id);

    @Select({
            "SELECT DISTINCT pa.id, pa.code, pa.subject_id as subjectId, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount,  ",
            "pa.total_score as totalScore, pa.video_url as videoUrl, pa.current_version as currentVersion, pa.source, pa.knowledge_tree_id as knowledgeTreeId,  ",
            "pa.exam_province as examProvince, pa.exam_session as examSession, pa.avg_difficulty_value as avgDifficultyValue,  ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag  ",
            "from t_paper pa  ",
            "WHERE pa.code = #{code} and pa.delete_flag = 0 and pa.current_version = 1 and pa.unit_id is null ORDER BY create_time DESC limit 1"
    })
    TeachPaper selectCurrentVersionPaperByCode(@Param("code") String code);

    @Select({
            "SELECT DISTINCT pa.id, pa.code, pa.subject_id as subjectId, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount,  ",
            "pa.total_score as totalScore, pa.video_url as videoUrl, pa.source, pa.knowledge_tree_id as knowledgeTreeId,  ",
            "pa.exam_province as examProvince, pa.exam_session as examSession, pa.avg_difficulty_value as avgDifficultyValue,  ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag  ",
            "from t_paper_code pa  ",
            "WHERE pa.id = #{id} and pa.delete_flag = 0 limit 1"
    })
    TeachPaper selectPaperCodeById(@Param("id") Integer id);

    @Select({
            "SELECT DISTINCT pa.id, pa.code, pa.subject_id as subjectId, IFNULL(es.name,'') as subjectName, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount,  ",
            "pa.total_score as totalScore, pa.video_url as videoUrl, pa.source, pa.knowledge_tree_id as knowledgeTreeId,  ",
            "pa.exam_province as examProvince, pa.exam_session as examSession, ifnull(pa.exam_tag,0) as examTag, pa.avg_difficulty_value as avgDifficultyValue,  ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag  ", // , pa.couldBeusedByStudents
            "from t_paper_code pa  ",
            "left join ent_subject es on pa.subject_id = es.id ",
            "WHERE pa.code = #{code} and pa.delete_flag = 0 limit 1"
    })
    TeachPaperDetail selectPaperCodeAndSubjectNameByCode(@Param("code") String code);

    @Select({
            "SELECT DISTINCT pa.id, pa.code, pa.subject_id as subjectId, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount,  ",
            "pa.total_score as totalScore, pa.video_url as videoUrl, pa.source, pa.knowledge_tree_id as knowledgeTreeId,  ",
            "pa.exam_province as examProvince, pa.exam_session as examSession, ifnull(pa.exam_tag,0) as examTag, pa.avg_difficulty_value as avgDifficultyValue,  ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag  ", // , pa.couldBeusedByStudents
            "from t_paper_code pa  ",
            "WHERE pa.code = #{code} and pa.delete_flag = 0 limit 1"
    })
    TeachPaper selectPaperCodeByCode(@Param("code") String code);

    @Select({
            "SELECT DISTINCT pa.id, pa.code, pa.subject_id as subjectId, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount,  ",
            "pa.total_score as totalScore, pa.video_url as videoUrl, pa.source, pa.knowledge_tree_id as knowledgeTreeId,  ",
            "pa.exam_province as examProvince, pa.exam_session as examSession, pa.avg_difficulty_value as avgDifficultyValue,  ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag  ", // , pa.couldBeusedByStudents
            "from t_paper_code pa  ",
            "WHERE pa.code = #{code} and pa.delete_flag = 1 limit 1"
    })
    TeachPaper IsPaperCodeDeleted(@Param("code") String code);

    @Select({"<script>",
            "SELECT pa.id, pa.code, pa.subject_id as subjectId, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount, ",
            "pa.total_score as totalScore, pa.video_url as videoUrl, pa.current_version as currentVersion, pa.source, pa.knowledge_tree_id as knowledgeTreeId, " ,
            "pa.exam_province as examProvince, pa.exam_session as examSession, pa.avg_difficulty_value as avgDifficultyValue, ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag ",
            "from t_paper pa ",
            "WHERE pa.code = #{code} and pa.invalid_flag = 0 and pa.unit_id is null",
            "</script>"
    })
    List<TeachPaper> selectPapersByCode(@Param("code") String code);

    @Update({"<script>",
            "UPDATE t_paper_code set invalid_flag = #{invalidFlag}, operator = #{operator} WHERE code = #{paperCode}",
            "</script>"
    })
    Integer updatePaperCodeStatusByCode(@Param("paperCode") String paperCode, @Param("invalidFlag") Integer invalidFlag, @Param("operator") String operator);

    @Update({
            "UPDATE t_paper_code set couldBeusedByStudents = #{couldBeusedByStudents}, operator = #{operator} WHERE code = #{paperCode} and delete_flag = 0"
    })
    Integer updateRealExerciseStatusByCode(@Param("paperCode") String paperCode, @Param("couldBeusedByStudents") Integer couldBeusedByStudents, @Param("operator") String operator);

    @Insert({
            "INSERT INTO t_paper (`code`,subject_id,knowledge_tree_id,`name`,type,invalid_flag,question_amount,total_score,total_time,video_url,current_version,source,exam_province,exam_session,avg_difficulty_value,creator,operator,delete_flag)",
            "SELECT `code`,subject_id,knowledge_tree_id,`name`,type,invalid_flag,question_amount,total_score,total_time,video_url,1,source,exam_province,exam_session,avg_difficulty_value,creator,operator,delete_flag ",
            "from t_paper_code",
            "where `code` = #{code}"
    })
    @SelectKey(before = false, keyProperty = "paperId", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    Integer insertPaper(ReqSavePaperDTO reqSavePaperDTO);

    @Insert({"INSERT INTO t_paper_code ",
            "(code, subject_id, knowledge_tree_id, name, type, invalid_flag,",
            "question_amount, total_score, total_time, video_url, source,",
            "exam_province, exam_session, exam_tag, avg_difficulty_value, create_time,",
            "creator, update_time, operator, delete_flag) ",
            "VALUES ",
            "(#{code}, #{subjectId}, #{knowledgeTreeId}, #{paperName}, #{paperType},",
            "#{invalidFlag}, #{questionAmount}, #{totalScore}, #{totalTime}, #{videoUrl},",
            "#{source}, #{examProvinceId}, #{examSessionId}, #{examTagId}, #{avgDifficultyValue}, #{createTime}, ",
            "#{creator}, #{updateTime}, #{operator}, #{deleteFlag})"
    })
    @SelectKey(before = false, keyProperty = "paperId", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    Integer insertPaperCode(ReqSavePaperDTO reqSavePaperDTO);

    /**
     * 新增C端试卷
     * @param reqSavePaperDTO
     * @return
     */
    @Insert({"INSERT INTO t_paper ",
            "(code, subject_id, knowledge_tree_id, name, type, invalid_flag,",
            "question_amount, total_score, source,",
            "avg_difficulty_value,",
            "creator, operator, delete_flag, current_version) ",
            "VALUES ",
            "(#{code}, #{subjectId}, #{knowledgeTreeId}, #{paperName}, #{paperType},",
            "#{invalidFlag}, #{questionAmount}, #{totalScore},",
            "#{source}, #{avgDifficultyValue}, ",
            "#{creator}, #{operator}, #{deleteFlag}, #{currentVersion})"
    })
    @SelectKey(before = false, keyProperty = "paperId", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    Integer insertQuizzesPaper(ReqSavePaperDTO reqSavePaperDTO);

    @Update({
            "UPDATE t_paper_code  ",
            "SET `name` = #{paperName},type = #{paperType},question_amount = #{questionAmount},total_score = #{totalScore},operator = #{operator}  ",
            "WHERE code = #{paperCode} and delete_flag = 0 "
    })
    Integer updatePaperCode(ReqPaperStatistics reqPaperStatistics);

    @Update({
            "UPDATE t_paper_code_head SET `value` = #{value}, operator = #{operator},show_flag = #{showFlag} WHERE paper_code = #{paperCode} and `code` = #{code} and delete_flag = 0"
    })
    Integer updatePaperCodeHead(PaperHeaderDTO paperHeaderDTO);

    @Insert({"INSERT INTO t_paper_code_no (datetime) VALUES (#{dateTime})"})
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    Integer insertPaperCodeNo(CodeNo codeNo);

    /**
     * 分id(版本)存储试卷试题关联关系-C端
     * @param paperCode
     */
    @Insert({
            "INSERT INTO t_paper_question_rel (paper_id,sequence,question_main_id,score,operator,delete_flag)",
            "SELECT #{paperId},a.sequence,b.id,a.score,a.operator,a.delete_flag",
            "from t_paper_question_code_rel a",
            "INNER JOIN t_question_main b on a.question_code = b.`code` and b.current_version = 1",
            "WHERE a.paper_code = #{paperCode} and a.delete_flag = 0 and b.delete_flag = 0"
    })
    void insertPaperQuestionRelation(@Param("paperCode") String paperCode, @Param("paperId") Integer paperId);


    /**
     * 新增C端试卷试题rel -- 多次随堂考
     * @param questionList
     * @param reqSavePaperDto
     */
    @Insert({
            "<script>",
            "INSERT INTO t_paper_question_rel (paper_id,sequence,question_main_id,score,operator,delete_flag) VALUES",
            "<foreach item=\"item\" index=\"index\" collection=\"questionList\"  separator=\",\" >",
            "(#{reqSavePaperDto.paperId}, #{item.sequence}, #{item.id}, #{item.score}, #{reqSavePaperDto.operator}, #{reqSavePaperDto.deleteFlag})",
            "</foreach>",
            "</script>"
    })
    void insertPaperQuestionRelation2(@Param("questionList") List<ReqPaperQuestionDTO> questionList, @Param("reqSavePaperDto") ReqSavePaperDTO reqSavePaperDto);

    /**
     * 分CODE存储试卷试题关联关系-B端
     * @param questionList
     * @param reqSavePaperDto
     */
    @Insert({"<script>",
            "INSERT INTO t_paper_question_code_rel",
            " (paper_code, sequence, question_code, score, create_time, update_time, operator) ",
            "VALUES ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionList\"  separator=\",\" >",
            "(#{reqSavePaperDto.code}, #{item.sequence}, #{item.code}, #{item.score}," ,
            "#{reqSavePaperDto.createTime}, #{reqSavePaperDto.updateTime}, #{reqSavePaperDto.operator})",
            "</foreach>",
            "</script>"})
    void insertPaperQuestionCodeRelation(@Param("questionList") List<ReqPaperQuestionDTO> questionList, @Param("reqSavePaperDto") ReqSavePaperDTO reqSavePaperDto);

    @Update({
            "UPDATE t_paper_question_code_rel SET delete_flag = 1 where paper_code = #{paperCode} and delete_flag = 0"
    })
    Integer delPaperQuestionCodeRelation(@Param("paperCode") String paperCode);

    @Update({"UPDATE t_paper_question_rel " ,
            "set delete_flag = 1 , operator = #{operator}" ,
            "WHERE paper_id = #{paperId} "})
    void deletePaperQuestionRelByPaperId(@Param("paperId") Integer paperId, @Param("operator") String operator);

    @Update({
            "UPDATE t_paper SET current_version = 0, operator = #{operator} WHERE code = #{code} and current_version = 1"
    })
    Integer updatePaperByPrimaryKey(TeachPaper paper);

    @Update({
            "<script>",
            "UPDATE t_paper set delete_flag = 1, operator = #{operator} WHERE code = #{code} AND invalid_flag = 1",
            "</script>"
    })
    Integer deletePaperByCode(@Param("code") String code, @Param("operator") String operator);

    @Update({
            "<script>",
            "UPDATE t_paper_code set delete_flag = 1, operator = #{operator} WHERE code = #{code} AND invalid_flag = 1",
            "</script>"
    })
    Integer deletePaperCodeByCode(@Param("code") String code, @Param("operator") String operator);

    @Update({
            "<script>",
            "UPDATE t_paper_code set delete_flag = 1, operator = #{operator} WHERE code = #{code}",
            "</script>"
    })
    Integer deletePaperCodeByCodeWithoutFlag(@Param("code") String code, @Param("operator") String operator);


    @Select({
            "SELECT ques.id, ques.`code`, ques.source_type sourceType, ques.question_type questionType, ques.question_id questionId, ",
            "rel.score, ques.knowledge_tree_id knowledgeTreeId, ques.main_node_id mainNodeId, ",
            "ques.vice_node_id_1 viceNodeId1, ques.vice_node_id_2 viceNodeId2, ques.difficulty_value difficultyValue, ",
            "ques.exam_province examProvince,ques.exam_session examSession, ques.exam_tag as examTag, ",
            "GROUP_CONCAT( DISTINCT b.session, c.province_name, d.name ORDER BY b.session DESC SEPARATOR '、' ) as examSessionAndProvinces,",
            "ques.current_version currentVersion, ques.parent_question_id parentQuestionId, ques.sequence,  ",
            "ques.invalid_flag invalidFlag  ",
            "from t_paper_question_code_rel rel  ",
            "INNER JOIN t_question_main ques on rel.question_code = ques.`code` and ques.current_version = 1",
            "LEFT JOIN t_question_main_exam_session_province as a ON a.t_question_main_id = ques.id AND a.delete_flag = 0",
            "LEFT JOIN t_exam_session as b ON b.id = a.exam_session AND b.delete_flag = 0",
            "LEFT JOIN sch_local_province as c ON c.id = a.exam_province AND c.delete_flag = 0",
            "LEFT JOIN t_exam_tag as d ON d.id = a.exam_tag AND d.delete_flag = 0",
            "WHERE rel.paper_code = #{paperCode} and rel.delete_flag = 0 and ques.delete_flag = 0",
            "GROUP BY ques.id",
            "ORDER BY rel.sequence"
    })
    List<QuestionMain> listRelatedQuestionByPaperCode(@Param("paperCode") String paperCode);

    @Select({
            "SELECT ques.id, ques.`code`, ques.source_type sourceType, ques.question_type questionType, ques.content_type contentType, ques.question_id questionId, ",
            "rel.score, ques.knowledge_tree_id knowledgeTreeId, ques.main_node_id mainNodeId, ",
            "ques.vice_node_id_1 viceNodeId1, ques.vice_node_id_2 viceNodeId2, ques.difficulty_value difficultyValue, ",
            "ques.exam_province examProvince,ques.exam_session examSession, ques.exam_tag examTag, ifnull(d.name, '') examTagName, ",
            "GROUP_CONCAT( DISTINCT b.session, c.province_name, ifnull(d.name,'') ORDER BY b.session DESC SEPARATOR '、' ) as examSessionAndProvinces,",
            "ques.current_version currentVersion, ques.parent_question_id parentQuestionId, ques.sequence,  ",
            "ques.invalid_flag invalidFlag  ",
            "from t_paper_question_rel rel  ",
            "INNER JOIN t_question_main ques on rel.question_main_id = ques.id ",
            "LEFT JOIN t_question_main_exam_session_province as a ON a.t_question_main_id = ques.id AND a.delete_flag = 0",
            "LEFT JOIN t_exam_session as b ON b.id = a.exam_session AND b.delete_flag = 0",
            "LEFT JOIN sch_local_province as c ON c.id = a.exam_province AND c.delete_flag = 0",
            "LEFT JOIN t_exam_tag AS d ON d.id = a.exam_tag AND d.delete_flag = 0",
            "WHERE rel.paper_id = #{paperId} and rel.delete_flag = 0 and ques.delete_flag = 0",
            "GROUP BY ques.id",
            "ORDER BY rel.sequence"
    })
    List<QuestionMain> listRelatedQuestionByPaperId(@Param("paperId") Integer paperId);

    @Select({
            "SELECT b.question_type ",
            "from t_paper_question_code_rel a ",
            "INNER JOIN t_question_main b on b.`code` = a.question_code and b.parent_question_id = 0 and b.current_version = 1",
            "where a.delete_flag = 0 and b.delete_flag = 0 and a.paper_code = #{paperCode}",
            "GROUP BY b.question_type ORDER BY a.sequence"
    })
    List<String> selectQuestionTypeInorder(@Param("paperCode") String paperCode);

    @Select({
            "SELECT b.question_type ",
            "from t_paper_question_rel a ",
            "INNER JOIN t_question_main b on b.id = a.question_main_id and b.parent_question_id = 0 ",
            "where a.delete_flag = 0 and b.delete_flag = 0 and a.paper_id = #{paperId}",
            "GROUP BY b.question_type ORDER BY a.sequence"
    })
    List<String> selectQuestionTypeInorderByPaperId(@Param("paperId") Integer paperId);

    @Select({
            "<script>",
            "SELECT b.id,a.score ",
            "from t_paper_question_code_rel a ",
            "INNER JOIN t_question_main b on b.code = a.question_code and b.current_version = 1",
            "where a.delete_flag = 0 and b.delete_flag = 0  ",
            "and a.paper_code = #{paperCode} and b.parent_question_id = 0 ",
            "<if test=\"questionType == 'FILL_BLANK'\">and b.question_type in ('ORDER_FILL_BLANK','DISORDER_FILL_BLANK')</if>",
            "<if test=\"questionType != 'FILL_BLANK'\">and b.question_type = #{questionType} </if>",
            "ORDER BY a.sequence",
            "</script>"
    })
    List<QuestionMain> getQuestionsByType(@Param("paperCode") String paperCode, @Param("questionType") String questionType);

    @Select({
            "<script>",
            "SELECT b.id,a.score ",
            "from t_paper_question_rel a ",
            "INNER JOIN t_question_main b on b.id = a.question_main_id ",
            "where a.delete_flag = 0 and b.delete_flag = 0  ",
            "and a.paper_id = #{paperId} and b.parent_question_id = 0 ",
            "<if test=\"questionType == 'FILL_BLANK'\">and b.question_type in ('ORDER_FILL_BLANK','DISORDER_FILL_BLANK')</if>",
            "<if test=\"questionType != 'FILL_BLANK'\">and b.question_type = #{questionType} </if>",
            "ORDER BY a.sequence",
            "</script>"
    })
    List<QuestionMain> getQuestionsByTypeAndPaperId(@Param("paperId") Integer paperId, @Param("questionType") String questionType);

    @Select({"<script>" ,
            "SELECT ques.code from t_question_main ques LEFT JOIN t_question_compose_paper_info compose on compose.question_code = ques.code " ,
            "WHERE ques.code not in(" ,
            "   SELECT DISTINCT ques.`code` from t_paper_question_code_rel rel, t_paper_code paper, t_question_main ques " ,
            "   WHERE rel.paper_code = paper.code and rel.question_code = ques.code" ,
            "   and rel.delete_flag =0 and paper.delete_flag = 0 and ques.delete_flag = 0" ,
            "   and paper.code = #{code}  )" ,
            "and ques.id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionList\" open=\"(\" separator=\",\" close=\")\" >",
            "#{item.id}" ,
            "</foreach>",
            "and ques.delete_flag = 0 and compose.id is null and ques.parent_question_id = 0",
            "</script>"})
    List<String> listNeedAddedQuestionCodeToComposeInfo(@Param("questionList") List<ReqPaperQuestionDTO> questionList, @Param("code") String code);

    @Select({"<script>" ,
            "SELECT ques.code from t_question_main ques LEFT JOIN t_question_compose_paper_info compose on compose.question_code = ques.code " ,
            "WHERE ques.code not in(" ,
            "   SELECT DISTINCT ques.`code` from t_paper_question_code_rel rel, t_paper_code paper, t_question_main ques " ,
            "   WHERE rel.paper_code = paper.code and rel.question_code = ques.code" ,
            "   and rel.delete_flag =0 and paper.delete_flag = 0 and ques.delete_flag = 0" ,
            "   and paper.`code` = #{code} )" ,
            "and ques.id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionList\" open=\"(\" separator=\",\" close=\")\" >",
            "#{item.id}" ,
            "</foreach>",
            "and ques.delete_flag = 0 and compose.id is not null and ques.parent_question_id = 0",
            "</script>"})
    List<String> listNeedUpdateQuestionCodeToComposeInfo(@Param("questionList") List<ReqPaperQuestionDTO> questionList, @Param("code") String code);

    @Insert({"<script>" ,
            "INSERT INTO t_question_compose_paper_info " ,
            "(question_code, used_times, create_time, update_time, delete_flag) " ,
            "VALUES" ,
            "<foreach item=\"item\" index=\"index\" collection=\"needAddedCodeList\"  separator=\",\" >",
            "(#{item}, 1, #{updateTime}, #{updateTime}, 0)",
            "</foreach>",
            "</script>"})
    Integer batchInsertQuestionComposeInfo(@Param("needAddedCodeList") List<String> needAddedCodeList, @Param("updateTime") Date updateTime);

       @Update({"<script>" ,
            "UPDATE t_question_compose_paper_info" ,
            "SET used_times = used_times + 1 , update_time = #{updateTime}" ,
            "WHERE question_code in",
            "<foreach item=\"item\" index=\"index\" collection=\"needUpdateCodeList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    Integer batchUpdateQuestionComposeInfo(@Param("needUpdateCodeList") List<String> needUpdateCodeList, @Param("updateTime")Date updateTime);

    @Insert({"<script>" ,
            "INSERT INTO t_paper_code_head (paper_code, code, title, value, show_flag, show_title_flag, line_no, sequence, create_time, creator, update_time, operator, delete_flag) " ,
            "VALUES " ,
            "<foreach item=\"item\" index=\"index\" collection=\"insertHeaders\"  separator=\",\" >",
            "( #{item.paperCode}, #{item.code}, #{item.title}, #{item.value}, #{item.showFlag}, #{item.showTitleFlag}, #{item.lineNo}, #{item.sequence}, #{item.createTime}, #{item.creator}, #{item.updateTime}, #{item.operator}, #{item.deleteFlag})",
            "</foreach>",
            "</script>"
    })
    void insertPaperCodeHeader(@Param("insertHeaders") List<PaperHeaderDTO> insertHeaders);

    /**
     * 新增随堂考卷头
     * @param insertHeaders
     */
    @Insert({"<script>" ,
            "INSERT INTO t_paper_head (paper_id, code, title, value, show_flag, show_title_flag, line_no, sequence, creator, operator, delete_flag) " ,
            "VALUES " ,
            "<foreach item=\"item\" index=\"index\" collection=\"insertHeaders\"  separator=\",\" >",
            "( #{item.paperId}, #{item.code}, #{item.title}, #{item.value}, #{item.showFlag}, #{item.showTitleFlag}, #{item.lineNo}, #{item.sequence}, #{item.creator}, #{item.operator}, #{item.deleteFlag})",
            "</foreach>",
            "</script>"
    })
    void insertQuizzesPaperHeader(@Param("insertHeaders") List<PaperHeaderDTO> insertHeaders);

    @Insert({
            "INSERT INTO t_paper_head (paper_id,`code`,title,`value`,show_flag,show_title_flag,line_no,sequence,creator,operator,delete_flag)",
            "SELECT #{paperId},`code`,title,`value`,show_flag,show_title_flag,line_no,sequence,creator,operator,delete_flag",
            "from t_paper_code_head",
            "where paper_code = #{paperCode} and delete_flag = 0"
    })
    void insertPaperHeader(@Param("paperCode") String paperCode, @Param("paperId") Integer paperId);

    @Select({"SELECT id, code as code, title as title, value as value, show_flag showFlag," ,
            "show_title_flag showTitleFlag, line_no lineNo, sequence, create_time createTime," ,
            "creator, update_time updateTime, operator, delete_flag deleteFlag" ,
            "from t_paper_code_head WHERE paper_code = #{paperCode} and delete_flag = 0"})
    List<PaperHeader> listPaperHeaderByPaperCode(@Param("paperCode") String paperCode);

    @Select({
            "SELECT id, code as code, title as title, value as value, show_flag showFlag," ,
            "show_title_flag showTitleFlag, line_no lineNo, sequence, create_time createTime," ,
            "creator, update_time updateTime, operator, delete_flag deleteFlag" ,
            "from t_paper_head WHERE paper_id = #{paperId} and delete_flag = 0"
    })
    List<PaperHeader> listPaperHeaderByPaperId(@Param("paperId") Integer paperId);

    @Select({
            "SELECT id, code as code, title as title, value as value, show_flag showFlag," ,
            "show_title_flag showTitleFlag, line_no lineNo, sequence, create_time createTime," ,
            "creator, update_time updateTime, operator, delete_flag deleteFlag" ,
            "from t_paper_code_head WHERE paper_code = #{paperCode} and code = #{code} and delete_flag = 0"
    })
    PaperHeader selectPaperHeaderByCondition(@Param("paperCode") String paperCode, @Param("code") String code);

    @Select({
            "SELECT ques.id id, ques.code code,rel.sequence sequence, rel.score score, ques.difficulty_value difficultyValue, ques.parent_question_id parentQuestionId, ques.question_type questionType",
            "from t_paper_question_code_rel rel ",
            "LEFT JOIN t_question_main ques on ques.code = rel.question_code and ques.current_version = 1 ",
            "WHERE rel.paper_code = #{paperCode} and rel.delete_flag = 0 and ques.delete_flag = 0"
    })
    List<ReqPaperQuestionDTO> listRelatedQuestion(@Param("paperCode") String paperCode);

    @Select({
            "<script>",
            "SELECT a.question_main_id",
            "FROM t_paper_question_rel as a",
            "WHERE a.paper_id = #{paperId}",
            "   AND EXISTS (SELECT 1 FROM t_question_main WHERE id = a.question_main_id AND parent_question_id = 0)",
            "ORDER BY a.sequence",
            "<if test='startIndex!=null and size!=null'>",
            "limit #{startIndex},#{size}",
            "</if>",
            "</script>"})
    List<Integer> listRelatedQuestionByPaperIdAndSize(@Param("paperId") Integer paperId,
                                                      @Param("startIndex") Integer startIndex,
                                                      @Param("size") Integer size);

    /**
     * add by hurw 20171108 查询学员答案
     * @param recordId
     * @return
     */
    @Select({
            "select `paper_user_record_id` paperUserRecordId, `sequence`,case when question_sub_id is null or question_sub_id=0 then `question_id` else question_sub_id end questionId, `question_id_source` questionIdSource, `question_type` questionType, `mark_paper_way` markPaperWay, `answer`, `correct_flag` correctFlag, `answer_time` answerTime, `score` ",
                    "from ${tableName} a ",
                    "where a.paper_user_record_id=#{recordId} and delete_flag=0",
            "union all ",
                    "SELECT `paper_user_record_id` paperUserRecordId, `sequence`,question_id questionId, `question_id_source` questionIdSource, '' questionType,  ",
                    "'' markPaperWay, group_concat(`answer`,'；'),5 correctFlag, sum(`answer_time`) answerTime, sum(`score`) score ",
                    "FROM ${tableName} a ",
                    "WHERE a.paper_user_record_id=#{recordId} AND delete_flag=0 and (ifnull(question_sub_id,0)!=0) ",
                    "GROUP BY paper_user_record_id,sequence,question_id,question_id_source "
    })
    List<PaperUserQuestionDTO> retrievePaperUserQuestionById(@Param("recordId") Integer recordId, @Param("tableName") String tableName);

    /**
     * add by hurw 20171108 根据paperId查询试题平均数
     * @param paperId
     * @return
     */
    @Select({
            "select a.question_id as questionId," ,
                    "max(a.avg_answer_time) as avgAnswerTime,",
					"max(a.avg_correct_rate) as avgCorrectRate,",
					"max(a.easy_error_answer) as easyErrorAnswer,",
					"max(a.avg_score) as avgScore" ,
                    "from ent_question_answer_statistic_info a,t_paper_question_rel b ",
                    "where a.question_id=b.question_main_id and b.paper_id=#{value} " ,
                    "group by a.question_id"
    })
    List<RestQuestionAnswerStatInfo> selectQuestionAnswerStatByPaperId(Integer paperId);

    /**
     * 查询B端试卷内试题内容
     * @param paperCode
     * @return
     */
    @Select({
            "SELECT a.score,a.sequence,a.paper_code paperCode,b.id questionMainId",
            "from t_paper_question_code_rel a",
            "INNER JOIN t_question_main b on b.code = a.question_code and b.current_version =  1",
            "where a.paper_code = #{paperCode} ",
            "AND a.delete_flag = 0 AND b.delete_flag = 0",
            "ORDER BY a.sequence,b.id"
    })
    List<ResPaperQuestionRelationDTO> selectPaperQuestionRelations(@Param("paperCode") String paperCode);

    /**
     * 查询C端试卷内试题内容
     * @param paperId
     * @return
     */
    @Select({
            "SELECT score,sequence,paper_id paperId,question_main_id questionMainId",
            "from t_paper_question_rel",
            "where paper_id = #{paperId} AND delete_flag = 0",
            "ORDER BY sequence,question_main_id"
    })
    List<ResPaperQuestionRelationDTO> selectCurrentVersionRelations(@Param("paperId") Integer paperId);

    @Select({
            "<script>",
            "SELECT DISTINCT a.id",
            "from t_question_main as a",
            "INNER JOIN t_question_main_exam_session_province as b ON b.t_question_main_id = a.id AND b.delete_flag = 0",
            "INNER JOIN t_exam_session as c ON c.id = b.exam_session AND c.delete_flag = 0 AND c.id = #{examSessionId}",
            "INNER JOIN sch_local_province as d ON d.id = b.exam_province AND d.delete_flag = 0 AND d.id = #{examProvinceId}",
            "<if test='examTagId != null and examTagId > 0'>",
            "INNER JOIN t_exam_tag as e ON e.id = b.exam_tag AND e.delete_flag = 0 AND e.id = #{examTagId}",
            "</if>",
            "where a.source_type = 'REAL_QUESTION' AND a.delete_flag = 0 and a.id in ",
            "<foreach item=\"id\" index=\"index\" collection=\"questionIdList\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{id}",
            "</foreach>",
            "GROUP BY a.id, a.knowledge_tree_id, b.exam_session, b.exam_province",
            "<if test='examTagId != null and examTagId > 0'>",
            ",b.exam_tag",
            "</if>",
            "</script>"
    })
    List<Integer> selectRealQuestionSourceByIds(@Param("questionIdList") List<Integer> questionIdList,
                                                @Param("examSessionId") Integer examSessionId,
                                                @Param("examProvinceId") Integer examProvinceId,
                                                @Param("examTagId") Integer examTagId);

    @Select({"<script>",
            "SELECT `code` FROM t_question_main WHERE delete_flag = 0 AND current_version = 1 AND `code` in (",
            "<foreach item=\"item\" index=\"index\" collection=\"questionList\"  separator=\",\" >",
            "#{item.code}",
            "</foreach>",
            ") </script>"})
    Set<String> getValidQuestionCode(@Param("questionList") List<ReqPaperQuestionDTO> questionList);

    /**
     * 创建模考试卷
     *
     * @param exam
     * @param startTime
     * @param endTime
     * @return
     */
    @Insert({
            "INSERT INTO ent_exam (college_id, subject_id, name, start_time, end_time, paper_code, round_id, operator, exam_type, paper_id) ",
                    "VALUES(#{exam.collegeId}, #{exam.subjectId}, #{exam.name}, #{startTime}, #{endTime}, #{exam.paperCode}, ",
                    "#{exam.roundId}, #{exam.operator}, 'MOCK_EXAM', #{paperId})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "exam.exerciseExamId")
    int insertMockExam(@Param("exam") ReqMockExamDTO exam, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime, @Param("paperId") Integer paperId);

    /**
     * 编辑模拟考试信息
     *
     * @param exam
     * @param startTime
     * @param endTime
     * @return
     */
    @Update({
            "update ent_exam set name = #{exam.name}, start_time = #{startTime}, end_time = #{endTime}, ",
                    "operator = #{exam.operator} where id = #{exam.exerciseExamId} and delete_flag = 0"
    })
    @Options(useGeneratedKeys = true, keyProperty = "exam.exerciseExamId")
    int updateMockExam(@Param("exam") ReqMockExamDTO exam, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

    /**
     * 删除模拟考试信息
     *
     * @param exam
     * @return
     */
    @Update({
            "update ent_exam set delete_flag = 1 where id = #{exam.exerciseExamId} and name = #{exam.name} ",
                    "and start_time = #{startTime} and end_time = #{endTime} and delete_flag = 0"
    })
    @Options(useGeneratedKeys = true, keyProperty = "exam.exerciseExamId")
    int deleteMockExam(@Param("exam") ReqMockExamDTO exam, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);

    /**
     * 根据试卷code和已存在C端的试卷，返回剩余知识点和题目
     * @param paperIds
     * @return
     */
    @Select({
            "<script>",
            "SELECT n.id nodeId, n.name nodeName, n.serial_number serialNumber, count(q.id) questionNum",
            "FROM t_knowledge_node n",
            "INNER JOIN t_question_main q ON q.main_node_id = n.id AND q.parent_question_id = 0 AND q.delete_flag = 0",
            "INNER JOIN t_paper_question_rel qr ON qr.question_main_id = q.id AND qr.delete_flag = 0",
            "INNER JOIN t_paper p ON p.id = qr.paper_id AND p.id IN",
            "<foreach collection='paperIds' item='item' open='(' separator=',' close=')'>",
            "#{item}</foreach>",
            "AND p.current_version = 1 AND p.delete_flag = 0",
            "WHERE n.delete_flag = 0",
            "GROUP BY n.id",
            "</script>"
    })
    List<NodeQuestionInfo> retrieveNodeQuestionsByCondition(@Param("paperIds") List<Integer> paperIds);

    /**
     * 随堂考下某一知识点的题
     * @param paperIds
     * @param nodeId
     * @return
     */
    @Select({
            "<script>",
            "SELECT q.id, q.code, q.sequence, q.score, q.difficulty_value difficultyValue, q.parent_question_id parentQuestionId, q.question_type questionType, q.source_type sourceType",
            "FROM t_question_main q",
            "INNER JOIN t_knowledge_node n ON q.main_node_id = n.id AND n.id = #{nodeId} AND n.delete_flag = 0",
            "INNER JOIN t_paper_question_rel qr ON qr.question_main_id = q.id AND qr.delete_flag = 0",
            "INNER JOIN t_paper p ON p.id = qr.paper_id AND p.id IN",
            "<foreach collection='paperIds' item='item' open='(' separator=',' close=')'>",
            "#{item}</foreach>",
            "AND p.current_version = 1 AND p.delete_flag = 0",
            "WHERE q.parent_question_id = 0 AND q.delete_flag = 0",
            "</script>"
    })
    List<ReqPaperQuestionDTO> getQuestionsByNodeIdAndPaperId(@Param("paperIds") List<Integer> paperIds, @Param("nodeId") Integer nodeId);

    @Select({
            "<script>",
            "SELECT a.paper_id paperId,b.`name` paperName ",
            "FROM t_paper_unit_rel a ",
            "INNER JOIN t_paper b on a.paper_id = b.id and b.delete_flag = 0  ",
            "and b.type = 'quiz' ",
            "where a.delete_flag = 0 and a.unit_id = #{unitId}",
            "<if test=\"paperCode != null\">",
            "AND a.paper_code = #{paperCode}</if>",
            "</script>"
    })
    List<QuizPaperDTO> getQuizPapers(@Param("unitId") Integer unitId, @Param("paperCode") String paperCode);

    @Insert({
            "<script>",
            "insert into t_paper_unit_rel(unit_id, paper_code, paper_id, operator, teacher_id) values",
            "<foreach collection=\"rels\" item=\"rel\" separator=\",\">",
            "(#{rel.unitId}, #{rel.paperCode}, #{rel.paperId}, #{rel.operator}, #{rel.teacherId})",
            "</foreach>",
            "</script>"
    })
    int batchInsertPaperUnitRel(@Param("rels") List<QuizPaperUnitRelDTO> rels);


    @Select({
            "SELECT a.paper_id",
            "from t_paper_unit_rel a",
            "INNER JOIN t_paper b on a.paper_id = b.id and b.delete_flag = 0 and b.type = 'quiz'",
            "where a.delete_flag = 0 and a.unit_id = #{unitId} and a.paper_code = #{paperCode}"
    })
    List<Integer> selectPaperIdsByUnitId(@Param("unitId") Integer unitId, @Param("paperCode") String paperCode);

    /**
     * 查询课程配置的随堂考试卷内还有那些大题可组卷
     * @param oldPaperId   课程配置的随堂考试卷id
     * @param newIds  多次随堂考试卷id列表
     * @return
     */
    @Select({
            "<script>",
            "SELECT b.id questionMainId,b.main_node_id nodeId",
            "from t_paper_question_rel a",
            "INNER JOIN t_question_main b on a.question_main_id = b.id ",
            "<if test=\"newIds != null and newIds.size() != 0\">",
            "LEFT JOIN t_paper_question_rel c on c.question_main_id = b.id and c.delete_flag = 0 and c.paper_id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"newIds\" open=\"(\" separator=\",\" close=\")\" >",
            "#{item}" ,
            "</foreach>",
            "</if>",
            "where a.delete_flag = 0 and a.paper_id = #{oldPaperId}",
            "and b.delete_flag = 0 and b.parent_question_id = 0",
            "<if test=\"newIds != null and newIds.size() != 0\">",
            "and c.id IS NULL",
            "</if>",
            "</script>"
    })
    List<ResNodeQuestionRelDTO> selectLeftQuestionInfo(@Param("oldPaperId") Integer oldPaperId, @Param("newIds") List<Integer> newIds);

    /**
     * 查询试卷内试题的答题人数
     * @param paperId   C端试卷id
     * @param index
     * @param correctFlag   答对的
     * @return
     */
    @Select({
            "<script>",
            "SELECT a.question_main_id questionMainId, SUM(a.total_answer_num) totalAnswerNum",
            "from t_tiku_exam_question_answer_statistics_${index} a",
            "where a.t_paper_id = #{paperId} and a.delete_flag = 0",
            "<if test=\"correctFlag != null and correctFlag == 1\">and a.correct_flag = 1 </if>",
            "GROUP BY a.question_main_id",
            "</script>"
    })
    List<ResQuestionAnswerNum> selectQuestionAnswerNum(@Param("paperId") Integer paperId, @Param("index") int index, @Param("correctFlag") Integer correctFlag);

    @Select({
            "<script>",
            "SELECT a.question_main_id questionMainId, SUM(a.total_answer_num) totalAnswerNum ",
            "from t_tiku_exam_question_answer_statistics_${index} a ",
            "where a.t_paper_id = #{paperId} and a.question_main_id = #{questionMainId} and a.delete_flag = 0",
            "<if test=\"correctFlag != null and correctFlag == 1\">and a.correct_flag = 1 </if>",
            "</script>"
    })
    ResQuestionAnswerNum getQuestionAnswerNum(@Param("paperId") Integer paperId,
                                              @Param("questionMainId") Integer questionMainId,
                                              @Param("index") int index,
                                              @Param("correctFlag") Integer correctFlag);

    @Select({
            "SELECT a.question_main_id ",
            "from t_paper_question_rel a ",
            "where a.delete_flag = 0 and a.paper_id = #{paperId}",
            "ORDER BY a.sequence"
    })
    List<Integer> listQuestionIdByPaperId(@Param("paperId") Integer paperId);

    @Select({"SELECT  ",
            "a.paper_id paperId, ",
            "b.`name` paperName, ",
            "a.teacher_id teacherId ",
            "from t_paper_unit_rel a ",
            "INNER JOIN t_paper b on b.id = a.paper_id and b.type = 'quiz'",
            "where a.unit_id = #{teachUnitId} AND a.paper_code = #{paperCode} and a.delete_flag = 0 and b.delete_flag = 0"})
    List<NewQuizPaperForCDTO> queryQuizPaperList(@Param("teachUnitId") Integer teachUnitId, @Param("paperCode") String paperCode);

    @Select({
            "SELECT DISTINCT pa.id, pa.code, pa.subject_id as subjectId, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount,  ",
            "pa.total_score as totalScore, pa.video_url as videoUrl, pa.current_version as currentVersion, pa.source, pa.knowledge_tree_id as knowledgeTreeId,  ",
            "pa.exam_province as examProvince, pa.exam_session as examSession, pa.avg_difficulty_value as avgDifficultyValue,  ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag  ",
            "from t_paper pa  ",
            "WHERE pa.code = #{code} and pa.unit_id = #{unitId} and pa.delete_flag = 0 and pa.current_version = 1 ORDER BY create_time DESC limit 1"
    })
    TeachPaper selectCurrentPaperByCode(@Param("code") String code, @Param("unitId") Integer unitId);

    @Insert({
            "INSERT INTO t_paper (unit_id,`code`,subject_id,knowledge_tree_id,`name`,type,invalid_flag,question_amount,total_score,total_time,video_url,current_version,source,exam_province,exam_session,avg_difficulty_value,creator,operator,delete_flag)",
            "SELECT #{unitId},`code`,subject_id,knowledge_tree_id,`name`,type,invalid_flag,question_amount,total_score,total_time,video_url,1,source,exam_province,exam_session,avg_difficulty_value,creator,operator,delete_flag ",
            "from t_paper_code",
            "where `code` = #{reqSavePaperDTO.code}"
    })
    @SelectKey(before = false, keyProperty = "reqSavePaperDTO.paperId", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    Integer insertCPaper(@Param("reqSavePaperDTO") ReqSavePaperDTO reqSavePaperDTO, @Param("unitId") Integer unitId);


    /**
     * 根据试卷code批量查询试卷id
     *
     * @param codes
     * @return
     */
    @Select({
            "<script>",
            " SELECT DISTINCT ",
            " tp.id, ",
            " pa.`code`, ",
            " pa.`name`  ",
            " FROM ",
            " t_paper_code pa ",
            " LEFT JOIN t_paper tp ON pa.`code` = tp.`code` AND tp.delete_flag = 0  ",
            " AND tp.current_version = 1  ",
            " AND tp.unit_id IS NULL ",
            " WHERE ",
            " pa.`code` in ",
            "<foreach item=\"item\" index=\"index\" collection=\"codes\" open=\"(\" separator=\",\" close=\")\" >",
            "#{item}",
            "</foreach>",
            " AND pa.delete_flag = 0 ",
            " ORDER BY ",
            " tp.create_time DESC ",
            "</script>"
    })
    @MapKey("code")
    Map<String, TeachPaper> selectPaperByCodeList(@Param("codes") List<String> codes);


    /**
     * 查询paperCode下对应的数据列表
     *
     * @param code
     * @return
     */
    @Select({
            "SELECT  pa.id, pa.code, pa.subject_id as subjectId, pa.name, pa.type, pa.invalid_flag as invalidFlag, pa.question_amount as questionAmount,  ",
            " IFNULL(pa.total_score, 0 ) as totalScore, pa.video_url as videoUrl, pa.current_version as currentVersion, pa.source, pa.knowledge_tree_id as knowledgeTreeId,  ",
            "pa.exam_province as examProvince, pa.exam_session as examSession, pa.avg_difficulty_value as avgDifficultyValue,  ",
            "pa.create_time createTime, pa.creator, pa.update_time updateTime, pa.operator, pa.delete_flag as deleteFlag  ",
            "from t_paper pa  ",
            "WHERE pa.code = #{code} and pa.delete_flag = 0 and pa.unit_id is null ORDER BY pa.id DESC "
    })
    List<TeachPaper> selectPaperListByCode(@Param("code") String code);

    @Select({
            "SELECT name FROM ent_subject WHERE id = #{subjectId}"
    })
    String getSubjectName(@Param("subjectId") Integer subjectId);


    /**
     * 查询试卷大题的数量
     *
     * @param paperId
     * @return
     */
    @Select({
            "SELECT count(rel.question_main_id) ",
            "from t_paper_question_rel rel, t_question_main b ",
            "WHERE rel.paper_id = #{paperId} and rel.delete_flag = 0 ",
            "and rel.question_main_id=b.id and b.delete_flag=0 and b.parent_question_id=0 ",
    })
    Integer selectPaperBigQuestionCount(@Param("paperId") Integer paperId);

    @Insert({
            "insert ignore t_question_main_unit_paper_tag (question_main_code)",
            "select a.question_code ",
            "from t_paper_question_code_rel a ",
            "inner join t_question_main b on a.question_code = b.code ",
            "and b.parent_question_id = 0 and b.current_version = 1 and b.delete_flag = 0 ",
            "where a.paper_code = #{paperCode} and a.delete_flag = 0"
    })
    int batchInsertQuestionUnitPaperTag(@Param("paperCode") String paperCode);

    /**
     * 根据试卷code和subjectId批量查询试卷id
     *
     * @param codes
     * @return
     */
    @Select({
            "<script>",
            " SELECT DISTINCT ",
            " tp.id, ",
            " pa.`code`, ",
            " pa.`name`  ",
            " FROM ",
            " t_paper_code pa ",
            " LEFT JOIN t_paper tp ON pa.`code` = tp.`code` AND tp.delete_flag = 0  ",
            " AND tp.current_version = 1  ",
            " AND tp.unit_id IS NULL ",
            " WHERE pa.subject_id = #{subjectId}",
            " AND pa.`code` in ",
            "<foreach item=\"item\" index=\"index\" collection=\"codes\" open=\"(\" separator=\",\" close=\")\" >",
            "#{item}",
            "</foreach>",
            " AND pa.delete_flag = 0 ",
            " ORDER BY ",
            " tp.create_time DESC ",
            "</script>"
    })
    @MapKey("code")
    Map<String, TeachPaper> selectPaperByCodeSubjectIdList(@Param("codes") List<String> codes, @Param("subjectId") Integer subjectId);

    /**
     * 根据试卷code批量查询试卷subjectId
     *
     * @param codes
     * @return
     */
    @Select({
            "<script>",
            " SELECT DISTINCT ",
            " pa.subject_id subjectId",
            " FROM ",
            " t_paper_code pa ",
            " LEFT JOIN t_paper tp ON pa.`code` = tp.`code` AND tp.delete_flag = 0  ",
            " AND tp.current_version = 1  ",
            " AND tp.unit_id IS NULL ",
            " WHERE ",
            " pa.`code` in ",
            "<foreach item=\"item\" index=\"index\" collection=\"codes\" open=\"(\" separator=\",\" close=\")\" >",
            "#{item}",
            "</foreach>",
            " AND pa.delete_flag = 0 ",
            "</script>"
    })
    @MapKey("subjectId")
    Map<Integer, TeachPaper> selectSubjectIdByCode(@Param("codes") List<String> codes);

    @Select({"<script>",
            "SELECT count(paper.id)",
            "FROM t_paper_code as paper  ",
            "LEFT JOIN t_exam_session es on es.id = paper.exam_session  ",
            "LEFT JOIN sch_local_province slp on slp.id = paper.exam_province  ",
            "LEFT JOIN t_exam_tag tag on tag.id = paper.exam_tag AND tag.delete_flag = 0  ",
            "WHERE paper.delete_flag = 0 ",
            "<if test=\"knowledgeTreeId != null and knowledgeTreeId != ''\">and paper.knowledge_tree_id = #{knowledgeTreeId} </if>",
            "<if test=\"knowledgeTreeIds != null and knowledgeTreeIds.size > 0 \"> ",
            "and paper.knowledge_tree_id in",
            "<foreach item=\"item\" index=\"index\" collection=\"knowledgeTreeIds\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"paperType != null and paperType != ''\">and paper.type = #{paperType} </if>",
            "<if test=\"examSessionId != null and examSessionId != ''\">and paper.exam_session = #{examSessionId} </if>",
            "<if test=\"examSessionIds != null and examSessionIds.size > 0 \"> ",
            "and paper.exam_session in",
            "<foreach item=\"item\" index=\"index\" collection=\"examSessionIds\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "</if>",
            "<if test=\"examProvinceId != null and examProvinceId != ''\">and paper.exam_province = #{examProvinceId}  </if>",
            "<if test=\"examTagId != null\">and paper.exam_tag = #{examTagId} </if>",
            "<if test=\"difficultyValue != null and difficultyValue != ''\">and paper.avg_difficulty_value BETWEEN #{minDifficulty} and #{maxDifficulty} </if>",
            "<if test=\"invalidFlag != null \">and paper.invalid_flag = #{invalidFlag} </if>",
            "<if test=\"source != null \">and paper.source = #{source} </if>",
            "<if test=\"operator != null \">and paper.creator = #{operator} </if>",
            "</script>"
    })
    Integer getPaperListCountByCondition(PaperSearchingDTO condition);
}
