class Solution {
    public int solution(int X, int Y, int D) {
        
        return ((Y-X) % D == 0)? (Y-X) / D +  0 : (Y-X) / D + 1;
    }
}
// Time Complexity : O(1)
