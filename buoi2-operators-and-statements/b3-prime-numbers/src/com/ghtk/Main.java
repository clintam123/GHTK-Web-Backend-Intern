package com.ghtk;

public class Main {

    public static void main(String[] args) {
        int start = 1;
        int end = 10000;
        int total = 0;
        // C1: For loop
        for (int i = start; i <= end; i++) {
            if (isPrime(i))
                total += i;
        }
        System.out.println("For loop: " + total);

        // C2: do while
        int i = start;
        total = 0;
        do {
            if (isPrime(i))
                total += i;
            i++;
        } while (i <= end);
        System.out.println("Do while loop: " + total);

        // C3: while
        i = start;
        total = 0;
        while (i <= end) {
            if (isPrime(i))
                total += i;
            i++;
        }
        System.out.println("While: " + total);


    }

    private static boolean isPrime(int x) {
        if (x == 2 || x == 3) {
            return true;
        }
        if (x % 2 == 0 || x < 2)
            return false;
        int squareRoot = (int) Math.sqrt(x);
        for (int i = 3; i <= squareRoot; i += 2) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

}
