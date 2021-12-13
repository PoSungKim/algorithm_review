// top-down 이후에 bottom-up으로 올라가면서 연산하는 방식이라서 time-out 발생하는 풀이 : 연습용 풀이

import java.util.*;

class Solution {
    public class Node {
        int amount;
        String name;
        List<Node> nodeList;
        
        public Node(String name) {
            this.amount = 0;
            this.name = name;
            this.nodeList = new ArrayList<>();
        }
        
        public void addNode(String referral, Node newNode) {
            if (referral.equals("-")) {
                this.nodeList.add(newNode);
                return;
            }
            else if (referral.equals(this.name)) {
                this.nodeList.add(newNode);
                return;
            }   
            else {
                for(Node myNode : this.nodeList) {
                    myNode.addNode(referral, newNode);
                }
            }
        }
        
        public int addAmount(String name, int amount) {
            int interest = 0;
            if (this.name.equals(name)) {
                this.amount += amount * 90;
                return amount * 10;
            } else {
                for(Node myNode : this.nodeList) {
                    interest = myNode.addAmount(name, amount);
                    if (interest > 0) break;
                }
            }
            
            if (this.name == "center") {
                this.amount += interest;
                return 0;
            }
            
            if ((int)(interest * 0.1) > 0) {
                this.amount += interest - (int)(interest * 0.1);
                return (int)(interest * 0.1);
            } else {
                this.amount += interest;
                return 0;
            }
        }
        
        public void traverse() {
            M.put(this.name, this.amount);
            
            for(Node myNode : this.nodeList)
                myNode.traverse();
        }
        
        @Override
        public String toString() {
            return String.format("(%s, %s)", this.name, this.amount);
        }
        
    }
    
    Map<String, Integer> M = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Node Root = new Node("center");
        
        for(int i = 0 ; i < enroll.length; i++) 
            Root.addNode(referral[i], new Node(enroll[i]));
        
        for(int i = 0; i < seller.length; i++) 
            Root.addAmount(seller[i], amount[i]);
        
        Root.traverse();
        
        int[] answer = new int[enroll.length];
        
        for(int i = 0; i < enroll.length; i++)
            answer[i] = M.get(enroll[i]);
        
        return answer;
    }
}
