// Dynamic Programming : 
// 1) Optimal Substructure (큰 문제를 작은 문제들로 나누어서 해결)
// 2) Overlapping Subproblem (동일한 작은 문제들을 반복적으로 해결)
// 3) Top-Down(재귀), Bottom-Up(반복문) 2 방식 가능
// 4) Memoization 가능
public class DynamicProgramming {
    // 점화식 : fn = fn-1 + fn-2;
    // O(2^n)
    public static long topDownFibo(long N) {
        if (N == 1 || N == 2) return 1;
        System.out.format("F(%d) ", N);
        return topDownFibo(N - 1) + topDownFibo(N - 2);
    }

    public static final int MAX_N = 10000;
    public static long[] fibo = new long[MAX_N];
    public static long topDownMemoFibo(int N) {
        if (N == 1 || N == 2) return 1;
        if (fibo[N] != 0) return fibo[N];

        System.out.format("F(%d) ", N);
        return fibo[N] = topDownMemoFibo(N - 1) + topDownMemoFibo(N - 2);
    }
    
    public static long bottomUpFibo(int N){
        long[] fibo = new long[N + 1];
        fibo[1] = fibo[2] = 1;
        for(int i = 3; i <= N; i++)
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        
        return fibo[N];
    }
    public static void main(String[] args) {
        System.out.println("==========================");
        System.out.println("Top-Down 방식의 Fibonacci");
        System.out.println("==========================");
        System.out.println(topDownFibo(10));
        System.out.println("==========================");
        System.out.println("Top-Down + Memoization 방식의 Fibonacci");
        System.out.println("==========================");
        System.out.println(topDownMemoFibo(10));
        System.out.println("==========================");
        System.out.println("Bottom-Up 방식의 Fibonacci");
        System.out.println("==========================");
        System.out.println(bottomUpFibo(10));
    }
}