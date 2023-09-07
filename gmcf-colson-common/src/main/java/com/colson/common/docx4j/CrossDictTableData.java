package com.colson.common.docx4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 18:43
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class CrossDictTableData {

    public CrossDictTableData(String level, String require) {
        this.level = level;
        this.require = require;
    }

    public CrossDictTableData() {
        this.level = "-";
        this.require = "-";
    }

    public void setPerRowDataList(List<PerRowData> perRowDataList) {
        this.perRowDataList = perRowDataList;
    }

    /**
     * 知识点层级
     */
    private String level;

    /**
     * 大纲要求
     */
    private String require;

    /**
     * 每行数据
     */
    private List<PerRowData> perRowDataList;

    /**
     * 每行数据
     */
    public class PerRowData {

        /**
         * 题型名称
         */
        private String typeName;

        /**
         * 考察次数
         */
        private String investigateTimes;

        /**
         * 考察次数占比
         */
        private String investigateTimesProportion;

        /**
         * 考察分值
         */
        private String investigateScore;

        /**
         * 考察分值占比
         */
        private String investigateScoreProportion;

        public PerRowData(String typeName, String investigateTimes, String investigateTimesProportion, String investigateScore, String investigateScoreProportion) {
            this.typeName = typeName;
            this.investigateTimes = null == investigateTimes ? "-" : investigateTimes;
            this.investigateTimesProportion = null == investigateTimesProportion ? "-" : investigateTimesProportion;
            this.investigateScore = null == investigateScore ? "-" : investigateScore;
            this.investigateScoreProportion = null == investigateScoreProportion ? "-" : investigateScoreProportion;
        }

        public PerRowData() {
            this.typeName = "-";
            this.investigateTimes = "-";
            this.investigateScore = "-";
            this.investigateScoreProportion = "-";
            this.investigateTimesProportion = "-";
        }

        public String getTypeName() {
            return typeName;
        }

        public String getInvestigateTimes() {
            return investigateTimes;
        }

        public String getInvestigateTimesProportion() {
            return investigateTimesProportion;
        }

        public String getInvestigateScore() {
            return investigateScore;
        }

        public String getInvestigateScoreProportion() {
            return investigateScoreProportion;
        }
    }

    public String getLevel() {
        return level;
    }

    public String getRequire() {
        return require;
    }

    public List<PerRowData> getPerRowDataList() {
        // 给出默认值
        if (null == perRowDataList) {
            this.perRowDataList = new ArrayList<>();
        }
        while (this.perRowDataList.size() < 4) {
            this.perRowDataList.add(new PerRowData());
        }
        return perRowDataList;
    }
}
