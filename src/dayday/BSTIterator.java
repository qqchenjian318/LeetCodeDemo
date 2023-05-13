package dayday;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉搜索树 迭代器
 */
public class BSTIterator {

    private TreeNode cur;
    private final Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new LinkedList<>();
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int ret = cur.val;
        cur = cur.right;
        return ret;
    }

    public boolean hasNext(){
        return cur != null || !stack.isEmpty();
    }
}
