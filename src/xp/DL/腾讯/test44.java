package xp.DL.腾讯;

import java.util.Arrays;
import java.util.Scanner;

public class test44 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        long[] c = Arrays.copyOf(arr, n);
        Arrays.sort(c);
        int mid = n >> 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] <= c[mid - 1]) {
                System.out.println(c[mid]);
            } else {
                System.out.println(c[mid - 1]);
            }
        }
    }
}
