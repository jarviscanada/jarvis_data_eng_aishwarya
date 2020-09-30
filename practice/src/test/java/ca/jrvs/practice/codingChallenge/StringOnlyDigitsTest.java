package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringOnlyDigitsTest {

  private StringOnlyDigits sod = new StringOnlyDigits();

  @Test
  public void checkStrings() {
    assertTrue(sod.checkStrings("1234"));
    assertFalse(sod.checkStrings("1,234"));
    assertFalse(sod.checkStrings("-1234"));
  }

  @Test
  public void checkStrings2() {
    assertTrue(sod.checkStrings2("1234"));
    assertFalse(sod.checkStrings2("1,234"));
    assertFalse(sod.checkStrings2("-1234"));
  }

  @Test
  public void checkStrings3() {
    assertTrue(sod.checkStrings3("1234"));
    assertFalse(sod.checkStrings3("1,234"));
    assertFalse(sod.checkStrings3("-1234"));
  }
}