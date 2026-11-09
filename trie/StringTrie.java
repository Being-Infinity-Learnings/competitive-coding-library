import java.util.*;

class StringTrie {
    public StringTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.getChildren().computeIfAbsent(ch, c -> new TrieNode());
        }
        node.setEndOfWord(true);
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.getChildren().get(ch);
            if (node == null) {
                return false;
            }
        }
        return node.isEndOfWord();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.getChildren().get(ch);
            if (node == null) {
                return false;
            }
        }
        return true;
    }    

    private final TrieNode root;
    private static class TrieNode {
        private final Map<Character, TrieNode> children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void setEndOfWord(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }
    }   
}
