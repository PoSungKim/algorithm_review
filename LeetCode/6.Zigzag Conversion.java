class Solution {
    public String convert(String s, int numRows) {
        List<String>[] strList = new ArrayList[numRows];
        for(int i = 0; i < numRows; i++) strList[i] = new ArrayList<>();
        
        for(int i = 0; i < s.length();) {
            
            for(int j = 0; j < numRows && i < s.length(); j++) 
                strList[j].add(Character.toString(s.charAt(i++)));
            
            for(int j = numRows - 2; j >= 1 && i < s.length(); j--) 
                strList[j].add(Character.toString(s.charAt(i++)));
            
        }
        
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < numRows; i++) 
            for(String str : strList[i]) 
                ans.append(str);
        
        return ans.toString();
    }
}
