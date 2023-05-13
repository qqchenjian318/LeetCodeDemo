package book.leetbook;

/**
 * LeetBook-动态规划
 */
public class BookDynamic {

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] result = new int[n];
        result[0] = 1;
        result[1] = 2;
        for (int i = 2; i < n; i++) {
            //当前这层等于 n-1 n-2
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n - 1];
    }

    /**
     * 给定一个数组 prices ，它的第i个元素prices[i] 表示一支给定股票第 i 天的价格。
     * <p>
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * <p>
     * 不能买卖多次？
     */
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int max = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                int now = prices[i] - min;
                max = Math.max(max, now);
            }
        }
        return max;
    }

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 子数组 是数组中的一个连续部分。
     */
    public static int maxSubArray(int[] nums) {
        //动态规划
        int pre = nums[0];//上一个值
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            pre = Math.max(pre + value, value);
            max = Math.max(max, pre);
        }
        return max;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
     */
    public static int rob(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int[] max = new int[nums.length];
        max[0] = nums[0];
        max[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int value = nums[i];
            max[i] = Math.max(max[i - 2] + value, max[i - 1]);
        }
        return max[nums.length - 1];
    }
}
