package xp.tiaozhanchengxujingsai;

import java.io.*;
import java.util.StringTokenizer;

class LargestRectangle {

    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        new LargestRectangle().solve();
    }

    private long getLastRect(long[] arr) {
        if (arr == null || arr.length <= 0) return 0;
        if (arr.length == 1) return arr[0];

        long max = 0;
        int[] L = new int[arr.length]; 
        int[] R = new int[arr.length];
        int[] stack = new int[arr.length]; // 保存索引
        int top = 0;
        //  计算L
        for (int i = 0; i < arr.length; i++) {
            // 维护单调递增
            while (top > 0 && arr[i] <= arr[stack[top - 1]]) top--;
            L[i] = top == 0 ? 0 : (stack[top - 1] + 1);
            stack[top++] = i;
        }
        // 计算R
        top = 0;
        for (int i = arr.length - 1; i >= 0 ; i--) {
            while (top > 0 && arr[i] <= arr[stack[top - 1]]) top--;
            R[i] = top == 0 ? arr.length : stack[top - 1];
            stack[top++] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i] * (R[i] - L[i]));
        }

        return max;
    }

    public void solve() {
        int n;
        long[] arr;
        while ((n = in.nextInt()) != 0) {
            arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            out.println(getLastRect(arr));
        }
        out.flush();
    }


    class InputReader{
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = new StringTokenizer("");
        }

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(s);
            }
            return true;
        }

        String next() {
            if (hasNext()) return tokenizer.nextToken();
            else return null;

        }
        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}

