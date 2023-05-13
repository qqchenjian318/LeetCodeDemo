package trainning;

import java.util.*;

/**
 * LeetCode的数据结构专项训练（一）
 */
public class TrainingOne {

    /**
     * 给你一个整数数组 nums 。
     * 如果任一值在数组中出现 至少两次 ，返回 true
     * ；如果数组中每个元素互不相同，返回 false 。
     * <p>
     * 1.传统方式-Hash计算
     */
    public static boolean containsDuplicate(int[] nums) {
        //排序-循环
        Set<Integer> data = new HashSet<>();
        for (int num : nums) {
            if (data.contains(num)) {
                return true;
            } else {
                data.add(num);
            }
        }
        return false;
    }

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 子数组 是数组中的一个连续部分。
     * 理论上遍历一次就行了，重点上是如何表征数据
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int pre = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            pre = Math.max(pre + num, num);
            max = Math.max(pre, max);
        }
        return max;
    }

    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出
     * 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int curValue = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == curValue) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 用Hash算法来加速？
     */
    public static int[] twoSum2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        HashMap<Integer, Integer> data = new HashMap<>();
        while (left <= right) {
            int leftValue = nums[left];
            int rightValue = nums[right];
            if (leftValue + rightValue == target && left != right) {
                //说明对咯
                return new int[]{left, right};
            } else {
                Integer integer = data.get(target - leftValue);
                if (integer != null && integer != left) {
                    return new int[]{left, right};
                }
                data.put(left, leftValue);
                Integer integer1 = data.get(target - rightValue);
                if (integer1 != null && integer1 != right) {
                    return new int[]{right, integer1};
                }
                data.put(right, rightValue);
                left++;
                right--;
            }
        }
        return null;
    }

    /**
     * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * <p>
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，
     * nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int leftIndex = 0;
        int rightIndex = 0;
        int writeIndex = 0;
        int[] newData = new int[m + n];

        while (leftIndex < m || rightIndex < n) {
            if (leftIndex == m) {
                newData[writeIndex] = nums2[rightIndex];
                rightIndex++;
                writeIndex++;
            } else if (rightIndex == n) {
                newData[writeIndex] = nums1[leftIndex];
                leftIndex++;
                writeIndex++;
            } else {
                int leftValue = nums1[leftIndex];
                int rightValue = nums2[rightIndex];
                if (leftValue <= rightValue) {
                    newData[writeIndex] = leftValue;
                    writeIndex++;
                    leftIndex++;
                } else {
                    newData[writeIndex] = rightValue;
                    writeIndex++;
                    rightIndex++;
                }
            }
        }
        if (m + n >= 0) System.arraycopy(newData, 0, nums1, 0, m + n);
    }

    /**
     * 如果正序比较写入，最大的麻烦是会覆盖原有的值
     * 所以，如果逆序写入的话，就不会存在值被覆盖的问题
     * 先从后比较大的写入
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int leftIndex = nums1.length - 1;
        int rightIndex = nums2.length - 1;
        int writeIndex = m + n - 1;
        while (writeIndex >= 0) {
            int leftValue;
            if (leftIndex < 0) {
                leftValue = Integer.MIN_VALUE;
            } else {
                leftValue = nums1[leftIndex];
            }
            int rightValue;
            if (rightIndex < 0) {
                rightValue = Integer.MIN_VALUE;
            } else {
                rightValue = nums2[rightIndex];
            }

            if (leftValue >= rightValue) {
                nums1[writeIndex] = leftValue;
                leftIndex--;
                writeIndex--;
            } else {
                nums2[writeIndex] = rightValue;
                writeIndex--;
                rightIndex--;
            }
        }
    }

    /**
     * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
     * <p>
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * 只能交易一次
     * 动态规划？
     * 如果今天卖出，那么
     */
    public static int maxProfit(int[] prices) {
        //数组1，记录今天之前的最小值
        int max = 0;
        int min = prices[0];
        for (int price : prices) {
            min = Math.min(price, min);
            max = Math.max(max, price - min);
        }
        return max;
    }

    /**
     * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
     * <p>
     * 给你一个由二维数组 mat 表示m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
     * <p>
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     * <p>
     * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     */
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (r * c != m * n) {
            return mat;
        }

        int[][] result = new int[r][c];
        for (int x = 0; x < m * n; ++x) {
            result[x / c][x % c] = mat[x / n][x % n];
        }
        return result;
    }

    /**
     * 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * <p>
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     */
    public static List<List<Integer>> generate(int numRow) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRow; i++) {
            List<Integer> cur = new ArrayList<>();
            if (i == 0) {
                cur.add(1);
            } else if (i == 1) {
                cur.add(1);
                cur.add(1);
            } else {
                List<Integer> integers = result.get(i - 1);
                cur.add(1);
                for (int j = 1; j < integers.size(); j++) {
                    int value = integers.get(j) + integers.get(j - 1);
                    cur.add(value);
                }
                cur.add(1);
            }
            result.add(cur);
        }
        return result;
    }

    /**
     * 是否是有效数独
     * 请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     * <p>
     * 数字1-9在每一行只能出现一次。
     * 数字1-9在每一列只能出现一次。
     * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
     */
    public static boolean isValidSudoku(char[][] board) {
        //判定每一行中是否有重复的数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                if (!checkCur(c, i, j, board)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkCur(char value, int i, int j, char[][] board) {
        //判定行
        for (int k = i + 1; k < 9; k++) {
            char hChar = board[i][k];
            if (hChar == value) {
                return false;
            }
        }
        for (int k = j + 1; k < 9; k++) {
            char ch = board[k][j];
            if (ch == value) {
                return false;
            }
        }
        int x = i / 3 * 3 + 2;
        int y = j / 3 * 3 + 2;
        for (int k = x; k >= i; k--) {
            for (int l = y; l >= y - 2; l--) {
                int curValue = board[k][l];
                if (curValue == value && k != i && l != j) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
     * 请使用 原地 算法。
     */
    public static void setZeroes(int[][] matrix) {
        //额外用一个列表来辅助记录
        int m = matrix.length;
        int n = matrix[0].length;
        //记录 如果=-1，说明已经修改过了
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = matrix[i][j];
                if (value == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
     */
    public static int firstUniqChar(String s) {
        int res = -1;
        for (char i = 'a'; i <= 'z'; i++) {
            int c = s.indexOf(i);
            if (c == -1) {
                continue;
            }
            int lastIndex = s.lastIndexOf(i);
            if (c == lastIndex) {
                res = (res == -1 || res > lastIndex) ? lastIndex : res;
            }
        }

        return -1;
    }

    /**
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * <p>
     * 如果可以，返回 true ；否则返回 false 。
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] record = new int[26];

        for (int i = 0; i < ransomNote.length(); i++) {
            char charAt = ransomNote.charAt(i);
            record[charAt - 'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            record[c - 'a']--;
            if (record[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] record = new int[26];
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        for (char c : chars1) {
            record[c - 'a']++;
        }

        for (char c : chars2) {
            record[c - 'a']--;
        }

        for (int i : record) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

}
