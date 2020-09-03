package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/Two-Sum-a2b13f2c714b40ffb140bf0f507c1f07
 */
public class TwoSum {

  /**
   * Big-O: O(n^2) Justification: Two for loops used
   */
  public int[] twoSumBruteForce(int[] num, int target) {
    for (int i = 0; i < num.length; i++) {
      for (int j = i + 1; j < num.length; j++) {
        if (num[i] + num[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    throw new IllegalArgumentException("No two sum solution found");
  }

  /**
   * Big-O: O(n^2) Justification: sorting being the reason.
   */
  public int[] twoSumSorting(int[] num, int target) {
    Arrays.sort(num);
    int midIndex = num.length / 2;
    for (int i = 0; i < num.length; i++) {
      int value = target - num[i];
      if (value == num[midIndex]) {
        return new int[]{i, midIndex};
      } else if (value < num[midIndex]) {
        for (int j = i + 1; j < midIndex; j++) {
          if (value == num[j]) {
            return new int[]{i, j};
          }
        }
      } else {
        for (int j = midIndex + 1; j < num.length; j++) {
          if (value == num[j]) {
            return new int[]{i, j};
          }
        }
      }
    }
    throw new IllegalArgumentException("No two sum solution found");
  }

  /**
   * Big-O: O(n) Justification: for loop
   */
  public int[] twoSum(int[] num, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < num.length; i++) {
      int value = target - num[i];
      if (map.containsKey(value)) {
        return new int[]{map.get(value), i};
      }
      map.put(num[i], i);
    }
    throw new IllegalArgumentException("No two sum solution found");
  }

}
