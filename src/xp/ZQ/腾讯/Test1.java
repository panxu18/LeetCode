package xp.ZQ.腾讯;

import java.util.Scanner;
import java.util.HashSet;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<n;i++){
            int temp=sc.nextInt();
            set.add(temp);
        }
        int m=sc.nextInt();
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<m;i++){
            int temp2=sc.nextInt();
            if(set.contains(temp2)){
                sb.append(temp2+" ");
            }
        }
        System.out.println(sb.toString());
    }
}
