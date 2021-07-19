import java.util.*;   

class Solution {
    public int solution(String s) {
        int ret = 987654321;

        for(int i = s.length() / 2; i > 0; i--) {
            StringBuilder sb = new StringBuilder(s), ans = new StringBuilder();

            while(sb.length() >= i) {
                int cnt = 0; String tmp = sb.substring(0, i);
                while(sb.length() >= i && tmp.equals(sb.substring(0, i))) {
                    cnt++;
                    sb = new StringBuilder(sb.substring(i));
                }
                ans.append(cnt > 1 ? String.valueOf(cnt) + tmp : tmp);
            }

            ans.append(sb);
            ret = Math.min(ret, ans.length());
        }

        return Math.min(ret, s.length());
    }
}
