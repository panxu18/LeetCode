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
        long[] arr = Arrays.stream(in.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] sortedArr = Arrays.copyOf(arr, N);
        Arrays.sort(sortedArr);
        int index = (N - 1) >> 1; // 中位数索引
        long mid = sortedArr[index]; // 中位数
        for (int i = 0; i < N; i++) {
            if (arr[i] > mid) {
                System.out.println(mid);
            } else {
                System.out.println(arr[index + 1]);
            }
        }
    }
}
