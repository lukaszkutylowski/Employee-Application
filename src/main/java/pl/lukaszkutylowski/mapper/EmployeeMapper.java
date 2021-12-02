package pl.lukaszkutylowski.mapper;

import org.springframework.stereotype.Component;
import pl.lukaszkutylowski.dto.EmployeeDto;
import pl.lukaszkutylowski.dto.EmployeeListDto;
import pl.lukaszkutylowski.form.EmployeeForm;
import pl.lukaszkutylowski.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class EmployeeMapper {

    public Employee entityUpdate(Employee employeeToUpdate, EmployeeForm form) {
        return employeeToUpdate == null ?
                Employee.builder().build() :
                Employee.builder()
                .id(employeeToUpdate.getId())
                .name(form.getName())
                .email(form.getEmail())
                .jobTitle(form.getJobTitle())
                .phone(form.getPhone())
                .imageUrl(form.getImageUrl())
                .employeeCode(employeeToUpdate.getEmployeeCode())
                .build();
    }

    public Employee toEntity(EmployeeForm form) {
        return form == null ?
                Employee.builder().build() :
                Employee.builder()
                .name(form.getName())
                .email(form.getEmail())
                .jobTitle(form.getJobTitle())
                .phone(form.getPhone())
                .imageUrl(form.getImageUrl())
                .employeeCode(UUID.randomUUID().toString())
                .build();
    }

    public EmployeeDto toDto(Employee entity) {
        return entity == null ?
                EmployeeDto.builder().build() :
                EmployeeDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .jobTitle(entity.getJobTitle())
                .phone(entity.getPhone())
                .imageUrl(entity.getImageUrl())
                .employeeCode(entity.getEmployeeCode())
                .build();
    }

    public EmployeeListDto toListDto(List<Employee> employees) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employees = employees == null ? new ArrayList<>() : employees;
        employees.forEach(e -> employeeDtoList.add(toDto(e)));
        return EmployeeListDto.builder()
                .employees(employeeDtoList)
                .build();
    }
}
