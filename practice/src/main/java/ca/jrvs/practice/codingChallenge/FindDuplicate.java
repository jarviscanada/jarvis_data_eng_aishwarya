package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * ticket: https://www.notion.so/Find-the-Duplicate-Number-3b4c0c705baf4b9fb868a0811a9b01da
 */
public class FindDuplicate {

  /**
   * Big-O: O(n log n) Justification: Sorted array.
   */
  public int findDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == nums[i + 1]) {
        return nums[i];
      }
    }
    return -1;
  }

  /**
   * Big-O: O(n) Justification: Set is used.
   */
  public int findDuplicateSet(int[] nums) {
    Set<Integer> set = new HashSet<>(nums.length);
    for (int num : nums) {
      if (set.contains(num)) {
        return num;
      }
      set.add(num);
    }
    return -1;
  }
}
