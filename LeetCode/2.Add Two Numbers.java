class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode();
        ListNode head = ans;
        
        boolean isEndL1 = false,
                isEndL2 = false;
        
        int up = 0;
        while(!isEndL1 || !isEndL2) {
            int sum = up;
            if (!isEndL1)
                sum += l1.val;
            if (!isEndL2)
                sum += l2.val;
            
            if (sum >= 10) {
                up = 1;
                sum %= 10;
            } else {
                up = 0;
            }
            
            ans.val = sum;
            
            if (l1.next != null)
                l1 = l1.next;
            else
                isEndL1 = true;
            
            if (l2.next != null)
                l2 = l2.next;
            else
                isEndL2 = true;
            
            if (!isEndL1 || !isEndL2){
                ans.next = new ListNode();
                ans = ans.next;
            } else {
                if (up == 1) {
                    ans.next = new ListNode();
                    ans = ans.next;
                    ans.val = up;
                }
            }
        }
        
        return head;
    }
}c
