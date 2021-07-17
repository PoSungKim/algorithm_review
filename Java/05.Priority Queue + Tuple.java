import java.util.PriorityQueue;
import java.util.Comparator;


public class Tuple  {
    private final Integer left;
    private final Integer right;
    
    public Tuple (Integer left, Integer right) {
        this.left  = left;
        this.right = right;
    }
    
    public Integer getLeft() {
        return left;
    }
    
    public Integer getRight() {
        return right;
    }
    
    @Override
    public String toString() {
        return "(" + getLeft() + "," + getRight() + ")";
    }
}

class Solution {
    
    PriorityQueue <Tuple> pq = new PriorityQueue<>(new Comparator<Tuple>() {
        @Override
        public int compare(Tuple o1, Tuple o2) {
            
            return o1.getLeft() != o2.getLeft() ?
                   o1.getLeft()  - o2.getLeft() : 
                   o1.getRight() - o2.getRight();
        }
    });
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        pq.offer(new Tuple(3,5));
        pq.offer(new Tuple(1,3));
        pq.offer(new Tuple(2,7));
        
        while(pq.size() > 0) {
            System.out.println(pq.poll().toString());
        }
        
        return 0;
    }
}
