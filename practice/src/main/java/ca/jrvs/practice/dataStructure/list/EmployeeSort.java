package ca.jrvs.practice.dataStructure.list;

import static ca.jrvs.practice.dataStructure.list.Employee.ageComparator;
import static ca.jrvs.practice.dataStructure.list.Employee.bothComparator;
import static ca.jrvs.practice.dataStructure.list.Employee.salaryComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeSort {


  public static void main(String[] args) {
    List<Employee> employees = new ArrayList<>();
    Employee John = new Employee(1,"John",30,60000);
    Employee Rani = new Employee(2,"Rani",28,70000);
    Employee Maya = new Employee(3,"Maya",28,65000);
    Employee Eve = new Employee(4,"Eve",35,80000);
    employees.add(John);
    employees.add(Rani);
    employees.add(Maya);
    employees.add(Eve);
    Collections.sort(employees);
    System.out.println("Using CompareTo");
    for (Employee employee:employees){
      System.out.println(employee.getId()+" "+employee.getName()+" "+employee.getAge()+" "+employee.getSalary());
    }
    System.out.println("\n Using Compare for Age");
    Collections.sort(employees,ageComparator);
    for (Employee employee:employees){
      System.out.println(employee.getId()+" "+employee.getName()+" "+employee.getAge()+" "+employee.getSalary());
    }
    System.out.println("\n Compare for Salary");
    Collections.sort(employees,salaryComparator);
    for (Employee employee:employees){
      System.out.println(employee.getId()+" "+employee.getName()+" "+employee.getAge()+" "+employee.getSalary());
    }

    System.out.println("\n Compare for by age and then by Salary");
    Collections.sort(employees,bothComparator);
    for (Employee employee:employees){
      System.out.println(employee.getId()+" "+employee.getName()+" "+employee.getAge()+" "+employee.getSalary());
    }
  }
}
