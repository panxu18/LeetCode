package xp.oj.string;

import java.util.Scanner;

public class EXKMP {

    private static int[] getPrefix(char[] pattern) {
        int[] prefix = new int[pattern.length];
        prefix[0] = pattern.length;

        int pos = 1;
        int len = 0;
        while (pos + len < pattern.length && pattern[pos + len] == pattern[len]) {
            len++;
        }
        prefix[pos] = len;

        for (int i = 2; i < pattern.length; i++) {
            if (i < pos + len && prefix[i - pos] + i < pos + len) {
                prefix[i] = prefix[i - pos];
            } else {
                len = Math.max(pos + len - i, 0);
                while (i + len < pattern.length && pattern[i + len] == pattern[len]) {
                    len++;
                }
                pos = i;
                prefix[pos] = len;
            }
        }
        return prefix;
    }

    private static int[] search(char[] source, char[] pattern, int[] prefix) {
        int[] extend = new int[source.length];
        int pos = 0;
        int len = 0;
        while (pos + len < source.length && len < pattern.length && source[pos + len] == pattern[len]) {
            len++;
        }
        extend[pos] = len;

        for (int i = 1; i < source.length; i++) {
            if (i < pos + len && prefix[i - pos] + i < pos + len) {
                extend[i] = prefix[i - pos];
            } else {
                len = Math.max(pos + len - i, 0);
                while (i + len < source.length && len < pattern.length && source[i + len] == pattern[len]) {
                    len++;
                }
                pos = i;
                extend[pos] = len;
            }
        }
        return extend;
    }

}
