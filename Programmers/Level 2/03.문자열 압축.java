import java.util.*;   

class Solution {
    public int solution(String s) {
        int ret = s.length();

        // 1.1 이렇게 나눗셈이 들어간 부분에서는 exception case를 조심해야 한다 : "a"처럼 길이가 1인 문자열이 들어올 수 있다
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
        // 1.2 "a"처럼 길이가 1이면, 압축이 불가능해서 답이 1과 같다
        return ret;
    }
}
