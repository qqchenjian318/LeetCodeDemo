public class NodeNext {
    public int val;
    public NodeNext left;
    public NodeNext right;
    public NodeNext next;

    public NodeNext() {

    }

    public NodeNext(int val) {
        this.val = val;
    }

    public NodeNext(int val, NodeNext left, NodeNext right, NodeNext next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}
