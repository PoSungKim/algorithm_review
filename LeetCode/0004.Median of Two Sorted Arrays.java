class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int n = nums1.length + nums2.length;
        List<Integer> numList = new ArrayList<>();
        
        int idx1 = 0, idx2 = 0;
        while(idx1 < nums1.length || idx2 < nums2.length) {

            while(idx2 == nums2.length) {
                if (idx1 == nums1.length) break;
                numList.add(nums1[idx1++]);
            }

            while(idx1 == nums1.length) {
                if (idx2 == nums2.length) break;
                numList.add(nums2[idx2++]);
            }

            if (idx2 < nums2.length && nums1[idx1] >= nums2[idx2] ) {
                numList.add(nums2[idx2++]);
            } else if (idx1 < nums1.length && nums1[idx1] < nums2[idx2]) {
                numList.add(nums1[idx1++]);
            }
        }
        
        double ans;
        if (n % 2 == 0) {
            ans = ((double)numList.get((n / 2) - 1) + numList.get(n / 2)) / 2;
        } else {
            ans = (double)numList.get(n / 2);
        }
        
        return ans;
    }
}
