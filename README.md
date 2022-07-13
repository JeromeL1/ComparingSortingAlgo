# ComparingSortingAlgo

wrote modified versions of merge and quicksort that exhibit better performance than the standard versions described in textbook (OpenDSA). implemented three improvements (two for merge sort and one for quicksort) that should improve some aspect of the algorithm’s performance without requiring a great deal of additional code. Also, experimentally evaluated the impact of the improvements.

## Given Implementations by professor

**Sorting algorithms**: 
- BasicSort - selection and insertion
- MergeSort
- QuickSort

**timing sorting algorithms**

- SortProfiler - times our sorting algorithms
- Sorter - functional interface for sorting methods
- Generators - static methods for generator methods
- Generators - static methods for generating data with

written by professor to help us experiement with the algorithm we wrote and test their time.

## MergeSortImproved.java

if $n$ is the size of the two subaarays being merged, the following requres n assignments operations.
```
1. Copy all values from the two merge-sorted halves into a temporary array.
2. Merge the values from the temporary array back into the original array.
```

the improved alternative approach cutsthe values from $n$ to $\frac{n}{2}$
```
1. Copy the values from the first merge-sorted half to a temporary array.
2. Merge the values from the temporary array and the second
   merge-sorted half into the original array.
```
### switching between merged and insertion sort

The next improvement is based on the observation that merge sort is actually slower than simple  sorts for small input $\Theta(n^2)$ sizes.

![insertion sort vs mergesort performance](https://w3.cs.jmu.edu/buchhofp/class/cs240_s22/pas/pa3/sorting_files/timings.svg)

the following pseudocode describes the method to recursively break the input into smaller and smaller pieces until some threshold is reached, and then switch strategies to a sorting algorithm that is more efficient on those small inputs.

```
merge_sort(sub-array)
    If sub-array has fewer than MERGE_SORT_THRESHOLD entries:
        Sort the sub-array with insertion sort. 
    Otherwise: 
        Recursively merge_sort the left half
        Recursively merge_sort the right half
        Merge the two sorted halves.
```
## Introspective Sort

The idea behind introspective sort is to perform a standard quick sort until there is evidence that the current input is pathological. If it looks like the current input will cause $\Theta(n^2)$ performance. the algorithm switches strategies to a sorting algorithm with guaranteed $O(n^2)$ performance. Typically, heap sort is used as the alternative, since it is in-place and takes $\Theta(nlog(n))$ time in the worst case. Since we haven’t yet studied heap sort, your version of introspective sort should use the improved merge sort from switing strategies as the alternate sorting algorithm. The resulting algorithm is almost as fast as quick sort for most inputs, but has a worst case performance of $\Theta(nlog(n))$.

It is possible to detect pathological inputs by tracking the recursion depth of the quicksort algorithm. When quicksort is working well, the partition operation typically moves the pivot item somewhere near the center of the current sub-sequence. When this is the case, the maximum recursion depth will be $\Theta(log(n))$. Therefore, introspective sort switches strategies when the recursion depth exceeds $2\lfloor(log^2(n)\rfloor$.

## algo performance doc

 - Figure 1 - Improved Merges. This figure compares the performance of mergeSort1 to the original merge sort implementation as well as quicksort. Results shows for both random and ordered inputs.

 - Figure 2 - Threshold Selection. This figure shows threshold for switching strategies in mergeSort2.
 
 - Figure 3 - Switching Strategies. This figure compares mergeSort2 to the previous two merge sort implementations as well as quicksort. Results shows for both random and ordered inputs.

 - Figure 4 - Introspective Sort (Random Inputs). This figure compares introspective sort to quicksort for randomly generated inputs.

 - Figure 5 - Introspective Sort (Pathological Inputs). This figure compares introspective sort to quicksort for worst-case quicksort inputs.

## Credit
JMUC huffman project, project description reworded for brevity.
