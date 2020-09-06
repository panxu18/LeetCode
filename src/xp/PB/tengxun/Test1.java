package xp.PB.tengxun;


import java.util.*;

public class Test1 {
    public static void main(String[] args)  {
        Scanner scan = new Scanner(System.in);
        int n1 = scan.nextInt();
        long[] list1 = new long[n1];
        for(int i=0;i<n1;i++){
            list1[i] = scan.nextLong();
        }
        int n2 = scan.nextInt();
        long[] list2 = new long[n2];
        for(int i=0;i<n2;i++){
            list2[i] = scan.nextLong();
        }
        long[] res = new long[Math.min(n1, n2)];
        int i=0,j=0,index = 0;
        while(i<n1&&j<n2){
            if (list1[i]==list2[j]){
                res[index++] = list1[i];
                i++;
                j++;
            }else if(list1[i]>list2[j]){
                i++;
            }
            else{
                j++;
            }
        }
        for(int k =0;k<index;k++){
            System.out.print(res[k]+" ");
        }
    }
}
