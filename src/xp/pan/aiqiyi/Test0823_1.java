package xp.pan.aiqiyi;

import java.util.Scanner;

public class Test0823_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ans = 0;
        while (n >= 5) {
            n = n/5;
            ans += n;
        }
        System.out.println(ans);
    }
}
