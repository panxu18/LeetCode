package xp.PB.bianlifeng;

import java.util.Scanner;

/**
 * @auther Peng
 * @date 2020/10/10 - 19:11
 */
public class test2 {
    public static int sum ;
    public static int index;
    public static int n;
    public static int count;
    public static int k;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
         n = scan.nextInt();
         k = scan.nextInt();
         dfs(1);

         System.out.println(count);
    }

    public static void dfs(int x) {
        if(k>n){
            return;
        }
        if(sum == n){
            if(index<=k) count++;
            else return;
        }
        if(sum > n){
            return;
        }
        for(int i = x;i<=n;i++){
            index++;
            sum +=i;
            dfs(i);
            index--;
            sum -=i;
        }
    }
}
