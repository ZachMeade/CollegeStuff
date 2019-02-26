import static org.junit.Assert.assertEquals;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
@RunWith(JUnit4.class)
public class sortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testInsertionSort()
    {

        double a[] = {14.67,10.33,12.11,4.89,5.62,7.99} ;
        String results = "4.89 5.62 7.99 10.33 12.11 14.67" ;

        a = SortComparison.insertionSort(a) ;
        assertEquals( "Checks insertion sort", results, SortComparison.toString(a) );

    }


    @Test
    public void testQuickSort()
    {
        double a[] = {14.67,10.33,12.11,4.89,5.62,7.99} ;
        String results = "4.89 5.62 7.99 10.33 12.11 14.67" ;
//
//        a = SortComparison.quickSort(a) ;
//        assertEquals( "Checks quick sort", results, SortComparison.toString(a) );
    }

    @Test
    public void testMergeSortRecursive()
    {
        double a[] = {14.67,10.33,12.11,4.89,5.62,7.99} ;
        String results = "4.89 5.62 7.99 10.33 12.11 14.67" ;

        a = SortComparison.mergeSortRecursive(a) ;
        assertEquals( "Checks quick sort", results, SortComparison.toString(a) );
    }

    @Test
    public void testMergeSortIterative()
    {
        double a[] = {14.67,10.33,12.11,4.89,5.62,7.99} ;
        String results = "4.89 5.62 7.99 10.33 12.11 14.67" ;

        a = SortComparison.mergeSortIterative(a) ;
        assertEquals( "Checks quick sort", results, SortComparison.toString(a) );
    }

    @Test
    public void testSelectionSort()
    {
        double a[] = {14.67,10.33,12.11,4.89,5.62,7.99} ;
        String results = "4.89 5.62 7.99 10.33 12.11 14.67" ;

        a = SortComparison.selectionSort(a) ;
        assertEquals( "Checks quick sort", results, SortComparison.toString(a) );
    }


    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
        int index = 0 ;
        int size = 100 ;
        double a[] = new double[size] ;
        File file = new File("numbers100.txt");
        //System.out.println(System.getProperty("user.dir"));
        Scanner sc ;
        try {
            sc = new Scanner(file) ;
            while((sc.hasNextDouble()))
            {
                a[index] = sc.nextDouble() ;
                index++ ;
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        a = SortComparison.selectionSort(a) ;
        //System.out.println(SortComparison.toString(a));









    }

}