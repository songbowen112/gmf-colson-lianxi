package com.colson.service;

import com.colson.service.dto.DownloadPdfParamInfo;

public interface PaperUploadService {

    void downloadPdf(DownloadPdfParamInfo paramInfo, String templatePath, String fontPath, String basePath);

    /**
     * pdf下载定时任务
     * @throws Exception
     */
    void downloadPdfByPaperCodes(String paperCodes, String templatePath, String fontPath, String basePath);
}
