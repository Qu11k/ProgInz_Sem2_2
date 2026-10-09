package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.model.Student;
import lv.venta.service.ICRUDStudentService;

@RestController
@RequestMapping("/students/crud")
public class CRUDStudentController {
	@Autowired
	private ICRUDStudentService studService;
@GetMapping("/all")
public ResponseEntity<?> getControllerAllStudents() {
	try {
		return new ResponseEntity<ArrayList<Student>>(studService.retrieveAll(),HttpStatusCode.valueOf(200));
		
	}
	catch(Exception e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatusCode.valueOf(409));
	}
}
@GetMapping("/delete/{id}")
public ResponseEntity<?> getControllerDeleteById(@PathVariable(name="id")long id) {
	try {
		studService.deleteByID(id);
		return new ResponseEntity<ArrayList<Student>>(studService.retrieveAll(),HttpStatusCode.valueOf(200));
}
	catch(Exception e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatusCode.valueOf(409));
	}
}

@PostMapping("/add")
public ResponseEntity<?> postController(@Valid @RequestBody Student student, 
		BindingResult result) {
	if(result.hasErrors()) {
		return new ResponseEntity<Integer>(result.getErrorCount(),HttpStatusCode.valueOf(409));
	}
	
	try
	{
		System.out.println(student);
		studService.create(student);
		return new ResponseEntity<ArrayList<Student>>(studService.retrieveAll(),HttpStatusCode.valueOf(200));
	}
	catch (Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatusCode.valueOf(409));
	}
}
}