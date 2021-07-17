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
    
    // cnt : 개수, r : nPr, flag : 현재 bit 형태의 숫자
    public void perm(int cnt, int r, int flag) {
        if (cnt == r) {
            System.out.println(Arrays.toString(ret));
            return;
        }
        // flag | 1 << i : OR 연산을 통해 하나씩 i 자리에 1을 더해가는 효과
        for(int i = 0; i < list.length; i++) {
            if ( (flag & (1 << i)) != 0) continue;
            // i 자리 값을 cnt 자리에 넣어준다
            ret[cnt] = list[i]; 
            perm(cnt + 1, r, flag | 1 << i);
        }
    }
}
