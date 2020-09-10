package xp.pan.shengxinfu;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test0910_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        if (name.matches(".*[^a-zA-Z ].*")) {
            System.out.println("unkown name");
            return;
        }
        String[] strs = name.split(" ");
        if (strs.length == 0) {
            System.out.println("unkown name");
            return;
        }
        if (strs.length < 3) {
            System.out.println(Arrays.stream(strs)
                    .collect(Collectors.joining())
                    .toLowerCase());
        } else {
            System.out.println(Arrays.stream(strs)
                    .map(s -> s.subSequence(0, 1))
                    .collect(Collectors.joining())
                    .toLowerCase());
        }
    }
}
