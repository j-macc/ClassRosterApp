package com.sg.classroster.dao;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassRosterDAOStubImpl implements ClassRosterDao {

    public Student onlyStudent;

    public ClassRosterDAOStubImpl() {
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("A");
        onlyStudent.setLastName("Kon");
        onlyStudent.setCohort("Rap-2004");
    }

    public ClassRosterDAOStubImpl(Student testStudent){
        this.onlyStudent = testStudent;
    }

    @Override
    public Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException {
        if (studentID.equals(onlyStudent.getStudentID())){
            return onlyStudent;
        } else{
            return null;
        }
    }

    @Override
    public Student getStudent(String studentID) throws ClassRosterPersistenceException{
        if (studentID.equals(onlyStudent.getStudentID())){
            return onlyStudent;
        } else{
            return null;
        }
    }

    @Override
    public Student removeStudent(String studentID) throws ClassRosterPersistenceException{
        if (studentID.equals(onlyStudent.getStudentID())){
            return onlyStudent;
        } else{
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException{
        List<Student> studentList = new ArrayList<>();
        studentList.add(onlyStudent);
        return studentList;
    }

}
