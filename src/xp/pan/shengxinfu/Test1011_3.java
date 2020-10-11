package xp.pan.shengxinfu;

import java.util.Arrays;

public class Test1011_3 {

    public int maxCoins(int[] piles) {
        int sum = 0;
        Arrays.sort(piles);
        for (int i = 1; 3 * i <= piles.length; i++) {
            sum += piles[piles.length - (i << 1)];
        }
        return sum;
    }
}
