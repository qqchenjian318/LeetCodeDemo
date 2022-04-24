import java.util.Arrays;

/**
 * Leet code 50-75题的测试
 */
public class LeetCode_75 {

    public static void num57() {
        int[] array = new int[]{
                2, 5, 6, 0, 0, 1, 2
        };
        int[] a = new int[]{
                1, 0, 1, 1, 1
        };
        int[] aa = new int[]{
                4, 5, 6, 7, 0, 1, 2
        };
        boolean search = LeetCode75Impl.search(aa, 0);
        System.out.println("result=" + search);
    }

    public static void num58() {
        ListNode first = new ListNode();
        first.val = 1;

        ListNode second = new ListNode();
        second.val = 2;

        ListNode three = new ListNode();
        three.val = 3;

        ListNode four = new ListNode();
        four.val = 3;

        ListNode five = new ListNode();
        five.val = 4;

        ListNode six = new ListNode();
        six.val = 4;

        ListNode seven = new ListNode();
        seven.val = 5;

        first.next = second;
        second.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;


        ListNode listNode = LeetCode75Impl.deleteDuplicates(first);
        System.out.println("result = " + listNode);
    }

    public static void num59() {
        int[] s = new int[]{2, 1, 5, 6, 2, 3, 0, 2, 1, 5, 6, 2, 3};
        int i = LeetCode75Impl.largestRectangleArea(s);
        System.out.println("result=" + i);
    }

    public static void num60() {
        ListNode first = new ListNode();
        first.val = 1;

        ListNode second = new ListNode();
        second.val = 4;

        ListNode three = new ListNode();
        three.val = 3;

        ListNode four = new ListNode();
        four.val = 2;

        ListNode five = new ListNode();
        five.val = 5;

        ListNode six = new ListNode();
        six.val = 2;

        ListNode seven = new ListNode();
        seven.val = 1;

        first.next = second;
        second.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        ListNode partition = LeetCode75Impl.partition(first, 1);
        System.out.println("result === " + partition);
    }

    public static void num61() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        LeetCode75Impl.merge(nums1, 3, nums2, 3);
        System.out.println("result = " + Arrays.toString(nums1));
    }

    public static void num62() {
        int i = LeetCode75Impl.numDecoding2("12");
        System.out.println("result = " + i);
    }

    public static void num63() {
        ListNode first = new ListNode();
        first.val = 1;

        ListNode second = new ListNode();
        second.val = 2;

        ListNode three = new ListNode();
        three.val = 3;

        ListNode four = new ListNode();
        four.val = 4;

        ListNode five = new ListNode();
        five.val = 5;

        ListNode six = new ListNode();
        six.val = 6;

        ListNode seven = new ListNode();
        seven.val = 7;

        first.next = second;
        second.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        ListNode listNode = LeetCode75Impl.reverseBetween(first, 1, 7);
        System.out.println("result=" + listNode);
    }

    public static void num64() {

//        LeetCode75Impl.inorderTraversal();
    }

    public static void num65() {
        TreeNode root = new TreeNode(3);

        TreeNode four = new TreeNode(1);
        TreeNode six = new TreeNode(5);
        TreeNode three = new TreeNode(4);
        TreeNode seven = new TreeNode(5);
        TreeNode zero = new TreeNode(0);
        TreeNode two = new TreeNode(2);

        root.left = four;
        root.right = six;
        four.left = zero;
        four.right = two;

        six.left = three;
        six.right = seven;

        boolean validBST = LeetCode75Impl.isValidBST(root);
        System.out.println("result=" + validBST);
    }
}
