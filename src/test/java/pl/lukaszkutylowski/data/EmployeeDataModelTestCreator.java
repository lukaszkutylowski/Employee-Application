package pl.lukaszkutylowski.data;

import pl.lukaszkutylowski.dto.EmployeeDto;
import pl.lukaszkutylowski.dto.EmployeeListDto;
import pl.lukaszkutylowski.form.EmployeeForm;
import pl.lukaszkutylowski.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataModelTestCreator {
    public static final Long EMPLOYEE_ID_ONE = 1L;
    public static final Long EMPLOYEE_ID_TWO = 2L;
    public static final String EMPLOYEE_CODE_1 = "176034f6-4e44-482a-89a6-a328d83506a5";
    public static final String EMPLOYEE_CODE_2 = "276034f6-4e44-482a-89a6-a328d83506a5";
    public static final String IMAGE_URL_1 = "https://static.wikia.nocookie.net/jamesbond/images/8/86/Bond_-_Roger_Moore_-_Profile.jpg/revision/latest?cb=20130217201358";
    public static final String IMAGE_URL_2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4t7RnfJJBkD8c03LnZG8MelpJDJPwDtDm3Q&usqp=CAU";
    public static final String PHONE_NUMBER_1 = "123456789";
    public static final String PHONE_NUMBER_2 = "987654321";
    public static final String JOB_TITLE_1 = "Actor";
    public static final String JOB_TITLE_2 = "Secretary";
    public static final String NAME_1 = "Jan Kowalski";
    public static final String NAME_2 = "Tomasz Nowak";
    public static final String EMAIL_1 = "kowalski@kowalski.com";
    public static final String EMAIL_2 = "nowak@nowak.com";

    public static Employee createEmployee() {
        return Employee.builder()
                .id(EMPLOYEE_ID_ONE)
                .employeeCode(EMPLOYEE_CODE_1)
                .imageUrl(IMAGE_URL_1)
                .phone(PHONE_NUMBER_1)
                .jobTitle(JOB_TITLE_1)
                .name(NAME_1)
                .email(EMAIL_1)
                .build();
    }

    public static Employee createEmployee2() {
        return Employee.builder()
                .id(EMPLOYEE_ID_TWO)
                .employeeCode(EMPLOYEE_CODE_2)
                .imageUrl(IMAGE_URL_2)
                .phone(PHONE_NUMBER_2)
                .jobTitle(JOB_TITLE_2)
                .name(NAME_2)
                .email(EMAIL_2)
                .build();
    }

    public static EmployeeForm createEmployeeForm() {
        return EmployeeForm.builder()
                .id(EMPLOYEE_ID_ONE)
                .imageUrl(IMAGE_URL_2)
                .phone(PHONE_NUMBER_2)
                .jobTitle(JOB_TITLE_2)
                .name(NAME_2)
                .email(EMAIL_2)
                .build();
    }

    public static EmployeeForm createEmployeeFormToAddEmployee() {
        EmployeeForm employeeForm = createEmployeeForm();
        employeeForm.setId(null);
        return employeeForm;
    }

    public static List<Employee> createEmployeesList() {
        List<Employee> employees = new ArrayList<>();

        Employee employee1 = createEmployee();
        Employee employee2 = createEmployee();

        employee2.setId(EMPLOYEE_ID_TWO);
        employee2.setName(NAME_2);

        employees.add(employee1);
        employees.add(employee2);

        return employees;
    }

    public static EmployeeListDto createEmployeeListDto() {
        List<EmployeeDto> dtos = new ArrayList<>();

        dtos.add(createEmployeeDto1());
        dtos.add(createEmployeeDto2());

        return EmployeeListDto.builder()
                .employees(dtos)
                .build();
    }

    public static EmployeeDto createEmployeeDto1() {
        return EmployeeDto.builder()
                .id(EMPLOYEE_ID_ONE)
                .employeeCode(EMPLOYEE_CODE_1)
                .imageUrl(IMAGE_URL_1)
                .phone(PHONE_NUMBER_1)
                .jobTitle(JOB_TITLE_1)
                .name(NAME_1)
                .email(EMAIL_1)
                .build();
    }
    private static EmployeeDto createEmployeeDto2() {
        return EmployeeDto.builder()
                .id(EMPLOYEE_ID_TWO)
                .employeeCode(EMPLOYEE_CODE_2)
                .imageUrl(IMAGE_URL_2)
                .phone(PHONE_NUMBER_2)
                .jobTitle(JOB_TITLE_2)
                .name(NAME_2)
                .email(EMAIL_2)
                .build();
    }
}
