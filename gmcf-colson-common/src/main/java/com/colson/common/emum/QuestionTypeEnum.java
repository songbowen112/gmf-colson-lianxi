package com.colson.common.emum;

/**
 * 试题类型
 */
public enum QuestionTypeEnum {

    /**
     * 试题类型
     * 2019-09-17添加试题默认排序
     * 所有题目按类型进行排序推题：单选>判断>多选>填空（有序）>填空（无序）>判断论述题>文字题>综合题>多选多>完形填空>主观题>其它
     */
    SINGLE_CHOICE("SINGLE_CHOICE", "单选题", 1),
    JUDGE_CHOICE("JUDGE_CHOICE", "判断题", 2),
    MULTI_CHOICE("MULTI_CHOICE", "多选题", 3),
    /**
     * 有序填空题
     */
    ORDER_FILL_BLANK("ORDER_FILL_BLANK", "填空题", 4),
    /**
     * 无序填空题
     */
    DISORDER_FILL_BLANK("DISORDER_FILL_BLANK", "填空题", 5),
    /**
     * 统一的填空题,包含上述两种类型
     */
    FILL_BLANK("FILL_BLANK", "填空题", 6),
    JUDGE_ESSAY("JUDGE_ESSAY", "判断论述题", 7),
    ESSAY("ESSAY", "文字题", 8),
    COMPREHENSIVE("COMPREHENSIVE", "综合题", 9),
    MANY_TO_MANY("MANY_TO_MANY", "多选多", 10),
    READING_COMPREHENSION("READING_COMPREHENSION", "完形填空题", 11),
    SUBJECTIVE("SUBJECTIVE", "主观题", 12),
    /**
     * 默认题目类型
     */
    OTHERS("OTHERS", "其他", 100);

    private String code;
    private String name;
    private Integer order;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 构造方法
     *
     * @param code
     * @param name
     * @param order
     * @return
     */
    QuestionTypeEnum(String code, String name, Integer order) {
        this.code = code;
        this.name = name;
        this.order = order;
    }

    public static String getNameByCode(String code) {
        for (QuestionTypeEnum c : QuestionTypeEnum.values()) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c.name;
            }
        }
        return QuestionTypeEnum.OTHERS.getName();
    }

    public static String getCodeByName(String name) {
        for (QuestionTypeEnum c : QuestionTypeEnum.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c.code;
            }
        }
        return QuestionTypeEnum.OTHERS.getCode();
    }

    public static Integer getOrderByCode(String code) {
        for (QuestionTypeEnum c : QuestionTypeEnum.values()) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c.order;
            }
        }
        return  QuestionTypeEnum.OTHERS.getOrder();
    }

    public static boolean isBigQuestion(String name) {
        if (QuestionTypeEnum.MANY_TO_MANY.getCode().equals(name) ||
                QuestionTypeEnum.READING_COMPREHENSION.getCode().equals(name) ||
                QuestionTypeEnum.COMPREHENSIVE.getCode().equals(name)) {
            return true;
        }
        return false;
    }

}
