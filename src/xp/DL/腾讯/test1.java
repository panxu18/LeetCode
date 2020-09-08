package xp.DL.腾讯;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }
        int l = Math.min(n, m);
        int j = 0;
        int k = 0;
        StringBuilder s = new StringBuilder();
        while (j <= l && k <= l) {
            if (a[j] == b[k]) {
                if (j == l || k == l) {
                    s.append(a[j]);
                } else {
                    s.append(a[j]).append(" ");
                }
                j++;
                k++;
            } else if (a[j] < b[k]) {
                k++;
            } else {
                j++;
            }
        }
        System.out.println(s.toString());
    }
}
