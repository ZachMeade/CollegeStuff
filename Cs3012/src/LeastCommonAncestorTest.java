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


}