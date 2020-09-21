package xp.pan.zijietiaodong;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给出平面上n个点( x , y )，求在给定的m个点中，到n个点的曼哈顿距离和最小的一个点；
 * ( a , b ) 到( c , d )曼哈顿距离为:∣a−c∣+∣b−d∣。n和M都是10^5。
 * 因为是求曼哈顿距离，所以这里可考虑将x和y分开计算，分开计算之后，这里使用前N项和计算。
 */
public class Test0920_3 {
    private static int[] xs = new int[100007];
    private static int[] ys = new int[100007];
    private static long[] sumx = new long[100007];
    private static long[] sumy = new long[100007];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        for (int i = 0; i < N; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
        }

        Arrays.sort(xs, 0, N);
        Arrays.sort(ys, 0, N);
        sumx[0] = xs[0];
        sumy[0] = ys[0];
        for (int i = 1; i < N; i++) {
            sumx[i] = sumx[i - 1] + xs[i];
            sumy[i] = sumy[i - 1] + ys[i];
        }
        long min = Long.MAX_VALUE;
        int ansx, ansy;
        for (int i = 0; i < M; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            long temp = query(xs, 0, N - 1, sumx, x) + query(ys, 0, N - 1, sumy, y);
            if (temp < min) {
                min = temp;
                ansx = x;
                ansy = y;
            }
        }
    }

    private static long query(int[] arr, int l, int h, long[] preSum, int x) {
        int lb = lowerBound(arr, l, h, x);
        int ub = upperBound(arr, l, h, x);
        long sum = 0L;
        if (lb > l) {
            sum = (x * (lb - l)) - preSum[lb - 1];
        }
        if (ub <= h) {
            sum += (preSum[h] - preSum[ub - 1]) - (x * (h - ub + 1));
        }

        return sum;
    }

    private static int upperBound(int[] arr, int l, int h, int x) {
        while (l < h) {
            int m = (l + h) >> 1;
            if (arr[m] <= x) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        if (arr[h] > x) {
            return h;
        } else {
            return h + 1;
        }
    }

    private static int lowerBound(int[] arr, int l, int h, int x) {
        while (l < h) {
            int m = (l + h) >> 1;
            if (arr[m] < x) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        if (arr[h] > x) {
            return h;
        } else {
            return h + 1;
        }
    }
}
