import java.util.*;

class Solution {
    
    class Trie {
        Trie[] child = new Trie[26];
        int count;
        int aletter = Character.getNumericValue('a');
        
        // Trie를 생성하는 로직
        void insert(String str) {
            Trie curr = this;
            // for문을 돌면서 문자열 끝까지 처리해주기
            for(char ch : str.toCharArray()) {
                curr.count++;
                
                int idx = Character.getNumericValue(ch) - aletter;
                if (curr.child[idx] == null) 
                    curr.child[idx] = new Trie();
                
                // 다음 노드로 이동하는 시점
                curr = curr.child[idx];
            }
            
            // leaf 노드의 count도 증가
            curr.count++;
        }
        
        // Count를 구하는 로직
        public int search(String str) {
            Trie curr = this;
            
            // 깊게 내려가면서 count 구하는 로직
            for(char ch : str.toCharArray()) {
                if (ch == '?') return curr.count;
                
                curr = curr.child[Character.getNumericValue(ch) - aletter];
                if (curr == null) return 0;
            }
            
            // 문제에서는 주어지지 않지만, frodo처럼 ?를 미포함하는 query에 대한 결과값
            return curr.count;
        }
    }
    
    Trie[] TrieRoot = new Trie[10000];
    Trie[] ReTrieRoot = new Trie[10000];
    
    public int[] solution(String[] words, String[] queries) {
        
        // Trie 생성
        for(String str : words) {
            int idx = str.length() - 1;
            if (TrieRoot[idx] == null) {
                TrieRoot[idx] = new Trie();
                ReTrieRoot[idx] = new Trie();
            }
            
            TrieRoot[idx].insert(str);
            str = new StringBuilder(str).reverse().toString();
            ReTrieRoot[idx].insert(str);
        }
        
        int[] answer = new int[queries.length];
        int ansIdx = 0;
        
        for (String str : queries) {
            int idx = str.length() - 1;
            if (TrieRoot[idx] == null) {
                answer[ansIdx++] = 0;
                continue;
            }
            
            if (str.charAt(0) != '?') {
                answer[ansIdx++] = TrieRoot[idx].search(str);
            } else  {
                str = new StringBuilder(str).reverse().toString();
                answer[ansIdx++] = ReTrieRoot[idx].search(str);
            }
        }
        return answer;
    }
}
