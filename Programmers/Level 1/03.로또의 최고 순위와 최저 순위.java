import java.util.*;

class Solution {
    
    Map <Integer, Integer> rank = new HashMap<Integer, Integer>();
    
    public int[] solution(int[] lottos, int[] win_nums) {
        
        rank.put(0, 6); rank.put(1, 6); rank.put(2, 5);
        rank.put(3, 4); rank.put(4, 3); rank.put(5, 2);
        rank.put(6, 1);
        
        int minScore = 0, zeroCnt = 0;
        for(int i = 0; i < lottos.length; i++) {
            for(int j = 0; j < win_nums.length; j++)
                if (lottos[i] == win_nums[j]) 
                    minScore++;
            if (lottos[i] == 0)
                zeroCnt++;
        }
         
        return new int[] {rank.get(minScore + zeroCnt), rank.get(minScore)};
    }
}
