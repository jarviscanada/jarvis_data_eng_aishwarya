package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import org.junit.Test;

public class SwapTwoNumTest {

  private SwapTwoNum swapNum = new SwapTwoNum();
  private List<Integer> num = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE + 1, 3));


  @Test
  public void swapTwoNumBit() {
    System.out.println(num);
    System.out.println(swapNum.swapTwoNumBit(num));
  }

  @Test
  public void swapTwoNum() {
    System.out.println(num);
    System.out.println(swapNum.swapTwoNum(num));
  }
}