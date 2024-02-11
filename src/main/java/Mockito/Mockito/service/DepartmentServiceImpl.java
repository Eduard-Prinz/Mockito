package Mockito.Mockito.service;

import org.springframework.stereotype.Service;
import Mockito.Mockito.exceptions.EmployeeNotFoundException;
import Mockito.Mockito.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalary(int departmentId) {
        return employeeService
                .findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee findEmployeeWithMinSalary(int departmentId) {
        return employeeService
                .findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService
                .findAll().stream()
                .sorted(comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .collect(groupingBy(Employee::getDepartmentId));
    }

    @Override
    public Collection<Employee> findEmployeesByDepartment(int departmentId) {
        return employeeService
                .findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .sorted(comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .collect(toList());
    }
}