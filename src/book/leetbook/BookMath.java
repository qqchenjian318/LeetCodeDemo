package book.leetbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * LeetBook-数学
 */
public class BookMath {

    /**
     * 给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：
     * <p>
     * answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
     * answer[i] == "Fizz" 如果 i 是 3 的倍数。
     * answer[i] == "Buzz" 如果 i 是 5 的倍数。
     * answer[i] == i （以字符串形式）如果上述条件全不满
     */
    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean isThree = i % 3 == 0;
            boolean isFive = i % 5 == 0;
            if (isThree && isFive) {
                result.add("FizzBuzz");
            } else if (isFive) {
                result.add("Buzz");
            } else if (isThree) {
                result.add("Fizz");
            } else {
                result.add(i + "");
            }
        }
        return result;
    }

    /**
     * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
     */
    public static int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += 1) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 给定一个整数，写一个函数来判断它是否是 3的幂次方。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
     */
    public static boolean isPowerOfThree(int n) {
        if (n > 1) {
            while (n % 3 == 0) {
                n /= 3;
            }
        }
        return n == 0;
    }

    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * <p>
     * 例如， 罗马数字 2 写做II，即为两个并列的 1 。12 写做XII，即为X+II。
     * 27 写做XXVII, 即为XX+V+II。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
     * 例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4
     * 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
     * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
     * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
     * 给定一个罗马数字，将其转换成整数。
     */
    public static int romanToInt(String s) {


        return 0;
    }

    /**
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），
     * 返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
     */
    public static int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * <p>
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     * 1 _0001
     * 4_ 0100
     * 01
     */
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     */
    public static boolean isValid(String s) {
        Stack<Character> popStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isLeft(c)) {
                popStack.add(c);
            } else {
                if (popStack.isEmpty()) {
                    return false;
                }
                Character pop = popStack.pop();
                if (pop != getLeft(c)) {
                    return false;
                }
            }
        }
        return popStack.isEmpty();
    }

    private static boolean isLeft(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private static char getLeft(char c) {
        if (c == ')') return '(';
        if (c == '}') return '{';
        if (c == ']') return '[';
        return '-';
    }

    /**
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     */
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int[] data = new int[n + 1];
        for (int i = 0; i < nums.length; i++) {
            data[nums[i]] = 1;
        }
        for (int i = 0; i < data.length; i++) {
            int value = data[i];
            if (value == 0){
                return i;
            }
        }
        return -1;
    }

}
