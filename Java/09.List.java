import java.util.*;

class Solution {

    List<Integer> List = new ArrayList<>();
    public int[] solution(int[] A, int K) {
      
        List.add(1); // [1]
        List.add(2); // [1, 2]
        List.add(3); // [1, 2, 3]
        System.out.println(List); // [1, 2, 3]
        System.out.println(List.get(0));      // 1
        System.out.println(List.set(0, 100)); // [100, 2, 3]
        System.out.println(List.get(0));      // 100 
        System.out.println(List.remove(100)); // [2, 3]
        System.out.println(List);             // [2, 3]

        return A;
    }
}
