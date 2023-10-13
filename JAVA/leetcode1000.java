//leetcode link -> https://leetcode.com/problems/minimum-cost-to-merge-stones/description/
//1000. Minimum Cost to Merge Stones

/*Problem Statement---------------------------------------------------------------
There are n piles of stones arranged in a row. The ith pile has stones[i] stones.

A move consists of merging exactly k consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these k piles.

Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.
---------------------------------------------------------------
*/

/*Approach----------------------
It is recursive problem and optimised by dp (mcm - problem)
1.((n-1)%(k-1) == 0) only then ans i possible
2.use prfix sum
3.cost=prefix[j+1] - prefix[i]

*/


class Solution {
    int[][] dp = new int[32][32];

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        
        // If it's not possible to merge the stones using the given 'k', return -1.
        if ((n - 1) % (k - 1) != 0) return -1;
        
        int[] pr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pr[i] = pr[i - 1] + stones[i - 1];
        }
        
        // Initialize the 'dp' array with -1 to indicate that no results are calculated yet.
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }
        
        // Call the recursive function 'f' to solve the problem.
        return f(0, n - 1, stones, k, pr);
    }

    int f(int i, int j, int[] stones, int k, int[] pr) {
        // Base case 1: If 'i' is greater than or equal to 'j', there are no stones to merge, so return 0.
        if (i >= j) return 0;
        
        // Base case 2: If the result for this subproblem is already calculated, return it.
        if (dp[i][j] != -1) return dp[i][j];
        
        int ans = Integer.MAX_VALUE;
        
        // Iterate through possible positions to split the stones.
        for (int s = i; s < j; s += k - 1) {
            // Calculate the cost of merging the left and right subarrays recursively.
            int sum = f(i, s, stones, k, pr) + f(s + 1, j, stones, k, pr);
            ans = Math.min(ans, sum);
        }
        
        // If the total number of stones from 'i' to 'j' can be evenly divided by 'k-1',
        // add the prefix sum difference to 'ans'.
        if ((j - i) % (k - 1) == 0) {
            ans += pr[j + 1] - pr[i];
        }
        
        // Store the result in the 'dp' array and return it.
        return dp[i][j] = ans;
    }
}

// Complexity Analysis---------------------------------------------------
// TC: O((N^3)/K) here as we increment by k piles in every step
// SC: O(N) for prefix sum + O(N^2) for memo + O(N) auxilary stack space
//-----------------------------------------------------------------------
