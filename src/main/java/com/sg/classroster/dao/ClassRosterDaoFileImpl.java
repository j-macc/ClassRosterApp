package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;

import java.io.*;
import java.util.*;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private final String ROSTER_FILE;
    public static final String DELIMITER = "::";

    private Map<String, Student> students = new HashMap<>();

    public ClassRosterDaoFileImpl(String rosterTextFile){
        ROSTER_FILE = rosterTextFile;
    }

    @Override
    public Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException {
        loadRoster();
        Student newStudent = students.put(studentID, student);
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        loadRoster();
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudent(String studentID) throws ClassRosterPersistenceException {
        loadRoster();
        return students.get(studentID);
    }

    @Override
    public Student removeStudent(String studentID) throws ClassRosterPersistenceException {
        loadRoster();
        Student removedStudent = students.remove(studentID);
        writeRoster();
        return removedStudent;
    }

    private Student unmarshallStudent(String studentAsText){

        // expecting file input of the form:
        // <studentID>::<firstName>::<lastName>::<cohort>

        String[] studentInfo = studentAsText.split(DELIMITER);

        String studentID = studentInfo[0];
        Student studentFromFile = new Student(studentID);
        studentFromFile.setFirstName(studentInfo[1]);
        studentFromFile.setLastName(studentInfo[2]);
        studentFromFile.setCohort(studentInfo[3]);

        return studentFromFile;
    }

    /**
     * Reads records in ROSTER_FILE and stores them in students object.
     *
     * @throws ClassRosterPersistenceException if an error occurs reading the file
     */
    private void loadRoster() throws ClassRosterPersistenceException {
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader((new FileReader(ROSTER_FILE))));
        }
        catch (FileNotFoundException e){
            throw new ClassRosterPersistenceException("Could not load roster data into memory.", e);
        }

        String currentLine;
        Student currentStudent;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentStudent = unmarshallStudent(currentLine);
            students.put(currentStudent.getStudentID(), currentStudent);
        }
        scanner.close();
    }

    private String marshallStudent(Student aStudent) {

        // create student record in the format expected to be read in

        String studentAsText = aStudent.getStudentID() + DELIMITER;
        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort();
        return studentAsText;

    }

    /**
     * Writes all current student records to the ROSTER_FILE
     *
     * @throws ClassRosterPersistenceException if an error occurs writing to the file
     */
    private void writeRoster() throws ClassRosterPersistenceException {

        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        }
        catch (IOException e){
            throw new ClassRosterPersistenceException("Could not save student data.", e);
        }

        String studentAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList){
            studentAsText = marshallStudent(currentStudent);
            out.println(studentAsText);
            out.flush();
        }
        out.close();

    }

}
