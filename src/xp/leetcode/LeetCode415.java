package xp.leetcode;

public class LeetCode415 {
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int i = chars1.length - 1;
        int j = chars2.length - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int x = i < 0 ? 0 : chars1[i--] - '0';
            int y = j < 0 ? 0 : chars2[j--] - '0';
            int sum = x + y + carry;
            builder.append(sum % 10);
            carry = sum >= 10 ? 1 : 0;
        }
        return builder.reverse().toString();
    }
}
