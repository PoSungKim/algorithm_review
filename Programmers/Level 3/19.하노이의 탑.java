import java.util.*;

class Solution {
    
    List<int[]> Seq = new ArrayList<>();
    
    public void hanoi(int n, int from, int to, int via) {
        
        int[] move = new int[] {from, to};
        
        if (n == 1) {
            Seq.add(move);
        } else {
            hanoi(n - 1, from, via, to);
            Seq.add(move);
            hanoi(n - 1, via, to, from);
        }
         
    }
    
    public int[][] solution(int n) {
        
        hanoi(n, 1, 3, 2);
        
        int[][] answer = new int[Seq.size()][2];
        for(int i = 0; i < Seq.size(); i++) 
            answer[i] = Seq.get(i);
        
        return answer;
    }
}
