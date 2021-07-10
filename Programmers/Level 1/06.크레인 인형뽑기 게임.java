import java.util.*;

class Solution {
    
    Deque<Integer> deq = new ArrayDeque<Integer>();
    Integer cnt = 0;
    public int solution(int[][] board, int[] moves) {       
        
        for(int move : moves) 
            pickItem(board, move - 1);
        
        return cnt;
    }
    
    public void pickItem(int[][] board, int curPos) {
        
        int fromTop = 0;
        while( fromTop < board.length ) {
            
            int curVal = board[fromTop][curPos];
            if (curVal != 0) {
                if (deq.size() > 0 && deq.peekFirst() == curVal) {
                    deq.removeFirst();
                    cnt+=2;
                }
                else
                    deq.addFirst(curVal);
                
                board[fromTop][curPos] = 0;
                
                return;
            }                
            fromTop++;
        }
    }
}
