// 하노이의 탑 
// 하노이의 탑 특성상, 자신이 최상단 원판이라면 자신의 n 값이 1이라면, 바로 이동 가능
// 자신이 from에서 to로 움직이기 전에 자신보다 작은 원판이 존재한다면 from에서 via로 옮기고, 자신이 from에서 to로 이동하고, 아까 via로 옮긴 더 작은 원판을 via에서 to로 이동

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
