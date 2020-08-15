package xp.oj.string;

import java.util.Arrays;


/**
 * 后缀数组
 *
 * 问题描述
 * 1、后字符串后缀指的是从字符串的某个位置开始到其末尾的字符串子串，空串也是原串的后缀。后缀数组指的是将某个字符串的
 * 所有后缀按字典序排序后得到的数组，数组中保存的是子串开始索引。
 * 2、高度数组是指后缀数组中相邻两个后缀的前缀长度，组成的数组。
 * 问题分析
 * 后缀数组需要将所有后缀排序，如果使用简单排序时间复杂度为O(n^2logn)。因为字符串都是原串的子串，所有比较时会有重复比较，
 * 例如子串[i,n]和子串[j,n]两个子串对齐之后[i+1,n]和[j+1,n]两个子串就会重复比较。这个问题可以使用动态规划求解，使用倍增
 * 的思想可以中间状态，将子串看做是由两部分组成[i,k]和[k + 1, n]，区间长度为2的幂。比较i和j子串时就变成分别比较两个部分，
 * 因此只要计算依次计算长度为2的幂子串的大小就可以得到最终排序结果。
 * 假设已知长度为k的子串的大小rank[i]，因为是数组比较大小，所以这里的rank是数组排序后的名次，数组相等时用同名次表示
 * （当然也可以使用其他的方式来表示数组的大小）。那么长度为2*k的子串的大小，就是利用相邻的长度为k的子串的进行排序，
 * 然后计算名次。
 *
 * 如果已经计算了某个后缀子串i（i表示在原串中的开始索引）在后缀数组的前一个后缀子串为j，且与子串j的公共前缀长度为h，
 * 那么子串i+1的公共前缀长度一定不小于h-1，因为子串i+1就是子串i去掉第一个字符，所以至少存在一个子串j+1和其有长度为h-1的
 * 公共前缀。可以发现在后缀数组中子串j+1一定在子串i+1前面，即使子串i+1的在后缀数组中的前一个子串不是j+1，i+1和前面的后缀
 * 子串的公共前缀长度也至少为h-1。
 */
public class P380 {
    public static void main(String[] args) {
        new P380().solve("yeshowmuchiloveyoumydearmotherreallyicannotbelieveit$yeaphowmuchiloveyoumydearmother", "aab");
    }

    Integer[] sa; // 后组数组
    int len, k;
    int[] rank, temp; //
    private void solve(String haystack, String needle) {
        sa = new Integer[haystack.length() + 1];
        rank = new int[haystack.length() + 1];
        temp = new int[haystack.length() + 1];
        constructSa(haystack);
        for (int a :
                sa) {
            System.out.println(haystack.substring(a));
        }

        System.out.println(contain(haystack, sa, needle));

    }

    private void reverse(int[] arr) {
    }

    /**
     * 计算后缀数组，根据长度为k的后缀排序结果，计算后缀长度为2k的后缀排序，
     *
     * @param s
     */
    void constructSa(String s) {
        len = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i <= len; i++) {
            sa[i] = i;
            rank[i] = i < len ? arr[i] : -1;
        }
        // 利用对长度为k的排序结果对长度为2k的排序
        for (k = 1; k <= len ; k <<= 1) {
            Arrays.sort(sa, (i, j)->compareSa(i, j));

            // 重新计算rank
            temp[sa[0]] = 0;
            for (int i = 1; i <= len ; i++) {
                temp[sa[i]] = temp[sa[i - 1]] - (compareSa(sa[i - 1], sa[i]));
            }
            for (int i = 0; i <= len; i++) {
                rank[i] = temp[i];
            }
        }

    }

    /**
     * 比较后缀的大小，后缀分为两部分依次比较,先比较前k位，再比较后k位
     * @param i
     * @param j
     * @return
     */
    int compareSa(int i, int j) {
        if (rank[i] != rank[j]) return compareInt(rank[i], rank[j]);
        int ri = i + k <= len ? rank[i + k] : -1;
        int rj = j + k <= len ? rank[j + k] : -1;
        return compareInt(ri, rj);
    }

    int compareInt(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    boolean contain(String s, Integer[] sa, String t) {
        int a = 0;
        int b = s.length();
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        while (b - 1> a) {
            int mid = (b + a) >> 1;
            int ans = Arrays.compare(arr1, sa[mid], Math.min(sa[mid] + t.length(), s.length()), arr2, 0, t.length());
            if (ans < 0) a = mid;
            else b = mid;
        }
        return Arrays.compare(arr1, sa[b], Math.min(sa[b] + t.length(), s.length()), arr2, 0, t.length()) == 0;
    }

    int[] lcp;
    /**
     * 计算高度数组
     * @param s
     */
    void constructLCP(String s) {
        // 初始化
        int h = 0;
        lcp = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            rank[sa[i]] = i; // 查找子串i在后缀数组中的索引
        }
        char[] arr = s.toCharArray();
        // 空串在最后一个,空串的高度为0
        for (int i = 0; i < len ; i++) {
            // 前一个后缀
            int j = sa[rank[i] - 1];
            if (h > 0) h--; // 前缀长度至少为h-1
            for (; j + h < len && i + h < len ; h++) {
                if (arr[j + h] != arr[i + h]) break;
            }
            lcp[rank[i] - 1] = h;
        }
    }


}
