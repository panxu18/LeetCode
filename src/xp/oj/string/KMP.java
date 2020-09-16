package xp.oj.string;

import java.util.Scanner;

import static java.lang.Math.max;

/**
 * KMP
 */
public class KMP {

    private static int[] getFail(char[] charArr) {
        int[] fail = new int[charArr.length];
        fail[0] = -1;
        for (int i = 0; i < charArr.length - 1; i++) {
            int j = fail[i];
            while (j >= 0 && charArr[i] != charArr[j]) {
                j = fail[j];
            }
            if (charArr[i + 1] == charArr[j + 1]) {
                fail[i + 1] = fail[j + 1];
            } else {
                fail[i + 1] = j + 1;
            }
        }
        return fail;
    }

    private static int search(char[] source, char[] pattern, int[] fail) {
        int i = 0, j = 0;
        while (i < source.length && j < pattern.length) {
            if (j >= 0 && source[i] != pattern[j]) {
                j = fail[j];
            } else {
                i++; j++;
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
        int[] prefix = getFail(patternArr);
        return search(sourceArr, patternArr, prefix);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int i = 0; i < T; i++) {
            String source = in.nextLine();
            String pattern = in.nextLine();
            System.out.println(kmp(source, pattern));
        }

    }
}
