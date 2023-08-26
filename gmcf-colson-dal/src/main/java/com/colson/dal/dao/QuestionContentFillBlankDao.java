package com.colson.dal.dao;

import com.colson.dal.dto.QuestionContentFillBlank;
import com.colson.dal.dto.ResBlankDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hurw on 2017/8/15.
 */
@Repository
public interface QuestionContentFillBlankDao {

    @Insert({
            "INSERT INTO `t_question_content_fill_blank` (`sequence`, `score`, `answer`, `create_time`, `creator`, `update_time`, `operator`, `delete_flag`) ",
            " VALUES (#{sequence,jdbcType=INTEGER},#{score,jdbcType=DECIMAL},#{answer,jdbcType=VARCHAR},now(),#{creator,jdbcType=VARCHAR},now(),#{operator,jdbcType=VARCHAR},0) "
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID()")
    int insert(QuestionContentFillBlank questionContentFillBlank);

    /**
     * 根据父试题ID查找子填空题内容
     * @param questionMainId
     * @return
     */
    @Select({
            " select b.id as questionMainId, b.code, a.`id`, a.`sequence`, a.`score`, a.`answer`, a.`create_time`, a.`creator`, a.`update_time` as updateTime, a.`operator`, a.`delete_flag` as deleteFlag ",
            " from t_question_content_fill_blank a,t_question_main b ",
            " where b.question_id=a.id and b.parent_question_id=#{questionMainId,jdbcType=INTEGER} ",
            " and b.delete_flag=0 and a.delete_flag=0 "
    })
    List<ResBlankDTO> selectByQuestionMainId(Integer questionMainId);
}
