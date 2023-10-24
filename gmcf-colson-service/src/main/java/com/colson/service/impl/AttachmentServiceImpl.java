package com.colson.service.impl;

import com.colson.dal.dao.AttachmentEntityMapper;
import com.colson.dal.model.AttachmentEntity;
import com.colson.service.AttachmentService;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Resource
    AttachmentEntityMapper attachmentEntityMapper;

    @Override
    public void queryAttachmentList(List<Integer> roundIdList) {
        String dirPath = "/Users/songbowen/attachment/";

        List<AttachmentEntity> attachmentEntities = attachmentEntityMapper.selectAttachmentList();
        if (CollectionUtils.isEmpty(attachmentEntities)) {
            return;
        }
        //资料根据轮次分组
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

    private static void downloadFile(String downloadUrl, OutputStream out) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(new Request.Builder().url(downloadUrl).build());
        try (Response execute = call.execute()) {
            if (execute.isSuccessful()) {
                try (InputStream is = Objects.requireNonNull(execute.body(), "下载文件为空").byteStream()) {
                    if (null != is) {
                        byte[] arr = new byte[10240];
                        int offset = 0;
                        while ((offset = is.read(arr)) != -1) {
                            out.write(arr, 0, offset);
                        }
                    }
                }
            } else {
            }
        }
    }
}
