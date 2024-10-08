package com.colson.dal.demo;

import cn.hutool.core.collection.CollectionUtil;
import com.colson.common.emum.SubjectCodeEnum;
import com.colson.dal.model.AttachmentEntity;
import com.colson.util.excel.ExlImport;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.wml.P;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MultiThreadedAttachmentProcessor {

    public static void main(String[] args) {
        // 假设你已经获得了 attachmentEntities
        List<AttachmentEntity> attachmentEntities = null;
        try {
            attachmentEntities = ExlImport.exlList("/Users/songbowen/Desktop/personal/attachment.xlsx", "AttachmentEntity");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // 第一步：调用Mapper接口对象的方法操作数据库
        String rootPath = "/Users/songbowen/Desktop/personal/资料/attachment/";

        if (CollectionUtil.isEmpty(attachmentEntities)) {
            throw new RuntimeException("attachmentEntities is empty");
        }
        // 将附件实体按照SubjectName进行分组
        Map<String, List<AttachmentEntity>> collect = attachmentEntities.stream()
            .collect(Collectors.groupingBy(AttachmentEntity::getSubjectName));

        // 创建固定大小的线程池，线程数可以根据需要调整
        int threadPoolSize = Runtime.getRuntime().availableProcessors();  // 使用CPU核心数作为线程池大小
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        // 为每个 Map.Entry 提交一个任务到线程池
        for (Map.Entry<String, List<AttachmentEntity>> entry : collect.entrySet()) {
            executorService.submit(() -> {
                // 任务逻辑部分
                List<AttachmentEntity> entryValue = entry.getValue();
                String subjectName = entry.getKey();
                String subjectCode = SubjectCodeEnum.getCode(subjectName);
                String subjectPath = subjectCode + "_" + subjectName;
                String dirPath = rootPath + subjectPath;
                System.out.println("---------dirPath:" + dirPath);

                // 创建目录
                File pathFile = new File(dirPath);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                }
                // 处理附件
                dealAttachment(entryValue, dirPath);
            });
        }
        // 关闭线程池，等待所有任务执行完毕
        executorService.shutdown();
        System.out.println("finish");
    }

    private static void dealAttachment(List<AttachmentEntity> list, String dirPath) {
        if (!CollectionUtils.isEmpty(list)) {
            for (AttachmentEntity attachmentEntity : list) {
                if (StringUtils.isEmpty(attachmentEntity.getFileUrl()) || StringUtils.isEmpty(attachmentEntity.getPrefix()) || StringUtils.isEmpty(attachmentEntity.getFileName())) {
                    continue;
                }
                String filePath = dirPath + File.separator + attachmentEntity.getFileName();
                File newFile = new File(filePath);
                if (newFile.exists()) {
                    System.out.println("---------无需处理:" + filePath);
                    continue;
                }
                try(FileOutputStream fos = new FileOutputStream(filePath);) {
                    String path = attachmentEntity.getPrefix() + attachmentEntity.getFileUrl();
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
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS) // 连接超时时间
                .readTimeout(60, TimeUnit.SECONDS)    // 读取超时时间
                .build();

        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            InputStream is = response.body().byteStream();
            byte[] arr = new byte[4096];
            int offset;

            try {
                while ((offset = is.read(arr)) != -1) {
                    out.write(arr, 0, offset);
                }
            } finally {
                out.flush();
            }
        }
    }
}