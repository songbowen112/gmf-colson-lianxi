package com.colson.service.impl;


import com.colson.dal.dao.TikuCommonDAO;
import com.colson.service.TikuCommonService;
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
    public List<Integer> getAllSubjectId() {
        return tikuCommonDAO.getAllSubjectId();
    }


}
