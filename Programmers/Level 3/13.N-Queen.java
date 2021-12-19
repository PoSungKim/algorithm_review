import java.util.*;

class Solution {
    
    public int board[];
    public int answer = 0;
    
    public void perm(int pos) {
        
        for(int i = 0; i < pos; i++) {
            for(int j = i + 1; j < pos; j++) {
                if (board[i] == board[j]) {
                    return;
                }
                if (Math.abs(i - j) == Math.abs(board[i] - board[j])) {
                    return;
                }
            }
        }
        
        if (pos == board.length) {
            answer++;
            return;
        }
        
        for(int i = 1; i <= board.length; i++) {
            board[pos] = i;
            perm(pos + 1);
        }
    }
    
    public int solution(int n) {
        board = new int[n];
        perm(0);
        
        return answer;
    }
}
