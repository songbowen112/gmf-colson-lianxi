package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/***
 * @Description:判分规则DTO
 * @author suntenghao
 * @date 2018-09-10 17:56
 */
@Data
public class QuestionScorePointRule {

    //题目类型:简答 shortAnswer, 中译英 chinese2english, 英译中 english2chinese

    public static final String QUESTION_TYPE_SHORT_ANSWER = "shortAnswer";
    public static final String QUESTION_TYPE_CHINESE_ENGLISH = "chinese2english";
    public static final String QUESTION_TYPE_ENGLISH_CHINESE = "english2chinese";

    //判分方式：strict 严格匹配，semantics 语义匹配

    public static final String JUDGE_TYPE_STRICT = "strict";
    public static final String JUDGE_TYPE_SEMANTICS = "semantics";

    /**
     * 题目类型
     */
    private String type;
    /**
     * 得分点列表
     */
    private List<ScorePointGroup> scorePointGroupList;

    @Data
    public static class ScorePointGroup {
        /**
         * 题目类型
         */
        private String type;
        /**
         * 得分点列表
         */
        private List<ScorePoint> scorePointList;
        /**
         * 是否开启语法检查
         */
        private Boolean syntaxCheck;
        /**
         * 语法检查错误规则列表
         */
        private List<SyntaxErrorRule> syntaxErrorRuleList;
        /**
         * 每个小题（填空题的空）的 questionMainId
         */
        private Integer questionId;
        /**
         * 每个小题（填空题的空）的 parentId，其他情况是 questionMainId
         */
        private Integer questionParentId;
        /**
         * 题目总分
         */
        private BigDecimal score;
    }

    @Data
    public static class ScorePoint {
        /**
         * 判分参考列表
         */
        private List<Reference> referenceList;
        /**
         * 得分点总分
         */
        private BigDecimal score;
    }

    @Data
    public static class Reference {
        /**
         * 参考答案
         */
        private String answer;
        /**
         * 判分方式
         */
        private String type;
    }

    @Data
    public static class SyntaxErrorRule {
        /**
         * 区间
         */
        private Integer min;
        private Integer max;
        /**
         * 扣分值
         */
        private BigDecimal deductScore;
    }
}
