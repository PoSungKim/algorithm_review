import java.util.*;

public class Solution {
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
