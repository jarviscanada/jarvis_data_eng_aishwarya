package ca.jrvs.practice.dataStructure.list;

public class LinkedJList<E> implements JList<E> {

  int size = 0;
  //Pointer to First Node
  Node<E> first;
  //Pointer to last Node
  Node<E> last;

  /**
   * ticket: https://www.notion.so/Duplicate-LinkedList-Node-59d69135f2ee4ae183eee4ade9eb178b Big-O:
   * Big-O: O(n^2) Justification: Two for loops.
   */
  public void removeduplicate() {
    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; ) {
        if (this.get(i) == this.get(j)) {
          this.remove(j);
        } else {
          j++;
        }
      }
    }
  }

  private static class Node<E> {

    E data;
    Node<E> nextNode;
    Node<E> prevNode;

    public Node(E data, Node<E> nextNode,
        Node<E> prevNode) {
      this.data = data;
      this.nextNode = nextNode;
      this.prevNode = prevNode;
    }
  }

  /**
   * Appends the specified element to the end of this list (optional operation).
   *
   * @param e element to be appended to this list
   * @return <tt>true</tt> (as specified by {@link //Collection#add})
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean add(E e) {
    if (e == null) {
      throw new NullPointerException(
          "Null value");
    }
    Node prev = last;
    Node<E> newNode = new Node<>(e, null, prev);
    //last = newNode;
    if (prev == null) {
      first = last = newNode;
    } else if (first.nextNode == null) {
      last = newNode;
      first.nextNode = newNode;
    } else {
      last.nextNode = newNode;
      last = newNode;
    }
    size++;
    return true;
  }

  /**
   * Returns an array containing all of the elements in this list in proper sequence (from first to
   * last element).
   *
   * <p>This method acts as bridge between array-based and collection-based
   * APIs.
   *
   * @return an array containing all of the elements in this list in proper sequence
   */
  @Override
  public Object[] toArray() {
    Object[] result = new Object[size];
    int i = 0;
    for (Node x = first; x != null; x = x.nextNode) {
      result[i++] = x.data;
    }
    return result;
  }

  /**
   * The size of the ArrayList (the number of elements it contains).
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns <tt>true</tt> if this list contains no elements.
   *
   * @return <tt>true</tt> if this list contains no elements
   */
  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  /**
   * Returns the index of the first occurrence of the specified element in this list, or -1 if this
   * list does not contain the element. More formally, returns the lowest index <tt>i</tt> such
   * that
   * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
   * or -1 if there is no such index.
   *
   * @param o
   */
  @Override
  public int indexOf(Object o) {
    int i = 0;
    for (Node<E> x = first; x != null; x = x.nextNode) {
      if (x.data.equals(o)) {
        return i;
      }
      i++;
    }
    return -1;
  }

  /**
   * Returns <tt>true</tt> if this list contains the specified element. More formally, returns
   * <tt>true</tt> if and only if this list contains at least one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   *
   * @param o element whose presence in this list is to be tested
   * @return <tt>true</tt> if this list contains the specified element
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean contains(Object o) {
    if (o == null) {
      throw new NullPointerException("Null Object");
    }
    for (Node<E> x = first; x != null; x = x.nextNode) {
      if (x.data.equals(o)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range (<tt>index &lt; 0 || index &gt;=
   *                                   size()</tt>)
   */
  @Override
  public E get(int index) {
    if (!(index >= 0 && index < size)) {
      throw new IndexOutOfBoundsException("invalid index");
    }
    Node<E> x = first;
    for (int i = 0; i < index; i++) {
      x = x.nextNode;
    }
    return x.data;
  }

  /**
   * Removes the element at the specified position in this list. Shifts any subsequent elements to
   * the left (subtracts one from their indices).
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E remove(int index) {
    if (!(index >= 0 && index < size)) {
      throw new IndexOutOfBoundsException("invalid index");
    }
    Node<E> x = first;
    for (int i = 0; i < index; i++) {
      x = x.nextNode;
    }
    Node<E> removed = x;
    if (index == 0) {
      first = x.nextNode;
      first.prevNode = null;
    } else if (index == size - 1) {
      x.prevNode.nextNode = x.nextNode;
      last = x.prevNode;
    } else {
      x.prevNode.nextNode = x.nextNode;
    }
    x = null;
    size--;
    return removed.data;
  }

  /**
   * Removes all of the elements from this list (optional operation). The list will be empty after
   * this call returns.
   */
  @Override
  public void clear() {
    for (Node<E> x = first; x != null; ) {
      Node<E> node = x.nextNode;
      x.data = null;
      x.nextNode = null;
      x.prevNode = null;
      x = node;
    }
    first = last = null;
    size = 0;
  }
}