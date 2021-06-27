import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;

class Solution {
    
    ArrayList<ArrayList<Integer>> globalList = new ArrayList<ArrayList<Integer>>();
    
    public int solution(int[] nums) {
        int answer = 0;
        
        comb(nums, 0, nums.length, new ArrayList<Integer>());
        
        for(ArrayList<Integer> list : this.globalList)
            if (isPrime(list))
                answer += 1;

        return answer;
    }
    
    public void comb(int[] nums, int pos, int maxPos, ArrayList<Integer> list) {
        
        if (pos == maxPos || list.size() == 3) {
            if (list.size() == 3) {
                globalList.add(new ArrayList<Integer>());
                globalList.get(globalList.size() - 1).addAll(list);
            }  
            
            return;
        }
        
        list.add(nums[pos]);
        comb(nums, pos + 1, maxPos, list);
        list.remove(list.size() - 1);
        comb(nums, pos + 1, maxPos, list);
    }
    
    public boolean isPrime(ArrayList<Integer> list) {
        Integer sum = 0;
        for(Integer element : list)
            sum += element;
        
        for(int i = 2; i * i <= sum; i++) 
            if (sum % i == 0)
                return false;
        
        return true;
    }
}
