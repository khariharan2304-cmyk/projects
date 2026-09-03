import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
         // Sort indices according to nums[index]
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] values = new int[n];
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = nums[order[i]];
            pos[order[i]] = i;
        }

        // nextPos[i] = farthest node reachable in one edge
        int[] nextPos = new int[n];

        int j = 0;

        for (int i = 0; i < n; i++) {
            j = Math.max(j, i);

            while (j + 1 < n &&
                   values[j + 1] - values[i] <= maxDiff) {
                j++;
            }

            nextPos[i] = j;
        }

        // Binary lifting
        int LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = nextPos[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int u = queries[q][0];
            int v = queries[q][1];

            if (u == v) {
                answer[q] = 0;
                continue;
            }

            int left = pos[u];
            int right = pos[v];

            if (left > right) {
                int temp = left;
                left = right;
                right = temp;
            }

            int curr = left;
            int steps = 0;

            // Take maximum jumps that still leave us before right
            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][curr] < right) {
                    curr = up[k][curr];
                    steps += (1 << k);
                }
            }

            // One final jump should reach right
            if (nextPos[curr] >= right) {
                answer[q] = steps + 1;
            } else {
                answer[q] = -1;
            }
        }

        return answer;
    }
}