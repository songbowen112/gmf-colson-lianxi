package com.colson.service.impl;

import com.colson.dal.dto.PaperDetailDTO;
import com.colson.service.PaperDownloadService;
import com.colson.service.TeachPaperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/8.
 */
@Slf4j
@Service("PaperDownloadService")
public class PaperDownloadServiceImpl implements PaperDownloadService {

    @Autowired
    TeachPaperService paperService;

    @Override
    public PaperDetailDTO getPaperInfoByPaperCode(String paperCode) {
        PaperDetailDTO paperByPaperCode = paperService.getPaperByPaperCode(paperCode);
        String subjectName = paperByPaperCode.getSubjectName();
        //科目名称超过十个字换行
        if (StringUtils.isNotEmpty(subjectName) && subjectName.length() > 10) {
            StringBuffer stringBuffer = new StringBuffer(subjectName);
            stringBuffer.insert(subjectName.length() / 2,"<br/>");
            paperByPaperCode.setSubjectName(stringBuffer.toString());
        }
        return paperByPaperCode;
    }
}
