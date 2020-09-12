package xp.pan.qianxin;

public class Test0912_1 {

    public static void main(String[] args) {
        int[] candis = {3,5,7,2,8,8,15,3};
        int[] coin = {1,0,1,0,1,0,1,0};
        int n = 3;
        System.out.println(maxCandies(candis, coin, n));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * M包糖果，抛M次硬币，硬币连续n次为正面，最多能得到多少颗糖果
     * @param candies int整型一维数组 每包糖果的数量
     * @param coin int整型一维数组 抛硬币的结果
     * @param n int整型 连续是正面的次数
     * @return int整型
     */
    public static int maxCandies (int[] candies, int[] coin, int n) {
        int res = 0;
        for (int i = 0; i < candies.length; i++) {
            if (coin[i] == 0) {
                res += candies[i];
            }
        }
        int addition = 0;
        int maxAddition = 0;
        for (int i = 0; i < n; i++) {
            if (coin[i] == 1) {
                addition += candies[i];
            }
        }
        maxAddition = addition;
        int l = 0, h = n;
        while (h < candies.length) {
            if (coin[l] == 1) {
                addition -= candies[l];
            }
            if (coin[h] == 1) {
                addition += candies[h];
            }
            l++;h++;
            maxAddition = Math.max(maxAddition, addition);
        }
        return res + maxAddition;
    }
}
