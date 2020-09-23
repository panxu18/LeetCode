package xp.pan.dada;

import java.util.Scanner;

public class Test0920_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().split("\\|");
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
