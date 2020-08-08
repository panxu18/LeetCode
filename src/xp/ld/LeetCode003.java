package xp.ld;

public class LeetCode003 {
    /**
     *
     * @param s 输入字符串
     * @return
     * @throws NullPointerException 输入参数为空是参数异常
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        int max = 0;
        int head = 0;
        char[] chars = s.toCharArray();
        int[] table = new int[256];
        for (int tail = 0; tail < chars.length; tail++) {
            while (table[chars[tail]] != 0) {
                table[chars[head++]] = 0;
            }
            table[chars[tail]] = 1;
            max = Math.max(max, tail - head + 1);
        }
        return max;
    }

    public static void main(String args[]) {
        System.out.println(new LeetCode003().lengthOfLongestSubstring("pwwkew"));
    }
}
