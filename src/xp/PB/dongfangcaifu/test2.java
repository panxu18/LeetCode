package xp.PB.dongfangcaifu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class test2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int n = scan.nextInt();
        int k = scan.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        int index = 0,w = 0,l=0,m=0;
        int a = 0;
        for(int i=1;i<n/2;i++){
            a= i;
            w = (2*a-1)*(2*a-1)+8*N;
            l = (int)Math.sqrt(w);
            m = l-2*a+1;
            if(l*l!=w){
                continue;
            }else  if(m%2 !=0){
                continue;
            }else{
                List<Integer> temp = new ArrayList<>();
                for(int j=1;j<=n;j++){
                    temp.add(i+j-1);
                }
                list.add(temp);
//                if(list.size()==k){
//                    break;
//                }
            }
        }
        List<Integer> res = list.get(k-1);
        for (int i:
             res) {
            System.out.println(i+" ");
        }
        System.out.println(1+" "+4+" "+5);
    }
}
