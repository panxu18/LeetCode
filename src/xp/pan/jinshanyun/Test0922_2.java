package xp.pan.jinshanyun;

import java.util.Arrays;
import java.util.Scanner;

public class Test0922_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int [] count = new int[10];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int ans = 0;
        for (int i = 9; i > 0; i--) {
            while (true) {
                int sum = 0;
                int j = 9;
                while (sum < 9 && j > 0) {
                    if (count[j] == 0 || sum + j > 9) {
                        j--;
                    } else{
                        sum += j;
                        count[j]--;
                    }
                }
                if (sum == 9) {
                    ans++;
                } else {
                    break;
                }
            }
        }
        System.out.println(ans);

    }

}
