import org.junit.Test;
import static org.junit.Assert.*;

public class LeastCommonAncestorTest {
    @Test // test with no nodes
    public void testEmpty() {
        LeastCommonAncestor<Integer> testBST = new LeastCommonAncestor<Integer>();

        assertNull( testBST.search(testBST.root, 7, 1));
    }
    @Test // test with only one node in
    public void testSingle()
    {
        LeastCommonAncestor<Integer> testBST = new LeastCommonAncestor<Integer>();
        testBST.put(7);
        assertNull(testBST.search(testBST.root,1,3));
        assertEquals(7,(long)testBST.search(testBST.root,7,7).key);

    }
    @Test // test with multiple nodes
    public void testLargeTree()
    {
        LeastCommonAncestor<Integer> test = new LeastCommonAncestor<Integer>();
        test.put(9);
        test.put(1);
        test.put(4);
        test.put(19);
        test.put(9);
        test.put(13);
        test.put(3);
        test.put(2);
        test.put(15);
        test.put(14);
        test.put(16);

        assertSame(test.search(test.root,19,1).key,9);
        assertSame(test.search(test.root,19,9).key,9);
        assertSame(test.search(test.root,14,16).key,15);
    }
    @Test // test when left or right are not in the tree
    public void testNotInTree()
    {

        LeastCommonAncestor<Integer> test = new LeastCommonAncestor<Integer>();
        test.put(9);
        test.put(1);
        test.put(4);
        test.put(19);
        test.put(9);
        test.put(13);
        assertNull(test.search(test.root,19,3));
        assertNull(test.search(test.root,1,20));
        assertNull(test.search(test.root,5,3));
        assertNull(test.search(test.root,5,5));
        assertNull(test.search(test.root,20,1));
    }
    @Test // test when v1 and v2 are the same value
    public void testSameValue()
    {
        LeastCommonAncestor<Integer> test = new LeastCommonAncestor<Integer>();
        test.put(9);
        test.put(1);
        test.put(4);
        test.put(19);
        test.put(9);
        test.put(13);
        assertSame(test.search(test.root,9,9).key,9);
        assertSame(test.search(test.root,19,19).key,19);
    }

    @Test
    public void testGet()
    {
        LeastCommonAncestor<Integer> bst = new LeastCommonAncestor<Integer>();
        assertNull("test get empty tree", bst.get(7));
        bst.put(1);
        assertSame("test single tree", 1, bst.get(1).key);
        bst.put(2);
        bst.put(4);
        bst.put(5);
        assertSame("test value larger than root",4, bst.get(4).key);
        assertSame("test value smaller than root", 2, bst.get(2).key);
        assertSame("test value larger but not in tree", null, bst.get(11));
    }


}