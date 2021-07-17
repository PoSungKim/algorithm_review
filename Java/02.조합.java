import java.util.Arrays;

class Solution {
    
    int[] list = new int[] {1,2,3,4,5};
    
    public int solution() {
        
        // nC0... nCn
        comb1();
        // nCr
        int r = 3;
        comb2(r);
        
        return 0;
    }
    
    public void comb1() {
        for(int i = 0; i < (1 << list.length); i++) {
            for(int j = 0; j < list.length; j++) {
                if ((i & (1 << j)) != 0 ) {
                    System.out.print(list[j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public void comb2(int r) {
        for(int i = 0; i < (1 << list.length); i++) {
            for(int j = 0; j < list.length; j++) {
                if (Integer.bitCount(i) == r && (i & (1 << j)) != 0 ) {
                    System.out.print(list[j] + " ");
                }
            }
            if (Integer.bitCount(i) == r) System.out.println();
        }
    }
}
