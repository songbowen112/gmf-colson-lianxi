package com.colson.dal.model;

public class DatumEntity {

    private Integer id;

    private Integer packageId;

    private String fileName;

    private String prefix;

    private String fileUrl;

    private Integer proj2Id;

    private String proj2Name;

    private Integer subjectId;

    private String subjectName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getProj2Id() {
        return proj2Id;
    }

    public void setProj2Id(Integer proj2Id) {
        this.proj2Id = proj2Id;
    }

    public String getProj2Name() {
        return proj2Name;
    }

    public void setProj2Name(String proj2Name) {
        this.proj2Name = proj2Name;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
