class Solution {
    public int countMajoritySubarrays(int[] arr, int target) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] == target) {
                    count++;
                }
                int len = j - i + 1;
                if (count > len / 2) {
                    ans++;
                }
            }
        }
        return ans;
    }
}