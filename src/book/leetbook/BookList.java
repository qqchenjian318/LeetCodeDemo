package book.leetbook;

import dayday.ListNode;

import java.util.Stack;

/**
 * LeetBook-链表
 */
public class BookList {

    /**
     * 有一个单链表的head，我们想删除它其中的一个节点node。
     * <p>
     * 给你一个需要删除的节点node。你将无法访问第一个节点head。
     * <p>
     * 链表的所有值都是 唯一的，并且保证给定的节点node不是链表中的最后一个节点。
     * <p>
     * 删除给定的节点。注意，删除节点并不是指从内存中删除它。这里的意思是：
     * <p>
     * 给定节点的值不应该存在于链表中。
     * 链表中的节点数应该减少 1。
     * node前面的所有值顺序相同。
     * node后面的所有值顺序相同。
     */
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 双指针，先走N步
     */
    public static ListNode removeNthFromENd(ListNode listNode, int n) {
        int count = n;
        ListNode headNode = listNode;
        ListNode firstNode = listNode;
        while (count > 0) {
            firstNode = firstNode.next;
            count--;
        }
        if (firstNode == null) {
            return headNode.next;
        }
        ListNode secondNode = listNode;
        ListNode lastNode = null;
        while (firstNode != null) {
            firstNode = firstNode.next;
            lastNode = secondNode;
            secondNode = secondNode.next;
        }
        //交换
        lastNode.next = lastNode.next.next;
        return headNode;
    }

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode lastNode = head;
        ListNode curNode = head.next;
        lastNode.next = null;
        while (curNode != null) {
            ListNode tempNode = curNode;
            curNode = curNode.next;
            tempNode.next = lastNode;
            lastNode = tempNode;
        }
        return lastNode;
    }

    /**
     *
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode firstNode = list1;
        ListNode secondNode = list2;
        ListNode dummy = new ListNode(0);

        ListNode curNode = dummy;
        while (firstNode != null && secondNode != null) {
            if (firstNode.val > secondNode.val) {
                curNode.next = secondNode;
                secondNode = secondNode.next;
            } else {
                curNode.next = firstNode;
                firstNode = firstNode.next;
            }
            curNode = curNode.next;
        }
        curNode.next = firstNode == null ? secondNode : firstNode;
        return dummy.next;
    }

    /**
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     */
    public static boolean isPalindrome(ListNode head) {
        Stack<ListNode> nodeStack = new Stack<>();
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            nodeStack.add(slowNode);
            slowNode = slowNode.next;
        }
        ListNode startNode;
        if (fastNode == null) {
            startNode = slowNode;
        } else {
            startNode = slowNode.next;
        }
        while (startNode != null) {
            ListNode pop = nodeStack.pop();
            if (pop.val != startNode.val) {
                return false;
            }
            startNode = startNode.next;
        }
        return true;
    }


    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * <p>
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     * <p>
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fastNode = head.next;
        ListNode slowNode = head;

        //如果有环，那肯定
        while (fastNode != null && fastNode.next != null) {
            if (fastNode == slowNode) {
                return true;
            }
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }
        return false;
    }
}
