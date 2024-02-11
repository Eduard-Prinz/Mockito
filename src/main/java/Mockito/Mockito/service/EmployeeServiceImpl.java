package Mockito.Mockito.service;

import org.springframework.stereotype.Service;
import Mockito.Mockito.exceptions.EmployeeExistsException;
import Mockito.Mockito.exceptions.EmployeeNotFoundException;
import Mockito.Mockito.exceptions.InvalidNameException;
import Mockito.Mockito.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        validateNames(firstName, lastName);

        Employee newEmployee = new Employee(
                firstName,
                lastName,
                salary,
                departmentId
        );

        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeExistsException();
        }

        employees.put(getKey(firstName, lastName), newEmployee);
        return newEmployee;
    }


    @Override
    public Employee remove(String firstName, String lastName) {
        validateNames(firstName, lastName);

        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }

        return employees.remove(getKey(lastName, firstName));
    }

    @Override
    public Employee find(String firstName, String lastName) {
        validateNames(firstName, lastName);

        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }

        return employees.get(getKey(lastName, firstName));
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private void validateNames(String... names) {
        for (String name : names) {
            if (!isAlpha(name)) {
                throw new InvalidNameException(name);
            }
        }
    }

    public static boolean isAlpha(String str) {
        char[] charArray = str.toCharArray();

        for (char c : charArray) {
            if (!Character.isLetter(c)) {
                return false; //
            }
        }
        return true;
    }
    private String getKey(String firstName, String lastName) {
        return (firstName + " " + lastName).toLowerCase();
    }
}