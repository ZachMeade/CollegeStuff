// -------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *                                      Average Running Times in nanoSeconds
 *                          | Selection | Insert | Merge Recursive | Merge Iterative |   Quick   |
 * 10 random                | 0.00521699|0.006031|0.013175         |0.050371         |0.010322   |
 * 100 random               | 0.11669099|0.119831|0.0636299        |0.05822799       |0.030222   |
 * 1000 random              | 5.782883  |7.994707|0.546095         |1.242688         |0.547064   |
 * 1000 with Duplicates     | 2.268436  |2.422701|0.10738199       |0.15132099       |0.08262499 |
 * 1000 nearly ordered      | 1.81323599|1.163818|0.07087199       |0.101463         |0.05148399 |
 * 1000 reverse order       | 0.717679  |0.413167|0.077947         |0.10166599       |0.041323   |
 * 1000 sorted              | 0.501154  |0.351057|0.061965         |0.099032         |0.077559   |
 *
 * 1. Which of the sorting algorithms does the order of input have an impact on? Why?
 * 	The order of the data has an impact on the quicksort algorithm due to the position of the pivot for the sorting.
 *
 * 2. Which algorithm has the biggest difference between the best and worst performance, based
 on the type of input, for the input of size 1000? Why?
        The the insertion sort has a best case running time of 0(n) and
        a worst case of 0(n^2)
 *
 *
 * 3. Which algorithm has the best/worst scalability, i.e., the difference in performance time
 based on the input size? Please consider only input files with random order for this answer.
 *
 *   From my results both of the mergesort algorithms have the best scalability based on input size. This is
 *   because as input size increases the time taken does not increase by much.
 *
 * 4. Did you observe any difference between iterative and recursive implementations of merge
 sort?
 *      From my results the recursive implementation of the mergesort algorithm appears to perform better than
 *     the iterative implementation.
 *
 * 5. Which algorithm is the fastest for each of the 7 input files?
 *    random10 works best with selection sort
 *    random100 works best with quick
 *    random1000 works best with merge recursive
 *    1000dups  works best with quick
 *    1000nearly ordered   works best with quick
 *    1000 reversed works best with quick
 *    1000 sorted   works best with merge recursive
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Zach Meade
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
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]) {
        recursiveQuickSort(a, 0, a.length-1);
        return a;
    }

    private static void recursiveQuickSort(double a[], int low, int high) {
        if (high <= low) {
            return;
        }
        int pivotIndex = partition(a, low, high);
        recursiveQuickSort(a, low, pivotIndex-1);
        recursiveQuickSort(a, pivotIndex+1, high);
    }

    private static int partition(double a[], int low, int high) {
        int i = low;
        int j = high+1;
        double pivot = a[low];
        while (true) {
            while (a[++i] < pivot) {
                if (i == high) break;
            }
            while (a[--j] > pivot) {
                if (j == low) break;
            }
            if (i >= j) break;
            double temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        a[low] = a[j];
        a[j] = pivot;
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
        int length = a.length;
        double[] aux = new double[length];
        for (int i = 1; i < length; i = i + i) {
            for (int low = 0; low < length - i; low += i + i) {
                merge(a, aux, low, low+i-1, Math.min(low+i+i-1,length-1));
            }
        }
        return a;

    }//end mergeSortIterative

    static double[] mergeSortRecursive (double a[]) {
        double[] aux = new double[a.length];
        sort(a, aux, 0, a.length-1);
        return a;

    }

    private static void sort(double[] a, double[] aux, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid+1, high);
        merge(a, aux, low, mid, high);
    }

    private static void merge(double[] a, double[] aux, int low, int mid, int high) {
        //copy
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        //merge
        int i = low, j = mid+1;
        for (int k = low; k <= high; k++) {
            if      (i > mid)           a[k] = aux[j++];
            else if (j > high)          a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
        }
    }





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
            System.out.println("\n\n Set Off Results "+(i+1)+"\n\n");
            numbers10 = fileRead(fileArray[0], 10);
            numbers100 = fileRead(fileArray[1], 100);
            numbers1000 = fileRead(fileArray[2], 1000);
            numbers1000Duplicates = fileRead(fileArray[3], 1000);
            numbersNearlyOrdered1000 = fileRead(fileArray[4], 1000);
            numbersReverse1000 = fileRead(fileArray[5], 1000);
            numbersSorted1000 = fileRead(fileArray[6], 1000);
            System.out.println("\nSorts using numbers10 \n ");
            printTimes(numbers10);
            System.out.println("\nSorts using numbers100 \n ");
            printTimes(numbers100);
            System.out.println("\nSorts using numbers1000 \n ");
            printTimes(numbers1000);
            System.out.println("\nSorts using numbers1000Duplicates \n ");
            printTimes(numbers1000Duplicates);
            System.out.println("\nSorts using numbersNearlyOrdered1000 \n ");
            printTimes(numbersNearlyOrdered1000);
            System.out.println("\nSorts using numbersReverse1000 \n ");
            printTimes(numbersReverse1000);
            System.out.println("\nSorts using numbersSorted1000 \n ");
            printTimes(numbersSorted1000);
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
        System.out.println("Selection Sort = "+total);


        startTime = System.nanoTime();
        insertionSort(a);
        endTime = System.nanoTime();
        total = endTime-startTime;
        System.out.println("Insertion Sort = "+total);


        startTime = System.nanoTime();
        mergeSortRecursive(a);
        endTime = System.nanoTime();
        total = endTime-startTime;
        System.out.println("mergeRecursive Sort = "+total);


        startTime = System.nanoTime();
        mergeSortIterative(a);
        endTime = System.nanoTime();
        total = endTime-startTime;
        System.out.println("mergeIterative Sort = "+total );

        startTime = System.nanoTime();
        quickSort(a);
        endTime = System.nanoTime();
        total = endTime-startTime;
        System.out.println("quick Sort = "+total );

    }
}
//end class
