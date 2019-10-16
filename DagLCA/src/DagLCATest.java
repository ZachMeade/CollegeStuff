
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

}