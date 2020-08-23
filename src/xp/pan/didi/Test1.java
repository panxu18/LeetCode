package xp.pan.didi;

import java.util.Arrays;
import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ans = 0;
        int[] ansInt = new int[n];
        for (int i = 102; i <= 999; i++) {
            if (check(i, n)){
                ansInt[ans++] = i;

            }
        }
        Arrays.sort(ansInt, 0, ans);
        System.out.println(ans);
        for (int i = 0; i < ans; i++) {
            System.out.printf("%d %d\n", ansInt[i], n - ansInt[i]);
        }
    }

    private static boolean check(int i, int n) {
        int num1 = i;
        int c = i%10;
        i /= 10;
        int b = i%10;
        i /= 10;
        int a = i%10;
        if (a == b || a == c || b == c || a == 0) {
            return false;
        }
        int num2 = a*100 + c * 11;
        return num1 + num2 == n;
    }
}
