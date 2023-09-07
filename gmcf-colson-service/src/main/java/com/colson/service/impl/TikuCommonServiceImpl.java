package com.colson.service.impl;


import com.colson.common.constants.TeachConstants;
import com.colson.common.emum.OutlineRequirementEnum;
import com.colson.dal.dao.KnowledgeTreeDAO;
import com.colson.dal.dao.TikuCommonDAO;
import com.colson.dal.demo.KnowledgeTree;
import com.colson.dal.demo.SubjectAIRel;
import com.colson.dal.dto.*;
import com.colson.service.TikuCommonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by PC on 2017/12/29.
 */
@Service
public class TikuCommonServiceImpl implements TikuCommonService {

    @Autowired
    private TikuCommonDAO tikuCommonDAO;

    @Autowired
    private KnowledgeTreeDAO knowledgeTreeDAO;

    @Override
    public Set<Integer> queryKnowledgeTreeIdsByCondition(List<Integer> subjectIds, List<Integer> provinceIds, Integer projectSecondId) {
        if (CollectionUtils.isEmpty(subjectIds)) {
            throw new RuntimeException("传入subjectIds为空！");
        }
        return tikuCommonDAO.selectTreeIdList(subjectIds, provinceIds, projectSecondId);
    }

    @Override
    public List<Integer> getExamSessionIdsBySession(String beginSession, String endSession) {
        return tikuCommonDAO.getExamSessionIdsBySession(beginSession, endSession);
    }

    @Override
    public List<Integer> getAllSubjectIds() {
        return tikuCommonDAO.getAllSubjectId();
    }

    @Override
    public List<ResProvinceDTO> getAllProvinces() {
        return tikuCommonDAO.getAllProvinces();
    }

    @Override
    public List<ResProvinceDTO> getProvincesByknowledgeTreeId(Integer knowledgeTreeId) {
        return tikuCommonDAO.getProvincesByknowledgeTreeId(knowledgeTreeId);
    }

    @Override
    public List<ResSubjectAndProvinceDTO> queryByKnowledgeTreeId(Integer knowledgeTreeId) {
        return tikuCommonDAO.queryByKnowledgeTreeId(knowledgeTreeId);
    }

    /**
     * 根据id查询知识树
     * @param knowledgeTreeId
     * @return
     * @throws Exception
     */
    @Override
    public ResKnowledgeTreeDTO getKnowledgeTreeById(Integer knowledgeTreeId, Integer invalidFlag) {
        if (knowledgeTreeId == null) {
            return null;
        }
        return this.queryKnowledgeTreeById(knowledgeTreeId, invalidFlag);
    }

