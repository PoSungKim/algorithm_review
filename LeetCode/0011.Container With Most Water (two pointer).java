            class Solution {
    public int maxArea(int[] height) {
        int max   = 0;
        int left  = 0;
        int right = height.length - 1;
        
        while(right > left) {
            int smaller = Math.min(height[left], height[right]);
            max = Math.max(max, smaller * (right - left));
            
            if (height[left] > height[right]) right--;
            else left++;
        }
        
        return max;
    }
}
