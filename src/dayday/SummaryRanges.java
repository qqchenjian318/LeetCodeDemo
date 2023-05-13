package dayday;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    private final List<Node> curList = new ArrayList<>();

    public SummaryRanges() {

    }

    public void addNum(int value) {
        System.out.println("value=" + value + ",list=" + curList);
        if (curList.isEmpty()) {
            Node node = new Node();
            node.start = value;
            node.end = value;
            curList.add(node);
            return;
        }
        boolean isOver = false;
        for (int i = 0; i < curList.size(); i++) {
            Node nodeArr = curList.get(i);
            if (nodeArr.start <= value && nodeArr.end >= value) {
                //条件1，刚好存在一个范围区间，不做任何操作
                isOver = true;
            } else if (value < nodeArr.start) {
                //如果值在当前当左侧，那么看看更加左侧是否有值
                if (i == 0) {
                    //说明左侧没有了
                    if (value == nodeArr.start - 1){
                        nodeArr.start = value;
                        isOver = true;
                        break;
                    }
                    Node node = new Node();
                    node.start = value;
                    node.end = value;
                    curList.add(0, node);
                    isOver = true;
                    break;
                } else {
                    int leftIndex = i - 1;
                    Node leftNode = curList.get(leftIndex);
                    if (leftNode.end == (value - 1) && nodeArr.start == (value + 1)) {
                        leftNode.end = nodeArr.end;
                        curList.remove(i);
                        isOver = true;
                        break;
                    } else if (leftNode.end == (value - 1)) {
                        leftNode.end = value;
                        isOver = true;
                        break;
                    } else if (nodeArr.start == (value + 1)) {
                        nodeArr.start = value;
                        isOver = true;
                        break;
                    } else if (leftNode.end < value) {
                        Node node = new Node();
                        node.start = value;
                        node.end = value;
                        curList.add(i, node);
                        isOver = true;
                        break;
                    }
                }
            }
        }
        if (!isOver) {
            Node lastNode = curList.get(curList.size() - 1);
            if (lastNode.end == value-1){
                lastNode.end = value;
            }else {
                Node node = new Node();
                node.start = value;
                node.end = value;
                curList.add(node);
            }
        }
    }


    public int[][] getIntervals() {
        int[][] result = new int[curList.size()][2];
        for (int i = 0; i < curList.size(); i++) {
            Node node = curList.get(i);
            result[i][0] = node.start;
            result[i][1] = node.end;
        }

        return result;
    }

    public static class Node {
        int start;
        int end;

        @Override
        public String toString() {
            return "dayday.Node{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
