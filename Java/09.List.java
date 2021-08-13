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
        System.out.println(List.remove(0));   // [2, 3] >> this.remove(int index) 
        List.add(0, 10);                      // [10, 2, 3]
        System.out.println(List);             // [2, 3]
        
        Collections.reverse(List);
        System.out.println(List);             // [3, 2]
        
        Collections.swap(List, 0, 1);
        System.out.println(List);             // [2, 3]
        
        List.addAll(new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        });
        Collections.shuffle(List);
        System.out.println(List);             // [2, 3, 2, 3, 5, 4, 1] >> [5, 2, 3, 2, 4, 3, 1] >> [4, 3, 3, 2, 2, 1, 5]
        
        Collections.rotate(List, K);          // + : 오른쪽, - : 왼쪽 Rotate
        
        return A;
    }
}

// Collections와 함께 사용할 수 있는 유용 Methods들이 많다

class Solution2 {
    
    public int solution2(int X, int Y, int D) {
        
        Map<Integer, Integer> Map = new TreeMap<>();
        Map.put(1, 2);
        Map.put(2, 1);
        Map.put(3, 0);
        System.out.println(Map);
        
        List<Map.Entry<Integer, Integer>> List = new ArrayList<>(Map.entrySet());
        System.out.println(List);

        Collections.sort(List, new Comparator<Map.Entry<Integer, Integer>>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });

        System.out.println(List);

        return 0;
    }
}

