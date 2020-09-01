package xp.pan.pdd;

import java.util.Scanner;

/**
 * 容斥原理
 * 给一个大小为M的整数集合，计算1-N中由多个整数至少能被集合中一个数整除。
 *
 * 对于一个数a，1-N中有N/a个数可以被a整除，例如2,3，将能被2和3整除的个数相加，但是都2和3都可以整除6，所以计算时会多算一次，因此需要将
 * 2和3的最小公倍数6可以整除的个数减去，这就是容斥原理。根据容斥原理，可以枚举所有组合，计算其最小公倍数可以整除的个数，符号可以根据组合
 * 中数的个数来计算，组合的大小为偶数时符号为负，否则为正。
 */
/*
输入
10 1
2
输出5

输入
10 2
2
3
输出
7
 */
public class Test0901_4 {

    private static int N, M;
    private static int[] arr = new int[15];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        for (int i = 0; i < M; i++) {
            arr[i] = in.nextInt();
        }
        int ans = solve();
        System.out.println(ans);
    }

    private static int solve() {
        int res = 0;
        for (int i = 1; i < (1 << M); i++) {
            int num = 0;
            for (int j = i; j > 0; j>>=1) {
                num += j&1;
            }
            int lcm = 1;
            for (int j = 0; j < M; j++) {
                if ((i >> j & 1) == 0) {
                    continue;
                }
                lcm = lcm / gcd(lcm, arr[j]) * arr[j];
                if (lcm > N) {
                    break;
                }
            }
            res += (num & 1) == 1 ? N / lcm : -N / lcm;
        }
        return res;
    }

    /**
     * 最大公约数
     */
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
}
