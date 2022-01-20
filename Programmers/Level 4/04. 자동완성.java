public class Trie {
    int cnt = 0;
    char ch;
    Trie[] next = new Trie[26];
}

class Solution {
    
    public int solution(String[] words) {
        
        int answer = 0;
        Trie root = new Trie();
        
        for(String w : words) {
            Trie cur = root;
            for(char c : w.toCharArray()) {
                int num = Character.getNumericValue(c) - Character.getNumericValue('a');
                if (cur.next[num] == null) 
                    cur.next[num] = new Trie();
                
                cur.next[num].ch = c;
                cur.next[num].cnt++;
                cur = cur.next[num];
            }
        }
        
        for(String w : words) {
            Trie cur = root;
            int idx = 0;
            boolean didCount = false;
            for(char c : w.toCharArray()) {
                int num = Character.getNumericValue(c) - Character.getNumericValue('a');
                cur = cur.next[num];
                idx++;
                if (cur.cnt == 1) {
                    answer += idx;
                    didCount = true;
                    break;
                }
            }
            
            if (!didCount) 
                answer += idx;
            
        }
        
        return answer;
    }
    
}
