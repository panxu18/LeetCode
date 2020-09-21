package xp.PB.Hensheng;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        char[] strChar = scan.nextLine().toCharArray();
        int first=0,second=0;
        char firChar,secChar =0;
        for(int i=0;i<strChar.length;i++){
            int count = 0;
            for(int j=0;j<strChar.length;j++){
                if(strChar[i]==strChar[j]){
                    count++;
                }
            }
            if(count>=first){
                first=count;
                firChar=strChar[i];
            }else if(count>=second){
                second=count;
                secChar=strChar[i];
            }
        }
        if(secChar==0){
            System.out.println("null");
        }else{
            System.out.println(secChar);
        }


    }
}
