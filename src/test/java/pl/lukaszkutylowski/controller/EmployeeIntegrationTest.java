package pl.lukaszkutylowski.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.lukaszkutylowski.dto.EmployeeDto;
import pl.lukaszkutylowski.dto.EmployeeListDto;
import pl.lukaszkutylowski.form.EmployeeForm;
import pl.lukaszkutylowski.mapper.EmployeeMapper;
import pl.lukaszkutylowski.model.Employee;
import pl.lukaszkutylowski.repository.EmployeeRepository;
import pl.lukaszkutylowski.service.EmployeeService;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.lukaszkutylowski.data.EmployeeDataModelTestCreator.*;

@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = {EmployeeController.class, EmployeeService.class})
@MockBeans(@MockBean(classes = {EmployeeMapper.class, EmployeeRepository.class}))
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeRepository employeeRepository;
    @MockBean
    private EmployeeMapper employeeMapper;

    @Test
    void shouldGetEmployeeByIdTest() throws Exception {
        // given
        Employee employeeById = createEmployee();
        EmployeeDto dto = createEmployeeDto1();
        Mockito.when(employeeRepository.findEmployeeById(anyLong())).thenReturn(java.util.Optional.ofNullable(employeeById));
        Mockito.when(employeeMapper.toDto(employeeById)).thenReturn(dto);
        Long id = 1L;

        //when // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/find/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo(NAME_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeCode", equalTo(EMPLOYEE_CODE_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", equalTo(EMAIL_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", equalTo(PHONE_NUMBER_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl", equalTo(IMAGE_URL_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobTitle", equalTo(JOB_TITLE_1)));
    }

    @Test
    void shouldGetAllEmployeesTest() throws Exception {
        // given
        List<Employee> employees = createEmployeesList();
        EmployeeListDto employeeListDto = createEmployeeListDto();
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        Mockito.when(employeeMapper.toListDto(employees)).thenReturn(employeeListDto);

        //when // then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[0].id", equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[0].name", equalTo(NAME_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[0].employeeCode", equalTo(EMPLOYEE_CODE_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[0].email", equalTo(EMAIL_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[0].phone", equalTo(PHONE_NUMBER_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[0].imageUrl", equalTo(IMAGE_URL_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[0].jobTitle", equalTo(JOB_TITLE_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[1].id", equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[1].name", equalTo(NAME_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[1].employeeCode", equalTo(EMPLOYEE_CODE_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[1].email", equalTo(EMAIL_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[1].phone", equalTo(PHONE_NUMBER_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[1].imageUrl", equalTo(IMAGE_URL_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[1].jobTitle", equalTo(JOB_TITLE_2)));
    }

    @Test
    void shouldAddEmployeeTest() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeForm form = createEmployeeForm();
        Employee employee = createEmployee2();
        Mockito.when(employeeMapper.toEntity(form)).thenReturn(employee);

        //when // then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateEmployeeTest() throws Exception {
        // given
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeForm form = createEmployeeForm();
        Employee employee = createEmployee2();
        Mockito.when(employeeRepository.findEmployeeById(anyLong())).thenReturn(java.util.Optional.ofNullable(employee));
        Mockito.when(employeeMapper.entityUpdate(employee, form)).thenReturn(employee);

        //when // then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/employee/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteEmployeeTest() throws Exception {
        // given
        Long id = 1L;

        //when // then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employee/delete/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
