import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int max = 0;
        Trie trie1 = new Trie();
        for(int i = 0; i < arr1.length; i++){
            trie1.insert("" + arr1[i]);
        }
        for(int i = 0; i < arr2.length; i++){
            max = Math.max(max, trie1.prefixLength(arr2[i] + ""));
        }

        return max;
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

    public boolean search(String word) {
        TrieNode temp = root;
        for (int i = 0; i < word.length(); i++) {
            if(temp.children.containsKey(word.charAt(i))){
                temp = temp.children.get(word.charAt(i));
            }else return false;
        }
        return temp.isTerminal;
    }
    public int prefixLength(String num){
        TrieNode temp = root;

        int i = 0;
        while(i < num.length() && temp != null && temp.children.containsKey(num.charAt(i))){
            temp = temp.children.get(num.charAt(i));
            i++;

        }
        return i;
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            if(temp.children.containsKey(prefix.charAt(i))){
                temp = temp.children.get(prefix.charAt(i));
            }else return false;
        }
        return true;
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


