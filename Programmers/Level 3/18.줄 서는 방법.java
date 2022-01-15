// top-down 재귀함수 + 메모이제이션 + 순열
// 순열을 하나 하나 다 구하는 것이 아니라, 순열의 패턴을 보고, 특정 순서의 순열 값을 바로 구하는 것이 주요 개념
// 순열에서는 n번 자리의 값은 전 자리의 순열들이 모두 합쳐진 패턴을 지닌다는 점을 활용하는 것이 주요!
// 시작 k 값은 0부터 index가 시작하니 k = k - 1
// n번 자리의 순서 값은 (k) / !(n-1)
// 다음 번 자리의 k 값은 (k) %= (n-1)

import java.util.*;

class Solution {
    List<Integer> List = new ArrayList<>();
    long[] fact = new long[21];
    public long fact(int n) {
        
        if (fact[n] != 0)
            return fact[n];
        
        if (n <= 1) return 1;
        
        return fact[n] = n * fact(n - 1);
    }
    
    public int[] solution(int n, long k) {
        
        for(int i = 0; i < n; i++) List.add(i + 1);
        
        int[] answer = new int[n];
        int i = 0;
        k--;
        
        while(n-- > 0) {
            long idx = k / fact(n);
            answer[i++] = List.remove((int)idx);
            
            k %= fact(n); 
        }
        
        return answer;
    }
}
