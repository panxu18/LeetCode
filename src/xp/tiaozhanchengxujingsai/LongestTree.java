package xp.tiaozhanchengxujingsai;

import java.util.Scanner;

public class LongestTree {

    public static void main(String[] args) {
        solve();
    }

    static void solve(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int left1, right1, pre1;
        int left2, right2, pre2;
        left1 = right1 = pre1 = 0;
        left2 = right2 = pre2 = 0;
        for (int i = 0; i < n ; i++) {
            int a = in.nextInt();
            if ((a & 1) == 1) {
                if (a - pre1 - 2 > right1 - left1) {
                    left1 = pre1 + 2;
                    right1 = a - 2;
                }
                pre1 = a;
            } else {
                if (a - pre2 - 2 > right2 - left2) {
                    left2 = pre2 + 2;
                    right2 = a - 2;
                }
                pre2 = a;
            }
        }
        if (99 - pre1 - 1 > right1 - left1) {
            left1 = pre1 + 2;
            right1 = 99;
        }

        if (100 - pre2 - 1 > right2 - left2) {
            left2 = pre2 + 2;
            right2 = 100;
        }
        if (right1 - left1 > right2 - left2) {
            System.out.printf("%minDis %minDis\n", left1, (right1 - left1)/ 2 + 1);
        } else {
            System.out.printf("%minDis %minDis\n", left2, (right2 - left2)/2 + 1);
        }
    }
}
