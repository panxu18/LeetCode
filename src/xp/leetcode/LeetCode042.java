package xp.leetcode;

public class LeetCode042 {

    /**
     * 从左到右计算每个谷的容量，为了不重复计算，需要将已经计算过的谷移除，即提高谷底。
     */
    public int trap(int[] height) {
        int sum = 0;
        int[] left = new int[height.length];
        int top = 0;
        for (int i = 0; i < height.length; i++) {
            while (top > 0 && height[left[top - 1]] <= height[i]) {
                if (top-- > 1) {
                    sum += (Math.min(height[left[top - 1]],height[i]) - height[left[top]] ) * (i - left[top - 1] - 1);
                }
            }
            left[top++] = i;
        }
        return sum;
    }

    public static void main(String[] args) {
//        int[] height = {4, 2, 3};
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new LeetCode042().trap(height));
    }
}
