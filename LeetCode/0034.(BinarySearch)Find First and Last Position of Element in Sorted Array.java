class Solution {

    public int binarySearch(int[] nums, int target, int left, int right) {

        while(left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] < target) 
                left = mid + 1;
            else // 즉, nums[mid] == target일 때도 mid - 1을 right에 넣어줌으로써 lower bound 값을 연산하게 된다는 의미
                right = mid - 1;
        }

        return left;
    }

    public int[] searchRange(int[] nums, int target) {

        int left  = binarySearch(nums, target, 0, nums.length - 1);
        int right = binarySearch(nums, target + 1, 0, nums.length - 1) - 1;
        
        // 빈 Array 케이스를 피하기 위한 left < nums.length 조건 추가
        if (left < nums.length && nums[left] == target)
            return new int[] {left, right};

        return new int[] {-1, -1};
    }
}
