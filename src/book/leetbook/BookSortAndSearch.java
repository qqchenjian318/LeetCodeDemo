package book.leetbook;

/**
 * LeetBook-排序和搜索
 */
public class BookSortAndSearch {
    /**
     * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * <p>
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //从后往里面写入
        int count = m + n - 1;
        int firstIndex = m - 1;
        int secondIndex = n - 1;
        while (count >= 0) {
            if (firstIndex < 0) {
                nums1[count] = nums2[secondIndex];
                secondIndex--;
            } else if (secondIndex < 0) {
                nums1[count] = nums1[firstIndex];
                firstIndex--;
            } else {
                int firstValue = nums1[firstIndex];
                int secondValue = nums2[secondIndex];
                if (firstValue > secondValue) {
                    nums1[count] = firstValue;
                    firstIndex--;
                } else {
                    nums1[count] = secondValue;
                    secondIndex--;
                }
            }

            count--;
        }
    }

    /**
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * <p>
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * <p>
     * 你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     */
    public static int firstBadVersion(int n) {
        if (n == 1) {
            return isBadVersion(n) ? n : -1;
        }
        //尽量少，就二分法
        int startIndex = 1;
        int endIndex = n;
        while (startIndex < endIndex) {
            int mid = (endIndex - startIndex) / 2 + startIndex;

            if (isBadVersion(mid)) {
                endIndex = mid;
            } else {
                startIndex = mid;
            }
        }
        return startIndex;
    }

    private static boolean isBadVersion(int version) {
        return false;
    }
}
