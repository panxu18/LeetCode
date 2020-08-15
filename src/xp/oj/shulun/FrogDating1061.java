package xp.oj.shulun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 扩展欧几里得
 *
 * 问题描述
 * 两只青蛙在网上相识了，它们聊得很开心，于是觉得很有必要见一面。它们很高兴地发现它们住在同一条纬度线上，于是它们约定各
 * 自朝西跳，直到碰面为止。可是它们出发之前忘记了一件很重要的事情，既没有问清楚对方的特征，也没有约定见面的具体位置。
 * 不过青蛙们都是很乐观的，它们觉得只要一直朝着某个方向跳下去，总能碰到对方的。但是除非这两只青蛙在同一时间跳到同一点上，
 * 不然是永远都不可能碰面的。为了帮助这两只乐观的青蛙，你被要求写一个程序来判断这两只青蛙是否能够碰面，会在什么时候碰面。
 * 我们把这两只青蛙分别叫做青蛙A和青蛙B，并且规定纬度线上东经0度处为原点，由东往西为正方向，单位长度1米，这样我们就得到
 * 一条首尾相接的数轴。设青蛙A的出发点坐标是x，青蛙B的出发点坐标是y。青蛙A一次能跳m米，青蛙B一次能跳n米，两只青蛙跳一次
 * 所花费的时间相同。纬度线总长L米。现在要你求出它们跳了几次以后才会碰面。
 * 问题分析
 * 先通过扩展欧几里得求ax + by = gcd(a,b)的解，可以先求 ax + by = gcd(a,b)的解x'，y'，然后将方程乘上c/gcd(a,b)
 * 得到a*(c/gcd(a,b))*x + b*(c/gcd(a,b))*y=c。于是可以得到ax + by = c的解x=(c/gcd(a,b))*x’。因为x增加b*(c/gcd(a,b))，
 * y减少a*(c/gcd(a,b))方程仍然成立，于是通过(x%b+b)%b就可以将x变为最小的正整数。
 */
public class FrogDating1061 {

    public static void main(String[] args) throws IOException {
        new FrogDating1061().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    long readLong() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Long.parseLong(st.nextToken());
    }

    long x, y, m, n, L;
    void solve() throws IOException {
        x = readLong();
        y = readLong();
        m = readLong();
        n = readLong();
        L = readLong();
        long a = m - n;
        long b = L;
        long c = y - x;
        if (a < 0) {
            a = -a;
            c = -c;
        }
        long[] ansX = new long[1];
        long[] ansY = new long[1];
        long g = exgcd(a, b, ansX, ansY);
        if (c % g == 0) {
            long ans = ansX[0] * c / g;
            b = b / g;
            ans = (ans % b + b) % b;
            out.println(ans);
        } else {
            out.println("Impossible");
        }
        out.flush();
    }

    private long exgcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        } else {
            long ans = exgcd(b, a%b, y, x);
            y[0] = (y[0] - (a / b) * x[0]);
            return ans;
        }
    }
}
