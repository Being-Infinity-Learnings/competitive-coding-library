class BinaryTrie{
    class TrieNode{
        TrieNode[] children;
        int count;

        TrieNode(){
            children = new TrieNode[2];
            count = 0;
        }
    }

    private TrieNode root;
    private final int BIT_SIZE = 31; // Assuming 32-bit integers
    public BinaryTrie(){
        root = new TrieNode();
    }
    public void insert(int num){
        TrieNode node = root;
        for(int i = BIT_SIZE; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(node.children[bit] == null){
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
            node.count++;
        }
    }
    public void delete(int num){
        TrieNode node = root;
        for(int i = BIT_SIZE; i >= 0; i--){
            int bit = (num >> i) & 1;
            TrieNode child = node.children[bit];
            if(child == null || child.count == 0){
                return; // Number not present
            }
            child.count--;
            node = child;
        }
    }
    public int maxXor(int num){
        TrieNode node = root;
        int maxXor = 0;
        for(int i = BIT_SIZE; i >= 0; i--){
            int bit = (num >> i) & 1;
            int toggledBit = 1 - bit;
            if(node.children[toggledBit] != null && node.children[toggledBit].count > 0){
                maxXor |= (1 << i);
                node = node.children[toggledBit];
            } else if(node.children[bit] != null && node.children[bit].count > 0){
                node = node.children[bit];
            } else {
                break; // No further path
            }
        }
        return maxXor;
    }
}
