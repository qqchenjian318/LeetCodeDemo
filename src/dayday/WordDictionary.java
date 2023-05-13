package dayday;

/**
 * 添加合搜索单词
 */
public class WordDictionary {

    private WordDictionary[] childWord = new WordDictionary[27];

    private boolean isEnd = false;
    private Character value;

    public WordDictionary() {

    }

    public void addWord(String word) {
        WordDictionary curNode = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.'){
                //说明是万能单词
                curNode.childWord[26] = new WordDictionary();
            }else {
                int index = word.charAt(i) - 'a';
                WordDictionary child = curNode.childWord[index];
                if (child == null){
                    child = new WordDictionary();
                    child.value = word.charAt(i);
                }
                curNode.childWord[index] = child;
                curNode = child;
            }
        }
        curNode.isEnd = true;
    }


}
