package com.colson.common.constants;



import com.colson.common.emum.BufferPoolQuestionStatusEnum;
import com.colson.common.emum.QuestionHistoryBehaviorEnum;
import com.colson.common.utils.MD5ForUploadPic;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 教研工作台公共常量
 * Created by wangzz on 2017/8/10.
 */
public final class TeachConstants {

    public static final String SUNLANDS_EMAIL_SUFFIX = "@sunlands.com";

    //删除标志
    public static final int MAX_PAPER_NUM = 200;
    public static final int MAX_PAPER_SIZE = 1000;
    public static final int MIN_PAPER_NUM = 1;
    public static final int MIN_PAPER_SIZE = 1;

    //删除标志
    public static final int DELETE_FLAG_VALID = 0;
    public static final int DELETE_FLAG_INVALID = 1;

    //启用标志
    public static final int INVALID_FLAG_VALID = 0;
    public static final int INVALID_FLAG_INVALID = 1;

    //当前版本标志
    public static final int CURRENT_VERSION_VALID = 1;
    public static final int CURRENT_VERSION_INVALID = 0;

    //redis试卷难度-[key:PAPER_DIFFICULTY]  [key:difficultyValueParam]  [value:difficultyValue]
    public static final String REDIS_KEY_PAPER_DIFFICULTY = "PAPER_DIFFICULTY";
    public static final String MIN_DIFFICULTY_KEY = "minDifficultyKey";
    public static final String MAX_DIFFICULTY_KEY = "maxDifficultyKey";

    // 知识点级别
    public static final Integer KNOWLEDGE_NODE_LEVEL_CHAPTER = 1;
    public static final Integer KNOWLEDGE_NODE_LEVEL_SECTION = 2;
    public static final Integer KNOWLEDGE_NODE_LEVEL_LEVELONE = 3;
    public static final Integer KNOWLEDGE_NODE_LEVEL_LEVELTWO = 4;
    public static final Integer KNOWLEDGE_NODE_LEVEL_FINAL = 4;

    // 知识树EXCEL 表头信息
    public static final String KNOWLEDGE_NODE_LEVEL_CHAPTER_HEAD = "章编号";
    public static final String KNOWLEDGE_NODE_LEVEL_SECTION_HEAD = "节编号";
    public static final String KNOWLEDGE_NODE_LEVEL_LEVELONE_HEAD = "一级知识点编号";
    public static final String KNOWLEDGE_NODE_LEVEL_LEVELTWO_HEAD = "二级知识点编号";

    // 保存知识点
    public static final Integer KNOWLEDGE_NODE_SAVE_EDIT = 0; // 编辑知识点
    public static final Integer KNOWLEDGE_NODE_SAVE_ADDPEER = 1; // 新增同级知识点
    public static final Integer KNOWLEDGE_NODE_SAVE_ADDLOWER = 2; // 新增下一级知识点

    // 保存知识树关系
    public static final Integer KNOWLEDGE_TREE_REL_ADD = 0;  // 新增知识树
    public static final Integer KNOWLEDGE_TREE_REL_RESELECT = 1;  // 保存重选

    // 知识树语言类别
    public static final Integer KNOWLEDGE_TREE_LANGUAGE_CH = 0;//中文
    public static final Integer KNOWLEDGE_TREE_LANGUAGE_EN = 1;//英文
    //试题难度
    public static final int QUESTION_EASY = 1; //难度-易
    public static final int QUESTION_MIDDLE_TO_EASY = 2; //难度-中偏易
    public static final int QUESTION_MIDDLE = 3; //难度-中
    public static final int QUESTION_MIDDLE_TO_HARD = 4; //难度-中偏难
    public static final int QUESTION_HARD = 5; //难度-难

    public static final String PROJECT_NAME = "EW";
    public static final String TW_PROJECT_NAME = "TW";
    public static final String IW_PROJECT_NAME = "IW";
    public static final String EXERCISE_PROJECT_NAME = "EXERCISE";

