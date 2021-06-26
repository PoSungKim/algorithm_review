// https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html

/*
java.lang.Object
 - java.util.Arrays
*/

import java.util.Arrays;
import java.lang.reflect.Method;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int cnt = 0;
        showClassMethods();
        for(int[] command : commands) {
            int[] copy_command = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(copy_command); 
            answer[cnt++] = copy_command[command[2] - 1];
        }
        return answer;
    }
    
    public void showClassMethods() {
        Class testClass = Arrays.class;
        Method[] methods = testClass.getMethods();
        for(Method method : methods) 
            System.out.println(method);
    }
}

