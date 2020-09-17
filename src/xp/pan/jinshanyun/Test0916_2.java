package xp.pan.jinshanyun;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Test0916_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] seq = Arrays.stream(in.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int bugs1 = in.nextInt();
        int bugs2 = in.nextInt();

        ArrayList<Integer> path1 = new ArrayList<>();
        path.clear();
        find(seq, 0, bugs1, path1);
        ArrayList<Integer> path2 = new ArrayList<>();
        path.clear();
        find(seq, 0, bugs2, path2);
        System.out.println(path1.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        System.out.println(path2.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        int i = 0;
        while (i < path1.size() && i < path2.size() && path1.get(i) == path2.get(i)) {
            i++;
        }

        System.out.println(path1.get(i - 1));
    }

    private static Stack<Integer> path = new Stack<>();
    private static int find(int[] seq, int root, int id, ArrayList<Integer> result) {
        int sum = 1;
        path.push(seq[root]);
        if (seq[root] == id) {
            result.addAll(path);
        }
        sum += find(seq, root + sum, id, result);
        sum += find(seq, root + sum, id, result);
        path.pop();
        return sum;
    }

}
