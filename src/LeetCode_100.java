import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leet code 75- 100
 */
public class LeetCode_100 {

    public static void num77() {
        List<List<Integer>> level = new ArrayList<>();

        List<Integer> first = new ArrayList<>();
        first.add(2);

        List<Integer> second = new ArrayList<>();
        second.add(3);
        second.add(4);

        List<Integer> three = new ArrayList<>();
        three.add(6);
        three.add(5);
        three.add(7);

        List<Integer> four = new ArrayList<>();
        four.add(4);
        four.add(1);
        four.add(8);
        four.add(3);

        level.add(first);
        level.add(second);
        level.add(three);
        level.add(four);

        int i = LeetCode100Impl.minimumTotal(level);
        System.out.println("result=" + i);

    }

    public static void num79() {
        int[] nums = new int[]{
                7, 1, 5, 3, 6, 4
        };
        int[] num2 = new int[]{
                1, 2, 3, 4, 5
        };
        int i = LeetCode100Impl.maxProfit2(num2);
        System.out.println("result=" + i);
    }

    public static void num80() {
        int[] nums = new int[]{
                7, 1, 5, 3, 6, 4
        };
        int[] num2 = new int[]{
                1, 2, 3, 4, 5
        };
        int i = LeetCode100Impl.maxProfit3(nums);
        System.out.println("result=" + i);
    }

    public static void num81() {
//        boolean palindrome = LeetCode100Impl.isPalindrome("A man, a plan, a canal: Panama");
        boolean palindrome = LeetCode100Impl.isPalindrome("race a car");
        System.out.println("result=" + palindrome);
    }

    public static void num82() {
        int[] num = new int[]{
                100, 4, 200, 1, 3, 2
        };
        int i = LeetCode100Impl.longestConsecutive(num);
        System.out.println("result=" + i);
    }

    public static void num83() {
        char[][] s = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        char[][] ss = new char[][]{
                {'O'}
        };
        LeetCode100Impl.solve(ss);
        for (char[] chars : ss) {
            System.out.println(" " + Arrays.toString(chars));
        }
    }

    public static void num84() {
        int[] gas = new int[]{3, 1, 1};
        int[] cost = new int[]{1, 2, 2};

        int i = LeetCode100Impl.canCompleteCircuit(gas, cost);
        System.out.println("result=" + i);
    }

    public static void num86() {
        int[] nums = new int[]{
                2, 2, 1, 2
        };
        int i = LeetCode100Impl.singleNumberNew(nums);
        System.out.println("result = " + i);
    }

    public static void num91() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        head.next = second;
        second.next = third;
        third.next = four;
        four.next = five;
        System.out.println("begin " + head);
        LeetCode100Impl.reorderList(head);
        System.out.println("result=" + head);
    }

    public static void num95() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        int i = lruCache.get(1);
        System.out.println(" " + i);
        lruCache.put(3, 3);
        int i1 = lruCache.get(2);
        System.out.println(" " + i1);
        lruCache.put(4, 4);
        int i2 = lruCache.get(1);
        System.out.println(" " + i2);
        int i3 = lruCache.get(3);
        System.out.println(" " + i3);
        int i4 = lruCache.get(4);
        System.out.println(" " + i4);

    }

    public static void num96() {
        ListNode start = new ListNode(4);

        ListNode node2 = new ListNode(19);
        start.next = node2;
        ListNode node3 = new ListNode(14);
        node2.next = node3;
        ListNode node4 = new ListNode(5);
        node3.next = node4;
        ListNode node5 = new ListNode(-3);
        node4.next = node5;
        ListNode node6 = new ListNode(1);
        node5.next = node6;
        ListNode node7 = new ListNode(8);
        node6.next = node7;
        ListNode node8 = new ListNode(5);
        node7.next = node8;
        ListNode node9 = new ListNode(11);
        node8.next = node9;
        ListNode node10 = new ListNode(15);
        node9.next = node10;

        ListNode node = LeetCode100Impl.sortList(start);

        System.out.println(" result=" + node);
    }

    public static void num97() {
        String s = LeetCode100Impl.reverseWords("the sky  is blue ");
        System.out.println(" result =" + s);
    }
}
