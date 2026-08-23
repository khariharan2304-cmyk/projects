class Solution {
    public int maxSubArray(int[] nums) {
        int maxs=Integer.MIN_VALUE;
        int subAsum=0;
        for (int i=0; i<nums.length; i++){
            subAsum+=nums[i];
            if (maxs<subAsum){
                maxs=subAsum;
            }
            
            if (subAsum<0){
                subAsum=0;
            }
        }
        return maxs;
    }
}