package xp.pan.weizhong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Math.max;

public class Test0927_1 {
    private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    private static int[] arr = new int[100007];
    private static HashMap<Integer, Integer> indexMap = new HashMap<>(100007);

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int q = nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
            if (!indexMap.containsKey(arr[i])) {
                indexMap.put(arr[i], i);
            }
        }
        Arrays.sort(arr, 0, n);
        for (int i = 0; i < q; i++) {
            System.out.println(query(n, nextInt()));
        }
    }

    private static int query(int len, int x) {
        // 最近的值最多有2个
        return find(arr, 0, len - 1, x);
    }

    /**
     * 二分查找，要么找到指定值，要么取指定值两边的数进行比较
     */
    private static int find(int[] arr, int start, int end, int num) {
        int lb = start;
        int ub = end;
        while (lb < ub) {
            int mid = (lb + ub) >> 1;
            if (arr[mid] < num) {
                lb = mid + 1;
            } else {
                ub = mid;
            }
        }
        if (arr[ub] == num) {
            return num;
        }
        lb = max(start, ub - 1);
        int l = Math.abs(num - arr[lb]);
        int r = Math.abs(num - arr[ub]);
        if (l == r) {
            return indexMap.get(arr[lb]) < indexMap.get(arr[ub]) ? arr[lb] : arr[ub];
        }
        return l < r ? arr[lb] : arr[ub];
    }
}
