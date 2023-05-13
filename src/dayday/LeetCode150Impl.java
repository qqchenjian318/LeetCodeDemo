package dayday;

import java.util.*;

/**
 * LeetCode 126 ~ 150
 */
public class LeetCode150Impl {

    private static Map<Integer, Integer> indexMap;

    public static TreeNode myBuildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight
            , int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        //前序遍历中的第一个节点就是根节点
        //中序遍历中定位根节点
        int inorderRoot = indexMap.get(preorder[preorderLeft]);
        //
        TreeNode root = new TreeNode(preorder[preorderLeft]);

        int sizeLefSub = inorderRoot - inorderLeft;

        root.left = myBuildTree(preorder, inorder, preorderLeft + 1,
                preorderLeft + sizeLefSub, inorderLeft, inorderRoot - 1);

        root.right = myBuildTree(preorder, inorder, preorderLeft + sizeLefSub + 1, preorderRight,
                inorderRoot + 1, inorderRight);
        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        //构建哈希映射
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private int post_idx;
    private int[] postorder;
    private int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<>();

    public TreeNode buildTreeNew(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;

        this.post_idx = postorder.length - 1;

        int idx = 0;
        for (int i : inorder) {
            idx_map.put(i, idx++);
        }
        return helper(0, inorder.length - 1);
    }

    public TreeNode helper(int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }
        int rootVal = postorder[post_idx];
        TreeNode root = new TreeNode(rootVal);

        int index = idx_map.get(rootVal);

