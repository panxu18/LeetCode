package xp.oj.string;

import java.util.Scanner;

import static java.lang.Math.max;

/**
 * KMP
 */
public class KMP {

    /**
     * 计算公共前后缀
     */
    private int[] getPrefix(char[] arr) {
        int[] fail = new int[arr.length];
        int[] prefix = new int[arr.length];
        fail[0] = -1;
        int j = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            while (j != -1 && arr[i] != arr[j]) {
                j = fail[j];
            }
            prefix[i] = j;
            fail[i + 1] = arr[i + 1] == arr[++j] ? fail[j] : j;
        }
        // 计算prefix[arr.length-1]
        while (j != -1 && arr[arr.length - 1] != arr[j]) {
            j = fail[j];
        }
        prefix[arr.length - 1] = j;
        return prefix;
    }

    /**
     * 计算失败指针
     */
    private static int[] getFail(char[] arr) {
        int[] fail = new int[arr.length];
        fail[0] = -1;
        int j = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            while (j != -1 && arr[i] != arr[j]) {
                j = fail[j];
            }
            fail[i + 1] = arr[i + 1] == arr[j] ? fail[j] : j;
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
