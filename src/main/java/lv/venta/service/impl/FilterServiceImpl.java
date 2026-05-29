package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.model.enums.Degree;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IProfessorRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.Course;
import lv.venta.service.IFilterService;

public class FilterServiceImpl implements IFilterService{
@Autowired
private IStudentRepo studRepo;
private IGradeRepo gradeRepo;
private ICourseRepo courseRepo;
private IProfessorRepo profRepo;
	@Override
	public ArrayList<Grade> filterGradesByStudentId(long id) throws Exception {
		if(id<1) {
			throw new Exception("id nevar but negativs");
		}
		if(!studRepo.existsById(id)) {
			throw new Exception("students ar tadu id neeksiste");
		}
		if(gradeRepo.count()==0) {
			throw new Exception("nav studentu");
		}
		return null;
	}

	@Override
	public ArrayList<Grade> filterGradesByCourseTitle(String title) throws Exception {
		if(title == null|| title.isEmpty()) {
			throw new Exception("Atzimju tabula ir tuksa");
		}
		if (gradeRepo.count()==0) {
			throw new Exception("atzimju tabula ir tuska");
		}
		if (courseRepo.existsByTitle(title)) {
			throw new Exception("kurss ar nosaukumu" +title+"neeskiste");
		}
		ArrayList<Grade> results = gradeRepo.findByCourseTitle(title);
		if(results.isEmpty()) {
			throw new Exception("Nav neviena atzime kura ir piesaistita kursam");
		}
		return results;
	}

	@Override
	public ArrayList<lv.venta.model.Course> filterCoursesByProfessorDegree(Degree degree) throws Exception {
		if (degree== null) {
			throw new Exception("profesora grads nav ievadits korekti");
		}
		if (courseRepo.count()==0) {
			throw new Exception("kursa tabula ir tuksa");
		}
		if(!profRepo.existsBydegree(degree)) {
			throw new Exception("Profesori ar"+degree+"gradu neeksite");
		}
		ArrayList<lv.venta.model.Course> results=courseRepo.findByProfessorDegree(degree);
		if (results.isEmpty()) {
			throw new Exception("nav neviens kurss kurs butu piesaistits profesoram");
		}
		return results;
	}

	@Override
	public ArrayList<Student> filterStudentsFailed() throws Exception {
		if (gradeRepo.count()==0) {
		throw new Exception("Atzimju tabula ir tuksa");
		}
		if(studRepo.count()==0){
			throw new Exception("studentu tabula ir tuksa");
		}
		ArrayList<Student> results=studRepo.findByGradesGrvalueLessThan(4);
		if(results.isEmpty()) {
			throw new Exception("nav neviens students kuram butu nesekmiga atzime");
		}
		return results;
	}

}
