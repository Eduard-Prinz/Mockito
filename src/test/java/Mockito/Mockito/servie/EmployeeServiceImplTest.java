package Mockito.Mockito.servie;

import org.junit.jupiter.api.Test;
import Mockito.Mockito.exceptions.EmployeeExistsException;
import Mockito.Mockito.exceptions.EmployeeNotFoundException;
import Mockito.Mockito.model.Employee;
import Mockito.Mockito.service.EmployeeServiceImpl;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static Mockito.Mockito.EmployeeTestConstants.*;

class EmployeeServiceImplTest {

    private final EmployeeServiceImpl out = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployeeWhenTheyDontExist() {
        //setup
        Employee expected = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(0, out.findAll().size());
        assertFalse(out.findAll().contains(expected));

        Employee actual = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);

        assertEquals(expected, actual);
        assertEquals(1, out.findAll().size());
        assertTrue(out.findAll().contains(expected));
    }

    @Test
    public void shouldThrowEmployeeExistsExceptionWhenTheyExist() {
        Employee existed = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertTrue(out.findAll().contains(existed));

        assertThrows(EmployeeExistsException.class,
                () -> out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID));
    }

    @Test
    public void shouldFindEmployeeWhenTheyExist() {
        Employee expected = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(expected, out.find(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExeptionWhenFindEmployeeWichDoesntExist() {
        assertEquals(0, out.findAll().size());

        assertThrows(EmployeeNotFoundException.class, () -> out.find(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldRemoveEmployeeWhenTheyExist() {
        Employee expected = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(1, out.findAll().size());
        assertTrue(out.findAll().contains(expected));

        Employee actual = out.remove(FIRST_NAME, LAST_NAME);

        assertEquals(expected, actual);
        assertTrue(out.findAll().isEmpty());
        assertFalse(out.findAll().contains(expected));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemoveEmployeeWhichDoesntExist() {
        assertTrue(out.findAll().isEmpty());

        assertThrows(EmployeeNotFoundException.class, () -> out.remove(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldReturnEmptyCollectionWhenEmployeesDontExist() {
        assertIterableEquals(emptyList(), out.findAll());
    }

    @Test
    public void shouldReturnListOfEmployeesWhenTheyExist() {
        Employee employee1 = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        Employee employee2 = out.add(FIRST_NAME2, LAST_NAME2, SALARY, DEPARTMENT_ID);
        Collection<Employee> expected = List.of(employee1, employee2);

        Collection<Employee> actual = out.findAll();

        assertIterableEquals(expected, actual);
    }
}