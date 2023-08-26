package com.colson.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.colson.common.constants.ApiConstants;
import com.colson.common.constants.TeachConstants;
import com.colson.common.emum.OutlineRequirementEnum;
import com.colson.common.emum.SourceTypeEnum;
import com.colson.dal.dao.*;
import com.colson.dal.dto.*;
import com.colson.service.QuestionService;
import com.colson.service.TeachPaperService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hurw on 2017/8/9.
 */
@Slf4j
@Service("questionServiceImpl")
public class QuestionServiceImpl implements QuestionService {
    static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Autowired
    private QuestionMainDao questionMainDao;

    @Autowired
    private QuestionContentFillBlankDao questionContentFillBlankDao;

    @Autowired
    private QuestionContentChoiceOptionDao questionContentChoiceOptionDao;

    @Autowired
    private QuestionContentEssayScorePointDao questionContentEssayScorePointDao;

    @Autowired
    private UploadQuestionDAO uploadQuestionDAO;

    @Autowired
    @Qualifier("teachPaperService")
    private TeachPaperService paperService;

    /**
     * 根据条件查询试题表总表t_question_main
     *
     * @param reqQuestionMainDTO
     * @param start
     * @param pageSize
     * @param needPaperInfo
     * @return
     */
    @Override
    public List<ResQuestionMainDTO> selectByConditions(ReqQuestionMainDTO reqQuestionMainDTO, Integer start, Integer pageSize, boolean needPaperInfo) {
        //排序处理
        int orderFlag = 0;
        List<ReqOrderDTO> reqOrderList = reqQuestionMainDTO.getReqOrderList();
        if (reqOrderList != null) {
            for (ReqOrderDTO reqOrderDTO : reqOrderList) {
                String value = reqOrderDTO.getValue();
                if (StringUtils.isNotBlank(value)) {
                    orderFlag = 1;
                    break;
                }
            }
        }
        reqQuestionMainDTO.setOrderFlag(orderFlag);
        //查询题目
        List<ResQuestionMainDTO> resQuestionMainList = questionMainDao.selectByCondition(reqQuestionMainDTO, start, pageSize);
        //根据考期省份过滤，并填充多考期省份信息
        filteAndSetExamProvinceAndSession(reqQuestionMainDTO.getExamProvince(),
                reqQuestionMainDTO.getExamSession(),
                reqQuestionMainDTO.getExamTag(),
                resQuestionMainList);
        //查询questionContentChoiceOptionList/blankList/childQuestionMainList/questionContentEssayScorePointList
        if (resQuestionMainList != null && resQuestionMainList.size() > 0) {
            Integer invalidFlag = reqQuestionMainDTO.getInvalidFlag();
            Integer currentVersion = reqQuestionMainDTO.getCurrentVersion();
            retrieveOthers(resQuestionMainList, invalidFlag, currentVersion);
            if (needPaperInfo) {
                // 设置试题组卷信息
                paperService.setPaperInfo(resQuestionMainList);
            }
        }
        return resQuestionMainList;
    }

    /**
     * 查询试题时根据考期省份过滤试题，真题多考期省份需求增加
     * @param examProvince
     * @param examSession
     * @param questionMainList
     */
    private void filteAndSetExamProvinceAndSession(Integer examProvince,
                                                   Integer examSession,
                                                   Integer examTag,
                                                   List<ResQuestionMainDTO> questionMainList) {
        if (CollectionUtils.isEmpty(questionMainList)) {
            return;
        }
        //查出来的只有真题和真题的多考期省份
        Map<Integer, ResQuestionMainDTO> questionMap = questionMainDao
                .selectQuestionIdsByExamSessionAndProvince(examProvince == null ? null : Collections.singletonList(examProvince),
                        examSession == null ? null : Collections.singletonList(examSession),
                        examTag == null ? null : Collections.singletonList(examTag),
                        questionMainList.stream().map(ResQuestionMainDTO::getId).collect(Collectors.toList()));
        if (examProvince == null && examSession == null && examTag == null) {
            if (CollectionUtils.isEmpty(questionMap)) {
                return;
            }
            //没有筛选项，需要留下所有题，填充真题的考期省份
            questionMainList.forEach(dto -> {
                Integer questionId = dto.getId();
                ResQuestionMainDTO resQuestionMainDTO = questionMap.get(questionId);
                if (resQuestionMainDTO != null) {
                    dto.setExamSessionAndProvinces(resQuestionMainDTO.getExamSessionAndProvinces());
                }
            });
        } else {
            if (CollectionUtils.isEmpty(questionMap)) {
                questionMainList.clear();
                return;
            }
            //有筛选项，需要移除未筛选到的真题，并填充真题的考期省份
            questionMainList.removeIf(dto -> {
                Integer questionId = dto.getId();
                ResQuestionMainDTO resQuestionMainDTO = questionMap.get(questionId);
                if (resQuestionMainDTO != null) {
                    dto.setExamSessionAndProvinces(resQuestionMainDTO.getExamSessionAndProvinces());
                }
                return resQuestionMainDTO == null;
            });
        }
    }

