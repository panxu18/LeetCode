package xp.pan.fenghuo;

import java.util.Arrays;

public class Test1010_3 {

    public int checkNetSegmet(String mask, String ip1, String ip2) {
        int[] maskArr = Arrays.stream(mask.split(".")).mapToInt(Integer::parseInt).toArray();
        int[] ip1Arr = Arrays.stream(mask.split(".")).mapToInt(Integer::parseInt).toArray();
        int[] ip2Arr = Arrays.stream(mask.split(".")).mapToInt(Integer::parseInt).toArray();
        if (!checkMask(maskArr) || !checkIp(ip1Arr) || !checkIp(ip2Arr)) {
            return 2;
        }
        for (int i = 0; i < 4; i++) {
            if ((ip1Arr[i] & maskArr[i]) != (ip2Arr[i] & maskArr[i])) {
                return 1;
            }
        }
        return 0;
    }

    private boolean checkMask(int[] mask) {
        if (mask == null || mask.length != 4) {
            return false;
        }
        int i = 3;
        while (mask[i] == 0) {
            i++;
        }
        if (i >= 0 && (mask[i] < 0 || mask[i] > 255 || Integer.bitCount(255 - mask[i] + 1) != 1)) {
            return false;
        }
        i++;
        while (i>=0) {
            if (mask[i] != 255) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIp(int[] ip) {
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
