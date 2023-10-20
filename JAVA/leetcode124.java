//leetcode link -> https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
//124. Binary Tree Maximum Path Sum

/*Problem Statement---------------------------------------------------------------
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.
---------------------------------------------------------------
*/

/*Approach----------------------
It is recursive problem 
It traverse left branch of root 
It traverse right branch of root
It compares which one has highest value among 2, consider it as max path sum 
*/

class Solution {
    int res=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root==null)
        return 0;
        helper(root);

        return (int) res;
    }
    public int helper(TreeNode root) {
        if(root==null)
        return 0;

        int left=Math.max(helper(root.left),0);
        int right=Math.max(helper(root.right),0);
        res=Math.max(res,(left+right+root.val));
        return Math.max(root.val+left,right+root.val);
    } 

}

// Complexity Analysis---------------------------------------------------
// TC: O(N)
// SC: O(N)
//-----------------------------------------------------------------------
