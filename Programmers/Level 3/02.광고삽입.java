class Solution {
    
    int convert(String time) {
        String[] nums = time.split(":");
        return Integer.parseInt(nums[0]) * 60 * 60 +
               Integer.parseInt(nums[1]) * 60 +
               Integer.parseInt(nums[2]);
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec    = convert(play_time);
        int advSec     = convert(adv_time);
        int[] totalSec = new int[100 * 60 * 60];
        
        for(String log : logs){
            int start = convert(log.substring(0, 8));
            int end = convert(log.substring(9, 17));
            
            for(int i = start; i < end; i++) 
                totalSec[i] += 1;
        }
        
        long curSum = 0;
        for(int i = 0; i < advSec; i++)
            curSum += totalSec[i];
        
        long maxSum = curSum;
        int maxIdx = 0;
        for(int i = advSec; i < playSec; i++) {
            curSum = curSum + totalSec[i] - totalSec[i - advSec]; 
            if (curSum > maxSum) {
                maxSum = curSum;
                maxIdx = i - advSec + 1;
            }
        }
        // %02d : decimal 타입, 길이 2, 남은 자리 0으로 fill
        return String.format("%02d:%02d:%02d", 
                             maxIdx / 3600, 
                             maxIdx / 60 % 60,
                             maxIdx % 60
                            );
    }
}
