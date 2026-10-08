class Solution:
    def __init__(self):
        self.maxSum = 0
        
    def maxSumBST(self, root):
        def postOrderTraverse(node):
            """ 
            Perform post order traversal of tree
            to determine sub trees which are BSTs
            and calculate maximum sum of its elements.
            
            Returns:
            isValidBST: True if valid BST else False
            currentSum: sum of current sub tree. None 
                        if not a valid BST.
            currentMin: minimum value of current sub tree
            currentMax: maximum value of current sub tree
            """
            if not node:
                return True, 0, float('inf'), float('-inf') # Empty sub tree

            lValidBST, lSum, lMin, lMax = postOrderTraverse(node.left)
            rValidBST, rSum, rMin, rMax = postOrderTraverse(node.right)

            # Check if current subtree is a valid BST
            if lValidBST and rValidBST and lMax < node.val < rMin: 
                currSum = lSum + rSum + node.val
                currMin = lMin if lMin != float('inf') else node.val
                currMax = rMax if rMax != float('-inf') else node.val
                self.maxSum = max(self.maxSum, currSum)  # update max sum
                return True, currSum, currMin, currMax
            
            return False, None, None, None 
        
        postOrderTraverse(root)
        return self.maxSum