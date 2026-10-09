package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.model.Student;
import lv.venta.model.enums.Degree;
import lv.venta.service.IFilterService;
import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Professor;
@RestController
@RequestMapping("/filter")
public class filterStudentController {
	@Autowired
	private IFilterService filterService;
	@GetMapping("/grade/student/{id}") //localhost:8080/filter/grade/student/1
	public  ResponseEntity<?> getControllerGradesByStudentId(@PathVariable(name="id")long id) {
		try {
			return new ResponseEntity<ArrayList<Grade>>(filterService.filterGradesByStudentId(id),HttpStatusCode.valueOf(200));
		
		
		}
		catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatusCode.valueOf(409));
		}
		
	}
	@GetMapping("/grade/course/{title}")
	public ResponseEntity<?> getControllerGradesByCourseTitle(@PathVariable(name="title")String title) {
		try {
			return new ResponseEntity<ArrayList<Grade>>(filterService.filterGradesByCourseTitle(title),HttpStatusCode.valueOf(200));
		}
		catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatusCode.valueOf(409));
		}
}
	@GetMapping("/course/professor/{degree}")
	public ResponseEntity<?> getControllerCourseByProfessorDegree(@PathVariable(name = "degree") Degree degree) {
		try {
			return new ResponseEntity<ArrayList<Course>>(filterService.filterCoursesByProfessorDegree(degree),HttpStatusCode.valueOf(200));
		}
		catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatusCode.valueOf(409));
		}
	}
	@GetMapping("student/failed")
	public String getControllerFailedStudents(Model model) {
		try {
		model.addAttribute("package", filterService.filterStudentsFailed());
		return "show-multiple-students";
		}
		catch(Exception e){
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
