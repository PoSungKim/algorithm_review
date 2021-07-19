import java.util.*;   

class Solution {
    public int solution(String s) {
        int ret = 987654321;

        // 이렇게 나눗셈이 들어간 부분에서는 exception case를 조심해야 한다 : "a"처럼 길이가 1인 문자열이 들어올 수 있다
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
