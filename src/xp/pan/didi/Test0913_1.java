package xp.pan.didi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 字符串反转，给定N表示，每N个字符进行反转。
 */
public class Test0913_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = in.nextLine();
        int start = 0;
        StringBuilder ans = new StringBuilder();
        while (start < str.length()) {
            StringBuilder sb = new StringBuilder(str.substring(start, Math.min(str.length(), start + n)));
            ans.append(sb.reverse());
            start += n;
        }
        System.out.println(ans);

    }
}
