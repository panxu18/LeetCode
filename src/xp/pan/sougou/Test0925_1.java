package xp.pan.sougou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class Test0925_1 {

    private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static {
        in.whitespaceChars(',', ',' + 1);
        in.whitespaceChars('"', '"' + 1);
    }

    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    private static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    public static void main(String[] args) throws IOException {

        Interval ans = solve(3, 1, "ABC", "DDD");
        System.out.println(ans.start);
        System.out.println(ans.end);

    }

    public static Interval solve (int n, int k, String str1, String str2) {
        // write code here
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (chars1[i] == chars2[i]) {
                m++;
            }
        }
        int w = n - k;
        // 最少对多少题，不相同的全部是错的， 相同的选项中最多错w个，剩下的肯定是对的
        int min = Math.max(0, m - w);
        // 最多对多少题，不相同的全部都是对的，相同的最多对K个，剩下的肯定是错的,如果m小于k，那么k-mge一定是错的
        int max = n - Math.abs(m - k);
        return new Interval(min, max);
    }


    static class Interval {
      int start;
      int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
