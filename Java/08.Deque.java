class Solution {
    Deque<Integer> Deq = new ArrayDeque<Integer>();
  
    public int[] solution(int[] A, int K) {
        Deq.addFirst(1);     // [1]
        Deq.addLast(2);      // [1, 2]
        Deq.offerFirst(3);   // [3, 1, 2]
        Deq.offerLast(4);    // [3, 1, 2, 4]
      
        System.out.println(Deq.peekFirst()); // 3
        System.out.println(Deq.peekLast());  // 4
        System.out.println(Deq);             // [3, 1, 2, 4]
      
        Deq.removeFirst(1);  // [1, 2, 4]
        Deq.removeLast(1);   // [1, 2]
        Deq.pollFirst(1);    // [2]
        Deq.pollLast(1);     // []
      
        return A;
    }
}

// add vs offer >> exception vs false
// remove vs poll >> exception vs null
