import java.util.ArrayList;
import java.util.HashMap;

public class SmallestSubarrayWithKDistinctElements {
    /*
    Time Complexity: O(N)
    Space Complexity: O(N)
    where 'N' is the total number of integers.
    */
    /*
     * Smallest Subarray With K Distinct Elements
     * Given an array 'A' consisting of 'N' integers, find the smallest subarray of 'A' containing exactly 'K' distinct integers.
     * Link: https://www.codingninjas.com/studio/problems/smallest-subarray-with-k-distinct-elements_630523
     */
    public static ArrayList<Integer> smallestSubarrayWithKDistinct(ArrayList<Integer> arr, int K) {
        // start -> start index of resultant subarray
        // end   -> end index of resultant subarray
        int n = arr.size();
        int start = 0;
        int end = n;

        HashMap<Integer, Integer> map = new HashMap<>();

        // i -> start index of current subarray
        // j -> next index to the end of current subarray
        int i = 0, j = 0;
        while (j < n) {
            // Add the current element to the map
            map.put(arr.get(j), map.getOrDefault(arr.get(j), 0) + 1);
            j++;

            /*
			If set contains exactly k elements,Consider subarray[i, j - 1],
            if it is smaller in size than the current resultant subarray
            and keep removing left most elements.
            */
            while (map.size() == K) {
                if (j - 1 - i < end - start) {
                    start = i;
                    end = j - 1;
                }

                // Remove elements from left
                if (map.get(arr.get(i)) == 1) {
                    map.remove(arr.get(i));
                } else {
                    map.put(arr.get(i), map.get(arr.get(i)) - 1);
                }
                i++;
            }
        }

        // Array to store result.
        ArrayList<Integer> ans = new ArrayList<Integer>();

        // There is no subarray with K distinct elements
        if (end == n) {
            ans.add(-1);
        } else {
            // Subarray found
            ans.add(start);
            ans.add(end);
        }

        return ans;
    }
}




