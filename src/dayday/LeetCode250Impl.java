package dayday;

import java.util.ArrayList;
import java.util.List;

/**
 * 200~250题
 */
public class LeetCode250Impl {

    public static int maxSumSubmatrix(int[][] matrix, int k) {
        return 0;
    }

    /**
     * 水壶问题
     * 最少能装出水的最小值出来的
     * 每次的操作只有，
     * 1. 往空的里面装水
     * 2.
     */
    public static boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (jug1Capacity == targetCapacity || jug2Capacity == targetCapacity) {
            return true;
        }
        if (jug1Capacity + jug2Capacity < targetCapacity) {
            return false;
        }
        if (jug1Capacity == 0 || jug2Capacity == 0) {
            return false;
        }

        return targetCapacity % gcp(jug1Capacity, jug2Capacity) == 0;
    }

    public static int gcp(int x, int y) {
        //求余数
        int re = x % y;
        while (re != 0) {
            x = y;
            y = re;
            re = x % y;
        }
        return y;
    }


    /**
     * 4/2 = 2
     * 5/2 = 2.5
     * 6/2 = 3
     */
    public static boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }

        long x = 1, square = 1;
        while (square <= num) {
            if (square == num) {
                return true;
            }
            ++x;
            square = x * x;
        }
        return false;
    }


    /**
     * 求和-不能用+、-法
     * 那么只能用位运算了
     * 位运算：
     * 只有
     * 1&1 = 1，1&0 = 0，0&0 = 1
     * 1 ｜ 0 = 1，0 ｜ 0 = 0，1｜1 = 1
     * 101
     * 110
     * & 011
     * 1000
     */
    public static int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static List<List<Integer>> kSP(int[] nums1, int[] nums2, int k) {
        int leftIndex = 0;
        int rightIndex = 0;

        List<List<Integer>> result = new ArrayList<>();

        while (leftIndex < nums1.length || rightIndex < nums2.length) {
            int leftValue = nums1[leftIndex];
            int righValue = nums2[rightIndex];
            List<Integer> curList = new ArrayList<>();
            curList.add(leftValue);
            curList.add(righValue);
            result.add(curList);
            if (result.size() == k) {
                break;
            }
            //下一个数
            int leftNext = leftIndex + 1;
            int rightNext = rightIndex + 1;
            if (leftNext == nums1.length && rightNext == nums2.length) {
                break;
            }
            if (leftNext == nums1.length) {
                rightIndex++;
            } else if (rightNext == nums2.length) {
                leftIndex++;
            } else {
                int leftNextValue = nums1[leftNext];
                int rightNextValue = nums2[rightNext];
                if (leftValue + rightNextValue < righValue + leftNextValue) {
                    leftIndex++;
                } else {
                    rightIndex++;
                }
            }
        }
        return result;
    }


    public static int guessNumber(int n) {
        int left = 1;
        int right = n;
        int leftCur = guess(left);
        if (leftCur == 0) {
            return left;
        }
        int rightCur = guess(right);
        if (rightCur == 0) {
            return right;
        }
        while (left < right) {
            int mid = (left + right) / 2;
            int cur = guess(mid);
            if (cur == 0) {
                return mid;
            } else if (cur == -1) {
                right = mid - 1;
            } else if (cur == 1) {
                left = mid + 1;
            }
        }
        return 0;
    }

    private static int guess(int n) {
        return 1;
    }

    public static int wiggleMaxLength(int nums) {
        return 0;
    }

    /**
     * dfs
     * 这是方式会导致栈太深，出发内存移除
     */
    public static int combinationSum4(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            dfs(nums[i], nums, target, result);
        }
        return result.size();
    }

    private static void dfs(int cur, int[] nums, int target, List<Integer> result) {
        if (cur == target) {
            result.add(1);
            return;
        }
        if (cur > target) {
            return;
        }
        int v = target - cur;
        for (int i = 0; i < nums.length; i++) {
            dfs(nums[i], nums, v, result);
        }
    }

    /**
     * 动态规划
     * [1,2,3] 4
     * max[0] = 1
     * max[1]
     *  max[1] = 0 + max[1-1] = 1
     *
     * max[2]
     *  max[2] = 0 + max[2-1] =1
     *  max[2] = 1 + max[0] = 2
     *
     * max[3]
     *  max[3] = 0 + max[3-1] = 2
     *  max[3] = 2 + max[3-2] = 3
     *  max[3] = 3 + max[3-3] = 4
     * */
    public static int combinationSum4New(int[] nums, int target) {
        int[] max = new int[target + 1];
        max[0] = 1;
        for (int i = 1; i < target + 1; i++) {
            for (int num : nums) {
                if (num <= i) {
                    //当前的数据最大数
                    max[i] += max[i - num];
                }
            }
        }
        return max[target];
    }

}

