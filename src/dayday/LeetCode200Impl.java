package dayday;

import java.util.*;
import java.util.List;

/**
 * LeetCode 151 ~ 200
 */
public class LeetCode200Impl {

    /**
     * 215. 数组中的第K个最大元素
     * O(n)
     */
    public static int findKthLargest(int[] nums, int k) {

        return 0;
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {

        int[] use = new int[10];
        List<List<Integer>> result = new ArrayList<>();
        int curValue = 0;

        for (int i = 1; i <= 7; i++) {
            //找从1开始的所有组合
            //说明还未使用
            List<Integer> curList = new ArrayList<>();

//            findComb(i, use, curValue, curList, k, n);

        }
        return null;
    }

    /**
     * 存在重复元素
     */
    public static boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Boolean> checkData = new HashMap<>();

        for (int num : nums) {
            Boolean aBoolean = checkData.get(num);
            if (aBoolean != null) {
                return true;
            } else {
                checkData.put(num, true);
            }
        }
        return false;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            Integer list = data.get(value);
            if (list == null) {
                data.put(value, i);
            } else {
                //说明存在
                if (Math.abs(list - i) <= k) {
                    return true;
                } else {
                    data.put(value, i);
                }
            }
        }
        return false;
    }

    private static boolean isTrue(List<Integer> list, int i, int k) {
        for (Integer integer : list) {
            if (Math.abs(i - integer) <= k) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否存在值
     * 给你一个整数数组 nums 和两个整数k和t 。请你判断是否存在 两个不同下标 i 和 j，
     * 使得abs(nums[i] - nums[j]) <= t ，
     * 同时又满足 abs(i - j) <= k 。
     * <p>
     * 如果存在则返回 true，不存在返回 false。
     * 该算法可以优化
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        //每个位置有两个属性：值，以及index
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            for (int j = i + 1; j < i + indexDiff; j++) {
                if (j >= nums.length) {
                    break;
                }
                int nextValue = nums[j];
                if (Math.abs(nextValue - value) <= valueDiff) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 滑动窗口
     */
    public static boolean containsNearbyAlmostDuplicate1(int[] nums, int indexDiff, int valueDiff) {
        if (nums.length < 2) {
            return false;
        }
        int firstIndex = 0;
        int secondIndex = 1;
        boolean isTure = false;
        while (secondIndex < nums.length) {
            //
            int firstValue = nums[firstIndex];
            int secondValue = nums[secondIndex];
            int curIndexDiff = Math.abs(firstValue - secondValue);

            if (Math.abs(firstValue - secondValue) < valueDiff) {
                if (curIndexDiff <= indexDiff) {
                    isTure = true;
                    break;
                }
            }
            //说明当前不满足
            //如果查值太大
            if (curIndexDiff > indexDiff && firstIndex < secondIndex) {
                firstIndex++;
            } else {
                secondIndex++;
            }
        }

        return isTure;
    }

    /**
     * 最大正方形
     * 找到最大的岛屿
     * 动态规划
     * d(i，j) 表示范围i，j内最大的正方形
     */
    public static int maximalSquare(char[][] matrix) {
        int x = matrix.length;
        int y = matrix[0].length;
        int[][] maxArea = new int[x][y];
        int maxSide = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        maxArea[i][j] = 1;
                    } else {
                        maxArea[i][j] = Math.min(Math.min(maxArea[i][j - 1], maxArea[i - 1][j]), maxArea[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, maxArea[i][j]);
                }

            }
        }
        return maxSide * maxSide;
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftNode = countNodes(root.left);
        int rightNode = countNodes(root.right);
        return leftNode + rightNode + 1;
    }


    /**
     * 计算重复部分的面积，总面积只和- 重复面积
     */
    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ay2 - ay1) * (ax2 - ax1);
        int area2 = (by2 - by1) * (bx2 - bx1);

        //
