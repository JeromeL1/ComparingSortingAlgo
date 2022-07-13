
/**
 * Improved MergeSort class.
 */

public class MergeSortsImproved {

  /**
   * Merge sort the provided array using an improved merge operation.
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void mergeSort1(T[] items) {

    // Unfortunately, it is not possible to create an array of T's, this is the
    // workaround.

    T[] temp;

    if (items.length * 2 == 0) {
      temp = (T[]) new Comparable[items.length / 2];
    } else {
      temp = (T[]) new Comparable[items.length / 2 + 1];
    }



    mergeSort1(items, temp, 0, items.length - 1);
  }

  /**
   * Recursive helper method for the merge sort algorithm.
   * 
   * @param items The array to sort
   * @param temp Temporary array for merge operation
   * @param left Index of the left end of the region to sort
   * @param right Index of the right end of the region to sort.
   */
  private static <T extends Comparable<T>> void mergeSort1(T[] items, T[] temp, int left,
      int right) {
    if (left >= right) {
      return; // Region has one record
    }

    int mid = (left + right) / 2; // Select midpoint

    mergeSort1(items, temp, left, mid); // Mergesort first half
    mergeSort1(items, temp, mid + 1, right); // Mergesort second half

    merge1(items, temp, left, mid, right);

  }

  /**
   * Merge two sorted sub-arrays.
   */
  private static <T extends Comparable<T>> void merge1(T[] items, T[] temp, int left, int mid,
      int right) {

    for (int i = 0; i < temp.length && i + left <= mid; i++) {
      temp[i] = items[left + i];
    }

    int i1 = 0;
    int i2 = mid + 1;

    for (int curr = left; curr <= right; curr++) {
      if (i1 + left > mid) { // if temp arr is done.
        items[curr] = items[i2++];
      } else if (i2 > right || temp[i1].compareTo(items[i2]) <= 0) {
        items[curr] = temp[i1++];
      } else {
        items[curr] = items[i2++];
      }
    }
  }

  /**
   * Merge sort the provided array by using an improved merge operation and switching to insertion
   * sort for small sub-arrays.
   */
  public static <T extends Comparable<T>> void mergeSort2(T[] items) {

    // Unfortunately, it is not possible to create an array of T's, this is the
    // workaround.

    mergeSort2(items, 0, items.length - 1);

  }
  
  /**
   * Helper mergeSort2 method to create a temp arr.
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void mergeSort2(T[] items, int start, int end) { 
    
    T[] temp = (T[]) new Comparable[items.length / 2 + 1];
    mergeSort2(items, start, end, temp);
    
  }


  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the fallback method
   * used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSort2(T[] items, int start, int end, T[] temp) {

    if (end - start < 124) {

      BasicSorts.insertionSort(items, start, end);

    } else {

      
      int mid = (start + end) / 2; // Select midpoint
      mergeSort2(items, start, mid, temp); // Mergesort first half
      mergeSort2(items, mid + 1, end, temp); // Mergesort second half
      merge1(items, temp, start, mid, end);
    }
  }
}
