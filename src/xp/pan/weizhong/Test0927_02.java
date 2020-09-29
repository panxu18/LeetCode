package xp.pan.weizhong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Test0927_02 {
    private static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
    public static void main(String[] args) throws IOException {
        int x = nextInt();
        int y = nextInt();
        int z = nextInt();

        if (x > z) {
            x = (x - z) % 4;
        } else {
            x = 4 - ((z - x) % 4);
        }
    }
}
