package xp.leetcode;

public class LeetCode003 {

    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int[] lastIndex = new int[128];
        int left = 0, right = 0;
        int max = 0;
        while (right < arr.length) {
            if (lastIndex[arr[right]] == 0) {
                lastIndex[arr[right++]] = 1;
                max = Math.max(max, right - left);
            } else {
                lastIndex[arr[left++]] = 0;
            }
        }
        return max;
    }

    public static void main(String args[]) {
        System.out.println(new LeetCode003().lengthOfLongestSubstring("abcabcbb"));
    }
}
