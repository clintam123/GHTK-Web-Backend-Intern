package com.ghtk;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
        Scanner sc = new Scanner(System.in);
        String input1 = getDateInput(sc);
        String input2 = getDateInput(sc);
        LocalDate  date1 = LocalDate.parse(input1, dtf);
        LocalDate  date2 = LocalDate.parse(input2, dtf);
        if(date1.isBefore(date2)){
            System.err.println("Err: Date 1 must > Date 2");
            return;
        }
        long daysBetween = DAYS.between(date2, date1); // date1 > date2
        System.out.println("Days between: " + daysBetween);
    }

    private static String getDateInput(Scanner sc){
        System.out.println("Nhap date (yyyy MM dd):");
        String tmp = "";
        tmp += sc.next() + " " + sc.next() + " " + sc.next();
        return tmp;
    }
}
