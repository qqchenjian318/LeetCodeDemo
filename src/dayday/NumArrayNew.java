package dayday;

import java.util.ArrayList;
import java.util.List;

public class NumArrayNew {
    private int[] nums;
    private int[] sum;

    public NumArrayNew(int[] nums) {
        this.nums = nums;
        sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    //1,3
    //-2
    public void update(int index, int val) {
        nums[index] = val;

        for (int i = index; i < nums.length; i++) {
            if (i == 0) {
                continue;
            }
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return sum[right] - sum[left] + nums[left];
    }
    //9,-8
    //3,-8
    //-8-11

    /**
     * 包含冷冻期的股票买卖
     * 最大利润
     * 如果卖掉，则第二天不能买卖
     * 动态规划
     * 当前天的状态
     * a. 持有一支股票，说明：要么是昨天买的，要么昨天已经有了
     * b. 不持有任何股票，并且处于冷冻期，说明：今天刚卖的，收益为昨天持有收益 + 今天股票出售的价格
     * c. 不持有任何股票，并且不处于冷冻期，说明：今天没进行买卖，那么今天结束的收益就是昨天刚卖或昨天也没有的最大值
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] max = new int[prices.length][3];
        //如果第0天存在股票，说明是第-1天，买入的，所以收益为-prices[0]
        max[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            //如果第i天持有一支股票，那么说明第i-1天，要么持有一支股票，要么不持有任何股票并且，不处于冷冻期
            max[i][0] = Math.max(max[i - 1][0], max[i - 1][2] - prices[i]);

            max[i][1] = max[i - 1][0] + prices[i];
            max[i][2] = Math.max(max[i - 1][2], max[i - 1][1]);
        }
        return Math.max(max[n - 1][1], max[n - 1][2]);
    }

    /**
     * 戳气球
     * max[i] = x +
     * //暴力解法
     */
    public static int maxCoins(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            int leftIndex = i - 1;
            int rightIndex = i + 1;

            int leftValue;
            if (leftIndex < 0) {
                leftValue = 1;
            } else {
                leftValue = nums[leftIndex];
            }

            int rightValue;
            if (rightIndex >= nums.length) {
                rightValue = 1;
            } else {
                rightValue = nums[rightIndex];
            }

            int curValue = nums[i];
            total += leftValue * curValue * rightValue;
        }
        return total;
    }

    /**
     * 超级丑数
     */
    public static int nthSuperUglyNumber(int n, int[] primes) {
        return 0;
    }

    /**
     * 动态规划
     * 从右边开始
     * [5,2,6,1]
     * [3,-4,5,1]
     * [,1,0]
     * 暴力解法：问题，会超时
     * 原因：每次都遍历对比右侧的大小
     * 1. 优化1，
     */
    public static List<Integer> countSmaller(int[] nums) {
        int[] max = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                max[i] = 0;
            } else {
                int curValue = nums[i];
                int rightValue = nums[i+1];
                if (curValue > rightValue){
                    max[i] = max[i+1] + 1;
                }else {
                    int count = 0;
                    for (int j = i; j < nums.length; j++) {
                        int newValue = nums[j];
                        if (curValue > newValue){
                            count++;
                        }
                    }
                    max[i] = count;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i : max) {
            result.add(i);
        }
        return result;
    }
}
