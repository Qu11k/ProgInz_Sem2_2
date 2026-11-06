package lv.venta.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lv.venta.model.Student;
import lv.venta.service.ICRUDStudentService;

class StudentControllerTest {

	@Mock
	private ICRUDStudentService studService;
	@InjectMocks
	private CRUDStudentController studController;
	private MockMvc mockMVC;
	
	@BeforeEach
	public void initialize() {
		MockitoAnnotations.openMocks(this);
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		mockMVC= MockMvcBuilders.standaloneSetup(studController).setValidator(validator).build();
	}
	@Test
public void testAllControllers() {
	Student st1= new Student("Martins", "Brokans");
	Student st2 = new Student("Margrieta","Mare");
	ArrayList<Student> testStudents= new ArrayList<>();
	testStudents.addAll(Arrays.asList(st1,st2));
	try {
		when(studService.retrieveAll()).thenReturn(testStudents);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	try {
	mockMVC.perform(get("student/crud/all")).andExpect(status().isOk());}
//	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
	catch(Exception e) {
		e.printStackTrace();
	}
}
}
