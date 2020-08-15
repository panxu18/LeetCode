package xp.oj.tanxin;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 贪心，最大区间覆盖
 * 问题描述
 * x轴上有n个坐标，每个坐标可以覆盖到周围半径为r的区域，选择最少的坐标使全部坐标被覆盖。
 * 问题分析
 * 使用贪心策略每次选择尽可能远的坐标使前面的区域都被覆盖。
 */
public class SarumanArmy3069 {

    public static void main(String[] args) {
        new SarumanArmy3069().solve();
    }

    int r, n;
    int[] arr = new int[1010];
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (true){
            r = in.nextInt();
            n = in.nextInt();
            if (r == -1 && n == -1) break;
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            Arrays.sort(arr, 0, n);
            int ans = 0;
            int start = 0;
            while (start < n) {
                ans++;
                int i = start;
                while (i < n && arr[i] - arr[start] <= r) i++;
                start = i - 1;
                i = start;
                while (i < n && arr[i] - arr[start] <= r) i++;
                start = i;
            }
            out.println(ans);

        }

        out.flush();
    }
}
