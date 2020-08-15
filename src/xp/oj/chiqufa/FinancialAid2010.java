package xp.oj.chiqufa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 枚举、topN、优先队列、尺取法
 * 问题描述
 * 给你C个奶牛参加考试的分数，和需要奖学金的数目，让你从中选N头奶牛，需要总奖学金的数目不超过F，并且分数的中位数最大
 * 问题分析
 * 问题看起来像01背包，但是最优值比较复杂。	设dp[i][j]表示前i头牛选择N头牛中位数是j时最小的奖金，这个dp从时间和空间上来说都过不了，而且状态转移也 比较复杂。主要是因为，定义的状态太笼统了。
 * 这里使用直接枚举中位数的方法，中位数肯定是已知的序列中的一个，如果由大到小枚举所有中位数，如果存在以该数为中位数的子序列且f的和不超过F(贪心，尽量选择最小的值)，则该中位数为所求值。已知某一个数为中位数，那么在其左边取N/2个数，计算最小的f的和，同样在右边也取N/2个数。这是一个ktop问题使用优先队列解决就可以了。
 * 注意点：优先队列中计算topN的和时，计算当前项之前的n项和，不包含当前项。
 */
public class FinancialAid2010 {
    public static void main(String[] args) throws IOException {
        new FinancialAid2010().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Integer.parseInt(st.nextToken());
    }

    String readLine() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), "");
        }

        return st.nextToken();
    }

    char readChar() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken().charAt(0);
    }

    int N, C, F;
    int ans = 0;
    Cow[] cows = new Cow[100005];
    int[] farr = new int[100005];
    int[] leftF = new int[100005];
    int[] rightF = new int[100005];
    private void solve() throws IOException {
        N = readInt();
        C = readInt();
        F = readInt();
        for (int i = 0; i < C; i++) {
            cows[i] = new Cow(readInt(), readInt());
        }

        Arrays.sort(cows, 0, C);
        for (int i = 0; i < C; i++) {
            farr[i] = -cows[i].f;
        }
        sumOfTopN(farr, leftF, N>>1, C);
        reverse(farr, 0, C);
        sumOfTopN(farr, rightF, N>>1, C);
        reverse(rightF, 0, C);
        int len = N >> 1;
        ans = -1;
        for (int i = C - len - 1; i >= len ; i--) {
            if (leftF[i] + rightF[i] + cows[i].f <= F) {
                ans = cows[i].c;
                break;
            }
        }
        out.println(ans);
        out.flush();
    }

    private void reverse(int[] arr, int s, int t) {
        t--;
        while (s < t){
            int temp = arr[s];
            arr[s++] = arr[t];
            arr[t--] = temp;
        }
    }

    void sumOfTopN(int[] list, int[] des, int n, int len) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            des[i] = -sum; // 不包含当前项的前n项
            minHeap.add(list[i]);
            sum += list[i];
            if (i >= n) {
                sum -= minHeap.poll();
            }
        }
    }

    class Cow implements Comparable<Cow>{
        int c,f;

        Cow(int c, int f){
            this.c = c;
            this.f = f;
        }

        @Override
        public int compareTo(Cow o) {
            return c - o.c;
        }
    }
}
