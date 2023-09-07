package com.colson.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.colson.dal.dto.ResSubjectAndProvinceDTO;
import com.colson.dal.dto.ValuableBookFileInfoDTO;
import com.colson.service.AnalyzeService;
import com.colson.service.TikuCommonService;
import com.colson.service.ValuableBookService;
import com.colson.service.dto.ValuableBookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Lazy(value = false)
public class ValuableBookServiceImpl implements ValuableBookService {

    private Logger logger = LoggerFactory.getLogger(ValuableBookServiceImpl.class);

    @Autowired
    private AnalyzeService analyzeService;

    @Autowired
    private TikuCommonService tikuCommonService;

    @Override
    public void batchCreateFilePath(Integer knowledgeTreeId) {
//        ValuableBookFileInfoDTO bookFileInfoDTO = analyzeService.getValuableBookByKnowledgeTreeId(knowledgeTreeId);
//        if (null != bookFileInfoDTO) {
//            return;
//        }
        ValuableBookFileInfoDTO valuableBookFileInfoDTO = analyzeService.queryValuableBookByKnowledgeTreeId(knowledgeTreeId);
//        if (null != valuableBookFileInfoDTO) {
//            List<ResSubjectAndProvinceDTO> resSubjectAndProvinceDTOS = tikuCommonService.queryByKnowledgeTreeId(knowledgeTreeId);
//            for (ResSubjectAndProvinceDTO resSubjectAndProvinceDTO : resSubjectAndProvinceDTOS) {
//                Integer subjectId = resSubjectAndProvinceDTO.getSubjectId();
//                Integer provinceId = resSubjectAndProvinceDTO.getProvinceId();
//                ValuableBookFileInfoDTO infoDTO = analyzeService.getValuableBookBySubjectAndProvinceAndKnowledgeTree(subjectId, provinceId, knowledgeTreeId);
//                if (null != infoDTO) {
//                    continue;
//                }
//                ValuableBookFileInfoDTO dto = new ValuableBookFileInfoDTO();
//                BeanUtils.copyProperties(valuableBookFileInfoDTO, dto);
//                dto.setProvinceId(provinceId);
//                dto.setSubjectId(subjectId);
//                dto.setKnowledgeTreeId(knowledgeTreeId);
//                analyzeService.insertValuableBookFileInfoDTO(dto);
//            }
//        }
    }

}
