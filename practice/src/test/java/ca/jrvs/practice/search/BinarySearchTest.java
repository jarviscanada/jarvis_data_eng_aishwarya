package ca.jrvs.practice.search;

import java.util.Arrays;
import org.junit.Test;

public class BinarySearchTest {

  BinarySearch search = new BinarySearch();
  Integer[] arr = {14, 7, 30, 89, 50, 20};

  @Test
  public void binarySearchRecursion() {
    System.out.println(Arrays.toString(arr));
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));
    System.out.println(search.binarySearchRecursion(arr, 50));
    System.out.println(search.binarySearchRecursion(arr, 70));
  }

  @Test
  public void binarySearchIteration() {
    System.out.println(Arrays.toString(arr));
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));
    System.out.println(search.binarySearchIteration(arr, 7));
    System.out.println(search.binarySearchIteration(arr, 70));
  }
}
