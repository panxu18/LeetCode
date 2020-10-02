package xp.PB.laohu;

/**
 * @auther Peng
 * @date 2020/9/30 - 17:03
 */
public class test2 {
    public int minOperationCount(String source, String target) {
        int len1 = source.length();
        int len2 = target.length();
        int[][] tmpInt = new int[len1+1][];
        for(int i=0;i<len1+1;i++){
            tmpInt[i] = new int[len2+1];
            tmpInt[i][0] = i;
        }
        for(int j=0;j<len2+1;j++){
            tmpInt[0][j] = j;
        }
        for(int i=1 ;i<len1+1;i++){
            for(int j=1;j<len2+1;j++){
                int temp = Math.min(tmpInt[i-1][j]+1,tmpInt[i][j-1]+1);
                int d;
                if(source.charAt(i-1) == target.charAt(j-1))
                    d = 0 ;
                else
                    d = 1;
                tmpInt[i][j] = Math.min(temp,tmpInt[i-1][j-1]+d);
            }
        }
        int ans = tmpInt[len1][len2];
        return ans;

    }
}
