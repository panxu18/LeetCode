package xp.PB.xinlang;

import java.util.Scanner;

/**
 * @auther Peng
 * @date 2020/9/24 - 21:08
 */
public class shudu {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextInt()){

        }
    }
}


 class Main {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int[][]arr=new int[9][9];
        boolean[][] lie = new boolean[9][9];
        boolean[][] hang = new boolean[9][9];
        boolean[][] blocks = new boolean[9][9];
        while(scan.hasNextInt()){
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    arr[i][j]=scan.nextInt();
                    if(arr[i][j]!=0){
                        int m = i/3*3+ j/3;
                        int n=arr[i][j]-1;
                        hang[i][n] = true;
                        lie[j][n] = true;
                        blocks[m][n] = true;
                    }
                }
            }
            deepFirstSerach(arr, lie, hang, blocks);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println(arr[i][8]);
            }
        }
    }




    public static boolean deepFirstSerach(int[][] arr,boolean[][] lie,boolean[][] hang,boolean[][] blocks) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(arr[i][j]==0){
                    int k=i/3*3+j/3;
                    for (int l = 0; l < 9; l++) {
                        if(!lie[j][l]&&!hang[i][l]&&!blocks[k][l]){
                            lie[j][l] = true;
                            hang[i][l] =true;
                            blocks[k][l] = true;
                            arr[i][j] = 1 + l;
                            if(deepFirstSerach(arr, lie, hang, blocks)){
                                return true;
                            }
                            hang[i][l] =true;
                            lie[j][l] =true;
                            blocks[k][l] = false;
                            arr[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}

