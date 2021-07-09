import java.util.*;

class Solution {
    int [][] probSets = {
        {1,2,3,4,5},
        {2,1,2,3,2,4,2,5},
        {3,3,1,1,2,2,4,4,5,5}
    };
    int[] score = new int[3];
    
    public int[] solution(int[] answers) {
        
        for(int i = 0; i < answers.length; i++) {
            if (probSets[0][i%probSets[0].length] == answers[i])
                score[0]++;
            if (probSets[1][i%probSets[1].length] == answers[i])
                score[1]++;
            if (probSets[2][i%probSets[2].length] == answers[i])
                score[2]++;
        }
        
        int maxNum = Math.max(Math.max(score[0], score[1]), score[2]);
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0 ; i < 3; i++)
            if (maxNum  == score[i])
                temp.add(i + 1);
        
        return temp.stream().mapToInt(x -> x).toArray();
    }
}