        post_idx--;
        root.right = helper(index + 1, inRight);
        root.left = helper(inLeft, index - 1);
        return root;
    }

    //层序遍历
    public static NodeNext connect(NodeNext root) {
        if (root == null) {
            return null;
        }
        Queue<NodeNext> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            NodeNext last = null;
            for (int i = 1; i <= size; i++) {
                NodeNext poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                if (i != 1) {
                    last.next = poll;
                }
                last = poll;
            }
        }
        return root;
    }

    /**
     * 求多少个点在同一条直线上
     */
    public static int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        }
        Map<Float, Integer> map = new HashMap<>();
        int max = 0;
        for (int[] point : points) {
            map.clear();
            int i = find(map, point, points);
            max = Math.max(max, i);
        }

        return max + 1;
    }

    private static int find(Map<Float, Integer> map, int[] target, int[][] data) {
        for (int[] point : data) {
            if (point[0] == target[0] && point[1] == target[1]) {
                continue;
            }
            int x = point[0] - target[0];
            int y = point[1] - target[1];

            if (x == 0) {
                Integer integer = map.get(Float.MAX_VALUE);
                if (integer == null) {
                    map.put(Float.MAX_VALUE, 1);
                } else {
                    integer++;
                    map.put(Float.MAX_VALUE, integer);
                }
            } else if (y == 0) {
                Integer integer = map.get(Float.MIN_VALUE);
                if (integer == null) {
                    map.put(Float.MIN_VALUE, 1);
                } else {
                    integer++;
                    map.put(Float.MIN_VALUE, integer);
                }
            } else {
                float key = y * 1f / x;
                System.out.println("key = " + key);
                Integer integer = map.get(key);
                if (integer == null) {
                    map.put(key, 1);
                } else {
                    integer++;
                    map.put(key, integer);
                }
            }
        }
        int max = 0;
        for (Map.Entry<Float, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();

            max = Math.max(value, max);
        }
        return max;
    }

    /**
     * 逆波兰表达式
     */
    public static int evalRPN(String[] tokens) {
        if (tokens == null) return 0;
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if ("+".equals(token)) {
                countStack(token, stack);
            } else if ("-".equals(token)) {
                countStack(token, stack);
            } else if ("*".equals(token)) {
                countStack(token, stack);
            } else if ("/".equals(token)) {
                countStack(token, stack);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public static void countStack(String eval, Stack<Integer> stack) {
        Integer second = stack.pop();
        Integer first = stack.pop();
        System.out.println("first=" + first + ",second=" + second + ",e=" + eval);
        if ("+".equals(eval)) {
            int value = first + second;
            stack.push(value);
        } else if ("-".equals(eval)) {
            int value = first - second;
            stack.push(value);
        } else if ("*".equals(eval)) {
            int value = first * second;
            stack.push(value);
        } else {
            int value = first / second;
            stack.push(value);
        }
    }

    /**
     * 相交链表
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> map = new HashMap<>();
        while (headA != null) {
            map.put(headA, 1);
            headA = headA.next;
        }
        ListNode findNode = null;
        while (headB != null) {
            Integer integer = map.get(headB);
            if (integer == 1) {
                findNode = headB;
                break;
            }
            headB = headB.next;
        }

        return findNode;
    }


    public static ListNode getIntersectionNodeNew(ListNode headA, ListNode headB) {
        ListNode firstNode = headA, secondNode = headB;

        while (firstNode != secondNode) {
            firstNode = firstNode == null ? headB : firstNode.next;
            secondNode = secondNode == null ? headA : secondNode.next;
        }
        return firstNode;
    }

    private static ListNode changeList(ListNode headA) {
        ListNode lastNode = null;
        while (headA != null) {
            ListNode nextNode = headA.next;

            headA.next = lastNode;
            lastNode = headA;
            headA = nextNode;
        }

        return lastNode;
    }

    /**
     * 查找峰值
     * log(n) 二分法
     */
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                return 0;
            } else {
                return 1;
            }
        }
        int start = 0;
        int end = nums.length - 1;
        return findPeak(nums, start, end);
    }

    public static int findPeak(int[] nums, int start, int end) {
        if (start < end) {
            if (start == end - 1) {
                int i = checkNum(nums, start);
                if (i != -1) {
                    return i;
                }
                return checkNum(nums, end);
            }
            int mid = (start + end) / 2;
            int value = nums[mid];
            System.out.println("findPeak start=" + start + ",end=" + end + ",mid=" + mid + ",value=" + value);
            int i = checkNum(nums, mid);
            if (i != -1) {
                return i;
            }
            int peak = findPeak(nums, start, mid);
            if (peak != -1) {
                return peak;
            }
            return findPeak(nums, mid, end);
        }
        return -1;
    }

    private static int checkNum(int[] nums, int mid) {
        int value = nums[mid];
        if (mid == nums.length - 1) {
            int beforeValue = nums[mid - 1];
            if (value > beforeValue) {
                return mid;
            }
        } else if (mid == 0) {
            int lastValue = nums[mid + 1];
            if (value > lastValue) {
                return mid;
            }
        } else {
            int beforeMid = nums[mid - 1];
            int lastValue = nums[mid + 1];
            if (value > beforeMid && value > lastValue) {
                return mid;
            } else {
                return -1;
            }
        }
        return -1;
    }

    /**
     * 最大间距
     */
    public static int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        Arrays.sort(nums);

        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            int beforeValue = nums[i - 1];
            max = Math.max(value - beforeValue, max);
        }
        return max;
    }

    public static int compareVersion(String version1, String version2) {
        String[] split = version1.split("\\.");
        String[] split1 = version2.split("\\.");
        int maxLength = Math.max(split.length, split1.length);
        for (int i = 0; i < maxLength; i++) {
            int first = 0;
            if (i < split.length) {
                first = Integer.parseInt(split[i]);
            }
            int second = 0;
            if (i < split1.length) {
                second = Integer.parseInt(split1[i]);
            }
            System.out.println("i=" + i + ",f=" + first + ",s=" + second);
            if (first > second) {
                return 1;
            } else if (first < second) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 两数之和
     */
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int value = numbers[i];
            if (value > target) {
                break;
            }
            int[] result = findNums(numbers, i, target - value);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private static int[] findNums(int[] numbers, int start, int target) {
        int startIndex = start + 1;
        int endIndex = numbers.length - 1;
        while (startIndex <= endIndex) {
            int mid = (start + endIndex) / 2;
            int midValue = numbers[mid];
            System.out.println("find mid=" + mid + ",midValue=" + midValue + ",target=" + target
                    + ",s=" + start + ",e=" + endIndex);
            if (midValue == target) {
                return new int[]{start + 1, mid + 1};
            } else if (mid < target) {
                startIndex = mid + 1;
            } else {
                endIndex = mid - 1;
            }
        }
        return null;
    }

    public static int[] twoSumFast(int[] numbers, int target) {
        int startIndex = 0;
        int endIndex = numbers.length - 1;
        while (startIndex < endIndex) {
            int startValue = numbers[startIndex];
            int endValue = numbers[endIndex];
            int curValue = startValue + endValue;
            if (curValue == target) {
                return new int[]{startIndex + 1, endIndex + 1};
            } else if (curValue < target) {
                startIndex++;
            } else {
                endIndex--;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 返回excel表格里面的名称
     * 26进制
     * num = 0，说明小于26，那就A-Z,A = 65
     */
    public static String convertToTile(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            columnNumber--;
            sb.append((char) (columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }

        return sb.reverse().toString();
    }


    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int num : nums) {
            Integer integer = result.get(num);
            if (integer == null) {
                result.put(num, 1);
            } else {
                integer++;
                result.put(num, integer);
            }
        }
        int max = Integer.MIN_VALUE;
        int value = -1;
        Set<Integer> integers = result.keySet();
        for (Integer key : integers) {
            Integer cur = result.get(key);
            if (cur > max) {
                value = key;
                max = cur;
            }
        }
        return value;
    }

    public static int ma(int[] nums) {
        Arrays.sort(nums);
        int mid = nums.length / 2;
        return nums[mid];
    }

    /**
     * 如果有一个数为众数
     */
    public static int majority(int[] nums) {
        Integer candidate = null;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * 阶乘后的零
     */
    public static int inttralingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    /**
     * 数组右移
     * 1. 如果当前的index > 数组长度，那么/ length
     * 2. 记录开始index
     * 3. 记录移动的总数目，如果等于总数量 就结束
     */
    public static void reverseBits(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[(i + k) % n] = nums[i];
        }
        System.arraycopy(result, 0, nums, 0, n);
    }

    /**
     * 动态规划？
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] max = new int[nums.length];

        for (int i = 0; i < max.length; i++) {
            if (i == 0) {
                max[i] = nums[i];
            } else if (i == 1) {
                max[i] = Math.max(nums[i], nums[i - 1]);
            } else {
                max[i] = Math.max(max[i - 1], max[i - 2] + nums[i]);
            }
        }
        System.out.println("max=" + Arrays.toString(max));
        return max[nums.length - 1];
    }

    /**
     * 广度优先遍历，记录每一层的最后一个数
     */
    public static List<Integer> rightSlidView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
//        defSearch(result, root);
        return result;
    }

    public static int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int height = grid[0].length;
        int width = grid.length;
        System.out.println("width=" + width + ",height=" + height);
        int islandCount = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.println("grid[i][j] = " + grid[i][j]);
                if (grid[i][j] == '1') {
                    dfsIslands(grid, i, j);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    /**
     * [["1","1","0","0","0"],
     * ["1","1","0","0","0"],
     * ["0","0","1","0","0"],
     * ["0","0","0","1","1"]]
     */

    private static void dfsIslands(char[][] grid, int x, int y) {
        if (!isVaild(grid, x, y)) {
            return;
        }
        if (grid[x][y] == '2') {
            //已经遍历过了
            return;
        }
        if (grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '2';
        dfsIslands(grid, x - 1, y);
        dfsIslands(grid, x, y - 1);
        dfsIslands(grid, x + 1, y);
        dfsIslands(grid, x, y + 1);
    }

    private static boolean isVaild(char[][] grid, int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        int width = grid.length;
        int height = grid[0].length;
        return x < width && y < height;
    }

    public static boolean isHappy(int n) {
        Set<Integer> list = new HashSet<>();
        int result = n;
        while (result != 1) {
            result = calNum(result);
            if (list.contains(result)) {
                break;
            }
            list.add(result);
        }
        return result == 1;
    }

    private static int calNum(int n) {
        int totalNum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalNum += d * d;
        }

        return totalNum;
    }

    /**
     * 移除链表中指定的数据
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode newNode = null;
        if (head.val != val) {
            newNode = head;
        }
        ListNode lastNode = null;
        while (head != null) {
            if (head.val == val) {
                if (lastNode != null) {
                    lastNode.next = head.next;
                }
            } else {
                if (newNode == null) {
                    newNode = head;
                }
                lastNode = head;
            }
            head = head.next;
        }
        return newNode;
    }

    /**
     * 所有小于n的质数
     */
    public static int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans += isPrime(i);
        }
        return ans;
    }

    private static int isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            System.out.println("i=" + i + ",x=" + x);
            if (x % i == 0) {
                return 0;
            }
        }
        return 1;
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        List<Character> secondList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char first = s.charAt(i);
            char second = t.charAt(i);
            Character oldValue = map.get(first);
            System.out.println("oldValue =" + oldValue + ",first=" + first + ",second=" + second);

            if (oldValue == null) {
                if (secondList.contains(secondList)) {
                    return false;
                }
                secondList.add(second);
                map.put(first, second);
            } else {
                if (oldValue != second) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     */
    static List<List<Integer>> list = new ArrayList<>();

    static int[] visited;
    static boolean valid = true;

    public static boolean canFinish(int numCourse, int[][] prerequisites) {
        if (prerequisites == null) {
            return true;
        }

        for (int[] prerequisite : prerequisites) {
            list.get(prerequisite[1]).add(prerequisite[0]);
        }

        visited = new int[numCourse];
        for (int i = 0; i < numCourse && valid; i++) {
            if (visited[i] == 0) {
                dfsCourse(i);
            }
        }

        return valid;
    }

    private static void dfsCourse(int i) {
        //标记为搜索中
        visited[i] = 1;
        for (int v : list.get(i)) {
            //i 课程所对应的后续所有课程v
            dfsCourse(v);
            if (!valid) {
                return;
            } else if (visited[v] == 1) {
                //如果正在搜索中，说明走了重复路了
                valid = false;
                return;
            }
        }
        //搜索完成
        visited[i] = 2;
    }

    /**
     * 长度最小的连续字数组
     * 滑动数组的概念
     * 几种情况
     * 1. 长度等于1，那么已经最小了，直接返回
     * 2. 如果值>= 目标值
     * a. 如果长度小于之前的长度，更新长度，并且减去尾部的值
     * b. 否则减去尾部的值
     * 3. 如果值 < 目标值
     * a. 增加endIndex，更新targetSize
     */
    public static int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            int value = nums[0];
            if (value >= target) {
                return 1;
            } else {
                return 0;
            }
        }
        int startIndex = 0;
        int endIndex = 1;
        int startV = nums[startIndex];
        int maxLength;
        if (startV >= target) {
            maxLength = 0;
        } else {
            maxLength = -1;
        }
        int curValue = nums[0] + nums[1];
        while (endIndex < nums.length) {
            if (maxLength == 0) {
                return 1;
            }

            if (curValue >= target) {
                if (maxLength == -1) {
                    maxLength = endIndex - startIndex;
                    int startValue = nums[startIndex];
                    curValue = curValue - startValue;
                } else if (endIndex - startIndex <= maxLength) {
                    maxLength = endIndex - startIndex;
                    int startValue = nums[startIndex];
                    curValue = curValue - startValue;
                } else {
                    int startValue = nums[startIndex];
                    curValue = curValue - startValue;
                }
                startIndex++;
            } else {
                //目标值小于target
                endIndex++;
                if (endIndex >= nums.length) {
                    break;
                }
                curValue = curValue + nums[endIndex];
            }
        }
        return maxLength + 1;
    }

    /**
     * 课程表！！
     * 拓扑排序
     * visited2 ;1搜索中、2搜索结束、0还没开始
     */
    static int[] visited2;
    static List<ArrayList<Integer>> data = new ArrayList<>();
    static boolean isValid = true;

    public static int[] findOrder(int numCourse, int[][] prerequisites) {
        visited2 = new int[numCourse];
        for (int[] prerequisite : prerequisites) {
            data.get(prerequisite[1]).add(prerequisite[0]);
        }
        int[] queue = new int[numCourse];

        for (int i = 0; i < numCourse; i++) {
            if (visited2[i] == 0) {
                dfs(i, queue);
            }
        }
        if (!isValid) {
            return new int[0];
        }
        return queue;

    }

    /**
     * 深度搜索，
     * 避免重复搜索
     */
    private static void dfs(int index, int[] queue) {
        visited2[index] = 1;
        List<Integer> courser = data.get(index);
        for (Integer integer : courser) {
            if (visited2[integer] == 0) {
                //搜索附近的节点
                dfs(integer, queue);
                if (!isValid) {
                    return;
                }
            } else if (visited2[integer] == 1) {
                isValid = false;
                return;
            }
        }
        visited2[index] = 2;
        queue[index--] = index;
    }

    /**
     * 单词搜索
     * 单个单词的搜索逻辑是，寻找上下左右没有被使用过的同一个字符，如果不存在，返回false
     * 如果存在，返回true,四维深度搜索
     */
//    public static List<String> findWords(char[][] board, String[] words) {
//
//    }
//
//    private static boolean hasWord(char[][] board, String word) {
//        int x = board[]
//    }

    /**
     * 最大值
     */
    public static int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return nums[0] + nums[1];
        }

        return Math.max(robRange(0, nums.length - 2, nums), robRange(1, nums.length - 2, nums));
    }

    private static int robRange(int start, int end, int[] nums) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
