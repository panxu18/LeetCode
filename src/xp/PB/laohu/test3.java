package xp.PB.laohu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @auther Peng
 * @date 2020/9/30 - 17:16
 */
public class test3 {

    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        int n = scan.nextInt();
        int[] dai = new  int[n];
        int add = 0;
        long mul = 1;
        for(int i = 0;i<n;i++){
            dai[i] = scan.nextInt();
        }
        Arrays.sort(dai);
        int res = count(dai,0,0,1);
        System.out.println(res);
    }
    public static int count(int[] nums,int n, long add, long mul){
        int num = 0;
        for(int i=n;i<nums.length;i++){
            add += nums[i];
            mul *= nums[i];
            if(add>mul)
                num+=1+count(nums,i+1,add,mul);
            else if(nums[i]==1)
                num+=count(nums,i+1,add,mul);
            else
                break;
            add-=nums[i];
            mul/=nums[i];
            while (i+1<nums.length&&nums[i+1]==nums[i]  ){
               i++;
            }
        }
        return num;
    }
}
