/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author TODO
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: theta(N)
     * where N is the number of nodes i the tree. The worst case is N because the function recursively
     * loops through the tree and returns the greatest height.
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */

    public int height() {
        return height(root);
    }
    private int height(Node target){
        int rightHeight = 0;
        int leftHeight = 0;

        if(isEmpty()){
            return -1;
        }
        else if(size(target)== 1 ){
            return 0;
        }
        else{
            if(target.left !=null) {
                leftHeight = height(target.left);
            }
            if(target.right != null) {
                rightHeight = height(target.right);
            }

            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }

        }
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key
     * is the element at position (N-1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
      if (isEmpty()) return null;
      else{
          int medianPos = (size()-1)/2;
          Key median = select(medianPos);
          return median;

          }
    }





    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
        if (isEmpty()) return "()";
        else{
            String resultString = printKeysInOrder(root, "");
            String finalResult = "("+resultString +")";
            //finalResult = finalResult.replaceAll("/", "");
            return finalResult;
        }
    }

    private String printKeysInOrder(Node node, String resultString) {
        if (node == null) {
            return "";
        }
        else {

            if (node.left == null && node.right == null) {

                resultString +=  "()" + node.val.toString() + "()";
                //resultString = resultString +"()" + node.val +"("+")";

            }
            else {
                String leftSubtree = printKeysInOrder(node.left, resultString);
                String rightSubtree = printKeysInOrder(node.right, resultString);
                resultString = resultString + ("(" + leftSubtree + ")" + node.val.toString() + "(" + rightSubtree + ")");
            }

            return resultString;
        }
    }

    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
        return prettyPrintKeys(this.root, "");

    }
    private String prettyPrintKeys(BST<Key,Value>.Node node, String result){
        if(isEmpty()) return "-null\n";
        else{
            String string = "";
            if(node != null){
                string += result + "-"+ node.key +"\n";
                string += prettyPrintKeys(node.left, result + " " + "|");
                string += prettyPrintKeys(node.right, result+ " "+ " ");
                return string;
            }
            else{
                string += result + "-"+ "null"+ "\n";
                return string;
            }
        }
    }

    /**
     * Deteles a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
      delete(this.root, key);
    }
    private Node delete(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = deleteMin(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public int rank(Key key)
    {  return rank(key, root);  }

    private int rank(Key key, Node x)
    {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp  < 0) return rank(key, x.left);
        else if (cmp  > 0) return 1 + size(x.left) + rank(key, x.right);
        else if (cmp == 0) return size(x.left);
        return 0;
    }
    public Key select(int n) {
        if (n < 0 || n >= size()) return null; Node x = select(root, n);
        return x.key;
    }
    private Node select(Node x, int n) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > n) return select(x.left, n); else if (t < n) return select(x.right, n-t-1); else return x;
    }
    public void deleteMin()
    {  root = deleteMin(root);  }
    private Node deleteMin(Node x)
    {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

}