package com.colson.common.bean;

public class AttendanceRecordBean {

    /**
     * 出勤日期
     */
    private String date;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 加班小时数
     */
    private Integer overtimeHours;

    /**
     * 事假小时数
     */
    private Integer leaveHours;

    /**
     * 缺勤小时数
     */
    private Integer absenteeismHours;

    /**
     * 缺卡次数
     */
    private Integer absenteeismTimes;

    /**
     * 备注
     */
    private String remark;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(Integer overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public Integer getLeaveHours() {
        return leaveHours;
    }

    public void setLeaveHours(Integer leaveHours) {
        this.leaveHours = leaveHours;
    }

    public Integer getAbsenteeismHours() {
        return absenteeismHours;
    }

    public void setAbsenteeismHours(Integer absenteeismHours) {
        this.absenteeismHours = absenteeismHours;
    }

    public Integer getAbsenteeismTimes() {
        return absenteeismTimes;
    }

    public void setAbsenteeismTimes(Integer absenteeismTimes) {
        this.absenteeismTimes = absenteeismTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
