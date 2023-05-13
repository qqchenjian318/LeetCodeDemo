package dayday;

import java.util.Arrays;

public class LeetCode_150 {

    public static void num129() {
        int[][] a = new int[][]{
                {1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}
        };
        int i = LeetCode150Impl.maxPoints(a);
        System.out.println("result= " + i);
    }

    public static void num130() {
        String[] data = new String[]{
                "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"
        };
        int i = LeetCode150Impl.evalRPN(data);
        System.out.println("result=" + i);
    }

    public static void num131() {
        int[] data = new int[]{
                1, 2, 3
        };
        int peakElement = LeetCode150Impl.findPeakElement(data);
        System.out.println("result=" + peakElement);
    }

    public static void num132() {
        int i = LeetCode150Impl.compareVersion("1.0", "1.0.1");
        System.out.println("result=" + i);
    }

    public static void num133() {
        int[] data = new int[]{
                -1, 0
        };
        int[] ints = LeetCode150Impl.twoSumFast(data, -1);
        System.out.println("result=" + Arrays.toString(ints));
    }

    public static void num156() {
        String s = LeetCode150Impl.convertToTile(28);
        System.out.println("result = " + s);
    }

    public static void num157() {
        int[] nums = {2, 7, 9, 3, 1};
        int s = LeetCode150Impl.rob(nums);
        System.out.println("result = " + s);
    }

    public static void num158() {

    }

    public static void num148() {
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};


        int i = LeetCode150Impl.numIslands(grid);
        System.out.println("result=" + i);
    }

    public static void num149() {
        boolean result = LeetCode150Impl.isHappy(19);
        System.out.println("result=" + result);
    }

    public static void num145() {
        LeetCode150Impl.removeElements(null, 0);
    }

    public static void num146() {
        int i = LeetCode150Impl.countPrimes(10);
        System.out.println("result=" + i);
    }

    public static void num147() {
        boolean result = LeetCode150Impl.isIsomorphic("foo", "bar");
        System.out.println("result=" + result);
    }

    public static void num150() {
        int[][] values = {{1, 0}};
        boolean canFinish = LeetCode150Impl.canFinish(2, values);
        System.out.println("result=" + canFinish);
    }

    public static void num151() {
//        int[] values = {2, 3, 1, 2, 4, 3};
//        int[] values = {1, 4, 4};
        int[] values = {1, 1, 1, 1, 1, 1};
        int v = LeetCode150Impl.minSubArrayLen(11, values);
        System.out.println("result=" + v);
    }

    public static void num152() {
        int[] data = {1, 2, 1, 1};
        int result = LeetCode150Impl.rob2(data);
        System.out.println("result=" + result);
    }
}
