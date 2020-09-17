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
        str = str.replaceAll("<<[\\s\\S]*>>", "");
        str = str.replaceAll("<>.*$?", "");
        str = str.replaceAll("\\n\\s*","\n");
        str = str.replaceAll("\\s*\\n","\n");
        System.out.println(str);

        System.out.println("Hallo, dies ist eine ziemlich lange Zeile, die in Html aber nicht umgebrochen");
        System.out.println("wird.");
        System.out.println("Zwei");
        System.out.println();
        System.out.println("produzieren zwei Newlines. Es gibt auch noch das tag");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("was einen Trenner darstellt. Zwei");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("produzieren zwei Horizontal Rulers. Achtung mehrere Leerzeichen irritieren Html");
        System.out.println("genauso wenig wie mehrere Leerzeilen.");
    }
}
