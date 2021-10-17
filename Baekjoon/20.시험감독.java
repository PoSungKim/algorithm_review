import java.util.*;

public class Main {
    public static int[] A = new int[1000000];
    public static int N, B, C;

    public static void getInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); 
        for(int i = 0; i < N; i++) A[i] = sc.nextInt(); 
        B = sc.nextInt(); C = sc.nextInt();
    }

    public static long solve() {
        long cnt = 0;
        for(int i = 0; i < N; i++) {
            A[i] -= B; cnt++;
            if (A[i] > 0) {
                cnt += A[i] / C;
                if (A[i] % C > 0) cnt++; 
            }
        }
        return cnt;
    }
    public static void main(String[] args){
        getInput();
        // long       : 8B >> 대략 800경까지의 숫자까지 표현 가능
        // int        : 4B >> 대략 21억까지의 숫자까지만 표현 가능
        // worst case : 10^6 * 10^6 = 10^12 ~ 1조 단위까지 가기 때문에 int가 아닌 long 타입으로 리턴 필요
        // https://about-life.tistory.com/26
        System.out.println(solve());
    }
}
