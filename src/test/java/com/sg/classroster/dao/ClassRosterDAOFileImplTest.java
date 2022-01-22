package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassRosterDAOFileImplTest {

    ClassRosterDao testDAO;

    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testroster.txt";
        new FileWriter(testFile);
        testDAO = new ClassRosterDaoFileImpl(testFile);
    }

    @Test
    public void testAddGetStudent() throws Exception {
        String studentID = "0001";
        Student student = new Student(studentID);
        student.setFirstName("A");
        student.setLastName("Kon");
        student.setCohort("Rap-2004");

        testDAO.addStudent(studentID, student);
        Student retrievedStudent = testDAO.getStudent(studentID);

        assertEquals(student.getStudentID(), retrievedStudent.getStudentID(), "student ID check");
        assertEquals(student.getFirstName(), retrievedStudent.getFirstName(), "student first name check");
        assertEquals(student.getLastName(), retrievedStudent.getLastName(), "student last name check");
        assertEquals(student.getCohort(), student.getCohort(), "student cohort check");
    }

    @Test
    public void testAddGetAllStudents() throws Exception {
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("A");
        firstStudent.setLastName("Kon");
        firstStudent.setCohort("Rap-2004");
        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Bill");
        secondStudent.setLastName("Withers");
        secondStudent.setCohort("Disco-1980");

        testDAO.addStudent(firstStudent.getStudentID(), firstStudent);
        testDAO.addStudent(secondStudent.getStudentID(), secondStudent);
        List<Student> allStudents = testDAO.getAllStudents();

        assertNotNull(allStudents, "the list of students must not be null");
        assertEquals(2, allStudents.size(), "list should have 2 students");
        assertTrue(testDAO.getAllStudents().contains(firstStudent), "the list should include A Kon");
        assertTrue(testDAO.getAllStudents().contains(secondStudent), "the list should include Bill Withers");
    }

    @Test
    public void testRemoveSudent() throws Exception {
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");
        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        testDAO.addStudent(firstStudent.getStudentID(), firstStudent);
        testDAO.addStudent(secondStudent.getStudentID(), secondStudent);

        Student removedStudent = testDAO.removeStudent(firstStudent.getStudentID());
        assertEquals(removedStudent, firstStudent, "the removed student should be Ada");
        List<Student> allStudents = testDAO.getAllStudents();
        assertNotNull(allStudents, "student list should not be null");
        assertEquals(1, allStudents.size(), "there should be one student in the list");
        assertFalse(allStudents.contains(firstStudent), "Ada should not be on the list");
        assertTrue(allStudents.contains(secondStudent), "Charles should be on the list");

        removedStudent = testDAO.removeStudent(secondStudent.getStudentID());
        assertEquals(removedStudent, secondStudent, "the removed student should be Charles");
        allStudents = testDAO.getAllStudents();
        assertTrue(allStudents.isEmpty(), "the student list should now be empty");

        Student retrievedStudent = testDAO.getStudent(firstStudent.getStudentID());
        assertNull(retrievedStudent, "Ada removed, should be null");
        retrievedStudent = testDAO.getStudent(secondStudent.getStudentID());
        assertNull(retrievedStudent, "Charles removed, should be null");
    }

}