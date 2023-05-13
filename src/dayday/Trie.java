package dayday;

/**
 * 前缀树
 */
public class Trie {
    private final Trie[] child = new Trie[26];
    private boolean isEnd = false;

    public Trie() {

    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.child[index] == null) {
                node.child[index] = new Trie();
            }
            node = node.child[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    public boolean startWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            Trie trie = node.child[index];
            if (trie == null) {
                return null;
            } else {
                node = trie;
            }
        }
        return node;
    }
}
