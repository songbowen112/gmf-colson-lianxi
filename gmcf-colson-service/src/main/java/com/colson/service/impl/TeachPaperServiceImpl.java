package com.colson.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.colson.common.emum.PaperTypeEnum;
import com.colson.common.emum.QuestionTypeEnum;
import com.colson.dal.dao.PaperAnalysisDAO;
import com.colson.dal.dao.QuestionMainDao;
import com.colson.dal.dao.TeachPaperManageDAO;
import com.colson.dal.dto.*;
import com.colson.dal.dto.PaperDetailDTO;
import com.colson.service.QuestionService;
import com.colson.service.TeachPaperService;
import com.colson.service.dto.*;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.alibaba.fastjson.JSON.parseArray;


/**
 * Created by Administrator on 2017/8/8.
 */
@Slf4j
@Service("teachPaperService")
public class TeachPaperServiceImpl implements TeachPaperService {
    private static final Logger logger = LoggerFactory.getLogger(TeachPaperServiceImpl.class);

    @Autowired
    private TeachPaperManageDAO paperManageDAO;
    @Autowired
    private PaperAnalysisDAO paperAnalysisDAO;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PaperDetailDTO getPaperByPaperCode(String paperCode) {
        TeachPaperDetail paper = paperManageDAO.selectPaperCodeAndSubjectNameByCode(paperCode);
        if (paper == null) {
            throw new IllegalArgumentException("试卷不存在！");
        }
//        String paperContentKey = TeachConstants.REDIS_KEY_PAPER_CONTENT_DOWNLOAD + paperCode;
//        String pageContentStr = RedisCacheUtil.getStrUnzip(paperContentKey);
//        if (pageContentStr != null) {
            //装填实时试题被使用数据
//            PaperDetailDTO paperDetailDTO = JSONObject.parseObject(pageContentStr, PaperDetailDTO.class);
//            if (!CollectionUtils.isEmpty(paperDetailDTO.getQuestionGroup())) {
//                for (QuestionGroupDTO questionGroupDTO : paperDetailDTO.getQuestionGroup()) {
//                    setPaperInfo(questionGroupDTO.getQuestionsList());
//                }
//            }
//            return paperDetailDTO;
//        }
        //获取试卷所有关联的题,包括小题-- 分数
        List<QuestionMain> questionList = paperManageDAO.listRelatedQuestionByPaperCode(paperCode);
        // 题目分类--已生成试卷的预览，取库中有序数据
        List<QuestionGroupDTO> questionGroupMap = getQuestionGroupBeanByPaperId(paperCode, questionList, true);
        PaperDetailDTO paperDetailDTO = new PaperDetailDTO();
        paperDetailDTO.setPaperCode(paperCode);
        paperDetailDTO.setKnowledgeTreeId(paper.getKnowledgeTreeId());
        paperDetailDTO.setPaperName(paper.getName());
        paperDetailDTO.setPaperHeaders(getPaperHeaderByPaperId(paperCode));
        paperDetailDTO.setQuestionGroup(questionGroupMap);
        paperDetailDTO.setSubjectName(paper.getSubjectName());
        //下载试卷内容缓存六小时
//        RedisCacheUtil.setAndGzip(paperContentKey, JSON.toJSONString(paperDetailDTO),6 * 60 * 60, GzipUtil.strIsGzip);
        return paperDetailDTO;
    }

