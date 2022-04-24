import java.util.List;

public class LeetCode_125 {

    public static void num101() {
//["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
//[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
        MinStack stack = new MinStack();
        stack.push(2147483646);
        System.out.println(" " + stack);
        stack.push(2147483646);
        System.out.println(" " + stack);
        stack.push(2147483647);
        System.out.println(" " + stack);
        int top = stack.top();
        System.out.println(" " + stack);
        System.out.println(" top=" + top);
        stack.pop();
        System.out.println(" " + stack);
        int min = stack.getMin();
        System.out.println(" " + stack);
        System.out.println("min=" + min);
        stack.pop();
        int min1 = stack.getMin();
        System.out.println(" min=" + min1);
        stack.pop();
        stack.push(2147483647);
        int top1 = stack.top();
        System.out.println(" top=" + top1);
        int min2 = stack.getMin();
        System.out.println(" min=" + min2);
        stack.push(-2147483648);
        int top2 = stack.top();
        System.out.println(" top=" + top2);
        int min3 = stack.getMin();
        System.out.println(" mon=" + min3);
        stack.pop();
        int min4 = stack.getMin();
        System.out.println(" min=" + min4);
        System.out.println(" " + stack);

    }

    public static void num102() {
        String s = LeetCode125Impl.longestPalindromeNew("bb");
        System.out.println("result=" + s);
    }

    public static void num103() {
        int i = LeetCode125Impl.threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        System.out.println("result=" + i);
    }

    public static void num105() {
        int divide = LeetCode125Impl.divide(-2147483648, -1);
        System.out.println("result = " + divide);
    }

    public static void num106() {
        List<List<Integer>> lists = LeetCode125Impl.combinationSum2(new int[]{4, 1, 2, 3}, 7);
        for (List<Integer> list : lists) {
            System.out.println("result=" + list);
        }
    }

    public static void num107() {
        int i = LeetCode125Impl.firstMissingPosive(new int[]{4, 1, -1, 2});
        System.out.println("result=" + i);
    }

    public static void num108() {
        List<List<Integer>> lists = LeetCode125Impl.permuteUnique(new int[]{1, 1, 2, 3, 4});
        for (List<Integer> list : lists) {
            System.out.println("result=" + list);
        }
    }

    public static void num109() {
        int i = LeetCode125Impl.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println("result=" + i);
    }

    public static void num110() {
        String permutation = LeetCode125Impl.getPermutation(9, 278621);
        System.out.println("result=" + permutation);
    }

    public static void num111() {
        String s = LeetCode125Impl.addBinary("11", "1");
        System.out.println("result=" + s);
    }

    public static void num112() {
        String s = LeetCode125Impl.minWindow("ADOBECODEBANC", "ABC");
        System.out.println("result=" + s);
    }

    public static void num113() {
        List<List<Integer>> combine = LeetCode125Impl.combine(4, 2);
        for (List<Integer> integers : combine) {
            System.out.println("result= " + integers);
        }
    }

    public static void num114() {
        boolean search = LeetCode125Impl.search(new int[]{1, 0, 1, 1, 1}, 0);
        System.out.println("result = " + search);
    }

    public static void num115() {
        ListNode two = new ListNode(1);
        ListNode three = new ListNode(2);
        ListNode four = new ListNode(3);
        ListNode five = new ListNode(3);
        ListNode six = new ListNode(4);
        ListNode seven = new ListNode(4);
        ListNode eight = new ListNode(5);

        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = eight;

        ListNode cur = LeetCode125Impl.deleteDuplicates(two);
        while (cur != null) {
            System.out.println("result = " + cur.val);
            cur = cur.next;
        }
    }

    public static void num120() {
        int[] n = new int[]{2, 1, 5, 6, 2, 3};
        int[] n1 = new int[]{1, 1};
        int i = LeetCode125Impl.largest(n);
        System.out.println("result=" + i);
    }

    public static void num121() {
        int[] n = new int[]{1, 2, 2, 3};
        List<List<Integer>> s = LeetCode125Impl.subsets(n);
        for (List<Integer> integers : s) {
            System.out.println("result=" + integers);
        }
    }

    public static void num123() {
        int i = LeetCode125Impl.numTrees(3);
        System.out.println("result=" + i);
    }


}
