package com.colson.service;

import com.colson.dal.dto.PaperDetailDTO;

/**
 * Created by Administrator on 2017/8/8.
 */
public interface PaperDownloadService {

    PaperDetailDTO getPaperInfoByPaperCode(String paperCode);
}
