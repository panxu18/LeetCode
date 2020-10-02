package xp.PB.qihu360;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<int[]> list = new ArrayList<>();
        while(scan.hasNext()){
            int[] arr = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list.add(arr);
        }
        for (int[] a:list) {
            System.out.println(load(a[0],a[1],a[2],a[3]));
        }
    }
    public static int load(int a, int b, int k, int v){
        int res = 0;
        int room = a%v==0?a/v:a/v+1;
        int temp = room%k==0?res/k:room/k+1;
        for(int n = 1;n<100000;n++){
            if(b>=n*(k-1)){
                if(room<=n*k){
                    res = n;
                    break;
                }else {
                    continue;
                }
            }else {
                res = (n-1)+ room - (n-1)*k;
            }
        }

        return res;
    }
}
