import java.util.*;

class Solution {
    
    public long calc(int[] tmp) {
        long sum = 0;
        for(int i = 0; i < tmp.length; i++)
            sum += tmp[i] * tmp[i];
        
        return sum;
    }
    
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> PQ = new PriorityQueue(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        
        for(int work : works) PQ.offer(work);
        
        while(n > 0) {
            Integer num = PQ.poll();
            PQ.offer(num > 0 ? num - 1 : num);
            n--;
        }
        
        int idx = 0;
        while(!PQ.isEmpty()) {
            works[idx++] = PQ.poll();
        }
        
        return calc(works);
    }
}
