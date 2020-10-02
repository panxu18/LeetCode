package xp.PB.jindong;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        String str;
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();
        if(str==null||str.length()<4||" ".equals(str)){
            System.out.println(" ");
            return;
        }
        List<String> list = new ArrayList<>();
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i)=='1'||str.charAt(i)=='2'||str.charAt(i)=='3'){

                if(str.length()-i>=4&&isYear(i,str)) {
                    String temp = str.substring(i,i+4);
                    list.add(temp);
                }
            }
        }
        for (String st:list) {
            System.out.print(st +" ");
        }

    }
    public static boolean isYear(int i,String str) {
        boolean isyaer =false;

        if(str.length()-1-i>=3){
            if(str.charAt(i+1)>='0'&&str.charAt(i+1)<='9'&&
                    str.charAt(i+2)>='0'&&str.charAt(i+2)<='9'&&
                    str.charAt(i+3)>='0'&&str.charAt(i+3)<='9'
            ){
                if(i>0&&str.charAt(i-1)>='0'&&str.charAt(i-1)<='9'){
                    return false;
                }
                if((str.length()-1-i>3)&&str.charAt(i+4)>='0'&&str.charAt(i+4)<='9'){
                    return false;
                }
                isyaer = true;
            }
        }

        return isyaer;
    }
}
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//public class test1 {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher(str);
//        ArrayList<Integer> ansList = new ArrayList<>();
//        while (matcher.find()) {
//            int year = Integer.parseInt(matcher.group());
//            if (year >= 1000 && year <= 3999){
//                ansList.add(year);
//            }
//        }
//        System.out.println(ansList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
//    }
//}
