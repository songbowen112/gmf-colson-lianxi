package com.colson.service.impl;

import com.colson.dal.dao.AttachmentEntityMapper;
import com.colson.dal.model.AttachmentEntity;
import com.colson.service.AttachmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Resource
    AttachmentEntityMapper attachmentEntityMapper;

    @Override
    public void queryAttachmentList(List<Integer> roundIdList) {
        String dirPath = "/Users/songbowen/attachment/";

        List<AttachmentEntity> attachmentEntities = attachmentEntityMapper.selectAttachmentList();
        Map<Integer, List<AttachmentEntity>> collect = attachmentEntities.stream().collect(Collectors.groupingBy(AttachmentEntity::getRoundId));
        for (Map.Entry<Integer, List<AttachmentEntity>> integerListEntry : collect.entrySet()) {
            Integer roundId = integerListEntry.getKey();
            List<AttachmentEntity> entryValue = integerListEntry.getValue();
            File filePath = new File(dirPath + roundId);
            if (!filePath.exists()) {
                filePath.mkdir();
            }
            System.out.println(roundId);
            for (AttachmentEntity attachmentEntity : entryValue) {
                if (StringUtils.isEmpty(attachmentEntity.getFileName())) {
                    continue;
                }
                String path = attachmentEntity.getPrefix()+attachmentEntity.getFileUrl();
                System.out.println(path);
            }
        }
    }
}
