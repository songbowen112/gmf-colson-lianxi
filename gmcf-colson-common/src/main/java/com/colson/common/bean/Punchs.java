/**
  * Copyright 2020 bejson.com 
  */
package com.colson.common.bean;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-07-01 13:48:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Punchs {

    private Date PunchTime;
    private int ClientType;
    private String IPAddress;
    private String Lat;
    private String Lng;
    private String LAddress;
    private boolean Fieldwork;
    private List<String> Photos;
    private String Remark;
    private int StatusLabel;
    private boolean LocError;
    private String ErrorAddress;
    public void setPunchTime(Date PunchTime) {
         this.PunchTime = PunchTime;
     }
     public Date getPunchTime() {
         return PunchTime;
     }

    public void setClientType(int ClientType) {
         this.ClientType = ClientType;
     }
     public int getClientType() {
         return ClientType;
     }

    public void setIPAddress(String IPAddress) {
         this.IPAddress = IPAddress;
     }
     public String getIPAddress() {
         return IPAddress;
     }

    public void setLat(String Lat) {
         this.Lat = Lat;
     }
     public String getLat() {
         return Lat;
     }

    public void setLng(String Lng) {
         this.Lng = Lng;
     }
     public String getLng() {
         return Lng;
     }

    public void setLAddress(String LAddress) {
         this.LAddress = LAddress;
     }
     public String getLAddress() {
         return LAddress;
     }

    public void setFieldwork(boolean Fieldwork) {
         this.Fieldwork = Fieldwork;
     }
     public boolean getFieldwork() {
         return Fieldwork;
     }

    public void setPhotos(List<String> Photos) {
         this.Photos = Photos;
     }
     public List<String> getPhotos() {
         return Photos;
     }

    public void setRemark(String Remark) {
         this.Remark = Remark;
     }
     public String getRemark() {
         return Remark;
     }

    public void setStatusLabel(int StatusLabel) {
         this.StatusLabel = StatusLabel;
     }
     public int getStatusLabel() {
         return StatusLabel;
     }

    public void setLocError(boolean LocError) {
         this.LocError = LocError;
     }
     public boolean getLocError() {
         return LocError;
     }

    public void setErrorAddress(String ErrorAddress) {
         this.ErrorAddress = ErrorAddress;
     }
     public String getErrorAddress() {
         return ErrorAddress;
     }

}