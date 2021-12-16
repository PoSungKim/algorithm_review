import java.util.*;

class Solution {
    
    public boolean[][][] board;
    public int N;
    
    public boolean check(int x, int y, int a){
        
        if (a == 0) { // 기둥
            if (y == 0) { // 바닥이거나
                return true;
            } else if (0 <= x - 1 && board[y][x - 1][1] || board[y][x][1]) { // 보의 한쪽 끝
                return true;
            } else if (0 <= y - 1 && board[y - 1][x][0]) { // 또 다른 기둥 위
                return true;
            }
        } else { // 보
            if ( (0 <= y - 1 && board[y - 1][x][0]) || (0 <= y - 1 && x + 1 < N && board[y - 1][x + 1][0]) ) { // 한쪽 끝 부분이 기둥 위
                return true;
            } else if (0 <= x - 1 && board[y][x - 1][1] && x + 1 < N && board[y][x + 1][1]) { // 양쪽 끝 부분이 다른 보와 동시에 연결
                return true;
            }
        }
        
        return false;
    }
    
    public void add(int x, int y, int a){
        if (check(x, y, a))
            board[y][x][a] = true;
    }
    
    public void remove(int x, int y, int a){
        
        board[y][x][a] = false;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < 2; k++) {
                    if (board[i][j][k]) {
                        if (!check(j, i, k)){
                            board[y][x][a] = true;
                            return;
                        }
                    }    
                }
            }
        }
        
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        n++;
        board = new boolean[n][n][2];
        N = n;
        
        for(int[] frame : build_frame) {
            int x = frame[0],
                y = frame[1],
                a = frame[2],
                b = frame[3];
            
            if (b == 1) {
                add(x, y, a);
            } else {
                remove(x, y, a);
            }
        }
        
        List<int[]> ansList = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < 2; k++) {
                    if (board[i][j][k]) {
                        ansList.add(new int[]{j, i, k});
                    }
                }
            }
        }
        
        Collections.sort(ansList, (a, b)-> {
            if (a[0] == b[0]) {
                if (a[1] == b[1]) 
                    return a[2] - b[2];
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        
        if (ansList.size() == 0)
            return new int[][]{};
        
        int[][] answer = new int[ansList.size()][3];
        for(int i = 0; i < ansList.size(); i++) {
            int[] a = ansList.get(i);
            answer[i][0] = a[0];
            answer[i][1] = a[1];
            answer[i][2] = a[2];
        }
            
        return answer;
    }
}
