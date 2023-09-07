package com.colson.common.docx4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 构造方法
     * @param ware              CrossDictBuilder对象
     * @param fileName          文件名称
     */
    public SfsUploadHandler(WordMLPackageWare ware, String fileName) {
        super(ware);
        this.fileName = fileName;
    }

    @Override
    public void handle() throws Exception {
        FileInputStream in = null;
        this.ware.getWordMLPackage().save(new File(fileName));
        // 将上传到服务器中的文件读取出来存入sfs文件服务器
        try {
            File tmpFile = getFile(fileName);

        } catch (Exception e) {
            throw new RuntimeException(
                    "将上传到服务器中的文件读取出来存入sfs文件服务器时出现错误！" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
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
