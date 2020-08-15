package xp.string;

public class EXKMP {
    int MAXN = 100000;
    int[] next = new int[MAXN];
    int[] extend = new int[MAXN];

    void getNext(char[] arr) {
        next[0] = arr.length;
        int i, j, p;
        j = 0;
        while (j < arr.length - 1 && arr[1 + j] == arr[j]) j++;
        next[1] = j;
        p = 1;
        for (i = 2; i < arr.length; i++) {
            if (next[i - p]< next[p] + p - i) next[i] = next[i - p];
            else {
                j = next[p] + p - i;
                j = Math.max(0, j);
                while (i + j < arr.length && arr[i + j] == arr[j]) j++;
                next[i] = j;
                p = i;
            }
        }
        for (i = 0; i < arr.length; i++) {
            System.out.println(next[i]);
        }
    }

    void exkmp(char[] str,char[] p)//计算extend数组
    {
        int i=0,j,p0,slen=str.length,plen=p.length;
        getNext(p);//计算p的next数组
        while(i<slen&&i<plen&&str[i]==p[i]) i++;//计算ex[0]
        extend[0]=i;
        p0=0;//初始化po的位置
        for(i=1;i<slen;i++)
        {
            if(next[i-p0]+i<extend[p0]+p0) {
                //第一种情况，直接可以得到ex[i]的值
                extend[i]=next[i-p0];
            } else {
                //第二种情况，要继续匹配才能得到ex[i]的值
                j=extend[p0]+p0-i;
                if(j<0) j=0;//如果i>ex[po]+po则要从头开始匹配
                while(i+j<slen&&j<plen&&str[i+j]==p[j]) j++;//计算ex[i]
                extend[i]=j;
                p0=i;//更新po的位置
            }
        }
    }

}
