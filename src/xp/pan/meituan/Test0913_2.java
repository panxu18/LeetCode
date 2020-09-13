package xp.pan.meituan;

import java.util.Scanner;

public class Test0913_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int K = in.nextInt();
        int[] arr = new int[100007];
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }
        int ans = 0;
        for (int l = 0, h = 0; h < N; h++) {
            if (arr[h] < K) {
                l = h + 1;
            } else if (h - l + 1 == M) {
                ans++;
                l++;
            }
        }
        System.out.println(ans);
    }
}
