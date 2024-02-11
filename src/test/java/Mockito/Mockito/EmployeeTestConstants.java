package Mockito.Mockito;

import Mockito.Mockito.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

public class EmployeeTestConstants {
    public static final String FIRST_NAME = "Анатолий";
    public static final String FIRST_NAME2 = "Иванов";
    public static final String LAST_NAME = "Акакий";
    public static final String LAST_NAME2 = "Аркадьев";
    public static final int SALARY = 400_000;
    public static final int MIN_SALARY = 50_000;
    public static final int DEPARTMENT_ID = 1;
    public static final int BAD_DEPARTMENT_ID = 2;
    public static final Employee MAX_SALARY_EMPLOYEE = new Employee(FIRST_NAME2, LAST_NAME2, SALARY, DEPARTMENT_ID);
    public static final Employee MIN_SALARY_EMPLOYEE = new Employee(FIRST_NAME, LAST_NAME, MIN_SALARY, DEPARTMENT_ID);
    public static final Set<Employee> EMPLOYEES = Set.of(MIN_SALARY_EMPLOYEE, MAX_SALARY_EMPLOYEE);
    public static final Employee DIFFRENT_DEPARTMENT_EMPLOYEE = new Employee(FIRST_NAME2, LAST_NAME2, SALARY, DEPARTMENT_ID);
    public static final Set<Employee> DIFFRENT_DEPARTMENT_EMPLOYEES = Set.of(MIN_SALARY_EMPLOYEE, DIFFRENT_DEPARTMENT_EMPLOYEE);
    public static final Map<Integer, List<Employee>> DEPARTMENT_MAP = DIFFRENT_DEPARTMENT_EMPLOYEES.stream()
            .collect(groupingBy(Employee::departmentId));
}