    @Override
    public void setPaperInfo(List<ResQuestionMainDTO> questionContents) {
        if (CollectionUtils.isEmpty(questionContents)) {
            return;
        }
        questionContents.forEach(dto -> dto.setPaperInfo(new ArrayList<>()));
        Map<String, ResQuestionMainDTO> questionMap = questionContents.stream().collect(Collectors.toMap(ResQuestionMainDTO::getCode, dto -> dto));
        List<String> paperCodeList = Lists.newArrayList("QUIZ", "ASSIGNMENTS", "COMMON_EXERCISE", "MOCK_EXAM", "MODEL_EXAM",
                "REAL_EXAM", "GUESS_SECRET_PAPER", "HIGH_FREQUENCY_TIKU", "ERROR_PRONE_100", "PRE_EXAM_MODEL");
        List<QuestionPaperInfoDTO> paperInfoList = paperAnalysisDAO.getPaperInfo(paperCodeList, questionMap.keySet());
        for (QuestionPaperInfoDTO info : paperInfoList) {
            info.setPaperTypeName(PaperTypeEnum.getNameByCode(info.getPaperTypeCode()));
            ResQuestionMainDTO resQuestionMainDTO = questionMap.get(info.getQuestionCode());
            resQuestionMainDTO.getPaperInfo().add(info);
        }
    }

    @Override
    public List<PaperInfoDTO> getPaperListByCondition(PaperSearchingDTO condition) {
//        validateDifficultyValue(condition);//校验搜索条件的合法性
        log.info("getPaperListByCondition param= {}", condition.toString());
        List<PaperInfoDTO> paperInfoList = paperManageDAO.getPaperListByCondition(condition);
        return paperInfoList;
    }

    @Override
    public List<PaperInfoDTO> getPaperListByPaperCodes(List<String> paperCodes) {
        List<PaperInfoDTO> paperInfoList = paperManageDAO.getPaperListByPaperCodes(paperCodes);
        return paperInfoList;
    }

    @Override
    public Integer getPaperListCountByCondition(PaperSearchingDTO condition) {
        log.info("getPaperListCountByCondition param= {}", condition.toString());
        return paperManageDAO.getPaperListCountByCondition(condition);
    }

    /**
     * 获取试卷头
     * @param paperCode  试卷code
     * @return 试卷头列表
     */
    private List<PaperHeader> getPaperHeaderByPaperId(String paperCode) {
        return paperManageDAO.listPaperHeaderByPaperCode(paperCode);
    }

    private List<QuestionGroupDTO> getQuestionGroupBeanByPaperId(String paperCode, List<QuestionMain> questionList, boolean resetScore) {
        // 按类型分组
        Map<String, JSONObject> questionGroupMap = retrieveQuestionGroupMap(paperCode);
        return getQuestionGroupBeanDetail(questionGroupMap, questionList, resetScore);
    }

    /**
     * 根据试题分组信息获取试题详情
     * @param questionGroupMap
     * @param questionList
     * @param resetScore
     * @return
     */
    private List<QuestionGroupDTO> getQuestionGroupBeanDetail(Map<String, JSONObject> questionGroupMap, List<QuestionMain> questionList, boolean resetScore) {
        //针对每一种类型，批量获取题目具体内容
        Integer orderNum = 1;
        List<QuestionGroupDTO> questionGroupList = new ArrayList<>();
        for (Map.Entry<String, JSONObject> entry : questionGroupMap.entrySet()) {
            JSONObject questionGroup = entry.getValue();
            JSONArray questionIds = questionGroup.getJSONArray("questionsList");
            //调用公共方法获取id对应的试题内容， 重新put到questionGroup 中
            // updateby chenqiuxia 分次拿出费时太长，改回批量查询，至于排序问题，后操作
            List<ResQuestionMainDTO> questionContents = getQuestionContent(questionIds); //待处理
            //设置已配置过的试卷数据 (2020/4/3 sth)
            setPaperInfo(questionContents);
            Map<Integer, ResQuestionMainDTO> questionContentMap = new HashMap<>();
            if (questionContents != null && questionContents.size() != 0) {
                for (ResQuestionMainDTO questionMainDTO : questionContents) {
                    questionContentMap.put(questionMainDTO.getId(), questionMainDTO);
                }
            }
            List<Integer> questionIdList = JSONArray.parseArray(questionIds.toJSONString(), Integer.class);
            List<ResQuestionMainDTO> newQuestionContents = new ArrayList<>();
            for (Integer tempQuestionMainId : questionIdList) {
                ResQuestionMainDTO resQuestionMainDTO = questionContentMap.get(tempQuestionMainId);
                if (resQuestionMainDTO != null) {
                    convertContent(resQuestionMainDTO);
                    if (!CollectionUtils.isEmpty(resQuestionMainDTO.getChildQuestionMainList())) {
                        for (ResQuestionMainDTO questionMainDTO : resQuestionMainDTO.getChildQuestionMainList()) {
                            convertContent(questionMainDTO);
                            if (!CollectionUtils.isEmpty(questionMainDTO.getChildQuestionMainList())) {
                                for (ResQuestionMainDTO questionMain : questionMainDTO.getChildQuestionMainList()) {
                                    convertContent(questionMain);
                                }
                            }
                        }
                    }
                    resQuestionMainDTO.setOrderNum(orderNum++);
                    newQuestionContents.add(resQuestionMainDTO);
                }
            }

            //重新赋分数
            if (resetScore) {
                resetQuestionScore(newQuestionContents, questionList);
            }
            questionGroup.put("questionsList", newQuestionContents);
            QuestionGroupDTO questionGroupDTO = questionGroup.toJavaObject(QuestionGroupDTO.class);
            questionGroupList.add(questionGroupDTO);
        }
        return questionGroupList;
    }

