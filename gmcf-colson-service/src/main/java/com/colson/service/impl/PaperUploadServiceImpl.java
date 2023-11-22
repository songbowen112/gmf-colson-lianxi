package com.colson.service.impl;

import com.alibaba.fastjson.JSON;
import com.colson.common.constants.TeachConstants;
import com.colson.common.emum.SubjectCodeEnum;
import com.colson.common.utils.ApiUtils;
import com.colson.common.utils.PathUtil;
import com.colson.common.utils.PdfDocumentGenerator;
import com.colson.common.utils.PdfUtils;
import com.colson.dal.dao.AnalyzeDAO;
import com.colson.dal.dao.PaperFileDAO;
import com.colson.dal.dto.*;
import com.colson.service.*;
import com.colson.service.dto.DownloadPdfParamInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaperUploadServiceImpl implements PaperUploadService {

    @Autowired
    private AnalyzeDAO analyzeDAO;

    @Autowired
    private PaperDownloadService paperDownloadService;

    @Autowired
    private TeachPaperService teachPaperService;

    @Autowired
    private TikuCommonService tikuCommonService;

    @Autowired
    private ValuableBookService valuableBookService;

    @Value("${courseTemplateAttachmentPath:/course_template/attachment/}")
    private String templateFilePath;


    @Override
    public void downloadPdf(DownloadPdfParamInfo paramInfo, String templatePath, String fontPath, String basePath) {
        log.info("downloadPdf ===== start, param={}", JSON.toJSONString(paramInfo));
        try {
            PaperSearchingDTO condition = new PaperSearchingDTO();
            condition.setInvalidFlag(paramInfo.getInvalidFlag());

            List<Integer> subjectIds = ApiUtils.convertStringToInteger(paramInfo.getSubjectIds());
            List<Integer> provinceIds = ApiUtils.convertStringToInteger(paramInfo.getProvinceIds());

            if (CollectionUtils.isEmpty(subjectIds)) {
                subjectIds = tikuCommonService.getAllSubjectIds();
            }

            if (StringUtils.isNotEmpty(paramInfo.getBeginSession()) && StringUtils.isNotEmpty(paramInfo.getEndSession())) {
                List<Integer> sessionIds = tikuCommonService.getExamSessionIdsBySession(paramInfo.getBeginSession(), paramInfo.getEndSession());
                condition.setExamSessionIds(sessionIds);
            }
            //科目对应的知识树
            Set<Integer> knowledgeTreeIds = tikuCommonService.queryKnowledgeTreeIdsByCondition(subjectIds,
                    provinceIds, null);
            if (CollectionUtils.isEmpty(knowledgeTreeIds)) {
                log.error("downloadPdf ===== 此科目/省份对应的知识树不存在!paramInfo={}", paramInfo);
                return;
            }
            condition.setKnowledgeTreeIds(knowledgeTreeIds);
            //教研只能看到教研端新增的试卷
            condition.setSource(TeachConstants.PROJECT_NAME);
            condition.setPaperType(paramInfo.getExerciseType());

            Integer totalNum = teachPaperService.getPaperListCountByCondition(condition);
            log.info("downloadPdf ===== 试卷数量, totalNum={}", totalNum);

            Integer pageNo = 1;//当前页码
            Integer pageSize = 100;//每页显示的条目数
            Integer pageIndex;

            condition.setPageSize(pageSize);
            while (totalNum > 0) {
                totalNum -= 100;
                pageIndex = (pageNo++ - 1) * pageSize;
                condition.setPageIndex(pageIndex);
                // 获取试卷list
                List<PaperInfoDTO> paperList = teachPaperService.getPaperListByCondition(condition);
                //批量下载pdf
                this.batchDownloadPdf(paperList, templatePath, fontPath, basePath);
                Thread.sleep(1000);
            }
            log.info("downloadPdf ===== end, param={}", JSON.toJSONString(paramInfo));
        } catch (Exception e) {
            log.error("downloadPdf ===== fail", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void downloadPdfByPaperCodes(String paperCodes, String templatePath, String fontPath, String basePath) {
        log.info("downloadPdfByPaperCodes ===== start, param={}", paperCodes);
        try {
            List<String> paperCodeList = ApiUtils.convertStringToList(paperCodes);
            // 获取试卷list
            List<PaperInfoDTO> paperList = teachPaperService.getPaperListByPaperCodes(paperCodeList);
            log.info("downloadPdfByPaperCodes ===== 试卷数量, totalNum={}", paperList.size());
            //批量下载pdf
            this.batchDownloadPdf(paperList, templatePath, fontPath, basePath);
            log.info("downloadPdfByPaperCodes ===== end, param={}", paperCodes);
        } catch (Exception e) {
            log.error("downloadPdfByPaperCodes ===== fail", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void downloadValuableBookTask(String subjectIdStr, String provinceIdStr) {
        log.info("downloadValuableBookTask ===== start, subjectIdStr={}, provinceIdStr={}", subjectIdStr, provinceIdStr);
        try {
            List<Integer> subjectIds = ApiUtils.convertStringToInteger(subjectIdStr);
            List<Integer> provinceIds = ApiUtils.convertStringToInteger(provinceIdStr);
            if (CollectionUtils.isEmpty(subjectIds)) {
                subjectIds = tikuCommonService.getAllSubjectIds();
            }
            if (CollectionUtils.isEmpty(provinceIds)) {
                List<ResProvinceDTO> allProvinces = tikuCommonService.getAllProvinces();
                provinceIds = allProvinces.stream().map(item -> item.getId()).collect(Collectors.toList());
            }
            //科目+省份对应的知识树
            Set<Integer> knowledgeTreeIds = tikuCommonService.queryKnowledgeTreeIdsByCondition(subjectIds,
                    provinceIds, null);
            if (CollectionUtils.isEmpty(knowledgeTreeIds)) {
                log.error("downloadValuableBookTask ===== 此科目/省份对应的知识树不存在!subjectIdStr={}, provinceIdStr={}", subjectIdStr, provinceIdStr);
                return;
            }

            /*	覆盖考期：2013年4月至2023年4月（每年的4月、10月两次）	*/
            int year = 13;
            int month = 4;
            List<String> examSessionLimit = analyzeDAO.queryExamSessionByYearAndMonth(year, month);

            for (Integer knowledgeTreeId : knowledgeTreeIds) {
                try {
                    valuableBookService.batchCreateFilePath(knowledgeTreeId, examSessionLimit);
                } catch (Exception e) {
                    log.error("downloadValuableBookTask error ===== knowledgeTreeId:{}, e:{}", knowledgeTreeId, e);
                }
            }
            log.info("downloadValuableBookTask ===== end, subjectIdStr={}, provinceIdStr={}", subjectIdStr, provinceIdStr);
        } catch (Exception e) {
            log.error("downloadValuableBookTask ===== fail", e);
            throw new RuntimeException(e);
        }
    }

    private void batchDownloadPdf(List<PaperInfoDTO> paperList, String templatePath, String fontPath, String basePath) {
        File tmpFile = null;
        FileInputStream in = null;
        for (PaperInfoDTO paperInfoDTO : paperList) {
//            int exist = paperFileDAO.paperFileCountByCode(paperInfoDTO.getCode());
//            if(exist > 0) {
//                log.error("batchDownloadPdf ===== 试卷已上传，paperCode：{}",paperInfoDTO.getCode());
//                continue;
//            }
            PaperDetailDTO paperDetailDTO = paperDownloadService.getPaperInfoByPaperCode(paperInfoDTO.getCode());
            if (null == paperDetailDTO) {
                log.error("batchDownloadPdf ===== 试卷内容不存在，paperCode：{}",paperInfoDTO.getCode());
                continue;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("realPaperInfo", paperDetailDTO);
            map.put("type", "REAL");

            // 生成 PDF 文档
            PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
            ByteArrayInputStream byteArrayInputStream = pdfGenerator.generateStream(templatePath, fontPath, map);

            String subjectName = paperDetailDTO.getOriginalSubjectName();
            String paperName = paperDetailDTO.getPaperName().replace("/", "-");
            String subjectCode = SubjectCodeEnum.getMap().get(subjectName);
            String subjectPath = subjectCode + "_" + subjectName;

            String pathUrl = "/Users/songbowen/Desktop/资料/真题pdf" + File.separator + subjectPath;
            File pathFile = new File(pathUrl);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }

            //添加水印
            String fileUrl = pathUrl + File.separator + paperName + ".pdf";
            PdfUtils.addWaterMarkByFile(byteArrayInputStream, fileUrl, "自考备考 就找牛哥", basePath);
            System.out.println("------------fileUrl:" + fileUrl);
            try {
                tmpFile = new File(fileUrl);

                in = new FileInputStream(tmpFile);
                String fileMd5 = DigestUtils.md5Hex(in);

//                String basePath = templateFilePath + fileMd5 + "/" + paperName + ".pdf";
//                ReqPaperFileDTO paperFileAddDTO = this.constructReqPaperFileDTO(paperInfoDTO);
//                int rows = paperFileDAO.insertPaperUpload(paperFileAddDTO);
//                if (rows > 0) {
//                    log.info("batchDownloadPdf ===== 新试卷文件上传成功！ReqPaperFileDTO:{}", JSON.toJSONString(paperFileAddDTO));
//                } else {
//                    log.error("batchDownloadPdf ===== 新试卷文件上传失败! ReqPaperFileDTO:{}", JSON.toJSONString(paperFileAddDTO));
//                }
            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }

        }
    }

    private ReqPaperFileDTO constructReqPaperFileDTO(PaperInfoDTO paperInfoDTO) {
        ReqPaperFileDTO paperFileAddDTO = new ReqPaperFileDTO();
        paperFileAddDTO.setPaperCode(paperInfoDTO.getCode());
        paperFileAddDTO.setPaperName(paperInfoDTO.getPaperName());
        paperFileAddDTO.setPaperType(paperInfoDTO.getPaperType());
        paperFileAddDTO.setAbPaperId(paperInfoDTO.getExamTagId());
        paperFileAddDTO.setAvgDifficultyValue(paperInfoDTO.getAvgDifficultyValue());
        paperFileAddDTO.setQuestionAmount(paperInfoDTO.getQuestionAmount());
        paperFileAddDTO.setTotalScore(paperInfoDTO.getTotalScore());
        paperFileAddDTO.setExamProvince(paperInfoDTO.getExamProvinceId());
        paperFileAddDTO.setExamSession(paperInfoDTO.getExamSessionId());
        paperFileAddDTO.setSubjectId(paperInfoDTO.getSubjectId());
        paperFileAddDTO.setKnowledgeTreeId(paperInfoDTO.getKnowledgeTreeId());

        paperFileAddDTO.setOnOffline(1);
        paperFileAddDTO.setCreator("系统");
        paperFileAddDTO.setOperator("系统");
        return paperFileAddDTO;
    }
}
