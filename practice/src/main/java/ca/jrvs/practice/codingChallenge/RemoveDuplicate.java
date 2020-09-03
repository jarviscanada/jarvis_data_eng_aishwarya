package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Duplicates-from-Sorted-Array-db0f4de61477499fa25e65d357974c45
 */
public class RemoveDuplicate {

  /**
   * Big-O: O(n) Justification: for loop.
   */
  public int removeDuplicates(int[] nums) {
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
      if (nums[j] != nums[i]) {
        i++;
        nums[i] = nums[j];
      }
    }
    return i + 1;
  }
}
