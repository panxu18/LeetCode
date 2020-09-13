package xp.pan.didi;

import java.util.Scanner;

public class Test0913_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String str = in.nextLine();
        int start = 0;
        StringBuilder ans = new StringBuilder();
        while (start < str.length()) {
            StringBuilder sb = new StringBuilder(str.substring(start, Math.min(str.length(), start + n)));
            ans.append(sb.reverse().toString());
            start += n;
        }
        System.out.println(ans);

    }
}
