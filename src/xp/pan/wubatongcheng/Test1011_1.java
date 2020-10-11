package xp.pan.wubatongcheng;

public class Test1011_1 {
    public int lineup (String peoples) {
        // write code here
        char[] arr = peoples.toCharArray();
        return Math.min(solve1(arr), solve2(arr));
    }

    private int solve1(char[] arr) {
        int sum = 0, result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'G') {
                result += sum;
            } else {
                sum++;
            }
        }
        return result;
    }

    private int solve2(char[] arr) {
        int sum = 0, result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'B') {
                result += sum;
            } else {
                sum++;
            }
        }
        return result;
    }
}
