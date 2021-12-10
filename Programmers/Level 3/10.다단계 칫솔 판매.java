import java.util.*;

class Solution {
    
    class Node {
        int sum;
        String name;
        List<Node> nodeList;
        
        public Node(String name) {
            this.sum      = 0;
            this.name     = name;
            this.nodeList = new ArrayList<>();
        }
        
        public void addNode(Node n) {
            this.nodeList.add(n);
        }
        
        public void findAddNode(Node newNode, String referral) {
            if (this.name.equals(referral)) {
                this.addNode(newNode);
                return;
            }
            
            for(Node myNode : nodeList) 
                myNode.findAddNode(newNode, referral);
        }
        
        public void findAddSum(String name, int amount) {
            if (this.name.equals(name)) {
                this.sum = amount * 90;
                return;
            }
            
            for(Node myNode : this.nodeList) {
                myNode.findAddSum(name, amount);
            }
        }
        
        public int calcSum() {
            if (nodeList.size() == 0) 
                return this.sum / 10;
            
            for(Node myNode : nodeList) 
                this.sum += myNode.calcSum();
            
            int interest = this.sum *= 0.1;
            
            this.sum *= 0.9;
            
            return interest;
        }
        
        public void traverse() {
            System.out.println(this);
            for(Node myNode : nodeList)
                myNode.traverse();
        }
        
        @Override
        public String toString() {
            return String.format("Node (%s, %s)", this.name, this.sum);
        }
    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Node Root = new Node("center");
        for(int i = 0; i < enroll.length; i++) {
            if (referral[i].equals("-")) 
                Root.addNode(new Node(enroll[i]));
            else
                Root.findAddNode(new Node(enroll[i]), referral[i]);
        }
        
        for(int i = 0; i < seller.length; i++) 
            Root.findAddSum(seller[i], amount[i]);
        
        // Root.calcSum();
        Root.traverse();
        
        int[] answer = {};
        return answer;
    }
}
