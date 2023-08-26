package com.colson.service;


import com.colson.dal.dto.QuestionScorePointRule;
import com.colson.dal.dto.ReqQuestionMainDTO;
import com.colson.dal.dto.ResQuestionMainDTO;

import java.util.List;

/**
 * Created by hurw on 2017/8/9.
 */
public interface QuestionService {

    /**
     * 根据条件查询试题表总表t_question_main
     * @param reqQuestionMainDTO
     * @param needPaperInfo
     * @return
     */
    List<ResQuestionMainDTO> selectByConditions(ReqQuestionMainDTO reqQuestionMainDTO, Integer start, Integer pageSize, boolean needPaperInfo);

    /**
     * @Description:获取判分点 区分temp表
     * @author suntenghao
     * @date 2018-09-11 15:03
     */
    QuestionScorePointRule getQuestionScorePointRule(Integer questionId, boolean isTemp);

}
