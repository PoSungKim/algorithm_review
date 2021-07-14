import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.reflect.Method;

class Solution {

    List<Map<String, Integer>> hm = new ArrayList<>();
    int[] maxCnt = new int[10];

    public String[] solution(String[] orders, int[] course) {

        // printMethods(new ArrayList<>());

        for(int i = 0 ; i < 10; i++) {
            hm.add(new HashMap<String, Integer>());
        }

        for(String str : orders) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            comb(s, new StringBuilder(), 0);
        }
        List<String> ls = new ArrayList<>();
        for(int len : course) {
            for(Map.Entry<String, Integer> e : hm.get(len - 1).entrySet()) {
                if (e.getValue() >= 2 && e.getValue() == maxCnt[len - 1]) {
                    ls.add(e.getKey());
                }
            }    
        }

        Collections.sort(ls);

        String[] ans = new String[ls.size()];
        for(int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }

        return ans;
    }

    public void comb(char[] s, StringBuilder s2, int depth) {
        if (depth == s.length) {
            if (s2.length() >= 2) {
                int cnt = hm.get(s2.length() - 1).getOrDefault(s2.toString(), 0) + 1;
                hm.get(s2.length() - 1).put(s2.toString(), cnt);
                maxCnt[s2.length() - 1] = Math.max(maxCnt[s2.length() - 1], cnt);
            }
            return;
        }

        comb(s, s2.append(s[depth]), depth + 1);
        s2.setLength(s2.length() - 1);
        comb(s, s2, depth + 1);
    }

    public void printMethods(Object o) {
        for(Method m : o.getClass().getMethods()) {
            System.out.println(m);
        }
    }
}
