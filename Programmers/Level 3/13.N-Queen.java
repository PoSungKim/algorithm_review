class Solution {
    int[] Board;
    int answer = 0;
    
    public void playChess(int pos) {
        
        if (pos == Board.length) {
            answer++;
            return;
        }
        
        for(int i = 0; i < Board.length; i++) {
            boolean isOkay = true;
            for(int j = 0; j < pos; j++) {
                if (Math.abs(j - pos) == Math.abs(Board[j] - i) || Board[j] == i) {
                    isOkay = false;
                    break;
                }
            }
            if (isOkay) {
                Board[pos] = i;
                playChess(pos + 1);    
            }
        }
    }
    
    public int solution(int n) {
        Board = new int[n];
        playChess(0);
        
        return answer;
    }
}
