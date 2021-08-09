import java.util.*;

class Solution {

    Map<Integer, Integer> Map = new TreeMap<>();

    public int solution(int X, int Y, int D) {
        
        Map.put(1, 2);
        Map.put(2, 1);
        Map.put(3, 0);
      
        PriorityQueue<Map.Entry<Integer, Integer>> PQ = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                if (b.getKey() == a.getKey())
                    return a.getValue() - b.getValue();
                
                return a.getKey() - b.getKey() ;
            }
        });
      
        PQ.addAll(Map.entrySet());
        
        System.out.println(PQ);              // Shows elements in heap (not necessarily ordered) >> don't be tricked! 
        
        while(!PQ.isEmpty())
            System.out.println(PQ.poll());   // Shows elements in order

        return 0;
    }
}

class Solution2 {

    Map<Integer, Integer> Map = new TreeMap<>();
    
    class myCompare implements Comparator<Map.Entry<Integer, Integer>>{
        @Override
        public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                if (b.getKey() == a.getKey())
                    return b.getValue() - a.getValue() ;
                
                return  b.getKey() - a.getKey() ;
            }
    };

    public int solution2(int X, int Y, int D) {
        
        Map.put(1, 2);
        Map.put(2, 1);
        Map.put(3, 0);
      
        PriorityQueue<Map.Entry<Integer, Integer>> PQ2 = new PriorityQueue<>(new myCompare());
      
        PQ2.addAll(Map.entrySet());
        
        System.out.println(PQ2);              // Shows elements in heap (not necessarily ordered) >> don't be tricked! 
        
        while(!PQ2.isEmpty())
            System.out.println(PQ2.poll());   // Shows elements in order

        return 0;
    }
}
