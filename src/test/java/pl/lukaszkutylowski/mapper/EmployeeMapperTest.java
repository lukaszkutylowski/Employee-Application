package pl.lukaszkutylowski.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lukaszkutylowski.dto.EmployeeDto;
import pl.lukaszkutylowski.dto.EmployeeListDto;
import pl.lukaszkutylowski.form.EmployeeForm;
import pl.lukaszkutylowski.model.Employee;

import java.util.ArrayList;
import java.util.List;

import static pl.lukaszkutylowski.data.EmployeeDataModelTestCreator.*;

class EmployeeMapperTest {
    @Autowired
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        employeeMapper = new EmployeeMapper();
    }

    @Test
    void shouldEntityUpdateTest() {
        // given
        Employee employeeToUpdate = createEmployee();
        EmployeeForm form = createEmployeeForm();

        // when
        Employee employeeUpdated = employeeMapper.entityUpdate(employeeToUpdate, form);

        // then
        Assertions.assertEquals(EMPLOYEE_ID_ONE, employeeUpdated.getId());
        Assertions.assertEquals(EMPLOYEE_CODE_1, employeeUpdated.getEmployeeCode());
        Assertions.assertEquals(NAME_2, employeeUpdated.getName());
        Assertions.assertEquals(IMAGE_URL_2, employeeUpdated.getImageUrl());
        Assertions.assertEquals(PHONE_NUMBER_2, employeeUpdated.getPhone());
        Assertions.assertEquals(JOB_TITLE_2, employeeUpdated.getJobTitle());
        Assertions.assertEquals(EMAIL_2, employeeUpdated.getEmail());
    }

    @Test
    void shouldNullableEntityUpdateTest() {
        // given
        Employee employeeEmpty = new Employee();
        EmployeeForm formEmpty = EmployeeForm.builder().build();
        Employee employeeNull = null;
        EmployeeForm formNull = null;

        // when // then
        employeeMapper.entityUpdate(employeeEmpty, formEmpty);
        employeeMapper.entityUpdate(employeeNull, formNull);
    }

    @Test
    void shouldToEntityTest() {
        // given
        EmployeeForm form = createEmployeeFormToAddEmployee();

        // when
        Employee employeeFromForm = employeeMapper.toEntity(form);

        // then
        Assertions.assertNull(employeeFromForm.getId());
        Assertions.assertNull(employeeFromForm.getEmployeeCode());
        Assertions.assertEquals(NAME_2, employeeFromForm.getName());
        Assertions.assertEquals(EMAIL_2, employeeFromForm.getEmail());
        Assertions.assertEquals(PHONE_NUMBER_2, employeeFromForm.getPhone());
        Assertions.assertEquals(JOB_TITLE_2, employeeFromForm.getJobTitle());
        Assertions.assertEquals(IMAGE_URL_2, employeeFromForm.getImageUrl());
    }

    @Test
    void shouldNullableToEntityTest() {
        // given
        EmployeeForm formEmpty = EmployeeForm.builder().build();
        EmployeeForm formNull = null;

        // when // then
        employeeMapper.toEntity(formEmpty);
        employeeMapper.toEntity(formNull);
    }

    @Test
    void shouldToDtoTest() {
        // given
        Employee employeeToDto = createEmployee();

        // when
        EmployeeDto dto = employeeMapper.toDto(employeeToDto);

        // then
        Assertions.assertEquals(EMPLOYEE_ID_ONE, dto.getId());
        Assertions.assertEquals(NAME_1, dto.getName());
        Assertions.assertEquals(EMAIL_1, dto.getEmail());
        Assertions.assertEquals(JOB_TITLE_1, dto.getJobTitle());
        Assertions.assertEquals(PHONE_NUMBER_1, dto.getPhone());
        Assertions.assertEquals(IMAGE_URL_1, dto.getImageUrl());
        Assertions.assertEquals(EMPLOYEE_CODE_1, dto.getEmployeeCode());
    }

    @Test
    void shouldNullableToDtoTest() {
        // given
        Employee employeeEmpty = new Employee();
        Employee employeeNull = null;

        // when // then
        employeeMapper.toDto(employeeEmpty);
        employeeMapper.toDto(employeeNull);
    }

    @Test
    void shouldToDtoListTest() {
        // given
        List<Employee> employees = createEmployeesList();

        // when
        EmployeeListDto employeeListDto = employeeMapper.toListDto(employees);
        List<EmployeeDto> employeesList = employeeListDto.getEmployees();
        EmployeeDto employeeDto1 = employeesList.get(0);
        EmployeeDto employeeDto2 = employeesList.get(1);

        // then
        Assertions.assertEquals(EMPLOYEE_ID_ONE, employeeDto1.getId());
        Assertions.assertEquals(NAME_1, employeeDto1.getName());
        Assertions.assertEquals(EMAIL_1, employeeDto1.getEmail());
        Assertions.assertEquals(JOB_TITLE_1, employeeDto1.getJobTitle());
        Assertions.assertEquals(PHONE_NUMBER_1, employeeDto1.getPhone());
        Assertions.assertEquals(IMAGE_URL_1, employeeDto1.getImageUrl());
        Assertions.assertEquals(EMPLOYEE_CODE_1, employeeDto1.getEmployeeCode());

        Assertions.assertEquals(EMPLOYEE_ID_TWO, employeeDto2.getId());
        Assertions.assertEquals(NAME_2, employeeDto2.getName());
        Assertions.assertEquals(EMAIL_1, employeeDto2.getEmail());
        Assertions.assertEquals(JOB_TITLE_1, employeeDto2.getJobTitle());
        Assertions.assertEquals(PHONE_NUMBER_1, employeeDto2.getPhone());
        Assertions.assertEquals(IMAGE_URL_1, employeeDto2.getImageUrl());
        Assertions.assertEquals(EMPLOYEE_CODE_1, employeeDto2.getEmployeeCode());
    }

    @Test
    void shouldNullableToDtoListTest() {
        // given
        List<Employee> employeesEmpty = new ArrayList<>();
        List<Employee> employeesNull = null;

        // when // then
        employeeMapper.toListDto(employeesEmpty);
        employeeMapper.toListDto(employeesNull);
    }
}
