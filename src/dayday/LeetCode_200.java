package dayday;

import java.util.Arrays;

/**
 * 150 - 200 测试
 */
public class LeetCode_200 {

    public static void num152() {
//        int[] nums = {3, 2, 1, 5, 6, 4};
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        //4 - 3
        //3 - 3
        //
        int kthLargest = LeetCode200Impl.findKthLargest(nums, 4);
        System.out.println("result=" + kthLargest);
    }

    public static void num153() {
        int[] data = {1, 2, 3, 1};
        boolean b = LeetCode200Impl.containsNearbyAlmostDuplicate1(data, 3, 0);
        System.out.println("result=" + b);
    }

    public static void num154() {
        TreeNode n1 = new TreeNode();
        n1.val = 3;
        TreeNode n2 = new TreeNode();
        n2.val = 1;
        TreeNode n3 = new TreeNode();
        n3.val = 2;
        TreeNode n4 = new TreeNode();
        n4.val = 4;

        n1.left = n2;
        n1.right = n4;
        n2.right = n3;

        int i = LeetCode200Impl.kthSmallest(n1, 1);
        System.out.println("result=" + i);
    }

    public static void num155() {
        int i = LeetCode200Impl.countDigitOne(13);
        System.out.println("result = " + i);
    }

    public static void num156() {
        int[] data = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] data = {1,-1};
        int[] result = LeetCode200Impl.maxSlidingWindow(data, 3);
        System.out.println("result=" + Arrays.toString(result));
    }

    public static void num171() {
        int i = LeetCode200Impl.addDigits(308);
        System.out.println("result=" + i);
    }

    public static void num172() {
//        int[] data = {3, 0, 6, 1, 5};
        int[] data = {11, 15};
        int i = LeetCode200Impl.hIndex(data);
        System.out.println("result=" + i);
    }

    public static void num176() {
        int[] data = {0, 1, 0, 3, 12};
        LeetCode200Impl.moveZeroes(data);
        System.out.println("result=" + Arrays.toString(data));
    }

    public static void num182() {
        int[][] data = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5},
        };
        int[][] data2 = {
                {3, 0, 1},
                {5, 6, 2},
                {1, 2, 3}
        };
//        dayday.NumMatrix matrix = new dayday.NumMatrix(data);
//        int r1 = matrix.sumRegion(2, 1, 4, 3);
//        System.out.println("========================result="+r1);
//        int r2= matrix.sumRegion(1,1,2,2);
//        System.out.println("========================result="+r2);
//        int r3 = matrix.sumRegion(1,2,2,4);
//        System.out.println("========================result="+r3);
        NumMatrix matrix = new NumMatrix(data2);
        int result = matrix.sumRegion(1, 1, 2, 2);
        System.out.println("========================result=" + result);
    }

    public static boolean isAdditiveNumber(String num) {
        if (num.length() < 3) {
            return false;
        }
        int lastNum = 0;
        int lastLastNum = 0;

        return true;
    }

    /**
     * 0-开启
     * 1-关闭
     */

    public static void num181() {
        int i = LeetCode200Impl.bulSwitch(4);
        System.out.println("result=" + i);
    }

    public static void num189() {
        ListNode f1 = new ListNode();
        ListNode f2 = new ListNode();
        ListNode f3 = new ListNode();
        ListNode f4 = new ListNode();
        ListNode f5 = new ListNode();
        f1.val = 1;
        f1.next = f2;
        f2.val = 2;
        f2.next = f3;
        f3.val = 3;
        f3.next = f4;
        f4.val = 4;
        f4.next = f5;
        f5.val = 5;
        ListNode node = LeetCode200Impl.oddEvenList(f1);
        while (node != null) {
            System.out.println("val=" + node.val);
            node = node.next;
        }
    }

    public static void num190() {
//        String data = "9,3,4,#,#,1,#,#,2,#,6,#,#";
//        String data = "#,7,6,9,#,#,#";
        String data = "7,2,#,2,#,#,#,6,#";
        boolean validSerialization = LeetCode200Impl.isValidSerialization(data);
        System.out.println("result=" + validSerialization);
    }

    public static void num191() {
        SummaryRanges sr = new SummaryRanges();
        sr.addNum(1);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
        sr.addNum(3);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
        sr.addNum(7);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
        sr.addNum(2);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
        sr.addNum(6);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
        sr.addNum(9);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
        sr.addNum(4);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
        sr.addNum(10);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
        sr.addNum(5);
        System.out.println("arr=" + Arrays.deepToString(sr.getIntervals()));
    }

    public static void num201() {
        int[] data = {1, 2, 3};
        int i = LeetCode250Impl.combinationSum4(data, 4);
        System.out.println("result=" + i);
    }
}
