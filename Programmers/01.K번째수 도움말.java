// https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html

import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int cnt = 0;
        for(int[] command : commands) {
            int[] copy_command = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(copy_command); 
            answer[cnt++] = copy_command[command[2] - 1];
            
            System.out.println(Arrays.toString(copy_command));
            System.out.println(Arrays.toString(answer) + "\n");
        }
        return answer;
    }
}
