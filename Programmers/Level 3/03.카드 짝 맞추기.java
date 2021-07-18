import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    class Point {
        int row, col, cnt;
        Point (int r, int c, int t) {
            row = r;
            col = c;
            cnt = t;
        }
    }
    
    int[][] Board;
    static final int   INF = 987654321;
    static final int[][] D = {{-1,0}, {1,0}, {0, -1}, {0, 1}};
    
    int bfs(Point src, Point dest) {
        boolean[][] visited = new boolean[4][4];
        Queue<Point> q = new LinkedList<>();
        q.add(src);
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            // 도착지점 도달 시, cnt 리턴
            if (cur.row == dest.row && cur.col == dest.col) 
                return cur.cnt;
            
            // 4 방향 BFS
            for(int i = 0; i < 4; i++) {
                // 새로운 이동 지점
                int nr = cur.row + D[i][0],
                    nc = cur.col + D[i][1];
                // 경계값 확인
                if (nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                
                // 아직 방문 X한 지점
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, cur.cnt + 1));
                }
                
                // ctr 키를 눌렀을 때
                for(int j = 0 ; j < 2; j++) {
                    
                    // 이미 카드 도달
                    if (Board[nr][nc] != 0) break;
                    // 이미 벽 도달
                    if (nr + D[i][0] < 0 || nr + D[i][0] > 3 ||
                        nc + D[i][1] < 0 || nc + D[i][1] > 3  )
                        break;
                    // 한칸 더 이동
                    nr += D[i][0];
                    nc += D[i][1];
                }
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, cur.cnt + 1));
                }
            }
        }
        
        return INF;
    }
    
    int permutate(Point src) {
        int ret = INF;
        // 삭제할 카드 번호를 1번부터 6번까지 차례대로 선택
        for(int num = 1; num <= 6; num++) {
            List<Point> card = new ArrayList<>();
            
            // 삭제할 카드 2장의 위치 찾기
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j ++) {
                    if (Board[i][j] == num) {
                        card.add(new Point(i, j, 0));
                    }
                }
            }
            // 해당 번호의 카드가 존재하지 않는 경우
            if (card.isEmpty()) continue;
            
            // src에서 순차적으로 이동 및 삭제 + 뒤집기 2장
            int one = bfs(src, card.get(0))  
                    + bfs(card.get(0), card.get(1))  
                    + 2;
            
            // src에서 역순으로 이동 및 삭제 + 뒤집기 2장
            int two = bfs(src, card.get(1))
                    + bfs(card.get(1), card.get(0))
                    + 2;
            
            // 뒤집은 카드 삭제 (0 처리)
            for(int i = 0; i < 2; i++) 
                Board[card.get(i).row][card.get(i).col] = 0;
            
            // 순차 및 역순 중 더 낮은 횟수 찾기
            // permutate(card.get(1 or 2)) : 삭제가 끝난 지점
            ret = Math.min(ret, one + permutate(card.get(1)));
            ret = Math.min(ret, two + permutate(card.get(0)));
            
            // 복원이후에 시작 숫자를 바꿔가면서 모든 경우를 시도
            for(int i = 0; i < 2; i++) 
                Board[card.get(i).row][card.get(i).col] = num;
            
        }
        
        // 모든 쌍의 카드가 삭제가 된 경우 : 더 이상 시도할게 없기에 0 리턴
        if (ret == INF) return 0;
        
        return ret;
    }
     
    public int solution(int[][] board, int r, int c) {
        Board = board;
        
        return permutate(new Point(r,c,0));
    }
}
