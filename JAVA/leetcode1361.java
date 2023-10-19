//leetcode 1361. Validate Binary Tree Nodes -> https://leetcode.com/problems/validate-binary-tree-nodes/?envType=daily-question&envId=2023-10-17 


/* Problem------------------------------------------------------------------------------------------------------
You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.
-----------------------------------------------------------------------------------------------------------------
*/



/*Approach------------------------------------------------------------------------------------------------------
First of all you have to know what is binary tree.
1. In question root is not given ,So first try to find root.
2. Then find cycle if exixst then return false .
2. If it follow all the rules of binary search then return true .

-----------------------------------------------------------------------------------------------------------------
*/

class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        Queue<Integer> q=new LinkedList<>();
        Set<Integer> set=new HashSet<>();
      
       //Calculating root of tree
        Set<Integer> set2=new HashSet<>();
        for(int i=0;i<leftChild.length;i++){
          
          //if duplicate node (child) is there then , it does not follow binary tree .
           if(set2.contains(leftChild[i]) || set2.contains(rightChild[i]))return false;
          
           // store all child in set .
           if(leftChild[i]!=-1)set2.add(leftChild[i]);
           if(rightChild[i]!=-1)set2.add(rightChild[i]);
        }
      
        //find root , root will not present in child arrays.
        for(int i=0;i<n;i++){
          //here root was found , So add it in queue .
           if(!set2.contains(i)){
               q.add(i);
               break;
           }
          
        }
        
        // traverse at all children from root left to right (BFS is used here)
        while(!q.isEmpty()){
             int size=q.size();
             for(int i=0;i<size;i++){
                 int ind=q.poll();
               
                //if child already present in set ,then it make cycle ,which is not binary tree.
                 if(set.contains(ind)){
                     return false;
                 }else{
                     set.add(ind);
                 }

                // add left and right in queue for traverse on it.
                 if(leftChild[ind]!=-1){
                     q.add(leftChild[ind]);
                 }
                 if(rightChild[ind]!=-1){
                     q.add(rightChild[ind]);
                 }
             }
        }
      
      //check if there is  disjoint tree is there 
        for(int i=0;i<leftChild.length;i++){
           if(leftChild[i] !=-1 && !set.contains(leftChild[i]))return false;
           if(rightChild[i] !=-1 && !set.contains(rightChild[i]))return false;
        }
      
      //  return true , if it follow all the rules of binary tree.
        return true;
    }
}




/*Complexity Analysis-------------------------------------------------------------------------------------------------------------

Time  Complexity -> O(4*n) => O(n)

Space Comlexity -> O(2*n) => O(n)

----------------------------------------------------------------------------------------------------------------------------------
*/
