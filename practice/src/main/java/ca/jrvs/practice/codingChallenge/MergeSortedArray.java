package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Merge-Sorted-Array-d604bde8cb6a4d958f59e97cce804061
 */
public class MergeSortedArray {

  /**
   * Big-O: O(n log n) Justification: while loop is used and only one element from either of the
   * array is selected.
   */
  public void merge(int[] num1, int m, int[] num2, int n) {
    //Assuming that num1 as enough space to take elements from num2

    int i = m - 1;
    int j = num2.length - 1;
    int k = m + n - 1;
    while (i >= 0 && j >= 0) {
      if (num1[i] > num2[j]) {
        num1[k] = num1[i];
        k--;
        i--;
      } else {
        num1[k] = num2[j];
        k--;
        j--;
      }
    }
    if (j >= 0) {
      while (j >= 0) {
        num1[k] = num2[j];
        j--;
        k--;
      }
    }
  }
}