    /**
     * 获取试题内容
     * @param questionIds 试题id
     * @return 试题内容列表
     */
    private List<ResQuestionMainDTO> getQuestionContent(JSONArray questionIds) {
        ReqQuestionMainDTO mainDTO = new ReqQuestionMainDTO();
        mainDTO.setIdList(parseArray(questionIds.toJSONString(), Integer.class));
        mainDTO.setInvalidFlag(2);
        mainDTO.setCurrentVersion(2);
        return questionService.selectByConditions(mainDTO, null, null, false);
    }

    /**
     * 试卷内试题已有序的前提下，不再重排，直接按序号取数据
     * @param paperCode
     * @return
     */
    private Map<String, JSONObject> retrieveQuestionGroupMap(String paperCode) {
        // sequence大小取得试题类型排序结果
        List<String> questionTypeList = paperManageDAO.selectQuestionTypeInorder(paperCode);
        Assert.notNull(questionTypeList);
        Map<String, JSONObject> questionGroupMap = new LinkedHashMap<>();
        for (String questionType : questionTypeList) {
            // 根据题型按排序结果查询试题
            List<QuestionMain> questionList = paperManageDAO.getQuestionsByType(paperCode, questionType);
            //有序填空、无序填空的都属填空类型
            if(isFillBlankQuestion(questionType)) {
                questionType = QuestionTypeEnum.FILL_BLANK.getCode();
            }
            JSONObject questionGroup = questionGroupMap.get(questionType) == null
                    ? new JSONObject() : questionGroupMap.get(questionType);
            JSONArray questionListByGroup = questionGroup.getJSONArray("questionsList") == null
                    ? new JSONArray() : questionGroup.getJSONArray("questionsList");
            BigDecimal totalScore = questionGroup.get("groupTotalScore") == null
                    ? new BigDecimal(0) : (BigDecimal) questionGroup.get("groupTotalScore");

            for (QuestionMain questionMain : questionList) {
                questionListByGroup.add(questionMain.getId());
                totalScore = questionMain.getScore().add(totalScore);
            }
            questionGroup.put("questionAmount", questionListByGroup.size());
            questionGroup.put("groupTotalScore", totalScore);
            if (questionGroup.get("questionType") == null) {
                JSONObject type = new JSONObject();
                type.put("itemCode", questionType);
                type.put("itemValue", QuestionTypeEnum.getNameByCode(questionType));
                questionGroup.put("questionType", type);
            }
            questionGroup.put("questionsList", questionListByGroup);
            questionGroupMap.put(questionType, questionGroup);
        }
        return questionGroupMap;
    }

    private boolean isFillBlankQuestion(String questionType){
        return QuestionTypeEnum.ORDER_FILL_BLANK.getCode().equals(questionType)
                || QuestionTypeEnum.DISORDER_FILL_BLANK.getCode().equals(questionType);
    }

