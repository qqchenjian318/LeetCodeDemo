package dayday;

import java.util.*;

/**
 * Leet code 1-25题的具体实现
 */
public class LeetCode25Impl {

    public static String convertZ(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return s;
        }
        if (numRows == 0) return s;
        if (numRows == 1) return s;
        if (numRows > s.length()) {
            return s;
        }
        int numCol = s.length() / 2 + 1;
        char[][] result = new char[numCol][numRows];

        int x = 0;
        int y = 0;
        //默认不是z形
        boolean isZ = false;
        System.out.println(" convertZ numCol=" + numCol + ",numRows=" + numRows);
        for (int i = 0; i < s.length(); i++) {
            //从数组中获取当前的char,放入对应的位置
            char curChar = s.charAt(i);
            result[x][y] = curChar;

            System.out.println("当前index = " + i + ",x=" + x + ",y=" + y + ",char=" + curChar + ",isZ=" + isZ);
            if (y == (numRows - 1)) {
                //说明y到了最后一行，
                isZ = true;
            } else if (y == 0) {
                //说明y到了第一行
                isZ = false;
            }

            if (isZ) {
                y--;
                x++;
            } else {
                y++;
            }
        }
        //
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCol; j++) {
                char curChar = result[j][i];
                if (curChar == 0) {
                    continue;
                }
                sb.append(curChar);
            }
        }

        return sb.toString();
    }

    /**
     * 最节约空间的做法是只存在一个string 一个结果数组
     * 最快的遍历是，遍历两次，出结果
     */
    public static String convertZFast(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return s;
        }
        if (numRows == 0) return s;
        if (numRows == 1) return s;
        if (numRows > s.length()) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean isZ = false;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            rows.get(curRow).append(curChar);

            if (curRow == numRows - 1) {
                //说明到底了
                isZ = true;
            } else if (curRow == 0) {
                isZ = false;
            }
            if (isZ) {
                curRow--;
            } else {
                curRow++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder row : rows) {
            sb.append(row);
        }
        return sb.toString();
    }

    public static int reverse(int x) {
        if (0 == x) {
            return x;
        }
        long i = 0;
        while (x != 0) {
            i = i * 10 + x % 10;
            x /= 10;
        }
        return (int) i == i ? (int) i : 0;
    }

    public static boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0) {
            return false;
        }
        long i = 0;
        int temp = x;
        while (temp != 0) {
            i = i * 10 + temp % 10;
            temp /= 10;
        }
        return (int) i == x;
    }

    /**
     * 实现一个正则函数，支持"*"，"."
     * . 匹配任意字符串
     * * 匹配0或多个前面的哪个元素
     * 1. 当前是否是无限匹配，如果是，就继续，并且
     */
    public static boolean isMatch(String s, String p) {
        int matchIndex = 0;
        boolean isAny = false;
        boolean isMany = false;
        char lastChar = 0;
        for (int i = 0; i < s.length(); i++) {

        }
        return true;
    }

    /**
     * 盛最多水的容器
     */
    public static int maxArea(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int maxArea = 0;
        while (leftIndex != rightIndex) {
            //左右指针不想等
            int culArea = culArea(height, leftIndex, rightIndex);
            if (culArea >= maxArea) {
                maxArea = culArea;
            }
            if (height[leftIndex] < height[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }

        return maxArea;
    }

    private static int culArea(int[] height, int leftIndex, int rightIndex) {
        int leftH = height[leftIndex];
        int rightH = height[rightIndex];
        System.out.println("culArea h=[" + leftH + "," + rightH + "],[" + leftIndex + "," + rightIndex + "]");
        return Math.min(leftH, rightH) * (rightIndex - leftIndex);
    }

    /**
     * 数字转罗马数字
     * I —— 1
     * V —— 5
     * X —— 10
     * L —— 50
     * C —— 100
     * D —— 500
     * M —— 1000
     * 1）I可以放在V和X的左边，用来表示4和9
     * 2）X可以放在L和C的左边，来表示40和90
     * 3）C可以放在D和M的左边，来表示400和900
     * <p>
     * 1 <= num <= 3999
     */
    public static String intToRoman(int num) {
        //显示M的数字
        StringBuilder string = new StringBuilder();
        //计算千位
        int M = num / 1000;
        int mLeter = num % 1000;
        for (int i = 0; i < M; i++) {
            string.append("M");
        }
        //计算百位
        int bLeter = 0;
        if (mLeter >= 900) {
            string.append("CM");
            bLeter = mLeter - 900;
        } else if (mLeter >= 500) {
            string.append("D");
            int C = (mLeter - 500) / 100;
            bLeter = (mLeter - 500) % 100;
            for (int i = 0; i < C; i++) {
                string.append("C");
            }
        } else if (mLeter >= 400) {
            string.append("CD");
            bLeter = mLeter - 400;
        } else {
            int C = mLeter / 100;
            bLeter = mLeter % 100;
            for (int i = 0; i < C; i++) {
                string.append("C");
            }
        }

        int leter = 0;
        if (bLeter >= 90) {
            string.append("XC");
            leter = bLeter - 90;
        } else if (bLeter >= 50) {
            string.append("L");
            int X = (bLeter - 50) / 10;
            leter = (bLeter - 50) % 10;
            for (int i = 0; i < X; i++) {
                string.append("X");
            }
        } else if (bLeter >= 40) {
            string.append("XL");
            leter = bLeter - 40;
        } else {
            int X = bLeter / 10;
            leter = bLeter % 10;
            for (int i = 0; i < X; i++) {
                string.append("X");
            }
        }

        if (leter == 9) {
            string.append("IX");
        } else if (leter >= 5) {
            string.append("V");
            for (int i = 0; i < (leter - 5); i++) {
                string.append("I");
            }
        } else if (leter == 4) {
            string.append("IV");
        } else {
            for (int i = 0; i < leter; i++) {
                string.append("I");
            }
        }

        return string.toString();
    }

    /**
     * 罗马数字转数字
     */
    public static int romanToIn(String s) {
        int result = 0;
        boolean isDig = false;
        for (int i = 0; i < s.length(); i++) {
            if (isDig) {
                isDig = false;
                continue;
            }
            char charAt = s.charAt(i);
            char nextChar = 0;
            String speiclaStr = null;
            if (i < s.length() - 1 && (charAt == 'I' || charAt == 'X' || charAt == 'C')) {
                nextChar = s.charAt(i + 1);
                speiclaStr = "" + charAt + nextChar;
                isDig = isDigChar(speiclaStr);
            }
            if (isDig) {
                result += special.get(speiclaStr);
            } else {
                result += getNum(charAt);
            }
        }
        return result;
    }

    private static HashMap<Character, Integer> normal = new HashMap<Character, Integer>() {
        {
            put('M', 1000);
            put('D', 500);
            put('C', 100);
            put('L', 50);
            put('X', 10);
            put('V', 5);
            put('I', 1);
        }
    };

    private static HashMap<String, Integer> special = new HashMap<String, Integer>() {
        {
            put("CM", 900);
            put("CD", 400);
            put("XC", 90);
            put("XL", 40);
            put("IX", 9);
            put("IV", 4);
        }
    };

    private static boolean isDigChar(String i) {
        return special.containsKey(i);
    }

    private static int getNum(char charAt) {
        return normal.get(charAt);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int curIndex = 1;
        boolean isFinish = false;
        String lastStr = "";
        String firstStr = strs[0];
        while (!isFinish) {
            if (firstStr.length() < curIndex) {
                break;
            }
            String curStr = firstStr.substring(0, curIndex);
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                if (!str.startsWith(curStr)) {
                    isFinish = true;
                    break;
                }
            }
            if (!isFinish) {
                lastStr = curStr;
            }

            curIndex++;
        }
        return lastStr;
    }

    public static String longestCommonPrefixNew(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String result = strs[0];

        for (String str : strs) {
            while (!str.startsWith(result)) {
                result = result.substring(0, result.length() - 1);
                if (result.length() == 0) return "";
            }
        }
        return result;
    }

    /**
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     */
    public static List<List<Integer>> threeNum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        int firstIndex = 0;
        int secondIndex = firstIndex + 1;
        boolean hasSpeical = false;
        while (firstIndex < (nums.length - 2)) {
            int firstNum = nums[firstIndex];
            while (secondIndex < nums.length - 1) {
                int secondNum = nums[secondIndex];
                int thirdIndex = secondIndex + 1;
                while (thirdIndex < nums.length) {
                    if (firstNum + secondNum + nums[thirdIndex] == 0) {
                        boolean speical = isSpeical(firstNum, secondNum, nums[thirdIndex]);
                        if (hasSpeical && speical) {

                        } else {
                            hasSpeical = true;
                            List<Integer> now = new ArrayList<>();
                            now.add(firstNum);
                            now.add(secondNum);
                            now.add(nums[thirdIndex]);
                            result.add(now);
                        }
                    }
                    thirdIndex++;
                }
                secondIndex++;
            }

            firstIndex++;
        }
        return result;
    }

    private static boolean isSpeical(int firstNum, int secondNum, int num) {
        return firstNum == 0 && secondNum == 0 && num == 0;
    }

    /**
     * 从nums中找到最接近target的三个数
     */
    public static int threeSumClosest(int[] nums, int target) {
        return 1;
    }

    private static char[] one = new char[]{};
    private static char[] two = new char[]{'a', 'b', 'c'};
    private static char[] three = new char[]{'d', 'e', 'f'};
    private static char[] four = new char[]{'g', 'h', 'i'};
    private static char[] five = new char[]{'j', 'k', 'l'};
    private static char[] six = new char[]{'m', 'n', 'o'};
    private static char[] seven = new char[]{'p', 'q', 'r', 's'};
    private static char[] eight = new char[]{'t', 'u', 'v'};
    private static char[] nine = new char[]{'w', 'x', 'y', 'z'};

    public static List<String> letterCombinations(String digits) {
        int length = digits.length();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            char charAt = digits.charAt(i);
            char[] chars = getChars(charAt);
            if (chars == null) {
                continue;
            }
            if (result.isEmpty()) {
                List<String> all = getAll("", chars);
                result.addAll(all);
            } else {
                List<String> s = new ArrayList<>();
                for (String stringBuilder : result) {
                    List<String> sb = getAll(stringBuilder, chars);
                    s.addAll(sb);
                }
                result.clear();
                result.addAll(s);
            }
        }
        return result;
    }

    private static List<String> getAll(String stringBuilder, char[] chars) {
        List<String> list = new ArrayList<>();
        for (char aChar : chars) {
            list.add(stringBuilder + aChar);
        }
        return list;
    }

    private static char[] getChars(char charAt) {
        if (charAt == '2') {
            return two;
        } else if (charAt == '3') {
            return three;
        } else if (charAt == '4') {
            return four;
        } else if (charAt == '5') {
            return five;
        } else if (charAt == '6') {
            return six;
        } else if (charAt == '7') {
            return seven;
        } else if (charAt == '8') {
            return eight;
        } else if (charAt == '9') {
            return nine;
        }
        return null;
    }


    public static List<String> letterCombinationsFast(String digits) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            char charAt = digits.charAt(i);
            char[] chars = getChars(charAt);

        }
        return null;
    }

    /**
     * 移除倒数第n个元素
     */
    private static int curIndex;

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        head.next = removeNthFromEnd(head, n);
        curIndex++;
        if (n == curIndex) {
            return head.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "num.ListNode:{" +
                    "val=" + val +
                    ",next=" + next +
                    '}';
        }
    }


    public static boolean isValid(String s) {
//        List<Character> left = new ArrayList<>();
        Stack<Character> left = new Stack<>();
        boolean isValid = true;
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (isLeft(charAt)) {
                System.out.println(" isLeft = " + charAt);
                left.push(charAt);
            } else if (isRight(charAt)) {
                Character pop = left.pop();
                System.out.println(" isRight = " + charAt + ",pop=" + pop);
                if (pop != getLeft(charAt)) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid && left.isEmpty();
    }

    public static boolean isLeft(char content) {
        return content == '[' || content == '{' || content == '(';
    }

    public static boolean isRight(char context) {
        return context == ']' || context == '}' || context == ')';
    }

    public static char getLeft(char context) {
        if (context == ']') {
            return '[';
        } else if (context == ')') {
            return '(';
        } else if (context == '}') {
            return '{';
        }
        return 0;
    }

    /**
     * 合并两个有序链表
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        ListNode curL1 = l1;
        ListNode curL2 = l2;
        ListNode curNode = null;
        while (curL1 != null || curL2 != null) {
            System.out.println("curl1 = " + curL1 + ",curL2 = " + curL2);
            if (curL1 == null) {
                curNode.next = curL2;
                break;
            }
            if (curL2 == null) {
                curNode.next = curL1;
                break;
            }
            if (curL1.val > curL2.val) {
                if (head == null) {
                    head = curL2;
                    curNode = head;
                } else {
                    curNode.next = curL2;
                    curNode = curNode.next;
                }
                curL2 = curL2.next;
            } else {
                if (head == null) {
                    head = curL1;
                    curNode = head;
                } else {
                    curNode.next = curL1;
                    curNode = curNode.next;
                }
                curL1 = curL1.next;
            }
            System.out.println(" curNode ==" + curNode + ",curl1" + curL1 + ",curl2=" + curL2);
        }
        return head;

    }

    /**
     * 生成所有可能的并且有效的括号组合
     * 如果右符号数量大于左符号，那么添加左侧
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        add(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    public static void add(List<String> result, StringBuilder sb, int open, int close, int max) {
        if (sb.length() == max * 2) {
            result.add(sb.toString());
            return;
        }
        if (open < max) {
            sb.append("(");
            add(result, sb, open + 1, close, max);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(")");
            add(result, sb, open, close + 1, max);
            System.out.println("add close < open " + sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 合并k个升序列表，返回头节点
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode headNode = null;
        ListNode curNode = null;
        while (true) {
            ListNode minNode = findMinNode(lists);
            if (minNode == null) {
                break;
            }
            if (headNode == null) {
                headNode = minNode;
            } else {
                //说明headNode已经有了
                curNode.next = minNode;
            }
            curNode = minNode;
        }
        return headNode;
    }


    public static ListNode findMinNode(ListNode[] curList) {
        if (curList == null) {
            return null;
        }
        ListNode minNode = null;
        int curIndex = -1;
        for (int i = 0; i < curList.length; i++) {
            ListNode curNode = curList[i];
            if (curNode == null) {
                continue;
            }
            if (minNode == null) {
                minNode = curNode;
                curIndex = i;
            } else if (curNode.val < minNode.val) {
                minNode = curNode;
                curIndex = i;
            }
            System.out.println(" findMinNode " + curList[i]);
        }
        if (curIndex != -1) {
            curList[curIndex] = curList[curIndex].next;
        }

        System.out.println(" findMinNode ==================" + curIndex);
        return minNode;
    }

    /**
     * 交换节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode lastLastNode = null;
        ListNode lastNode = head;
        ListNode curNode = head.next;
        ListNode nextNode = curNode.next;
        ListNode headNode = curNode;
        while (curNode != null) {
            System.out.println("swap last=" + lastNode.val + ",curNode=" + curNode.val + ",nextNode=" + nextNode);
            //1. 交换操作
            curNode.next = lastNode;
            lastNode.next = nextNode;

            //2. 链接上一段
            if (lastLastNode != null) {
                lastLastNode.next = curNode;
            }
            System.out.println("交换后 ==" + headNode);
            //3. 移动
            lastLastNode = lastNode;
            lastNode = nextNode;
            if (nextNode != null) {
                curNode = nextNode.next;
                if (curNode != null) {
                    nextNode = curNode.next;
                }
            } else {
                curNode = null;
            }
        }
        return headNode;
    }

    /**
     * 每k个节点进行一次翻转
     * 借用来栈来实现翻转操作，时间复杂度提升了
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        int i = 0;
        ListNode curNode = head;
        ListNode lastReadNode = null;
        ListNode headNode = null;
        while (curNode != null) {
            if (i < k) {
                i++;
            } else {
                //执行读取并翻转的操作
                ListNode readNode;
                while (!stack.isEmpty()) {
                    readNode = stack.pop();
                    if (headNode == null) {
                        headNode = readNode;
                    }
                    if (lastReadNode != null) {
                        lastReadNode.next = readNode;
                    }
                    lastReadNode = readNode;
                }
                i = 1;
            }
            stack.add(curNode);

            curNode = curNode.next;
        }
        //最后一次 如果i < k，那么从stack读取出来，然后正常链接
        System.out.println("reverseKGroup i=" + i + ",k=" + k + ",headNode=" + headNode);
        if (i < k) {
            //说明最后一次不足
            ListNode firstNode = null;
            while (!stack.isEmpty()) {
                firstNode = stack.pop();
            }
            //链接最后一次
            if (headNode == null) {
                headNode = firstNode;
            } else {
                lastReadNode.next = firstNode;
            }
        } else {
            ListNode readNode;
            while (!stack.isEmpty()) {
                readNode = stack.pop();
                if (headNode == null) {
                    headNode = readNode;
                }
                if (lastReadNode != null) {
                    lastReadNode.next = readNode;
                    readNode.next = null;
                }
                lastReadNode = readNode;
            }
        }
        return headNode;
    }

    /**
     * 第20题
     * 删除有序数据中的重复元素
     * 有序数组，所以只需要跟前一个进行对比，如果相同 删除即可
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int lastValue = Integer.MAX_VALUE;
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int curValue = nums[i];
            if (lastValue != curValue) {
                lastValue = curValue;
                nums[size] = curValue;
                size++;
            }
        }
        return size;
    }

    /**
     * 移除数组中等于val的值
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value != val) {
                nums[size] = value;
                size++;
            }
        }
        return size;
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        char firstChar = needle.charAt(0);
        for (int i = 0; i < haystack.length(); i++) {
            char c = haystack.charAt(i);
            System.out.println(" 遍历 i=" + i + ",c=" + c + ",firstChar=" + firstChar);
            if (c == firstChar) {
                //那么判断是否符合
                ErrorIndex eql = isEql(haystack, needle, i);
                System.out.println(" eql = " + eql);
                if (eql.isEql) {
                    return i;
                } else {
                    i = eql.index;
                }
            }
        }
        return -1;
    }

    public static int strStrNew(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int m = needle.length();
        int n = haystack.length();
        char firstChar = needle.charAt(0);
        for (int i = 0; i + m <= n; i++) {
            char c = haystack.charAt(i);
            if (c == firstChar) {
                String substring = haystack.substring(i, i + m);
                System.out.println("哈哈哈 " + substring);
                if (substring.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static ErrorIndex isEql(String haystack, String needle, int startIndex) {
        for (int i = 0; i < needle.length(); i++) {
            char c = needle.charAt(i);
            if (haystack.length() <= (startIndex + i)) {
                return new ErrorIndex(false, startIndex + i);
            }
            char c1 = haystack.charAt(startIndex + i);
            if (c != c1) {
                return new ErrorIndex(false, startIndex + i - 2);
            }
        }
        return new ErrorIndex(true, 0);
    }

    public static class ErrorIndex {
        public boolean isEql;
        public int index;

        public ErrorIndex(boolean isEql, int index) {
            this.isEql = isEql;
            this.index = index;
        }

        @Override
        public String toString() {
            return "ErrorIndex{" +
                    "isEql=" + isEql +
                    ", index=" + index +
                    '}';
        }
    }


    public static int divide(int dividend, int divisor) {
        int result = 0;
        while (dividend > divisor) {
            dividend -= divisor;
            result++;
        }
        return result;
    }

    /**
     * 遍历一次s，生成所有可能的子串
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        HashMap<Integer, String> map = new HashMap<>();
        int n = words[0].length();
        for (int i = 0; i < s.length(); i++) {
            if (i + n > s.length()) {
                break;
            }
            String substring = s.substring(i, i + n);
            System.out.println(" 遍历生成 " + substring + ",n=" + n);
            map.put(i, substring);

        }
        List<Integer> result = new ArrayList<>();
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            String value = entry.getValue();
            boolean equlas = isEqulas(value, words);
            if (equlas) {
                //说明存在符合的，那么检查接下来的是否符合
                List<String> strings = Arrays.asList(words);

                for (int i = 0; i < words.length; i++) {
                    int key = i + entry.getKey();
                    String s1 = map.get(key);
                    if (s1 == null) {
                        break;
                    }
                    boolean remove = strings.remove(s1);
                    if (!remove) {
                        break;
                    }
                }
                if (strings.isEmpty()) {
                    result.add(entry.getKey());
                }
            }
        }

        return result;
    }

    private static boolean isEqulas(String value, String[] words) {
        for (String word : words) {
            if (word.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static void nexPermutation(int[] nums) {
        int n = nums.length;
        boolean isOver = false;
        for (int i = n - 1; i >= 0; i--) {
            int cur = nums[i];
            if (i - 1 < 0) {
                isOver = true;
                break;
            }
            int last = nums[i - 1];
            if (cur > last) {
                nums[i - 1] = cur;
                nums[i] = last;
                break;
            } else {
                nums[i - 1] = cur;
                nums[i] = last;
            }
        }
        if (isOver) {
            Arrays.sort(nums);
        }
    }

    /**
     * 最长的有效括号
     * 如果左侧的比右侧的多，说明无效了
     */
    public static int longestValidParentheses(String s) {
        int n = s.length();
        int size = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {

                int now = findStartBy(s, i);
                if (now > size) {
                    size = now;
                }
                break;
            }
        }
        return size;
    }

    private static int findStartBy(String s, int i) {
        //从i开始找
        int left = 0;
        int right = 0;
        int size = 0;
        int lastRight = 0;
        for (int j = i; j < s.length(); j++) {
            char c = s.charAt(j);
            if (c == '(') {
                right++;
            } else if (c == ')') {
                left++;
            }
            if (left > right) {
                return size;
            } else if (left == right) {
                lastRight = size + 1;
            }

            size++;
            System.out.println(" 遍历中 left=" + left + ",right=" + right + ",size=" + size + ",char=" + c);
        }

        return lastRight;
    }
}
