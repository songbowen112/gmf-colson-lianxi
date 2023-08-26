package com.colson.dal.dto;

import java.util.List;

/**
 * 试题类，用于智能组卷
 */
public class QuestionForGeneratePaper extends QuestionMain {

    //是否被选到试卷中
    private boolean selected = false;

    // addby chenqiuxia 20190103  试题关联知识点，不区分主副知识点（用作随机组卷，知识点全覆盖）
    private List<Integer> nodeIds;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<Integer> getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(List<Integer> nodeIds) {
        this.nodeIds = nodeIds;
    }

}
