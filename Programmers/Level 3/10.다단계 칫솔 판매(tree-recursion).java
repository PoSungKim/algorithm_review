import java.util.*;

class Solution {
    
    public class Node {
        String name;
        int amount;
        Node Parent;
        
        public Node(String name) {
            this.amount = 0;
            this.name = name.equals("-") ? "center" : name;
        }
        
        public void calcAmount(int amount) {
            int interest = amount / 10;
            if (interest >= 1) {
                this.amount += amount - interest;    
            } else {
                this.amount += amount;
                return;
            }
            
            if (name.equals("center")) 
                return;
            
            this.Parent.calcAmount(interest);
        }
    }
    
    Map<String, Node> NodeMap = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        for(int i = 0; i < enroll.length; i++) {
            String e = enroll[i],
                   r = referral[i];
            NodeMap.put(e, new Node(e));
            NodeMap.put(r, new Node(r));
        }
        
        for(int i = 0; i < enroll.length; i++) {
            String e = enroll[i],
                   r = referral[i];
            NodeMap.get(e).Parent = NodeMap.get(r);
        }
        
        int[] answer = new int[enroll.length];
        
        for(int i = 0; i < seller.length; i++) {
            String s = seller[i];
            int    a = amount[i] * 100;
            
            NodeMap.get(s).calcAmount(a);
        }
        
        for(int i = 0; i < enroll.length; i++) {
            String e = enroll[i];
            answer[i] = NodeMap.get(e).amount;
        }
        
        return answer;
    }
}
