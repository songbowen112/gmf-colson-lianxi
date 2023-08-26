package com.colson.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by PC on 2017/12/29.
 */
public interface TikuCommonService {

    /**
     * 查询知识树id
     *
     * @param subjectIds
     * @param provinceIds
     * @param projectSecondId
     * @return
     */
    Set<Integer> queryKnowledgeTreeIdsByCondition(List<Integer> subjectIds, List<Integer> provinceIds, Integer projectSecondId);

    List<Integer> getExamSessionIdsBySession(String beginSession, String endSession);

    List<Integer> getAllSubjectId();

}
