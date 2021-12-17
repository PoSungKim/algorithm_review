import java.util.*;

class Solution {
    List<List<Integer>> distList = new ArrayList<>();;
    static final int MAX = 987654321;
    Integer[] tmpList;
    
    public void perm(int cnt, int flag, int n, int[] dist) {
        
        if (cnt == n) {
            List<Integer> tmp = new ArrayList<>(Arrays.asList(tmpList));
            distList.add(tmp);
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if ((flag & (1 << i)) != 0) continue;
            tmpList[cnt] = dist[i];
            perm(cnt + 1, flag | (1 << i), n, dist);
        }
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        List<Integer> M = new ArrayList<>();
        for(int num : weak) M.add(num);
        
        tmpList = new Integer[dist.length];
        perm(0,0, dist.length, dist);
        
        int answer = MAX;
        for(List<Integer> d : distList) {
            for(int i = 0; i < dist.length; i++) dist[i] = d.get(i);
            
            // for(int m = 0; m < 2; m++) {
                for(int i = 0; i < M.size(); i++) {
                    List<Integer> L = new ArrayList<>(M);

                    int curPos = L.get(0),
                        cnt    = 0,
                        tmpAns = 0;

                    for(int j = dist.length - 1; j >= 0; j--) {
                        tmpAns++;
                        if (tmpAns > answer) break;
                        
                        for(int k = 0; k <= dist[j]; k++) {
                            if (curPos == L.get(0)) {
                                cnt++;
                                L.remove(0);
                            }
                            if (L.size() == 0) {
                                answer = Math.min(answer, tmpAns);
                                break;
                            }
                            // curPos = (m == 0) ? (curPos + 1) % n : curPos - 1;
                            curPos = (curPos + 1) % n ;
                        }
                        if (L.size() > 0)
                            curPos = L.get(0);
                        else
                            break;
                    }
                    Collections.rotate(M, 1);
                }
                // Collections.reverse(M);
            // }
        }
        
        if (answer == MAX) return -1;
        return answer;
    }
}
