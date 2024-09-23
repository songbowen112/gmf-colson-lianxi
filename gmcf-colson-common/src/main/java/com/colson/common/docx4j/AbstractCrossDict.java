package com.colson.common.docx4j;

import org.apache.commons.io.IOUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 16:34
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public abstract class AbstractCrossDict implements CrossDictBuilder, WordMLPackageWare, CrossDictHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected WordprocessingMLPackage wordMLPackage;

    protected WordprocessingMLPackage bak;

    private List<CrossDictHandler> handlers;

    public AbstractCrossDict(String fileLocation) throws Exception {
        loadTemplate(fileLocation);
    }

    public AbstractCrossDict(InputStream inputStream) throws Exception {
        loadTemplate(inputStream);
    }

    /**
     * 空参构造, 用于clone对象
     */
    protected AbstractCrossDict() {

    }

    @Override
    public void loadTemplate(String fileLocation) throws Exception {
        try {
            logger.debug("load from path: {}", fileLocation);
            this.wordMLPackage = WordprocessingMLPackage.load(new File(fileLocation));
            this.bak = (WordprocessingMLPackage) this.wordMLPackage.clone();
        } catch (Docx4JException e) {
            throw new Exception(e);
        }
        logger.debug("load success");
    }

//    @Override
//    public void loadTemplate(InputStream inputStream) throws Exception {
//        this.wordMLPackage = WordprocessingMLPackage.load(inputStream);
//        this.bak = (WordprocessingMLPackage) this.wordMLPackage.clone();
//    }

    @Override
    public void loadTemplate(InputStream inputStream) throws Exception {
        // 将输入流保存为临时文件
        File tempFile = File.createTempFile("temp", ".docx");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            IOUtils.copy(inputStream, fos);
        }

        // 使用临时文件加载
        this.wordMLPackage = WordprocessingMLPackage.load(tempFile);
        this.bak = (WordprocessingMLPackage) this.wordMLPackage.clone();
    }

    @Override
    public void addHandler(CrossDictHandler handler) {
        if (null == this.handlers) {
            handlers = new ArrayList<>();
        }
        if (handler instanceof CrossDictBuilder) {
            return;
        }
        handlers.add(handler);
    }

    @Override
    public void exportFile(String fileLocation) throws Exception {
        File file = new File(fileLocation);
        if (file.isDirectory()) {
            throw new Exception("export file is directory");
        }
        this.wordMLPackage.save(file);
    }

    @Override
    public void exportFile(OutputStream outputStream) throws Exception {
        if (null == outputStream) {
            throw new Exception("outputStream is null");
        }
        this.wordMLPackage.save(outputStream);
    }

    @Override
    public void clearAll() {
        this.wordMLPackage = (WordprocessingMLPackage) this.bak.clone();
        this.handlers.clear();
    }

    /**
     * 克隆一个对象, 重复使用模板
     *
     * @param obj 被拷贝的对象
     * @return
     */
    protected CrossDictBuilder clone(AbstractCrossDict obj) {
        if (obj instanceof AbstractCrossDict) {
            AbstractCrossDict copy = obj;
            this.wordMLPackage = (WordprocessingMLPackage) copy.wordMLPackage.clone();
            this.bak = this.wordMLPackage;
        }
        return this;
    }

    @Override
    public void handle() throws Exception {
        logger.debug("handle word handler.");
        if (null != handlers && !handlers.isEmpty()) {
            for (CrossDictHandler handler : this.handlers) {
                handler.handle();
            }
        } else {
            logger.error("handles is zero");
        }
    }

    @Override
    public WordprocessingMLPackage getWordMLPackage() {
        return this.wordMLPackage;
    }
}
