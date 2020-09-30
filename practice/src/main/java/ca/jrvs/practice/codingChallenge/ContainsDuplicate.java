package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/Contains-Duplicate-b9c0c2dca1264028828949f89c0c40e7
 */
public class ContainsDuplicate {

  /**
   * Big-O: O(n log n) Justification: Sorting array algorithm.
   */
  public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == nums[i + 1]) {
        return true;
      }
    }
    return false;
  }

  /**
   * Big-O: O(n) Justification: for loop.
   */
  public boolean containsDuplicateSet(int[] nums) {
    Set<Integer> arrSet = new HashSet<Integer>(nums.length);
    for (int num : nums) {
      if (arrSet.contains(num)) {
        return true;
      }
      arrSet.add(num);
    }
    return false;
  }
}
