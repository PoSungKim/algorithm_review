import java.util.*;

public class Solution {

    public List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        for(int i = 0; i < nums.length && nums[i] <= 0; i++) {
            
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int s = i,
                l = i + 1, 
                r = nums.length - 1;

            while(l < r) {
                int sum = nums[s] + nums[l] + nums[r];

                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    ans.add(List.of(nums[s], nums[l], nums[r]));
                    l++;

                    while (l < r && nums[l - 1] == nums[l]) {
                        l++;
                    } 
                }
            }
        }

        return ans;
    }
}
