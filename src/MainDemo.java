import book.leetbook.BookDesign;

import java.util.Arrays;

/**
 * 入口函数
 */
public class MainDemo {
    public static void main(String[] args) {
        System.out.println("算法启动");
//        num.LeetCode_200.num176();
//        LeetCode_200.num201();
        int[] prices = new int[]{
                1, 2, 3, 4, 5
        };
        BookDesign design = new BookDesign(prices);
        int[] shuffle = design.shuffle();
        System.out.println("shuffle ="+ Arrays.toString(shuffle));
        int[] reset = design.reset();
        System.out.println("rest="+ Arrays.toString(reset));
    }
}
