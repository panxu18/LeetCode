package xp.oj.string;

import static java.lang.Math.max;

/**
 * KMP
 */
public class KMP {

    final String patternString;
    final int[] next;
    private char[] chars;

    public KMP(String patternString) {
        this.patternString = patternString;
        this.chars = patternString.toCharArray();
        this.next = new int[patternString.length()];
        build();
    }

    private void build() {
        next[0] = patternString.length();
        int len=0, pos = 1;
        while (pos+1 < patternString.length() && chars[pos+len] == chars[len]) {
            len++;
        }
        next[pos] = len;
        for (int i = 2; i < patternString.length(); i++) {
            if (next[i-pos] + i < next[pos] + pos) {
                next[i] = next[i-pos];
            } else {
                len = max(0, next[pos] + pos - i);
                while (i + len < patternString.length() && chars[i+len] == chars[len]){
                    len++;
                }
                next[i] = len;
                pos = i;
            }
        }
    }

    public int[] match(String str){
        char[] chars2 = str.toCharArray();
        int[] res = new int[str.length()];
        int pos = 0, len = 0;
        while (len < patternString.length() && len < str.length() && chars2[pos] == chars[len]){
            len++;
        }
        res[pos] = len;
        for (int i = 1; i < str.length(); i++) {
            if (next[i-pos] + i < res[pos] + pos){
                res[i] = next[i - pos];
            } else {
                len = max(0, res[pos] + pos - i);
                while (i + len < str.length() && len < patternString.length() && chars2[i + len] == chars[len]){
                    len++;
                }
                res[i] = len;
                pos = i;
            }
        }
        return res;
    }
}
