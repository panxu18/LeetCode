package xp.PB.Hensheng;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Peng
 * @date 2020/10/9 - 18:26
 */
public class test1009 {

    //思路：遍历两个数组直到其中一个数组遍历结束，比较两数组元素，相同则取出，不同继续遍历
    //时间复杂度： O(n)
    public List<Integer> commonElem(int[] a, int[] b)
    {
        //存放公共元素
        List<Integer> res = new ArrayList<>();
        int i=0,j=0;
        while(i<a.length && j<b.length)
        {
            if(a[i] == b[j])
            {
                res.add(a[i]);
                i++;
                j++;
            }
            else {
                if (a[i] > b[j]) j++;
                else i++;
            }
        }
        return res;
    }
}
