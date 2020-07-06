package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MissingNumberTest {
  private MissingNumber missNum = new MissingNumber();
  int[] arrNum = {6,3,2,8,1,7,9,0,4};

  @Test
  public void sumNum() {
    System.out.println(missNum.sumNum(arrNum));
  }

  @Test
  public void numSet() {
    System.out.println(missNum.numSet(arrNum));
  }

  @Test
  public void numGauss() {
    System.out.println(missNum.numGauss(arrNum));
  }
}