package com.sg.classroster.dao;

public class ClassRosterAuditDAOStubImpl implements ClassRosterAuditDAO {

    // Since we test to a stubbed Roster DAO implementation, we do not need the testing Audit DAO to do anything

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {

    }
}
