package com.colson.service;

import com.colson.dal.dto.ResKnowledgeTreeDTO;
import com.colson.dal.dto.ResProvinceDTO;
import com.colson.dal.dto.ResSubjectAndProvinceDTO;

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

    List<Integer> getAllSubjectIds();

    List<ResProvinceDTO> getAllProvinces();

    /**
     * 根据科目id，省份id和二级项目id获取真题归属省份列表
     *
     * @return
     */
    List<ResProvinceDTO> getProvincesByknowledgeTreeId(Integer knowledgeTreeId);

    List<ResSubjectAndProvinceDTO> queryByKnowledgeTreeId(Integer knowledgeTreeId);

    ResKnowledgeTreeDTO getKnowledgeTreeById(Integer knowledgeTreeId, Integer invalidFlag);

}
