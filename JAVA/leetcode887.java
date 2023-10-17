//Leetcode Problem link -> https://leetcode.com/problems/super-egg-drop/

/*
You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.

You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.

Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.

Return the minimum number of moves that you need to determine with certainty what the value of f is.
*/

/*Approach 
Try some examples you can think about binary search , but we to use recurion and dp

It is mcm problem with binary search
1. We have 2 choices egg may burst or may not.
2.if burst then count of egg reduce by 1 and we have also checked 1 floor so it fill decrease by 1
3 if not burst then we have only upper part which is n-i floors only egg remains same .
4.use temp ans to get max of both options and update finaly ans which is minimum.
5.use binary search to optimise it 
6.use dp for same recursive call.


*/


class Solution {
    public int superEggDrop(int k, int n) {
        //Initialising dp
        int[][] dp=new int[k+1][n+1];
        for(int[] i:dp)Arrays.fill(i,-1);
        
        // call for recursive function
        return f(k,n,dp);
    }
    int f(int k,int n,int[][] dp){
        //Base case
        if(k<=1 || n<=1)return n;
        //Checking in dp
        if(dp[k][n]!=-1)return dp[k][n];

        //Initialising ans
        int ans=Integer.MAX_VALUE;
        int l=1,r=n;
        
        //Binary Serach On all the floors
        while(l<=r){
                int mid=(l+r)/2;
                //if burst then reduce count of egg and floor by 1
                int burst=f(k-1,mid-1,dp);
            
                //if not burst then   egg remains same and reduce count of floor by n-mid
                int notburst=f(k,n-mid,dp);
            
                //update ans in local variable
                int temp=1+Math.max(burst,notburst);

                
                if(burst<notburst){
                    l=mid+1;
                }else{
                    r=mid-1;
                }
                ans=Math.min(ans,temp);
        }
        
        return dp[k][n]=ans;
    }
}


/* Complexity Analysis
Time Complexity : O(n2log(n))
Space complexity: O(n2)

*/
