package com.colson.dal.demo;

import com.alibaba.fastjson.JSON;
import com.colson.common.emum.SubjectCodeEnum;
import com.colson.dal.dao.AttachmentEntityMapper;
import com.colson.dal.model.AttachmentEntity;
import com.colson.dal.model.DatumEntity;
import com.colson.util.excel.ExlImport;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author song
 * @description: t_attachment取资料下载到本地
 * @date 2021/12/28 上午10:58
 */
public class AttachmentDemo4 {

    public static void main(String[] args) {
        try {
            List<AttachmentEntity> attachmentEntities = ExlImport.exlList("/Users/songbowen/Desktop/私活/attachment.xlsx", "AttachmentEntity");

            //第五步：调用Mapper接口对象的方法操作数据库
            String rootPath = "/Users/songbowen/Desktop/私活/资料/attachment/";

            Map<String, List<AttachmentEntity>> collect = attachmentEntities.stream().collect(Collectors.groupingBy(AttachmentEntity::getSubjectName));
            for (Map.Entry<String, List<AttachmentEntity>> integerListEntry : collect.entrySet()) {
                List<AttachmentEntity> entryValue = integerListEntry.getValue();
                String subjectName = integerListEntry.getKey();
                String subjectCode = SubjectCodeEnum.getCode(subjectName);
                String subjectPath = subjectCode + "_" + subjectName;
                String dirPath = rootPath + subjectPath;
                System.out.println("---------dirPath:" + dirPath);
                File pathFile = new File(dirPath);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                }
                dealAttachment(entryValue,dirPath);
            }
            System.out.println("finish");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dealAttachment(List<AttachmentEntity> list, String dirPath) {
        if (!CollectionUtils.isEmpty(list)) {
            for (AttachmentEntity attachmentEntity : list) {
                if (StringUtils.isEmpty(attachmentEntity.getFileUrl()) || StringUtils.isEmpty(attachmentEntity.getPrefix()) || StringUtils.isEmpty(attachmentEntity.getFileName())) {
                    continue;
                }
                String filePath = dirPath + File.separator + attachmentEntity.getFileName();
                try(FileOutputStream fos = new FileOutputStream(filePath);) {
                    String path = attachmentEntity.getPrefix() + attachmentEntity.getFileUrl();
                    File newFile = new File(path);
                    if (newFile.exists()) {
                        System.out.println("---------无需处理:" + path);
                        continue;
                    }
                    System.out.println("---------开始下载:" + path);
                    downloadFile(path, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
