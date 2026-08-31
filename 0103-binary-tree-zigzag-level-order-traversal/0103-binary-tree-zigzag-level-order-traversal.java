class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        boolean reverse = false;

        while (!q.isEmpty()) {

            int lvlSize = q.size();

            List<Integer> subList = new ArrayList<>();

            while (lvlSize != 0) {

                TreeNode t = q.poll();

                subList.add(t.val);

                if (t.left != null) {
                    q.offer(t.left);
                }

                if (t.right != null) {
                    q.offer(t.right);
                }

                lvlSize--;
            }

            if (reverse) {
                Collections.reverse(subList);
            }

            res.add(subList);

            reverse = !reverse;
        }

        return res;
    }
}