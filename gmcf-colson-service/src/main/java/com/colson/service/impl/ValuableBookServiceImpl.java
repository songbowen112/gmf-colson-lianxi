package com.sunlands.analyze.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunlands.analyze.dto.ValuableBookDTO;
import com.sunlands.analyze.service.AnalyzeService;
import com.sunlands.analyze.service.ShortUrlService;
import com.sunlands.analyze.service.ValuableBookService;
import com.sunlands.common.cacheConstants.TikuCacheConstants;
import com.sunlands.common.dao.ValuableBookDAO;
import com.sunlands.common.dto.ShortUrlResultDTO;
import com.sunlands.common.dto.ValuableBookFileInfoDTO;
import com.sunlands.common.service.CommonService;
import com.sunlands.common.util.RedisCacheUtil;
import com.sunlands.knowledgeTree.dto.ResProvinceDTO;
import com.sunlands.knowledgeTree.dto.ResSubjectAndProvinceDTO;
import com.sunlands.knowledgeTree.service.KnowledgeTreeService;
import org.apache.commons.collections4.CollectionUtils;
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
    private CommonService commonService;

    @Autowired
    private KnowledgeTreeService knowledgeTreeService;

    @Override
    public Integer queryExistsBySubjectAndProvince(Integer subjectId, Integer provinceId) {
        String redisKey = String.format(TikuCacheConstants.TIKU_VALUABLE_BOOK_EXISTS, subjectId, provinceId);
        String valuableBookJson = RedisCacheUtil.getStr(redisKey);
        if (!StringUtils.isEmpty(valuableBookJson)) {
            return Integer.valueOf(valuableBookJson);
        }
        Integer knowledgeTreeId = knowledgeTreeService.getKnowledgeTreeIdByCondition(subjectId, provinceId);
        Assert.notNull(knowledgeTreeId, "知识树id不能为空");

        Integer exists = analyzeService.queryExistsBySubjectAndProvince(knowledgeTreeId);
        RedisCacheUtil.set(redisKey, exists + "",  TikuCacheConstants.TIKU_CACHE_TIME_ONE_HOUR);
        return exists;
    }

    @Override
    public ValuableBookDTO getFilePathBySubjectAndProvince(Integer subjectId, Integer provinceId) {
        ValuableBookDTO valuableBookDTO = new ValuableBookDTO();

        String redisKey = String.format(TikuCacheConstants.TIKU_VALUABLE_BOOK_URL, subjectId, provinceId);
        String valuableBookJson = RedisCacheUtil.getStr(redisKey);
        if (!StringUtils.isEmpty(valuableBookJson)) {
            return JSONObject.parseObject(valuableBookJson, ValuableBookDTO.class);
        }
        ValuableBookFileInfoDTO bookFileInfoDTO = analyzeService.getValuableBookBySubjectIdAndProvinceId(subjectId, provinceId);
        if (null != bookFileInfoDTO) {
            valuableBookDTO.setSfsPath(bookFileInfoDTO.getFileLink());
            valuableBookDTO.setSfsShortPath(bookFileInfoDTO.getFileShortLink());
            RedisCacheUtil.set(redisKey, JSON.toJSONString(valuableBookDTO),  TikuCacheConstants.TIKU_CACHE_TIME_ONE_HOUR);
            return valuableBookDTO;
        }

        ValuableBookFileInfoDTO valuableBookFileInfoDTO = analyzeService.queryValuableBookBySubjectAndProvince(subjectId, provinceId);
        if (null != valuableBookFileInfoDTO) {
            analyzeService.insertValuableBookFileInfoDTO(valuableBookFileInfoDTO);
            valuableBookDTO.setSfsPath(valuableBookFileInfoDTO.getFileLink());
            valuableBookDTO.setSfsShortPath(valuableBookFileInfoDTO.getFileShortLink());
            RedisCacheUtil.set(redisKey, JSON.toJSONString(valuableBookDTO),  TikuCacheConstants.TIKU_CACHE_TIME_ONE_HOUR);
        }
        return valuableBookDTO;
    }

    @Override
    public void batchCreateFilePath(Integer knowledgeTreeId) {
        ValuableBookFileInfoDTO bookFileInfoDTO = analyzeService.getValuableBookByKnowledgeTreeId(knowledgeTreeId);
        if (null != bookFileInfoDTO) {
            return;
        }
        ValuableBookFileInfoDTO valuableBookFileInfoDTO = analyzeService.queryValuableBookByKnowledgeTreeId(knowledgeTreeId);
        if (null != valuableBookFileInfoDTO) {
            List<ResSubjectAndProvinceDTO> resSubjectAndProvinceDTOS = commonService.queryByKnowledgeTreeId(knowledgeTreeId);
            for (ResSubjectAndProvinceDTO resSubjectAndProvinceDTO : resSubjectAndProvinceDTOS) {
                Integer subjectId = resSubjectAndProvinceDTO.getSubjectId();
                Integer provinceId = resSubjectAndProvinceDTO.getProvinceId();
                ValuableBookFileInfoDTO infoDTO = analyzeService.getValuableBookBySubjectAndProvinceAndKnowledgeTree(subjectId, provinceId, knowledgeTreeId);
                if (null != infoDTO) {
                    continue;
                }
                ValuableBookFileInfoDTO dto = new ValuableBookFileInfoDTO();
                BeanUtils.copyProperties(valuableBookFileInfoDTO, dto);
                dto.setProvinceId(provinceId);
                dto.setSubjectId(subjectId);
                dto.setKnowledgeTreeId(knowledgeTreeId);
                analyzeService.insertValuableBookFileInfoDTO(dto);
            }
        }
    }
}
