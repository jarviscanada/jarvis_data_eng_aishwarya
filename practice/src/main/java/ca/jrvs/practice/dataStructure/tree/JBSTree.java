package ca.jrvs.practice.dataStructure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A simplified BST implementation
 *
 * @param <E> type of object to be stored
 */
public class JBSTree<E> implements JTree<E> {

  Node<E> rootNode=null;

  int size = 0;

  /**
   * The comparator used to maintain order in this tree map
   * Comparator cannot be null
   */
  private Comparator<E> comparator;

  public int compare(E o1, E o2) {
    if (comparator==null) {
      return ((Comparable<E>) o1).compareTo(o2);
    }
    else {
      return comparator.compare(o1, o2);
    }
  }

  /**
   * Create a new BST
   *
   * @param comparator the comparator that will be used to order this map.
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    if(comparator==null){
      throw new IllegalArgumentException("comparator cannot be null");
    }
    this.comparator = comparator;
  }

  /**
   * Insert an object into the BST.
   * Please review the BST property.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */
  @Override
  public E insert(E object) {
    if (rootNode==null){
      rootNode=new Node<>(object,null);
      size++;
      return rootNode.value;
    }
    Node<E> n = rootNode;
    while(true){
      if(compare(object,n.value)<0) {
        if (n.getLeft() == null) {
          n.setLeft(new Node<>(object, n));
          size++;
          return n.getLeft().value;
        }
        n=n.getLeft();
      }
      else if (compare(object,n.value)>0){
          if(n.getRight()==null){
            n.setRight(new Node<>(object,n));
            size++;
            return n.getRight().value;
          }
          n=n.getRight();
      }
      else {
        throw new IllegalArgumentException("the object exists");
      }
    }
  }

  /**
   * Search and return an object, return null if not found
   *
   * @param object to be found
   * @return the object if exists or null if not
   */
  @Override
  public E search(E object) {
    Node<E> n = rootNode;
    while(true){
      if (compare(object,n.value)<0){
        n=n.getLeft();
      }
      else if (compare(object,n.value)>0){
        n=n.getRight();
      }
      else if (compare(object,n.value)==0){
        return n.value;
      }
      else {
        return null;
      }
    }
  }

  /**
   * Remove an object from the tree.
   *
   * @param object to be removed
   * @return removed object
   * @throws IllegalArgumentException if the object not exists
   */
  @Override
  public E remove(E object) {
    Node<E> n = rootNode;
    Node<E> removed = null;
    int flag = 0;
    while(true){
      if (compare(object,n.value)<0){
        n=n.getLeft();
        flag=0;
      }
      else if (compare(object,n.value)>0){
        n=n.getRight();
        flag=1;
      }
      else if (compare(object,n.value)==0){
        //Leaf node
        if(n.getLeft()==null && n.getRight()==null){
          removed = n;
          if(flag==0){
            n.getParent().setLeft(null);
          }
          else{
            n.getParent().setRight(null);
          }
          size--;
          return removed.value;
        }
        //Node with two children
        else if(n.getLeft()!=null && n.getRight()!=null){
          Node<E> pred = predecessor(n);
          removed = n;
          n.value= pred.value;
          size--;
          return removed.value;

        }
        //only Right child
        else if(n.getRight()!=null){
          removed=n;
          n.getParent().setRight(n.getRight());
          n.getRight().setParent(n.getParent());
          size--;
          return removed.value;
        }
        //only left child
        else if(n.getLeft()!=null){
          removed=n;
          n.getParent().setLeft(n.getLeft());
          n.getLeft().setParent(n.getParent());
          size--;
          return removed.value;
        }
      }
      else {
        throw new IllegalArgumentException("Doesn't exist");
      }
    }
  }

