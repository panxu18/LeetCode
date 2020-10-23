package xp.pan.pdd;

import java.util.Scanner;

public class Test1023_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int[] arr = new int[N];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
        }
        int lowBound = min, upperBound = max;
        while (lowBound < upperBound) {
            int mid = (lowBound + upperBound) >> 1;
            if (check(arr, mid, M)) {
                upperBound = mid;
            } else {
                lowBound = mid + 1;
            }
        }
        int ans1 = check(arr, upperBound, M) ? upperBound : -1;
        if (ans1 == -1) {
            System.out.println(ans1);
            return;
        }
        lowBound = min;
        upperBound = max;
        while (lowBound < upperBound) {
            int mid = (lowBound + upperBound + 1) >> 1;
            if (check(arr, mid, M)) {
                lowBound = mid;
            } else {
                upperBound = mid - 1;
            }
        }
        int ans2 = upperBound;
        System.out.printf("%d %d%n", ans1, ans2);
    }

    private static boolean check(int[] array, int x, int m) {
        for (int a : array) {
            if (Math.abs(x - a) > m) {
                return false;
            }
        }
        return true;
    }
}
