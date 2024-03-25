package com.colson.dal.demo;

import com.colson.dal.dao.AttachmentEntityMapper;
import com.colson.dal.dao.PaymentEntityMapper;
import com.colson.dal.model.AttachmentEntity;
import com.colson.dal.model.PaymentEntity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author song
 * @description: t_attachment取资料下载到本地
 * @date 2021/12/28 上午10:58
 */
public class AttachmentDemo {

    public static void main(String[] args) {
        SqlSession session = null;
        try {
            //第一步：读取mybatis-config.xml配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            //第二步：构建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //第三步：打开SqlSession
            session = sqlSessionFactory.openSession();

            //第四步：获取Mapper接口对象
            AttachmentEntityMapper attachmentEntityMapper = session.getMapper(AttachmentEntityMapper.class);

            //第五步：调用Mapper接口对象的方法操作数据库
            String rootPath = "/Users/songbowen/attachment/";

            List<AttachmentEntity> attachmentEntities = attachmentEntityMapper.selectAttachmentList();
            Map<Integer, List<AttachmentEntity>> collect = attachmentEntities.stream().collect(Collectors.groupingBy(AttachmentEntity::getRoundId));
            for (Map.Entry<Integer, List<AttachmentEntity>> integerListEntry : collect.entrySet()) {
                Integer roundId = integerListEntry.getKey();
                List<AttachmentEntity> entryValue = integerListEntry.getValue();
                String roundName = entryValue.get(0).getRoundName();
                String dirPath = rootPath + roundId + "_" + roundName;
                System.out.println("---------dirPath:" + dirPath);
                File file = new File(dirPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                for (AttachmentEntity attachmentEntity : entryValue) {
                    if (StringUtils.isEmpty(attachmentEntity.getFileName())) {
                        continue;
                    }
                    String filePath = dirPath + File.separator + attachmentEntity.getFileName();
                    FileOutputStream fos = new FileOutputStream(filePath);

                    try {
                        String path = attachmentEntity.getPrefix() + attachmentEntity.getFileUrl();
                        File newFile = new File(path);
                        if (newFile.exists()) {
                            continue;
                        }
                        downloadFile(path, fos);
                    } finally {
                        if (fos != null) {
                            fos.close();
                        }
                    }

                }
            }
            System.out.println("finish");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
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
