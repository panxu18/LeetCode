package xp.pan.jingdong;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test0917_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        ArrayList<Integer> ansList = new ArrayList<>();
        while (matcher.find()) {
            int year = Integer.parseInt(matcher.group());
            if (year >= 1000 && year <= 3999){
                ansList.add(year);
            }
        }
        System.out.println(ansList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
