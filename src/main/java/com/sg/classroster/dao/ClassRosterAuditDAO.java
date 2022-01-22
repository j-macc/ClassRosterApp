package com.sg.classroster.dao;

public interface ClassRosterAuditDAO {

    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;

}
