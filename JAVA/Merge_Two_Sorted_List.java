/**
 You are given the heads of two sorted linked lists list1 and list2.
 Merge the two lists into one sorted list. The list should be made by
 splicing together the nodes of the first two lists.
 Return the head of the merged linked list.
 leetcode - https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class Merge_Two_Sorted_List {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode res = null;

        if(list1 == null){

            return list2;
        }
        else if(list2 == null){

            return list1;
        }

        if(list1.val < list2.val){

            res = list1;
            list1 = list1.next;
        }
        else{

            res = list2;
            list2 = list2.next;
        }

        ListNode s = res;

        while(list1 != null && list2 != null){

            if(list1.val < list2.val){

                res.next = list1;
                res = res.next;
                list1 = list1.next;
            }
            else{

                res.next = list2;
                res = res.next;
                list2 = list2.next;
            }
        }

        while(list1 != null){

            res.next = list1;
            res = res.next;
            list1 = list1.next;
        }

        while(list2 != null){

            res.next = list2;
            res = res.next;
            list2 = list2.next;
        }

        return s;

    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
