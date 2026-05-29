package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.ICRUDStudentService;

@Service
public class CRUDStudentServiceImpl implements ICRUDStudentService {
	@Autowired
private IStudentRepo studRepo;
	@Autowired
private IGradeRepo gradeRepo;
	@Override
	public void create(Student newObject) throws Exception {
		
	}

	@Override
	public ArrayList<Student> retrieveAll() throws Exception {
		if(studRepo.count()==0) {
			throw new Exception("studentu tabula ir tuksa");
		}
		return (ArrayList<Student>)studRepo.findAll();
			
		}

	@Override
	public Student retrieveById(long id) throws Exception {
		if (studRepo.count()==0) {
		throw new Exception("studentu tabula ir tuksa");
		}
		if(id<1) {
			throw new Exception("id nevar negativs");
		}
		if(!studRepo.existsById(id)) {
			throw new Exception();
	}
		return studRepo.findById(id).get();
		}

	@Override
	public void deleteByID(long id) throws Exception {
		Student studentsForDelete = retrieveById(id);
		ArrayList<Grade> allGradesForStudent = gradeRepo.findByStudentIds(id);
		for(Grade tempG:allGradesForStudent) {
			tempG.setStudent(null);
			gradeRepo.save(tempG);
		}
		studRepo.delete(studentsForDelete);
	}

	@Override
	public void updateById(long id, String name, String surname) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