    /**
     * 查询questionContentChoiceOptionList/blankList/childQuestionMainList
     *
     * @param resQuestionMainList
     * @return
     */
    private String retrieveOthers(List<ResQuestionMainDTO> resQuestionMainList,
                                  Integer invalidFlag, Integer currentVersion) {
        for (ResQuestionMainDTO resQuestionMainDTO : resQuestionMainList) {
            //1、判断论述题/综合题，需要查子childQuestionMainList
            if (ApiConstants.CONTENT_TYPE_ESSAY.equals(resQuestionMainDTO.getContentType()) &&
                    (ApiConstants.QUESTION_TYPE_JUDGE_ESSAY.equals(resQuestionMainDTO.getQuestionType())
                            || ApiConstants.QUESTION_TYPE_READING_COMPREHENSION.equals(resQuestionMainDTO.getQuestionType())  // 新增完形填空
                            || ApiConstants.QUESTION_TYPE_COMPREHENSIVE.equals(resQuestionMainDTO.getQuestionType()))) {
                List<ResQuestionMainDTO> childQuestionMainDList = questionMainDao
                        .selectByParentMainId(resQuestionMainDTO.getId(), invalidFlag, currentVersion);
                resQuestionMainDTO.setChildQuestionMainList(childQuestionMainDList);
                //递归查询子类
                if (childQuestionMainDList != null && childQuestionMainDList.size() > 0) {
                    retrieveOthers(childQuestionMainDList, invalidFlag, currentVersion);
                }
            }
            // 多选多，查询子
            if (ApiConstants.CONTENT_TYPE_CHOICE.equals(resQuestionMainDTO.getContentType()) &&
                    ApiConstants.QUESTION_TYPE_MANY_TO_MANY.equals(resQuestionMainDTO.getQuestionType())) {
                List<ResQuestionMainDTO> childQuestionMainList = questionMainDao
                        .selectByParentMainId(resQuestionMainDTO.getId(), invalidFlag, currentVersion);
                // 递归查询子类
                if (!CollectionUtils.isEmpty(childQuestionMainList)) {
                    resQuestionMainDTO.setChildQuestionMainList(childQuestionMainList);
                    retrieveOthers(childQuestionMainList, invalidFlag, currentVersion);
                }
            }
            //2、填空题，根据第一步结果childQuestionMainList查空集合并置blankList
            if (ApiConstants.QUESTION_TYPE_ORDER_FILL_BLANK.equals(resQuestionMainDTO.getQuestionType()) || ApiConstants.QUESTION_TYPE_DISORDER_FILL_BLANK.equals(resQuestionMainDTO.getQuestionType())) {
                List<ResBlankDTO> resBlankDTOS = questionContentFillBlankDao.selectByQuestionMainId(resQuestionMainDTO.getId());
                resQuestionMainDTO.setBlankList(resBlankDTOS);
            }
            //3、单选/多选/判断题置questionContentChoiceOptionList
            if (ApiConstants.CONTENT_TYPE_CHOICE.equals(resQuestionMainDTO.getContentType())) {
                List<ResQuestionContentChoiceOptionDTO> resQuestionContentChoiceOptionList = questionContentChoiceOptionDao.selectByQuestionId(resQuestionMainDTO.getQuestionId());
                resQuestionMainDTO.setQuestionContentChoiceOptionList(resQuestionContentChoiceOptionList);
            }
            // 4、英文题配置scorePointRule，中文文字题/判断论述题配置scorePointList
            Integer languageType = uploadQuestionDAO.getLanguageTypeByQuestionId(resQuestionMainDTO.getId());
            if (TeachConstants.LANGUAGE_TYPE_ENGLISH.equals(languageType)) {
                resQuestionMainDTO.setQuestionScorePointRule(getQuestionScorePointRule(resQuestionMainDTO.getId(), false));
            } else {
                if (ApiConstants.CONTENT_TYPE_ESSAY.equals(resQuestionMainDTO.getContentType())) {
                    List<ResQuestionContentEssayScorePointDTO> resQuestionContentEssayScorePointList = questionContentEssayScorePointDao.selectByQuestionId(resQuestionMainDTO.getQuestionId());
                    resQuestionMainDTO.setQuestionContentEssayScorePointList(resQuestionContentEssayScorePointList);
                }
            }
            // 5、真题标签
            if (SourceTypeEnum.REAL_QUESTION.getCode().equals(resQuestionMainDTO.getSourceType())) {
                List<ExamLabelDTO> labelDTOList = uploadQuestionDAO.queryLabelListByQuestionMainId(resQuestionMainDTO.getId());
                resQuestionMainDTO.setExamLabelList(labelDTOList);
            } else {
                resQuestionMainDTO.setExamLabelList(Collections.EMPTY_LIST);
            }

            resQuestionMainDTO.setOutlineRequirement(OutlineRequirementEnum.getNameByCode(resQuestionMainDTO.getOutlineRequirement()));
        }
        return null;
    }

    @Override
    public QuestionScorePointRule getQuestionScorePointRule(Integer questionId, boolean isTemp) {
        List<QuestionScorePoint> questionScorePoint = null;
        if (isTemp) {
            questionScorePoint = uploadQuestionDAO.getQuestionScorePointTempByParentId(questionId);
        } else {
            questionScorePoint = uploadQuestionDAO.getQuestionScorePointByParentId(questionId);
        }
        if (!CollectionUtils.isEmpty(questionScorePoint)) {
            List<QuestionScorePointRule.ScorePointGroup> scorePointGroupList = new ArrayList<>(questionScorePoint.size());
            for (QuestionScorePoint scorePoint : questionScorePoint) {
                QuestionScorePointRule.ScorePointGroup scorePointGroup = JSONObject.parseObject(scorePoint.getScorePointGroup(), QuestionScorePointRule.ScorePointGroup.class);
                scorePointGroupList.add(scorePointGroup);
            }
            QuestionScorePointRule rule = new QuestionScorePointRule();
            rule.setType(scorePointGroupList.get(0).getType());
            rule.setScorePointGroupList(scorePointGroupList);
            return rule;
        } else {
            return null;
        }
    }

}
