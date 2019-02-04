import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import sun.util.resources.ga.LocaleNames_ga;

import static org.junit.Assert.*;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
    }
    @Test
    public void testDeletAt() {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to a list containing 1 elements at position 0", "", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);

        assertTrue(testDLL.deleteAt(0)  );
        testDLL.insertBefore(0, 1);

        testDLL.insertBefore(2, 3);
        testDLL.deleteAt(255);
        assertEquals( "Checking deleteAt to a list containing 3 elements at position 0", "1,2,3", testDLL.toString() );
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to a list containing 3 elements at position 0", "2,3", testDLL.toString() );
        testDLL.insertBefore(0, 1);
        testDLL.deleteAt(2);
        assertEquals( "Checking deleteAt to a list containing 3 elements at position 2", "1,2", testDLL.toString() );
        testDLL.insertBefore(2, 3);
        testDLL.insertBefore(3, 4);
        testDLL.deleteAt(1);
        assertEquals( "Checking deleteAt to a list containing 4 elements at position 1", "1,3,4", testDLL.toString() );

    }

    @Test
    public void testReverse(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 3);
        testDLL.reverse();
        assertEquals( "Checking reverse to a list containing 3 elements", "3,2,1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 800);
        testDLL.insertBefore(1, 900);
        testDLL.reverse();
        assertEquals( "Checking reverse to a list containing 2 elements", "900,800", testDLL.toString() );
    }
    @Test
    public void testMakeUnique(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 1);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique to a list containing 3 elements", "1,2", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();

        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 1);
        testDLL.insertBefore(3, 4);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique to a list containing 4 elements", "1,2,4", testDLL.toString() );
        DoublyLinkedList<Character> testDLLChar = new DoublyLinkedList<Character>();

        testDLLChar.insertBefore(0, 't');
        testDLLChar.insertBefore(1, 'e');
        testDLLChar.insertBefore(2, 's');
        testDLLChar.insertBefore(3, 't');
        assertEquals( "Checking makeUnique to a list containing 4 elements", "t,e,s,t", testDLLChar.toString() );
        testDLL = new DoublyLinkedList<Integer>();

        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 2);
        testDLL.insertBefore(3, 1);
        testDLL.insertBefore(4, 1);
        testDLL.insertBefore(5, 1);
        testDLL.insertBefore(6, 1);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique to a list containing 7 elements", "2,1", testDLL.toString() );
        DoublyLinkedList<String> testDLLString = new DoublyLinkedList<String>();

        testDLLString.insertBefore(0, "test");
        testDLLString.insertBefore(1, "test");
        testDLLString.makeUnique();
        assertEquals( "Checking makeUnique to a list containing 2 strings", "test", testDLLString.toString() );

    }
    @Test
    public void testPush(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(4);
        assertEquals( "Checking push to a list containing 0 elements", "4", testDLL.toString() );
        testDLL.push(2);
        assertEquals( "Checking push to a list containing 1 elements", "2,4", testDLL.toString() );
        testDLL.push(7);
        assertEquals( "Checking push to a list containing 1 elements", "7,2,4", testDLL.toString() );
    }
    @Test
    public void testPop(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.pop();
        assertEquals( "Checking pop to a list containing 0 elements", "", testDLL.toString() );
        testDLL.push(1);
        testDLL.pop();
        assertEquals( "Checking pop to a list containing 1 elements", "", testDLL.toString() );
        testDLL.push(4);
        testDLL.push(2);
        testDLL.push(7);
        testDLL.pop();
        assertEquals( "Checking pop to a list containing 3 elements", "2,4", testDLL.toString() );


    }
    @Test
    public void testEnqueue(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(4);
        assertEquals( "Checking enqueue to a list containing 0 elements", "4", testDLL.toString() );
        testDLL.enqueue(2);
        assertEquals( "Checking enqueue to a list containing 1 elements", "2,4", testDLL.toString() );
        testDLL.enqueue(7);
        assertEquals( "Checking enqueue to a list containing 2 elements", "7,2,4", testDLL.toString() );
    }
    @Test
    public void testDequeue(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.dequeue();
        assertEquals( "Checking dequeue to a list containing 0 elements", "", testDLL.toString() );
        testDLL.enqueue(1);
        testDLL.dequeue();
        assertEquals( "Checking dequeue to a list containing 1 elements", "", testDLL.toString() );
        testDLL.enqueue(4);
        testDLL.enqueue(2);
        testDLL.enqueue(7);
        testDLL.dequeue();
        assertEquals( "Checking dequeue to a list containing 3 elements", "7,2", testDLL.toString() );


    }
    @Test
    public void testGet(){
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertSame(null, testDLL.get(0));
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 1);
        assertEquals(1, (long)testDLL.get(0));
        assertEquals(1, (long)testDLL.get(2));
        assertSame(null, testDLL.get(60));
    }
    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.

}
