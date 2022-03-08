import java.util.*;

public class Solution {
    // 주소값 비교를 통해서 비교 가능
    Set<ListNode> hashSet = new HashSet<>();
    
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        for(;;) {
            if (!hashSet.contains(head)) {
                hashSet.add(head);
                head = head.next;
                if (head == null) break;
            } else return true;
        }
        
        return false;
    }
}
