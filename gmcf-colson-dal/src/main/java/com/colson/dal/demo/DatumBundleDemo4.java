package com.colson.dal.demo;

import com.alibaba.fastjson.JSON;
import com.colson.common.emum.SubjectCodeEnum;
import com.colson.dal.dao.DatumEntityMapper;
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
 * @description: ent_datum_bundle_detail取资料下载到本地
 * @date 2023/10/24 上午10:58
 */
public class DatumBundleDemo4 {

    public static void main(String[] args) {
        try {
            List<DatumEntity> datumEntities = ExlImport.exlList("/Users/songbowen/Desktop/personal/datum.xlsx", "DatumEntity");
            System.out.println(JSON.toJSONString(datumEntities));

            //第五步：调用Mapper接口对象的方法操作数据库
            String rootPath = "/Users/songbowen/Desktop/personal/资料/datum/";

            Map<String, List<DatumEntity>> collect = datumEntities.stream().collect(Collectors.groupingBy(DatumEntity::getSubjectName));
            for (Map.Entry<String, List<DatumEntity>> integerListEntry : collect.entrySet()) {
                String subjectName = integerListEntry.getKey();
                String subjectCode = SubjectCodeEnum.getCode(subjectName);
                String subjectPath = subjectCode + "_" + subjectName;
                String dirPath = rootPath + subjectPath;
                System.out.println("---------dirPath:" + dirPath);
                File pathFile = new File(dirPath);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                }
                List<DatumEntity> entryValue = integerListEntry.getValue();
                dealDatum(entryValue,dirPath);
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

    private static void dealDatum(List<DatumEntity> list, String dirPath) {
        if (!CollectionUtils.isEmpty(list)) {
            for (DatumEntity datumEntity : list) {
                if (StringUtils.isEmpty(datumEntity.getFileUrl()) || StringUtils.isEmpty(datumEntity.getPrefix()) || StringUtils.isEmpty(datumEntity.getFileName())) {
                    continue;
                }
                String filePath = dirPath + File.separator + datumEntity.getFileName();
                File newFile = new File(filePath);
                if (newFile.exists()) {
                    System.out.println("---------无需处理:" + filePath);
                    continue;
                }
                try(FileOutputStream fos = new FileOutputStream(filePath)) {
                    String path = datumEntity.getPrefix() + datumEntity.getFileUrl();
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
