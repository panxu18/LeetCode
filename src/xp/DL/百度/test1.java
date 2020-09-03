package xp.DL.百度;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int countZero = 0;
        int countFive = 0;
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            if (temp == 0) {
                countZero++;
            } else {
                countFive++;
            }
        }
        if (countZero == 0) {
            System.out.println(-1);
        } else if (countFive < 9) {
            System.out.println(-1);

        } else {
            int s = countFive % 9;
            countFive = countFive - s;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < countFive; i++) {
                sb.append("5");
            }
            for (int i = 0; i < countZero; i++) {
                sb.append("0");
            }
            System.out.println(sb.toString());
        }
    }
}


