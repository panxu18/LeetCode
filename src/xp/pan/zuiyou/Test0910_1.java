package xp.pan.zuiyou;

import java.util.Scanner;

public class Test0910_1 {
    public static void main(String[] args) {
//        long test = 4738381338321616895L;
//        System.out.println(test);
//        System.out.println(test * 36);
//        return;
        long INF = 9223372036854775807L;
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        if (str == null || str.isEmpty()) {
            System.out.println("0");
            return;
        }
        char[] strArr = str.toCharArray();
//        char[] strArr = in.nextLine().toCharArray();
        int flag = 1;
        if (strArr[0] == '-') {
            flag = -1;
        } else if (strArr[0] >= '0' && strArr[0] <= '9'
                || strArr[0] >= 'a' && strArr[0] <= 'z') {
            flag = 1;
        } else {
            System.out.println("0");
            return;
        }
        long ans = 0;
        int start = flag > 0 ? 0 : 1;
        for (int i = start; i < strArr.length; i++) {
            int d = 0;
            if (strArr[i] >= '0' && strArr[i] <= '9') {
                d = strArr[i] - '0';
            } else if (strArr[i] >= 'a' && strArr[i] <= 'z'){
                d = strArr[i] - 'a' + 10;
            } else {
                ans = 0;
                break;
            }
            if (INF / 36 <= ans || ans * 36 + d < 0) {
                ans = INF;
                break;
            }
            ans *= 36;
            ans += d;
        }
        System.out.println(ans * flag);

    }
}
