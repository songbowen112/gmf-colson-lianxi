package com.sunlands.analyze.docx4j;

import com.shangde.common.exception.BaseException;
import com.shangde.common.exception.ExceptionCategory;
import com.sunlands.common.util.PathUtil;
import com.sunlands.common.util.fs.FileSystemFactory;
import com.sunlands.common.util.fs.SunlandsFileSystem;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.surround.query.SrndTermQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/27 14:18
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class SfsUploadHandler extends AbstractCrossDictHandler {

    private Logger logger = LoggerFactory.getLogger(SfsUploadHandler.class);

    private String fileName;

    private String knowledgeTreeId;

    private String examProvinceId;

    private SunlandsFileSystem sunlandsFileSystem;

    private String basePath;

    private String charsetName;

    private String sfsPath;

    /**
     * 构造方法
     * @param ware              CrossDictBuilder对象
     * @param fileName          文件名称
     * @param knowledgeTreeId   知识树ID
     * @param examProvinceId    考试省份ID
     * @param sunlandsFileSystem          sfs工具类
     * @param basePath          sfs上传基路径
     * @param charsetName       文件名getBytes编码
     */
    public SfsUploadHandler(WordMLPackageWare ware, String fileName, String knowledgeTreeId, String examProvinceId, SunlandsFileSystem sunlandsFileSystem, String basePath, String charsetName) {
        super(ware);
        this.fileName = fileName;
        this.knowledgeTreeId = knowledgeTreeId;
        this.examProvinceId = examProvinceId;
        this.sunlandsFileSystem = sunlandsFileSystem;
        this.basePath = basePath;
        this.charsetName = charsetName;
    }

    public String getSfsPath() {
        return this.sfsPath;
    }

    @Override
    public void handle() throws Exception {
        FileInputStream in = null;
        this.ware.getWordMLPackage().save(new File(fileName));
        // 将上传到服务器中的文件读取出来存入sfs文件服务器
        try {
            File tmpFile = getFile(fileName);

            in = new FileInputStream(tmpFile);
            String fileMd5 = DigestUtils.md5Hex(in);
            String uploadPath = basePath + fileMd5 + "/" + fileName;
            String sfsUrl = sunlandsFileSystem.createFile(uploadPath, tmpFile);
            if (StringUtils.isNotEmpty(sfsUrl)) {
                logger.info("upload \"{}\" to sfs: {} success, path: \"{}\"", fileName, sfsUrl, uploadPath);
            } else {
                logger.error("upload \"{}\" to sfs: {} failure", fileName, sfsUrl);
            }
            this.sfsPath = sfsUrl;
        } catch (Exception e) {
            throw new BaseException(ExceptionCategory.Illegal_Parameter,
                    "将上传到服务器中的文件读取出来存入sfs文件服务器时出现错误！" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            deleteFile(fileName);
        }
    }

    /**
     * 删除文件
     *
     * @param fileName
     */
    private void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 获取保存在服务器中的文件
     *
     * @param fileName
     * @return
     */
    private File getFile(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new Exception(fileName + "不存在");
        }
        return file;
    }
}
