package xp.pan.xinlang;

public class Test0924_2 {
    public static void main(String[] args) {

    }

    public int couples (int month) {
        // f(n) = f(n-2) + f(n-1)
        int a = 1, b = 1;
        for (int i = 3; i <= month; i++) {
            b = a + b;
            a = b - a;
        }
        return b;
    }
}
