package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotateStringTest {

  private RotateString rs = new RotateString();

  @Test
  public void rotateString() {
    String A = "abcde";
    String B = "cdeab";
    String C = "abced";
    String D = "abpcd";
    assertTrue(rs.rotateString(A, B));
    assertFalse(rs.rotateString(A, C));
    assertFalse(rs.rotateString(A, D));
  }

}