package com.my.pro;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Akasha {

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        final String[] strings = computeTime(scanner.nextLine());
        for (String str : strings) System.out.println(str);
        scanner.close();
    }

    public static String[] computeTime(String time) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date sunrise = sdf.parse(time);

        String[] akashaPeriods = new String[12];

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sunrise);

        for (int i = 0; i < 12; i++) {
            String start = sdf.format(calendar.getTime());
            calendar.add(Calendar.MINUTE, 24);

            String end = sdf.format(calendar.getTime());
            calendar.add(Calendar.MINUTE, -24);

            calendar.add(Calendar.HOUR_OF_DAY, 2);
            akashaPeriods[i] = start + "-" + end;
        }
        return akashaPeriods;
    }
}