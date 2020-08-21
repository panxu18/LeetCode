package xp.ZQ;

import java.util.ArrayList;
import java.util.Scanner;

public class Test1 {
    static Scanner sc = new Scanner(System.in);
    static int n = sc.nextInt();
    static int count=n*n-1;
    public static void main(String[] args) {


        int[] array=new int[n*n];
        int len=array.length;
        if(n==1){
            array[0]=1;
        }else{
            array[0]=1;
            array[1]=1;
            for(int i=2;i<len;i++){
                array[i]=array[i-1]+array[i-2];
            }
        }
        int[][] matrix=new int[n][n];
        ArrayList<Integer> list=printMatrix(matrix,array);

        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i));
            if((i+1)/n==0){
                System.out.println();
            }
        }
    }

    public static ArrayList<Integer> printMatrix(int [][] matrix,int[] array) {
        ArrayList<Integer> list=new ArrayList<>();
        if(matrix.length==0||matrix[0].length==0){
            return list;
        }
        int tR=0;
        int tC=0;
        int dR=matrix.length-1;
        int dC=matrix[0].length-1;
        while(tR<=dR&&tC<=dC){
            printEdge(tR++,tC++,dR--,dC--,matrix,list,array);
        }
        return list;
    }

    public static void printEdge(int tR,int tC,int dR,int dC,int[][] matrix,ArrayList<Integer> list,int[] array){
        if(tR==dR){
            for(int i=tC;i<=dC;i++){
                matrix[tR][i]=array[count--];
                list.add(matrix[tR][i]);
            }
        }else if(tC==dC){
            for(int i=tR;i<=dR;i++){
                matrix[i][tC]=array[count--];
                list.add(matrix[i][tC]);
            }
        }else{
            for(int i=tC;i<dC;i++){
                matrix[tR][i]=array[count--];
                list.add(matrix[tR][i]);
            }
            for(int i=tR;i<dR;i++){
                matrix[i][dC]=array[count--];
                list.add(matrix[i][dC]);
            }
            for(int i=dC;i>tC;i--){
                matrix[dR][i]=array[count--];
                list.add(matrix[dR][i]);
            }
            for(int i=dR;i>tR;i--){
                matrix[i][tC]=array[count--];
                list.add(matrix[i][tC]);
            }
        }
    }


}
