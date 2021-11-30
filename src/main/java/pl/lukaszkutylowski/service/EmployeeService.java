package pl.lukaszkutylowski.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lukaszkutylowski.dto.EmployeeDto;
import pl.lukaszkutylowski.dto.EmployeeListDto;
import pl.lukaszkutylowski.exception.UserNotFoundException;
import pl.lukaszkutylowski.form.EmployeeForm;
import pl.lukaszkutylowski.mapper.EmployeeMapper;
import pl.lukaszkutylowski.model.Employee;
import pl.lukaszkutylowski.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public void addEmployee(EmployeeForm form) {
        Employee employee = employeeMapper.toEntity(form);
        employeeRepository.save(employee);
    }

    @Transactional
    public EmployeeListDto findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toListDto(employees);
    }

    @Transactional
    public void updateEmployee(EmployeeForm form) {
        Employee employeeById = employeeRepository.findEmployeeById(form.getId())
                .orElseThrow(() -> new UserNotFoundException("User by id " + form.getId() + " was not found"));
        Employee employeeUpdated = employeeMapper.entityUpdate(employeeById, form);
        employeeRepository.save(employeeUpdated);
    }

    @Transactional
    public EmployeeDto findEmployeeById(Long id) {
        Employee employeeById = employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
        return employeeMapper.toDto(employeeById);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
