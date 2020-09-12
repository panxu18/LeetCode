package xp.PB.wangyi;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        int ans = 0;
        for(int j=0;j<str.length();j++){
            Map<Character,Integer> tool = new HashMap<>();
            tool.put('a', 0);
            tool.put('b', 0);
            tool.put('c', 0);
            tool.put('x', 0);
            tool.put('y', 0);
            tool.put('z', 0);
            boolean[] isTure = new boolean[str.length()];
            int max = 0;
            for(int i=j;i<str.length();i++){
                tool.put(str.charAt(i),tool.get(str.charAt(i))==null?1:tool.get(str.charAt(i))+1);
                if(check(tool)){
                    isTure[i] = true;
                    max = i-j+1;
                }
            }
            ans = Math.max(ans,max);
        }

        System.out.println(ans);

    }

    public static boolean check(Map<Character,Integer> tool){
        if(tool.get('a')%2==0&&tool.get('b')%2==0
                &&tool.get('c')%2==0 &&tool.get('x')%2==0
                &&tool.get('y')%2==0 &&tool.get('z')%2==0){
            return  true;
        }
        return false;
    }

}
