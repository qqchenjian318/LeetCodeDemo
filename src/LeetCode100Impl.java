import java.util.*;

/**
 * leet code 75 - 100
 */
public class LeetCode100Impl {


    public static List<Integer> minimumTotal(int rowIndex) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);
        if (rowIndex == 0) {
            return first;
        }
        List<Integer> lastList = first;
        for (int i = 1; i <= rowIndex; i++) {

            List<Integer> curLevel = new ArrayList<>();
            curLevel.add(lastList.get(0));

            for (int j = 1; j < lastList.size(); j++) {
                Integer preValue = lastList.get(j - 1);
                Integer curValue = lastList.get(j);
                curLevel.add(preValue + curValue);
            }
            curLevel.add(lastList.get(lastList.size() - 1));
            lastList = curLevel;
        }
        return lastList;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        //存储达到当前节点的最短路径
        int size = triangle.size();
        int[][] newPath = new int[size][size];

        newPath[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < (i + 1); j++) {
                List<Integer> integers = triangle.get(i);
                Integer curValue = integers.get(j);
                if (j == 0) {
                    newPath[i][j] = newPath[i - 1][j] + curValue;
                } else if (j == i) {
                    newPath[i][j] = newPath[i - 1][j - 1] + curValue;
                } else {
                    newPath[i][j] = Math.min(newPath[i - 1][j], newPath[i - 1][j - 1]) + curValue;
                }
            }
        }
        int[] lastLevel = newPath[size - 1];
        int minPath = 0;
        for (int i = 0; i < lastLevel.length; i++) {
            if (i == 0) {
                minPath = lastLevel[i];
            } else {
                minPath = Math.min(minPath, lastLevel[i]);
            }
        }

        return minPath;
    }

    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int minValue = prices[0];
        int maxValue = 0;

        for (int i = 1; i < prices.length; i++) {
            int curValue = prices[i];
            minValue = Math.min(curValue, minValue);
            int value = curValue - minValue;
            maxValue = Math.max(maxValue, value);
        }

        return maxValue;
    }

    public static int maxProfit2(int[] prices) {
        if (prices.length <= 1) return 0;
        int result = 0;
        int lastValue = prices[0];
        int minValue = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int curValue = prices[i];
            if (curValue < lastValue) {
                result += (lastValue - minValue);
                minValue = curValue;
            }
            lastValue = curValue;
        }
        if (lastValue > minValue) {
            result += (lastValue - minValue);
        }
        return result;
    }

    public static int maxProfit3(int[] prices) {
        if (prices.length <= 1) return 0;
        int firstValue = 0;
        int secondValue = 0;
        int lastValue = prices[0];
        int minValue = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int curValue = prices[i];
            if (curValue < lastValue) {
                int cur = lastValue - minValue;
                if (firstValue > secondValue) {
                    secondValue = Math.max(cur, secondValue);
                } else {
                    firstValue = Math.max(cur, firstValue);
                }
                minValue = curValue;
            }
            lastValue = curValue;
        }
        if (lastValue > minValue) {
            int cur = lastValue - minValue;
            if (firstValue > secondValue) {
                secondValue = Math.max(cur, secondValue);
            } else {
                firstValue = Math.max(cur, firstValue);
            }
        }
        return firstValue + secondValue;
    }

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase(Locale.ROOT).trim();
        if (s.length() == 0) {
            return true;
        }
        int leftIndex = 0;
        int rightIndex = s.length() - 1;
        boolean isTrue = true;
        while (leftIndex <= (rightIndex - 1)) {
            char leftChar = s.charAt(leftIndex);
            char rightChar = s.charAt(rightIndex);
            System.out.println("left=" + leftChar + ",right=" + rightChar + ",[" + leftIndex + "," + rightIndex + "]");
            if (leftChar == rightChar) {
                leftIndex++;
                rightIndex--;
            } else if (leftChar == ' ' || !isValid(leftChar)) {
                leftIndex++;
            } else if (rightChar == ' ' || !isValid(rightChar)) {
                rightIndex--;
            } else {
                isTrue = false;
                break;
            }
        }
        if (s.charAt(leftIndex) != s.charAt(rightIndex)) {
            return false;
        }

        return isTrue;
    }

    private static boolean isValid(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return c >= 'a' && c <= 'z';
    }

    /**
     * 采用hash表匹配
     */
    public static int longestConsecutive(int[] nums) {

        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int longest = 0;
        for (int num : nums) {
            if (!hashSet.contains(num - 1)) {
                //如果不包括，那么以该数位起点
                //说明可以进入下层遍历
                int curLongest = 1;
                int curNum = num;
                while (hashSet.contains(curNum + 1)) {
                    curNum += 1;
                    curLongest += 1;
                }
                longest = Math.max(curLongest, longest);
            }
        }
        return longest;
    }

    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int preNum) {
        if (root == null) {
            return 0;
        }
        int sum = preNum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    /**
     * 遍历一遍所有的边缘，然后标记所有的位置
     * 然后遍历数组内部将所有的O 更换为O
     * 1. 从边缘开始找O，并且将所有与其相连的O标记为不可修改
     */
    public static void solve(char[][] board) {

        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            dfsChange(board, i, 0, n, m);
            dfsChange(board, i, m - 1, n, m);
        }
        for (int i = 1; i < m - 1; i++) {
            dfsChange(board, 0, i, n, m);
            dfsChange(board, n - 1, i, n, m);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void dfsChange(char[][] board, int x, int y, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfsChange(board, x + 1, y, n, m);
        dfsChange(board, x - 1, y, n, m);
        dfsChange(board, x, y + 1, n, m);
        dfsChange(board, x, y - 1, n, m);

    }

    /**
     * 加油站
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] diff = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            diff[i] = gas[i] - cost[i];
        }
        int index = -1;
        for (int i = 0; i < diff.length; i++) {
            System.out.println("从 i=" + i + " 出发");
            boolean isTure = checkIndex(diff, i);
            if (isTure) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static boolean checkIndex(int[] diff, int i) {
        if (diff[i] < 0) {
            System.out.println("不可以从这出发 i=" + i + ",value=" + diff[i]);
            return false;
        }
        //从i开始，再回到i
        int curValue = diff[i];
        int curIndex = i + 1;
        boolean isOk = true;
        while (curIndex != i) {
            if (curIndex == diff.length) {
                curIndex = 0;
            }
            curValue += diff[curIndex];
            System.out.println(" 遍历 curIndex=" + curIndex + ",i=" + i + ",curValue=" + curValue);
            if (curValue < 0) {
                isOk = false;
                break;
            }
            if (curValue == 0) {
                int nextIndex = curIndex + 1;
                if (nextIndex == diff.length && i == 0) {
                    break;
                }
                if (nextIndex != i) {
                    isOk = false;
                }
                break;
            }
            curIndex++;
            if (curIndex >= diff.length) {
                curIndex = 0;
            }
        }

        return isOk;
    }

    /**
     * 分糖果
     * 遍历找到评分最低的小孩，并且给一个糖果
     * 然后分别向左向右分发糖果
     * 分发的规则是，尽量少给
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            //先往左侧遍历，如果当前比左侧大，那么当前等于左侧+1
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        //再从右往左遍历,如果当前分数大于后一个分数，那么在当前分发糖果的基础上+1，
        //左右两次遍历中取最大值，就是同时满足左右的值
        int right = 0, result = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < (n - 1) && ratings[i] > ratings[i + 1]) {
                //当前比后一个分数高
                right++;
            } else {
                right = 1;
            }
            result += Math.max(right, left[i]);
        }
        return result;
    }

    public static int singleNumber(int[] nums) {
        int totalValue = 1;
        for (int num : nums) {
            totalValue |= num;
        }
        return totalValue;
    }

    public static int singleNumberNew(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int value : nums) {
            Integer integer = hashMap.get(value);
            if (integer == null) {
                hashMap.put(value, 1);
            } else {
                integer++;
                hashMap.put(value, integer);
            }
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            System.out.println("entry=" + entry);
            if (entry.getValue() == 1) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }

    public static Node copyRandomList(Node head) {
        HashMap<Node, Node> cacheNode = new HashMap<>();
        return copyOrGet(head, cacheNode);
    }

    public static Node copyOrGet(Node root, HashMap<Node, Node> cacheNode) {
        if (root == null) {
            return null;
        }
        if (cacheNode.containsKey(root)) {
            //说明已经包含了
            return cacheNode.get(root);
        } else {
            Node node = new Node(root.val);
            cacheNode.put(root, node);
            node.next = copyOrGet(root.next, cacheNode);
            node.random = copyOrGet(root.random, cacheNode);
            return node;
        }
    }

    public static ListNode hasCycle(ListNode head) {
        HashSet<ListNode> cacheNode = new HashSet<>();

        while (head != null) {
            if (!cacheNode.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    public static boolean dfsNode(ListNode root, HashSet<ListNode> cacheNode) {
        if (root == null) {
            return false;
        }
        if (cacheNode.contains(root)) {
            return true;
        }
        cacheNode.add(root);
        return dfsNode(root.next, cacheNode);
    }

    public static boolean hsCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void reorderList(ListNode head) {
        List<ListNode> data = new ArrayList<>();
        while (head != null) {
            data.add(head);
            head = head.next;
        }
        int start = 0;
        int end = data.size() - 1;
        ListNode lastNode = null;
        while (true) {
            ListNode startNode = data.get(start);
            ListNode endNode = data.get(end);
            if (lastNode == null) {
                lastNode = startNode;
                lastNode.next = endNode;
            } else {
                lastNode.next = startNode;
                startNode.next = endNode;
            }
            lastNode = endNode;
            start++;
            end--;
            if (lastNode == null) {
                break;
            }
            if (start > end) {
                lastNode.next = null;
                break;
            }
            if (start == end) {
                ListNode listNode = data.get(start);
                lastNode.next = listNode;
                listNode.next = null;
            }
        }
    }

    public static void reorderListNew(ListNode head) {
        List<ListNode> data = new ArrayList<>();
        while (head != null) {
            data.add(head);
            head = head.next;
        }
        int start = 0;
        int end = data.size() - 1;
        while (start < end) {
            data.get(start).next = data.get(end);
            start++;
            if (start == end) {
                break;
            }
            data.get(end).next = data.get(start);
            end--;
        }
        data.get(end).next = null;
    }

    /**
     * 前序遍历
     * 根、左、右
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> data = new ArrayList<>();
        dfsPreorder(root, data);
        return data;
    }

    public static void dfsPreorder(TreeNode node, List<Integer> data) {
        if (node == null) return;
        data.add(node.val);
        dfsPreorder(node.left, data);
        dfsPreorder(node.right, data);
    }

    public static void dfsPostorder(TreeNode node, List<Integer> data) {
        if (node == null) return;
        dfsPostorder(node.left, data);
        dfsPostorder(node.right, data);
        data.add(node.val);
    }

    public static ListNode insertionSortList(ListNode head) {
        ListNode startNode = new ListNode();
        while (head != null) {
            ListNode templeNode = head.next;
            if (startNode.next == null) {
                startNode.next = head;
                head.next = null;
            } else {
                insertToList(startNode, head);
            }

            head = templeNode;
        }
        return startNode.next;
    }

    private static void insertToList(ListNode startNode, ListNode insertNode) {
        ListNode curNode = startNode.next;
        System.out.println(" insert " + insertNode.val + ",curNode=" + curNode);

        while (true) {
            if (insertNode.val > curNode.val) {
                if (curNode.next == null) {
                    curNode.next = insertNode;
                    insertNode.next = null;
                    break;
                } else {
                    if (insertNode.val <= curNode.next.val) {
                        ListNode temple = curNode.next;
                        curNode.next = insertNode;
                        insertNode.next = temple;
                        break;
                    } else {
                        curNode = curNode.next;
                    }
                }
            } else {
                //
                startNode.next = insertNode;
                insertNode.next = curNode;
                break;
            }
        }
    }

    public static ListNode insert(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode startNode = new ListNode();
        startNode.next = head;
        ListNode lastSorted = head;
        ListNode curNode = head.next;
        while (curNode != null) {
            if (lastSorted.val <= curNode.val) {
                lastSorted = lastSorted.next;
            } else {
                //遍历已有的列表
                ListNode preNode = startNode;
                while (preNode.next.val <= curNode.val) {
                    preNode = preNode.next;
                }
                lastSorted.next = curNode.next;
                curNode.next = preNode.next;
                preNode.next = curNode;

            }
            curNode = lastSorted.next;
        }

        return startNode.next;
    }

    public static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        //找到中间的节点 然后断开，分别继续排序左右
        ListNode fast = head, slow = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return merge(list1, list2);
    }

    private static ListNode merge(ListNode list1, ListNode list2) {
        ListNode startNode = new ListNode();
        ListNode temp = startNode, temple1 = list1, temple2 = list2;
        while (temple1 != null && temple2 != null) {
            if (temple1.val <= temple2.val) {
                temp.next = temple1;
                temple1 = temple1.next;
            } else {
                temp.next = temple2;
                temple2 = temple2.next;
            }
            temp = temp.next;
        }
        if (temple1 != null) {
            temp.next = temple1;
        } else if (temple2 != null) {
            temp.next = temple2;
        }

        return startNode.next;
    }

    public static String reverseWords(String s) {
        if (s.length() == 0) {
            return s;
        }
        String trim = s.trim();
        String[] s1 = trim.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length - 1; i >= 0; i--) {
            String cur = s1[i];
            if (cur.length() != 0) {
                sb.append(cur).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static int findMin(int[] nums) {
        int minValue = Integer.MAX_VALUE;
        for (int num : nums) {
            minValue = Math.min(num, minValue);
        }
        return minValue;
    }
}