    public static final String CURRENT_SYSTEM_CODE = "ew";

    /**
     * 角色 - 教研
     */
    public static final String EW_ADMINISTRATOR = "教研管理员";  // 原PM
    public static final String EW_DIRECTOR = "教研主管"; // 原教研员
    public static final String EW_AUDITOR = "审核教研员";
    public static final String EW_PERSON_TO_UPLOAD_QUESTION = "录题教研员";


    public static final String PAPER_HEAD = "PAPER_HEAD";
    public static final String PAPER_HEAD_NAME = "PAPER_HEAD_NAME";
    public static final String PAPER_HEAD_TOTAL_SCORE = "PAPER_HEAD_TOTAL_SCORE";
    public static final String PAPER_HEAD_TOTAL_TIME = "PAPER_HEAD_TOTAL_TIME";
    public static final String PAPER_HEAD_STUDENT_NAME = "PAPER_HEAD_STUDENT_NAME";
    public static final String PAPER_HEAD_CREATOR = "PAPER_HEAD_CREATOR";
    public static final String PAPER_HEAD_CREATE_TIME = "PAPER_HEAD_CREATE_TIME";
    public static final String PAPER_HEAD_REMARK1 = "PAPER_HEAD_REMARK1";
    public static final String PAPER_HEAD_REMARK2 = "PAPER_HEAD_REMARK2";
    public static final String PAPER_HEAD_REMARK3 = "PAPER_HEAD_REMARK3";

    public static final String REDIS_KEY_QUESTION_LIST_AUTO = "CHANGE_QUESTION_LIST_AUTO";
    public static final String REDIS_KEY_QUESTION_LIST_NORMAL = "CHANGE_QUESTION_LIST_NORMAL";

    public static final String REDIS_KEY_PAPER_CONTENT = "EW_PAPER_CONTENT_"; //redis key -- 试卷内容

    public static final String REDIS_KEY_PAPER_CONTENT_DOWNLOAD = "EW_PAPER_CONTENT_DOWNLOAD_"; //redis key -- 下载试卷内容
    /**
     * 多次随堂考试卷内容缓存 redis_key + paperId; 注：请与其他试卷内容区分开
     */
    public static final String REDIS_KEY_QUIZZES_PAPER_CONTENT = "QUIZZES_PAPER_CONTENT_";

    public static final String REDIS_KEY_PAPER_ANSWER_DETAIL = "PAPER_ANSWER_DETAIL"; // redis key -- 试卷答题详情

    public static final String KNOWLEDGE_TREE_TEMPLATE_FULL_NAME = "template" + File.separator + "知识树模版.xlsx";

    /**
     * 试题来源 上传试题
     */
    public static final String QUESTION_SOURCE_UPLOAD_QUESTION = "UPLOAD_QUESTION";

    /**
     * 试题来源 老题库
     */
    public static final String QUESTION_SOURCE_TIKU = "TIKU";

    /**
     * 试题类型-真题
     */
    public static final String SOURCE_TYPE_REAL_QUESTION = "REAL_QUESTION";

    /**
     * 试题类型-模拟题
     */
    public static final String SOURCE_TYPE_MOCK_QUESTION = "MOCK_QUESTION";

    /**
     * 试题类型-教材题
     */
    public static final String SOURCE_TYPE_MATERIAL_QUESTION = "MATERIAL_QUESTION";

    /**
     * 操作 - 查询不同类型试题列表
     */
    public static final String OPERATE_TYPE_UPLOAD_QUESTIONS = "UPLOAD_QUESTIONS";
    public static final String OPERATE_TYPE_TRANSFER_QUESTIONS = "TRANSFER_QUESTIONS";
    public static final String OPERATE_TYPE_AUDIT_QUESTIONS = "AUDIT_QUESTIONS";


