package dayday;

import java.util.*;

/**
 * LeetCode 101 ~ 125
 */
public class LeetCode125Impl {

    /**
     * 中心扩散法
     * 重点：区分偶数串和奇数串的情况
     */
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        if (chars.length < 2) {
            return s;
        }
        int maxLength = 1;
        int index = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            int oddLength = longest(chars, i, i);
            int doubleLength = longest(chars, i, i + 1);
            int curMax = Math.max(oddLength, doubleLength);
            if (curMax > maxLength) {
                maxLength = curMax;
                index = i;
            }
        }
        if (maxLength % 2 == 0) {
            //说明是偶数
            index = index - (maxLength - 1) / 2;
        } else {
            index = index - maxLength / 2;
        }
        return s.substring(index, index + maxLength);
    }

    /**
     * 分别判定偶和奇的情况
     */
    public static int longest(char[] chars, int left, int right) {
        char leftChar = chars[left];
        char rightChar = chars[right];
        if (leftChar != rightChar) {
            return 1;
        }
        while (left >= 0 && right < chars.length) {
            if (chars[left] != chars[right]) {
                break;
            }
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 动态规划实现
     */
    public static String longestPalindromeNew(String s) {
        char[] chars = s.toCharArray();
        if (chars.length < 2) {
            return s;
        }
        boolean[][] result = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i][i] = true;
        }
        int maxLength = 1;
        int startIndex = 0;
        int len = chars.length;
        //L 子串长度
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = i + L - 1;
                if (j >= len) {
                    break;
                }
                if (chars[i] != chars[j]) {
                    result[i][j] = false;
                } else {
                    if (j - i < 3) {
                        result[i][j] = true;
                    } else {
                        result[i][j] = result[i + 1][j - 1];
                    }
                }
                if (result[i][j] && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    startIndex = i;
                }
            }

        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, i + 1, nums.length - 1, -nums[i], result);
        }
        return result;
    }

    private static void twoSum(int[] nums, int start, int end, int target, List<List<Integer>> result) {
        while (start < end) {
            List<Integer> answer = new ArrayList<>();
            int startValue = nums[start];
            int endValue = nums[end];
            int sum = startValue + endValue;
            if (target == sum) {
                answer.add(-target);
                answer.add(startValue);
                answer.add(endValue);
                result.add(answer);
                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                start++;
                while (start < end && nums[end] == nums[end - 1]) {
                    end--;
                }
                end--;
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
    }

    /**
     * 找出三数之和最接近target的排列
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int best = 10000000;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int twoClosest = twoSumClosest(nums, i + 1, nums.length - 1, target - value);
            int diff = target - value - twoClosest;
            if (Math.abs(diff) < Math.abs(target - best)) {
                best = value + twoClosest;
            }
            System.out.println(" 外 best=" + best + ",value=" + value + ",two=" + twoClosest);
        }
        return best;
    }

    private static int twoSumClosest(int[] nums, int start, int end, int target) {
        int best = 10000000;
        System.out.println(" start=" + start + ",end=" + end + ",target=" + target);
        while (start < end) {
            int startValue = nums[start];
            int endValue = nums[end];
            int cur = startValue + endValue;
            int abs = Math.abs(cur - target);
            System.out.println("循环 cur=" + cur + ",target=" + target + ",best=" + best);
            if (abs < Math.abs(best - target)) {
                best = cur;
            }
            if (cur == target) {
                return cur;
            } else if (cur < target) {
                start++;
            } else {
                end--;
            }
        }

        return best;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        //排序
        ArrayList<List<Integer>> quadruplets = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 1] + nums[length - 2] + nums[length - 3] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 1] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1;
                int right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    public static int divide(int dividend, int divisor) {
        int sign;
        if (((dividend ^ divisor) >> 31 & 0x1) == 1) {
            sign = -1;
        } else {
            sign = 1;
        }
        long newDividend = Math.abs((long) dividend);
        long newDivisor = Math.abs((long) divisor);
        long result = 0;
        while (newDividend >= newDivisor) {
            long i = 1;
            long tmp = newDivisor;
            while (newDividend >= tmp) {
                newDividend -= tmp;
                result += i;
                i = i << 1;
                tmp = tmp << 1;
            }
        }
        result ^= sign;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private static void reverse(int[] nums, int i) {
        int left = i, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int length = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        if (length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();

        dfs(candidates, length, 0, target, path, result);
        return result;
    }

    private static void dfs(int[] candidates, int length, int begin, int target, List<Integer> path, List<List<Integer>> result) {
        System.out.println("dfs=" + path + ",begin=" + begin + ",target=" + target);
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int j = begin; j < length; j++) {
            if (target - candidates[j] < 0) {
                //说明后续没有合适的数据
                break;
            }
            if (j > begin && candidates[j] == candidates[j - 1]) {
                continue;
            }
            path.add(candidates[j]);

            dfs(candidates, length, j + 1, target - candidates[j], path, result);
            //移除掉上一个添加的数字
            path.remove(path.size() - 1);
        }
    }

    public static int firstMissingPosive(int[] num) {
        int len = num.length;
        //1. 处理负数
        System.out.println("1. nums=" + Arrays.toString(num));
        boolean hasOne = false;
        for (int i = 0; i < len; i++) {
            int value = num[i];
            if (value == 1) {
                hasOne = true;
            }
            if (value < 0 || value > len) {
                num[i] = 1;
            }
        }
        if (!hasOne) {
            return 1;
        }

        System.out.println("2. nums=" + Arrays.toString(num));
        for (int i = 0; i < len; i++) {
            int value = Math.abs(num[i]) - 1;
            num[value] = -Math.abs(num[value]);
        }
        System.out.println("3. nums=" + Arrays.toString(num));
        for (int i = 0; i < len; i++) {
            int value = num[i];
            if (value > 0) {
                return i + 1;
            }
        }

        return len + 1;
    }

    /**
     * 递归 回溯算法
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        boolean[] vis = new boolean[nums.length];
        List<Integer> stack = new ArrayList<>();
        //先排序
        Arrays.sort(nums);
        backtrack(nums, vis, 0, stack, result);
        return result;
    }

    private static void backtrack(int[] nums, boolean[] vis, int begin, List<Integer> stack,
                                  List<List<Integer>> result) {
        if (begin == nums.length) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            stack.add(nums[i]);
            vis[i] = true;
            backtrack(nums, vis, begin + 1, stack, result);
            vis[i] = false;
            stack.remove(begin);
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        //答案
        List<List<String>> solutions = new ArrayList<>();

        int[] queens = new int[n];
        Arrays.fill(queens, -1);

        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();

        backtrackSolve(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    private static void backtrackSolve(List<List<String>> solutions, int[] queens, int n, int row,
                                       Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            //生成结果
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrackSolve(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    private static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static int totalNQueens(int n) {
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();

        return backtrackSolveNew(n, 0, columns, diagonals1, diagonals2);
    }

    private static int backtrackSolveNew(int n, int row,
                                         Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            //生成结果
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backtrackSolveNew(n, row + 1, columns, diagonals1, diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }

    public static int totalNQueensTwo(int n) {
        return solve(n, 0, 0, 0, 0);
    }

    private static int solve(int n, int row, int columns, int diagonals1, int diagonals2) {
        if (n == row) {
            return 1;
        } else {
            int count = 0;
            int availablePositions = ((1 << n) - 1) & (-(columns | diagonals1 | diagonals2));
            while (availablePositions != 0) {
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions & (availablePositions - 1);
                count += solve(n, row + 1, columns | position,
                        (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
            }
            return count;
        }
    }

    public static int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int num : nums) {
            //上一个和,一旦当前出现了负数，那么就不是合格的连续大数
            pre = Math.max(pre + num, num);
            maxAns = Math.max(pre, maxAns);
        }

        return maxAns;
    }

    private static int count = 0;

    public static String getPermutation(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = i + 1;
        }
        if (k == 1) {
            return toStr(seq);
        }
        //1. 计算每组包含的个数
        int nCount = 1;
        for (int i = n - 1; i >= 1; i--) {
            nCount *= i;//3! = 3*2*1
        }
        System.out.println(" nCount=" + nCount);
        //2. 计算从哪个开始
        int locateK = 0;
        while (locateK < k) {
            locateK += nCount;//计算开始的位置
        }
        System.out.println("old locateK=" + locateK);
        //计算从哪个数字开始
        locateK /= nCount;

        count = k - (locateK - 1) * nCount - 1;
        System.out.println("new locateK=" + locateK + ",count=" + count);
        List<Integer> path = new ArrayList<>();
        path.add(seq[locateK - 1]);
        backtrack(seq, path, res);
        return toStr(res.get(0));
    }

    private static void backtrack(int[] seq, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == seq.length) {
            if (count == 0) {
                res.add(new ArrayList<>(path));
            } else {
                count--;
            }
            return;
        }
        for (int j : seq) {
            if (!path.contains(j)) {
                path.add(j);
                backtrack(seq, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private static String toStr(List<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : path) {
            sb.append(integer);
        }
        return sb.toString();
    }

    private static String toStr(int[] seq) {
        StringBuilder sb = new StringBuilder();
        for (int i : seq) {
            sb.append(i);
        }
        return sb.toString();
    }

    /**
     * 二进制求和
     */
    public static String addBinary(String a, String b) {
        int max = Math.max(a.length(), b.length());

        int lastChar = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < max; i++) {
            lastChar += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            lastChar += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;

            sb.append((char) lastChar % 2 + '0');
            lastChar /= 2;
        }

        if (lastChar == 1) {
            sb.append("1");
        }
        sb.reverse();

        return sb.toString();
    }

    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (n * m == 0) {
            //说明有一个是空串
            return n + m;
        }
        //状态数组
        int[][] D = new int[n + 1][m + 1];
        //初始化边界值
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            D[0][i] = i;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    /**
     * 滑动窗口？
     */
    public static String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        int[] winFreq = new int[128];
        int[] tFreq = new int[128];

        for (char tChar : tChars) {
            tFreq[tChar]++;
        }

        int distance = 0;
        int minLen = sLen + 1;
        int begin = 0;

        int left = 0;
        int right = 0;

        while (right < sLen) {
            //说明还未滑动到最右侧
            if (tFreq[sChars[right]] == 0) {
                //说明是无效元素
                right++;
                continue;
            }

            if (winFreq[sChars[right]] < tFreq[sChars[right]]) {
                //说明右边元素还不够
                distance++;
            }
            //往右边移动一个
            winFreq[sChars[right]]++;
            right++;

            while (distance == tLen) {
                //说明完全包含了t的所有字符
                if (tFreq[sChars[left]] == 0) {
                    //说明左侧是无效元素
                    left++;
                    continue;
                }
                if (right - left < minLen) {
                    minLen = right - left;
                    begin = left;
                }

                if (winFreq[sChars[left]] == tFreq[sChars[left]]) {
                    distance--;
                }
                winFreq[sChars[left]]--;
                left++;

            }
        }
        if (minLen == sLen + 1) {
            return "";
        }
        return s.substring(begin, begin + minLen);
    }

    /**
     * 返回所有k个数的组合
     * 递归回溯算法
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfsCombine(n, 1, k, new ArrayList<>(), result);
        return result;
    }

    public static void dfsCombine(int n, int begin, int target, List<Integer> path, List<List<Integer>> result) {
        if (path.size() + (n - begin + 1) < target) {
            return;
        }
        if (target == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        path.add(begin);
        dfsCombine(n, begin + 1, target, path, result);
        path.remove(path.size() - 1);
        //不考虑选择当前位置
        dfsCombine(n, begin + 1, target, path, result);

    }

    /**
     * 一个排序的重复数组
     * 二分法查找
     * 1. 如果旋转的位置 刚好的是重复数据，
     */
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = nums[mid];
            int leftValue = nums[left];
            int rightValue = nums[right];
            if (midValue == target) {
                return true;
            }
            if (leftValue == midValue && midValue == rightValue) {
                left++;
                right--;
            } else if (leftValue <= midValue) {
                if (leftValue <= target && target < midValue) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (midValue < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 删除链表中的重复元素
     * 给定一个已排序的链表的头head
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0, head);

        ListNode curNode = dummy;
        while (curNode.next != null && curNode.next.next != null) {
            if (curNode.next.val == curNode.next.next.val) {
                int x = curNode.next.val;
                while (curNode.next != null && curNode.next.val == x) {
                    curNode.next = curNode.next.next;
                }
            } else {
                curNode = curNode.next;
            }
        }

        return dummy.next;
    }

    /**
     * 柱状图中最大的矩形
     */
    public static int largestRectangleAre(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int max = heights[0];
        for (int i = 0; i < heights.length; i++) {
            int curValue = heights[i];
            //往左找到第一个小于他的值
            int left = i;
            while (left >= 0) {
                int leftValue = heights[left];
                if (leftValue < curValue) {
                    break;
                }
                left--;
            }

            int right = i;
            while (right < heights.length) {
                int rightValue = heights[right];
                if (rightValue < curValue) {
                    break;
                }
                right++;
            }
            System.out.println("left=" + left + ",right=" + right + ",curValue=" + curValue);
            int curMax = curValue * (right - left - 1);
            max = Math.max(curMax, max);
        }
        return max;
    }

    public static int largest(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }
        int area = 0;
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        len += 2;
        heights = newHeights;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            System.out.println("stack=" + stack);
            while (heights[stack.peekLast()] > heights[i]) {
                int height = heights[stack.removeLast()];
                int widht = i - stack.peekLast() - 1;
                area = Math.max(area, widht * height);
            }
            stack.addLast(i);
        }
        return area;
    }

    /**
     * 递归+回溯
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfsSubsets(nums, 0, new ArrayList<>(), result, true);
        return result;
    }

    public static void dfsSubsets(int[] nums, int begin, List<Integer> stack,
                                  List<List<Integer>> result, boolean isAdd) {
        if (begin == nums.length) {
            result.add(new ArrayList<>(stack));
            return;
        }
        dfsSubsets(nums, begin + 1, stack, result, false);
        if (!isAdd && begin > 0 && nums[begin - 1] == nums[begin]) {
            return;
        }
        stack.add(nums[begin]);
        dfsSubsets(nums, begin, stack, result, true);
        stack.remove(stack.size() - 1);
    }

    /**
     * 生成并返回所有由n个节点组成的不通二叉搜索树
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }

    public static List<TreeNode> generateTrees(int left, int right) {
        List<TreeNode> result = new ArrayList<>();
        if (left > right) {
            result.add(null);
            return result;
        }
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftTrees = generateTrees(left, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, right);
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode curTree = new TreeNode(i);
                    curTree.left = leftTree;
                    curTree.right = rightTree;
                    result.add(curTree);
                }
            }
        }
        return result;
    }

    public static int numTrees(int n) {
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                result[i] += result[j - 1] * result[i - j];
            }
        }
        return result[n];
    }

    public static void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        //找到被交换的两个
        int[] swapped = findTwoSwapped(nums);
        //恢复交换
        recover(root, 2, swapped[0], swapped[1]);
    }

    private static void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                --count;
                if (count == 0) {
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }

    private static int[] findTwoSwapped(List<Integer> nums) {
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i + 1) < nums.get(i)) {
                index2 = i + 1;
                if (index1 == -1) {
                    index1 = i;
                } else {
                    break;
                }
            }
        }
        int x = nums.get(index1), y = nums.get(index2);

        return new int[]{x, y};
    }

    /**
     * 中序遍历 拆开
     */
    public static void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

}
