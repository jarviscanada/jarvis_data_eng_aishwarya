package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LinkedJListTest {

  private JList<String> list;

  @Before
  public void setup() {
    this.list = new LinkedJList<>();
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void add() {
    this.list.add("first");
    assertEquals(list.get(0), "first");
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(1));
    }
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Null value");
    this.list.add(null);
  }

  @Test
  public void toArray() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    System.out.println(Arrays.toString(list.toArray()));
  }

  @Test
  public void size() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    System.out.println(list.size());
  }

  @Test
  public void isEmpty() {
    System.out.println(list.isEmpty());
  }

  @Test
  public void indexOf() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    System.out.println(list.indexOf("1"));
    System.out.println(list.indexOf("20"));
  }

  @Test
  public void contains() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Null Object");
    System.out.println(list.contains("1"));
    System.out.println(list.contains("20"));
    System.out.println(list.contains(null));
  }

  @Test
  public void get() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    thrown.expect(IndexOutOfBoundsException.class);
    System.out.println(list.get(2));
    System.out.println(list.get(20));
  }

  @Test
  public void remove() {
    for (int i = 0; i < 5; i++) {
      this.list.add(String.valueOf(i));
    }
    thrown.expect(IndexOutOfBoundsException.class);
    System.out.println(list.remove(4));
    System.out.println(list.remove(0));
    System.out.println(list.remove(1));
    System.out.println(list.remove(20));
  }

  @Test
  public void clear() {
    for (int i = 0; i < 5; i++) {
      this.list.add(String.valueOf(i));
    }
    System.out.println(list.size());
    assertNotNull("It is Null", list.isEmpty());
    list.clear();
    System.out.println(list.size());
    assertTrue("Not empty", list.isEmpty());
  }

  @Test
  public void removeDuplicateNodes() {
    LinkedJList<Integer> list = new LinkedJList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(2);
    list.add(4);
    list.add(3);
    System.out.println("LinkedList: " + Arrays.toString(list.toArray()));
    list.removeduplicate();
    System.out.println("updated LinkedList:" + Arrays.toString(list.toArray()));
  }
}