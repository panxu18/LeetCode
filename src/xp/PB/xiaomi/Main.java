package xp.PB.xiaomi;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        String[] passwords = str.split(" ");
        for(int i=0;i<passwords.length;i++){
            System.out.println(new Main().result(passwords[i]));
        }
    }

    //正则判断
    private  int result(String str) {
        if (!str.matches(".*[^\\dA-Za-z].*")|| !str.matches(".*\\d.*")|| !str.matches(".*[a-z].*")|| !str.matches(".*[A-Z].*")) {
            return 2;
        }
        if (str.length() < 8 || str.length() > 120) {
            return 1;
        }
        return 0;
    }
}