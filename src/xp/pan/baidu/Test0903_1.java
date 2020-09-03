package xp.pan.baidu;

import java.util.Scanner;

/**
 * 有N个数，只包含0和5,从中选择若干个数组成一个最大的数能被90整除。
 */
public class Test0903_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int fiveCount = 0;
        int zeroCount = 0;
        for (int i = 0; i < N; i++) {
            if (in.nextInt() == 5) {
                fiveCount++;
            } else {
                zeroCount++;
            }
        }
        if (zeroCount == 0 || fiveCount < 9) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (fiveCount >= 9) {
            sb.append("555555555");
            fiveCount -= 9;
        }
        while (zeroCount > 0) {
            sb.append("0");
            zeroCount--;
        }
        System.out.println(sb.toString());
    }
}
