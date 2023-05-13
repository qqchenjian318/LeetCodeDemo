package book.leetbook;

import dayday.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetBook-树
 */
public class BookTree {

    /**
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public static int maxDepth(TreeNode root) {
        //遍历
        List<Integer> maxDepth = new ArrayList<>();
        dfsTree(root, maxDepth, 0);
        int max = 0;
        for (Integer integer : maxDepth) {
            max = Math.max(integer, max);
        }
        return max;
    }

    private static void dfsTree(TreeNode root, List<Integer> depthList, int cur) {
        if (root == null) {
            return;
        }
        cur++;
        if (root.left == null && root.right == null) {
            depthList.add(cur);
            return;
        }
        dfsTree(root.left, depthList, cur);
        dfsTree(root.right, depthList, cur);
    }

    public static int maxDepthNew(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepthNew(root.left), maxDepthNew(root.right)) + 1;
    }

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 有效 二叉搜索树定义如下：
     * <p>
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTRange(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBSTRange(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val > max) {
            return false;
        }
        return isValidBSTRange(root.left, min, root.val) && isValidBSTRange(root.right, root.val, max);
    }

    /**
     * 给你一个二叉树的根节root ， 检查它是否轴对称。
     * <p>
     * 添加进stack
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfsSymmetric(root.left, root.right);
    }

    public static boolean dfsSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return dfsSymmetric(left.left, right.right) && dfsSymmetric(left.right, right.left);
    }

    /**
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsOrder(root, result, 0);
        return result;
    }

    public static void dfsOrder(TreeNode root, List<List<Integer>> result, int level) {
        if (root == null) return;
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        dfsOrder(root.left, result, level + 1);
        dfsOrder(root.right, result, level + 1);
    }

    /**
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * <p>
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * <p>
     * 左边的高度，右边的高度，如果添加进右边，则
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortArray(nums, 0, nums.length - 1);
    }

    public static TreeNode sortArray(int[] nums, int startIndex, int endIndex) {
        TreeNode node = new TreeNode();
        if (startIndex == endIndex) {
            node.val = nums[startIndex];
            return node;
        }
        if (startIndex + 1 == endIndex) {
            node.val = nums[endIndex];
            TreeNode leftNode = new TreeNode();
            leftNode.val = nums[startIndex];
            node.left = leftNode;
            return node;
        }
        int mid = (startIndex + endIndex) / 2;
        node.val = nums[mid];
        node.left = sortArray(nums, startIndex, mid - 1);
        node.right = sortArray(nums, mid + 1, endIndex);
        return node;
    }
}