    public static final String UPLOAD_PIC_CHANNEL = "qiyejia";
    public static final String UPLOAD_PIC_TOKEN = MD5ForUploadPic.getMD5tEncryptStr(UPLOAD_PIC_CHANNEL);

    public static final String UPLOAD_QUESTION_OPEATION_KEY = "UPLOAD_QUESTION_OPEATION_";

    public static final String EXPIRED = "EXPIRED";

    private TeachConstants() {
    }

    /**
     * 试题难度名称-易
     */
    public static final String QUESTION_DIFFICULTY_VALUE_EASY_DIFFICULTY = "易";

    /**
     * 试题难度名称-中偏易
     */
    public static final String QUESTION_MIDDLE_TO_EASY_DIFFICULTY = "中偏易";

    /**
     * 试题难度名称-中
     */
    public static final String QUESTION_MIDDLE_DIFFICULTY = "中";

    /**
     * 试题难度名称-中偏难
     */
    public static final String QUESTION_MIDDLE_TO_HARD_DIFFICULTY = "中偏难";

    /**
     * 试题难度名称-难
     */
    public static final String QUESTION_HARD_DIFFICULTY = "难";

    /**
     * 课程模板启用标识
     */
    public static final String COURSE_TEMPLATE_VALID_FLAG = "VALID";
    public static final String COURSE_TEMPLATE_INVALID_FLAG = "INVALID";

    /**
     * 知识点频度：2 极高频，1 高频， 0 中频
     */
    public static final int EXTREMELY_HIGH_FREQUENCY = 2;
    public static final int HIGH_FREQUENCY = 1;
    public static final int MIDDLE_FREQUENCY = 0;

    /**
     * 知识树对应知识点数据表现
     */
    public static final String NODE_DATA_LIST = "NODE_DATA_LIST_";
    public static final String NODE_DATA_REFRESH_TIME = "NODE_DATA_REFRESH_TIME_";

    public static final Integer LANGUAGE_TYPE_CHINESE = 0;
    public static final Integer LANGUAGE_TYPE_ENGLISH = 1;

    public static final List<Integer> INVALID_NODE_ID = Arrays.asList(-1, 0);

    public static final String EW_HOME_PAGE_INFO = "EW_HOME_PAGE_INFO";
    public static final String EW_HOME_PAGE_INCR_INFO = "EW_HOME_PAGE_INCR_INFO";
    public static final Integer EW_HOME_PAGE_INFO_TIME = 86400;//1天

    /**
     * 试题报错处理状态：0 未处理，1 已更正，2 已拒绝
     */
    public static final int REPORT_QUESTION_WRONG_STATUS_UNTREATED = 0;
    public static final int REPORT_QUESTION_WRONG_STATUS_CORRECTED = 1;
    public static final int REPORT_QUESTION_WRONG_STATUS_REJECTED = 2;

    public static final List<Integer> UPDATE_REPORT_QUESTION_WRONG_STATUS = Arrays.asList(REPORT_QUESTION_WRONG_STATUS_CORRECTED,
            REPORT_QUESTION_WRONG_STATUS_REJECTED);


    /**
     * 编辑主池试题可有行为
     */
    public static final List<String> EDIT_QUESTION_MAIN_BEHAVIOR = Arrays.asList(
            QuestionHistoryBehaviorEnum.EDIT_QUESTION_AFTER_AUDIT.getCode(),
            QuestionHistoryBehaviorEnum.HANDLE_REPORT_QUESTION_ERROR.getCode(),
            QuestionHistoryBehaviorEnum.HANDLE_REPORT_QUESTION_NO_ERROR.getCode());
    /**
     * 编辑边池试题可有行为
     */
    public static final List<String> EDIT_TEMP_QUESTION_MAIN_BEHAVIOR = Arrays.asList(
            QuestionHistoryBehaviorEnum.UPLOAD_QUESTION.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_ABANDON.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_QUALIFIED.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_REPEAT.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_UNQUALIFIED.getCode(),
            QuestionHistoryBehaviorEnum.EDIT_QUESTION_BEFORE_AUDIT.getCode());

