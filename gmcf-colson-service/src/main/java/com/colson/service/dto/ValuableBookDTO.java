package com.sunlands.analyze.dto;

import lombok.Data;

@Data
public class ValuableBookDTO {

    /**
     * 上传sfs文件服务的地址
     */
    private String sfsPath;

    /**
     * 上传sfs文件服务地址短链
     */
    private String sfsShortPath;
}
