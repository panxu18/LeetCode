package xp.oj.tanxin;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 贪心
 *
 * 问题描述
 * 给一个字符串S，和一个空串T，每次只能将S的头部或尾部的字符添加到T的尾部，求最终T的最小字典序。
 * 问题分析
 * 考虑字符串S的两端，每次添加其中较小的一个字符，如果两个字符相同，则需要考虑中间的字符大小。至少有一端相同的部分会被
 * 先去掉，那么第一个不相同的字符越小，最后生成的T就越小。因为可能存在相同前缀，所以实际上就是比较正序的字符和反
 * 转字符串大小。
 * 算法实现
 */
public class BestCowLine3617 {

    public static void main(String[] args) {
        new BestCowLine3617().solve();
    }

    int n;
    void solve() {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        n = in.nextInt();
        in.nextLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(in.nextLine());
        }
        char[] arr = new char[n];
        char[] reArr = new char[n];
        sb.getChars(0, n, arr, 0);
        sb.reverse();
        sb.getChars(0, n, reArr, 0);

        char[] ans = new char[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {

            int mismatch;
            for (mismatch = 0; mismatch < n - i ; mismatch++) {
                if (arr[l + mismatch] != reArr[r + mismatch]) break;
            }
            if (mismatch < n - i && arr[l + mismatch] < reArr[r + mismatch]) {
                ans[i] = arr[l++];
            } else {
                ans[i] = reArr[r++];
            }
        }

        for (int i = 1; i <= n; i++) {
            out.print(ans[i - 1]);
            if (i % 80 == 0) out.println();
        }
        out.flush();
    }
}
