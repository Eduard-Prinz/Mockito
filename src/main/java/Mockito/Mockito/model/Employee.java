package Mockito.Mockito.model;

import java.util.Objects;

public record Employee(String firstName, String lastName, int salary, int departmentId) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return departmentId == employee.departmentId
                && firstName.equals(employee.firstName)
                && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, departmentId);
    }

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s из отдела № %d с зарплатой %d",
                lastName, firstName, departmentId, salary);
    }
}