import java.util.Arrays;

class Solution {
    
    int[] list = new int[] {1,2,3,4,5};
    int[] ret  = new int[list.length];
    
    public int solution() {
        
        // nPr
        int r = 3;
        perm(0, r, 0);
        
        return 0;
    }
    
    public void perm(int cnt, int r, int flag) {
        if (cnt == r) {
            System.out.println(Arrays.toString(ret));
            return;
        }
        
        for(int i = 0; i < list.length; i++) {
            if ( (flag & (1 << i)) != 0) continue;
            ret[cnt] = list[i];
            perm(cnt + 1, r, flag | 1 << i);
        }
    }
}
