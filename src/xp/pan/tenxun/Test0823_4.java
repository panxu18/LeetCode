package xp.pan.tenxun;

import java.util.Scanner;

/**
 * 动态规划
 * 有n块不同高度的木板并列放置，木板的宽度都为1，有一把宽度为1的刷子为它们刷油漆，假设刷子可以
 * 竖着刷也可以横着刷，横着刷时只能刷连续的木板，中间不能跳跃。计算最少需要刷多少次？
 * 考虑使用动态规划表示区间[l,r]需要刷的最少次数，任意一个时刻，可以选择竖着刷区间内的一块木板，或者从最底下开始横着刷
 * 到最小高度。
 */
public class Test0823_4 {
    private static int[] arr = new int[5005];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

    }
}
