package dayday;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * xx
 */
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> vals;
    private Iterator<Integer> cur;

    public NestedIterator(List<NestedInteger> nestedList) {
        vals = new ArrayList<>();
        dfs(nestedList);
        cur = vals.iterator();
    }

    private void dfs(List<NestedInteger> nestedList) {
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()){
                vals.add(nest.getInteger());
            }else {
                dfs(nest.getList());
            }
        }
    }


    @Override
    public boolean hasNext() {
        return cur.hasNext();
    }

    @Override
    public Integer next() {
        return cur.next();
    }
}
