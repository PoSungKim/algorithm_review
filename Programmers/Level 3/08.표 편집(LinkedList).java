import java.util.*;

class Solution {
    LinkedList<Integer> controlState = new LinkedList<>();
    LinkedList<LinkedList<Integer>> deleteState  = new LinkedList<>();
    int cR;
    
    public void runCmd(char op, int r) {
        if (op == 'D') {
            cR = (cR + r) % controlState.size();
        } else {
            cR = cR - r;
            if (cR < 0) {
                cR *= -1;
            }
        }
    }
    
    public void runCmd(char op) {
        if (op == 'C') {
            int dR = controlState.remove(cR);
            LinkedList tmp = new LinkedList<Integer>();
            tmp.add(cR);
            tmp.add(dR);
            deleteState.add(tmp);
            if (cR == controlState.size())
                cR = controlState.size() - 1;
            
        } else {
            LinkedList<Integer> lastR = deleteState.remove(deleteState.size()-1);
            controlState.add(lastR.get(0), lastR.get(1));
            if (lastR.get(0) <= cR)
                cR++;
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        for(int i = 0; i < n; i++) {
            controlState.add(i);
        }
        cR = k;
        
        for(String c : cmd) {
            if (c.length() != 1) {
                char op = c.charAt(0);
                int  r  = Integer.parseInt(c.substring(2));
                runCmd(op, r);
            } else {
                runCmd(c.charAt(0));
            }
        }
        HashSet<LinkedList<Integer>> S = new HashSet(controlState);
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < n; i++)
            if (S.contains(i)) 
                ans.append("O");
            else 
                ans.append("X");
        
        return ans.toString();
    }
}
