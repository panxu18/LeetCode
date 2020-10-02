package xp.PB.qianxin;



import java.util.*;

/**
 * @auther Peng
 * @date 2020/9/25 - 15:49
 */
public class test2 {

    public static void main(String[] args) {

        int[] arr = {1,1,1,2,3,3,3};
        int n = 2;
        leastWorkTime(arr,n);

    }
    public static int leastWorkTime (int[] tasks, int n) {
        int res = 0;

        List<Integer> list = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i:tasks){
            map.put(i,map.containsKey(i)?map.get(i)+1:1);
        }
        int[] task = new int[map.values().size()];
        int index = 0;
        for(Integer val:map.values()){
            task[index] = val;
            System.out.println(val);
        }
        if(n<=1){
            res = tasks.length;
        }
        Arrays.sort(task);
        if(task.length<n){

                for(int i=0;i<task[task.length-1];i=i+n){
                    list.add(i,task.length-1);
                }

        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {

        }
        Iterable<Map.Entry<Integer,Integer>> iterable = (Iterable<Map.Entry<Integer, Integer>>) map.entrySet().iterator();
        Iterable iterable1 = (Iterable) map.keySet().iterator();
        Iterable iterable2 = (Iterable) map.values().iterator();
        for(Integer integer:map.keySet()){

        }
        for(Integer integer:map.values()){

        }






        return res;
    }



}
