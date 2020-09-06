package xp.pan.sougou;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Test0905_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[3];
        arr[0] = in.nextInt();
        arr[1] = in.nextInt();
        arr[2] = in.nextInt();
        Arrays.sort(arr);
        // 最大匀两个给最小，将最大的两个数的差缩小到2以内
        while (arr[2] - arr[1] >= 2) {
            arr[0]++;
            arr[2] -= 2;
            int temp = arr[1];
            arr[1] =  max(arr[0], arr[1]);
            arr[0] = min(arr[0], temp);
        }
        Arrays.sort(arr);
        // 最大和次大分别匀一个给最小
        while (arr[1] > arr[0]) {
            arr[2]--;
            arr[1]--;
            arr[0]++;
        }
        System.out.println(min(arr[0], min(arr[1], arr[2])));
    }
}
