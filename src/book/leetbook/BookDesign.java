package book.leetbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * LeetCode-设计问题
 */
public class BookDesign {

    /**
     * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
     * 打乱后，数组的所有排列应该是等可能的。
     * <p>
     * 实现 Solution class:
     * <p>
     * Solution(int[] nums) 使用整数数组 nums 初始化对象
     * int[] reset() 重设数组到它的初始状态并返回
     * int[] shuffle() 返回数组随机打乱后的结果
     */
    private final int[] originArr;

    public BookDesign(int[] nums) {
        originArr = nums;
    }

    public int[] reset() {
        return originArr;
    }

    public int[] shuffle() {
        int[] a = originArr.clone();
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            int j = i + random.nextInt(a.length - i);
            swap(a, i, j);
        }
        return a;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * <p>
     * 实现 MinStack 类:
     * <p>
     * MinStack() 初始化堆栈对象。
     * void push(int val) 将元素val推入堆栈。
     * void pop() 删除堆栈顶部的元素。
     * int top() 获取堆栈顶部的元素。
     * int getMin() 获取堆栈中的最小元素。
     */
    private ListNode head;

    public void push(int val) {
        if (head == null) {
            head = new ListNode(val, val, null);
        } else {
            int min = getMin();
            min = Math.min(min, val);
            head = new ListNode(val, min, head);
        }
    }

    public void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    public static class ListNode {
        public int val;
        public int min;
        public ListNode next;

        public ListNode(int val, int min, ListNode next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    /**
     * 颠倒给定的 32 位无符号整数的二进制位。
     * <p>
     * 提示：
     * <p>
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
     * 因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。
     * 因此，在 示例 2中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
     */
    public static int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            //res 先往左移一位，把最后一个位置空出来
            res <<= 1;
            res |= n & 1;
            n >>= 1;
        }
        return res;
    }
}
