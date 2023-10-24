package com.colson.common.docx4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节知识
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 17:54
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class CrossDictChapterKnowledge {

    /**
     * 章节级别: 1,2,3,4,5...
     */
    private int chapterLevel;

    /**
     * 章节名称
     */
    private String chapterName;

    /**
     * 标签
     */
    private String label;

    /**
     * 思维导图图片
     */
    private byte[] imageBytes;

    /**
     * 章节下的表数据
     */
    private CrossDictTableData tableDataList;

    /**
     * 章节描述
     */
    private List<String> chapterDescriptions = new ArrayList<>();

    private List<CrossDictChapterKnowledge> subChapters = new ArrayList<>();

    public CrossDictChapterKnowledge(int chapterLevel, String chapterName, byte[] imageBytes, CrossDictTableData tableDataList, List<String> chapterDescriptions) {
        this.chapterLevel = chapterLevel;
        this.chapterName = chapterName;
        this.imageBytes = imageBytes;
        this.tableDataList = tableDataList;
        this.chapterDescriptions = chapterDescriptions;
    }

    public CrossDictChapterKnowledge(int chapterLevel, String chapterName) {
        this.chapterLevel = chapterLevel;
        this.chapterName = chapterName;
    }

    public CrossDictChapterKnowledge(int chapterLevel, String chapterName, CrossDictTableData tableDataList) {
        this.chapterLevel = chapterLevel;
        this.chapterName = chapterName;
        this.tableDataList = tableDataList;
    }

    public CrossDictChapterKnowledge(int chapterLevel, String chapterName, String label, CrossDictTableData tableDataList) {
        this.chapterLevel = chapterLevel;
        this.chapterName = chapterName;
        this.label = label;
        this.tableDataList = tableDataList;
    }

    public int getChapterLevel() {
        return chapterLevel;
    }

    public String getLabel() {
        return label;
    }

    public String getChapterName() {
        return chapterName;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public CrossDictTableData getTableDataList() {
        // 给出默认值
        if (null == tableDataList) {
            tableDataList = new CrossDictTableData();
        }
        return tableDataList;
    }

    public List<String> getChapterDescriptions() {
        return chapterDescriptions;
    }

    public List<CrossDictChapterKnowledge> getSubChapters() {
        return subChapters;
    }

    public void addSubChapter(CrossDictChapterKnowledge subChapter) {
        this.subChapters.add(subChapter);
    }

    public void removeSubChapter(CrossDictChapterKnowledge subChapter) {
        this.subChapters.remove(subChapter);
    }

    public void removeSubChapter(int index) {
        this.subChapters.remove(index);
    }

    public void addParagraph(String paragraph) {
        this.chapterDescriptions.add(paragraph);
    }
}
