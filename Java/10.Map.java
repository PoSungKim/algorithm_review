import java.util.*;

class Solution {
    List<Map.Entry<Integer, Integer>> List = new ArrayList<>();
    Map<Integer, Integer> HashMap = new HashMap<>();
    Map<Integer, Integer> TreeMap = new TreeMap<>();
    Map<Integer, Integer> LinkedHashMap = new LinkedHashMap<>();

    public int solution(int[] A, int[] B) {
        // A : [4, 3, 2, 1, 5]
        // B : [0, 1, 0, 0, 0]
        for(int i = 0; i < A.length; i ++)  {
            List.add(new AbstractMap.SimpleEntry<Integer, Integer>(A[i], B[i])); // Map.Entry는 Interface; therefore, it cannot be instantiated
            HashMap.put(A[i], B[i]);
            TreeMap.put(A[i], B[i]);
            LinkedHashMap.put(A[i], B[i]);
        }
            
        System.out.println(List);          // [4=0, 3=1, 2=0, 1=0, 5=0] 
        System.out.println(HashMap);       // {1=0, 2=0, 3=1, 4=0, 5=0} >> Iteration Order is not guaranteed       >> using Hash Table
        System.out.println(TreeMap);       // {1=0, 2=0, 3=1, 4=0, 5=0} >> Iteration Order is guaranteed           >> using Red-Black Tree
        System.out.println(LinkedHashMap); // {4=0, 3=1, 2=0, 1=0, 5=0} >> Iteration Order follows Insertion Order >> using doubly-linked list buckets 
        
        for(Map.Entry<Integer, Integer> e : List ) // {List, HashMap.entrySet(), TreeMap.entrySet(), LinkedHashMap.entrySet()}  
            System.out.print("{ " + e.getKey() + ", " + e.getValue() + " } ");

        return 0;
    }
}

class Solution2 {
    // TreeMap은 Key 값으로 정렬하기 편함
    Map<Integer, Integer> Map = new TreeMap<>(new Comparator<Integer>(){
        @Override
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    });

    public int solution(int X, int Y, int D) {
        
        Map.put(1, 2);
        Map.put(2, 1);
        Map.put(3, 0);
        
        System.out.println(Map);

        return 0;
    }
}
