package com.sg.classroster.service;

import com.sg.classroster.dao.*;
import com.sg.classroster.dto.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class ClassRosterServiceLayerImplTest {

    private ClassRosterServiceLayer service;

    public ClassRosterServiceLayerImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", ClassRosterServiceLayerImpl.class);
    }

    @Test
    public void testCreateValidStudent() {
        Student student = new Student("0002");
        student.setFirstName("Bill");
        student.setLastName("Withers");
        student.setCohort("Disco-1980");

        try{
            service.createStudent(student);
        } catch (ClassRosterPersistenceException
                | ClassRosterDataValidationException
                | ClassRosterDuplicateIDException e) {
            fail("student was valid - no exception should have been thrown");
        }
    }

    @Test
    public void testCreateDuplicateIDStudent() {
        Student student = new Student("0001");
        student.setFirstName("A");
        student.setLastName("Kon");
        student.setCohort("Rap-2004");

        try{
            service.createStudent(student);
            System.out.println(service.getAllStudents().toString());
            fail("expected duplicateID exception not thrown");
        } catch (ClassRosterDataValidationException
                | ClassRosterPersistenceException e){
            fail("incorrect exception thrown");
        } catch (ClassRosterDuplicateIDException e){
            return;
        }
    }

    @Test
    public void testCreateStudentInvalidData() throws Exception{
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Withers");
        student.setCohort("Disco-1980");

        try{
            service.createStudent(student);
            fail("expected validation exception not thrown");
        } catch (ClassRosterDuplicateIDException | ClassRosterPersistenceException e){
            fail("incorrect exception thrown");
        } catch (ClassRosterDataValidationException e){
            return;
        }
    }

    @Test
    public void testGetAllStudents() throws Exception{
        Student testClone = new Student("0001");
        testClone.setFirstName("A");
        testClone.setLastName("Kon");
        testClone.setCohort("Rap-2004");

        assertEquals(1, service.getAllStudents().size(), "should only have one student");
        assertTrue(service.getAllStudents().contains(testClone), "the one student should be A Kon");
    }

    @Test
    public void testGetStudent() throws Exception{
        Student testClone = new Student("0001");
        testClone.setFirstName("A");
        testClone.setLastName("Kon");
        testClone.setCohort("Rap-2004");

        Student shouldBeAKon = service.getStudent("0001");
        assertNotNull(shouldBeAKon, "getting student '0001' should not give 'null'");
        assertEquals(testClone, shouldBeAKon,"student with id '0001' should be A Kon");

        Student shouldBeNull = service.getStudent("0042");
        assertNull(shouldBeNull,"getting student '0042' should give back 'null'");
    }

    @Test
    public void testRemoveStudent() throws Exception{
        Student testClone = new Student("0001");
        testClone.setFirstName("A");
        testClone.setLastName("Kon");
        testClone.setCohort("Rap-2004");

        Student shouldBeAKon = service.removeStudent("0001");
        assertNotNull(shouldBeAKon, "removing student with id '0001' should not be null");
        assertEquals(testClone,shouldBeAKon,"removing student with id '0001' should remove A Kon");

        Student shouldBeNull = service.getStudent("0042");
        assertNull(shouldBeNull,"removing student '0042' should give back 'null'");
    }

}