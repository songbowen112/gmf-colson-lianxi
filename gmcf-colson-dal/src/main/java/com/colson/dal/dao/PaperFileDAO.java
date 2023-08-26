package com.colson.dal.dao;

import com.colson.dal.dto.ReqPaperFileDTO;
import com.colson.dal.dto.ReqPaperFileQueryDTO;
import com.colson.dal.vo.ResPaperFileVO;
import com.colson.dal.vo.ResPaperTypeVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperFileDAO {

    /**
     * 分页查询试卷文件列表
     * @param conondition
     * @author chenliang
     * @date 2022-07-18 16:22
     * @return java.util.List<com.sunlands.paperfile.vo.ResPaperFileVO>
     */
    @Select({
            "<script>",
            "SELECT t.id AS id, t.subject_id AS subjectId, t.knowledge_tree_id AS knowledgeTreeId,",
            "t.paper_type AS paperType, paper_name AS paperName, t.paper_upload_url AS paperUploadUrl,",
            "t.exam_session AS examSession, t.exam_province AS examProvince, slp.province_name AS examProvinceName,",
            "t.a_b_paper AS abPaperId, t.avg_difficulty_value AS avgDifficultyValue, t.question_amount AS questionAmount,",
            "t.total_score AS totalScore,t.creator AS creator, t.operator AS operator,",
            "DATE_FORMAT(t.create_time, '%Y-%m-%d %H:%i:%S') AS createTime,",
            "DATE_FORMAT(t.update_time, '%Y-%m-%d %H:%i:%S') AS modifyTime,",
            "t.on_offline AS onOffline",
            "FROM t_paper_upload t",
            "LEFT JOIN sch_local_province slp ON t.exam_province = slp.id AND slp.delete_flag = 0",
            "WHERE 1=1",
            "<if test=\"subjectId!=null\">AND t.subject_id = #{subjectId}</if>",
            "<if test=\"knowledgeTreeId!=null\">AND t.knowledge_tree_id = #{knowledgeTreeId}</if>",
            "<if test=\"paperType!=null and paperType!=''\">AND t.paper_type = #{paperType}</if>",
            "<if test=\"examSession!=null\">AND t.exam_session = #{examSession}</if>",
            "<if test=\"examProvince!=null\">AND t.exam_province = #{examProvince}</if>",
            "<if test=\"avgDifficultyValue!=null\">AND t.avg_difficulty_value = #{avgDifficultyValue}</if>",
            "<if test=\"onOffline!=null\">AND t.on_offline = #{onOffline}</if>",
            "<if test=\"abPaperId!=null\">AND t.a_b_paper = #{abPaperId}</if>",
            "<if test=\"idOrPaperName!=null and idOrPaperName!=''\">AND (t.id = #{idOrPaperName} OR t.paper_name LIKE concat('%', #{idOrPaperName}, '%'))</if>",
            "AND t.delete_flag = 0",
            "ORDER BY t.id DESC",
            "LIMIT #{pageIndex}, #{pageSize}",
            "</script>"
    })
    List<ResPaperFileVO> paperFileList(ReqPaperFileQueryDTO conondition);

    @Select({
            "<script>",
            "SELECT COUNT(1)",
            "FROM t_paper_upload t",
            "LEFT JOIN sch_local_province slp ON t.exam_province = slp.id AND slp.delete_flag = 0",
            "WHERE 1=1",
            "<if test=\"paperCode!=null\">AND t.paper_code = #{paperCode}</if>",
            "<if test=\"subjectId!=null\">AND t.subject_id = #{subjectId}</if>",
            "<if test=\"knowledgeTreeId!=null\">AND t.knowledge_tree_id = #{knowledgeTreeId}</if>",
            "<if test=\"paperType!=null and paperType!=''\">AND t.paper_type = #{paperType}</if>",
            "<if test=\"examSession!=null\">AND t.exam_session = #{examSession}</if>",
            "<if test=\"examProvince!=null\">AND t.exam_province = #{examProvince}</if>",
            "<if test=\"avgDifficultyValue!=null\">AND t.avg_difficulty_value = #{avgDifficultyValue}</if>",
            "<if test=\"onOffline!=null\">AND t.on_offline = #{onOffline}</if>",
            "<if test=\"abPaperId!=null\">AND t.a_b_paper = #{abPaperId}</if>",
            "<if test=\"idOrPaperName!=null and idOrPaperName!=''\">AND (t.id = #{idOrPaperName} OR t.paper_name LIKE concat('%', #{idOrPaperName}, '%'))</if>",
            "AND t.delete_flag = 0",
            "</script>"
    })
    int paperFileCount(ReqPaperFileQueryDTO conondition);

    @Insert({"INSERT INTO t_paper_upload(paper_code, subject_id, knowledge_tree_id, paper_type, paper_name,",
            "paper_upload_url, exam_session, exam_province, a_b_paper, avg_difficulty_value, question_amount,",
            "total_score, creator, operator)",
            "VALUES(",
            "#{paperCode},",
            "#{subjectId},",
            "#{knowledgeTreeId},",
            "#{paperType},",
            "#{paperName},",
            "#{paperUploadUrl},",
            "#{examSession},",
            "#{examProvince},",
            "#{abPaperId},",
            "#{avgDifficultyValue},",
            "#{questionAmount},",
            "#{totalScore},",
            "#{creator},",
            "#{operator}",
            ")"
            })
    int paperFileAdd(ReqPaperFileDTO paperFileAddDTO);

    @Insert({"INSERT INTO t_paper_upload(paper_code, subject_id, knowledge_tree_id, paper_type, paper_name,",
            "paper_upload_url, exam_session, exam_province, a_b_paper, avg_difficulty_value, question_amount,",
            "total_score, creator, operator, on_offline)",
            "VALUES(",
            "#{paperCode},",
            "#{subjectId},",
            "#{knowledgeTreeId},",
            "#{paperType},",
            "#{paperName},",
            "#{paperUploadUrl},",
            "#{examSession},",
            "#{examProvince},",
            "#{abPaperId},",
            "#{avgDifficultyValue},",
            "#{questionAmount},",
            "#{totalScore},",
            "#{creator},",
            "#{operator},",
            "#{onOffline}",
            ")"
    })
    int insertPaperUpload(ReqPaperFileDTO paperFileAddDTO);

    @Update({
            "UPDATE t_paper_upload",
            "SET on_offline = 0,delete_flag = 1",
            "WHERE id = #{id}"
    })
    int paperFileDelete(ReqPaperFileDTO paperFileAddDTO);

    @Update({
            "UPDATE t_paper_upload",
            "SET ",
            "subject_id = #{subjectId},",
            "knowledge_tree_id = #{knowledgeTreeId},",
            "paper_type = #{paperType},",
            "paper_name = #{paperName},",
            "paper_upload_url = #{paperUploadUrl},",
            "exam_session = #{examSession},",
            "exam_province = #{examProvince},",
            "a_b_paper = #{abPaperId},",
            "avg_difficulty_value = #{avgDifficultyValue},",
            "question_amount = #{questionAmount},",
            "total_score = #{totalScore},",
            "operator = #{operator}",
            "WHERE id = #{id} AND delete_flag = 0"
    })
    int paperFileModify(ReqPaperFileDTO paperFileModifyDTO);

    @Update({
            "UPDATE t_paper_upload",
            "SET ",
            "on_offline = #{onOffline}",
            "WHERE id = #{id} AND delete_flag = 0"
    })
    int paperFileOnoffline(ReqPaperFileDTO paperFileModifyDTO);

    @Select({
            "SELECT t.id AS id, t.subject_id AS subjectId, t.knowledge_tree_id AS knowledgeTreeId,",
            "t.paper_type AS paperType, paper_name AS paperName, t.paper_upload_url AS paperUploadUrl,",
            "t.exam_session AS examSession, t.exam_province AS examProvince,",
            "t.a_b_paper AS abPaperId, t.avg_difficulty_value AS avgDifficultyValue, t.question_amount AS questionAmount,",
            "t.total_score AS totalScore,t.creator AS creator, t.operator AS operator,",
            "t.on_offline AS onOffline",
            "FROM t_paper_upload t",
            "WHERE t.id = #{id}",
            "AND t.delete_flag = 0"
    })
    ResPaperFileVO paperFileGet(ReqPaperFileDTO conondition);

    @Select({
            "<script>",
            "SELECT COUNT(1)",
            "FROM t_paper_upload t",
            "WHERE t.subject_id = #{subjectId}",
            "AND t.exam_province =#{examProvince}",
            "AND t.paper_type = #{paperType}",
            "AND t.exam_session =#{examSession}",
            "AND t.a_b_paper = #{abPaperId}",
            "AND t.delete_flag = 0",
            "<if test = \"id!=null\">AND t.id NOT IN (#{id})</if>",
            "</script>"
    })
    int paperFileExistCount(ReqPaperFileDTO conondition);

    @Select({
            "<script>",
            "SELECT COUNT(1)",
            "FROM t_paper_upload t",
            "WHERE",
            "t.paper_code = #{paperCode}",
            "AND t.delete_flag = 0",
            "</script>"
    })
    int paperFileCountByCode(String paperCode);

    @Select({
            "SELECT t.paper_type AS paperType, t.paper_type_desc AS paperTypeDesc, t.show_front AS showFront, t.save_limit AS saveLimit,",
            "t.sort_num AS sortNum, t.show_session_province AS showSessionProvince, t.valid_score AS validScore,",
            "t.valid_question_type AS validQuestionType, t.support_file_upload AS supportFileUpload",
            "FROM t_paper_type t",
            "WHERE t.delete_flag = 0",
            "ORDER BY t.sort_num ASC"
    })
    List<ResPaperTypeVO> paperTypeGet();

    @Select({
            "SELECT",
            "t.exam_session AS examSession,",
            "(SELECT session FROM t_exam_session WHERE id = t.exam_session) AS examSessionName,",
            "t.exam_province AS examProvince, ",
            "(SELECT province_name FROM sch_local_province WHERE id = t.exam_province) AS examProvinceName,",
            "t.a_b_paper AS abPaperId,",
            "(SELECT name FROM t_exam_tag WHERE id = t.a_b_paper) AS abPaperName",
            "FROM t_paper_upload t",
            "WHERE",
            "t.knowledge_tree_id = #{knowledgeTreeId}",
            "AND t.delete_flag = 0",
    })
    List<ResPaperFileVO> getProvinceAndSession(ReqPaperFileQueryDTO conondition);
}
