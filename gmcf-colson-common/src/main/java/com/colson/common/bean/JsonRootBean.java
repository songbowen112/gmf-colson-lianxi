/**
  * Copyright 2020 bejson.com 
  */
package com.colson.common.bean;
import java.util.List;

/**
 * Auto-generated: 2020-07-01 13:48:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private int Total;
    private List<ReturnObject> ReturnObject;
    private boolean Result;
    private int Code;
    private String Description;
    public void setTotal(int Total) {
         this.Total = Total;
     }
     public int getTotal() {
         return Total;
     }

    public void setReturnObject(List<ReturnObject> ReturnObject) {
         this.ReturnObject = ReturnObject;
     }
     public List<ReturnObject> getReturnObject() {
         return ReturnObject;
     }

    public void setResult(boolean Result) {
         this.Result = Result;
     }
     public boolean getResult() {
         return Result;
     }

    public void setCode(int Code) {
         this.Code = Code;
     }
     public int getCode() {
         return Code;
     }

    public void setDescription(String Description) {
         this.Description = Description;
     }
     public String getDescription() {
         return Description;
     }

}