package com.colson.service;

import com.colson.service.dto.DownloadPdfParamInfo;

public interface PaperUploadService {

    /**
     * pdf真题下载定时任务
     * @throws Exception
     */
    void downloadPdf(DownloadPdfParamInfo paramInfo, String templatePath, String fontPath, String basePath);

    /**
     * pdf真题下载定时任务
     * @throws Exception
     */
    void downloadPdfByPaperCodes(String paperCodes, String templatePath, String fontPath, String basePath);

    /**
     * 通关宝典word下载
     * @param subjectIdStr
     * @param provinceIdStr
     */
    void downloadValuableBookTask(String subjectIdStr, String provinceIdStr);
}
