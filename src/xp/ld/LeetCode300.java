package xp.ld;

public class LeetCode300 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length <= 0)
            return 0;
        return soluteFast(nums);
    }


    /**
     * 一般动态规划解法,时间复杂度为O(N^2),
     * dp[i]代表以第i个数结尾的最长上升序列长度，
     * 因此当j<i时，如果nums[j]<nums[i],则有dp[i] = max(dp[i], dp[j] + 1)
     * @param nums 输入序列
     * @return 输入序列中的最长上升序列长度
     * @throws NullPointerException 输入序列为空产生空指针异常
     * @see #soluteFast(int[])
     */
    public int solute(int[] nums) {
        int dp[] = new int[nums.length];
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 优化解法，时间复杂度为O(nlogn)
     * @param nums 输入序列中的最长上升序列长度
     * @return 输入序列中的最长上升序列长度
     * @throws NullPointerException 输入序列为空产生空指针异常
     * @see #solute(int[])
     */
    public int soluteFast(int[] nums) {
        int dp[] = new int[nums.length];
        int maxLength = 0;
        for (int num : nums) {
           maxLength = binarySearchAndInsert(num, dp, maxLength);
        }
        return maxLength;
    }

    /**
     * 通过二分查找，将nums插入到dp数组中,直接替换掉第一个比num大的值，如果没有就插入到最后
     * @param num 需要插入的值
     * @param dp 递增序列
     * @param length 序列长度
     * @return 插入后序列的长度
     */
    private int binarySearchAndInsert(int num, int[] dp, int length) {
        int low = 0;
        int high = length - 1;
        int mid;
        if (high < 0 || num > dp[high]) {
            dp[length] = num;
            return length + 1;
        } else if (num == dp[high]) {
            return length;
        } else {
            while (low <= high) {
                mid = (low + high) >> 1;
                if (dp[mid] == num) {
                    return length;
                }else if (dp[mid] < num) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            dp[low] = num;
            return low == length? length + 1 : length ;
        }
    }

    public static void main(String args[]) {
        LeetCode300 solute = new LeetCode300();
        System.out.println(solute.soluteFast(new int[]{10,9,2,5,3,7,101,18}));
    }
}
