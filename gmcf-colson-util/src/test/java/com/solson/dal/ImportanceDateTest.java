package com.solson.dal;


import com.colson.util.DateUtils;

import java.text.ParseException;

public class ImportanceDateTest {

    private static String date = "2018-6-27 00:00:00";

    private static String date2 = "2020-6-27 00:00:00";

    private static String date3 = "2020-1-1 00:00:00";

    public static void main(String[] args) {
        try {
            System.out.println(DateUtils.datesub(date,DateUtils.getStringDate()));

            System.out.println(DateUtils.datesub(date2,DateUtils.getStringDate()));

            System.out.println(DateUtils.datesub(date3,DateUtils.getStringDate()));


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
