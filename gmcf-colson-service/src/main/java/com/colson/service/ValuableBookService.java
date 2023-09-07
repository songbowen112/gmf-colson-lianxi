package com.sunlands.analyze.service;

import com.sunlands.analyze.dto.AnalyzeKnowledgeNodeDTO;
import com.sunlands.analyze.dto.ValuableBookDTO;
import com.sunlands.common.datasource.DataSource;
import com.sunlands.common.datasource.DataSourceEnum;
import com.sunlands.common.dto.ValuableBookFileInfoDTO;

import java.util.List;

public interface ValuableBookService {

    @DataSource(DataSourceEnum.READ)
    Integer queryExistsBySubjectAndProvince(Integer subjectId, Integer provinceId);

    ValuableBookDTO getFilePathBySubjectAndProvince(Integer subjectId, Integer provinceId);

    void batchCreateFilePath(Integer knowledgeTreeId);
}
