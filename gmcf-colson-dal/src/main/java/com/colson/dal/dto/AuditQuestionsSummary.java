package com.colson.dal.dto;

import lombok.Data;

/**
 * 试题审核总数概要
 * @author liujionghao
 */
@Data
public class AuditQuestionsSummary {
    /**
     * 待审核试题数量
     */
    private Integer notAuditQuestionCount;

    /**
     * 不合格试题数量
     */
    private Integer failAuditQuestionCount;
}
