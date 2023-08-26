package com.colson.dal.dao;

import com.colson.dal.dto.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 试卷-学员答题
 * add by chenqiuxia 2017/10/27
 */
@Repository
public interface PaperAnalysisDAO {

    /**
     **
     * 试卷分析-学员答题概要列表-根据code查询该试卷所有版本的学员答题记录
     * @param paperCode
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Select({
            "SELECT a.stu_id stuId,a.stu_name stuName,a.end_time endTimeDate,a.answer_paper_time consumedTimeInt,",
            "a.total_score stuScore,b.total_score fullCredit,a.id paperUserRecordId",
            "from ent_paper_user_record a",
            "INNER JOIN t_paper b on b.id = a.paper_id",
            "WHERE a.delete_flag = 0 and b.delete_flag = 0",
            "and a.paper_id_source = 't_paper' and a.status_code = 'COMPLETE'",
            "and b.code = #{paperCode}",
            "limit #{pageIndex},#{pageSize}"
    })
    List<ResStuPaperAnswerDTO> getPaperAnalysisByPaperCode(@Param("paperCode") String paperCode, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    /**
     * 根据paperUserRecordId 查询试卷分析
     * @param paperUserRecordId
     * @return
     */
    @Select({
            "SELECT a.stu_id stuId,a.stu_name stuName,a.end_time endTimeDate,a.answer_paper_time consumedTimeInt,",
            "a.total_score stuScore,b.total_score fullCredit,a.id paperUserRecordId,b.id paperId,b.name paperTitle, b.code paperCode",
            "from ent_paper_user_record a",
            "INNER JOIN t_paper b on b.id = a.paper_id",
            "where a.delete_flag = 0 and b.delete_flag = 0",
            "and a.status_code = 'COMPLETE'",
            "and a.id = #{paperUserRecordId}"
    })
    ResStuPaperAnswerDetailDTO getPaperAnalysisByPaperUserRecordId(@Param("paperUserRecordId") Integer paperUserRecordId);

    /**
     * 查询答题信息
     * @param paperUserQuestionDTO
     * @return
     */
    @Select({
            "<script>",
            "SELECT id,paper_user_record_id paperUserRecordId,question_id questionId,question_sub_id questionSubId,",
            "question_type questionType,answer,score,operator,delete_flag deleteFlag,question_id_source questionIdSource",
            "FROM ent_paper_user_question_${paperUserQuestionDTO.stuId} ",
            "WHERE delete_flag = 0 AND paper_user_record_id = #{paperUserQuestionDTO.paperUserRecordId}", // and question_id_source = 't_question_main'
            "and question_id = #{paperUserQuestionDTO.questionId} ",
            "<if test=\"paperUserQuestionDTO.questionSubId != null\">",
            "and question_sub_id = #{paperUserQuestionDTO.questionSubId}",
            "</if>",
            "</script>"
    })
    ResPaperUserQuestionDTO getPaperUserQuestion(@Param("paperUserQuestionDTO") ReqPaperUserQuestionDTO paperUserQuestionDTO);

    /**
     * 题目配过的试卷信息统计
     * @param paperTypeCodeList
     * @param questionCodeList
     * @return java.util.List<com.sunlands.question.dto.QuestionPaperInfoDTO>
     * @author suntenghao
     * @date 2020-04-07
     */
    @Select({
            "<script>",
            "SELECT pq.question_code questionCode, pc.type paperTypeCode, count(1) amount ",
            "FROM t_paper_code pc ",
            "LEFT JOIN t_paper_question_code_rel pq ON pc.`code`=pq.paper_code ",
            "WHERE pc.invalid_flag=0 AND pc.delete_flag=0 AND pq.delete_flag=0 ",
            "AND pc.type IN ",
            "<foreach collection=\"paperTypeCodeList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "AND pq.question_code IN ",
            "<foreach collection=\"questionCodeList\" item=\"item\" open=\"(\" separator=\",\" close=\")\">",
            "#{item}",
            "</foreach>",
            "GROUP BY pq.question_code,pc.type",
            "</script>"
    })
    List<QuestionPaperInfoDTO> getPaperInfo(@Param("paperTypeCodeList") Collection<String> paperTypeCodeList,
                                            @Param("questionCodeList") Collection<String> questionCodeList);
}
