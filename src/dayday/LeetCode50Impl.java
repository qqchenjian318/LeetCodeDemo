package dayday;

import java.util.*;

/**
 * Leet code 26- 50题的具体实现
 */
public class LeetCode50Impl {

    /**
     * 在进行过旋转的数组中查找数据
     * 二分法
     */
    public static int search(int[] nums, int target) {
        if (nums.length < 3) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    return i;
                }
            }
            return -1;
        }
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        int resultIndex = -1;
        while (leftIndex < (rightIndex - 1)) {
            int midIndex = (leftIndex + rightIndex) / 2;

            int leftValue = nums[leftIndex];
            int rightValue = nums[rightIndex];
            int midValue = nums[midIndex];
            if (target == leftValue) {
                return leftIndex;
            } else if (target == rightValue) {
                return rightIndex;
            } else if (target == midValue) {
                return midIndex;
            }
            boolean isLeftSort = leftValue < midValue;
            if (target > midValue) {
                //说明在右侧查找，那么要知道右侧是否有序
                if (isLeftSort) {
                    //说明左侧有序，那么在右侧寻找
                    leftIndex = midIndex;
                } else {
                    //说明右侧是有序的，那么在右侧找
                    if (target > rightValue) {
                        //说明右侧没有
                        rightIndex = midIndex;
                    } else {
                        leftIndex = midIndex;
                    }
                }

            } else {
                if (isLeftSort) {
                    //左侧有序
                    if (target < leftValue) {
                        leftIndex = midIndex;
                    } else {
                        rightIndex = midIndex;
                    }
                } else {
                    //右侧有序
                    rightIndex = midIndex;
                }
            }
        }
        return resultIndex;
    }

    /**
     * 采用二分法，找到该元素，然后向左向右移动 找到开始和结束位置
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length < 3) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    return findStartAndEnd(i, nums, target);
                }
            }
        }
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        while (leftIndex < (rightIndex - 1)) {
            int midIndex = (leftIndex + rightIndex) / 2;

            int leftValue = nums[leftIndex];
            int rightValue = nums[rightIndex];
            int midValue = nums[midIndex];

            if (target == leftValue) {
                return findStartAndEnd(leftIndex, nums, target);
            } else if (target == rightValue) {
                return findStartAndEnd(rightIndex, nums, target);
            } else if (target == midValue) {
                return findStartAndEnd(midIndex, nums, target);
            }

            if (target > midValue) {
                leftIndex = midIndex;
            } else {
                rightIndex = midIndex;
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] findStartAndEnd(int index, int[] nums, int target) {
        int leftValue;
        int leftIndex = index;
        while (leftIndex >= 0) {
            leftValue = nums[leftIndex];
            if (leftValue != target) {
                break;
            }
            leftIndex--;
        }
        int rightIndex = index;
        int rightValue;
        while (rightIndex < nums.length) {
            rightValue = nums[rightIndex];
            if (rightValue != target) {
                break;
            }
            rightIndex++;
        }
        return new int[]{leftIndex + 1, rightIndex - 1};
    }

    /**
     * 查找位置，如果不存在，就返回被插入的位置
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums.length < 3) {
            for (int i = 0; i < nums.length; i++) {
                int value = nums[i];
                if (value >= target) {
                    return i;
                }
            }
        }
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        int midIndex = 0;

        while (leftIndex < (rightIndex - 1)) {
            midIndex = (leftIndex + rightIndex) / 2;
            int leftValue = nums[leftIndex];
            int rightValue = nums[rightIndex];
            int midValue = nums[midIndex];
            if (midValue == target) {
                return midIndex;
            } else if (leftValue == target) {
                return leftIndex;
            } else if (rightValue == target) {
                return rightIndex;
            }
            if (target > midValue) {
                leftIndex = midIndex;
            } else {
                rightIndex = midIndex;
            }
        }
        if (target > nums[rightIndex]) {
            return rightIndex + 1;
        } else if (target > nums[midIndex]) {
            return midIndex + 1;
        } else if (target > nums[leftIndex]) {
            return leftIndex + 1;
        } else {
            return leftIndex;
        }
    }

    /**
     * 有效数独
     * 1. 数字 1-9 在每一行只能出现一次。
     * 2. 数字 1-9 在每一列只能出现一次。
     * 3. 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 一行一行的检查，检查过的，就不用再检查了
     */
    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char value = board[i][j];
                if (value == '.') {
                    continue;
                }
                boolean isValid = checkCurValue(value, i, j, board);
                System.out.println(" 单次检查结果 i=" + i + ",j=" + j + ",r=" + isValid);
                if (!isValid) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 从board[i][j] 位置开始检查
     * 1。 横检查
     * 2。 竖检查
     * 3。 圈检查
     */
    private static boolean checkCurValue(char value, int i, int j, char[][] board) {
        for (int k = i + 1; k < 9; k++) {
            char curValue = board[k][j];
            System.out.println("checkCurValue 行 k=" + k + ",j=" + j
                    + ",i=" + i + ",value=" + value + ",cur=" + curValue);
            if (curValue == value && k != i) {
                return false;
            }
        }
        for (int k = j + 1; k < 9; k++) {
            char curValue = board[i][k];
            System.out.println("checkCurValue  列 k=" + k + ",j=" + j
                    + ",i=" + i + ",value=" + value + ",cur=" + curValue);
            if (curValue == value) {
                return false;
            }
        }
        //下部检查
        int x = (i / 3) * 3 + 2;
        int y = (j / 3) * 3 + 2;
        System.out.println("圈 x=" + x + ",y=" + y + ",i=" + i + ",j=" + j);
        for (int k = x; k >= i; k--) {
            for (int l = y; l >= y - 2; l--) {
                char curValue = board[k][l];
                System.out.println("checkCurValue  圈 k=" + k + ",l=" + l +
                        ",j=" + j
                        + ",i=" + i + ",value=" + value + ",cur=" + curValue);
                if (curValue == value && k != i && l != j) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 解数独
     * 1. 横向判断
     * 2. 竖向判断
     * 3. 小框框填空
     * 判断该位置可以放入几，如果超过两个 就不放入，并且返回1
     * 记录一次遍历的结果 如果大于0 就再来一次循环
     */
    public static void solveSudoku(char[][] board) {
        System.out.println("  " + Arrays.toString(board[0]));
        System.out.println("  " + Arrays.toString(board[1]));
        System.out.println("  " + Arrays.toString(board[2]));
        System.out.println("  " + Arrays.toString(board[3]));
        System.out.println("  " + Arrays.toString(board[4]));
        System.out.println("  " + Arrays.toString(board[5]));
        System.out.println("  " + Arrays.toString(board[6]));
        System.out.println("  " + Arrays.toString(board[7]));
        System.out.println("  " + Arrays.toString(board[8]));
        System.out.println(" ********************************* ");
        //如果本轮没能填入任意一个数字，则认为当前无法通过这种方式计算了
        //采用后续的算法
        int[] i = loopSolve(board);
        int result = i[0];
        int right = i[1];

        if (result > 0 && right != 0) {
            solveSudoku(board);
        } else {
            System.out.println(" 当前无法采用该算法找到结果了");
            solveNew(board);
        }
    }

    /**
     * 新的函数，
     */
    public static void solveNew(char[][] board) {

    }

    public static int[] loopSolve(char[][] board) {
        int result = 0;
        int right = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char curChar = board[i][j];
                if (curChar == '.') {
                    //说明需要填充，就判定一次，需要填入几，如果可以填入的数字超过1 ，进行下一次判定
                    int num = loopEach(board, i, j);
                    if (num > 1) {
                        result++;
                    } else {
                        right++;
                    }
                }
            }
        }
        return new int[]{result, right};
    }

    /**
     * 单个位置的判定，遍历1-9，然后依次横、纵、圈比较
     */
    public static int loopEach(char[][] board, int i, int j) {
        //判断横
        List<Character> currentChar = new ArrayList<>();
        int x = (i / 3) * 3 + 2;
        int y = (j / 3) * 3 + 2;
        System.out.println(" 当前哟 i=" + i + ",j=" + j);
        for (char num : nums) {
            boolean isRight = true;
            for (int k = 0; k < 9; k++) {
                char curChar = board[i][k];
                System.out.println("横---- i=" + i + ",k=" + k +
                        ",curChar=" + curChar + ",num=" + num);
                if (num == curChar) {
                    isRight = false;
                    break;
                }
            }
            if (isRight) {
                for (int k = 0; k < 9; k++) {
                    char curChar = board[k][j];
                    System.out.println("竖**** k=" + k + ",j=" + j +
                            ",curChar=" + curChar + ",num=" + num);
                    if (num == curChar) {
                        isRight = false;
                        break;
                    }
                }
            }
            if (isRight) {
                for (int k = (x - 2); k <= x; k++) {
                    for (int l = (y - 2); l <= y; l++) {
                        char curChar = board[k][l];
                        System.out.println(" 圈=== k=" + k + ",l=" + l +
                                ",x=" + x + ",y=" + y + ",curChar=" + curChar + ",num=" + num);
                        if (curChar == num) {
                            isRight = false;
                            break;
                        }
                    }
                }
            }
            if (isRight) {
                currentChar.add(num);
            }
            if (currentChar.size() > 1) {
                break;
            }
        }
        if (currentChar.size() == 1) {
            board[i][j] = currentChar.get(0);
            System.out.println("填入数字 i=" + i + ",j=" + j +
                    ",char=" + board[i][j]);
        }
        return currentChar.size();
    }

    public static char[] nums = new char[]{
            '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static String realCountAndSay(int n) {
        if (n == 1) {
            return "1";
        } else {
            String res = realCountAndSay(n - 1);
            return countAndSay(res);
        }
    }

    private static String countAndSay(String n) {
        StringBuilder sb = new StringBuilder();

        char lastChar = '.';
        int num = 0;
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(i);

            if (lastChar == '.') {
                lastChar = c;
                num = 1;
            } else if (lastChar == c) {
                num++;
            } else {
                //说明与上一个不想等，那么开始切换了
                sb.append(num);
                sb.append(lastChar);
                lastChar = c;
                num = 1;
            }
        }
        //遍历完毕
        if (lastChar != '.') {
            sb.append(num);
            sb.append(lastChar);
        }
        return sb.toString();
    }

    /**
     * 组合综合
     * 给定一个无重复元素的整数数组和一个正整数，找出candidates中所有可以使数字和为目标数target
     * 的唯一组合
     * 搜索回溯方式
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, 0, result, target, combine);
        return result;
    }

    private static void dfs(int[] candidates, int index, List<List<Integer>> result,
                            int target, List<Integer> combine) {
        if (index >= candidates.length) {
            //说明已经没有了
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(combine));
            return;
        }
        //1. 跳过当前的选择
        dfs(candidates, index + 1, result, target, combine);
        int diff = target - candidates[index];
        if (diff >= 0) {
            combine.add(candidates[index]);
            dfs(candidates, index, result, diff, combine);
            combine.remove(combine.size() - 1);
        }
    }

    /**
     * 找出所有组合，每个数字只能使用一次
     * 解集不能包含重复的组合
     * 无序的有重复数的集合
     * 1. 先找出所有的，然后干掉重复的？
     * 2. 如果长度一致的，就认为可能重复
     * 3. 找出长度一致的，然后遍历，
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        return null;
    }

    /**
     * 缺失的第一个正数
     * 1. 数组未排序
     * 思路：
     * 1. 找到最小的正数，如果大于0，那么结果就是0
     * 2. 找到非紧密排列的第一个数
     * <p>
     * 1。假设第一个未出现的正数是MAX，result
     * 2. 如果当前小于等于result,那么lastResult = result,result = cur - 1
     * 3. 如果最终result = 0，那么return lastResult
     */
    public static int firstMissingPositive(int[] nums) {
        int minValue = -1;
        int maxValue = -1;
        int curValue = Integer.MAX_VALUE;
        int lastValue = -1;
        int rightValue = -1;
        Stack<Integer> lastValues = new Stack<>();
        Stack<Integer> rightValues = new Stack<>();

        for (int num : nums) {

        }

        System.out.println("rightValue=" + rightValue + ",curValue=" + curValue +
                ",lastValues=" + lastValues + ",minValue=" +
                minValue + ",maxValue=" + maxValue);

        if (minValue > 0) {
            return 0;
        }
        if (maxValue < 0) {
            return 0;
        }
        if (curValue != -1) {
            return curValue;
        }

        return rightValue;
    }

    /**
     * 给定n个非负整数，每个宽度为1的柱子的高度图，计算按此排列的柱，下雨后能接多少雨水
     * 每个柱子能接多少雨水是由左右两侧柱子的高度决定了的
     * <p>
     * 最左侧和最右侧的柱子不能接水
     */
    public static int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int result = 0;
        int leftMaxValue = 0;
        int rightMaxValue = 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            int curValue = height[i];
            leftMax[i] = leftMaxValue;
            if (curValue > leftMaxValue) {
                leftMaxValue = curValue;
            }
        }
        for (int i = height.length - 1; i >= 0; i--) {
            int curValue = height[i];
            rightMax[i] = rightMaxValue;
            if (curValue > rightMaxValue) {
                rightMaxValue = curValue;
            }
        }

        for (int i = 1; i < height.length - 1; i++) {
            //当前柱子的高度
            int curHeight = height[i];
            int leftHeight = leftMax[i];
            int rightHeight = rightMax[i];

            System.out.println("i=" + i + ",rightH = " + rightHeight + ",leftH=" + leftHeight + ",curH=" + curHeight);
            if (leftHeight > curHeight && rightHeight > curHeight) {
                int value = Math.min(leftHeight, rightHeight) - curHeight;
                result += value;
                System.out.println(" curValue=" + value + ",result=" + result);
            }
        }
        return result;
    }

    /**
     * 乘法
     * 每一位和上一个数相乘，然后结果相加
     */
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        List<String> results = new ArrayList<>();
        for (int i = num2.length() - 1; i >= 0; i--) {
            char curChar = num2.charAt(i);
            String result = multiplyChar(num1, curChar, num2.length() - i - 1);
            results.add(result);
        }
        String result = "0";
        for (String s : results) {
            result = add(result, s);
        }
        return new StringBuilder(result).reverse().toString();
    }

    /**
     * 字符串与单字符相乘
     */
    private static String multiplyChar(String num1, char curChar, int i) {
        char jin = '0';
        StringBuilder sb = new StringBuilder();
        System.out.println("multiplyChar num1=" + num1 + ",curChar=" + curChar);
        for (int j = num1.length() - 1; j >= 0; j--) {
            char at = num1.charAt(j);
            char[] mul = mul(at, curChar, jin);
            if (mul.length > 1) {
                jin = mul[0];
                sb.append(mul[1]);
            } else {
                sb.append(mul[0]);
                jin = '0';
            }
        }
        if (jin != '0') {
            sb.append(jin);
        }
        StringBuilder sss = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sss.append('0');
        }

        return sss + sb.toString();
    }

    private static char[] mul(char num1, char num2, char jin) {
        Integer one = Character.getNumericValue(num1);
        Integer two = Character.getNumericValue(num2);
        Integer three = Character.getNumericValue(jin);
        String result = (one * two + three) + "";
        System.out.println("mul num1=" + num1 + ",num2=" + num2 + ",result=" + result);
        return result.toCharArray();
    }

    private static String add(String num1, String num2) {
        int max = Math.max(num1.length(), num2.length());
        System.out.println("addString num1=" + num1 + ",num2=" + num2);
        char jin = '0';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
            char first = '0';
            if (i < num1.length()) {
                first = num1.charAt(i);
            }

            char second = '0';
            if (i < num2.length()) {
                second = num2.charAt(i);
            }
            System.out.println("add first=" + first + ",second=" + second);
            char[] addChar = addChar(first, second, jin);
            if (addChar.length > 1) {
                //存在进位
                jin = addChar[0];
                sb.append(addChar[1]);
            } else {
                sb.append(addChar[0]);
                jin = '0';
            }
        }
        if (jin != '0') {
            sb.append(jin);
        }
        return sb.toString();
    }

    private static char[] addChar(char num1, char num2) {
        Integer one = Character.getNumericValue(num1);
        Integer two = Character.getNumericValue(num2);
        String result = one + two + "";
        return result.toCharArray();
    }

    private static char[] addChar(char num1, char num2, char num3) {
        Integer one = Character.getNumericValue(num1);
        Integer two = Character.getNumericValue(num2);
        Integer three = Character.getNumericValue(num3);
        String result = (one + two + three) + "";
        return result.toCharArray();
    }

    /**
     * 贪心策略
     * 当前位置能达到的最远的下一步
     * 1. 如果达到了结尾就结束
     */
    public static int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }

        return steps;
    }

    /**
     * 回溯算法
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPermute(nums, result, new ArrayList<>());

        return result;
    }

    private static void dfsPermute(int[] nums, List<List<Integer>> result, List<Integer> comb) {
        if (comb.size() == nums.length) {
            result.add(new ArrayList<>(comb));
            return;
        }
        System.out.println("dfsPermute " + comb);
        for (Integer curValue : nums) {
            if (!comb.contains(curValue)) {
                comb.add(curValue);
                dfsPermute(nums, result, comb);
                comb.remove(curValue);
            }
        }
    }

    /**
     * 全排列，返回所有不重复的全排列
     * 1. 回溯算法 + 剪枝
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPermuteUnique(nums, result, new ArrayList<>());
        return result;
    }

    private static void dfsPermuteUnique(int[] nums, List<List<Integer>> result, ArrayList<Integer> comb) {
        if (comb.size() == nums.length) {
            result.add(new ArrayList<>(comb));
            return;
        }

    }

    /**
     * 90度旋转
     * 1. 先水平翻转
     * 2. 再对角线翻转
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        System.out.println(" 开始变化前");
        for (int[] ints : matrix) {
            System.out.println("" + Arrays.toString(ints));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("" + matrix[i][j]);
            }
            System.out.println("");
        }

        //1. 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[n - i - 1][j];
                System.out.println(" 水平 i=" + i + ",j=" + j + ",value=" + matrix[i][j] + ",temp=" + temp);
                matrix[n - i - 1][j] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        System.out.println(" 水平翻转后");
        for (int[] ints : matrix) {
            System.out.println("" + Arrays.toString(ints));
        }
        //2. 对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[j][i];
                System.out.println("对角线 i=" + i + ",j=" + j + ",v=" + matrix[j][i] + ",v2=" + matrix[i][j]);
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    /**
     * 字母异位词分组
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        //已经分过组的词
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> strings = map.get(key);
            if (strings == null) {
                strings = new ArrayList<>();
            }
            strings.add(str);
            map.put(key, strings);
        }
        List<List<String>> result = new ArrayList<>();
        Set<Map.Entry<String, List<String>>> entries = map.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            List<String> value = entry.getValue();
            result.add(value);
        }
        return result;
    }

    /**
     * 实现计算x的n次方
     */
    public static double myPow(double x, int n) {

        return n >= 0 ? quickPow(x, n) : quickPow(x, -n);
    }

    /**
     * 采用分治的思想
     * 结果*结果 减少计算
     */
    private static double quickPow(double x, int N) {
        if (N == 0) {
            return 1.0;
        }
        double quickPow = quickPow(x, N / 2);
        return N % 2 == 0 ? quickPow * quickPow : quickPow * quickPow * x;
    }

    /**
     * 任何两个皇后不能处于同一条横行、纵行、斜线
     * 以一个二维数组作为棋盘
     * 如果是'f',说明已经不能放置了，
     * 如果是''
     */
    private static List<List<String>> solveNQueens(int n) {
        return null;
    }

    public static int maxSubArray(int[] nums) {
        return 0;
    }

    /**
     * 1. 每次读取到边界或已经读取过的数，就转换，直到所有的数都读取完毕
     * 2.
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix[0].length;
        int n = matrix.length;

        Boolean[][] read = new Boolean[n][m];
        List<Integer> result = new ArrayList<>();
        int state = RIGHT;
        int x = 0;
        int y = 0;
        while (true) {
            if (state == RIGHT) {
                result.add(matrix[x][y]);
                read[x][y] = true;
                y++;
                if (y >= m || read[x][y] != null) {
                    y--;
                    state = DOWN;
                    x++;
                    if (x >= n || read[x][y] != null) {
                        break;
                    }
                }
            } else if (state == DOWN) {

                result.add(matrix[x][y]);
                read[x][y] = true;
                x++;
                if (x >= n || read[x][y] != null) {
                    x--;
                    state = LEFT;
                    y--;
                    if (y < 0 || read[x][y] != null) {
                        break;
                    }
                }
            } else if (state == LEFT) {
                result.add(matrix[x][y]);
                read[x][y] = true;
                y--;
                if (y < 0 || read[x][y] != null) {
                    y++;
                    state = UP;
                    x--;
                    if (x < 0 || read[x][y] != null) {
                        break;
                    }
                }
            } else {
                result.add(matrix[x][y]);
                read[x][y] = true;
                x--;
                if (x < 0 || read[x][y] != null) {
                    x++;
                    state = RIGHT;
                    y++;
                    if (y >= m || read[x][y] != null) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    /**
     * 递归回溯算法
     * 1. 如果到的位置是0，那么不能继续往前了，就回退一个位置，看能否绕过0位置，如果不行
     * 就返回false
     */
    public static boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int range = nums[i];
            System.out.println("遍历 i=" + i + ",range=" + range + ",max=" + max);
            if (i > max) {
                break;
            }
            max = Math.max(range + i + 1, max);
        }
        return max >= (nums.length - 1);
    }

    /**
     * 合并区间
     */
    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        int curLeft = -1;
        int curRight = -1;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        for (int[] interval : intervals) {
            if (curLeft == -1 && curRight == -1) {
                curLeft = interval[0];
                curRight = interval[interval.length - 1];
            } else {
                int nowLeft = interval[0];
                int nowRight = interval[interval.length - 1];
                if (nowLeft > curRight) {
                    result.add(new int[]{curLeft, curRight});
                    //添加后，更新
                    curLeft = nowLeft;
                    curRight = nowRight;
                } else {
                    curRight = Math.max(nowRight, curRight);
                    curLeft = Math.min(nowLeft, curLeft);
                }
            }
        }
        result.add(new int[]{curLeft, curRight});
        return result.toArray(new int[result.size()][]);
    }

    /**
     * 给你一个无重叠的，按照区间起始端点排序的区间列表
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] s = new int[1][];
            s[0] = newInterval;
            return s;
        }
        int curLeft;
        int curRight;
        int left = newInterval[0];
        int right = newInterval[1];
        List<int[]> result = new ArrayList<>();
        boolean isOver = false;
        for (int[] interval : intervals) {
            curLeft = interval[0];
            curRight = interval[1];

            if (isOver) {
                result.add(interval);
                continue;
            }
            if (right < curLeft) {
                result.add(new int[]{left, right});
                isOver = true;
                result.add(new int[]{curLeft, curRight});
            } else if (left > curRight) {
                //说明不合格
                result.add(interval);
            } else {
                left = Math.min(curLeft, left);
                right = Math.max(curRight, right);
            }
        }
        if (!isOver) {
            result.add(new int[]{left, right});
        }
        return result.toArray(new int[result.size()][]);
    }

    //倒序遍历，如果
    public static int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int wordLength = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLength++;
            index--;
        }

        return wordLength;
    }

    /**
     * 生成nxn的矩阵
     */
    public static int[][] generateMatrix(int n) {
        int value = 1;
        int x = 0;
        int y = 0;
        int[][] result = new int[n][n];
        int state = RIGHT;
        while (value <= n * n) {
            if (state == RIGHT) {
                result[x][y] = value;
                y++;
                if (y >= n || result[x][y] != 0) {
                    state = DOWN;
                    y--;
                    x++;
                    if (x >= n || result[x][y] != 0) {
                        break;
                    }
                }
            } else if (state == DOWN) {
                result[x][y] = value;
                x++;
                if (x >= n || result[x][y] != 0) {
                    state = LEFT;
                    x--;
                    y--;
                    if (y < 0 || result[x][y] != 0) {
                        break;
                    }
                }
            } else if (state == LEFT) {
                result[x][y] = value;
                y--;
                if (y < 0 || result[x][y] != 0) {
                    state = UP;
                    y++;
                    x--;
                    if (x < 0 || result[x][y] != 0) {
                        break;
                    }
                }
            } else {
                result[x][y] = value;
                x--;
                if (x < 0 || result[x][y] != 0) {
                    state = RIGHT;
                    x++;
                    y++;
                    if (y >= n || result[x][y] != 0) {
                        break;
                    }
                }
            }

            value++;
        }
        return result;
    }

    public static String getPermutation(int n, int k) {
        int[] nums = new int[n];
        List<String> ss = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        return dfsStr(ss, nums, k, "");
    }

    public static String dfsStr(List<String> result, int[] nums, int k,
                                String target) {
        if (target.length() == nums.length) {
            result.add(new String(target));
            if (result.size() == k) {
                return target;
            }
            return "";
        }

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (!target.contains(value + "")) {
                target = target + value;
                String s = dfsStr(result, nums, k, target);
                if (!s.equals("")) {
                    return s;
                }
                target = target.substring(0, target.length() - 1);
            }
        }
        return "";
    }

    /**
     * 链接头尾节点，从尾巴节点开始数，然后到k个断开
     */
    public static LeetCode25Impl.ListNode rotateRight(LeetCode25Impl.ListNode head, int k) {
        if (k == 0) {
            return head;
        }
        LeetCode25Impl.ListNode curNode = head;
        int m = 1;
        while (curNode.next != null) {
            m++;
            curNode = curNode.next;
        }
        System.out.println(" 环 m=" + m + ",cur=" + curNode.val + ",head=" + head.val);
        //结成环
        curNode.next = head;
        int diff = k % m;
        int z = m - diff;
        LeetCode25Impl.ListNode newHead = head;
        while (z > 1) {
            System.out.println("new=" + newHead.val);
            newHead = newHead.next;
            z--;
        }
        LeetCode25Impl.ListNode result = newHead.next;
        newHead.next = null;
        return result;
    }

    /**
     * 路径计算
     * 每次只能向下或向右移动一步
     * 思路一：
     * 深度优先搜索算法
     * 思路二：
     * 动态规划
     * 能走到位置x、y的路径数量 = 能走到位置x-1，y的路径 + 能走到位置x ,y-1的路径
     */
    public static int uniquePaths(int m, int n) {
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            result[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            result[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i - 1][j] + result[i][j - 1];
                System.out.println("uniquePaths [i,j]=" + result[i][j] +
                        ",[i-1,j]=" + result[i - 1][j] + ",[i,j-1]=" + result[i][j - 1]);
            }
        }
        return result[m - 1][n - 1];
    }


    /**
     * 路径数量计算，存在障碍物
     * 还是动态规划
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] result = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                result[i][0] = 0;
                break;
            }
            result[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                result[0][i] = 0;
                break;
            }
            result[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = result[i - 1][j] + result[i][j - 1];
                }
            }
        }
        return result[m - 1][n - 1];
    }

    /**
     * 最短路径的和
     * 妈的 最短路径算法
     * 还是动态规划
     * f[x][y] = f[x-1][y] + value
     * f[x][y] = f[x][y - 1] + value
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m][n];
        result[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            result[i][0] = result[i - 1][0] + grid[i][0];
            System.out.println("x=" + i + ",y=0,value=" + result[i][0]);
        }
        for (int i = 1; i < n; i++) {
            result[0][i] = result[0][i - 1] + grid[0][i];
            System.out.println("x=0,y=" + i + ",value=" + result[0][i]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = Math.min(result[i - 1][j], result[i][j - 1]) + grid[i][j];
            }
        }
        return result[m - 1][n - 1];
    }

    public static int[] plusOne(int[] digits) {
        //反向遍历，如果相加之后需要进位，就继续加，如果遍历完成之后 还需要进位，就添加1
        int s = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int z = digits[i] + s;
            digits[i] = z % 10;
            s = z / 10;
            if (s == 0) {
                break;
            }
        }
        if (s > 0) {
            int[] newArray = new int[digits.length + 1];
            for (int i = 0; i < newArray.length; i++) {
                if (i == 0) {
                    newArray[0] = s;
                } else {
                    newArray[i] = digits[i - 1];
                }
            }
            return newArray;
        }
        return digits;
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int lessWidth = maxWidth;
        StringBuilder curStr = new StringBuilder();
        for (String curWord : words) {
            //1. 拿到当前的单词
            //2. 判定当前剩余的空格数，如果不足，则将原字符打乱重排
            //3. 进行下一行排序
            if (curWord.length() == maxWidth) {
                if (curStr.length() != 0) {
                    String s = resetCurStr(curStr.toString(), lessWidth);
                    result.add(s);
                }
                result.add(curWord);
                lessWidth = maxWidth;
                curStr = new StringBuilder();
                continue;
            }
            if (curWord.length() >= lessWidth) {
                String s = resetCurStr(curStr.toString(), lessWidth);
                result.add(s);
                curStr = new StringBuilder(curWord);
                lessWidth = maxWidth - curWord.length();
            } else {
                if (curStr.length() != 0) {
                    curStr.append(" ").append(curWord);
                    lessWidth = (lessWidth - curWord.length() - 1);
                    System.out.println(" 当前剩余单词数 lessWidth=" + lessWidth + ",curStr=" + curStr);
                } else {
                    curStr.append(curWord);
                    lessWidth = lessWidth - curWord.length();
                }
            }
        }
        if (curStr.length() > 0) {
            int width = maxWidth - curStr.length();
            addEmpty(curStr, width);
            result.add(curStr.toString());
        }
        return result;
    }

    private static String resetCurStr(String curStr, int lessWidth) {
        if (lessWidth == 0) {
            return curStr;
        }
        System.out.println(" curStr=" + curStr + ",lessWidth=" + lessWidth);
        curStr = curStr.trim();
        String[] s = curStr.split(" ");
        StringBuilder sb = new StringBuilder();

        if (s.length == 1) {
            sb.append(s[0]);
            addEmpty(sb, lessWidth);
        } else {
            int total = lessWidth + (s.length - 1);
            int num = total / (s.length - 1);
            int less = total % (s.length - 1);
            System.out.println(" 当前单词 total=" + total + ",num=" + num + ",less=" + less);
            for (int i = 0; i < s.length; i++) {
                String curS = s[i];
                sb.append(curS);
                System.out.println(" reset,curS=" + curS + ",i=" + i + ",size=" + s.length);
                if (i != s.length - 1) {
                    int curNum = num;
                    if (less > 0) {
                        curNum += 1;
                        less--;
                    }
                    addEmpty(sb, curNum);
                }
            }
        }
        return sb.toString();
    }

    private static void addEmpty(StringBuilder sb, int num) {
        for (int i = 0; i < num; i++) {
            sb.append(" ");
        }
    }

    public static int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }
        long start = 1;
        long end = x;
        long n = 1;
        while (start != (end - 1)) {
            long mid = (start + end) / 2;
            System.out.println(" left=" + start + ",end=" + end + ",mid=" + mid);
            long now = (long) mid * mid;
            if (now > x) {
                end = mid;
            } else if (now < x) {
                start = mid;
            } else {
                n = mid;
                break;
            }
        }
        if (n == 1) {
            n = start;
        }
        return (int) n;
    }

    /**
     * 爬楼梯
     * 递归
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] r = new int[n];
        r[0] = 1;
        r[1] = 2;
        for (int i = 2; i < n; i++) {
            r[i] = r[i - 1] + r[i - 2];
        }
        return r[n - 1];
    }

    public static int comb(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return comb(n - 1) + comb(n - 2);
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] reset = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                int resetValue = reset[i][j];
                if (value == 0 && resetValue == 0) {
                    //说明是原始的value
                    reset(matrix, reset, i, j, m, n);
                }
            }
        }
    }

    public static void reset(int[][] matrix, int[][] reset, int i, int j, int m, int n) {
        System.out.println("reset i=" + i + ",m=" + m + "n=" + n);
        for (int k = 0; k < m; k++) {
            if (matrix[k][j] != 0) {
                reset[k][j] = 1;
            }
            matrix[k][j] = 0;
        }
        for (int k = 0; k < n; k++) {
            if (matrix[i][k] != 0) {
                matrix[i][k] = 0;
            }
            reset[i][k] = 1;
        }
    }

    /**
     * 矩阵中是否有这个数据
     * 1. 因为有顺序，所以单行内，采用二分法，换行时先判定第一个，然后判定最后一个
     * 2.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {

        return checkRow(0, target, matrix);
    }

    private static boolean checkRow(int x, int target, int[][] matrix) {
        if (x >= matrix.length) {
            return false;
        }
        int[] row = matrix[x];
        int length = row.length;
        int firstIndex = 0;
        int lastIndex = length - 1;
        int lastValue = row[length - 1];
        System.out.println("checkRow x=" + x + ",target=" + target + ",lastValue=" + lastValue);
        if (target > lastValue) {
            return checkRow(x + 1, target, matrix);
        } else {
            //检查当前行
            boolean hasValue = false;
            while (firstIndex < (lastIndex - 1)) {
                int midIndex = (firstIndex + lastIndex) / 2;
                System.out.println("firstIndex=" + firstIndex + ",lastIndex=" + lastIndex + ",midIndex=" + midIndex);
                int midValue = row[midIndex];
                if (midValue > target) {
                    lastIndex = midIndex;
                } else if (midValue < target) {
                    firstIndex = midIndex;
                } else {
                    hasValue = true;
                    break;
                }
            }
            if (row[firstIndex] == target || row[lastIndex] == target) {
                return true;
            }
            return hasValue;
        }
    }

    /**
     * 颜色排序
     * redIndex，blueIndex
     */
    public static void sortColors(int[] nums) {
        //先遍历一次，求出所有的数量，然后再一次添加进去
        int redCount = 0;
        int whiteCount = 0;
        int blueCount = 0;
        for (int num : nums) {
            if (num == 0) {
                redCount++;
            } else if (num == 1) {
                whiteCount++;
            } else {
                blueCount++;
            }
        }
        int index = 0;
        while (redCount > 0) {
            nums[index] = 0;
            index++;
            redCount--;
        }
        while (whiteCount > 0) {
            nums[index] = 1;
            index++;
            whiteCount--;
        }
        while (blueCount > 0) {
            nums[index] = 2;
            index++;
            blueCount--;
        }
    }

    /**
     * 1。 找到t所有字串的所有位置
     * 2. 计算
     */
    public static String minWindow(String s, String t) {
        return "";
    }

    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * <p>
     * 你可以按 任何顺序 返回答案。
     */
    public static List<List<Integer>> combine(int n, int k) {
        return null;
    }

    /**
     * 采用深度优先于回溯算法
     * 采用另外一个数组记录当前经过了的路径
     */
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        //第一个找到第一个开始的位置，并且开始查找,如果有多个，就开始一个，如果为true，就返回掉
        if (word.length() == 0) {
            return false;
        }
        char c = word.charAt(0);
        ArrayList<Boolean> result = new ArrayList<>();
        boolean re = false;
        boolean isOver = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char target = board[i][j];
                int[][] paths = new int[m][n];
                if (c == target) {
                    System.out.println(" 开始哟 i=" + i + ",j=" + j);
                    dfsExist(board, paths, word, 0, i, j, result, "");
                }
                if (!result.isEmpty() && result.get(0)) {
                    re = true;
                    isOver = true;
                    break;
                }
            }
            if (isOver) {
                break;
            }
        }
        return re;
    }

    /**
     * 深度优先搜索
     */
    public static void dfsExist(char[][] board, int[][] path, String word, int index, int x, int y,
                                ArrayList<Boolean> result, String now) {
        char curChar = board[x][y];
        if (index >= word.length()) {
            result.add(true);
            return;
        }
        char c = word.charAt(index);

        System.out.println("本次 index=" + index + ",cur=" + curChar + ",word=" + c + ",x=" + x + ",y=" + y + ",now=" + now);
        if (c == curChar) {
            //可以选择左右前后四个位置
            path[x][y] = 1;
            now += curChar;
            index++;
            if (index >= word.length()) {
                result.add(true);
                return;
            }
            if (x + 1 < board.length) {
                if (path[x + 1][y] == 0) {
                    dfsExist(board, path, word, index, x + 1, y, result, now);
                }
            }
            if (y + 1 < board[0].length) {
                if (path[x][y + 1] == 0) {
                    dfsExist(board, path, word, index, x, y + 1, result, now);
                }
            }
            if (x - 1 >= 0) {
                if (path[x - 1][y] == 0) {
                    dfsExist(board, path, word, index, x - 1, y, result, now);
                }
            }
            if (y - 1 >= 0) {
                if (path[x][y - 1] == 0) {
                    dfsExist(board, path, word, index, x, y - 1, result, now);
                }
            }
            path[x][y] = 0;
        }
    }

    public static int removeDuplicates(int[] nums) {
        int index = 0;

        int num = 0;
        int lastValue = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (lastValue == nums[i]) {
                num++;
                if (num > 2) {
                    //是重复的数字哟
                    continue;
                }
            } else {
                num = 1;
                lastValue = nums[i];
            }
            //
            System.out.println("重置位置 index=" +index+","+ nums[i]+",num="+num);
            nums[index] = nums[i];
            index++;
        }
        return index;
    }
}
