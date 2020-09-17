package xp.PB.jindong;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test1 {

    public static void main(String[] args) {
        String str;
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();
        if(str==null||str.length()==0||" ".equals(str)){
            System.out.println(" ");
            return;
        }
        List<String> list = new ArrayList<>();
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i)=='1'||str.charAt(i)=='2'||str.charAt(i)=='3'){
                if(isYear(i,str)) {
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
        if(str.charAt(i+1)>='0'&&str.charAt(i+1)<='9'&&
                str.charAt(i+2)>='0'&&str.charAt(i+2)<='9'&&
                str.charAt(i+3)>='0'&&str.charAt(i+3)<='9'){
            isyaer = true;
        }
        return isyaer;
    }
}
