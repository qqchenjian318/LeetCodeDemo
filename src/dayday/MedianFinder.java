package dayday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 数据流的中位数
 * 存储数据的时候，大小有序
 */
public class MedianFinder {
    private final List<Integer> data = new ArrayList<>();

    public MedianFinder() {

    }

    public void addNum(int num) {
        data.add(num);
        data.sort((o1, o2) -> o1 > o2 ? 1 : 0);
        System.out.println("addNum = "+ data);
    }

    public double findMedian() {
        int midIndex = data.size() / 2;
        if (data.size() % 2 == 0) {
            return (data.get(midIndex) + data.get(midIndex - 1)) / 2f;
        } else {
            return data.get(midIndex);
        }
    }
    //6 6
    //6 10-8
    //2 6 10- 6
    //2 6 6 10-6
    //2 5 6 6 10 -6
    //0 2 5 6 6 10-5.5
}
