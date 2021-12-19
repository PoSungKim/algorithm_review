import java.util.*;

class Solution {
    
    public int board[];
    public int answer = 0;
    
    public void perm(int pos) {
        
        if (pos == board.length) {
            answer++;
            return;
        }
        
        for(int i = 1; i <= board.length; i++) {
            board[pos] = i;
            boolean isOkay = true;
            for(int j = 0; j < pos; j++) {
                if (board[j] == board[pos]) isOkay = false;
                if (Math.abs(j - pos) == Math.abs(board[j] - board[pos])) isOkay = false;
            }
            if (isOkay)
                perm(pos + 1);
        }
    }
    
    public int solution(int n) {
        board = new int[n];
        perm(0);
        
        return answer;
    }
}