    public ResKnowledgeTreeDTO queryKnowledgeTreeById(Integer knowledgeTreeId, Integer invalidFlag) {
        KnowledgeTree knowledgeTree = knowledgeTreeDAO.selectKnowledgeTreeById(knowledgeTreeId);
        if(knowledgeTree == null) {
            throw new RuntimeException("查询知识树不存在");
        }
        // 封装知识树基本信息
        ResKnowledgeTreeDTO resKnowledgeTreeDTO = new ResKnowledgeTreeDTO();
        resKnowledgeTreeDTO.setId(knowledgeTree.getId());
        resKnowledgeTreeDTO.setName(knowledgeTree.getName());
        resKnowledgeTreeDTO.setSubjectId(knowledgeTree.getSubjectId());
        resKnowledgeTreeDTO.setLanguageType(knowledgeTree.getLanguageType());
        // 查询省份列表
        List<ResProvinceDTO> provinces = knowledgeTreeDAO.queryProvincesByKnowledgeTreeId(knowledgeTree.getId());
        if(!CollectionUtils.isEmpty(provinces)){
            resKnowledgeTreeDTO.setProvinceList(provinces);
        }
        // 查询二级项目列表
        List<ResProjectSecondDTO> project2nds = knowledgeTreeDAO.queryProject2ndsByKnowledgeTreeId(knowledgeTree.getId());
        if (!CollectionUtils.isEmpty(project2nds)) {
            resKnowledgeTreeDTO.setProjectSecondList(project2nds);
        }
        // 查询知识点信息
        List<ResKnowledgeNodeDTO> children = this.queryChildrenByCondition(knowledgeTree.getId(), TeachConstants.KNOWLEDGE_NODE_LEVEL_CHAPTER,null,invalidFlag);
        if (!CollectionUtils.isEmpty(children)) {
            resKnowledgeTreeDTO.setChildren(children);
        }
        // 查询挂题状态
        int hasQuestion = this.hasQuestion(knowledgeTree.getId());
        resKnowledgeTreeDTO.setHasQuestionFlag(hasQuestion);
        // 查询文字题&判断论述题是否开启AI判分标志位
        SubjectAIRel subjectAIRel = knowledgeTreeDAO.selectAIJudgementBySubjectId(resKnowledgeTreeDTO.getSubjectId());
        if (subjectAIRel != null) {
            resKnowledgeTreeDTO.setEssayAIFlag(subjectAIRel.getEssayAIFlag());
            resKnowledgeTreeDTO.setJudgeEssayAIFlag(subjectAIRel.getJudgeEssayAIFlag());
            resKnowledgeTreeDTO.setOrderFillBlankAIFlag(subjectAIRel.getOrderFillBlankAIFlag());
            resKnowledgeTreeDTO.setDisorderFillBlankAIFlag(subjectAIRel.getDisorderFillBlankAIFlag());
        }
        return resKnowledgeTreeDTO;
    }

    /**
     * 查询知识树是否挂题
     * @param knowledgeTreeId
     * @return
     */
    public int hasQuestion(Integer knowledgeTreeId){
        if (knowledgeTreeDAO.checkQuestionByKnowledgeTreeId(knowledgeTreeId) > 0) {
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 递归查询知识点- 返回当前及所有子知识点
     * @param knowledgeTreeId
     * @param level
     * @param parentNodeId
     * @return
     */
    public List<ResKnowledgeNodeDTO> queryChildrenByCondition(Integer knowledgeTreeId,Integer level,Integer parentNodeId, Integer invalidFlag){
        if (level > TeachConstants.KNOWLEDGE_NODE_LEVEL_FINAL) {
            return null; // 已到末级知识点
        }
        // 查询知识点--invalidFlag为0时，返回所有未禁用知识点
        List<ResKnowledgeNodeDTO> nodes = knowledgeTreeDAO.queryChildrenByCondition(knowledgeTreeId,level,parentNodeId,invalidFlag);
        // 根据知识点编号正序排列
        sortKnowledgeNodesBySerialNumber(nodes);
        if(!CollectionUtils.isEmpty(nodes)){
            for(ResKnowledgeNodeDTO node : nodes){
                // 设置大纲要求
                if(node.getOutlineRequirementCode() != null && !"".equals(node.getOutlineRequirementCode())){
                    node.setOutlineRequirementName(OutlineRequirementEnum.getNameByCode(node.getOutlineRequirementCode()));
                }
                // 递归查询子知识点
                List<ResKnowledgeNodeDTO> children = queryChildrenByCondition(knowledgeTreeId,node.getLevel()+1,node.getId(),invalidFlag);
                if (!CollectionUtils.isEmpty(children)) {
                    node.setChildren(children);
                }
            }
        }
        return nodes;
    }

    /**
     * 同目录下的知识点按知识点编号正序排列(取末位排序即可)
     * @param nodes
     */
    private void sortKnowledgeNodesBySerialNumber(List<ResKnowledgeNodeDTO> nodes){
        Comparator<ResKnowledgeNodeDTO> compare = new Comparator<ResKnowledgeNodeDTO>() {
            public int compare(ResKnowledgeNodeDTO o1, ResKnowledgeNodeDTO o2) {   // 自定义升序
                if((o1 == null) && (o2 == null)){
                    return 0;
                }else if(o1 == null){
                    return 1;
                }else if(o2 == null){
                    return -1;
                }
                return o1.compareTo(o2);
            }
        };
        Collections.sort(nodes,compare);
    }

}
