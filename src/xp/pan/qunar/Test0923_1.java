package xp.pan.qunar;

import java.math.BigInteger;
import java.util.Scanner;

public class Test0923_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        BigInteger ans = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            ans = ans.multiply(BigInteger.valueOf(m-i)).divide(BigInteger.valueOf(i+1));
        }
        System.out.println(ans.longValueExact());

    }
}
