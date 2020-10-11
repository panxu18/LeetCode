package xp.pan.fenghuo;

import java.util.Arrays;
import java.util.Scanner;

public class Test1010_3 {
    public static void main(String[] args) {
        String mask = "255.255.255.0";
        String ip1 = "192.168.244.251";
        String ip2 = "192.168.10.4";
//        Scanner in = new Scanner(System.in);
//        String mask = in.nextLine();
//        String ip1 = in.nextLine();
//        String ip2 = in.nextLine();
        System.out.println(new Test1010_3().checkNetSegmet(mask, ip1, ip2));

    }

    public static int checkNetSegmet(String mask, String ip1, String ip2) {
        int[] maskArr = Arrays.stream(mask.split("\\.")).mapToInt(Integer::parseInt).toArray();
        int[] ip1Arr = Arrays.stream(ip1.split("\\.")).mapToInt(Integer::parseInt).toArray();
        int[] ip2Arr = Arrays.stream(ip2.split("\\.")).mapToInt(Integer::parseInt).toArray();
        if (!checkMask(maskArr) || !checkIp(ip1Arr) || !checkIp(ip2Arr)) {
            return 1;
        }
        for (int i = 0; i < 4; i++) {
            if ((ip1Arr[i] & maskArr[i]) != (ip2Arr[i] & maskArr[i])) {
                return 2;
            }
        }
        return 0;
    }

    private static boolean checkMask(int[] mask) {
        if (mask == null || mask.length != 4) {
            return false;
        }
        int i = 3;
        while (i >= 0 && mask[i] == 0) {
            i--;
        }
        if (i >= 0 && (mask[i] < 0 || mask[i] > 255 || Integer.bitCount(255 - mask[i] + 1) != 1)) {
            return false;
        }
        i--;
        while (i >= 0) {
            if (mask[i] != 255) {
                return false;
            }
            i--;
        }
        return true;
    }

    private static boolean checkIp(int[] ip) {
        if (ip == null || ip.length != 4) {
            return false;
        }
        for (int a : ip) {
            if (a < 0 || a > 255) {
                return false;
            }
        }
        return true;
    }
}
