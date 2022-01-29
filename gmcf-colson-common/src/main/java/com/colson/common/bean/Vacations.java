package com.colson.common.bean;

import java.util.Date;

/**
 * Auto-generated: 2020-07-01 17:10:20
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Vacations {

    private String VacationId;
    private Date BeginTime;
    private Date EndTime;
    private int HourSpan;
    private int HourDirection;
    private int VacationType;
    private String Label;
    private long ProcInstId;
    private String ProcFolio;
    private String ProcUrl;
    public void setVacationId(String VacationId) {
        this.VacationId = VacationId;
    }
    public String getVacationId() {
        return VacationId;
    }

    public void setBeginTime(Date BeginTime) {
        this.BeginTime = BeginTime;
    }
    public Date getBeginTime() {
        return BeginTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }
    public Date getEndTime() {
        return EndTime;
    }

    public void setHourSpan(int HourSpan) {
        this.HourSpan = HourSpan;
    }
    public int getHourSpan() {
        return HourSpan;
    }

    public void setHourDirection(int HourDirection) {
        this.HourDirection = HourDirection;
    }
    public int getHourDirection() {
        return HourDirection;
    }

    public void setVacationType(int VacationType) {
        this.VacationType = VacationType;
    }
    public int getVacationType() {
        return VacationType;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }
    public String getLabel() {
        return Label;
    }

    public void setProcInstId(long ProcInstId) {
        this.ProcInstId = ProcInstId;
    }
    public long getProcInstId() {
        return ProcInstId;
    }

    public void setProcFolio(String ProcFolio) {
        this.ProcFolio = ProcFolio;
    }
    public String getProcFolio() {
        return ProcFolio;
    }

    public void setProcUrl(String ProcUrl) {
        this.ProcUrl = ProcUrl;
    }
    public String getProcUrl() {
        return ProcUrl;
    }

}