package dayday;

import java.util.HashMap;

/**
 * 实现一个lru cahce的数据结构
 * 采用Hash表和双向链表实现
 */
public class LRUCache {
    public HashMap<Integer, DListNode> cache = new HashMap<>();

    private final DListNode headNode;
    private final DListNode lastNode;
    private final int maxCapacity;
    private int size;

    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
        this.headNode = new DListNode();
        this.lastNode = new DListNode();
        this.headNode.nextNode = lastNode;
        this.lastNode.preNode = headNode;
    }

    public int get(int key) {
        DListNode listNode = cache.get(key);
        System.out.println(" get key=" + key + ",node=" + listNode);
        if (listNode == null) {
            return -1;
        }
        moveToFirst(listNode);
        return listNode.val;
    }

    public void put(int key, int value) {
        DListNode dListNode = cache.get(key);
        System.out.println("put key=" + key + ",value=" + value + ", " + dListNode);
        if (dListNode == null) {
            dListNode = new DListNode(key, value);
            //创建一个node，先判定
            cache.put(key, dListNode);
            addToHead(dListNode);
            size++;
            if (size > maxCapacity) {
                //说明当前已经满了，那么先移除掉末尾的节点
                DListNode removeList = removeLast();
                System.out.println(" put removeLast=" + removeList);
                cache.remove(removeList.key, removeList);
                --size;
            }
        } else {
            //说明已经有了 那么修改值并且移动到开头
            dListNode.val = value;
            moveToFirst(dListNode);
        }
    }

    private DListNode removeLast() {
        DListNode removeNode = lastNode.preNode;
        removeNode(removeNode);
        return removeNode;
    }

    private void removeNode(DListNode node) {
        node.preNode.nextNode = node.nextNode;
        node.nextNode.preNode = node.preNode;
    }

    public void addToHead(DListNode node) {
        node.preNode = headNode;
        node.nextNode = headNode.nextNode;
        headNode.nextNode.preNode = node;
        headNode.nextNode = node;
    }

    public void moveToFirst(DListNode curNode) {
        removeNode(curNode);
        addToHead(curNode);
    }

    /**
     * 双向链表
     */
    public static class DListNode {
        public int key;
        public int val;
        public DListNode preNode;
        public DListNode nextNode;

        public DListNode(int key, int value) {
            this.key = key;
            this.val = value;
        }

        public DListNode() {

        }

    }
}
