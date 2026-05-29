package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.enums.Degree;
import lv.venta.service.IFilterService;
@Controller
@RequestMapping("/filter")
public class filterStudentController {
	@Autowired
	private IFilterService filterService;
	@GetMapping("/grade/student/{id}") //localhost:8080/filter/grade/student/1
	public String getControllerGradesByStudentId(@PathVariable(name="id")long id, Model model) {
		try {
		model.addAttribute("package", filterService.filterGradesByStudentId(id));
		return "show-multiple-grades";
		}
		catch(Exception e){
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
		
	}
	@GetMapping("/grade/course/{title}")
	public String getControllerGradesByCourseTitle(@PathVariable(name="title")String title,Model model) {
		try {
			model.addAttribute("package", filterService.filterGradesByCourseTitle(title));
			return "show-multiple-grades";
		}
		catch(Exception e){
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
}
	@GetMapping("/course/professor/{degree}")
	public String getControllerCourseByProfessorDegree(@PathVariable(name = "degree") Degree degree, Model model) {
		try {
			model.addAttribute("package", filterService.filterCoursesByProfessorDegree(degree));
			return "show-multiple-courses";
		}
		catch(Exception e){
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
