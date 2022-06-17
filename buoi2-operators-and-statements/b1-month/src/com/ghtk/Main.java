package com.ghtk;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(int days) {
            this.days = days;
        }

        public int getDays() {
            return days;
        }
    }

    public static void main(String[] args) {
        // write your code here


        Scanner sc = new Scanner(System.in);
        String month = "";
        Month enumMonth = Month.JANUARY;
        try {
            System.out.println("Month name (January, February, ...): ");
            month = sc.next().toUpperCase();
            enumMonth = Month.valueOf(month);
        } catch (Exception e) {
            System.err.println("Err: Wrong month name");
            return;
        }
        //C1: enum + switch
        System.out.print("C1:");
        switch (enumMonth) {
            case FEBRUARY:
                System.out.println(28);
                break;
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                System.out.println(31);
                break;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                System.out.println(30);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + enumMonth);
        }

        // C2: ko dung enum
        System.out.print("C2:");
        HashMap<Integer, String[]> monthWithDays = new HashMap<>();
        monthWithDays.put(31, new String[]
                {"JANUARY",
                        "MARCH",
                        "MAY",
                        "JULY",
                        "AUGUST",
                        "OCTOBER",
                        "DECEMBER"});

        monthWithDays.put(28, new String[]{"FEBRUARY"});
        monthWithDays.put(30, new String[]{"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"});

        for (Integer days : monthWithDays.keySet()) {
            for (String m : monthWithDays.get(days)) {
                if (m.equals(month)) {
                    System.out.println(days);
                    break;
                }
            }
        }

        // C3: dung enum + getter
        System.out.print("C3:");
        System.out.println(enumMonth.getDays());

    }
}
