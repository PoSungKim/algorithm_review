import java.util.*;

class Solution {

    Set<Integer> Set = new HashSet<>(Arrays.asList(1,2,3)); // [1,2,3]

    public int solution(int X, int Y, int D) {
        Set.add(4);  // [1,2,3,4]
        Set.add(5);  // [1,2,3,4,5] 
        System.out.println(Set);

        Set.remove(1); // [2,3,4,5]
        Set.remove(2); // [3,4,5]
        System.out.println(Set);

        return 0;
    }
}
