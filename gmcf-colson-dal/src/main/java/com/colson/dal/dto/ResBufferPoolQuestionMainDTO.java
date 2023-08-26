package com.colson.dal.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResBufferPoolQuestionMainDTO extends ResQuestionMainDTO {

    private Integer subjectId;

    private String subjectName;

    private String knowledgeTreeBelongProvince;

    private String questionSource;

    private String	questionStatus;

    private List<BufferPoolQuestionEditLogDTO> LogList;

    private String repeatQuestionMainId;

    private Float repeatPercent;

    private ResQuestionMainDTO repeatQuestionMainDTO;

}
