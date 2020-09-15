package xp.oj.string;

import java.util.Scanner;

import static java.lang.Math.max;

/**
 * KMP
 */
public class KMP {

    private static int[] getNext(char[] charArr) {
        int[] next = new int[charArr.length];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < charArr.length - 1) {
            if (j >= 0 && charArr[i] != charArr[j]) {
                j = next[j];
                continue;
            }
            if (charArr[++i] == charArr[++j]) {
                next[i] = next[j];
            } else {
                next[i] = j;
            }
        }
        return next;
    }

    private static int search(char[] source, char[] pattern, int[] next) {
        int i = 0, j = 0;
        while (i < source.length && j < pattern.length) {
            if (j == -1 || source[i] == pattern[j]) {
                i++;j++;
            } else {
                j = next[j];
            }
        }
        if (j >= pattern.length) {
            return i - pattern.length;
        } else {
            return -1;
        }
    }

    public static int kmp(String source, String pattern) {
        char[] sourceArr = source.toCharArray();
        char[] patternArr = pattern.toCharArray();
        if (sourceArr.length < patternArr.length) {
            char[] temp = sourceArr;
            sourceArr = patternArr;
            patternArr = temp;
        }
        int[] next = getNext(patternArr);
        return search(sourceArr, patternArr, next);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String source = in.nextLine();
        String pattern = in.nextLine();
        System.out.println(kmp(source, pattern));
    }
}
