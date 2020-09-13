package xp.PB.didi;


import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sacn = new Scanner(System.in);
        int n = sacn.nextInt();
        String str = sacn.next();
        if(n>=str.length()){
            System.out.println(new StringBuilder(str).reverse());
            return;
        }
        int l =0;
        if(str.length()%n==0) l=str.length()/n; else l=str.length()/n+1;
        String[] strs = new String[l];
        int index = 0;
        for(int i=0;i<str.length();i=i+2){
            if(i<str.length()-2){
                strs[index++] = str.substring(i,i+2);
            }else{
                strs[index++] = str.substring(i,str.length());
            }

        }
        StringBuilder res = new StringBuilder();
        for(int i=0;i<index;i++){
            res.append(new StringBuilder(strs[i]).reverse());
        }
        System.out.println(res);
        return;

    }
}
