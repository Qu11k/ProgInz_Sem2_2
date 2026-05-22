package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Grade;
import lv.venta.model.enums.Degree;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.Course;
import lv.venta.service.IFilterService;

public class FilterServiceImpl implements IFilterService{
@Autowired
private IStudentRepo studRepo;
private IGradeRepo gradeRepo;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<lv.venta.model.Course> filterCoursesByProfessorDegree(Degree degree) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
