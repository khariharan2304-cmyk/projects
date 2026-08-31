/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int idx;
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int l = 0;
        int r = inorder.length - 1;

        // Step 1: Map inorder values to their indices for O(1) lookup 🗺️
        for (int i = 0; i <= r; i++) {
            map.put(inorder[i], i);
        }

        // Start postorder pointer from the last element (root of whole tree) 📍
        idx = postorder.length - 1;

        return helper(inorder, postorder, l, r);
    }

    public TreeNode helper(int[] inorder, int[] postorder, int left, int right) {
        // Base Case: Invalid range boundary 🍃
        if (left > right) return null;

        // Step 2: Extract current root value from postorder 🎯
        int rootVal = postorder[idx--];
        TreeNode nn = new TreeNode(rootVal);

        // Find root index in inorder array to divide left and right subtrees
        int mid = map.get(rootVal);

        // Step 3: MUST build RIGHT subtree first because postorder backward walk visits Right before Left! ⚠️
        nn.right = helper(inorder, postorder, mid + 1, right);
        nn.left = helper(inorder, postorder, left, mid - 1);

        return nn;
    }
}