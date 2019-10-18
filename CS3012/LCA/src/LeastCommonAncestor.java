//Main class
public class LeastCommonAncestor<Key extends Comparable<Key>>{

    public class Node{
        Node left, right;
        Key key;

        public Node(Key key)
        {
            this.key = key;
        }
    }

    public Node root;


    public void put(Key key)
    {
        root = put(root, key);
    }

    private Node put(Node x, Key key)
    {
        if(x==null){
            return new Node(key);
        }
        int cmp = key.compareTo(x.key);
        if(cmp<0){
            x.left = put(x.left, key);
        }else if(cmp>0){
            x.right = put(x.right, key);
        }
        return x;
    }

    public Node get(Key key)
    {
        return get(root, key);
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x;
    }




    public Node search(Node root, Key v1, Key v2)
    {
        if(root != null &&(get(v1)!=null &&get(v2)!=null))
        {


            if(root.key.compareTo(v1) == 0 || root.key.compareTo(v2) == 0)
            {
                return root;
            }

            Node leftBranch = search(root.left, v1,v2);
            Node rightBranch = search(root.right,v1,v2);
            //If they both have an answer this is the LCA 
            if(leftBranch != null && rightBranch != null)
            {
                return root;
            }
            if(leftBranch != null) // find and return the node with the value returns null if neither have the value
            {
                return leftBranch;

            }
            else if(rightBranch != null)
            {
                return rightBranch;
            }
            else
                return null;
        }
        return null;
    }
}