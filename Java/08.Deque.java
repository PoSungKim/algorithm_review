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
        
        System.out.println(Deq.remove(2));   // true >> this.remove(E);
        System.out.println(Deq);             // [3, 1, 4]
        System.out.println(Deq.offerFirst(2)); // [2, 3, 1, 4]
        
        Iterator<Integer> iter = Deq.iterator(); // Deq.descendingIterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " "); // 3 1 2 4 
        }
        iter.forEachRemaining(x -> System.out.print(x + " ")) // 3 1 2 4 >> hasNext()와 forEachRemaining() 둘 중에 하나만 사용
      
        Deq.removeFirst(1);  // [3, 1, 4]
        Deq.removeLast(1);   // [3, 1]
        Deq.pollFirst(1);    // [1]
        Deq.pollLast(1);     // []
      
        return A;
    }
}

// add vs offer >> exception vs false
// remove vs poll >> exception vs null
