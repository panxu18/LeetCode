package xp.leetcode;

import java.util.Arrays;

public class LeetCode300 {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = Arrays.binarySearch(dp, 0, ans, nums[i]);
            if (j < 0) {
                dp[-j - 1] = nums[i];
                ans = Math.max(ans, -j);
            }
        }
        return ans;
    }
}
