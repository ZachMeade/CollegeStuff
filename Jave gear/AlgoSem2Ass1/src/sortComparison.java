// -------------------------------------------------------------------------

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
     *
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     */
    static double[] insertionSort(double a[]) {

        double temp;
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
        return a;

    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param numbers: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
//        public void quickSort(double[] numbers) {
//        recursiveQuick(numbers, 0, numbers.length-1);
//    }
//    public void recursiveQuick(double[] numbers, int lo, int hi) {
//        if(hi <= lo) {
//            return;
//        }
//        int pivotPos = partition(numbers, lo, hi);
//        recursiveQuick(numbers, lo, pivotPos-1);
//        recursiveQuick(numbers, pivotPos+1, hi);
//    }
//    static int partition(double[] numbers, int lo, int hi) {
//        int i = lo;
//        int j = hi+1;
//        Comparable pivot = numbers[lo];
//        while(true) {
//            while((numbers[++i].equa(pivot) < 0)) {
//                if(i == hi) break;
//            }
//            while((pivot.compareTo(numbers[--j]) < 0)) {
//                if(j == lo) break;
//            }
//            if(i >= j) break;
//            Comparable temp = numbers[i];
//            numbers[i] = numbers[j];
//            numbers[j] = temp;
//        }
//        numbers[lo] = numbers[j];
//        numbers[j] = pivot;
//        return j;
//    }
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

    static double[] mergeSortIterative(double a[]) {


        for (int size = 1; size <= a.length - 1; size = 2 * size) {

            for (int left_edge = 0; left_edge < a.length - 1; left_edge += 2 * size) {

                int mid = left_edge + size - 1;

                int right_end = Math.min(left_edge + 2 * size - 1, a.length - 1);
                merge(a, left_edge, mid, right_end);
            }
        }
        return a;
        //todo: implement the sort
    }//end mergesortIterative

    static void merge(double a[], double left_edge, double mid, double right_edge) {
        int i, j, k;
        double leftSubSize = mid - left_edge + 1;
        double rightSubSize = right_edge - mid;
        double leftSub[] = new double[(int) leftSubSize];
        double rightSub[] = new double[(int) rightSubSize];

        for (i = 0; i < leftSubSize; i++) {
            leftSub[i] = a[(int) (left_edge + i)];
        }
        for (j = 0; j < rightSubSize; j++) {
            rightSub[j] = a[(int) (mid + 1 + j)];
        }
        i = 0;
        j = 0;
        k = (int) left_edge;
        while (i < leftSubSize && j < rightSubSize) {
            if (leftSub[i] <= rightSub[j]) {
                a[k] = leftSub[i];
                i++;
            } else {
                a[k] = rightSub[j];
                j++;
            }
            k++;
        }
        while (i < leftSubSize) {
            a[k] = leftSub[i];
            i++;
            k++;
        }
        while (j < rightSubSize) {
            a[k] = rightSub[j];
            j++;
            k++;
        }
    }


    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive(double a[]) {
        if (a.length > 1) {
            int mid = a.length / 2;
            double[] leftSub = new double[mid];
            double[] rightSub = new double[a.length - mid];
            for (int i = 0; i < mid; i++) {
                leftSub[i] = a[i];
            }
            for (int i = mid; i < a.length; i++) {
                rightSub[i - mid] = a[i];
            }
            mergeSortRecursive(leftSub);
            mergeSortRecursive(rightSub);

            int i = 0;
            int j = 0;
            int k = 0;

            while (i < leftSub.length && j < rightSub.length) {
                if (leftSub[i] < rightSub[j]) {
                    a[k] = leftSub[i];
                    i++;
                } else {
                    a[k] = rightSub[j];
                    j++;
                }
                k++;
            }
            while (i < leftSub.length) {
                a[k] = leftSub[i];
                i++;
                k++;
            }
            while (j < rightSub.length) {
                a[k] = rightSub[j];
                j++;
                k++;
            }
        }
        return a;


    }//end mergeSortRecursive


    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param arr: An unsorted array of doubles.
     * @return array sorted in ascending order
     */
    static double[] selectionSort(double arr[]) {

        int n = arr.length;
// One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
// Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
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
        double numbers10[];
        double numbers100[];
        double numbers1000[];
        double numbers1000Duplicates[];
        double numbersNearlyOrdered1000[];
        double numbersReverse1000[];
        double numbersSorted1000[];
        File[] fileArray = new File[7];
        fileArray[0] = new File("src/inputFiles/numbers10.txt");
        fileArray[1] = new File("src/inputFiles/numbers100.txt");
        fileArray[2] = new File("src/inputFiles/numbers1000.txt");
        fileArray[3] = new File("src/inputFiles/numbers1000Duplicates.txt");
        fileArray[4] = new File("src/inputFiles/numbersNearlyOrdered1000.txt");
        fileArray[5] = new File("src/inputFiles/numbersReverse1000.txt");
        fileArray[6] = new File("src/inputFiles/numbersSorted1000.txt");
        for (int i = 0; i<3;i++) {
            System.out.println("Set Off Results "+i+"\n\n\n\n");
            numbers10 = fileRead(fileArray[0], 10);
            numbers100 = fileRead(fileArray[1], 100);
            numbers1000 = fileRead(fileArray[2], 1000);
            numbers1000Duplicates = fileRead(fileArray[3], 1000);
            numbersNearlyOrdered1000 = fileRead(fileArray[4], 1000);
            numbersReverse1000 = fileRead(fileArray[5], 1000);
            numbersSorted1000 = fileRead(fileArray[6], 1000);
            printTimes(numbers10);
        }

    }

    //todo: do experiments as per assignment instructions

    public static String toString(double[] a) {
        String result = "";
        for (int i = 0; i< a.length; i++){
            if(i+1 != a.length){
                result = result +a[i] + " ";
            }
            else{
                result = result + a[i];
            }
        }
        return result;
    }
    public static double[] fileRead(File file, int size){
        double[] a = new double[size];
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int count = 0;
            String st;
            while ((st = br.readLine()) != null)

                a[count] = Double.parseDouble(st);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }//try-catch
        return a;
    }
    public static void printTimes(double a[]){
        long startTime = System.nanoTime();
        selectionSort(a);
        long endTime = System.nanoTime();
        long total = endTime-startTime;
        System.out.println("Selection Sort = "+total +"\n" );


        startTime = System.nanoTime();
        insertionSort(a);
        endTime = System.nanoTime();
        total = endTime-startTime;
        System.out.println("Insertion Sort = "+total +"\n" );


        startTime = System.nanoTime();
        mergeSortRecursive(a);
        endTime = System.nanoTime();
        total = endTime-startTime;
        System.out.println("mergeRecursive Sort = "+total +"\n" );


        startTime = System.nanoTime();
        mergeSortIterative(a);
        endTime = System.nanoTime();
        total = endTime-startTime;
        System.out.println("mergeIterative Sort = "+total +"\n" );

//        startTime = System.nanoTime();
//        quickSort(a);
//        endTime = System.nanoTime();
//        total = endTime-startTime;
//        System.out.println("quick Sort = "+total +"\n" );

    }
}
//end class
