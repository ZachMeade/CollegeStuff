// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2019
 */

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){

        double temp;
        for (int i = 1; i < a.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(a[j] < a[j-1]){
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
        return a;

    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    public void sort(Comparable[] numbers) {
        recursiveQuick(numbers, 0, numbers.length-1);
    }
    public void recursiveQuick(Comparable[] numbers, int lo, int hi) {
        if(hi <= lo) {
            return;
        }
        int pivotPos = partition(numbers, lo, hi);
        recursiveQuick(numbers, lo, pivotPos-1);
        recursiveQuick(numbers, pivotPos+1, hi);
    }
    static int partition(Comparable[] numbers, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        Comparable pivot = numbers[lo];
        while(true) {
            while((numbers[++i].compareTo(pivot) < 0)) {
                if(i == hi) break;
            }
            while((pivot.compareTo(numbers[--j]) < 0)) {
                if(j == lo) break;
            }
            if(i >= j) break;
            Comparable temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        numbers[lo] = numbers[j];
        numbers[j] = pivot;
        return j;
    }
    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {

        //todo: implement the sort
        return null;

    }//end mergesortIterative



    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {


        //todo: implement the sort
        return null;
    }//end mergeSortRecursive


    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double arr[]){

        int n = arr.length;
// One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
// Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
// Swap the found minimum element with the first
// element
            double temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }//end selectionsort




    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

}//end class
