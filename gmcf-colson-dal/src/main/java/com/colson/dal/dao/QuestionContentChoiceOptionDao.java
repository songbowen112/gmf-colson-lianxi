package com.colson.dal.dao;

import com.colson.dal.dto.QuestionContentChoiceOption;
import com.colson.dal.dto.ResQuestionContentChoiceOptionDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by root on 2017/8/14.
 */
@Repository
public interface QuestionContentChoiceOptionDao {

    /**
     * 批量新增
     * @param questionContentChoiceOptionList
     * @return
     */
    @Insert({
            "<script>",
            " INSERT INTO `t_question_content_choice_option` (`question_id`, `sequence`, `option_title`, `content`, ",
            " `is_correct`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag`) VALUES ",
            "<foreach collection=\"questionContentChoiceOptionList\" item=\"item\"  separator=\",\">",
            " (#{item.questionId,jdbcType=INTEGER},#{item.sequence,jdbcType=INTEGER},#{item.optionTitle,jdbcType=VARCHAR},#{item.content,jdbcType=VARCHAR}, ",
            " #{item.isCorrect,jdbcType=INTEGER},now(),#{item.creator,jdbcType=VARCHAR},now(),#{item.operator,jdbcType=VARCHAR},0) ",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("questionContentChoiceOptionList") List<QuestionContentChoiceOption> questionContentChoiceOptionList);

    /**
     * 根据试题id查询选择题选项集合
     * @param questionId
     * @return
     */
    @Select({
            " select `id`, `question_id`, `sequence`, `option_title`, `content`, `is_correct`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag` ",
            " from `t_question_content_choice_option` a ",
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
    List<ResQuestionContentChoiceOptionDTO> selectByQuestionId(Integer questionId);

    /**
     * 根据试题id批量查询选择题选项集合
     * @param questionIds
     * @return
     */
    @Select({
            "<script>",
            " select `id`, `question_id`, `sequence`, `option_title`, `content`, `is_correct`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag` ",
            " from `t_question_content_choice_option` a ",
            " where a.question_id IN ",
            "<foreach item=\"item\" index=\"index\" collection=\"questionIds\"  open=\"(\" separator=\",\" close=\")\"  >",
            "#{item}",
            "</foreach>",
            "and a.delete_flag=0 ",
            "</script>"
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
    List<ResQuestionContentChoiceOptionDTO> selectAllByQuestionId(@Param("questionIds") List<Integer> questionIds);
}
