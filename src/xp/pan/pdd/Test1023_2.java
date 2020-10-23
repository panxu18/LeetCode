package xp.pan.pdd;

import java.util.Arrays;
import java.util.Scanner;

public class Test1023_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] arr = in.nextLine().toCharArray();
        String ans = null;
        int lb = -1, ub = -1;
        int[] characterCnt = new int[128];
        int total = 0;
        int cnt = 0;
        for (char c : arr) {
            if (++characterCnt[c] == 1) {
                total++;
            }
        }
        Arrays.fill(characterCnt, 0);
        for (int head = 0, end = 0; end < arr.length; end++) {
            if (++characterCnt[arr[end]] == 1 && ++cnt == total) {
                while (true) {
                    String temp = String.valueOf(arr, head, end - head + 1);
                    if (ans == null || ans.compareTo(temp) >= 0) {
                        ans = temp;
                        lb = head;
                        ub = end - head + 1;
                    }
                    if (--characterCnt[arr[head++]] == 0) {
                        cnt--;
                        break;
                    }
                }
            }
        }
        System.out.printf("%d %d%n", lb, ub);
    }
}
