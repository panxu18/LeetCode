package xp.PB.wangyi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * @auther Peng
 * @date 2020/9/20 - 20:32
 */
public class test3 {
    public static  StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static  int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int res=0;
        int[] parent  = new int[105];
        int m = nextInt();
        int n = nextInt();
        for(int i=0;i<n;i++){
            int from = nextInt();
            st.nextToken();
            int to = nextInt();
            parent[to] = from;
        }

        for(int i= 1;i<=m;i++){
            parent[parent[i]] = 0;
        }
        int[] childCount = new int[105];
        for(int i=1;i<=m;i++){
            if(parent[i]!=0 && ++childCount[parent[i]]==2){
                res ++;
            }
        }
        System.out.println(res);

    }
}
