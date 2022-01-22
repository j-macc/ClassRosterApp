package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;

import java.util.List;

public interface ClassRosterDao {

    /**
     * Adds the given Student to the roster and associates it with the given student ID.
     * If there is already a student associated with the given ID it will return that
     * student object, otherwise returns null.
     *
     * @param studentID id with which student is to be associated
     * @param student student to be added to the roster
     * @return the Student object previously associated with the given ID if exists,
     * otherwise null
     */
    Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException;

    /**
     * Returns a List of all students in the roster.
     *
     * @return List containing all students in the roster.
     */
    List<Student> getAllStudents() throws ClassRosterPersistenceException;

    /**
     * Returns the student object associated with the given student ID.
     * Returns null if no such student exists.
     *
     * @param studentID id of the student to retrieve
     * @return the Student object associated with the given ID if exists, otherwise null
     */
    Student getStudent(String studentID) throws ClassRosterPersistenceException;

    /**
     * Removes from the roster the student associated with the given ID.
     * Returns the student object that is being removed or null if there is no student
     * associated with the given ID.
     *
     * @param studentID id of student to be removed
     * @return Student object that was removed if exists, otherwise null
     */
    Student removeStudent(String studentID) throws ClassRosterPersistenceException;

}
