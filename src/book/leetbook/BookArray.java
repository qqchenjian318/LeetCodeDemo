package book.leetbook;

import java.util.*;

/**
 * LeetBook-数组
 */
public class BookArray {

    /**
     * 第一题
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，
     * 使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     * <p>
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。
     * 更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
     * <p>
     * 将最终结果插入 nums 的前 k 个位置后返回 k 。
     * <p>
     * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    public static int one(int[] nums) {
        int lastIndex = 0;
        int lastValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curValue = nums[i];
            if (curValue != lastValue) {
                //不写入
                lastIndex++;
                lastValue = curValue;
                nums[lastIndex] = curValue;
            }
        }
        return lastIndex + 1;
    }

    /**
     * 第二题：
     * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
     * <p>
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。
     * 你也可以先购买，然后在 同一天 出售。
     * <p>
     * 返回 你能获得的 最大 利润。
     * 1. 普通方式
     * 先找到第一个有上升趋势，并且买入，然后再第一次出现下降趋势的时候卖出
     */
    public static int two(int[] prices) {
        int max = 0;
        int buyPrice = -1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                //第一次出现上升
                if (buyPrice == -1) {
                    buyPrice = prices[i - 1];
                }
            } else if (prices[i] < prices[i - 1]) {
                //说明出现了下降
                if (buyPrice != -1) {
                    max += prices[i - 1] - buyPrice;
                    buyPrice = -1;
                }
            }
        }
        if (buyPrice != -1) {
            //说明到最终都没出现下降的场景
            max += prices[prices.length - 1] - buyPrice;
        }
        return max;
    }

    /**
     * 2. 草，原来思路这么简单，只需要买入涨的日子,就行
     */
    public static int two2(int[] prices) {
        int max = 0;

        for (int i = 1; i < prices.length; i++) {
            int value = prices[i] - prices[i - 1];
            if (value > 0) {
                //记录所有开始涨的钱
                max += value;
            }
        }
        return max;
    }

    /**
     * 第三题：
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 1. 先全部轮转一遍
     * 2. 再轮转前面的0,k-1个
     * 3. 再轮转k,length个
     */
    public static void three(int[] nums, int k) {
        //轮转的意思是，
        //第i个位置的元素，他的新位置是(i+k) % l
        reverseNum(nums, 0, nums.length - 1);
        reverseNum(nums, 0, k - 1);
        reverseNum(nums, k, nums.length - 1);
    }

    private static void reverseNum(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }

    /**
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true；
     * 如果数组中每个元素互不相同，返回 false 。
     * <p>
     * 1. 方法虽然慢，但是有效
     * 有没有更快的方式呢
     */
    public static boolean four(int[] nums) {
        HashSet<Integer> data = new HashSet<>();
        for (int value : nums) {
            if (data.contains(value)) {
                return true;
            } else {
                data.add(value);
            }
        }
        return false;
    }

    /**
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     */
    public static int five(int[] nums) {
        int n = nums[0];
        for (int i = 1; i < nums.length; i++) {
            n ^= nums[i];
        }
        return n;
    }

    /**
     * 给你两个整数数组nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）
     * 。可以不考虑输出结果的顺序。
     * 1. 有点慢
     * 两个数组的排序，并O(m+n)
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 1000
     */
    public static int[] six(int[] nums1, int[] nums2) {
        List<Integer> data = new ArrayList<>();

        int leftIndex = 0;
        int rightIndex = 0;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        while (leftIndex < nums1.length && rightIndex < nums2.length) {
            int leftValue = nums1[leftIndex];
            int rightValue = nums2[rightIndex];

            if (leftValue == rightValue) {
                data.add(leftValue);
                leftIndex++;
                rightIndex++;
            } else if (leftValue < rightValue) {
                leftIndex++;
            } else {
                rightIndex++;
            }
        }
        int[] nums = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            nums[i] = data.get(i);
        }
        return nums;
    }

    /**
     * 两个大小是限定的，
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        int[] record = new int[1001];
        for (int j : nums1) {
            record[j]++;
        }
        //record先记录了在1数组中出现的数据及其数量
        int[] ans = new int[nums2.length];
        int index = 0;
        for (int i = 0; i < nums2.length; i++) {
            //遍历2数组中的数据
            int value = nums2[i];
            if (record[value] > 0) {
                ans[index] = value;
                record[value]--;
            }
        }
        return Arrays.copyOf(ans, index);
    }

    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    public static int[] seven(int[] digits) {
        //倒序遍历，判定是否要进位，如果要进位,
        int plus = 1;
        int[] result = new int[digits.length + 1];

        for (int i = digits.length - 1; i >= 0; i--) {
            int num = digits[i] + plus;
            if (num == 10) {
                result[i + 1] = 0;
                plus = 1;
            } else {
                result[i + 1] = num;
                plus = 0;
            }
        }
        if (plus == 1) {
            result[0] = 1;
        } else {
            return Arrays.copyOfRange(result, 1, result.length);
        }

        return result;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public static void eight(int[] nums) {
        int startIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            //如果是0
            if (value == 0) {
                if (startIndex == -1) {
                    startIndex = i;
                }
            } else {
                if (startIndex != -1) {
                    //说明前面没有0，不操作
                    nums[startIndex] = value;
                    nums[i] = 0;
                    if (startIndex < i) {
                        startIndex++;
                    } else {
                        startIndex = -1;
                    }
                }
            }
        }
    }

    /**
     * 双指针
     */
    public static void eight2(int[] nums) {
        int n = nums.length;
        int left = 0;//指向非0
        int right = 0;//指向非0
        while (right < n) {
            if (nums[right] != 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
            right++;
        }
    }

    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，
     * 并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     */
    public static int[] twoSum(int[] nums, int target) {
        //双重for循环O(n~2)
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int tar = target - num;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == tar) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int curValue = nums[i];
            Integer integer = data.get(curValue);
            if (integer == null) {
                int x = target - nums[i];
                data.put(x, i);
            } else {
                return new int[]{i, integer};
            }
        }
        return null;
    }

    /**
     * 请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     * <p>
     * 数字1-9在每一行只能出现一次。
     * 数字1-9在每一列只能出现一次。
     * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
     */
    public static boolean isValidSudoku(char[][] board) {
        int x = board.length;
        int y = board[0].length;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (!checkValid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 先检查横
     * 再检查纵
     * 最后检查框
     */
    private static boolean checkValid(char[][] board, int x, int y) {
        char checkChar = board[x][y];
        if (checkChar == '.') {
            return true;
        }
        for (int i = x + 1; i < 9; i++) {
            char c = board[i][y];
            if (c == checkChar) {
                return false;
            }
        }
        for (int i = y + 1; i < 9; i++) {
            char c = board[x][i];
            if (c == checkChar) {
                return false;
            }
        }
        //先定位范围
        int bX = x / 3 * 3;//1-0,4-3,5-3,6-6
        int bY = y / 3 * 3;
        for (int i = bX; i < bX + 3; i++) {
            for (int j = bY; j < bY + 3; j++) {
                if (i == x && j == y) {
                    continue;
                }
                char c = board[i][j];
                if (c == checkChar) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * <p>
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        //先上下交换
        for (int i = 0; i < n / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[n - i - 1];
            matrix[n - i - 1] = temp;
        }
        //再按照对角线交换
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
