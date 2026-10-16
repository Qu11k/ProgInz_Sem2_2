package lv.venta.service.impl;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
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
		if(newObject == null) {
			throw new Exception("Students nevar būt tukšs");
		}
		//TODO pārbaudīt newObject name, surname pēc regex, vai nav tukšs
		if(studRepo.existsByNameAndSurname(
				newObject.getName(), newObject.getSurname() )) {
			throw new Exception("Tāds students jau eksistē!");
		}
		
		studRepo.save(newObject);
		
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
		if(studRepo.count() == 0) {
			throw new Exception("Studentu tabula ir tukša un nevar rediģēt nevienu studentu");
		}
		
		if(id < 1) {
			throw new Exception("Id nevar būt negatīvs vai 0");
		}
		
		if(!studRepo.existsById(id)) {
			throw new Exception("Students ar id " + id + " neeksistē");
		}
		//TODO pārbaudīt name un surname uz not null, not empty un regex
		Student stud = studRepo.findById(id).get();
		stud.setName(name);
		stud.setSurname(surname);
		studRepo.save(stud);
	}

}
