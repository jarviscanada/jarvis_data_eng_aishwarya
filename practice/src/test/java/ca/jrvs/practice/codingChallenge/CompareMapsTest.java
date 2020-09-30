package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CompareMapsTest {

  private CompareMaps cm = new CompareMaps();

  @Test
  public void compareMaps() {
    Map<String, Integer> m1 = new HashMap<>();
    Map<String, Integer> m2 = new HashMap<>();
    Map<String, Integer> m3 = new HashMap<>();
    m1.put("first", 100);
    m2.put("second", 200);
    m3.put("first", 100);
    assertFalse(cm.compareMaps(m1, m2));
    assertTrue(cm.compareMaps(m1, m3));
  }
}