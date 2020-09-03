package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidParenthesisTest {

  private ValidParenthesis vd = new ValidParenthesis();

  @Test
  public void validParenthesis() {
    String str1 = "(]";
    assertFalse(vd.validParenthesis(str1));
    String str2 = "([)]";
    assertFalse(vd.validParenthesis(str2));
    String str3 = "{[]}";
    assertTrue(vd.validParenthesis(str3));
    String str4 = "()[]{}";
    assertTrue(vd.validParenthesis(str4));
  }
}