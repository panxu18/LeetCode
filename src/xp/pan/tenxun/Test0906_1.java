package xp.pan.tenxun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test0906_1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine();
        long[] list1 = Arrays.stream(scan.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int M = scan.nextInt();
        scan.nextLine();
        long[] list2 = Arrays.stream(scan.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        ArrayList<Long> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < N && j < M) {
            if (list1[i] > list2[j]) {
                i++;
            } else if (list1[i] < list2[j]) {
                j++;
            } else {
                result.add(list1[i]);
                i++;
                j++;
            }
        }
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }
}
