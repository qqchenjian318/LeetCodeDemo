package dayday;

import dayday.TreeNode;

/**
 * 二叉树的序列化
 */
public class Codec {

    /**
     * 中序遍历
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        dfs(root, sb);
        return sb.substring(0, sb.length()) + "]";
    }

    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        String result = data.substring(1, data.length() - 1);
        if (result.length() == 0) {
            return null;
        }
        String[] split = result.split(",");
        return addToTree(split);
    }

    private TreeNode addToTree(String[] split) {
        TreeNode root = new TreeNode();
        root.val = Integer.parseInt(split[0]);

        for (int i = 1; i < split.length; i++) {
            TreeNode cur = new TreeNode();
            cur.val = Integer.parseInt(split[i]);
            addToTree(root, cur);
        }
        return root;
    }

    private void addToTree(TreeNode root, TreeNode cur) {
        if (cur.val > root.val) {
            if (root.right == null) {
                root.right = cur;
            } else {
                addToTree(root.right, cur);
            }
        } else {
            if (root.left == null) {
                root.left = cur;
            } else {
                addToTree(root.left, cur);
            }
        }
    }
}
