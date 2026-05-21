import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie1 = new Trie();
        for(int x : arr1) {
            trie1.insert(String.valueOf(x));
        }

        Trie trie2 = new Trie();
        for(int x : arr2) {
            trie2.insert(String.valueOf(x));
        }

        int res = 0;
        for(int x : arr1) {
            res = Math.max(res, trie2.search(String.valueOf(x)) );
        }
        for(int x : arr2) {
            res = Math.max( res, trie1.search(String.valueOf(x)) );
        }
        return res;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode('0');
    }

    public void insert(String word) {
        int wordLength = word.length();
        TrieNode temp = root;
        for (int i = 0; i < wordLength; i++) {
            if(!temp.children.containsKey(word.charAt(i))){
                TrieNode node = new TrieNode(word.charAt(i));
                temp.children.put(word.charAt(i), node);
            }
            temp = temp.children.get(word.charAt(i));
            if(i == wordLength - 1){
                temp.isTerminal = true;
            }
        }

    }

    public int search(String word) {
        TrieNode temp = root;
        int c = 0;
        for (int i = 0; i < word.length(); i++) {
            if(temp.children.containsKey(word.charAt(i))){
                temp = temp.children.get(word.charAt(i));
                c++;
            }else return c;
        }
        return c;
    }

    
}

class TrieNode{
    char value;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isTerminal;

    public TrieNode(char value) {
        this.value = value;
        this.isTerminal = false;
    }
}