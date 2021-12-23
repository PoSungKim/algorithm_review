import java.util.*;

class Solution {

    Set<Integer> Set1 = new HashSet<>(Arrays.asList(1,2,3)); // [1,2,3]
    Set<Integer> Set2 = new HashSet<>(Arrays.asList(1,2,3)); // [1,2,3]

    public int solution(int X, int Y, int D) {
        Set1.add(4);  // [1,2,3,4]
        Set1.add(5);  // [1,2,3,4,5] 
        System.out.println(Set);

        Set1.remove(1); // [2,3,4,5]
        Set1.remove(2); // [3,4,5]
        System.out.println(Set1);
        
        Set1.containsAll(Set2); // true or false
        Set1.retainsAll(Set2);  // 교집합 Set
        Set1.addAll(Set2);      // 합집합 Set
        Set1.removeAll(Set2);   // 차집합 Set

        return 0;
    }
}
