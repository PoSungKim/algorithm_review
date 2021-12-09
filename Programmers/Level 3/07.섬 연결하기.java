import java.util.*;

class Solution {
    int[] parents;
    
    public int findParent(int Node) { 
        return (parents[Node] == Node) ? Node : findParent(parents[Node]);
    }
    
    public void union(int u, int v) {
        int rootU = findParent(u),
            rootV = findParent(v);
        
        if (rootU <= rootV)
            parents[rootV] = rootU;
        else
            parents[rootU] = rootV;
    }
    
    public int solution(int n, int[][] costs) {
        parents = new int[n];
        for(int i = 0; i < n; i++) parents[i] = i;
        
        Arrays.sort(costs, (a,b) -> {
            return a[2] - b[2];       
        });
        
        int ans = 0;
        
        for(int[] cost : costs) {
            
            int u = cost[0],
                v = cost[1],
                c = cost[2];
            
            if (findParent(u) != findParent(v)) {
                union(u, v);
                ans += c;
            }
        }
        
        return ans;
    }
}
