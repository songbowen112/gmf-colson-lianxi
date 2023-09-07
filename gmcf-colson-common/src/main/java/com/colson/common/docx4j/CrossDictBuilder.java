package com.sunlands.analyze.docx4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 通关宝典建造者
 *
 * 通关宝典分为3个部分:
 * 第一部分为首页:
 *  首页部分也包含一些动态数据, 在创建首页时, 需要给出一些必要数据
 * 第二部分为目录:
 *  目录部分主要是刷新作用, 更新目录文档, 但是目录部分不能成功更新页码, 也就是说会创建一个不包含页码的目录
 * 第三部分为内容:
 *  内容主要是以章节为单位的集合, 章节中包含有思维导图, 章节表格, 章节描述, 以及子目录,
 *  子目录下包含有 表格, 内容, 子目录.
 *  子目录可以无限接下去, 程序是否需要递归根据需要设定
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 16:18
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public interface CrossDictBuilder extends WordMLPackageWare, CrossDictHandler, Serializable {

    /**
     * 加载docx模板, 根据模板中的特殊定位填充特定数据
     *
     * @param fileLocation  文件位置
     * @throws Exception
     */
    void loadTemplate(String fileLocation) throws Exception;

    /**
     * 加载docx模板, 根据模板中的特殊定位填充特定数据
     *
     * @param inputStream  输入流
     * @throws Exception
     */
    void loadTemplate(InputStream inputStream) throws Exception;

    /**
     * 添加处理器
     * @param handler
     */
    void addHandler(CrossDictHandler handler);

    /**
     * 输出到文件目录
     * @param fileLocation
     * @throws Exception
     */
    void exportFile(String fileLocation) throws Exception;

    /**
     * 输出到流中
     * @param outputStream
     * @throws Exception
     */
    void exportFile(OutputStream outputStream) throws Exception;

    /**
     * 清除所有信息, 保留模板继续使用
     */
    void clearAll();
}
