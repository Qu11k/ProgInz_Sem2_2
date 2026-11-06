package lv.venta.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lv.venta.model.Student;

class StudentTest {
	@Test
	public void testStudentConstructor(){
		Student student = new Student ("Martins","Brokans");
		assertEquals("Martins", student.getName());
		assertEquals("Brokans", student.getSurname());
	}
	@Test
	public void testToString() {
		Student student= new Student("Martins","Brokans");
		assertNotNull(student.toString());
	}
	@Test
	public void testDefaultConstructor() {
		Student student = new Student();
		student.setName("Martins");
		student.setSurname("Brokans");
		assertEquals("Martins",student.getName());
		assertEquals("Brokans",student.getSurname());
	}

}
