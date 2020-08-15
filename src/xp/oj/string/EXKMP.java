package xp.oj.string;

/**
 * EXKMP
 *
 * 问题描述
 * kmp算法中通过求next数组，可以快速计算以i结尾的子串的后缀子串与原字符串的最大公共前缀（不包含本身）。现在需要计算字符串中所有后缀子串，与原字符串的最大公共前缀。
 * 问题分析
 * 假设后缀子串i与原字符串的最大前缀长度为L，那么说明后缀子串i+a和后缀子串a的最大前缀为L-a。子串a和原字符串的最大前缀为L'，如果子串a与原字符串的最大前缀小于L-1，那么子串i+a的子串的前缀也为L'，如果如果子串a与原字符串的最大前缀大于等于L-a，就说明子串i+a需要从长度L开始和原字符串相比较。
 * 上面的分析中长度L与后缀子串索引有关，不同后缀索引的最大前缀长度不同，这里保存最大的匹配长度，即子串p的最大前缀长度为L，最大匹配索引为p+L-1。对于任意子串i，与分一下几个情况：
 * 1、ex[i - p] + i < ex[p] + p, 则ex[i] = ex[i - p]
 * 2、ex[i - p] >= ex[p] + p, 则子串i需要从ex[p] + p - i位开始匹配，
 */
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
