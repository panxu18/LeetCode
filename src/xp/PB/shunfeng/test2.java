package xp.PB.shunfeng;


import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
//        String[] strs = str.split(" ");
//        int count = 0;
//        for (String s:strs) {
//            count = count+isTrue(s);
//        }
        System.out.println(isTrue(str));
    }
    public static int isTrue(String str) {
        int count = 0;
        if(str.length()<5){
            return count;
        }

        for(int i = 0;i<str.length();i++){
            if(str.length()-i<5){
                break;
            }
            if(str.charAt(i) != 'A' && str.charAt(i) != 'a'){
                continue;
            }else{
                if ((str.charAt(i+1) == 'P' || str.charAt(i+1) == 'p')
                        && (str.charAt(i+2) == 'P' || str.charAt(i+2) == 'p')
                        && (str.charAt(i+3) == 'L' || str.charAt(i+3) == 'l')
                        && (str.charAt(i+4) == 'E' || str.charAt(i+4) == 'e')){
                    count++;
                }
            }
        }
        return count;
    }
}
