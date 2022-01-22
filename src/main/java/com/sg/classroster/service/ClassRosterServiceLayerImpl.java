package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterAuditDAO;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    private final ClassRosterDao dao;
    private final ClassRosterAuditDAO auditDao;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDAO auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createStudent(Student student) throws
            ClassRosterPersistenceException,
            ClassRosterDuplicateIDException,
            ClassRosterDataValidationException {

        if (dao.getStudent(student.getStudentID()) != null){
            throw new ClassRosterDuplicateIDException(
                    String.format("ERROR: Could not create student record; Student ID %s already exists",
                            student.getStudentID())
            );
        }
        validateStudentData(student);
        dao.addStudent(student.getStudentID(), student);
        auditDao.writeAuditEntry("Student: "+student.getStudentID()+" CREATED.");
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentID) throws ClassRosterPersistenceException {
        return dao.getStudent(studentID);
    }

    @Override
    public Student removeStudent(String studentID) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentID);
        if (removedStudent != null) {
            auditDao.writeAuditEntry("Student: "+studentID+" REMOVED.");
        }
        return removedStudent;

    }

    private void validateStudentData(Student student) throws ClassRosterDataValidationException{

        if (student.getFirstName() == null
            || student.getFirstName().trim().length() == 0
            || student.getLastName() == null
            || student.getLastName().trim().length() == 0
            || student.getCohort() == null
            || student.getCohort().trim().length() == 0){
            throw new ClassRosterDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }

    }

}
