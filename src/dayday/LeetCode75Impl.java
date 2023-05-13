package dayday;

import java.util.*;

/**
 * Leet code 50 -75题
 */
public class LeetCode75Impl {

    /**
     * 其实还是二分法，因为数组是有序的
     * 唯一的问题就是，折断的地方
     */
    public static boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        while (leftIndex <= (rightIndex - 1)) {
            int midIndex = (leftIndex + rightIndex) / 2;
            int midValue = nums[midIndex];
            int leftValue = nums[leftIndex];
            int rightValue = nums[rightIndex];
            if (midValue == target) {
                return true;
            }
            if (leftValue == target || rightValue == target) {
                return true;
            }
            System.out.println("leftValue=" + leftValue + ",midValue=" + midValue
                    + ",rightValue=" + rightValue + ",l=" + leftIndex + ",r=" + rightIndex + ",m=" + midIndex);
            if (leftValue == midValue && midValue == rightValue) {
                leftIndex++;
                rightIndex--;
            } else if (leftValue < rightValue) {
                if (leftValue <= target && target < midValue) {
                    rightIndex = midIndex - 1;
                } else {
                    leftIndex = midIndex + 1;
                }
            } else {
                if (midValue < target && target <= nums.length - 1) {
                    leftIndex = midIndex + 1;
                } else {
                    rightIndex = midIndex - 1;
                }
            }
        }
        return false;
    }

    /**
     * 生序的链表，删除数字存在重复的节点
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode lastNode = null;
        ListNode curNode = head;
        ListNode lastRightNode = null;
        ListNode nextNode = curNode.next;

        while (curNode != null) {
            //如果当前节点和上一个节点一致，说明是重复节点

            curNode = curNode.next;
            if (curNode != null) {
                nextNode = curNode.next;
            }
        }

        return head;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        ListNode lastNode = null;
        ListNode curNode = head;

        while (curNode != null) {
            if (lastNode == null) {
                lastNode = curNode;
            } else if (lastNode.val != curNode.val) {
                lastNode.next = curNode;
                lastNode = curNode;
            } else {
                lastNode.next = null;
            }
            curNode = curNode.next;
        }

        return head;
    }

    /**
     * 动态规划的思想(错误)
     * 1. 一个柱子的最大值
     * 2. 两个柱子的最大值
     */
    public static int largestRectangleArea(int[] height) {
        //最大的面积
        int maxHeight = 0;
        int lastIndex = 0;
        int lastMinHeight = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] < lastMinHeight) {
                lastMinHeight = height[i];
            }
            int minH = Math.min(lastMinHeight, height[i]);
            //包括本柱的最大区域
            int maxZ = (i - lastIndex) * minH;
            int curZ = height[i];
            if (maxHeight > maxZ) {
                if (maxHeight > curZ) {
                    //不变
                    System.out.println("高度不变哟 i=" + i + ",max=" + maxHeight);
                } else {
                    System.out.println("高度变化 lastMax=" + maxHeight + ",curZ=" + curZ + ",i=" + i);
                    maxHeight = curZ;
                    lastIndex = i;
                    lastMinHeight = curZ;
                }
            } else {
                //
                if (curZ > maxZ) {
                    System.out.println("高度变化 lastMax=" + maxHeight + ",maxZ=" + maxZ + ",i=" + i);

                    lastIndex = i;
                    maxHeight = curZ;
                    lastMinHeight = height[i];
                } else {
                    System.out.println("高度变化 lastMax=" + maxHeight + ",maxZ=" + maxZ + ",i=" + i);
                    maxHeight = maxZ;
                    lastMinHeight = minH;
                }
            }
        }
        return maxHeight;
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode leftNode = null;
        ListNode rightNode = null;
        ListNode curNode = head;
        ListNode firstRight = null;
        ListNode firstLeft = null;
        while (curNode != null) {
            System.out.println("cur=" + curNode.val);
            if (curNode.val < x) {
                if (leftNode != null) {
                    System.out.println("leftNode=" + leftNode.val + ",cur=" + curNode.val);
                    leftNode.next = curNode;
                    leftNode = leftNode.next;
                } else {
                    System.out.println("leftNode=null" + ",cur=" + curNode.val);
                    leftNode = curNode;
                    firstLeft = leftNode;
                }
            } else {
                if (rightNode != null) {
                    System.out.println("rightNode=" + rightNode.val + ",cur=" + curNode.val);
                    rightNode.next = curNode;
                    rightNode = rightNode.next;
                } else {
                    System.out.println("rightNode=null" + ",cur=" + curNode.val);
                    rightNode = curNode;
                    firstRight = rightNode;
                }
            }

            curNode = curNode.next;
        }
        if (rightNode != null) {
            rightNode.next = null;
        }
        System.out.println("left=" + leftNode);
        System.out.println("right=" + firstRight);
        if (leftNode != null) {
            leftNode.next = firstRight;
            return firstLeft;
        } else {
            return firstRight;
        }
    }

    //1. 当前写入的index
    //2. 当前读取的m的index
    //3. 当前读取的n的index，当前写入的n的index
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int m_read_index = 0;
        int n_read_index = 0;
        int[] r = new int[m + n];
        int writeIndex = 0;
        while (writeIndex < (m + n)) {
            int readM = Integer.MAX_VALUE;
            int readN = Integer.MAX_VALUE;
            if (m_read_index < m) {
                readM = nums1[m_read_index];
            }
            if (n_read_index < n) {
                readN = nums2[n_read_index];
            }
            if (readM > readN) {
                n_read_index++;
                r[writeIndex] = readN;
            } else {
                m_read_index++;
                r[writeIndex] = readM;
            }
            writeIndex++;
        }
        System.arraycopy(r, 0, nums1, 0, r.length);
    }

    /**
     * n位格雷序列
     */
    public static List<Integer> grayCode(int n) {
        return null;
    }

    /**
     * 包含重复元素，
     */
    public static int numDecodings(String s) {
        List<List<Character>> ss = new ArrayList<>();
        dfs(s, s.length() - 1, ss, new ArrayList<>());

        return ss.size();
    }

    public static int numDecoding2(String s) {
        int[] nums = new int[s.length() + 1];

        nums[0] = 1;

        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                nums[i] += nums[i - 1];
            }
            //判定前两个
            if (i > 1) {
                char c = s.charAt(i - 2);
                char lastC = s.charAt(i - 1);
                if (c != '0' && ((c - '0') * 10 + (lastC - '0')) <= 26) {
                    nums[i] += nums[i - 2];
                }
            }
        }
        return nums[s.length()];
    }

    public static void dfs(String s, int index, List<List<Character>> result, List<Character> curStr) {
        if (index < 0) {
            System.out.println(" index读取完毕，" + curStr);
            result.add(curStr);
            return;
        }
        char c = s.charAt(index);
        if (index == 0) {
            //那么
            if (c != '0') {
                curStr.add(0, getChar(c + ""));
                System.out.println("添加结果 " + curStr);
                result.add(curStr);
                curStr.remove(0);
            }
        } else {
            //1. 添加一个进去
            if (c != '0') {
                curStr.add(0, getChar(c + ""));
                dfs(s, index - 1, result, curStr);
                curStr.remove(0);
            }


            //2. 添加两个进去
            char lastChar = s.charAt(index - 1);
            if (lastChar != '0') {
                String intValue = lastChar + "" + c;
                System.out.println("两个判定 " + intValue);
                if (Integer.parseInt(intValue) <= 26) {
                    Character aChar = getChar(lastChar + "" + c);
                    curStr.add(0, aChar);
                    dfs(s, index - 2, result, curStr);
                    curStr.remove(0);
                }
            }
        }
    }

    public static Character getChar(String c) {
        int value = Integer.parseInt(c + "");
        return charts[value];
    }

    public static Character[] charts = new Character[]{
            '0',
            'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        //反转从left到right位置的链表
        if (left == right) {
            return head;
        }

        ListNode curNode = head;
        ListNode lastNode = null;
        ListNode startNode = null;
        ListNode endNode = null;

        int index = 1;
        while (curNode != null) {
            if (index == left) {
                startNode = lastNode;

                endNode = curNode;
                lastNode = curNode;
                curNode = curNode.next;
            } else if (index == right) {
                System.out.println(" 最后一个节点了哟 " + curNode.val + ",index=" + index + ",right=" + right);
                //交换要结束了哟
                if (startNode != null) {
                    startNode.next = curNode;
                } else {
                    startNode = curNode;
                }
                ListNode tempNode = curNode.next;
                curNode.next = lastNode;
                if (endNode != null) {
                    endNode.next = tempNode;
                }
            } else if (index > left) {
                System.out.println("交换中 index=" + index + ",left=" + left + ",right=" + right);
                //开始交换了哟
                System.out.println("交换哟 cur=" + curNode.val + ",last=" + (lastNode == null ? 0 : lastNode.val));
                ListNode tempNode = curNode.next;
                curNode.next = lastNode;
                lastNode = curNode;
                curNode = tempNode;

            } else {
                System.out.println("前期跳 " + curNode.val);
                lastNode = curNode;
                curNode = curNode.next;
            }


            index++;
            if (index > right) {
                break;
            }
        }
        if (left == 1) {
            return startNode;
        } else {
            return head;
        }
    }

    /**
     * 中序遍历
     * 所谓的中序遍历指的是左-根-右
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfsTree(root, result);
        return result;
    }

    public static void dfsTree(TreeNode node, List<Integer> result) {
        if (node.left != null) {
            dfsTree(node.left, result);
        }
        result.add(node.val);
        if (node.right != null) {
            dfsTree(node.right, result);
        }
    }

    /**
     * 二叉搜索树
     * 1. 根节点大于左边节点
     * 2. 根节点小于右边节点
     * 是否是有效的二叉搜索树
     * 如果每一个树都成立，那么就是成立的
     */
    public static boolean isValidBST(TreeNode root) {
        return dfsValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean dfsValid(TreeNode root, long lower, long upper) {
        if (root == null) return true;
        if (root.val < lower || root.val > upper) return false;
        return dfsValid(root.left, lower, root.val) && dfsValid(root, root.val, upper);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;

    }

    /**
     * 二叉树是否左右对称
     */
    public static boolean isSymmetric(TreeNode root) {
        return dfsSymmetric(root.left, root.right);
    }

    public static boolean dfsSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && dfsSymmetric(p.left, q.right) && dfsSymmetric(p.right, q.left);
    }


    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        dfsLevel(root, result, 0);

        return result;
    }

    public static void dfsLevel(TreeNode root, List<List<Integer>> result, int level) {
        if (root != null) {
            List<Integer> levelList;
            if (level >= result.size()) {
                levelList = new ArrayList<>();
            } else {
                levelList = result.remove(level);
            }
            levelList.add(root.val);
            result.add(level, levelList);

            dfsLevel(root.left, result, level + 1);
            dfsLevel(root.right, result, level + 1);
        }
    }

    /**
     * 二叉树的最大深度，
     * 当前节点的深度 = max(左子树，右子树)
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsLevel2(root, result, 0);

        List<List<Integer>> xx = new ArrayList<>();
        for (int i = result.size() - 1; i >= 0; i--) {
            List<Integer> integers = result.get(i);
            xx.add(integers);
        }
        return xx;
    }

    public static void dfsLevel2(TreeNode root, List<List<Integer>> result, int level) {
        if (root != null) {
            List<Integer> levelList;
            if (level >= result.size()) {
                levelList = new ArrayList<>();
            } else {
                levelList = result.remove(level);
            }
            levelList.add(root.val);
            result.add(level, levelList);

            dfsLevel2(root.left, result, level + 1);
            dfsLevel2(root.right, result, level + 1);
        }
    }

    /**
     * 因为是有序的，所有，数组中间的数据可以确定为根节点
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    public static TreeNode dfs(int[] nums, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return new TreeNode(nums[leftIndex]);
        }
        if (leftIndex == (rightIndex - 1)) {
            TreeNode root = new TreeNode(nums[leftIndex]);
            root.right = new TreeNode(nums[rightIndex]);
            return root;
        }
        int midIndex = (leftIndex + rightIndex) / 2;
        TreeNode root = new TreeNode(nums[midIndex]);

        TreeNode leftRoot = dfs(nums, leftIndex, midIndex - 1);
        TreeNode rightRoot = dfs(nums, midIndex + 1, rightIndex);
        root.left = leftRoot;
        root.right = rightRoot;
        return root;
    }

    public static boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public static boolean dfsIsBalanced(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }
        int leftDepth = 0;
        if (root.left != null) {
            leftDepth = maxDepth(root.left);
        }
        int rightDepth = 0;
        if (root.right != null) {
            rightDepth = maxDepth(root.right);
        }
        if (Math.abs(rightDepth - leftDepth) <= 1) {
            //
            return dfsIsBalanced(root.left) && dfsIsBalanced(root.right);
        }
        return false;
    }

    public static int height(TreeNode root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);
        int diff = Math.abs(left - right);
        if (diff > 1 || left == -1 || right == -1) {
            //非平衡
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int minDepth = Integer.MAX_VALUE;
        if (root.left != null) {
            minDepth = Math.min(minDepth, minDepth(root.left));
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth, minDepth(root.right));
        }
        return minDepth + 1;
    }


    public static boolean hasPathSum(TreeNode root, int targetSum) {
        return dfsPath(root, targetSum);
    }

    public static boolean dfsPath(TreeNode root, int target) {
        if (root == null) {
            return false;
        }

        if (target == root.val && root.left == null && root.right == null) {
            return true;
        }
        int targetNow = target - root.val;

        boolean leftResult = dfsPath(root.left, targetNow);
        if (leftResult) {
            return true;
        }

        return dfsPath(root.right, targetNow);
    }

    /**
     * 路径总和
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPath(root, targetSum, result, new ArrayList<>());
        return result;
    }

    public static void dfsPath(TreeNode root, int target, List<List<Integer>> result, List<Integer> curPath) {
        if (root == null) {
            return;
        }
        curPath.add(root.val);
        if (target == root.val && root.left == null && root.right == null) {
            result.add(new ArrayList<>(curPath));
            curPath.remove(curPath.size() - 1);
            return;
        }
        int diff = target - root.val;
        dfsPath(root.left, diff, result, curPath);

        dfsPath(root.right, diff, result, curPath);
        curPath.remove(curPath.size() - 1);
    }

    /**
     * 先前序遍历
     * 在交换节点
     */
    public static void flatten(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        firstList(root, nodes);

        for (int i = 1; i < nodes.size(); i++) {
            TreeNode curNode = nodes.get(i);
            TreeNode preNode = nodes.get(i - 1);
            preNode.left = null;
            preNode.right = curNode;
        }
    }

    public static void firstList(TreeNode root, List<TreeNode> nodes) {
        if (root == null) return;
        nodes.add(root);
        firstList(root.left, nodes);
        firstList(root.right, nodes);
    }

    /**
     * 先分层遍历，然后移除设置右节点
     */
    public static TreeNode connect(TreeNode node) {
        List<List<TreeNode>> result = new ArrayList<>();
        nodeList(node, result, 0);

        for (List<TreeNode> treeNodes : result) {
            for (int i = 1; i < treeNodes.size(); i++) {
                TreeNode curNode = treeNodes.get(i);
                TreeNode preNode = treeNodes.get(i - 1);
                preNode.next = curNode;
                curNode.next = null;
            }
        }
        return node;
    }

    public static void nodeList(TreeNode node, List<List<TreeNode>> result, int index) {
        if (node == null) return;
        List<TreeNode> levelList;
        if (index >= result.size()) {
            levelList = new ArrayList<>();
        } else {
            levelList = result.remove(index);
        }
        levelList.add(node);
        result.add(index, levelList);

        nodeList(node.left, result, index + 1);
        nodeList(node.right, result, index + 1);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        levelList.add(1);
        result.add(levelList);

        for (int i = 1; i < numRows; i++) {
            List<Integer> level = result.get(i - 1);
            List<Integer> curLevel = new ArrayList<>();
            curLevel.add(level.get(0));
            for (int j = 1; j < level.size(); j++) {
                Integer curVal = level.get(j);
                Integer preVal = level.get(j - 1);
                curLevel.add(curVal + preVal);
            }

            curLevel.add(level.get(level.size() - 1));
            result.add(i, curLevel);
        }
        return result;
    }

}
