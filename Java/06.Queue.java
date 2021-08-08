import java.util.*;

class Solution {
    Queue<Integer> Queue = new LinkedList<Integer>();

    public int[] solution(int[] A) {
        Queue.offer(1); // [1]
        Queue.add(2);   // [1,2]
        Queue.offer(3); // [1,2,3]
        System.out.println(Queue); // [1,2,3]
      
        System.out.println(Queue.peek());   // 1
        System.out.println(Queue.poll());   // 1 >> [2,3]
        System.out.println(Queue.remove()); // 2 >> [3]
        System.out.println(Queue);          // [3]

        return new int[]{};
    }
}

// add, offer   : 두 메소드의 차이: Exception 에러 발생 vs false 값 리턴 (Size를 넘어가서)
// remove, poll : 두 메소드의 차이: Exception 에러 발생 vs null 값 리턴  (Size가 없어서)
// 사이즈 관련 에러가 normal한 Program에서는 offer와 poll 사용 추천
