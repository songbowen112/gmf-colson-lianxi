package com.colson.dal.demo;

import com.colson.common.emum.SubjectCodeEnum;
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
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author song
 * @description:
 * t_attachment取资料下载到本地
 * ent_datum_bundle_detail取资料下载到本地
 * 根据文件名筛选
 * @date 2021/12/28 上午10:58
 */
public class AttachmentDatumDemo {

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
            DatumEntityMapper datumEntityMapper = session.getMapper(DatumEntityMapper.class);


            //第五步：调用Mapper接口对象的方法操作数据库
            //补漏百题斩包含：补漏百题斩、考学一点通、考前黄金卷、决胜3小时、考前60分-主观题带背
            String dirName = "补漏百题斩/";
            String rootPath = "/Users/songbowen/Desktop/资料/" + dirName;

            //根据这四个文件名分类：密训  急救  押题  压轴
            List<AttachmentEntity> attachmentEntities = attachmentEntityMapper.selectAttachmentListByFileName();
            System.out.println("------Attachment资料有：" + attachmentEntities.size() + "个");
            List<DatumEntity> datumBundleList = datumEntityMapper.selectDatumBundleListByFileName();
            System.out.println("------Datum资料有：" + datumBundleList.size() + "个");

            Map<String, List<AttachmentEntity>> collect = attachmentEntities.stream().collect(Collectors.groupingBy(AttachmentEntity::getSubjectName));
            Map<String, List<DatumEntity>> collect2 = datumBundleList.stream().collect(Collectors.groupingBy(DatumEntity::getSubjectName));
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
                //处理Attachment文件
                AttachmentDatumDemo.dealAttachment(entryValue, dirPath);
            }
            for (Map.Entry<String, List<DatumEntity>> integerListEntry : collect2.entrySet()) {
                List<DatumEntity> entryValue = integerListEntry.getValue();
                String subjectName = integerListEntry.getKey();
                String subjectCode = SubjectCodeEnum.getCode(subjectName);
                String subjectPath = subjectCode + "_" + subjectName;
                String dirPath = rootPath + subjectPath;

                System.out.println("---------dirPath:" + dirPath);
                File pathFile = new File(dirPath);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                }
                //处理datum文件
                AttachmentDatumDemo.dealDatum(entryValue, dirPath);
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

    private static void dealAttachment(List<AttachmentEntity> list, String dirPath) {
        if (!CollectionUtils.isEmpty(list)) {
            for (AttachmentEntity attachmentEntity : list) {
                if (StringUtils.isEmpty(attachmentEntity.getFileName())) {
                    continue;
                }
                String filePath = dirPath + File.separator + attachmentEntity.getFileName();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(filePath);

                    String path = attachmentEntity.getPrefix() + attachmentEntity.getFileUrl();
                    File newFile = new File(path);
                    if (newFile.exists()) {
                        continue;
                    }
                    downloadFile(path, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static void dealDatum(List<DatumEntity> list, String dirPath) {
        if (!CollectionUtils.isEmpty(list)) {
            for (DatumEntity datumEntity : list) {
                if (StringUtils.isEmpty(datumEntity.getFileUrl()) || StringUtils.isEmpty(datumEntity.getPrefix())) {
                    continue;
                }
                String filePath = dirPath + File.separator + datumEntity.getFileName();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(filePath);

                    String path = datumEntity.getPrefix() + datumEntity.getFileUrl();
                    File newFile = new File(path);
                    if (newFile.exists()) {
                        continue;
                    }
                    downloadFile(path, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
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
