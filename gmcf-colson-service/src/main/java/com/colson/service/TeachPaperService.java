package com.colson.service;

import com.colson.dal.dto.PaperDetailDTO;
import com.colson.dal.dto.PaperInfoDTO;
import com.colson.dal.dto.PaperSearchingDTO;
import com.colson.dal.dto.ResQuestionMainDTO;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */
public interface TeachPaperService {


    /**
     * 根据试卷code获取预览试卷内容
     *
     * @param paperCode 试卷code
     * @return 试卷内容
     */
    PaperDetailDTO getPaperByPaperCode(String paperCode);

    /**
     * 装填试题被试卷引用的信息
     * @param questionContents
     * @return void
     * @author suntenghao
     * @date 2020-04-03
     */
    void setPaperInfo(List<ResQuestionMainDTO> questionContents);

    List<PaperInfoDTO> getPaperListByCondition(PaperSearchingDTO condition);

    List<PaperInfoDTO> getPaperListByPaperCodes(List<String> paperCodes);

    Integer getPaperListCountByCondition(PaperSearchingDTO condition);


}
