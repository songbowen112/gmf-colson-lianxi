package com.colson.dal.demo;

import com.colson.dal.dao.AttachmentEntityMapper;
import com.colson.dal.dao.DatumEntityMapper;
import com.colson.dal.model.AttachmentEntity;
import com.colson.dal.model.DatumEntity;
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
 * @description: ent_datum_bundle_detail取资料下载到本地
 * @date 2023/10/24 上午10:58
 */
public class DatumBundleDemo {

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
            DatumEntityMapper datumEntityMapper = session.getMapper(DatumEntityMapper.class);

            //第五步：调用Mapper接口对象的方法操作数据库
            String rootPath = "/Users/songbowen/datum/";


            List<DatumEntity> datumEntities = datumEntityMapper.selectDatumBundleList();
            Map<Integer, List<DatumEntity>> collect = datumEntities.stream().collect(Collectors.groupingBy(DatumEntity::getProj2Id));
            for (Map.Entry<Integer, List<DatumEntity>> integerListEntry : collect.entrySet()) {
                Integer proj2Id = integerListEntry.getKey();
                List<DatumEntity> entryValue = integerListEntry.getValue();
                String proj2Name = entryValue.get(0).getProj2Name();
                String dirPath = rootPath + proj2Id + "_" + proj2Name;
                System.out.println("---------dirPath:" + dirPath);
                File file = new File(dirPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                for (DatumEntity datumEntity : entryValue) {
                    if (StringUtils.isEmpty(datumEntity.getFileName())) {
                        continue;
                    }
                    String filePath = dirPath + File.separator + datumEntity.getFileName();
                    FileOutputStream fos = new FileOutputStream(filePath);

                    try {
                        String path = datumEntity.getPrefix() + datumEntity.getFileUrl();
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
