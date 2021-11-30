package pl.lukaszkutylowski.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lukaszkutylowski.dto.EmployeeDto;
import pl.lukaszkutylowski.dto.EmployeeListDto;
import pl.lukaszkutylowski.form.EmployeeForm;
import pl.lukaszkutylowski.mapper.EmployeeMapper;
import pl.lukaszkutylowski.model.Employee;
import pl.lukaszkutylowski.repository.EmployeeRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static pl.lukaszkutylowski.data.EmployeeDataModelTestCreator.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(employeeRepository, employeeMapper);
    }

    @Test
    void shouldAddEmployeeTest() {
        // given
        EmployeeForm form = createEmployeeForm();
        Employee employee = createEmployee2();
        Mockito.when(employeeMapper.toEntity(form)).thenReturn(employee);

        // when
        employeeService.addEmployee(form);

        // then
        Mockito.verify(employeeRepository, Mockito.times(1))
                .save(employee);
    }

    @Test
    void shouldFindAllEmployeesTest() {
        // given
        List<Employee> employees = createEmployeesList();
        EmployeeListDto employeeListDto = createEmployeeListDto();
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        Mockito.when(employeeMapper.toListDto(employees)).thenReturn(employeeListDto);

        // when
        EmployeeListDto allEmployees = employeeService.findAllEmployees();
        EmployeeDto dto1 = allEmployees.getEmployees().get(0);
        EmployeeDto dto2 = allEmployees.getEmployees().get(1);

        // then
        Assertions.assertEquals(EMPLOYEE_ID_ONE, dto1.getId());
        Assertions.assertEquals(NAME_1, dto1.getName());
        Assertions.assertEquals(EMPLOYEE_CODE_1, dto1.getEmployeeCode());
        Assertions.assertEquals(EMAIL_1, dto1.getEmail());
        Assertions.assertEquals(PHONE_NUMBER_1, dto1.getPhone());
        Assertions.assertEquals(IMAGE_URL_1, dto1.getImageUrl());
        Assertions.assertEquals(JOB_TITLE_1, dto1.getJobTitle());

        Assertions.assertEquals(EMPLOYEE_ID_TWO, dto2.getId());
        Assertions.assertEquals(NAME_2, dto2.getName());
        Assertions.assertEquals(EMPLOYEE_CODE_2, dto2.getEmployeeCode());
        Assertions.assertEquals(EMAIL_2, dto2.getEmail());
        Assertions.assertEquals(PHONE_NUMBER_2, dto2.getPhone());
        Assertions.assertEquals(IMAGE_URL_2, dto2.getImageUrl());
        Assertions.assertEquals(JOB_TITLE_2, dto2.getJobTitle());
    }

    @Test
    void shouldUpdateEmployeeTest() {
        // given
        EmployeeForm form = createEmployeeForm();
        form.setEmail("example@example.com");
        form.setPhone("111222111");
        Employee employeeById = createEmployee2();
        Employee employeeUpdated = createEmployee2();
        Mockito.when(employeeMapper.entityUpdate(employeeById, form)).thenReturn(employeeUpdated);
        Mockito.when(employeeRepository.findEmployeeById(anyLong())).thenReturn(java.util.Optional.of(employeeById));

        // when
        employeeService.updateEmployee(form);

        // then
        Mockito.verify(employeeRepository, Mockito.times(1))
                .save(employeeUpdated);
    }

    @Test
    void shouldFindEmployeeByIdTest() {
        // given
        Employee employeeById = createEmployee();
        EmployeeDto dto = createEmployeeDto1();
        Mockito.when(employeeRepository.findEmployeeById(anyLong())).thenReturn(java.util.Optional.of(employeeById));
        Mockito.when(employeeMapper.toDto(employeeById)).thenReturn(dto);

        // when
        EmployeeDto expectedEmployeeById = employeeService.findEmployeeById(1L);

        // then
        Assertions.assertEquals(EMPLOYEE_ID_ONE, expectedEmployeeById.getId());
        Assertions.assertEquals(NAME_1, expectedEmployeeById.getName());
        Assertions.assertEquals(EMPLOYEE_CODE_1, expectedEmployeeById.getEmployeeCode());
        Assertions.assertEquals(EMAIL_1, expectedEmployeeById.getEmail());
        Assertions.assertEquals(PHONE_NUMBER_1, expectedEmployeeById.getPhone());
        Assertions.assertEquals(IMAGE_URL_1, expectedEmployeeById.getImageUrl());
        Assertions.assertEquals(JOB_TITLE_1, expectedEmployeeById.getJobTitle());
    }

    @Test
    void shouldDeleteEmployeeTest() {
        // given
        Long id = 1L;

        // when
        employeeService.deleteEmployee(id);

        // then
        Mockito.verify(employeeRepository, Mockito.times(1))
                .deleteEmployeeById(id);
    }
}
