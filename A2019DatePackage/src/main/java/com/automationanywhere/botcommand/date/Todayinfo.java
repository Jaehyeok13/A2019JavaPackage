/*
 * Copyright (c) 2019 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */
/**
 *
 */
package com.automationanywhere.botcommand.date;

import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;

/**
 * @author Raj Singh Sisodia
 *
 */
@BotCommand
@CommandPkg(label="Todayinfo", name="Todayinfo", description="TodayInfo", icon="pkg.svg",
        node_label="요일 출력",
        return_type=STRING, return_label="Assign the output to variable", return_required=true)
public class Todayinfo {

    Date now = new Date();
    Calendar cal = Calendar.getInstance();

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월 dd일 HH시mm분ss초",Locale.KOREA);
    SimpleDateFormat format3 = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);

    //private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");

    @Execute

    //   public Value<String> action(@Idx(index = "1", type = TEXT) @Pkg(label = "First string", default_value_type = STRING) @NotEmpty String firstString,
    //                            @Idx(index = "2", type = TEXT) @Pkg(label = "Second string") @NotEmpty String se condString) {
    public StringValue action(){
   /*
        if("".equals(firstString.trim()))
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "firstString"));

        if("".equals(secondString.trim()))
            throw new BotCommandException(MESSAGES.getString("emptyInputString", "secondString"));
    */

        String day1 = format1.format(now.getTime());
        String day2 = format2.format(now.getTime());
        String day3 = format3.format(now.getTime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String sYear = String.format("%d",year);
        String sMonth = String.format("%02d",month);
        String sDay = String.format("%02d", day);

        char dayOfWeek = DayofDate(year, month, day);

        String result = day3 + "\n/" + sYear + "\n/" + sMonth + "\n/" + sDay + "\n/" + dayOfWeek;
        System.out.println(result);
        return new StringValue(result);

    }

    public  int MonthDays(int year, int month) {
        int dayOfMonth = 0;
        //윤년일 조건
        Boolean isLeapYear = ((year % 4 == 0) && (year % 100 !=0)) || year % 400 == 0;
        //31일 일 조건
        Boolean is31 = (month == 1) || (month == 3) || (month == 5) || (month == 7)
                || (month == 8) || (month == 10) || (month == 12);

        if (isLeapYear && month == 2) {
            dayOfMonth = 29;
        } else if (!isLeapYear && month == 2) {
            dayOfMonth = 28;
        } else if (is31) {
            dayOfMonth = 31;
        } else {
            dayOfMonth = 30;
        }
        return dayOfMonth;
    }

    public char DayofDate(int year, int month, int day){
        char dayOfWeek = 0;
        int totalDay = 0;
        for(int i = 1900; i <= year; i++) {
            //입력 년도 이전 까지는 12월 까지 다 더함
            if (i < year) {
                for(int j = 1; j <= 12; j++) {
                    totalDay += MonthDays(i, j);
                }
                //입력 년도는 입력 월 이전까지 더함
            } else {
                for(int j = 1; j < month; j++) {
                    totalDay += MonthDays(i, j);
                }
            }
        }
        //나머지 일 수를 구해진 전체 일수에 더함
        totalDay += day;
        //기준일인 1900년 1월 1일이 월요일이므로
        switch (totalDay % 7) {
            case 0:
                dayOfWeek = '일';
                break;
            case 1:
                dayOfWeek = '월';
                break;
            case 2:
                dayOfWeek = '화';
                break;
            case 3:
                dayOfWeek = '수';
                break;
            case 4:
                dayOfWeek = '목';
                break;
            case 5:
                dayOfWeek = '금';
                break;
            case 6:
                dayOfWeek = '토';
                break;
        }

        return dayOfWeek;
    }

}
