class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> map = new HashMap<>(); 
        int ones = 0;
        for(int t : nums) {
            if(t == 1) {
                ones++;
                continue;
            }
            long n = (long) t;
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        if(ones % 2 == 0) ones--;

        int max = Math.max(1, ones);

        for(long el : map.keySet()) {
            if(map.containsKey(el) && map.get(el) > 1) {
                int k = 0;
                long x = el;
                while(map.containsKey(x) && map.get(x) > 1) {
                    k++;
                    x *= x;
                }
                if(map.containsKey(x) && map.get(x) == 1) {
                    k++;
                } 
                max = Math.max(max, (2 * k) - 1);
            }
        }

        return max;
    }
}