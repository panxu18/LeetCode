package xp.pan.shunfeng;

import java.util.Scanner;

public class Test0913_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] charArr = in.nextLine().toCharArray();
        if (charArr.length == 0) {
            System.out.println("empty");
            return;
        }
        int l = 0;
        int r = 1;
        while (l < charArr.length && r < charArr.length) {
            if (charArr[l] == charArr[r]) {
                charArr[l] = charArr[r] = 'D';
                while (l >= 0 && charArr[l] == 'D') {
                     l--;
                }
                if (l == -1) {
                    l = ++r;
                }
            } else {
                l = r;
            }
            r++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] != 'D') {
                sb.append(charArr[i]);
            }
        }
        System.out.println(sb.length() > 0 ? sb : "empty");
    }

}
