package ca.jrvs.practice.dataStructure.list;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {

  private int id;
  private String name;
  private int age;
  private long salary;

  public Employee() {
  }

  public Employee(int id, String name, int age, long salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public long getSalary() {
    return salary;
  }

  public void setSalary(long salary) {
    this.salary = salary;
  }

  @Override
  public int compareTo(Employee o) {
    return (this.age - o.age);
  }

  public static Comparator<Employee> ageComparator = new Comparator<Employee>() {
    @Override
    public int compare(Employee o1, Employee o2) {
      return (o1.getAge() - o2.getAge());
    }
  };

  public static Comparator<Employee> salaryComparator = new Comparator<Employee>() {
    @Override
    public int compare(Employee o1, Employee o2) {
      return (int)(o1.getSalary()-o2.getSalary());
    }
  };

  public static Comparator<Employee> bothComparator = new Comparator<Employee>() {
    @Override
    public int compare(Employee o1, Employee o2) {
      if (o1.getAge() == o2.getAge()) {
        return (int) (o1.getSalary() - o2.getSalary());
      }
      return (o1.getAge() - o2.getAge());
    }
  };
  }