  public Node<E> predecessor(Node<E> node){
    Node<E> n = node.getLeft();
    int flag=0;
    if(n!=null){
      while(n.getRight()!=null){
        flag=1;
        n=n.getRight();
      }
    }
    if(flag==0){
      n.getParent().setLeft(null);
    }
    else {
      n.getParent().setRight(null);
    }
    return n;
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in pre-order
   */
  @Override
  public E[] preOrder() {
    E[] nodetraverse;
    List<E> list =preOrder(rootNode);
    nodetraverse= (E[]) list.toArray();
    return nodetraverse;
  }

  public List<E> preOrder(Node<E> node){
    List<E> nodeList = new ArrayList<>();
    if(node!=null){
      nodeList.add(node.value);
      nodeList.addAll(preOrder(node.getLeft()));
      nodeList.addAll(preOrder(node.getRight()));
    }
    return nodeList;
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in-order
   */
  @Override
  public E[] inOrder() {
    E[] nodetraverse;
    List<E> list =inOrder(rootNode);
    nodetraverse= (E[]) list.toArray();
    return nodetraverse;
  }

  public List<E> inOrder(Node<E> node){
    List<E> nodeList = new ArrayList<>();
    if(node!=null){
      nodeList.addAll(inOrder(node.getLeft()));
      nodeList.add(node.value);
      nodeList.addAll(inOrder(node.getRight()));
    }
    return nodeList;
  }
  /**
   * traverse the tree recursively
   *
   * @return all objects pre-order
   */
  @Override
  public E[] postOrder() {
    E[] nodetraverse;
    List<E> list =postOrder(rootNode);
    nodetraverse= (E[]) list.toArray();
    return nodetraverse;
  }

  public List<E> postOrder(Node<E> node) {
    List<E> nodeList = new ArrayList<>();
    if (node != null) {
      nodeList.addAll(postOrder(node.getLeft()));
      nodeList.addAll(postOrder(node.getRight()));
      nodeList.add(node.value);
    }
    return nodeList;
  }
  /**
   * traverse through the tree and find out the tree height
   * @return height
   * @throws NullPointerException if the BST is empty
   */
  @Override
  public int findHeight() {
    int heightL=0;
    int heightR=0;
    if(rootNode==null){
      throw new NullPointerException("Empty tree");
    }
    Node<E> n = rootNode;
    while(n!=null){
      if (n.getLeft()!=null){
        heightL++;
        n=n.getLeft();
      }
      else if (n.getRight()!=null){
        heightL++;
        n=n.getRight();
      }
      else {
        n = null;
      }
    }
    n=rootNode;
    while(n!=null) {
      if (n.getRight() != null) {
        heightR++;
        n = n.getRight();
      } else if (n.getLeft() != null) {
        heightR++;
        n = n.getLeft();
      } else {
        n = null;
      }
    }
    return Math.max(++heightR,++heightL);
  }

  static final class Node<E> {

    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Node)) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return getValue().equals(node.getValue()) &&
          Objects.equals(getLeft(), node.getLeft()) &&
          Objects.equals(getRight(), node.getRight()) &&
          getParent().equals(node.getParent());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getValue(), getLeft(), getRight(), getParent());
    }
  }

  public static void main(String[] args) {
   JBSTree jbsTree = new JBSTree(new Comparator<Integer>() {
     @Override
     public int compare(Integer o1, Integer o2) {
       return o1.compareTo(o2);
     }
   });

    System.out.println(jbsTree.insert(10));
    System.out.println(jbsTree.insert(20));
    System.out.println(jbsTree.insert(15));
    System.out.println(jbsTree.insert(5));
    System.out.println(jbsTree.insert(4));
    System.out.println(jbsTree.insert(25));

    System.out.println(Arrays.toString(jbsTree.preOrder()));
    System.out.println(Arrays.toString(jbsTree.inOrder()));
    System.out.println(Arrays.toString(jbsTree.postOrder()));
    System.out.println(jbsTree.findHeight());
    System.out.println(jbsTree.search(20));
    System.out.println(jbsTree.remove(15));
    System.out.println(Arrays.toString(jbsTree.preOrder()));
    System.out.println(Arrays.toString(jbsTree.inOrder()));
    System.out.println(Arrays.toString(jbsTree.postOrder()));
    System.out.println(jbsTree.remove(5));
    System.out.println(Arrays.toString(jbsTree.preOrder()));
    System.out.println(Arrays.toString(jbsTree.inOrder()));
    System.out.println(Arrays.toString(jbsTree.postOrder()));
    System.out.println(jbsTree.remove(10));
    System.out.println(Arrays.toString(jbsTree.preOrder()));
    System.out.println(Arrays.toString(jbsTree.inOrder()));
    System.out.println(Arrays.toString(jbsTree.postOrder()));



  }
}