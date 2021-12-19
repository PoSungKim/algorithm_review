import java.util.*;

class Solution {
    
    public boolean[][][] board;
    
    public boolean isOkay(int x, int y, int a) {
        if (a == 0) {
            if (y == 0) {
                return true;
            } else if (x - 1 >= 0 && board[y][x - 1][1] || board[y][x][1]) {
                return true;
            } else if (y - 1 >= 0 && board[y - 1][x][a]) {
                return true;
            } 
        } else {
            if (x - 1 >= 0 && board[y][x - 1][a] && x + 1 < board.length && board[y][x + 1][a]) {
                return true;
            } else if ( (y - 1 >= 0 && board[y - 1][x][0]) || (y - 1 >= 0 && x + 1 < board.length && board[y - 1][x + 1][0]) ) {
                return true;
            }
        }
        return false;
    }
    
    public void build(int x, int y, int a) {
        if (isOkay(x, y, a))
            board[y][x][a] = true;
    }
    
    public void delete(int x, int y, int a) {
        board[y][x][a] = false;
        for(int i = 0; i < board.length; i++) 
            for(int j = 0; j < board[i].length; j++) 
                for(int k = 0; k < 2; k++) 
                    if (board[i][j][k]) 
                        if (!isOkay(j, i, k)) 
                            board[y][x][a] = true;
            
    }
    
    public int[][] emitAns() {
        List<int[]> ansList = new ArrayList<>();
        for(int i = 0; i < board.length; i++) 
            for(int j = 0; j < board[i].length; j++) 
                for(int k = 0; k < 2; k++) 
                    if (board[i][j][k]) 
                        ansList.add(new int[] {j, i, k});
        
        int[][] ans = new int[ansList.size()][3];
        for(int i = 0; i < ansList.size(); i++) 
            for(int j = 0; j < 3; j++) 
                ans[i][j] = ansList.get(i)[j];
        
        Arrays.sort(ans, (a,b)->{
            
            if(a[0] == b[0]) {
                if (a[1] == b[2])
                    return a[2] - b[2];
                return a[1] - b[1];
            }
            
            return a[0] - b[0];
        });
        
        return ans;
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        
        board = new boolean[n + 1][n + 1][2];
        
        for(int[] frame : build_frame) {
            int x = frame[0],
                y = frame[1],
                a = frame[2],
                b = frame[3];
            
            if (b == 1) build(x, y, a);    
            else delete(x, y, a);
            
        }
        
        return emitAns();
    }
}
