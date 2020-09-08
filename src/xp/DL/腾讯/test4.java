package xp.DL.腾讯;

import java.util.Arrays;
import java.util.Scanner;

public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println(0);
            return;
        }
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextLong();
        }
        long[] a = b.clone();
        Arrays.sort(a);

        int mid = n / 2;
        long l = a[mid - 1];
        long r = a[mid];
        for (int i = 0; i < n; i++) {
            if (b[i] > l) {
                System.out.println(l);
            } else {
                System.out.println(r);
            }
        }
    }
}
