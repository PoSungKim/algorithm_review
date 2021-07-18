import java.util.List;
import java.util.ArrayList;

class Solution {
    static final int INF = 987654321;
    int[] Sales;
    List<List<Integer>> Children = new ArrayList<>();
    int[][] Cost = new int[300000][2];
    
    void traversal(int node) {
        // Cost[node] : node를 Root로 하는 Subtree가 갖는 최소 비용
        Cost[node][0] = 0;
        Cost[node][1] = Sales[node];
        
        // 현 Node의 자식이 없다면 return
        if(Children.get(node).isEmpty()) return;
        
        int extraCost = INF;
        // 현 Node의 자식이 있다면 DFS Traversal
        for(int child : Children.get(node)) {
            traversal(child);
            // 만약 현 팀원을 미포함하는 것이 낫다면, 팀장 포함
            // extraCost는 모든 팀원들이 미포함될 수도 있기 때문에,
            // 만약에 추후에 한 명을 넣어야 할 때 추가 발생하는 비용을 미리 기록
            if(Cost[child][0] < Cost[child][1]) {
                Cost[node][0] += Cost[child][0];
                Cost[node][1] += Cost[child][0];
                extraCost = Math.min(extraCost, 
                                 Cost[child][1] - Cost[child][0]);
            // 만약 현 팀원을 포함하는 것이 낫다면, 팀장 미포함
            } else {
                Cost[node][0] += Cost[child][1];
                Cost[node][1] += Cost[child][1];
                extraCost = 0;
            }
        }
        
        // 팀장이 미포함되어서, 팀원들 중 한 명이 들어와야할 때의 값 
        // extraCost = (들어왔을 때의 값 - 안 들어온다는 것을 가장하여 이미 넣은 값)
        Cost[node][0] += extraCost;
    }
    
    public int solution(int[] sales, int[][] links) {
        Sales = sales;
        for(int i = 0; i < sales.length; i++) {
            Children.add(new ArrayList<>());
        }
        // Children[parent][children]
        for (int [] link : links) 
            Children.get(link[0] - 1).add(link[1] -1);
        
        traversal(0);
        return Math.min(Cost[0][0], Cost[0][1]);
    }
}
