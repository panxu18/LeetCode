package xp.pan.tenxun;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 没通过。。
 */
public class Test0906_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        long[] arr = new long[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextLong();
        }
        // 要在新数组中排序
        long[] sortedArr = Arrays.copyOf(arr, N);
        Arrays.sort(sortedArr);
        int index = (N - 1) >> 1; // 中位数索引
        long mid = sortedArr[index]; // 中位数
        // 比较和中位数的大小，分奇数和偶数两种情况
        for (int i = 0; i < N; i++) {
            if (N % 2 == 1) {
                if (arr[i] < mid) {
                    System.out.println(mid);
                } else {
                    System.out.println(sortedArr[index - 1]);
                }
            } else {
                if (arr[i] > mid) {
                    System.out.println(mid);
                } else {
                    System.out.println(sortedArr[index + 1]);
                }
            }

        }
    }
}
