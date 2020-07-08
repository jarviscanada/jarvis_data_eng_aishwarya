package ca.jrvs.practice.search;

import java.util.Arrays;
import java.util.Optional;

public class BinarySearch {

  /**
   * find the the target index in a sorted array.
   *
   * @param arr    input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    return binarySearch(arr, target, 0, arr.length);
  }

  private <E> Optional<Integer> binarySearch(E[] arr, E target, int from, int to) {
    int i = from;
    int len = to - 1;
    int mid = (i + len) / 2;
    int comp = ((Comparable) target).compareTo(arr[mid]);
    if (comp > 0 && i < len) {
      return binarySearch(arr, target, mid + 1, arr.length);
    } else if (comp < 0 && i < len) {
      return binarySearch(arr, target, 0, mid);
    } else if (comp == 0) {
      return Optional.of(mid);
    } else {
      return Optional.empty();
    }

  }

  /**
   * find the the target index in a sorted array.
   *
   * @param arr    input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int i = 0;
    int len = arr.length - 1;
    while (i <= len) {
      int midValue = (i + len) / 2;
      if (((Comparable) target).compareTo(arr[midValue]) == 0) {
        return Optional.of(midValue);
      } else if (((Comparable) target).compareTo(arr[midValue]) > 0) {
        i = midValue + 1;
      } else {
        len = midValue - 1;
      }
    }
    return Optional.empty();
  }
}