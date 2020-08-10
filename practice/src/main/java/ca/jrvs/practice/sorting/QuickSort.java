package ca.jrvs.practice.sorting;

public class QuickSort {

  public void quicksort(int[] arr, int begin, int end) {
    if (begin < end) {
      int pivotIndex = partition(arr, begin, end);
      quicksort(arr, begin, pivotIndex - 1);
      quicksort(arr, pivotIndex + 1, end);
    }
  }

  private int partition(int[] arr, int begin, int end) {
    int pivot = arr[end];
    int i = begin;
    for (int j = begin; j < end; j++) {
      int comp = ((Comparable) arr[j]).compareTo(pivot);
      if (comp <= 0) {
        int swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp;
        i++;
      }
    }
    int swapTemp = arr[i];
    arr[i] = pivot;
    arr[end] = swapTemp;
    return i;
  }
}