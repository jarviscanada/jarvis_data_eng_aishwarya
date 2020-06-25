package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArrayJListTest {

  private JList<String> list;

  @Before
  public void setup(){
    list = new ArrayJList<>();
    }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void add() {
    list.add("first");
    assertEquals(list.get(0),"first");
    for (int i =0;i<10;i++){
      list.add(String.valueOf(i));
    }
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Null value");
    list.add(null);
  }

  @Test
  public void toArray() {
    for (int i =0;i<10;i++){
      list.add(String.valueOf(i));
    }
    System.out.println(Arrays.toString(list.toArray()));
  }

  @Test
  public void size() {
    for (int i =0;i<10;i++){
      list.add(String.valueOf(i));
    }
    System.out.println(list.size());
  }

  @Test
  public void isEmpty() {
    System.out.println(list.isEmpty());
  }

  @Test
  public void indexOf() {
    for (int i =0;i<10;i++){
      list.add(String.valueOf(i));
    }
    System.out.println(list.indexOf("5"));
    System.out.println(list.indexOf("20"));
  }

  @Test
  public void contains() {
    for (int i =0;i<10;i++){
      list.add(String.valueOf(i));
    }
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Null Object");
    System.out.println(list.contains("6"));
    System.out.println(list.contains("20"));
    System.out.println(list.contains(null));
  }

  @Test
  public void get() {
    for (int i =0;i<10;i++){
      list.add(String.valueOf(i));
    }
    thrown.expect(IndexOutOfBoundsException.class);
    thrown.expectMessage("Index value more than size of ArrayList");
    System.out.println(list.get(6));
    System.out.println(list.get(20));
  }

  @Test
  public void remove() {
    thrown.expect(IndexOutOfBoundsException.class);
    thrown.expectMessage("Index value more than size of ArrayList");
    for (int i =0;i<10;i++){
      list.add(String.valueOf(i));
    }
    System.out.println(list.remove(0));
    System.out.println(list.get(0));
    System.out.println(list.remove(20));
  }

  @Test
  public void clear() {
    for (int i =0;i<10;i++){
      list.add(String.valueOf(i));
    }
    assertFalse("empty",list.isEmpty());
    list.clear();
    System.out.println(list.size());
    assertTrue("Not empty",list.isEmpty());
  }
}