import java.util.Arrays;

class Solution {
    
    static final int INF = 40000000;
    int[][] Dist = new int[200][200];
        
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        for(int i = 0; i < 200; i ++) {
            for(int j = 0; j < 200; j++) {
                if (i == j)
                    Dist[i][j] = 0;
                else
                    Dist[i][j] = INF;
            }
        }
        
        for(int[] edge : fares) {
            Dist[edge[0] - 1][edge[1] - 1] = edge[2];
            Dist[edge[1] - 1][edge[0] - 1] = edge[2];
        }
        
        floyd(n); s--; a--; b--;
        
        int answer = INF;
        for(int k = 0; k < n; k++) 
            // Dist[s][k] : 합승한 최단 거리 | Dist[k][a], Dist[k][b] : 합승 이후의 최단 거리들 
            answer = Math.min(answer, Dist[s][k] + Dist[k][a] + Dist[k][b]);
        
        return answer;
    }
    
    // int n : Node 개수
    public void floyd(int n) {
        // int k : 경유하는 Node
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if (Dist[i][k] + Dist[k][j] < Dist[i][j])
                        Dist[i][j] = Dist[i][k] + Dist[k][j];
                }
            }
        }
    }
}
