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
                    // 특정 character를 내림차순으로 sorting하고자 한다면, b를 기준으로 sort하면 편함
                    // b의 값이 a보다 더 클 때   + 양수 리턴  >> [b,a]
                    // b의 값이 a보다 더 작을 때  - 음수 리턴 >> [a,b]
                    if (a.getKey() == 't') {
                        if (b.getKey() == 'o')
                            return -1;
                        if (b.getKey() == 's')
                            return -1;
                        return -1;
                    } else if (a.getKey() == 'o') {
                        if (b.getKey() == 't')
                            return 1;
                        if (b.getKey() == 's')
                            return -1;
                        return -1;
                    } else if (a.getKey() == 's') {
                        if (b.getKey() == 't')
                            return 1;
                        if (b.getKey() == 'o')
                            return 1;
                        return -1;
                    }

                    if (b.getKey() == 't') {
                        if (a.getKey() == 'o')
                            return 1;
                        if (a.getKey() == 's')
                            return 1;
                        return 1;
                    } else if (b.getKey() == 'o') {
                        if (a.getKey() == 't')
                            return -1;
                        if (a.getKey() == 's')
                            return 1;
                        return 1;
                    } else if (b.getKey() == 's') {
                        if (a.getKey() == 't')
                            return -1;
                        if (a.getKey() == 'o')
                            return -1;
                        return 1;
                    }
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
        
        Iterator<Map.Entry<Integer, Integer>> it = PQ2.iterator();
        while(it.hasNext()) {
            it.remove();
        }

        return 0;
    }
}
