package xp.DDLiang;

import java.util.ArrayList;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        ArrayList<String> s = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int sum = 0;
            sum += i * 200;
            for (int j = 0; j <= 9; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k <= 9; k++) {
                    if (k == j || k == i) {
                        continue;
                    }
                    int tmp = sum;
                    sum += (j + k) * 10;
                    sum += k * 2;
                    if (sum == n) {
                        count++;
                        int o = i * 100 + j * 10 + k;
                        int p = i * 100 + k * 10 + k;
                        s.add(o + " " + p);
                    } else {
                        sum = tmp;
                    }
                }
            }
            System.out.println(count);
            for (String s1 : s) {
                System.out.println(s1);
            }
        }
    }
}