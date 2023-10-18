/*LeetCode Problem Link : https://leetcode.com/problems/remove-nth-node-from-end-of-list/solutions/2633379/java-1ms-comments-explained/ */
// Level : Medium

/*Problem : Given the head of a linked list, remove the nth node from the end of the list and return its head.

 

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


 /*Approach : Idea is - since this is a singly linked list, there is no way to iterate from tail and count backwards. Instead what we can do is we can keep two pointers at the required given gap and traverse till the end. When we hit the end, the first pointer will give us the reference to the node to be deleted. */

 
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
		dummyNode.next = head;
		ListNode l1 = dummyNode;
		ListNode l2 = dummyNode;
		while (n-- != 0) l2 = l2.next;

		while (l2.next != null) {
			l1 = l1.next;
			l2 = l2.next;
		}
		l1.next = l1.next.next;
		return dummyNode.next;
    }
}