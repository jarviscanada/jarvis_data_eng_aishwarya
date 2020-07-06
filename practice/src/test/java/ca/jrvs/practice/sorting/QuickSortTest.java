package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class QuickSortTest {

  private QuickSort sort = new QuickSort();
  int[] arr = {4, 5, 2, 1, 3};

  @Test
  public void quicksort() {
    System.out.println(Arrays.toString(arr));
    sort.quicksort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));

  }
}