package com.colson.common.constants;

/**
 * Created by wangzz on 2016/9/29.
 */
public class ApiConstants {

    // json接口数据
    public static final String STATUS_CODE = "statusCode";
    public static final int STATUS_CODE_SUCCESS = 0;
    public static final int STATUS_CODE_FAIL_1 = 1;
    public static final int STATUS_CODE_FAIL_401 = 401;

    public static final String STATUS_DESC = "statusDesc";
    public static final String STATUS_DESC_SUCCESS = "ok";
    public static final String STATUS_DESC_FAIL_401 = "授权失败，请重新登录";
    public static final String STATUS_DESC_FAIL_401_2 = "session失效或授权失败";

    public static final String DATA = "data";
    public static final String DATA_LIST = "dataList";

    public static final String FLAG = "flag";
    public static final String MESSAGE = "message";
    public static final int FLAG_SUCCESS = 1;
    public static final int FLAG_FAIL_0 = 0;
    public static final String MESSAGE_SUCCESS = "ok";

    //格式列表
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String INTEGER_FORMAT = "1";
    public static final String LONG_FORMAT = "2";
    public static final String FLOAT_FORMAT = "3";
    public static final String INTEGER_COMMA_LIST_FORMAT = "11";

    //分页接口
    public static final int DEFAULT_PAGE_SIZE = 10; //默认分页数
    public static final int DEFAULT_PAGE_NO = 1; //默认页码

    //匿名用户
    public static final String DEFAULT_USER_NAME = "guest";

    public static final String UPGARDE_NO_LIMIT_PROJ2ND = "UPGARDE_NO_LIMIT_PROJ2ND";

    //学员考试记录表完成情况-未开始
    public static final String PAPER_USER_RECORD_STATUS_CODE_UNSTART = "UNSTART";
    //学员考试记录表完成情况-未完成
    public static final String PAPER_USER_RECORD_STATUS_CODE_UNCOMPLETE = "UNCOMPLETE";
    //学员考试记录表完成情况-已完成 
    public static final String PAPER_USER_RECORD_STATUS_CODE_COMPLETE = "COMPLETE";

    //试卷类型-模拟考
    public static final String PAPER_TYPE_MODEL_EXAM = "MODEL_EXAM";
    //试卷类型-刷题
    public static final String PAPER_TYPE_COMMON_EXERCISES = "COMMON_EXERCISES";

    //学院答题详情表-截取学员id末位
    public static final int ENT_PAPER_USER_QUESTION_STU_TABLE = 2;

    //update by hurw 20180202 修改缓存存储方式不建议使用哈希set
    //redis存储key
    //试卷-[key:PAPER]  [key:{paperId}]  [value:Paper]
    //update key:EW_PAPER:EW_PAPER_ value:Paper
    public static final String REDIS_KEY_PAPER_ID_OLD = "PAPER";
    public static final String REDIS_KEY_PAPER_ID = "EW_PAPER:EW_PAPER_";

    //试卷题目-[key:PAPER_QUESTION_BY_ID]  [key:{questionId}]  [value:ResPaperQuestionDTO]
    //update key:EW_PAPER_QUESTION_BY_ID:EW_PAPER_QUESTION_BY_ID_
    public static final String REDIS_KEY_PAPER_QUESTION_BY_ID_OLD = "PAPER_QUESTION_BY_ID";
    public static final String REDIS_KEY_PAPER_QUESTION_BY_ID = "EW_PAPER_QUESTION_BY_ID:EW_PAPER_QUESTION_BY_ID_";

    //试卷题目列表-[key:PAPER_QUESTION_BY_PACKAGE_ID]  [key:{paperId}]  [value:List<ResPaperQuestionDTO>]
    //update key:EW_PAPER_QUESTION_BY_PACKAGE_ID:EW_PAPER_QUESTION_BY_PACKAGE_ID_
    public static final String REDIS_KEY_PAPER_QUESTION_BY_PACKAGE_ID_OLD = "PAPER_QUESTION_BY_PACKAGE_ID";
    public static final String REDIS_KEY_PAPER_QUESTION_BY_PACKAGE_ID = "EW_PAPER_QUESTION_BY_PACKAGE_ID:EW_PAPER_QUESTION_BY_PACKAGE_ID_";

    //试卷题目列表-[key:PAPER_QUESTION_AND_ANSWER]  [key:{paperId}_{recordId}]  [value:List<ResPaperQuestionDTO>]
    //update key:EW_PAPER_QUESTION_AND_ANSWER:EW_PAPER_QUESTION_AND_ANSWER_
    public static final String REDIS_KEY_PAPER_QUESTION_AND_ANSWER_OLD = "PAPER_QUESTION_AND_ANSWER";
    public static final String REDIS_KEY_PAPER_QUESTION_AND_ANSWER = "EW_PAPER_QUESTION_AND_ANSWER:EW_PAPER_QUESTION_AND_ANSWER_";

    //学员考试记录-[key:PAPER_USER_RECORD_BY_ID]  [key:{recordId}]  [value:PaperUserRecord]
    //update key:EW_PAPER_USER_RECORD_BY_ID:EW_PAPER_USER_RECORD_BY_ID_
    public static final String REDIS_KEY_PAPER_USER_RECORD_BY_ID_OLD = "PAPER_USER_RECORD_BY_ID";
    public static final String REDIS_KEY_PAPER_USER_RECORD_BY_ID = "EW_PAPER_USER_RECORD_BY_ID:EW_PAPER_USER_RECORD_BY_ID_";

