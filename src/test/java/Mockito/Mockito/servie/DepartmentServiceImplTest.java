package Mockito.Mockito.servie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import Mockito.Mockito.exceptions.EmployeeNotFoundException;
import Mockito.Mockito.service.DepartmentServiceImpl;
import Mockito.Mockito.service.EmployeeService;
import Mockito.Mockito.model.Employee;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static Mockito.Mockito.EmployeeTestConstants.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    public void shouldFindEmployeeWhithMaxSalaryByDepartmentId() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MAX_SALARY_EMPLOYEE, out.findEmployeeWithMaxSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindMaxSalaryInEmptyEmployeesList() {
        when(employeeService.findAll()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployeeWithMaxSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindMaxSalaryInEmptyDepartment() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployeeWithMaxSalary(BAD_DEPARTMENT_ID));
    }

    @Test
    public void shouldFindEmployeeWithMinSalaryByDepartmentId() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MIN_SALARY_EMPLOYEE, out.findEmployeeWithMinSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindMinSalsryInEmptyEmployeesList() {
        when(employeeService.findAll()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployeeWithMinSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindMinSalaryInEmptyDepartment() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployeeWithMinSalary(BAD_DEPARTMENT_ID));
    }

    @Test
    public void shouldReturnAllEmployeesByDepartmentWhenEmployeesExist() {
        when(employeeService.findAll()).thenReturn(DIFFRENT_DEPARTMENT_EMPLOYEES);
        assertEquals(DEPARTMENT_MAP, out.findEmployeesByDepartment());
    }

    @Test
    public void shouldReturnEmptyMapWhenEmployeeDontExist() {
        when(employeeService.findAll()).thenReturn(emptyList());
        assertEquals(emptyMap(), out.findEmployeesByDepartment());
    }

    @Test
    public void shouldReturnEmployeesByDepartmentWhenDepartmentIsCorrectAndEmployeesExistThere() {
        when(employeeService.findAll()).thenReturn(DIFFRENT_DEPARTMENT_EMPLOYEES);
        Map<Integer, List<Employee>> expected = DIFFRENT_DEPARTMENT_EMPLOYEES.stream()
                .collect(Collectors.groupingBy(Employee::departmentId));

        assertEquals(expected, out.findEmployeesByDepartment());
    }

    @Test
    public void shouldReturnEmptyListWhenEmployeesDontFOndInDepartment() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(emptyList(), out.findEmployeesByDepartment(BAD_DEPARTMENT_ID));
    }
}