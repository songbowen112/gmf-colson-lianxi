package com.colson.common.bean;

import lombok.Data;

@Data
public class FilePathBean {

    /**
     * 一级目录
     */
    private String firstDir;

    /**
     * 二级目录
     */
    private String secondDir;

    /**
     * 文件名
     */
    private String fullFileName;


}
