package xp.PB.shenzhou;


import java.util.*;

public class test1 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String indata = s.nextLine();
        System.out.println(sort(indata));
    }
    public static int sort (String inData) {
        String[] s = inData.split(" ");
        int[] num = new int[s.length];
        Map<Integer,Integer> num2 =new HashMap<>();
        for(int i=0;i<s.length;i++){
            if(i==0){
                String replace = s[i].substring(1, s[i].length());
                num[i] = Integer.parseInt(replace);
                num2.put(num[i],i);
            }
            else if(i==s.length-1){
                String replace = s[i].substring(0, s[i].length()-1);
                num[i] = Integer.parseInt(replace);
                num2.put(num[i],i);
            }
            else
            {
                num[i] = Integer.parseInt(s[i]);
                num2.put(num[i],i);
            }
        }
        Arrays.sort(num);
        int count = 0;
        int t=s.length;
        for(int i=0;i<s.length-1;i++){
            if(num2.get(num[i])>num2.get(num[i+1])){
                num2.put(num[i+1],t++);
                count++;
            }
        }
        return count;
    }
}
