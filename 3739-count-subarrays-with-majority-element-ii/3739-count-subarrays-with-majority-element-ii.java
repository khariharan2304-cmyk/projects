// Convert the array:
//
// target     -> +1
// everything -> -1
//
// Now a subarray has target as the majority
// exactly when its sum is strictly greater than 0.
//
// prefix[i] stores the sum of the first i elements.
//
// prefix[0] = 0 means we have processed 0 elements.
//
// For any subarray:
//
// sum = currentPrefix - previousPrefix
//
// We need:
// sum > 0
//
// Therefore:
// currentPrefix > previousPrefix
//
// So for every current prefix, we need to count how many
// PREVIOUS prefix sums are STRICTLY smaller than it.
//
// The Fenwick Tree stores the frequency of prefix sums
// that we have already seen.
//
// We shift every prefix sum by n because prefix sums can
// be negative, while Fenwick Tree indices must be positive.
//
// Example:
//
// prefix = -1
// shifted index = -1 + n
//
// query(prefix[i] + n - 1)
//
// counts all previous prefix sums strictly smaller
// than the current prefix.
//
// We query BEFORE updating so that the current prefix
// itself is not included.
//
// After the query, we insert the current prefix:
//
// update(prefix[i] + n)
//
// This means:
// "I have now seen this prefix sum once."
//
// The initial prefix[0] = 0 is also processed.
// It represents the boundary before the array starts,
// allowing us to count subarrays that begin at index 0.
//
// Overall:
//
// Time:  O(n log n)
// Space: O(n)
class Fenwick{
    long tree[];
    int n;

    Fenwick(int n){
        this.n = n;
        tree = new long[n];
    }

    void update(int i){
        i++;

        while(i < n){
            tree[i]++;
            i = i + (i & -i);
        }

    }

    long query(int i){
        i++;

        long ct = 0;

        while(i > 0){
            ct += tree[i];
            i = i - (i & -i);
        }

        return ct;
    }

}
class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int arr[] = new int[n];

        for(int i=0;i<n;i++){
            if(nums[i] == target)
                arr[i] = 1;
            else
                arr[i] = -1;
        }

        int prefix[] = new int[n+1];

        for(int i=0;i<n;i++)
            prefix[i+1] = arr[i] + prefix[i];
        
        Fenwick f = new Fenwick(2 * n + 2);

        long count = 0;

        for(int i=0;i<n+1;i++){

            count += f.query(prefix[i] + n - 1);
            f.update(prefix[i] + n);

        }

        return count;
    }
}