    //学员考试记录-[key:PAPER_USER_RECORD]  [key:{userId}_{paperId}_{paperType}]  [value:ResPaperUserRecordDTO]
    //update key:EW_PAPER_USER_RECORD:EW_PAPER_USER_RECORD_
    public static final String REDIS_KEY_PAPER_USER_RECORD_OLD = "PAPER_USER_RECORD";
    public static final String REDIS_KEY_PAPER_USER_RECORD = "EW_PAPER_USER_RECORD:EW_PAPER_USER_RECORD_";

    //学员答题详情列表-[key:RECORD_{recordId}]  [value:List<PaperUserQuestion>]
    public static final String REDIS_KEY_PAPER_USER_QUESTION = "RECORD_";

    //学员成绩查询考期
    public static final String STUDENT_EXAM_DATE = "201704";

    /**
     * 题型
     */
    public static final String QUESTION_TYPE_SINGLE_CHOICE = "SINGLE_CHOICE";//单选题
    public static final String QUESTION_TYPE_MULTI_CHOICE = "MULTI_CHOICE";//多选题
    public static final String QUESTION_TYPE_JUDGE_CHOICE = "JUDGE_CHOICE";//判断题
    public static final String QUESTION_TYPE_ORDER_FILL_BLANK = "ORDER_FILL_BLANK";//填空题
    public static final String QUESTION_TYPE_DISORDER_FILL_BLANK = "DISORDER_FILL_BLANK";//填空题
    public static final String QUESTION_TYPE_JUDGE_ESSAY = "JUDGE_ESSAY";//判断论述题
    public static final String QUESTION_TYPE_ESSAY = "ESSAY";//文字题
    public static final String QUESTION_TYPE_COMPREHENSIVE = "COMPREHENSIVE";//综合题
    public static final String QUESTION_TYPE_READING_COMPREHENSION = "READING_COMPREHENSION"; //完形填空题
    public static final String QUESTION_TYPE_MANY_TO_MANY = "MANY_TO_MANY"; //多选多
    /**
     * question_id来源
     */
    public static final String CONTENT_TYPE_ESSAY = "ESSAY";
    public static final String CONTENT_TYPE_BLANK = "BLANK";
    public static final String CONTENT_TYPE_CHOICE = "CHOICE";

    /**
     * 文字题、判断论述题-得分点总条数
     */
    public static final int QUESTION_CONTENT_ESSAY_SCORE_POINT_MAX = 25;
    /**
     * 分隔符
     */
    public static final String QUESTION_ANSWER_SEPARATOR = "<![CDATA[,]]>";

    // redis存储key
    // 知识树- [key:KNOWLEDGE_TREE_BY_CONDITION] [key:{knowledgeTreeId}] [value:ResKnowledgeTreeDTO]
    public static final String REDIS_KEY_KNOWLEDGE_TREE_BY_CONDITION = "KNOWLEDGE_TREE_BY_CONDITION";

    public static final String REDIS_KEY_SUBJECTS_OF_KNOWLEDGE_TREES = "SUBJECTS_OF_KNOWLEDGE_TREES";

    public static final int CAS_STATUS_CODE_SUCCESS = 1;

    /**
     * 学员考试记录表
     */
    public static final String PAPER_ID_SOURCE_ENT_PAPER = "ent_paper";
    public static final String PAPER_ID_SOURCE_T_PAPER = "t_paper";

    //判断类型的题是否正确
    public static final Integer CORRECT_FLAG_RIGHT = 1;

    //试卷的来源 APP
    public static final String T_PAPER_USER_RECORD_SOURCE = "APP";

    /**
     * 试卷时长
     */
    public static final Integer EXAM_TOTAL_TIME = 7200;//秒

    public static final String MAIL_INVALID_ADDRESS = "550 ";
    /**
     * 知识点描述字符限制
     */
    public static final String KNOWLEDGE_NODE_DESCRIPTION_MAX_LENGTH = "KNOWLEDGE_NODE_DESCRIPTION_MAX_LENGTH";
    /**
     * 知识点名称字符限制
     */
    public static final String KNOWLEDGE_NODE_NAME_MAX_LENGTH = "KNOWLEDGE_NODE_NAME_MAX_LENGTH";

    public static final long ONE_DAY_MILLISECONDS = 86400000; // 一天的毫秒数

    public static final String[] QuestionOptionOrderArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 最末级的知识点 级别
     */
    public static final Integer REAL_LAST_KNOWLEDGE_NODE_LEVEL = 4;

    /**
     * 是错题
     */
    public static final Integer WRONG_QUESTION_FLAG_TRUE = 1;

    /**
     * 不是错题
     */
    public static final Integer WRONG_QUESTION_FLAG_FALSE = 0;


    /**
     * 是收藏题目
     */
    public static final Integer FAVOR_QUESTION_FLAG_TRUE = 1;

    /**
     * 不是收藏题目
     */
    public static final Integer FAVOR_QUESTION_FLAG_FALSE = 0;

    /**
     * 文字题是否开启AI判分，默认为0不开启
     */
    public static final Integer ESSAY_AI_FLAG_DEFAULT = 0;

    /**
     * 论述题文字题是否开启AI判分，默认为0不开启
     */
    public static final Integer JUDGE_ESSAY_AI_FLAG_DEFAULT = 0;

    public static final String REDIS_KEY_COURSE_TEMPLATE_KNOWLEDGE_TREE = "REDIS_KEY_COURSE_TEMPLATE_KNOWLEDGE_TREE";

    public static final String QUIZ_PAPER = "QUIZ_PAPER";
    public static final String QUIZ_USER_ANSWER = "QUIZ_USER_ANSWER";

    /**
     * 学员每日做题信息统计index和type
     */
    public static final String STU_DAILY_QUESTION_STAT_ES_INDEX = "exercise_es";
    public static final String STU_DAILY_QUESTION_STAT_ES_TYPE = "t_tiku_user_daily_question";
}
