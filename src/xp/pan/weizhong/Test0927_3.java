package xp.pan.weizhong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Test0927_3 {
    private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    private static int[] arr = new int[100007];

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
        discretize(arr, 0, n);
        long sum = 0;

        for (int i = 0; i < n; i++) {
            long cnt = sum(arr[i] + 1);
            if (cnt > 0) {
                sum += (cnt - 1) * cnt / 2;
            }
            add(arr[i] + 1);
        }
        System.out.println(sum);
    }

    private static int[] unique = new int[100007];
    private static void discretize(int[] arr,int from, int to){
        System.arraycopy(arr, from, unique, 0, to - from);
        int len = unique(unique, 0, to-from);
        for (int i = from; i < to; i++) {
            arr[i] = Arrays.binarySearch(unique, 0, len, arr[i])+1;
        }
    }

    private static int unique(int[] arr, int from, int to) {
        int k = 0;
        Arrays.sort(arr, from, to);
        for (int i = from; i < to; i++) {
            if (k == 0 || arr[i] != arr[k-1]){
                arr[k++] = arr[i];
            }
        }
        return k;
    }

    private static int[] bit = new int[1000007];

    private static void add(int x) {
        while (x < 1000007) {
            bit[x] += 1;
            x += x & -x;
        }
    }

    private static long sum(int x) {
        long res = 0;
        while (x > 0) {
            res += bit[x];
            x -= x & -x;
        }
        return res;
    }
}
