class Solution {
    
    public int trans(int n) {
        long ans = 0;
        
        while(n > 0) {
            ans += n % 10;   
            
            n /= 10;
            if (n == 0) break;
            
            ans *= 10;
        }
        
        if (-2147483648 > ans || ans > 2147483647) return 0;
        
        return (int)ans;
    }
    
    public int reverse(int x) {
        return (x >= 0) ? trans(x) : -1 * trans(-1 * x);
    }
}
