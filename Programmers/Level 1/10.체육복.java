import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        int[] ls = new int[n];
        Arrays.fill(ls, 1);
        
        for(int l : lost) ls[l - 1]--;
        for(int l : reserve) ls[l - 1]++;
        
        for(int i = 0; i < ls.length; i++) {
            if (ls[i] == 0) {
                if(i > 0 && ls[i - 1] == 2) {
                    ls[i]++;
                    ls[i - 1]--;
                }
                else if( i < ls.length - 1 && ls[i + 1] == 2) {
                    ls[i]++;
                    ls[i + 1]--;
                } 
            }
        }
        
        int ans = 0;
        for(int i = 0; i < ls.length; i++) if(ls[i] > 0) ans++;
        
        return ans;
    }
}
