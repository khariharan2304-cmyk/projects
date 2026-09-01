class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
 
        Map<Integer, List<Integer>> indices = new HashMap<>();  // num -> sorted indices
        int[] idxs = new int[n];  // i -> index in indices[nums[i]]
        for (int i = 0; i < n; i++) {
            idxs[i] = indices.computeIfAbsent(nums[i], x -> new ArrayList<>()).size();
            indices.get(nums[i]).add(i);
        }
 
        List<Integer> result = new ArrayList<>();
        for (int q : queries) {
            List<Integer> qind = indices.get(nums[q]);
            int sz = qind.size();
            if (sz == 1) {
                result.add(-1);
            } else {
                int j = idxs[q];  // Get index in qind with O(1)
                int prev = qind.get((j - 1 + sz) % sz);
                int nextt = qind.get((j + 1) % sz);
                result.add(Math.min(
                    (nextt + n - qind.get(j)) % n,
                    (qind.get(j) - prev + n) % n
                ));
            }
        }
 
        return result;
    }
}