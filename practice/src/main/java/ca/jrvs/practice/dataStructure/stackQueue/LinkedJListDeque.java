package ca.jrvs.practice.dataStructure.stackQueue;

import java.util.NoSuchElementException;

public class LinkedJListDeque<E> implements JDeque<E> {

  int size = 0;
  //Pointer to First Node
  Node<E> first;
  //Pointer to last Node
  Node<E> last;

  private static class Node<E> {
    E data;
    Node<E> nextNode;
    Node<E> prevNode;

    public Node(E data, Node<E> nextNode, Node<E> prevNode) {
      this.data = data;
      this.nextNode = nextNode;
      this.prevNode = prevNode;
    }
  }


  /**
   * This is equivalent enqueue operation in Queue ADT
   * <p>
   * Inserts the specified element into the queue represented by this deque (in other words, at the
   * tail of this deque) if it is possible to do so immediately without violating capacity
   * restrictions, returning {@code true} upon success and throwing an {@code IllegalStateException}
   * if no space is currently available.
   *
   * @param e the element to add
   * @return {@code true} (as specified by {@link Collection#add})
   * @throws NullPointerException if the specified element is null and this deque does not permit
   *                              null elements
   */
  @Override
  public boolean add(E e) {
      if (e == null) {
        throw new NullPointerException(
            "Null value");
      }
      Node prev = last;
      Node<E> newNode = new Node<>(e,null,prev);
      //last = newNode;
      if(prev == null){
        first=last=newNode;
      }
      else if(first.nextNode==null){
        last=newNode;
        first.nextNode=newNode;
      }
      else {
        last.nextNode=newNode;
        last=newNode;
      }
      size++;
      return true;
  }

  /**
   * This is equivalent dequeue operation in Queue ADT
   * <p>
   * Retrieves and removes the head of the queue represented by this deque (in other words, the
   * first element of this deque).
   *
   * @return the head of the queue represented by this deque
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E remove() {
    if (first==null) {
      throw new NoSuchElementException("Empty! nothing to remove");
    }
    Node<E> removed = first;
    first = first.nextNode;
    if(first!=null) {
      first.prevNode = null;
    }
    size--;
    return removed.data;
  }

  /**
   * Pops an element from the stack represented by this deque. In other words, removes and returns
   * the first element of this deque.
   *
   * @return the element at the front of this deque (which is the top of the stack represented by
   * this deque)
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E pop() {
    if (first==null) {
      throw new NoSuchElementException("Empty! nothing to remove");
    }
    Node<E> removed = first;
    first = first.nextNode;
    if(first!=null) {
      first.prevNode = null;
    }
    size--;
    return removed.data;
  }

  /**
   * Pushes an element onto the stack represented by this deque (in other words, at the head of this
   * deque) if it is possible to do so immediately without violating capacity restrictions
   *
   * @param e the element to push
   * @throws NullPointerException if the specified element is null and this deque does not permit
   *                              null elements
   */
  @Override
  public void push(E e) {
    if (e == null) {
      throw new NullPointerException(
          "Null value");
    }
    Node next = first;
    Node<E> newNode = new Node<>(e,next,null);
    if(first == null){
      first=last=newNode;
    }

    first = newNode;
    size++;
  }

  /**
   * Retrieves, but does not remove, the head of the queue represented by this deque (in other
   * words, the first element of this deque), or returns {@code null} if this deque is empty.
   *
   * @return the head of the queue represented by this deque, or {@code null} if this deque is empty
   */
  @Override
  public E peek() {
    if (last==null&&first==null) {
      return null;
    }
    return first.data;
  }
}

