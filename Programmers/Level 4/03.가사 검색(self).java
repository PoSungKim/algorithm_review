class Solution {
    class Trie {
        Trie[] child = new Trie[26];
        int count = 0;
        int aNumber = Character.getNumericValue('a');
        
        public void insert(String str) {
            Trie curr = this;
            
            for(char ch : str.toCharArray()) {
                curr.count++;
                int idx = Character.getNumericValue(ch) - aNumber;
                if (curr.child[idx] == null) 
                    curr.child[idx] = new Trie();
                
                curr = curr.child[idx];
            }
            curr.count++;
        }
        
        public int search(String str) {
            Trie curr = this;
            
            for(char ch : str.toCharArray()) {
                if (ch == '?') return curr.count;
                int idx = Character.getNumericValue(ch) - aNumber;
                if (curr.child[idx] == null) return 0;
                curr = curr.child[idx];
            }
            
            return curr.count;
        }
    }
    
    public static Trie[] TrieRoot = new Trie[100000];
    public static Trie[] ReTrieRoot = new Trie[10000];
    
    public int[] solution(String[] words, String[] queries) {
        
        for(String str : words) {
            int idx = str.length() - 1;
            if (str.charAt(0) != '?') {
                if (TrieRoot[idx] == null) {
                    TrieRoot[idx] = new Trie();
                    ReTrieRoot[idx] = new Trie();
                }
                TrieRoot[idx].insert(str);
                ReTrieRoot[idx].insert(new StringBuilder(str).reverse().toString());
            }    
        }
        
        int[] answer = new int[queries.length];
        int ansIdx = 0;
        for(String str : queries) {
            int idx = str.length() - 1;
            if (TrieRoot[idx] == null) {
                    answer[ansIdx++] = 0;
                    continue;
            }
            if (str.charAt(0) != '?')
                answer[ansIdx++] = TrieRoot[idx].search(str);
            else 
                answer[ansIdx++] = ReTrieRoot[idx].search(new StringBuilder(str).reverse().toString());
        }
        
        return answer;
    }
}