//        int x = Math.min(bx1,ax1) - Math.min()
        return 0;
    }

    public static TreeNode invertTree(TreeNode root) {
        exchange(root);

        return root;
    }

    private static void exchange(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = temp.right;
        root.right = temp;
        exchange(root.left);
        exchange(root.right);
    }

    public static int calculate(String s) {
        Stack<Character> num = new Stack<>();

        return 0;
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        int i = 0;
        int n = nums.length;
        while (i < n) {
            int now = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int start = nums[now];
            int last = nums[i - 1];
            StringBuilder sb = new StringBuilder(Integer.toString(start));
            if (start < last) {
                sb.append("->").append(last);
            }
            result.add(sb.toString());
        }
        return result;
    }

    private static void add(List<String> result, int start, int last) {
        String str;
        if (last == start) {
            str = start + "";
        } else {
            str = start + "->" + last;
        }
        result.add(str);
    }

    public static List<Integer> majorityElement(int[] nums) {
        int target = nums.length / 3;

        HashMap<Integer, Integer> data = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int value : nums) {
            Integer integer = data.get(value);

            if (integer == null) {
                integer = 1;
            } else {
                integer++;
            }
            data.put(value, integer);
        }
        for (Integer integer : data.keySet()) {
            Integer value = data.get(integer);
            if (value > target) {
                result.add(integer);
            }

        }
        return result;
    }

    /**
     * 二叉树搜索树中的第k小的元素
     */
    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        dfsMiddle(root, k, stack);

        TreeNode pop = stack.pop();
        return pop.val;
    }

    private static int dfsMiddle(TreeNode root, int k, Stack<TreeNode> stack) {
        if (root == null) {
            return k;
        }
        if (k <= 0) {
            return k;
        }
        int newK = dfsMiddle(root.left, k, stack);
        System.out.println("dfsMiddle k=" + k + ",root=" + root.val);
        stack.add(root);
        int newnewK = dfsMiddle(root.right, newK - 1, stack);
        return newnewK - 1;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n == 1) {
            return true;
        }
        if (n < 1) {
            return false;
        }
        boolean isP = true;
        while (n > 1) {

            int y = n % 2;
            if (y != 0) {
                isP = false;
                break;
            }
            n = n / 2;
        }
        return isP;
    }

    /**
     * 数字一的个数
     * 计算每一位上出的个数
     */
    public static int countDigitOne(int n) {
        int count = 0;
        String str = n + "";
        int index = str.length();
        for (int i = index - 1; i >= 0; i--) {
            //位数
            char c = str.charAt(i);
            int z = c - '0';
            int cur = z > 0 ? 1 : 0;

        }

        return count;
    }

    /**
     * 判定是否是回文链表
     */
    public static boolean isPalindrome(ListNode head) {
        List<ListNode> stack = new ArrayList<>();

        while (head != null) {
            stack.add(head);
            head = head.next;
        }
        int half = stack.size() / 2;
        for (int i = 0; i < half; i++) {
            ListNode node = stack.get(i);
            ListNode node1 = stack.get(stack.size() - i - 1);
            if (node.val != node1.val) {
                return false;
            }
        }
        return true;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }


    /**
     * 二叉树的最近公共祖先
     */
    public static TreeNode lowest(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;

        TreeNode leftRoot = lowest(root.left, p, q);
        TreeNode rightRoot = lowest(root.right, p, q);
        if (leftRoot != null && rightRoot != null) {
            return root;
        } else if (leftRoot != null) {
            return leftRoot;
        } else if (rightRoot != null) {
            return rightRoot;
        } else {
            return null;
        }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 1. 先遍历做个最大值
     * 2. 然后除掉当前掉值
     */
    public static int[] productExceptSelf(int[] nums) {
        int total = 1;
        int zeroCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else {
                total *= num;
            }
        }
        int length = nums.length;
        int[] result = new int[length];
        if (zeroCount > 1) {
            return result;
        }
        for (int i = 0; i < length; i++) {
            int val = nums[i];
            if (zeroCount == 0) {
                result[i] = total / val;
            } else if (zeroCount == 1) {
                if (val == 0) {
                    result[i] = total;
                } else {
                    result[i] = 0;
                }
            }
        }
        return result;
    }

    /**
     * 不用除法
     * result[i] = left * right
     */
    public static int[] productException(int[] nums) {
        int length = nums.length;
        int[] leftArr = new int[length];
        int[] rightArr = new int[length];
        leftArr[0] = 0;
        rightArr[length - 1] = 0;
        for (int i = 1; i < nums.length; i++) {
            leftArr[i] = leftArr[i - 1] * nums[i - 1];
            rightArr[length - i - 1] = rightArr[length - i] * nums[length - i];
        }
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = leftArr[i] * rightArr[i];
        }
        return result;
    }

    /**
     * 1,3,-1 = 3
     * 3,-1,-3 = -1
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = k - 1; i < nums.length; i++) {
            if (i == k - 1) {
                for (int j = 0; j < k; j++) {
                    if (maxValue <= nums[j]) {
                        maxIndex = j;
                        maxValue = nums[j];
                    }
                }
                System.out.println("初始化遍历 maxValue=" + maxValue + ",maxIndex=" + maxIndex);
            } else {
                int inValue = nums[i];
                if (inValue >= maxValue) {
                    maxValue = inValue;
                    maxIndex = i;
                    System.out.println("新加入的更大 maxValue=" + maxValue + ",maxIndex=" + maxIndex);
                } else {
                    if (maxIndex >= i - k + 1) {
                        //不变
                        System.out.println("大的值还未被淘汰 maxValue=" + maxValue + ",maxIndex=" + maxIndex);
                    } else {
                        maxValue = Integer.MIN_VALUE;
                        for (int j = i - k + 1; j < i + 1; j++) {
                            int curValue = nums[j];
                            if (curValue >= maxValue) {
                                maxValue = curValue;
                                maxIndex = j;
                            }
                        }
                        System.out.println("更新最大值 maxValue=" + maxValue + ",maxIndex=" + maxIndex);
                    }
                }
            }
            result[i - k + 1] = maxValue;
        }
        return result;
    }

    /**
     *
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> data = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer integer = data.get(c);
            if (integer == null) {
                data.put(c, 1);
            } else {
                integer++;
                data.put(c, integer);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer integer = data.get(c);
            if (integer == null) {
                return false;
            } else {
                integer--;
                if (integer == 0) {
                    data.put(c, null);
                } else {
                    data.put(c, integer);
                }
            }
        }
        return true;
    }

    public static boolean isAn(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] size = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char c1 = t.charAt(i);
            size[c - 'a']++;
            size[c1 - 'a']--;
        }
        for (int i : size) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> curList = new ArrayList<>();
        dfsTree(root, curList, result);
        return result;
    }

    public static void dfsTree(TreeNode root, List<Integer> curList, List<String> result) {
        if (root == null) {
            return;
        }
        curList.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < curList.size(); i++) {
                Integer integer = curList.get(i);
                if (i == curList.size() - 1) {
                    sb.append(integer);
                } else {
                    sb.append(integer).append("->");
                }
            }
            result.add(sb.toString());
        } else {
            dfsTree(root.left, curList, result);
            dfsTree(root.right, curList, result);
        }
        curList.remove(curList.size() - 1);
    }

    public static int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int result = 0;
        int length = String.valueOf(num).length();
        int z = 1;
        for (int i = 1; i < length; i++) {
            z *= 10;
        }
        while (true) {
            int c = num / z;
            result += c;
            num = num % z;
            if (num < 10) {
                result += num;
                break;
            }
            z /= 10;
        }

        return addDigits(result);
    }

    public static int add(int num) {
        while (num > 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

    public static int[] singleNumber(int[] nums) {
        HashMap<Integer, Integer> data = new HashMap<>();
        for (int value : nums) {
            Integer integer = data.get(value);
            if (integer == null) {
                data.put(value, 1);
            } else {
                data.remove(value);
            }
        }

        List<Integer> result = new ArrayList<>(data.keySet());
        int[] d = new int[2];
        for (int i = 0; i < result.size(); i++) {
            d[i] = result.get(i);
        }
        return d;
    }

    public static int missingNumber(int[] nums) {
        int length = nums.length;
        int[] data = new int[length + 1];
        for (int num : nums) {
            data[num] = 1;
        }
        for (int i = 0; i < data.length; i++) {
            int num = data[i];
            if (num == 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     * H指数
     * n篇论文中公共有h篇论文分别被引用了至少h次，且其余n-h篇论文每篇被引用次数不超过h次
     * 1 1
     */
    public static int hIndex(int[] citations) {
        if (citations.length == 0) return 0;
        if (citations.length == 1) {
            int x = citations[0];
            if (x >= 1) {
                return 1;
            } else {
                return 0;
            }
        }
        Arrays.sort(citations);
        System.out.println(Arrays.toString(citations));
        int max = citations.length;
        int maxValue = 0;
        for (int i = 1; i <= max; i++) {
            int curValue = citations[max - i];
            if (i == max) {
                if (curValue >= i) {
                    maxValue = i;
                }
            } else {
                int leftValue = citations[max - i - 1];
                if (curValue >= i && leftValue <= i) {
                    //说明有
                    maxValue = Math.max(i, maxValue);
                }
            }
        }
        return maxValue;
    }

    /**
     * citations是排过序的
     * 那么用二分来找
     */
    public static int hIndex2(int[] citations) {
        int leftIndex = 0;
        int rightIndex = citations.length - 1;
        while (leftIndex <= rightIndex) {
            int middle = (leftIndex + rightIndex) / 2;
            int value = citations[middle];
            if (value > middle) {
                //说明
            }
        }
        return 0;
    }

    public static int isFirstBadVersion(int n) {
        int left = 0;
        int right = n;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (isBadVersion(middle)) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return left;
    }

    public static boolean isBadVersion(int n) {
        return false;
    }

    /**
     * 完全平方数
     * 动态规划
     */
    public static int numSquares(int n) {
        List<Integer> data = new ArrayList<>();
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
//                min = Math.min(min, f[])
            }
        }
        for (int i = n; i >= 0; i--) {
//            if (isSquare(i)) {
//                data.add(i);
//            }
        }
        return 0;
    }

    public static void moveZeroes(int[] nums) {
        int startIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value == 0) {
                if (startIndex == -1) {
                    startIndex = i;
                }
            } else {
                //不等于0，
                if (startIndex != -1) {
                    nums[i] = 0;
                    nums[startIndex] = value;
                    startIndex++;
                }
            }
        }
    }

    public static int findDuplicate(int[] nums) {
        int[] data = new int[nums.length + 1];
        for (int num : nums) {
            if (data[num] == 1) {
                return num;
            } else {
                data[num] = 1;
            }
        }
        return 0;
    }

    /**
     * 采用一些额外的数据来标识
     * 遍历后：2-> 之前是死的，现在变活了
     * 3 -> 之前是活的，现在死了
     */
    public static void gameOfLife(int[][] board) {
        int x = board.length;
        int y = board[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int state = board[i][j];
                int left = i - 1;
                int right = i + 1;
                int top = j - 1;
                int bottom = j + 1;
                int count = 0;
                if (i > 0) {
                    if (j > 0) {
                        count += board[i - 1][j - 1] % 2;
                    }
                    if (j < y - 1) {
                        count += board[left][bottom] % 2;
                    }
                    count += board[left][j] % 2;
                }
                if (right < x) {
                    if (top >= 0) {
                        count += board[right][top] % 2;
                    }
                    if (bottom < y) {
                        count += board[right][bottom] % 2;
                    }
                    count += board[right][j] % 2;
                }
                if (top >= 0) {
                    count += board[i][top] % 2;
                }
                if (bottom < y) {
                    count += board[i][bottom] % 2;
                }
                if (state == 1) {
                    if (count < 2 || count > 3) {
                        state = 3;
                    }
                } else {
                    if (count == 3) {
                        state = 2;
                    }
                }
                board[i][j] = state;
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int value = board[i][j];
                if (value == 2) {
                    board[i][j] = 1;
                } else if (value == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private static int judge(int[][] board, int i, int j, int x, int y) {
        int value = board[i][j];
        int left = i - 1;
        int right = i + 1;
        int top = j - 1;
        int bottom = j + 1;
        int count = 0;
        if (left >= 0) {
            if (top >= 0) {
                count += board[left][top] % 2;
            }
            if (bottom < y) {
                count += board[left][bottom] % 2;
            }
            count += board[left][j] % 2;
        }
        if (right < x) {
            if (top >= 0) {
                count += board[right][top] % 2;
            }
            if (bottom < y) {
                count += board[right][bottom] % 2;
            }
            count += board[right][j] % 2;
        }
        if (top >= 0) {
            count += board[i][top] % 2;
        }
        if (bottom < y) {
            count += board[i][bottom] % 2;
        }
        if (value == 1) {
            if (count < 2) {
                return 3;
            } else if (count == 2 || count == 3) {
                return 1;
            } else {
                return 3;
            }
        } else {
            if (count == 3) {
                return 2;
            }
        }
        return value;
    }

    public static boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }
        return n % 4 != 0;
    }

    public String getHint(String secret, String guess) {
        //对比A
        int min = Math.min(secret.length(), guess.length());
        int count = 0;
        int bCount = 0;
        List<Character> has = new ArrayList<>();
        for (int i = 0; i < min; i++) {
            char target = secret.charAt(i);
            char c = guess.charAt(i);
            if (target == c) {
                count++;
            } else {
                //如果没有匹配上，就去尝试找其他的匹配
                if (!has.contains(c)) {
                    boolean contains = secret.contains(c + "");
                    if (contains) {
                        has.add(c);
                        bCount++;
                    }
                }
            }
        }
        return count + "A" + bCount + "B";
    }

    public static int bulSwitch(int n) {
        //second
        int count = 0;
        for (int i = 1; i <= n; i++) {
            boolean isTrue = checkCur(i, n);
            if (!isTrue) {
                count++;
            }
        }
        return count;
    }

    private static boolean checkCur(int i, int n) {
        int count = 0;
        System.out.println("check i=" + i + ",n=" + n + ",n/i=" + (n / i));
        for (int j = n; j > 0; j--) {
            if (i % j == 0) {
                count++;
                System.out.println(" j=" + j + ",i=" + i + ",count=" + count);
            }
        }
        return count % 2 == 0;
    }

    /**
     * 零钱兑换
     * 动态规划
     * 一块钱，需要最少几个
     * 二块钱，最少需要几个
     * 三块钱，最少需要几个
     */
    public static int coinChange(int[] coins, int amout) {
        int[] min = new int[amout + 1];
        Arrays.fill(min, amout + 1);

        for (int i = 1; i < min.length; i++) {
            //i当前的钱数量
            for (int j = 0; j < coins.length; j++) {
                int cutCoin = coins[j];
                if (cutCoin <= j) {
                    min[j] = Math.max(min[j], min[j - cutCoin] + 1);
                }
            }
        }
        return min[amout] == amout + 1 ? -1 : min[amout];
    }

    /**
     * 摆动排序-2
     */
    public static void wiggleSort(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);

        int rightIndex = nums.length;
        int x = (rightIndex + 1) / 2;
        for (int i = 0, j = x - 1, k = rightIndex - 1; i < rightIndex; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < rightIndex) {
                nums[i + 1] = arr[k];
            }
        }
    }

    /**
     * 3的平方数特点
     * 能被3整除
     */
    private static boolean isPowerOfThree(int n) {
        while (n >= 3) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 区间和的个数
     */
    public static int countRangeSum(int[] nums, int lower, int uper) {
        return 0;
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode slowNode = head;
        ListNode fastParentNode = head.next;
        ListNode fastNode = head.next.next;

        while (fastNode != null) {
            System.out.println("start slow=" + slowNode.val + ",parent=" + fastParentNode.val + ",fast=" + fastNode.val);
            //recover
            ListNode reFastParent = fastNode.next;
            ListNode reFastTemplate = null;
            if (reFastParent != null) {
                reFastTemplate = reFastParent.next;
            }

            //exchange
            ListNode tempNode = slowNode.next;
            ListNode fastTempNode = fastNode.next;

            slowNode.next = fastNode;
            fastNode.next = tempNode;
            fastParentNode.next = fastTempNode;

            //
            slowNode = slowNode.next;
            fastParentNode = reFastParent;
            fastNode = reFastTemplate;
            if (fastParentNode != null) {
                System.out.println("end slow=" + slowNode.val + ",parent=" + fastParentNode.val + ",fast=" + fastNode.val);
            }
        }

        return head;
    }

    /**
     * 前序序列化树的有效性
     * 特点：先自己，再左再右
     */
    public static boolean isValidSerialization(String preorder) {
        if (preorder.length() == 0) {
            return false;
        }
        if (preorder.length() == 1) {
            char c = preorder.charAt(0);

            return c == '#';
        }
        //节点数组
        String[] split = preorder.split(",");
        Stack<Integer> cur = new Stack<>();
        cur.push(1);
        boolean isRight = true;
        boolean isFirst = true;
        for (String s : split) {
            if (isFirst && "#".equals(s)) {
                isRight = false;
                break;
            }
            isFirst = false;
            if ("#".equals(s)) {
                if (cur.isEmpty()) {
                    isRight = false;
                    break;
                }
                //消耗一个数量
                int pop = cur.pop() - 1;
                if (pop < 0) {
                    break;
                } else if (pop > 0) {
                    cur.push(pop);
                }
            } else {
                //每遇到一个正数，需要增加两个槽位
                if (cur.isEmpty()) {
                    isRight = false;
                    break;
                }
                cur.add(2);
                int pop = cur.pop() - 1;
                if (pop > 0) {
                    cur.push(pop);
                } else if (pop < 0) {
                    break;
                }
            }
            System.out.println("stack=" + cur);
        }

        return cur.isEmpty() && isRight;
    }

    /**
     * 贪心算法
     */
    public static boolean increasingTriplet(int[] nums) {
        int first = nums[0];
        int second = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }

    /**
     * 动态规划
     * 每个节点有选和不选两种状态
     * yi
     */
    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = countOnes(i);
        }
        return ans;
    }

    private static int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }

    /**
     * i & (i-1) 表示i的最低放置位由1变成0
     * i——》10——》   1010
     * i-1——》9——》  1001
     * i & (i-1) ——》1000
     */
    public static int[] fastCountBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }

    public static boolean isPowerOfFour(int n) {
        int last = -1;
        while (n > 1) {
            last = n % 4;
            if (last > 0) {
                return false;
            }
            n /= 4;
        }
        return n == 1 && last == 0;
    }

    public static void reverseString(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            int lastIndex = n - i - 1;
            char temp = s[lastIndex];
            s[lastIndex] = s[i];
            s[i] = temp;
        }
    }

    /**
     * 反转
     * a,e,i,o,u
     */
    public static String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int rightIndex = chars.length - 1;
        int leftIndex = 0;

        while (leftIndex < rightIndex) {
            char leftChar = chars[leftIndex];
            char rightChar = chars[rightIndex];
            if (isVowels(leftChar) && isVowels(rightChar)) {
                //两个都是，可以交换了
                chars[rightIndex] = leftChar;
                chars[leftIndex] = rightChar;
                leftIndex++;
                rightIndex--;
            } else if (!isVowels(leftChar)) {
                leftIndex++;
            } else if (!isVowels(rightChar)) {
                rightIndex--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return sb.toString();
    }

    private static boolean isVowels(char s) {

        return s == 'a' || s == 'e'
                || s == 'i' || s == 'o' || s == 'u'
                || s == 'A' || s == 'E'
                || s == 'I' || s == 'O' || s == 'U';
    }

    /**
     * 找到第一个相等的数据
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int firstIndex = 0;
        int secondIndex = 0;
        List<Integer> result = new ArrayList<>();

        while (firstIndex < nums1.length && secondIndex < nums2.length) {
            int firstValue = nums1[firstIndex];
            int secondValue = nums2[secondIndex];
            if (firstValue == secondValue) {
                result.add(firstValue);
                firstIndex++;
                secondIndex++;
            } else if (firstValue > secondValue) {
                secondIndex++;
            } else {
                firstIndex++;
            }
        }

        int[] s = new int[result.size()];
        int index = 0;
        for (int num : result) {
            s[index] = num;
            index++;
        }

        return s;
    }

    /**
     * 数字个数
     * 动态规划
     * n = 1
     * n = 2 91 = (1 ~ 100,9)
     * n = 3 (11x,22x,33x,9 * 3 = 27,110,111,112,113,114,115,116,117,118,119,
     * 101,,121,131,141,151,161,171,181,191
     * ,211,311,411,511,611,711,811,911) (n * 9 - n)
     * n = max(n-1)*
     */
    public static int count(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;

    }

}
