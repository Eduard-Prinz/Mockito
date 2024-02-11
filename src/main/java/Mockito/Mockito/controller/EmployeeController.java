package Mockito.Mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Mockito.Mockito.model.Employee;
import Mockito.Mockito.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int salary, @RequestParam int department) {
        Employee result = employeeService.add(firstName, lastName, salary, department);
        return generateMessage(result, "успешно создан");
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.remove(firstName, lastName);
        return generateMessage(result, "удален");
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping
    private Collection<Employee> findAll() {
        return employeeService.findAll();
    }

    private String generateMessage(Employee employee, String status) {
        return String.format("Сотрудник %s %s %s.",
                employee.getLastName(), employee.getFirstName(), status
        );
    }
}