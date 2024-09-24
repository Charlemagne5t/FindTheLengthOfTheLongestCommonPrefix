import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie = new Trie();
        for(int i = 0; i < arr1.length; i++) {
            trie.insert("" + arr1[i]);
        }
        int res = 0;
        for(int x : arr2) {
            res = Math.max(res, trie.startsWith("" + x));
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
                node.d = temp.d + 1;
                temp.children.put(word.charAt(i), node);

            }
            temp = temp.children.get(word.charAt(i));
            if(i == wordLength - 1){
                temp.isTerminal = true;
            }
        }

    }


    public int startsWith(String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            if(temp.children.containsKey(prefix.charAt(i))){
                temp = temp.children.get(prefix.charAt(i));
            }else return temp.d;
        }
        return temp.d;
    }
}

class TrieNode{
    char value;
    int d = 0;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isTerminal;

    public TrieNode(char value) {
        this.value = value;
        this.isTerminal = false;
    }
}




