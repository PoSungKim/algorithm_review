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
        
        @Override
        public String toString() {
            return String.format("(%s, %s : %s)", name, amount, Parent);
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
            
            Node curNode = NodeMap.get(s);
            while(curNode.name.equals("center") == false) {
                int curInterest = a / 10;
                if (curInterest >= 1) {
                    a -= curInterest;
                    curNode.amount += a;
                    a = curInterest; 
                } else {
                    curNode.amount += a;
                    break;
                }
                curNode = NodeMap.get(curNode.name).Parent;
            }
        }
        
        for(int i = 0; i < enroll.length; i++) {
            String e = enroll[i];
            answer[i] = NodeMap.get(e).amount;
        }
        
        return answer;
    }
}
