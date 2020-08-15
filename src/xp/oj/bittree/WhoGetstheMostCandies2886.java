package xp.oj.bittree;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 伪约瑟夫环、反素数（约数最多的最小数）打表、树状数组
 *
 * 问题描述
 *  n个人顺时针围成一圈玩约瑟夫游戏  每个人手上有一个数v，第k个人出队,如果v大于0，下一个出队的人为右边第v个人。
 *  如果v小于0，则出队的人为左边第v个， 第m出队的人可以得到m的约数个数的糖果 。问得到最多糖果的人是谁，以及获得的糖果数量。
 * 问题分析
 * 刚开始的思路是，模拟出队的操作，当前有N个人，如果一个人出队了那么后面的人向前移动，剩下的N-1个人的位置都会减1，
 * 需要计算剩下N-1个位置对应的是原来哪个人。错误的以为每次操作之后，每个位置都会加1,后来发现并不是的。
 * 之后考虑记录每个人当前在什么位置，这个可以先计算他前面有多少人出队，然后再得出他应该排在第几位。
 * 计算约数个数，根据整数分解唯一性可以算出约数个数，可以采用暴力法算法每个数的约数个数，取约数最多的数中的最小值。
 * 其实所求的这个数称为反素数，还有一种枚举质因数的方法计算反素数。
 *
 */
public class WhoGetstheMostCandies2886 {

    PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        new WhoGetstheMostCandies2886().solve();
    }

    int MAXN = 500005;
    int N, K;
    int ans;
    String[] name = new String[MAXN];
    int[] val = new int[MAXN];
    int maxDiv, antiprime;
    // 反素数
    int[] antiprimes = {1,2,4,6,12,24,36,48,60,120,180,240,360,720,840,1260,1680,2520,5040,7560,10080,15120,20160,25200,
            27720,45360,50400,55440,83160,110880,166320,221760,277200,332640,498960,500001};
    //因子数打表
    int[] div = {1,2,3,4,6,
            8,9,10,12,16,
            18,20,24,30,32,
            36,40,48,60,64,
            72,80,84,90,96,
            100,108,120,128,144,
            160,168,180,192,200,
            1314521};

    private void solve() throws IOException {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()){
            N = in.nextInt();
            K = in.nextInt();
            for (int i = 1; i <= N; i++) {
                name[i] = in.next();
                val[i] = in.nextInt();
            }
            int idx = upperBound(antiprimes, 0 , div.length, N);
            maxDiv = div[idx-1];
            antiprime = antiprimes[idx-1];
            ans = doSolve();
            out.printf("%s %d\n", name[ans], maxDiv);
        }

        out.flush();
    }

    private int upperBound(int[] arr, int s, int t, int key) {
        int lb = s, ub = t - 1;
        while (lb < ub){
            int mid = (lb + ub) >> 1;
            if (arr[mid] > key){
                ub = mid;
            } else {
                lb = mid+1;
            }
        }
        if (arr[ub] > key){
            return ub;
        } else {
            return ub +1;
        }
    }

    private int doSolve() {
        init();
        int out = K;
        int n = N;
        for (int i = 1; i < antiprime; i++) {
            n--;
            int kp = findK(out);
            add(kp, -1);
            if (val[kp] > 0) {
                out = ((out + val[kp] - 2) % n + n) % n + 1;
            } else {
                out = ((out + val[kp] - 1) % n + n)%n  +1;
            }
        }
        return findK(out);
    }

    private void init(){
        Arrays.fill(bit, 0);
        for (int i = 1; i <= N; i++) {
            add(i, 1);
        }
    }

    private int findK(int k) {
        int lb = 1, ub = N;
        while (lb < ub){
            int mid = (lb + ub)>>1;
            int t = sum(mid);
            if (t >= k){
                ub = mid;
            } else {
                lb = mid + 1;
            }
        }
        return ub;
    }

    int[] bit = new int[MAXN];
    private int sum(int x) {
        int res = 0;
        while (x > 0){
            res += bit[x];
            x -= x & -x;
        }
        return res;
    }

    private void add(int x, int a){
        while (x <= N){
            bit[x] += a;
            x += x & -x;
        }
    }
}
