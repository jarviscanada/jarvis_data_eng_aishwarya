package ca.jrvs.practice.dataStructure.map;

import static java.util.Objects.hash;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class HashJMap<K, V> implements JMap<K, V> {

  /**
   * The default initial capacity - MUST be a power of two.
   */
  static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

  /**
   * The load factor used when none specified in constructor.
   */
  static final float DEFAULT_LOAD_FACTOR = 0.75f;
  /**
   * The load factor for the hash table.
   *
   * @serial
   */
  final float loadFactor;
  /**
   * The table, initialized on first use, and resized as
   * necessary. When allocated, length is always a power of two.
   * (We also tolerate length zero in some operations to allow
   * bootstrapping mechanics that are currently not needed.)
   */
  Node<K, V>[] table;
  /**
   * Holds cached entrySet(). Note that AbstractMap fields are used
   * for keySet() and values().
   */
  Set<Map.Entry<K, V>> entrySet;
  /**
   * The number of key-value mappings contained in this map.
   */
  int size;
  /**
   * The next size value at which to resize (capacity * load factor).
   * Use #capacity() to compute capacity
   *
   * @serial
   */
  // (The javadoc description is true upon serialization.
  // Additionally, if the table array has not been allocated, this
  // field holds the initial array capacity, or zero signifying
  // DEFAULT_INITIAL_CAPACITY.)
  int threshold;

  public HashJMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  /**
   * Constructs an empty <tt>HashMap</tt> with the specified initial
   * capacity and load factor.
   *
   * @param  initialCapacity the initial capacity
   * @param  loadFactor      the load factor
   * @throws IllegalArgumentException if the initial capacity is negative
   *         or the load factor is nonpositive
   */
  public HashJMap(int initialCapacity, float loadFactor) {
    //validate inputs
    //your code goes here
    if (initialCapacity < 0)
      throw new IllegalArgumentException("Illegal initial capacity: " +
          initialCapacity);
    if (loadFactor<=0)
      throw new IllegalArgumentException("Illegal load factor: " +
          loadFactor);
    this.loadFactor = loadFactor;
    //threshold = capacity *
    this.threshold = (int) ((float) initialCapacity * loadFactor);
  }

  /**
   * Associates the specified value with the specified key in this map
   * (optional operation).  If the map previously contained a mapping for
   * the key, the old value is replaced by the specified value.  (A map
   * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
   * if {@link #containsKey(Object) m.containsKey(k)} would return
   * <tt>true</tt>.)
   *
   * Key cannot be null
   *
   * @param key key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @return the previous value associated with <tt>key</tt>, or
   *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
   *         (A <tt>null</tt> return can also indicate that the map
   *         previously associated <tt>null</tt> with <tt>key</tt>,
   *         if the implementation supports <tt>null</tt> values.)
   * @throws NullPointerException if the specified key or value is null
   *         and this map does not permit null keys or values
   */
  @Override
  public V put(K key, V value) {
    //validate key == null
    if (key == null) {
      throw new NullPointerException("Key is null" + key);
    }
    //init this.table
    int index = key.hashCode() % (table.length - 1);
    HashJMap.Node element = this.table[index];
    if (containsKey(key)) {
      this.table[index].value = value;
    }
    if (element == null) {
      this.table[index] = new Node<>(hash(key), key, value, null);
      size++;
      return (V)element.value;
    }
    else {
      do{
        if ((element=element.next) == null) {
          element = new Node<>(hash(key), key, value, null);
          size++;
          Map.Entry<K, V> map = new MyEntry<>(key, value);
          this.entrySet.add(map);
          return (V)element.value;
        }
        else {
          element = element.next;
        }
      }while(true);
    }
  }

    //using key.hashcode to compute the bucket location (this.table)
    //e.g. key.hashcode % (table.length -1)

    //store KV in the table[index] (as Node<K,V>)
    //if key already exist (use #containsKey) update the value instead
    //if table[index] is taken, link the KV pair next to the exiting KV pair

    //add KV pair to this.entrySet

    //if this.size is greater than threshold, double table and re-hash
    //(iterate through this.entrySet for re-hashing )

  class MyEntry<K, V> implements Map.Entry<K, V> {

    private final K key;
    private V value;

    public MyEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }
    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V value) {
      V old = this.value;
      this.value = value;
      return old;
    }
  }

  final int capacity() {
    return (table != null) ? table.length :
        (threshold > 0) ? threshold :
            DEFAULT_INITIAL_CAPACITY;
  }

  /**
   * Returns <tt>true</tt> if this map contains a mapping for the specified
   * key.  More formally, returns <tt>true</tt> if and only if
   * this map contains a mapping for a key <tt>k</tt> such that
   * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
   * at most one such mapping.)
   *
   * @param key key whose presence in this map is to be tested
   * @return <tt>true</tt> if this map contains a mapping for the specified
   *         key
   * @throws NullPointerException if the specified key is null and this map
   *         does not permit null keys
   * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public boolean containsKey(Object key) {
    //validate key == null
    if(key==null){
      throw new NullPointerException("Key is null"+key);
    }
    //using key.hashcode to compute the bucket location (this.table)
    //e.g. key.hashcode % (table.length -1)

    //if there is more than one Node<K,V> in the bucket,
    //traverse through the linkedList and use `equals` to find the key
    int index = key.hashCode()%(table.length-1);
    HashJMap.Node element = this.table[index];
    if(this.table!=null&&this.table.length>0&&element!=null) {
      if (element.hash == hash(key) && // always check first node
          (element.key == key || (key != null && key.equals(element.key)))){
        return true;
      }
      if ((element=element.next) != null) {
        do {
          if (element.hash == hash(key) &&
              (element.key == key || (key != null && key.equals(element.key)))) {
            return true;
          }
        } while ((element=element.next) != null);
      }
    }
    return false;
    }

  /**
   * Returns the value to which the specified key is mapped,
   * or {@code null} if this map contains no mapping for the key.
   *
   * @param key the key whose associated value is to be returned
   * @return the value to which the specified key is mapped, or
   *         {@code null} if this map contains no mapping for the key
   * @throws NullPointerException if the specified key is null and this map
   *         does not permit null keys
   * (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public V get(Object key) {
    if(key==null){
      throw new NullPointerException("Key is null"+key);
    }
    int index = key.hashCode()%(table.length-1);
    HashJMap.Node element = this.table[index];
    if(this.table!=null&&this.table.length>0&&element!=null) {
      if (element.hash == hash(key) && // always check first node
          (element.key == key || (key != null && key.equals(element.key)))){
        return (V)element.value;
      }
      if ((element=element.next) != null) {
        do {
          if (element.hash == hash(key) &&
              (element.key == key || (key != null && key.equals(element.key)))) {
            return (V)element.value;
          }
        } while ((element=element.next) != null);
      }
    }
    return null;
  }

  /**
   * Returns the number of key-value mappings in this map.  If the
   * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
   * <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of key-value mappings in this map
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns a {@link Set} view of the mappings contained in this map.
   * The set is backed by the map, so changes to the map are
   * reflected in the set, and vice-versa.
   *
   * @return a set view of the mappings contained in this map
   */
  @Override
  public Set<Entry<K, V>> entrySet() {
    return entrySet;
  }

/**
   * Basic hash bin node, used for most entries.  (See below for
   * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
   */
   static class Node<K, V> implements Map.Entry<K, V> {

    final int hash;
    final K key;
    V value;
    HashJMap.Node next;

    Node(int hash, K key, V value, HashJMap.Node<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public final K getKey() {
      return key;
    }

    public final V getValue() {
      return value;
    }

    public final String toString() {
      return key + "=" + value;
    }

    public final int hashCode() {
      return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
      V oldValue = value;
      value = newValue;
      return oldValue;
    }

    public final boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Map.Entry) {
        Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
        if (Objects.equals(key, e.getKey()) &&
            Objects.equals(value, e.getValue())) {
          return true;
        }
      }
      return false;
    }
  }
}