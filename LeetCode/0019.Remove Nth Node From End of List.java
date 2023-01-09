// Time Complexity : O(n)
// Space Complexity : O(3)
class Solution {

    int totLength = 0,
        nth = 0;

    public ListNode findEnd(ListNode curNode) {
        totLength++;
        if (curNode.next == null)
            return curNode;
        else
            return findEnd(curNode.next);
    }

    public void removeNth(ListNode curNode, ListNode beforeNode, int curLength) {
        
        if (nth == curLength) {
            beforeNode.next = curNode.next;
            return;
        }
        
        removeNth(curNode.next, curNode, curLength + 1);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        findEnd(head);
        nth = totLength - n;
        if (nth == 0) return head.next;
        removeNth(head, null, 0);

        return head;
    }
}
