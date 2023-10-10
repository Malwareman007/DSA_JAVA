//Leetcode problem link -> https://leetcode.com/problems/decode-ways/solutions/

/* Problem Statement ---------------------------------------------------------------------------------------
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.
---------------------------------------------------------------------------------------
*/

/* Approach To Solve
  Keep in Mind : We have 2 optins take 1 index or take 2 index without starting index of 0.

  1.Recursive  Solution:
    i. take initialy l and r as 0. 
    ii. if char at inedex is not 0 then calculate and move to next index.
    ii. if char at inedex is not 0 and i+1 within range then move 2 index ahead.(Beacause we have taken 2 positions )
    iii. if we are at last position then, return 1. (Because we have made one of the possible combination)

*/


// 1. Recursive Solution (Bruetforce)(Give TLE)
class Solution {
    public int numDecodings(String s) {
      //call for recursive function
        return f(0,s);
    }
    int f(int i,String s){
      
        //return 1 if i is greater than length of string
        if(i>=s.length()){
            return 1;
        }
      
       //two possibiliest have 2 variables l and r
        int l=0,r=0;
      
      //check for '0' at index i in s
        if(s.charAt(i)!='0'){
            //if at i there is not a 0 then move by 1
            l=f(i+1,s);
          
            //i+1 < length of s  and at both i and i+1 index of s is smaller than 26 than move by 2
            if(i+1<s.length()  && Integer.parseInt(s.substring(i,i+2)) <= 26){
                r=f(i+2,s);
            }
        }
        return l+r;
    }
}

/* Complexity Analysis

Time Complexity- O(2^n)
Space Complexity - (n)

*/

//----------------------------------------------------------------------------------------------------------------
//2 . Memoize Solution - dp is used to store  already calculated values
class Solution {
    public int numDecodings(String s) {
        int[] dp=new int[s.length()];
        Arrays.fill(dp,-1);
        return f(0,s,dp);
    }
    int f(int i,String s,int[] dp){
        if(i>=s.length()){
            return 1;
        }
      
      //check if already computed and return it values
        if(dp[i]!=-1)return dp[i];
      
        int l=0,r=0;
        if(s.charAt(i)!='0'){
            l=f(i+1,s,dp);
            if(i+1<s.length()  && Integer.parseInt(s.substring(i,i+2)) <= 26){
                r=f(i+2,s,dp);
            }
        }
      
        //store final answer in dp 
        return dp[i]=l+r;
    }
}


/* Complexity Analysis

Time Complexity- O(n)
Space Complexity - (n+n)->(2n)->(n)

*/


//-----------------------------------------------------------------------------------------------------------------
// 3. Tabulation 
class Solution {
    public int numDecodings(String s) {
        int n=s.length();
        int[] dp=new int[n+1];
      
        //Base case according to recursive function
        dp[n]=1;
      
       // Instaed of recursive call loop is used from n-1 to 0 and ,
       // dp is used there instead of next function call
        for(int i=n-1;i>=0;i--){
            int l=0,r=0;
            
            if(s.charAt(i)!='0'){
                l=dp[i+1];
                if(i+1<s.length()  && Integer.parseInt(s.substring(i,i+2)) <= 26){
                    r=dp[i+2];
                }
            }
            dp[i]=l+r;
        }

        //return ans at 0 index in dp 
        return dp[0];
    }
}

/* Complexity Analysis

Time Complexity- O(n)
Space Complexity - (1)

*/

