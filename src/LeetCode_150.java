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
}