    public static final List<String> EDIT_QUESTION_MAIN_BEHAVIOR_ALL = Arrays.asList(
            QuestionHistoryBehaviorEnum.EDIT_QUESTION_AFTER_AUDIT.getCode(),
            QuestionHistoryBehaviorEnum.HANDLE_REPORT_QUESTION_ERROR.getCode(),
            QuestionHistoryBehaviorEnum.HANDLE_REPORT_QUESTION_NO_ERROR.getCode(),
            QuestionHistoryBehaviorEnum.UPLOAD_QUESTION.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_ABANDON.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_QUALIFIED.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_REPEAT.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_UNQUALIFIED.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_COMBINED.getCode(),
            QuestionHistoryBehaviorEnum.AUDIT_QUESTION_USEANALYSIS.getCode(),
            QuestionHistoryBehaviorEnum.EDIT_QUESTION_BEFORE_AUDIT.getCode()
    );

    public static final List<String> AUDIT_QUESTION_MAIN_ALL = Arrays.asList(
            BufferPoolQuestionStatusEnum.UNQUALIFIED.getCode(),
            BufferPoolQuestionStatusEnum.QUALIFIED.getCode(),
            BufferPoolQuestionStatusEnum.ABANDON.getCode(),
            BufferPoolQuestionStatusEnum.REPEAT.getCode(),
            BufferPoolQuestionStatusEnum.COMBINED.getCode(),
            BufferPoolQuestionStatusEnum.USEANALYSIS.getCode()
    );




    //最短路径启用标志
    public static final String SHORTEST_PATH_VALID = "VALID";
    public static final String SHORTEST_PATH_INVALID = "INVALID";
    public static final String SHORTEST_PATH_INIT = "INIT";
    public static final String SHORTEST_PATH_LIMITED = "LIMITED";
    public static final String SHORTEST_PATH_CHANGING = "CHANGING";
    public static final String SHORTEST_PATH_COMPLETE = "COMPLETE";
    public static final String SHORTEST_PATH_EXAM_SESSION = "1904";
    public static final double SHORTEST_PATH_MIN_NUM = 30;
    public static final double SHORTEST_PATH_MIN_SCORE = 70;

    //模考人工阅卷用
    public final static String MOCK_EXAM_JUDGE = "JUDGE";
    public final static String MOCK_EXAM_SHOW = "SHOW";
    public final static String MOCK_EXAM_PREVIOUS = "PREVIOUS";
    public final static String MOCK_EXAM_NEXT = "NEXT";
    public static final String RECORD_STATUS_COMPLETE = "COMPLETE";
    public static final String RECORD_STATUS_MARKING = "MARKING";
    public final static String MOCK_EXAM_REPEAT_SUBMIT_MESSAGE = "试卷已经判过分";
    public final static String MOCK_EXAM_OK = "OK";

    public final static String FILE_SUFFIX_GIF = ".gif";

    // 单词本sfs地址
    public final static String ORIGIN_VOCABULARY = "VOCABULARY";

    /**
     * 管理类联考逻辑课
     */
    public static final Integer MBA_LOGIC = 827;

    /**
     * 管理类联考数学课
     */
    public static final Integer MBA_MATH = 828;

    /**
     * 管理类联考英语课
     */
    public static final Integer MBA_ENGLISH = 829;

    /**
     * 管理类联考写作课
     */
    public static final Integer MBA_COMPOSITION = 830;

    /**
     * 管理类联考面试
     */
    public static final Integer MBA_SIGN_TYPE = 848;

    public static final List<Integer> MBA_SUBJECT_LIST=Arrays.asList(MBA_LOGIC,MBA_MATH,MBA_ENGLISH,MBA_COMPOSITION,MBA_SIGN_TYPE);
}
