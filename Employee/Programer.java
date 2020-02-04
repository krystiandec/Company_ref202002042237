package Company.Employee;

import java.math.BigDecimal;

public class Programer extends Employee {
    private String technology;
    public String getTechnology() {
        return technology;
    }

    private void setTechnology(String technology) {
        this.technology = technology;
    }

    public Programer(String firstName, String surname, Sex sex, Age age, BigDecimal employeeSalary, String technology) {
        super(firstName, surname, sex, age, employeeSalary);
        setTechnology(technology);
    }

    @Override
    public BigDecimal getSalary() {
        return super.getEmployeeSalary();
    }

    @Override
    public String toString() {
//        System.out.print("Programista, " + " \t" );
        return "{Programista}, \t" + super.toString() + ", \t" + "Technologia: " + technology;
    }

    @Override
    public void wypisz() {
        System.out.print("Programista, " + " \t" );
        super.wypisz();
    }
}
