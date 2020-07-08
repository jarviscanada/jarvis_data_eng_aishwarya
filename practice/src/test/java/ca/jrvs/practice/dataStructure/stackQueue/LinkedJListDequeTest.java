package ca.jrvs.practice.dataStructure.stackQueue;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LinkedJListDequeTest {
  private JDeque<String> list;

  @Before
  public void setup() {
    list = new LinkedJListDeque<String>();
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void add() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Null value");
    this.list.add(null);
  }

  @Test
  public void remove() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    thrown.expect(NoSuchElementException.class);
    thrown.expectMessage("Empty! nothing to remove");
    System.out.println(this.list.remove());
    System.out.println(this.list.remove());
    System.out.println(this.list.remove());
    System.out.println(this.list.remove());
  }

  @Test
  public void pop() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    thrown.expect(NoSuchElementException.class);
    thrown.expectMessage("Empty! nothing to remove");
    System.out.println(this.list.pop());
    System.out.println(this.list.pop());
    System.out.println(this.list.pop());
    System.out.println(this.list.pop());
  }

  @Test
  public void push() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    list.push("5");
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("Null value");
    this.list.add(null);

  }

  @Test
  public void peek() {
    for (int i = 0; i < 3; i++) {
      this.list.add(String.valueOf(i));
    }
    System.out.println(list.peek());
    System.out.println(list.peek());
    System.out.println(this.list.pop());
    System.out.println(list.peek());
  }
}