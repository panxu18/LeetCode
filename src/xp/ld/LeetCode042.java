package xp.ld;

import java.util.ArrayList;

public class LeetCode042 {

    /**
     * 对于任意一个坐标，只要算出左边最高的柱子的高度，和右边最高柱子的高度
     * 那么这个坐标上的容量就可以求出来了
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null)
            return 0;
        int sum = 0;
        int top = -1;
        int[] stack = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            while (top > -1 && height[stack[top]] <= height[i]) {
                int min = stack[top--];
                if (top > -1)
                    sum = sum + (i - stack[top] - 1) * (Math.min(height[stack[top]], height[i]) - height[min]);
            }
            stack[++top] = i;
        }
        return sum;
    }

    private int solute(int[] height) {
        int[] leftHigher = higherLeftNeibor(height);
        int[] righHigher = higherRightNeibor(height);
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            res += (Math.min(leftHigher[i], righHigher[i]) - height[i]);
        }
        return res;
    }

    private int[] higherLeftNeibor(int[] list) {
        int[] res = new int[list.length];
        int h = 0;
        for (int i = 0; i < list.length; i++) {
            h = Math.max(h,list[i]);
            res[i] = h;
        }
        return res;
    }

    private int[] higherRightNeibor(int[] list) {
        int[] res = new int[list.length];
        int h = 0;
        for (int i = list.length - 1; i >= 0; i--) {
            h = Math.max(h,list[i]);
            res[i] = h;
        }
        return res;
    }

    public static void main(String args[]) {

    }

}
