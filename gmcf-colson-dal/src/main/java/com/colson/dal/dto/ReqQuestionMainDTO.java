package com.colson.dal.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hurw on 2017/8/8.
 * 试题表总表
 */
@Data
public class ReqQuestionMainDTO {
    private Integer id;
    private String questionStatus;                                                     //试题状态：未提交审核NOT_SUBMIT 不合格UNQUALIFIED 待审核NOT_AUDIT
    private Integer subjectId;
    private List<Integer> idList;                                                       //用于批量操作
    private String code;    //编号
    private String sourceType;                                                        //类型（真题REAL_QUESTION、模拟题MOCK_QUESTION、教材题MATERIAL_QUESTION）
    private String questionType;                                                        //题型：单选题RADIO_QUESTION、多选题MULTIPLE_QUESTION、填空题COMPLETION_QUESITON、判断题JUDGMENT_QUESTION、判断论述题JUDGMENT_DISCOURSE_QUESTION、文字题WORD_QUESTION、综合题COMPREHENSIVE_QUESTION
    private String contentType;                                                         //question_id来源，ESSAY取t_question_content_essay表，BLANK取t_question_fill_black表，CHOICE取t_question_content_choice表
    private BigDecimal score;                                                           //总分
    private Integer knowledgeTreeId;                                                    //所属知识树
    private Integer mainNodeId;                                                        //主知识点
    private Integer viceNodeId1;                                                        //副知识点1
    private Integer viceNodeId2;                                                        //副知识点2
    private Integer difficultyValue;                                                    //难度
    private Integer examProvince;                                                        //考试省份（真题）
    private String examProvinceName;                                                   //考试省份名称（真题）
    private Integer examSession;                                                        //考期（真题）
    private String examSessionName;                                                    //考期名称（真题）  // integer --> string  audit by chenqiuxia 2018/5/25
    private Integer examTag;                                                            // AB卷TAG ID
    private String examTagName;                                                         // AB卷TAG NAME
    /**
     * 「真题」标签列表
     */
    private List<ExamLabelDTO> examLabelList;
    private String content;                                                            //题干内容（文字题）
    private String correctAnswer;                                                    //正确答案（文字题）
    private String analysis;                                                            //解析（文字题）
    private Integer analysisUseful;                                                  //解析有用
    private Integer analysisUseless;                                                 //解析无用
    private List<ReqBlankDTO> blankList;                                            //(填空题)
    private List<ReqOptionDTO> questionContentChoiceOptionList;                                        //（选择题）
    private List<ReqScorePointDTO> questionContentEssayScorePointList;                                     // （文字题、判断论述题）得分点
    private String parentQuestion;                                                    //父题目，若没有为0，若有为1，以下参数不能为空
    private List<ReqQuestionMainDTO> childQuestionMainList;                             //综合题小题
    private List<KnowledgeNode> reqKnowledgeNodeList;                                   //知识点ID列表
    private Integer questionId;                                                          //试题id
    private Integer parentQuestionId;                                                    //父题目id
    private String outlineRequirement;                                                  //大纲要求
    private String beginTime;                                                             //开始时间
    private String endTime;                                                               //结束时间
    private String condition;                                                           //题干/知识点关键字/ID
    private String conditionCode;                                                       //搜索字段标识
    private List<ReqOrderDTO> reqOrderList;                                             //排序
    private Integer invalidFlag;                                                        //是否禁用
    private Integer orderFlag;                                                          //是否排序
    private Integer currentVersion;                                                     //是否当前版本
    private Integer sequence;
    private Integer correctRateMin;                                                      //正确率下阈值
    private Integer correctRateMax;                                                      //正确率上阈值
    private QuestionScorePointRule questionScorePointRule;                                      //判分规则
    private List<String> excludePaperType;                                              //排除的试卷类型


    // ----- 自定义属性 -----
    // 用于新增试题后，向调用方传递主键
    private Integer newQuestionMainId;

    public List<ExamLabelDTO> getExamLabelList() {
        if (!CollectionUtils.isEmpty(examLabelList)) {
            examLabelList.forEach(label -> {
                if (label.getExamTag() == null) {
                    label.setExamTag(0);
                }
                if (label.getExamTagName() == null) {
                    label.setExamTagName("");
                }
            });
        }
        return examLabelList;
    }

    public void setExamTag(Integer examTag) {
        if (examTag != null && examTag <= 0) {
            examTag = null;
        }
        this.examTag = examTag;
    }
}
