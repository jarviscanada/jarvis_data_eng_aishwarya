package ca.jrvs.practice.sorting;

import java.util.Arrays;

public class MergeSort {

  public void mergeSort(int[] arr, int begin, int end) {
    if (begin < end) {
      int mid = (begin + end) / 2;
      mergeSort(arr, begin, mid);
      mergeSort(arr, mid + 1, end);
      merge(arr, begin, mid, end);
    }
  }

  private void merge(int[] arr, int begin, int mid, int end) {
    int[] newArr = Arrays.copyOf(arr, arr.length);
    int i = begin;
    int j = mid + 1;
    int k = begin;
    while (i <= mid && j <= end) {
      if (newArr[i] < newArr[j]) {
        arr[k] = newArr[i];
        i++;
        k++;
      } else {
        arr[k] = newArr[j];
        j++;
        k++;
      }
    }
    if (i <= mid) {
      while (i <= mid) {
        arr[k] = newArr[i];
        i++;
        k++;
      }
    }
    if (j <= end) {
      while (j <= end) {
        arr[k] = newArr[j];
        j++;
        k++;
      }
    }
  }
}