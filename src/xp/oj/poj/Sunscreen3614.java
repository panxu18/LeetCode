package xp.oj.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 贪心，优先队列
 * 问题描述
 * 有n头牛想要获得完美的肤色，所以它们去沙滩晒日光浴。为了防止晒伤，需要使用遮阳伞，每种遮阳伞的大小和遮挡紫外线效果不
 * 一样。遮阳伞遮阳效果太好了没办法晒黑，太差了会晒伤，而且每头牛能接受的阳光强度也不一样。求最多能使多少牛舒服晒太阳。
 * 问题分析
 * 使用贪心从遮阳效果最差的伞开始安排，为了尽可能多的安排更多牛，因此需要将牛按照它们的承受范围的最小值由小到大排序。
 * 同时需要对承受区间的最大值进行排序，然后选择其中最小的牛先安排。
 */
public class Sunscreen3614 {

    public static void main(String[] args) throws IOException {
        new Sunscreen3614().solve();
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

    int C, L;
    int ans = 0;
    Cow[] cows = new Cow[2505];
    Bottle[] bottles = new Bottle[2505];
    private void solve() throws IOException {
        C = readInt();
        L = readInt();
        for (int i = 0; i < C; i++) {
            cows[i] = new Cow(readInt(), readInt());
        }
        for (int i = 0; i < L; i++) {
            bottles[i] = new Bottle(readInt(), readInt());
        }

        Arrays.sort(cows, 0, C);
        Arrays.sort(bottles, 0, L);
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int ccnt = 0;
        for (int i = 0; i < L; i++) {
            for (; ccnt < C; ccnt++) {
                if (cows[ccnt].min <= bottles[i].s) {
                    minHeap.add(cows[ccnt].max);
                } else {
                    break;
                }
            }
            while (!minHeap.isEmpty() && bottles[i].num > 0) {
                int c = minHeap.poll();
                if (c >= bottles[i].s){
                    bottles[i].num--;
                    ans++;
                }
            }
        }
        out.println(ans);
        out.flush();
    }

    class Bottle implements Comparable<Bottle>{
        int s,num;

        Bottle(int s, int num){
            this.s = s;
            this.num = num;
        }

        @Override
        public int compareTo(Bottle o) {
            return s - o.s;
        }
    }

    class Cow implements Comparable<Cow>{
        int min,max;

        Cow(int min, int max){
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(Cow o) {
            return min - o.min;
        }
    }
}
