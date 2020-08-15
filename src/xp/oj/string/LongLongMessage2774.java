package xp.oj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 最长公共子串
 *
 * 问题描述
 * 求两个字符串的最长公共子串。
 * 问题分析
 * 两个字符的公共子串可转为一个字符串中的相同子串，相同子串所在后缀子串在后缀数组中是聚集在一起，它们都有相同的前缀。
 * 例如相同前缀为s，用st1表示前缀s加上t1后缀，那么st1、st2、st3等后缀子串排列在一起，它们之间根据ti的大小排序。
 * 如果ti中也有相同前缀，也会聚集在一起，可以发现相邻的后缀之间的的公共前缀更大，后缀距离越远公共前缀越短。因此，
 * 最长的相同子串一定在相邻的两个后缀子串中，通过遍历高度数组就可以找到最大的相同子串。
 * 将两个字符串拼接成一个字符串时，需要保证最大前缀只使用一个字符串中的字符，即原串s中的后缀和原串t中的后缀，
 * 它们的前缀不会超过s的最后一个字符。因此，使用一个在t中不包含的字符拼接在两个字符中间。
 * 另外，计算的后缀一定要分别属于两个字符。
 */
public class LongLongMessage2774 {
    public static void main(String[] args) {
        new LongLongMessage2774().solve();
    }

    String tempStr;
    private void solve() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in),200010);
        String s, t;
        try {
            s = bf.readLine();
            t  = bf.readLine();
        } catch (IOException e) {
            return;
        }
//        tempStr = s + '$' + t;
        char[] arr = new char[s.length() + t.length() + 1];
        char[] temp = s.toCharArray();
        System.arraycopy(temp, 0, arr, 0, s.length());
        temp = t.toCharArray();
        System.arraycopy(temp, 0, arr, s.length() + 1, t.length());
        // 计算高度数组
        constructSa(arr);
//        System.out.println(tempStr);
//        for (int a :
//                sa) {
//            System.out.println(tempStr.substring(a));
//        }
        constructLcp(arr);

        // 查找最大的前缀
        int ans = 0;
        for (int i = 0; i < len ; i++) {
            if ((sa[i] < s.length()) != (sa[i + 1] < s.length())) {
                ans = Math.max(ans, lcp[i]);
            }
        }
        System.out.println(ans);
    }

    Integer[] sa;
    int[] rank, temp;
    int[] lcp;
    int len, k;
    private void constructSa(char[] arr) {
        // init
        len = arr.length;
        sa = new Integer[len + 1];
        rank = new int[len + 1];
        temp = new int[len + 1];

        for (int i = 0; i <= len ; i++) {
            sa[i] = i;
            rank[i] = i < len ? arr[i] : -1; // 长度为1的子串顺序
        }

        for (k = 1; k <= len ; k <<= 1) {
            // 对长度为2*k的子串排序
            Arrays.sort(sa, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return compareSa(o1, o2);
                }
            });
            // 计算长度为2 * k的子串的rank
            temp[sa[0]] = 0;
            for (int i = 1; i <= len ; i++) {
                // compareSa(sa[i - 1], sa[i]) 一定为0或-1
                temp[sa[i]] = temp[sa[i - 1]] - compareSa(sa[i - 1], sa[i]);
            }
            System.arraycopy(temp, 0, rank, 0, len + 1);
        }
    }

    void constructLcp(char[] arr) {
        // init
        len = arr.length;
        lcp = new int[len + 1];
        for (int i = 0; i <= len ; i++) {
            rank[sa[i]] = i;
        }
        // 空串的高度默认为0
        int h = 0;
        for (int i = 0; i < len ; i++) {
            int j = sa[rank[i] - 1]; // 前一个后缀子串
            if (h > 0) h--;
            for (; j + h < len && i + h < len ; h++) {
                if (arr[j + h] != arr[i + h]) break;
            }
            lcp[rank[i] - 1] = h;
        }
    }

    int compareSa(int i, int j) {
        if (rank[i] != rank[j]) return compareInt(rank[i], rank[j]);
        int ri = i + k <= len ? rank[i + k] : -1;
        int rj = j + k <= len ? rank[j + k] : -1;
        return compareInt(ri, rj);
    }

    int compareInt(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }
}
