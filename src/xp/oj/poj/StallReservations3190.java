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
 *
 * 给你n头牛挤奶的时间段，同一时间同一栏内只能有一只牛问最少需要几个栏。并输出每头牛挤奶时所在的栏编号；
 * 按照奶牛占用的时间端的开始时间升序排列，按顺序为奶牛安排牛栏，同时将牛栏按照使用结束时间排序，如果无法为奶牛安排空闲的牛栏，
 * 就新增一个牛栏。如果有空闲牛栏就为其安排空闲牛栏。
 */
public class StallReservations3190 {

    public static void main(String[] args) throws IOException {
        new StallReservations3190().solve();
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
        return in.readLine();
    }

    int N, D;
    Range[] ranges = new Range[50005];
    int[] assign = new int[50005];
    int num = 0;
    void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            ranges[i] = new Range(i, readInt(), readInt());
        }
        Arrays.sort(ranges, 0, N);
        PriorityQueue<Stalk> que = new PriorityQueue<Stalk>();
        for (int i = 0; i < N; i++) {
            Range r = ranges[i];
            if (!que.isEmpty() && que.peek().freeTime < r.x) {
                Stalk s = que.poll();
                s.freeTime = r.y;
                que.add(s);
                assign[r.id] = s.id;
            } else {
                que.add(new Stalk(++num, r.y));
                assign[r.id] = num;
            }
        }
        out.println(num);
        for (int i = 0; i < N; i++) {
            out.println(assign[i]);
        }
        out.flush();

    }

    class Stalk implements Comparable<Stalk>{
        int id;
        int freeTime;

        Stalk(int id , int freeTime) {
            this.id = id;
            this.freeTime = freeTime;
        }

        @Override
        public int compareTo(Stalk o) {
            return freeTime - o.freeTime;
        }
    }

    class Range implements Comparable<Range>{
        int id;
        int x;
        int y;
        Range(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Range o) {
            return x - o.x;
        }
    }
}
