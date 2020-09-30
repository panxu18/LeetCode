package xp.pan.shenceshuju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Test0915_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = in.readLine()) != null) {
            sb.append(str).append("\n");
        }
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            line.append('-');
        }
        str = sb.toString();
        str = str.replaceAll("<br>", "\n");
        str = str.replaceAll("<hr>", "\n" + line.toString() + "\n");
        Arrays.stream(str.split("\n"))
                .map(a->a);
        System.out.println(str);
    }
}
