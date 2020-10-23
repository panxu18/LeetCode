package xp.DL.PDD;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] array = new int[N];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int temp = sc.nextInt();
            if (temp < min) {
                min = temp;
            }
            if (temp > max) {
                max = temp;
            }
            array[i] = temp;
        }
        int a = max - M;
        int b = min + M;
        if (a > b) {
            System.out.println(-1);
        } else {
            System.out.println(a + " " + b);
        }
    }
}
