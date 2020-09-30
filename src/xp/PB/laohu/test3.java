package xp.PB.laohu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @auther Peng
 * @date 2020/9/30 - 17:16
 */
public class test3 {

    public static int n;
    public static int[] dai;

    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        n = scan.nextInt();
        dai = new  int[n];
        int add = 0;
        long mul = 1;
        for(int i = 0;i<n;i++){
            dai[i] = scan.nextInt();
        }
        Arrays.sort(dai);
        int num = count(0,add,mul);
        System.out.println(num);

    }

    public static int count(int pos, int add, long mul){

        int num = 0;
        for(int i=pos;i<n;i++){
            add += dai[i];
            mul *= dai[i];
            if(add>mul)
                num+=1+count(i+1,add,mul);
            else if(dai[i]==i)
                num+=count(i+1,add,mul);
            else
                break;
            add-=dai[i];
            mul/=dai[i];
            while (dai[i+1]==dai[i] && i+1<n){
                ++i;
            }

        }
        return num;
    }
}
