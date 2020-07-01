/**
  * Copyright 2020 bejson.com 
  */
package com.colson.web.bean;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-07-01 13:48:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ReturnObject {

    private String UserId;
    private String UserName;
    private String ScheduleId;
    private Date ScheduleDate;
    private String ScheduleName;
    private int AttendanceType;
    private boolean IsWorkingDay;
    private String AccountId;
    private int DeptId;
    private String DeptName;
    private List<Periods> Periods;
    private List<Punchs> Punchs;
    private List<Vacations> Vacations;
    private String Fieldworks;
    private List<String> Labels;
    public void setUserId(String UserId) {
         this.UserId = UserId;
     }
     public String getUserId() {
         return UserId;
     }

    public void setUserName(String UserName) {
         this.UserName = UserName;
     }
     public String getUserName() {
         return UserName;
     }

    public void setScheduleId(String ScheduleId) {
         this.ScheduleId = ScheduleId;
     }
     public String getScheduleId() {
         return ScheduleId;
     }

    public void setScheduleDate(Date ScheduleDate) {
         this.ScheduleDate = ScheduleDate;
     }
     public Date getScheduleDate() {
         return ScheduleDate;
     }

    public void setScheduleName(String ScheduleName) {
         this.ScheduleName = ScheduleName;
     }
     public String getScheduleName() {
         return ScheduleName;
     }

    public void setAttendanceType(int AttendanceType) {
         this.AttendanceType = AttendanceType;
     }
     public int getAttendanceType() {
         return AttendanceType;
     }

    public void setIsWorkingDay(boolean IsWorkingDay) {
         this.IsWorkingDay = IsWorkingDay;
     }
     public boolean getIsWorkingDay() {
         return IsWorkingDay;
     }

    public void setAccountId(String AccountId) {
         this.AccountId = AccountId;
     }
     public String getAccountId() {
         return AccountId;
     }

    public void setDeptId(int DeptId) {
         this.DeptId = DeptId;
     }
     public int getDeptId() {
         return DeptId;
     }

    public void setDeptName(String DeptName) {
         this.DeptName = DeptName;
     }
     public String getDeptName() {
         return DeptName;
     }

    public void setPeriods(List<Periods> Periods) {
         this.Periods = Periods;
     }
     public List<Periods> getPeriods() {
         return Periods;
     }

    public void setPunchs(List<Punchs> Punchs) {
         this.Punchs = Punchs;
     }
     public List<Punchs> getPunchs() {
         return Punchs;
     }


    public void setFieldworks(String Fieldworks) {
         this.Fieldworks = Fieldworks;
     }
     public String getFieldworks() {
         return Fieldworks;
     }

    public void setLabels(List<String> Labels) {
         this.Labels = Labels;
     }
     public List<String> getLabels() {
         return Labels;
     }

    public boolean isWorkingDay() {
        return IsWorkingDay;
    }

    public void setWorkingDay(boolean workingDay) {
        IsWorkingDay = workingDay;
    }

    public List<Vacations> getVacations() {
        return Vacations;
    }

    public void setVacations(List<Vacations> vacations) {
        Vacations = vacations;
    }
}