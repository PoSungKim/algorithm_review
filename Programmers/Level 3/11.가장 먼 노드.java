import java.util.*;

class Solution {
    public boolean[][] graph;
    public boolean[] visited;
    public int[] distList;
    public int maxN = -1;
    
    public void bfs(int startNode, int n) {
        Queue<int[]> myQueue = new LinkedList<>();
        visited[0] = true;
        myQueue.offer(new int[]{startNode, 0});
        
        while(!myQueue.isEmpty()) {
            int[] nodeInfo = myQueue.poll();
            int node = nodeInfo[0],
                dist = nodeInfo[1];
            maxN = Math.max(maxN, dist);
            
            for(int next = 0; next < n; next++) {
                if (graph[node][next] && !visited[next]) {
                    visited[next] = true;
                    distList[next] = dist + 1;
                    myQueue.offer(new int[]{next, dist + 1});
                }
            }
            
        }
    }
    
    public int solution(int n, int[][] edge) {
        graph    = new boolean[n][n];
        visited  = new boolean[n];
        distList = new int[n];
        
        for(int[] e : edge) {
            int u = e[0] - 1,
                v = e[1] - 1;
            graph[u][v] = true;
            graph[v][u] = true;
        }
        
        bfs(0, n);
        
        List<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
            if (distList[i] == maxN)
                ans.add(i + 1);
        
        return ans.size();
    }
}
