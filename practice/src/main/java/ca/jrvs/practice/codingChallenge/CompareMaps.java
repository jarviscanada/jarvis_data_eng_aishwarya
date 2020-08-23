package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * ticket: https://www.notion.so/How-to-compare-two-maps-6b711430be6e4a738adc1799a20f2df1
 */
public class CompareMaps {

  /**
   * Big-O: O(1) Justification: Hashcode is used to compare keys and values are accessed from the
   * hashtable.
   */
  public <K, V> boolean compareMaps(Map<K, V> m1, Map<K, V> m2) {
    return m1.equals(m2);
  }
}
