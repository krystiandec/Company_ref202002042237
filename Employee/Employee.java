package Company.Employee;

import Company.Loggers.ConsoleLogger;
import Company.Loggers.FileLogger;
import Company.Loggers.MultiLogger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

public abstract class Employee implements Serializable {


    private String firstName, surname;
    private Sex sex;

    private int ageVal;

//    private Age age;// transient - oznacza to, ze to pole nie będzie serializowane.

    private BigDecimal employeeSalary;

    protected Employee() {
    }

    public void setAgeVal(Age age) {
        this.ageVal = age.getAge();
    }

    private void setEmployeeSalary(BigDecimal employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    private void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAgeVal() {
        return ageVal;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Sex getSex() {
        return sex;
    }


    public BigDecimal getEmployeeSalary() {
        return employeeSalary;
    }

    public abstract BigDecimal getSalary();

    public void wypisz() {
        System.out.print(getSurname() + ", " + "\t");
        System.out.print(getFirstName() + ", " + "\t");
        System.out.print(getSex().toString() + ", " + "\t");
        System.out.print("lat : " + ageVal + ", " + "\t");
        System.out.printf("pensja brutto: %.2f ", getEmployeeSalary());
        System.out.println(" ");

    }

    @Override
    public String toString() {
        return surname + ", \t" + firstName + ", \t" + "Płeć: " + sex + ", \t" + "Lat: " + ageVal + ", \t" + "Pensja zasadnicza: " + employeeSalary;
    }

    Employee(String firstName, String surName, Sex plec, Age age, BigDecimal employeeSalary) {
        setFirstName(firstName);
        setSurname(surName);
        setSex(plec);
        setAgeVal(age);
        setEmployeeSalary(employeeSalary);
    }

    public static Comparator<Employee> employeeNameComparator = (o1, o2) -> {
        String name1 = o1.getFirstName().toLowerCase();
        String name2 = o2.getFirstName().toLowerCase();
        return name1.compareTo(name2);
    };
    public static Comparator<Employee> employeeSurnameComparator = (o1, o2) -> {
        String surname1 = o1.getSurname().toLowerCase();
        String surname2 = o2.getSurname().toLowerCase();
        return surname1.compareTo(surname2);
    };
    public static Comparator<Employee> employeeAgeComparator = (o1, o2) -> {
        int ageVal1 = o1.getAgeVal();
        int ageVal2 = o2.getAgeVal();
        return ageVal1-ageVal2;
    };
    public static Comparator<Employee> employeeSalaryComparator = (o1, o2) -> {
        BigDecimal employeeSalary1 = o1.getEmployeeSalary();
        BigDecimal employeeSalary2 = o2.getEmployeeSalary();

        return employeeSalary2.compareTo(employeeSalary1);
    };

}

