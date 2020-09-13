package xp.pan.shunfeng;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test0913_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Pattern pattern = Pattern.compile("(apple)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        int ans = 0;
        while (matcher.find()) {
            ans++;
            //System.out.println(matcher.group());
        }
        System.out.println(ans);
    }
}
