package xp.pan.jinshanyun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test0916_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] seq = Arrays.stream(in.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int bugs1 = in.nextInt();
        int bugs2 = in.nextInt();
        Stack<Integer> path1 = new Stack<>();
        find(seq, 0, bugs1, path1);
        Stack<Integer> path2 = new Stack<>();
        find(seq, 0, bugs2, path2);
//        System.out.println(path1.stream().map(String::valueOf).collect(Collectors.joining(" ")));
//        System.out.println(path2.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        while (path1.size() != path2.size()) {
            if (path1.size() > path2.size()) {
                path1.pop();
            } else {
                path2.pop();
            }
        }
        System.out.println(path1.peek());
    }

    private static int find(int[] seq, int root, int id, Stack<Integer> path) {
        if (seq[root] == -1) {
            return 1;
        }
        int sum = 1;
        path.push(seq[root]);
        if (seq[root] == id) {
            return sum;
        }
        sum += find(seq, root + sum, id, path);
        sum += find(seq, root + sum, id, path);
        path.pop();
        return sum;
    }
}