    /**
     * 将content内部含有html标签的样式去掉
     *
     * @param content
     */
    private String resetContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        // 屏蔽微软控制字符
        // https://www.utf8-chartable.de/unicode-utf8-table.pl?utf8=0x
        String con = content.replaceAll("[\u0000-\u001f\u007f-\u009f]", "");
        return con.replaceAll("(style=\")[\\s\\S]*?(\")", "");

    }

    /**
     * 将content内部含有img标签的下划线样式去掉
     *
     * @param content
     */
    private String resetBlankImgContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        return content.replaceAll("<img [\\s\\S]*?class=\"__blank__placeholder\"[\\s\\S]*?>", "＿");
    }

    /**
     * 将content内部含有br标签的样式去掉
     *
     * @param content
     */
    private String resetBrContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        return content.replaceAll("<br[\\s\\S]*?>", "");
    }

    /**
     * 将content内部含有br标签的样式去掉
     *
     * @param content
     */
    private String resetPContent(String content) {
        if (StringUtils.isEmpty(content)) {
            return "";
        }
        return content.replaceAll("<p[\\s\\S]*?>", "").replaceAll("</p>", "");
    }

    private void convertContent(ResQuestionMainDTO resQuestionMainDTO) {
        resQuestionMainDTO.setAnalysis(resetContent(resQuestionMainDTO.getAnalysis()));
        resQuestionMainDTO.setAnalysis(resetPContent(resQuestionMainDTO.getAnalysis()));
        resQuestionMainDTO.setAnalysis(resetBrContent(resQuestionMainDTO.getAnalysis()));
        resQuestionMainDTO.setAnalysis(resetBlankImgContent(resQuestionMainDTO.getAnalysis()));

        resQuestionMainDTO.setCorrectAnswer(resetContent(resQuestionMainDTO.getCorrectAnswer()));
        resQuestionMainDTO.setCorrectAnswer(resetPContent(resQuestionMainDTO.getCorrectAnswer()));
        resQuestionMainDTO.setCorrectAnswer(resetBrContent(resQuestionMainDTO.getCorrectAnswer()));
        resQuestionMainDTO.setCorrectAnswer(resetBlankImgContent(resQuestionMainDTO.getCorrectAnswer()));

        resQuestionMainDTO.setContent(resetContent(resQuestionMainDTO.getContent()));
        resQuestionMainDTO.setContent(resetPContent(resQuestionMainDTO.getContent()));
        resQuestionMainDTO.setContent(resetBrContent(resQuestionMainDTO.getContent()));
        resQuestionMainDTO.setContent(resetBlankImgContent(resQuestionMainDTO.getContent()));

        if (!CollectionUtils.isEmpty(resQuestionMainDTO.getQuestionContentChoiceOptionList())) {
            for (ResQuestionContentChoiceOptionDTO option : resQuestionMainDTO.getQuestionContentChoiceOptionList()) {
                option.setContent(resetContent(option.getContent()));
                option.setContent(resetPContent(option.getContent()));
                option.setContent(resetBrContent(option.getContent()));
            }
        }
        //完形填空or多选多
        if (QuestionTypeEnum.READING_COMPREHENSION.getCode().equals(resQuestionMainDTO.getQuestionType())
                || QuestionTypeEnum.MANY_TO_MANY.getCode().equals(resQuestionMainDTO.getQuestionType())) {
            String content = resQuestionMainDTO.getContent();
            if (StringUtils.isNotEmpty(content)) {
                Pattern pattern = Pattern.compile("＿");
                Matcher matcher = pattern.matcher(content);
                int index = 0;
                while (matcher.find()) {
                    index++;
                    content = content.replaceFirst("＿", "__" + index + "__");
                }
                resQuestionMainDTO.setContent(content);
            }
        }
        //填空题
        if (QuestionTypeEnum.FILL_BLANK.getCode().equals(resQuestionMainDTO.getQuestionType())
                || QuestionTypeEnum.ORDER_FILL_BLANK.getCode().equals(resQuestionMainDTO.getQuestionType())
                || QuestionTypeEnum.DISORDER_FILL_BLANK.getCode().equals(resQuestionMainDTO.getQuestionType())) {
            String content = resQuestionMainDTO.getContent();
            if (StringUtils.isNotEmpty(content)) {
                Pattern pattern = Pattern.compile("＿");
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    content = content.replaceFirst("＿", "__________________");
                }
                resQuestionMainDTO.setContent(content);
            }

        }
    }

    /**
     * 根据试卷试题关系中保存的分数给题目重新设置分数
     * @param questionContents 试题内容
     * @param questionList 试卷中的试题列表
     */
    private void resetQuestionScore(List<ResQuestionMainDTO> questionContents, List<QuestionMain> questionList) {
        if (CollectionUtils.isEmpty(questionContents) || CollectionUtils.isEmpty(questionList)) {
            return;
        }
        for (ResQuestionMainDTO questionContent : questionContents) {
            if (questionContent.getChildQuestionMainList() != null) {
                resetQuestionScore(questionContent.getChildQuestionMainList(), questionList);
            }
            if (!CollectionUtils.isEmpty(questionContent.getBlankList())) {
                for (ResBlankDTO childQuestion : questionContent.getBlankList()) {
                    setRealScoreForFillBlank(childQuestion, questionList);
                }
            }
            setRealScore(questionContent, questionList);
        }
    }

    /**
     * 填空题重新设置分数
     * @param childQuestion 子题目
     * @param questionList 试卷中的试题列表
     */
    private void setRealScoreForFillBlank(ResBlankDTO childQuestion, List<QuestionMain> questionList) {
        for (QuestionMain questionMain : questionList) {
            if (childQuestion.getQuestionMainId().equals(questionMain.getId())) {
                childQuestion.setScore(questionMain.getScore());
            }
        }
    }

    /**
     * 文字题&判断论述题 - 重置得分点分数 - 按原比例变更（保留两位小数，向下截断，如5/3取1.66）
     * @param questionMain
     * @param questionContent
     */
    private void setRealScoreForScorePoint(QuestionMain questionMain, ResQuestionMainDTO questionContent) {
        float tempOldScore = questionContent.getScore().floatValue();
        Float tempNewScore = questionMain.getScore().floatValue();
        if (!tempNewScore.equals(tempOldScore)&& !CollectionUtils.isEmpty(questionContent.getQuestionContentEssayScorePointList())) {
            List<ResQuestionContentEssayScorePointDTO> scorePointList = questionContent.getQuestionContentEssayScorePointList();
            for (int i = 0,size = scorePointList.size();i<size;i++){
                ResQuestionContentEssayScorePointDTO scorePointDTO = scorePointList.get(i);
                BigDecimal score = scorePointDTO.getScore();
                if (score != null && i != size - 1) {
                    float itemScorePoint = score.floatValue();
                    float newItemScorePoint = tempNewScore * itemScorePoint / tempOldScore;
                    //先扩大两倍再四舍五入，然后缩小到1/2确保一定是0.5的倍数(AI判分要求）
                    //update 2020/10/15 取消0.5倍数限制，改为0.1倍数限制
                    newItemScorePoint = Math.round(newItemScorePoint * 10) / 10f;
                    //动态确定缩放系数，使结果更均匀
                    tempOldScore -= itemScorePoint;
                    tempNewScore -= newItemScorePoint;
                    scorePointDTO.setScore(new BigDecimal(newItemScorePoint).setScale(1,BigDecimal.ROUND_HALF_UP));
                } else if (score != null) {
                    //最后一个得分点分配剩余的所有分值
                    float newItemScorePoint = tempNewScore;
                    scorePointDTO.setScore(new BigDecimal(newItemScorePoint).setScale(1,BigDecimal.ROUND_HALF_UP));
                }
            }
        }
        //设置采分组新分
        tempOldScore = questionContent.getScore().floatValue();
        tempNewScore = questionMain.getScore().floatValue();
        //拼写扣分放缩系数
        float scale = tempNewScore / tempOldScore;
        QuestionScorePointRule questionScorePointRule = questionContent.getQuestionScorePointRule();
        if (questionScorePointRule != null) {
            List<QuestionScorePointRule.ScorePointGroup> scorePointList = questionScorePointRule.getScorePointGroupList();
            List<ResBlankDTO> blankDTOList = questionContent.getBlankList();
            for (int i = 0,size = scorePointList.size();i<size;i++) {
                QuestionScorePointRule.ScorePointGroup scorePointGroup = scorePointList.get(i);
                BigDecimal score = scorePointGroup.getScore();
                if (score == null) {
                    continue;
                }
                //设置采分组的得分点新分
                List<QuestionScorePointRule.ScorePoint> scorePoints = scorePointGroup.getScorePointList();
                float tempOldPointScore = score.floatValue();
                float tempNewPointScore;
                if (!CollectionUtils.isEmpty(blankDTOList)) {
                    //填空题将每个空的分设置为采分组新总分
                    tempNewPointScore = blankDTOList.get(i).getScore().floatValue();
                    scorePointGroup.setScore(blankDTOList.get(i).getScore());
                } else {
                    //文字题只有一个采分组，将题目总分设置为采分组新总分
                    tempNewPointScore = tempNewScore;
                    scorePointGroup.setScore(new BigDecimal(tempNewScore));
                }
                for (int j = 0, size_j =scorePoints.size(); j < size_j; j++) {
                    QuestionScorePointRule.ScorePoint scorePoint = scorePoints.get(j);
                    BigDecimal pointScore = scorePoint.getScore();
                    if (pointScore != null && j != size_j - 1) {
                        float itemScorePoint = pointScore.floatValue();
                        float newItemScorePoint = tempNewPointScore * itemScorePoint / tempOldPointScore;
                        //先扩大两倍再四舍五入，然后缩小到1/2确保一定是0.5的倍数(AI判分要求）
                        //update 2020/10/15 取消0.5倍数限制，改为0.1倍数限制
                        newItemScorePoint = (float) (Math.round(newItemScorePoint * 10) / 10f);
                        //动态确定缩放系数，使结果更均匀
                        tempOldPointScore -= itemScorePoint;
                        tempNewPointScore -= newItemScorePoint;
                        scorePoint.setScore(new BigDecimal(newItemScorePoint).setScale(1,BigDecimal.ROUND_HALF_UP));
                    } else if (pointScore != null) {
                        //最后一个得分点分配剩余的所有分值
                        scorePoint.setScore(new BigDecimal(tempNewPointScore).setScale(1,BigDecimal.ROUND_HALF_UP));
                    }
                }
                //设置拼写扣分
                for (QuestionScorePointRule.SyntaxErrorRule syntaxerrorrule : scorePointGroup.getSyntaxErrorRuleList()) {
                    syntaxerrorrule.setDeductScore(syntaxerrorrule.getDeductScore().multiply(new BigDecimal(scale)));
                }
            }
        }
    }

    /**
     * 给试题设置上在试卷中设置的分数；如果有得分点，根据规则-设置得分点分值。
     * @param questionContent 题目内容
     * @param questionList 试卷中的试题列表
     */
    private void setRealScore(ResQuestionMainDTO questionContent, List<QuestionMain> questionList) {
        for (QuestionMain questionMain : questionList) {
            if (questionContent.getId().equals(questionMain.getId())) { // TODO: 2017/11/21 关于questionId与id的区分
                // 设置得分点分值
                if (!CollectionUtils.isEmpty(questionContent.getQuestionContentEssayScorePointList()) || questionContent.getQuestionScorePointRule() != null) {
                    setRealScoreForScorePoint(questionMain, questionContent);
                }
                // 设置试题总分
                questionContent.setScore(questionMain.getScore());
            }
        }
    }
}
