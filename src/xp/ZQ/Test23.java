package xp.ZQ;

import java.util.HashSet;
import java.util.Scanner;

public class Test23 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        char[] path=s.toCharArray();
        int x=0;
        int y=0;
        HashSet<String> set=new HashSet<>();
        set.add(x+":"+y);
        for(int i=0;i<path.length;i++){
            int tempX=0;
            int tempY=0;
            if(path[i]=='N'){
                y++;
            }else if (path[i]=='S'){
                y--;
            }else if (path[i]=='E'){
                x++;
            }else if (path[i]=='W'){
                x--;
            }
            if(set.contains(x+":"+y)){
                System.out.println("True");
                return;
            }else{
                set.add(x+":"+y);
            }
        }
        System.out.println("False");
    }
}
