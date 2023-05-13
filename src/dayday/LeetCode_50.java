package dayday;

import java.util.Arrays;
import java.util.List;

/**
 * Leet code 25-50题的测试
 */
public class LeetCode_50 {

    public static void num26() {
        int search = LeetCode50Impl.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2);
        System.out.println("result = " + search);
    }

    public static void num27() {
        int index = LeetCode50Impl.searchInsert(new int[]{1, 3, 5, 6}, 5);
        int index1 = LeetCode50Impl.searchInsert(new int[]{1, 3, 5, 6}, 2);
        int index2 = LeetCode50Impl.searchInsert(new int[]{1, 3, 5, 6}, 7);
        int index3 = LeetCode50Impl.searchInsert(new int[]{1, 3, 5, 6}, 0);
        System.out.println(" result = " + index);
        System.out.println(" result = " + index1);
        System.out.println(" result = " + index2);
        System.out.println(" result = " + index3);
    }

    public static void num28() {
        char[][] v = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        char[][] v2 = new char[][]{
                {'.', '.', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '8', '.', '.', '.', '3', '.'},
                {'.', '5', '.', '.', '2', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '9'},
                {'.', '.', '.', '.', '.', '.', '4', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '7'},
                {'.', '1', '.', '.', '.', '.', '.', '.', '.'},
                {'2', '4', '.', '.', '.', '.', '9', '.', '.'}
        };
        boolean validSudoku = LeetCode50Impl.isValidSudoku(v);
        System.out.println("result = " + validSudoku);
    }

    public static void num29() {
        char[][] result = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        char[][] test = new char[][]{
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };


        LeetCode50Impl.solveSudoku(test);
        System.out.println("result = " + Arrays.toString(result[0]));
        System.out.println("result = " + Arrays.toString(result[1]));
        System.out.println("result = " + Arrays.toString(result[2]));
        System.out.println("result = " + Arrays.toString(result[3]));
        System.out.println("result = " + Arrays.toString(result[4]));
        System.out.println("result = " + Arrays.toString(result[5]));
        System.out.println("result = " + Arrays.toString(result[6]));
        System.out.println("result = " + Arrays.toString(result[7]));
        System.out.println("result = " + Arrays.toString(result[8]));
    }

    public static void num30() {
        String s = LeetCode50Impl.realCountAndSay(4);
        System.out.println(" result = " + s);
    }

    //11.28
    public static void num31() {
        List<List<Integer>> lists = LeetCode50Impl.combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list : lists) {
            System.out.println("result = " + list.toString());
        }
    }

    public static void num32() {
        List<List<Integer>> lists = LeetCode50Impl.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        for (List<Integer> list : lists) {
            System.out.println("result = " + list.toString());
        }
    }

    public static void num33() {
        int i = LeetCode50Impl.firstMissingPositive(new int[]{1, 4, 2, 0});
        int i2 = LeetCode50Impl.firstMissingPositive(new int[]{5, 3, 7, 1, 4, 0});
        int i3 = LeetCode50Impl.firstMissingPositive(new int[]{5, 7, 6, 4, 1, 3, 2, 0});
        System.out.println("result = " + i);
        System.out.println("result = " + i2);
        System.out.println("result = " + i3);
    }

    public static void num34() {
        int trap = LeetCode50Impl.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println("result=" + trap);
    }

    public static void num35() {
        String multiply = LeetCode50Impl.multiply("123456789", "987654321");
        String multiply1 = LeetCode50Impl.multiply("123456789", "3");
        System.out.println("result= " + multiply);
        System.out.println("result= " + multiply1);
    }

    public static void num36() {
        int jump = LeetCode50Impl.jump(new int[]{
                5, 6, 4, 4, 6, 9, 4, 4, 7, 4,
                4, 8, 2, 6, 8, 1, 5, 9, 6, 5
                , 2, 7, 9, 7, 9, 6, 9, 4, 1, 6
                , 8, 8, 4, 4, 2, 0, 3, 8, 5});
        int jump1 = LeetCode50Impl.jump(new int[]{
                1, 2, 1, 1, 1
        });

        System.out.println("result=" + jump);
        System.out.println("result=" + jump1);
    }

    public static void num37() {
        List<List<Integer>> permute = LeetCode50Impl.permute(new int[]{1, 2, 3});
        for (List<Integer> integers : permute) {
            System.out.println("result=" + integers.toString());
        }
    }

    public static void num38() {
        List<List<Integer>> lists = LeetCode50Impl.permuteUnique(new int[]{1, 1, 2});
        for (List<Integer> integers : lists) {
            System.out.println("result=" + integers.toString());
        }
    }

    public static void num39() {
        int[][] r = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        LeetCode50Impl.rotate(r);
        for (int[] ints : r) {
            System.out.println(" " + Arrays.toString(ints));
        }
    }

    public static void num40() {
        String[] s = new String[]{
                "eat", "tea", "tan", "ate", "nat", "bat"
        };
        List<List<String>> lists = LeetCode50Impl.groupAnagrams(s);
        for (List<String> list : lists) {
            System.out.println("result=" + list);
        }
    }

    public static void num41() {
        double v = LeetCode50Impl.myPow(2.0, Integer.MIN_VALUE);
        System.out.println("result=" + v);
    }

    public static void num42() {
        int[][] pow = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        int[][] pow1 = new int[][]{
                {1}
        };

        List<Integer> integers = LeetCode50Impl.spiralOrder(pow);
        List<Integer> integers1 = LeetCode50Impl.spiralOrder(pow1);
        System.out.println(" result=" + integers);
        System.out.println(" result=" + integers1);
    }

    public static void num43() {
        int[] steps = new int[]{
                3, 4, 5, 2, 0, 1, 1, 0, 0, 0, 1
        };
        int[] steps1 = new int[]{
                2, 0, 0
        };

        boolean b = LeetCode50Impl.canJump(steps1);
        System.out.println(" result=" + b);
    }

    public static void num44() {
        int[][] s = new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };

        int[][] ss = new int[][]{
                {1, 4}, {0, 0}
        };
        int[][] merge = LeetCode50Impl.merge(ss);
        for (int[] ints : merge) {
            System.out.println("result=" + Arrays.toString(ints));
        }
    }

    public static void num45() {
        int[][] s = new int[][]{
                {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        };
        int[] target = new int[]{
                4, 8
        };
        int[][] insert = LeetCode50Impl.insert(s, target);
        for (int[] ints : insert) {
            System.out.println("result=" + Arrays.toString(ints));
        }
    }

    public static void num46() {
        int result = LeetCode50Impl.lengthOfLastWord("a bc  ");
        System.out.println("result=" + result);
    }

    public static void num47() {
        int[][] ints = LeetCode50Impl.generateMatrix(3);
        for (int[] anInt : ints) {
            System.out.println("result=" + Arrays.toString(anInt));
        }
    }

    public static void num48() {
        String permutation = LeetCode50Impl.getPermutation(3, 3);
        System.out.println("result = " + permutation);
    }

    public static void num49() {
        LeetCode25Impl.ListNode one = new LeetCode25Impl.ListNode();
        one.val = 1;

        LeetCode25Impl.ListNode two = new LeetCode25Impl.ListNode();
        two.val = 2;
        one.next = two;

        LeetCode25Impl.ListNode three = new LeetCode25Impl.ListNode();
        three.val = 3;
        two.next = three;

        LeetCode25Impl.ListNode listNode = LeetCode50Impl.rotateRight(one, 3);
        System.out.println(" result=" + listNode);
    }

    public static void num50() {
        int i = LeetCode50Impl.uniquePaths(3, 2);
        System.out.println("result=" + i);
    }

    public static void num51() {
        int[][] s = new int[][]{
                {0, 0, 0}, {0, 1, 0}, {0, 0, 0}
        };
        int[][] ss = new int[][]{
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0}, {
                1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1}, {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {
                0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0}, {
                0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}, {1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {
                0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0}, {
                0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {
                0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1}, {0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}, {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0}, {
                0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0}, {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {
                0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0}, {
                0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1}, {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0}, {
                1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {
                0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1}, {
                1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
        };
        int i = LeetCode50Impl.uniquePathsWithObstacles(ss);
        System.out.println("result=" + i);
    }

    public static void num52() {
        int[][] s = new int[][]{
                {1, 3, 1}, {1, 5, 1}, {4, 2, 1}
        };
        int[][] ss = new int[][]{
                {1, 2, 3}, {4, 5, 6}
        };
        int i = LeetCode50Impl.minPathSum(ss);
        System.out.println("result=" + i);
    }

    public static void num53() {
        String[] words = new String[]{
                "This", "is", "an", "example", "of", "text", "justification."
        };
        String[] wordss = new String[]{
                "Listen", "to", "many,", "speak", "to", "a", "few."
        };
        String[] ss = new String[]{
                "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"
        };
        String[] sss = new String[]{
                "What", "must", "be", "shall", "be"
        };

        List<String> strings = LeetCode50Impl.fullJustify(sss, 5);
        for (String string : strings) {
            System.out.println("result=" + string);
        }
    }

    public static void num54() {
        int i = LeetCode50Impl.mySqrt(2147483647);
        System.out.println("result=" + i);
    }

    public static void num55() {
        int i = LeetCode50Impl.climbStairs(3);
        System.out.println("result=" + i);
    }

    public static void num56() {
        int[][] s = new int[][]{
                {1, 1, 1}, {1, 0, 1}, {1, 1, 1}
        };

        LeetCode50Impl.setZeroes(s);
        for (int[] ints : s) {
            System.out.println("result = " + Arrays.toString(ints));
        }
    }

    public static void num57() {
        int[][] s = new int[][]{
                {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
        };
        int[][] ss = new int[][]{
                {1}, {3}
        };
        int[][] sss = new int[][]{
                {1}
        };

        boolean b = LeetCode50Impl.searchMatrix(sss, 2);
        System.out.println("result = " + b);
    }

    public static void num58() {
        int[] s = new int[]{
                2, 0, 2, 1, 1, 0
        };
        int[] ss = new int[]{
                1, 0, 0
        };
        int[] sss = new int[]{
                0
        };
        LeetCode50Impl.sortColors(ss);
        System.out.println("result=" + Arrays.toString(ss));
    }

    public static void num59() {
        char[][] ss = new char[][]{
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}
        };

        boolean abcced = LeetCode50Impl.exist(ss, "ABCESEEEFS");
        System.out.println("result = " + abcced);
    }

    public static void num60(){
        int[] s = new int[]{0,0,0,1,2,2,3,3,3,3,5,5,6,7};
        int[] ss = new int[]{1,1,1,2,2,3};
        int i = LeetCode50Impl.removeDuplicates(ss);
        System.out.println("index="+i+",s="+ Arrays.toString(ss));
    }
}
