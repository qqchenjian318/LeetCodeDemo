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
     * 地下城游戏,num174
     * 1. 动态规划
     * 从右下往左上，计算每一步需要的最少点数
     * 当计算到[0][0]，就得出所需的最少点数
     * */
    public static void calculateMinimumHP(int[][] dungeon){

        int startLeft = dungeon.length - 1;
        int startRight = dungeon[0].length - 1;

        int[][] minStep = new int[dungeon.length +1][dungeon[0].length+1];


        for (int i = startLeft; i >= 0; i--) {
            for (int j = startRight; j >= 0 ; j--) {
                int curStep = dungeon[i][j];

                if (startLeft == i && startRight == j){
                    //说明是最后一间房间
                    if (curStep >= 0){
                        minStep[i][j] = 1;
                    }else {
                        minStep[i][j] = Math.abs(curStep) + 1;
                    }
                }else if (startLeft == i){
                    //说明是边边上，那么他的值 = 右侧的框框 - 当前
                    int minValue = minStep[i][j+1] - curStep;
                    if (minValue <=0){
                        minValue = 1;
                    }
                    minStep[i][j] = minValue;
                }else if (startRight == j){
                    int minValue = minStep[i + 1][j] - curStep;
                    if (minValue <= 0){
                        minValue = 1;
                    }
                    minStep[i][j] = minValue;
                }else {
                    int minLast = Math.min(minStep[i+1][j], minStep[i][j +1]);

                    minStep[i][j] = Math.max(minLast - curStep,1);
                }
                System.out.println("("+i+","+j+")= "+minStep[i][j]+","+curStep);
            }
        }
        System.out.print("0,0 = "+minStep[0][0]);
    }
}
