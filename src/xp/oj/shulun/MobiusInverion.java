package xp.oj.shulun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 数论莫比乌斯反演
 */
public class MobiusInverion {

    static int n;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        System.out.println(solve(n));
    }

    // 质数分解
    static ArrayList<Integer> intFactorization(int n) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                while (n % i == 0) n /= i;
            }
        }
        if (n != 1) res.add(n);
        return res;
    }

    /**
     * 求一个数的正因数的莫比乌斯函数
     * 1、对n质数分解，根据唯一性定理，n的正因数都可以通过n的质因数的幂方积表示
     * 2、由奇数个质因数相乘得到的数的莫比乌斯函数为-1
     * 3、由偶数个质因数相乘得到的数的莫比乌斯函数为1
     * 4、其他的数可以被一个完全平方数整除，莫比乌斯函数为0
     */
    static Map<Integer, Integer> mobius(int n) {
        Map<Integer, Integer> res = new HashMap<>();
        ArrayList<Integer> intFactries = intFactorization(n);
        int m = intFactries.size();
        for (int i = 0; i < (1 << m); i++) {
            int mu = 1, d = 1;
            for (int j = 0; j < m; j++) {
                if ((i >> j & 1) == 1) {
                    mu *= -1;
                    d *= intFactries.get(j);
                }
            }
            res.put(d, mu);
        }
        return res;
    }

    /**
     * 快速幂
     */
    static int mod_pow(int x, int p, int mod) {
        int res = 1;
        int a = x;
        while (p > 0) {
            if ((p & 1) == 1) {
                res = res * a % mod;
            }
            p >>= 1;
            a = a * a % mod;
        }
        return res;
    }

    /**
     * 周期性计数问题
     */
    static int solve(int n) {
        int mod = 100_000_007;
        Map<Integer, Integer> map =  mobius(n);
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res += entry.getValue() * mod_pow(26, n / entry.getKey(), mod);
            res = res % mod;
        }
        return res;
    }
}
