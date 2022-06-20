package com.ghtk;

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
        //C1: enum
        System.out.print("C1:");
        System.out.println(enumMonth.getDays());

        // C2: switch
        System.out.print("C2:");
        switch (month) {
            case "JANUARY", "MARCH", "MAY", "JULY", "AUGUST", "OCTOBER", "DECEMBER":
                System.out.println("Thang " + month + " 31");
                break;
            case "FEBRUARY":
                System.out.println("Thang " + month + " 28");
                break;
            case "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER":
                System.out.println("Thang " + month + " 30");
                break;
            default:
                System.err.println("Err: Wrong month name");
        }
    }
}
