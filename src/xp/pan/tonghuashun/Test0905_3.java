package xp.pan.tonghuashun;

import java.util.Arrays;

public class Test0905_3 {

    public static void main(String[] args) {
        int n = 6;
        int[] arr = {1,3,4,5,6,7,8,9,9,4,2,3};
        int[][] result = combine(arr, n);

        for (int i = 0; i < result.length; i++) {
            System.out.printf("(%d,%d)\n",result[i][0], result[i][1]);
        }
    }

    private static int[][] combine(int[]arr, int n) {
        Arrays.sort(arr, 0, n << 1);
        int[][] result = new int[n][2];
        for (int i = 0; i < n; i++) {
            result[i][0] = arr[i];
            result[i][1] = arr[i + n];
        }
        return result;
    }
}
