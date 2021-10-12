import java.util.*;
// '.' : 빈 칸
// '#' : 장애물 또는 벽
// 'O' : 구멍의 위치
// 'R' : 빨간 구슬의 위치 
// 'B' : 파란 구슬의 위치

public class Main {
    public static char[][] Board = new char[11][11];
    public static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        // Scanner에서는 char을 인식해서 가져오는 Method를 제공하지 않기 때문에
        // next()로 String 값을 가져온 뒤에 toCharArray()로 변환해서 넣어주면 효율적으로 처리 가능
        for(int i = 0; i < N; i++) Board[i] = sc.next().toCharArray();            
        
        System.out.println("Hello World");
    }
}