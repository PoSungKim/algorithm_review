import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    
    Map<String, Integer> wordMap = new HashMap<>();
    List<List<Integer>> scoreList = new ArrayList<>();
    
    public int[] solution(String[] infos, String[] queries) {
        
        wordMap.put("-",        0); wordMap.put("cpp",      1); wordMap.put("java",     2); wordMap.put("python",   3);
        wordMap.put("backend",  1); wordMap.put("frontend", 2);
        wordMap.put("senior",   1); wordMap.put("junior",   2);
        wordMap.put("chicken",  1); wordMap.put("pizza",    2);
        
        for(int i = 0; i < 4*3*3*3; i++){
            scoreList.add(new ArrayList<Integer>());
        }
        
        for(String info : infos) {
            String[] words = info.split(" ");
            int[] nums     = {wordMap.get(words[0]) * 3 * 3 * 3, 
                            wordMap.get(words[1]) * 3 * 3, 
                            wordMap.get(words[2]) * 3, 
                            wordMap.get(words[3])};
            int score = Integer.parseInt(words[4]);
            //System.out.println(Arrays.toString(words));
            //System.out.println(Arrays.toString(nums));
            
            for(int i = 0; i < (1 << 4); i++) {
                int index = 0;
                for(int j = 0; j < 4; j++)
                    if ( (i & (1 << j)) != 0)
                        index += nums[j]; 
                
                scoreList.get(index).add(score);
            }
        }
        
        for(int i = 0; i < 4 * 3 * 3 *3 ; i++) 
            Collections.sort(scoreList.get(i));
        
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++)  {
            String[] words = queries[i].split(" ");
            int index = wordMap.get(words[0]) * 3 * 3 * 3
                + wordMap.get(words[2]) * 3 * 3
                + wordMap.get(words[4]) * 3
                + wordMap.get(words[6]);
            int score = Integer.parseInt(words[7]);
            int result = Collections.binarySearch(scoreList.get(index), score);
            
            if (result < 0) {
                result = -(result+1);
            } else {
                for(int j = result - 1; j >= 0; j--){
                    if (scoreList.get(index).get(j) == score){
                        result = j;
                    } else {
                        break;
                    }
                }
            }
            answer[i] = scoreList.get(index).size() - result;
        }
        return answer;
    }
}
