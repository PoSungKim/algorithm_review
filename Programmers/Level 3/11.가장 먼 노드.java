import java.util.*;

class Solution {
    public boolean[][] graph;
    public boolean[] visited;
    public int[] dist;
    public int maxN = -1;
    
    public void bfs(int startNode, int n) {
        Queue<Integer> myQueue = new LinkedList<>();
        visited[0] = true;
        myQueue.offer(startNode);
        
        while(!myQueue.isEmpty()) {
            int node = myQueue.poll();
            maxN = Math.max(maxN, dist[node]);
            
            for(int next = 0; next < n; next++) {
                if (graph[node][next] && !visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[node] + 1;
                    myQueue.offer(next);
                }
            }
            
        }
    }
    
    public int solution(int n, int[][] edge) {
        graph    = new boolean[n][n];
        visited  = new boolean[n];
        dist     = new int[n];
        
        for(int[] e : edge) {
            int u = e[0] - 1,
                v = e[1] - 1;
            graph[u][v] = true;
            graph[v][u] = true;
        }
        
        bfs(0, n);
        
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
            if (dist[i] == maxN)
                ans.add(i + 1);
        
        return ans.size();
    }
}
