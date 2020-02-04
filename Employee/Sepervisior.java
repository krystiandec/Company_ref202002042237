package Company.Employee;

import java.math.BigDecimal;

public class Sepervisior extends Employee {

    private BigDecimal percentExtra;

    public BigDecimal getPercentExtra() {
        return percentExtra;
    }

    private void setPercentExtra(BigDecimal percentExtra) {
        this.percentExtra = percentExtra;
    }

    public Sepervisior(String firstName, String surname, Sex sex, Age age, BigDecimal employeeSalary, BigDecimal percentExtra) {
        super(firstName, surname, sex, age, employeeSalary);
        this.percentExtra = percentExtra;
    }

    @Override
    public BigDecimal getSalary() {
        BigDecimal totalIncrese = percentExtra.multiply(new BigDecimal("0.01"));
        totalIncrese = totalIncrese.add(new BigDecimal("1"));
        return super.getEmployeeSalary().multiply(totalIncrese); // poprawić to wszystko tak aby było głupotoodporne
    }

    @Override
    public String toString() {
//        System.out.print("Kierownik, \t");
        return "{Kierownik}, \t" + super.toString() + ", \tProcent premii: " + percentExtra;
    }

    @Override
    public void wypisz() {
        System.out.print("Kierownik, " + " \t");
        super.wypisz();
//        System.out.println(", "+ "% premii: " + percentExtra);
    }
}
