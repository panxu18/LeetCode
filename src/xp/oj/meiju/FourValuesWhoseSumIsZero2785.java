package xp.oj.meiju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 枚举, 4数和为0
 *
 *  问题描述
 * 给4个长度为N的数组，在这4个数组中分别取一个数字，计算有多少种组合使4个数的和为0。同一数组中相同值的数，认为不同数。
 * 问题分析
 * 一共有N^4种组合，如果考虑先将两个数组合并，一共有N^2种组合，然后枚举另外2个数组，在N^2种数字中查找一个数使四个数
 * 和为0，查找一个数的时间复杂度为O(logn)。
 */
public class FourValuesWhoseSumIsZero2785 {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        new FourValuesWhoseSumIsZero2785().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    char readChar() throws IOException {
        if (canRead()) {
            return st.nextToken().charAt(0);
        }
        throw new NoSuchElementException();
    }

    boolean canRead() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = in.readLine();
            if (s != null) {
                st = new StringTokenizer(s, " ");
            } else {
                return false;
            }
        }
        return true;
    }

    int MAXN = 4005;
    long INF = 1000000007;
    long T, N, M,S;
    long[][] arr = new long[MAXN][4];
    long[] arrAB = new long[MAXN * MAXN];
    int ans;
    private void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = readInt();
            }
        }

        int tot = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arrAB[tot++] = arr[i][0] + arr[j][1];
            }
        }
        Arrays.sort(arrAB, 0, tot);
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long m = arr[i][2] + arr[j][3];
                int ub = upperBound(arrAB, 0, tot, -m);
                int lb = lowerBound(arrAB, 0, tot, -m);
                sum += ub - lb;
            }
        }
        out.println(sum);
        out.flush();
    }

    /**
     * 在数组中查找第一个出现的值大于key的位置
     * @param a 查找的数组
     * @param fromIndex 查找区间开始索引
     * @param toIndex 查找区间结束索引（不包含）
     * @param key 查找的值
     * @return 返回大于key的第一个元素的索引，如果查找区间中所有值都小于key，返回toIndex（越界）
     */
    int upperBound(long[] a, int fromIndex, int toIndex, long key) {
        int lb = 0;
        int ub = toIndex - 1;

        while (lb < ub) {
            int mid = (lb + ub) / 2;
            if (a[mid] > key) {
                ub = mid;
            } else {
                lb = mid + 1;
            }
        }
        if (a[ub] > key) {
            return ub;
        } else {
            return ub + 1;
        }
    }

    /**
     * 在数组中查找大于或等于key的第一个元素位置
     * @param a 查找的数组
     * @param fromIndex 查找区间开始索引
     * @param toIndex 查找区间结束索引（不包含）
     * @param key 查找的值
     * @return 返回大于或等于key的第一个元素的索引，如果查找区间中所有值都小于key，返回toIndex（越界）
     */
    int lowerBound(long[] a, int fromIndex, int toIndex, long key) {
        int lb = 0;
        int ub = toIndex - 1;

        while (lb < ub) {
            int mid = (lb + ub) / 2;
            if (a[mid] >= key) {
                ub = mid;
            } else {
                lb = mid + 1;
            }
        }
        if (a[ub] >= key) {
            return ub;
        } else {
            return ub + 1;
        }
    }
}
