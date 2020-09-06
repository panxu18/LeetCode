package xp.ZQ.腾讯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m=sc.nextInt();
        int flag=-1;
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
        for(int i=0;i<m;i++){
            int g1=sc.nextInt();
            ArrayList<Integer> list=new ArrayList<>();
            for(int j=0;i<g1;i++){
                int temp=sc.nextInt();
                if(temp==0){
                    flag=i;
                }
                list.add(temp);
            }
            res.add(list);
        }


        System.out.println();
    }
}