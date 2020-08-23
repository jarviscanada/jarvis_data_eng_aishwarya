package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToIntegerTest {

  private StringToInteger si = new StringToInteger();

  @Test
  public void atoi() {
    String str1 = "42";
    String str2 = "   -42";
    String str3 = "4193 with words";
    String str4 = "words and 987";
    String str5 = "-91283472332";

    System.out.println(si.atoi(str1));
    System.out.println(si.atoi(str2));
    System.out.println(si.atoi(str3));
    System.out.println(si.atoi(str4));
    System.out.println(si.atoi(str5));

  }

  @Test
  public void atoiWithoutParsing() {
    String str1 = "42";
    String str2 = "   -42";
    String str3 = "4193 with words";
    String str4 = "words and 987";
    String str5 = "-91283472332";

    System.out.println(si.atoiWithoutParsing(str1));
    System.out.println(si.atoiWithoutParsing(str2));
    System.out.println(si.atoiWithoutParsing(str3));
    System.out.println(si.atoiWithoutParsing(str4));
    System.out.println(si.atoiWithoutParsing(str5));

  }
}