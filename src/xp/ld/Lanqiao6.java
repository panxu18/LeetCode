package xp.ld;

import java.util.Scanner;

public class Lanqiao6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] arr = in.nextLine().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 'x') {
                arr[i] -= 23;
            } else {
                arr[i] += 3;
            }
        }
        System.out.println(String.valueOf(arr));
    }
}
