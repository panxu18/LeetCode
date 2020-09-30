package xp.PB.qianxin;


import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println(jump(n));

    }
    public static int jump(int n){
        int res=0;
        if(n ==0){
            res=0;
        }else if(n==1||n==2){
            res = n;
        }else{
            res = 2*jump(n-1);
        }
        return res;
    }
}
