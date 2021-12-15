// Z 연산에서 번호를 기준으로 복구, 하지만 항상 마지막에 삭제된 Node가 복귀되기 때문에 바로 밑 문제 풀이를 통한 방법이 더 효율적

import java.util.*;

class Solution {
    
    public class Node {
        int num;
        Node prev, next;
        public Node(int num){
            this.num = num;
        }
        
        public void traverse() {
            System.out.printf("%s ", this);
            if (this.next != null) this.next.traverse();
        }
        
        @Override
        public String toString() {
            return String.format("(%s)", num);
        }
    }
    
    Stack<Node> myStack = new Stack<>();
    
    public String solution(int n, int k, String[] cmd) {
        Node Root = new Node(-1);
        Node Cur = Root;
        for(int i = 0; i < n; i++) {
            Node Next = new Node(i);
            Cur.next = Next;
            Next.prev = Cur;
            Cur = Next;
        }
        
        Cur = Root; for(int i = 0; i <= k; i++) Cur = Cur.next;
        
        for(String c : cmd) {
            char op = c.charAt(0);
            
            switch(op) {
                case 'U' :
                    int move = Integer.parseInt(c.split(" ")[1]);
                    for(int i = 0; i < move; i++) Cur = Cur.prev;
                    
                    break;
                case 'D' :
                    move = Integer.parseInt(c.split(" ")[1]);
                    for(int i = 0; i < move; i++) Cur = Cur.next;
                    
                    break;
                case 'C' :
                    myStack.push(Cur);
                    Node Prev = Cur.prev,
                         Next = Cur.next;
                    
                    if (Prev != null)
                        Prev.next = Next;
                    if (Next != null)
                        Next.prev = Prev;
                    
                    if (Next != null)
                        Cur = Next;
                    else
                        Cur = Prev;
                    
                    break;
                case 'Z' :
                    Node Delete = myStack.pop();
                    Node Cursor = Root;
                    
                    while(true) {
                        if (Cursor.num > Delete.num) {
                            Cursor = Cursor.prev;
                            break;
                        } else if (Cursor.next == null) {
                            break;
                        }
                        
                        Cursor = Cursor.next;
                    }
                
                    Prev = Cursor;
                    Next = Cursor.next;
                    
                    if (Prev != null)
                        Prev.next = Delete;
                    if (Next != null)
                        Next.prev = Delete;
                    
                    break;
            }
        }
        
        StringBuilder ans = new StringBuilder("O".repeat(n));
        while(!myStack.isEmpty()) ans.setCharAt(myStack.pop().num, 'X');
        return ans.toString();
    }
}
