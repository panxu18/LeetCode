package xp.pan.shunfeng;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test0913_2 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        Pattern pattern = Pattern.compile("(apple)", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(str);
//        int ans = 0;
//        while (matcher.find()) {
//            ans++;
//            //System.out.println(matcher.group());
//        }
//        System.out.println(ans);
        List<Integer> arr = Arrays.asList(1,2,3,4,5,6,7,8,9);
//        for(int i=arr.size();i>=0;i--){
//            arr.remove(i);
//            System.out.println(arr.get(arr.size()-i+1));
//        }
        Iterator<Integer> iterator = arr.iterator();
        while (iterator.hasNext()){
            Integer it1 = iterator.next();
            System.out.println(it1);
            iterator.remove();
        }

    }
}
