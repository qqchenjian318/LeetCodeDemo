package dayday;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 顶端迭代器
 */
public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> mIterator;
    private Integer nextElement;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.mIterator = iterator;
        this.nextElement = iterator.next();
    }

    public Integer peek() {
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    @Override
    public Integer next() {
        Integer temple = nextElement;
        nextElement = mIterator.hasNext() ? mIterator.next() : null;
        return temple;
    }
}
