/**
  * Copyright 2020 bejson.com 
  */
package com.colson.common.bean;

import java.util.Date;

/**
 * Auto-generated: 2020-07-01 13:48:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Periods {

    private Date ClockIn;
    private Date ClockOut;
    private PunchIn PunchIn;
    private PunchOut PunchOut;
    private int WorkingHours;
    public void setClockIn(Date ClockIn) {
         this.ClockIn = ClockIn;
     }
     public Date getClockIn() {
         return ClockIn;
     }

    public void setClockOut(Date ClockOut) {
         this.ClockOut = ClockOut;
     }
     public Date getClockOut() {
         return ClockOut;
     }

    public void setPunchIn(PunchIn PunchIn) {
         this.PunchIn = PunchIn;
     }
     public PunchIn getPunchIn() {
         return PunchIn;
     }

    public void setPunchOut(PunchOut PunchOut) {
         this.PunchOut = PunchOut;
     }
     public PunchOut getPunchOut() {
         return PunchOut;
     }

    public void setWorkingHours(int WorkingHours) {
         this.WorkingHours = WorkingHours;
     }
     public int getWorkingHours() {
         return WorkingHours;
     }

}