package xp.pan.sougou;

import java.util.Arrays;

/**
 * 旋转矩阵
 */
public class Test0925_2 {
    public static void main(String[] args) {
        String[] str1 = {"0"};
        String[] str2 = {"A"};
        System.out.println(rotatePassword(str1, str2));
    }

    public static String rotatePassword(String[] s1, String[] s2) {
        // write code here
        int n = s1.length;
        char[][] charMap = new char[n][];
        int[][] mask = new int[n][n];
        for (int i = 0; i < n; i++) {
            charMap[i] = s2[i].toCharArray();
            mask[i] = Arrays.stream(s1[i].split("")).mapToInt(Integer::parseInt).toArray();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(password(charMap, mask));
            rotateMatrix(mask);
        }
        return sb.toString();

    }


    private static String password(char[][] charMap, int[][] mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charMap.length; i++) {
            for (int j = 0; j < charMap.length; j++) {
                if (mask[i][j] == 0) {
                    sb.append(charMap[i][j]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 矩阵右旋90度
     */
    private static void rotateMatrix(int[][] map) {
        int n = map.length;
        if (n == 1) {
            return;
        }
        // 左右轴对称
        for (int i = 0; i << 1 < n; i++) {
            for (int j = 0; j < n; j++) {
                map[j][i] ^= map[j][n - 1 - i];
                map[j][n - 1 - i] ^= map[j][i];
                map[j][i] ^= map[j][n - 1 - i];
            }
        }
        // 左上右下对称
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                map[i][j] ^= map[n - 1 - j][n - 1 - i];
                map[n - 1 - j][n - 1 - i] ^= map[i][j];
                map[i][j] ^= map[n - 1 - j][n - 1 - i];
            }
        }
    }
}
