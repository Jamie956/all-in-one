package leetC;

public class _148 {
    public static void main(String[] args) {
        int[][] testCases = {
                {1, 2, 3, 4, 5, 6},
                {4, 2, 1, 3},
                {-1, 5, 3, 4, 0},
                {}
        };

        for (int[] testCase : testCases) {
            ListNode head = null;
            ListNode pre = null;
            for (int i = 0; i < testCase.length; i++) {
                ListNode node = new ListNode(testCase[i]);
                if (head == null) {
                    head = node;
                } else {
                    pre.next = node;
                }
                pre = node;
            }
            ListNode result = new _148().sortList(head);

            System.out.println();
        }

    }

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    /*
    递归树:
    1)双数节点
    1,2,3,4,5,6
    -> 1,2,3,4  4,5,6
    -> 1,2,3  3,4  4,5,6  6
    -> 1,2  2,3  3  4,5  5,6  6

    2)单数节点
    1,2,3,4,5
    -> 1,2,3  3,4,5
     */
    public ListNode sortList(ListNode head, ListNode tail) {
        //递归中断条件1
        if (head == null) {
            return head;
        }
        //递归中断条件2
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        //快慢指针找中点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        //以中点为界，递归子链
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        //子链合并和排序
        ListNode merged = merge(list1, list2);
        return merged;
    }

    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode cursor1 = list1;
        ListNode cursor2 = list2;
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        //双指针排序
        while (cursor1 != null && cursor2 != null) {
            if (cursor1.val <= cursor2.val) {
                current.next = cursor1;
                cursor1 = cursor1.next;
            } else {
                current.next = cursor2;
                cursor2 = cursor2.next;
            }
            current = current.next;
        }
        //合并未遍历尾链
        if (cursor1 != null) {
            current.next = cursor1;
        } else if (cursor2 != null) {
            current.next = cursor2;
        }

        return dummyHead.next;
    }
}
