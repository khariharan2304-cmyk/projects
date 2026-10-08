class Solution:
    def rob(self, root):
        dp = {}

        def rec(node):
            if not node:
                return 0
            if node in dp:
                return dp[node]

            not_take = rec(node.left) + rec(node.right)
            take = node.val

            if node.left:
                take += rec(node.left.left) + rec(node.left.right)
            if node.right:
                take += rec(node.right.left) + rec(node.right.right)

            dp[node] = max(take, not_take)
            return dp[node]

        return rec(root)