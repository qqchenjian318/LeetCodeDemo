package book.leetbook;

import javax.swing.plaf.TextUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * LeetBook-字符串
 */
public class BookString {

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题
     */
    public static void reverseString(char[] s) {
        int startIndex = 0;
        int endIndex = s.length - 1;
        //0,1
        while (startIndex < endIndex) {
            char temp = s[startIndex];
            s[startIndex] = s[endIndex];
            s[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
    }

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
     * <p>
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     */
    public static int reverse(int x) {
        //结果数
        //123
        //3、3
        //2、32
        int rev = 0;
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;
        while (x != 0) {
            if (rev < min / 10 || rev > max) {
                return 0;
            }
            int dig = x % 10;
            x /= 10;
            rev = rev * 10 + dig;
        }
        return rev;
    }

    /**
     * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
     * aabb
     */
    public static int firstUniqChar(String s) {
        //"loveleetcode"
        int[] result = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            result[c1 - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (result[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int firstUniqCharNew(String s) {
        int index = -1;
        for (char i = 'a'; i <= 'z'; i++) {
            int indexOf = s.indexOf(i);
            if (indexOf == -1) {
                continue;
            }
            int indexOf1 = s.lastIndexOf(i);
            if (indexOf1 == indexOf) {
                if (index == -1) {
                    index = indexOf1;
                } else {
                    index = Math.min(indexOf1, index);
                }
            }
        }
        return index;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] result = new int[26];

        for (char c : s.toCharArray()) {
            result[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            result[c - 'a']--;
            if (result[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     * <p>
     * 字母和数字都属于字母数字字符。
     */
    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int startIndex = 0;
        int endIndex = chars.length - 1;
        while (startIndex < endIndex) {
            char startChar = chars[startIndex];
            char endChar = chars[endIndex];
            if (!isVaild(startChar)) {
                startIndex++;
                continue;
            } else if (!isVaild(endChar)) {
                endIndex--;
                continue;
            }
            if (exchange(startChar) != exchange(endChar)) {
                return false;
            }


            startIndex++;
            endIndex--;
        }
        return true;
    }

    private static char exchange(char value) {
        if (value >= 'A' && value <= 'Z') {
            return (char) (value + 32);
        }
        return value;
    }

    //97-65
    private static boolean isVaild(char value) {
        if (value >= 'a' && value <= 'z') {
            return true;
        }
        if (value >= 'A' && value <= 'Z') {
            return true;
        }
        return value >= '0' && value <= '9';
    }

    /**
     * 请你来实现一个myAtoi(string s)函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231, 231− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231− 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     */
    public static int myAtoi(String s) {
        int len = s.length();

        char[] chars = s.toCharArray();
        int index = 0;
        while (index < len && chars[index] == ' ') {
            index++;
        }
        if (index == len) {
            return 0;
        }

        //记录正负数
        int sign = 1;
        char firstChar = chars[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        //将后续的数字进行转换
        int maxValue = Integer.MAX_VALUE / 10;
        int dMAxValue = Integer.MAX_VALUE % 10;
        int minValue = Integer.MIN_VALUE / 10;
        int dMiNValue = Integer.MIN_VALUE % 10;

        int res = 0;
        while (index < len) {
            char curChar = chars[index];
            if (curChar > '9' || curChar < '0') {
                break;
            }
            if (res > maxValue || (res == maxValue && (curChar - '0') > dMAxValue)) {
                return Integer.MAX_VALUE;
            }
            if (res < minValue || (res == minValue && (curChar - '0') > -dMiNValue)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * (curChar - '0');
            index++;
        }
        return res;
    }

    /**
     * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的
     * 第一个匹配项的下标（下标从 0 开始）。如果needle 不是 haystack 的一部分，则返回 -1 。
     * <p>
     * 找到第一个相等，然后判定后续的是否一致
     */
    public static int strStr(String haystack, String needle) {
        char firstChar = needle.charAt(0);
        for (int i = 0; i < haystack.length(); i++) {
            char c = haystack.charAt(i);
            if (c == firstChar) {
                //遍历后续的是是否相等
                if (isTrue(haystack, i, needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean isTrue(String haystack, int startIndex, String needle) {
        int endIndex = startIndex + needle.length();

        for (int i = startIndex; i < endIndex; i++) {
            if (i >= haystack.length()) {
                return false;
            }
            char c = haystack.charAt(i);
            char twoChar = needle.charAt(i - startIndex);
            if (c != twoChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * <p>
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     * <p>
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     * <p>
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     * <p>
     * 1 - 一个1
     * 11  - 一个1，记作11
     * 21 - 二个1，记作21
     * -一个2一个1，记作1211
     * 一个1和一个2和两个1
     */
    public static String countAndSay(int n) {
        String count = "1";
        while (n > 1) {
            //4
            count = read(count);
            n--;
        }
        return count;
    }

    private static String read(String str) {
        int count = 0;
        char lastChar = str.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == lastChar) {
                count++;
            } else {
                sb.append(count).append(lastChar);
                count = 1;
                lastChar = c;
            }
        }
        sb.append(count).append(lastChar - '0');
        return sb.toString();
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     */
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (true) {
            char lastChar = '#';
            boolean isTrue = true;
            for (int i = 0; i < strs.length; i++) {
                String str = strs[i];
                if (str.length() <= index) {
                    isTrue = false;
                    break;
                }
                char c = str.charAt(index);
                if (lastChar == '#') {
                    lastChar = c;
                } else if (lastChar != c) {
                    isTrue = false;
                    break;
                }
            }
            if (lastChar != '#' && isTrue) {
                sb.append(lastChar);
            } else {
                break;
            }
            index++;
        }
        return sb.toString();
    }

    /**
     * 假定第一个位最长公共子串
     * 找出其与第二个字符串最长的公共子串，
     */
    public static String longestNew(String[] strs) {
        String longest = strs[0];
        for (int i = 1; i < strs.length; i++) {
            longest = findLongest(longest, strs[i]);
            if (longest.length() == 0) {
                break;
            }
        }
        return longest;
    }

    private static String findLongest(String firstStr, String secondStr) {
        int min = Math.min(firstStr.length(), secondStr.length());
        int index = 0;
        while (index < min) {
            char firstChar = firstStr.charAt(index);
            char secondChar = secondStr.charAt(index);
            if (firstChar != secondChar) {
                break;
            }
            index++;
        }
        if (index == 0) {
            return "";
        }
        return firstStr.substring(0, index);
    }

}
