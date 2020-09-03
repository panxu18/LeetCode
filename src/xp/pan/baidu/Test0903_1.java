package xp.pan.baidu;

import java.util.Scanner;

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
