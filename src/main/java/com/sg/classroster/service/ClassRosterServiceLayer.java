package com.sg.classroster.service;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;

import java.util.List;

public interface ClassRosterServiceLayer {

    void createStudent(Student student) throws
            ClassRosterPersistenceException,
            ClassRosterDuplicateIDException,
            ClassRosterDataValidationException;

    List<Student> getAllStudents() throws ClassRosterPersistenceException;

    Student getStudent(String studentID) throws ClassRosterPersistenceException;

    Student removeStudent(String studentID) throws ClassRosterPersistenceException;

}
