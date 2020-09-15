package xp.pan.shenceshuju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test0915_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = in.readLine()) != null) {
            sb.append(str).append("\n");
        }
        str = sb.toString();
        str = str.replaceAll("<>.*$?", "");
        str = str.replaceAll("<<[\\s\\S]*>>", "");
        str = str.replaceAll("\\n\\s*","\n");
        str = str.replaceAll("\\s*\\n","\n");
        System.out.println(str);
    }
}
