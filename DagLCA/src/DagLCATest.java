
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
public class DagLCATest {
    // testing functions based on DAG similar to SEdgwick AdjMatrix implementation
    @Test
    public void testV()
    {
        DagLCA test1 = new DagLCA(20);
        assertEquals("testing V() function", 20, test1.V());

    }
    @Test
    public void testE()
    {
        DagLCA test1 = new DagLCA(13);
        assertEquals("testing with no edges",0,test1.E());
        test1.addEdge(1, 2);
        assertEquals("testing with no edges",1,test1.E());
    }

    @Test
    public void testAcyclic()
    {
        DagLCA test1 = new DagLCA(13);//hopefully not unlucky
        test1.addEdge(1, 2);
        test1.addEdge(2, 3);
        test1.addEdge(3, 4);
        test1.isAcyclic();
        assertTrue(test1.acyclic());
        test1.addEdge(4,1);
        test1.isAcyclic();
        assertFalse(test1.acyclic());
    }
    @Test
    public void testLCA()
    {
        DagLCA test1 = new DagLCA(15);
        assertEquals("both vertices exist but not connected", -1,test1.LCA(1, 2));
        test1.addEdge(1, 2);
        test1.addEdge(2, 3);
        test1.addEdge(3, 4);
        test1.addEdge(3, 5);
        assertEquals("We know it it 3",3,test1.LCA(4,5));
        assertEquals("We know it it 3",3,test1.LCA(5,4));
        test1.addEdge(5, 6);
        test1.addEdge(4, 7);
        test1.addEdge(7,8);
        test1.addEdge(6, 9);
        test1.addEdge(9,10);//joins the branches
        test1.addEdge(8, 10);
        assertEquals("LCA should be at the branch poin 3",3, test1.LCA(9,7));
        assertEquals("LCA of two of the same line", 7,test1.LCA(7,8));
        assertEquals("LCA of two of the same line", 4,test1.LCA(7,4));
        assertEquals("LCA of two of the same line", 4,test1.LCA(4,7));
        assertEquals("when one is in the DAG but not connected", -1,test1.LCA(13, 4));
        test1.addEdge(9, 1);
        assertEquals("LCA when it is cyclical",-1,test1.LCA(3, 1));

    }
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrowers() // tested through the adj function giving errors
    {
        DagLCA test = new DagLCA(6);
        test.adj(-1);
        test.adj(88);
        test.LCA(1, -1);//not possible to have it
        test.LCA(1, 88);//and the vertex is out of bounds
        DagLCA test2 = new DagLCA(-4); //shouldnt have negative vertices for our purposes
    }

    @Test
    public void testReverse()
    {
        DagLCA test1 = new DagLCA(10);
        test1.addEdge(1, 2);
        test1.addEdge(0, 1);
        DagLCA testReverse = test1.reverse();
        assertSame("Edges should be the same", testReverse.E(),test1.E());
        assertSame("shouldnt lose vertices", testReverse.V(),test1.V());
        DagLCA test2 = new DagLCA(25);
        DagLCA rev2 = test2.reverse();
        assertSame("Edges should be the same", rev2.E(),test2.E());
        assertSame("shouldnt lose vertices", rev2.V(),test2.V());
    }


}