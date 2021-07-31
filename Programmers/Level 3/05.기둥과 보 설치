import java.util.*;

class Solution {
    
    boolean[][] Bar;
    boolean[][] Pillar;
    
    public boolean checkPillar(int y, int x) {
        
        if (y == 0 || Pillar[y - 1][x])
            return true;
        
        if ((x > 0 && Bar[y][x - 1]) || Bar[y][x])
            return true;
        
        return false;
    }
    
    public boolean checkBar(int y, int x) {
        
        if ( (y > 0 && Pillar[y - 1][x]) || (y > 0 && Pillar[y - 1][x + 1]))
            return true;
        
        if ( (x > 0 && Bar[y][x - 1]) && Bar[y][x + 1])
            return true;
        
        return false;
    }
    
    public boolean canDelete(int y, int x) {
        
        for(int i = Math.max(y -1 ,0); i <= y + 1; i++) {
            for(int j = Math.max(x - 1, 0); j <= x + 1; j++ ){
                if (Pillar[i][j] && checkPillar(i, j) == false) 
                    return false;
                if (Bar[i][j] && checkBar(i, j) == false)
                    return false;
            }
        }
        
        return true;
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        Bar    = new boolean[n + 2][n + 2];
        Pillar = new boolean[n + 2][n + 2];
        int count = 0;
        
        for(int [] frame : build_frame) {
            int x    = frame[0], y    = frame[1];
            int type = frame[2], cmd  = frame[3];
            
            // 기둥 
            if (type == 0)  {
                if (cmd == 1) {
                    if (checkPillar(y, x)) {
                        Pillar[y][x] = true;
                        count++;
                    }
                }
                else {
                    Pillar[y][x] = false;
                    if (canDelete(y, x) == false) {
                        Pillar[y][x] = true;
                    }  else {
                        count--;
                    }
                }
            } else {
                if (cmd == 1) {
                    if (checkBar(y,x)) {
                        Bar[y][x] = true;
                        count++;
                    }
                } else {
                    Bar[y][x] = false;
                    if (canDelete(y, x) == false) {
                        Bar[y][x] = true;
                    } else {
                        count--;
                    } 
                }
            }
        }
        
        int[][] answer = new int[count][3];
        count = 0;
        for(int x = 0; x <= n; x++) {
            for(int y = 0; y <= n; y++) {
                if (Pillar[y][x]) {
                    answer[count][0] = x;
                    answer[count][1] = y;
                    answer[count++][2] = 0;
                    
                } 
                if (Bar[y][x]) {
                    answer[count][0] = x;
                    answer[count][1] = y;
                    answer[count++][2] = 1;
                }
            }
        }
        return answer;
    }
}
