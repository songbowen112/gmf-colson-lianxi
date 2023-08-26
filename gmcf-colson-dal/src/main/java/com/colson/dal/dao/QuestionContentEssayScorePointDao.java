package com.colson.dal.dao;

import com.colson.dal.dto.QuestionContentEssayScorePoint;
import com.colson.dal.dto.ResQuestionContentEssayScorePointDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * add by chenqiuxia on 2017/10/23
 */
@Repository
public interface QuestionContentEssayScorePointDao {
    @Insert({
            "<script>",
            "INSERT INTO t_question_content_essay_score_point(essay_id,content,score,creator,operator) VALUES ",
            "<foreach collection=\"scorePointList\" item=\"item\" separator=\",\">  ",
            "(#{item.essayId},#{item.content},#{item.score},#{item.creator},#{item.operator})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("scorePointList") List<QuestionContentEssayScorePoint> scorePointList);

    @Select({
            "SELECT content,score from t_question_content_essay_score_point where essay_id = #{questionMainId} and delete_flag = 0"
    })
    List<ResQuestionContentEssayScorePointDTO> selectByQuestionId(@Param("questionMainId") Integer questionMainId);
}
