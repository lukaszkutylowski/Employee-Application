package pl.lukaszkutylowski.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lukaszkutylowski.dto.EmployeeDto;
import pl.lukaszkutylowski.dto.EmployeeListDto;
import pl.lukaszkutylowski.form.EmployeeForm;
import pl.lukaszkutylowski.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeListDto getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody EmployeeForm form) {
        employeeService.addEmployee(form);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@RequestBody EmployeeForm form) {
        employeeService.updateEmployee(form);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
