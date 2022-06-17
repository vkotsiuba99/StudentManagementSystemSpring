package com.sms.studentmanagementsystem.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StudentTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Student#Student()}
     *   <li>{@link Student#setEmail(String)}
     *   <li>{@link Student#setFirstName(String)}
     *   <li>{@link Student#setId(Long)}
     *   <li>{@link Student#setLastName(String)}
     *   <li>{@link Student#getEmail()}
     *   <li>{@link Student#getFirstName()}
     *   <li>{@link Student#getId()}
     *   <li>{@link Student#getLastName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Student actualStudent = new Student();
        actualStudent.setEmail("jane.doe@example.org");
        actualStudent.setFirstName("Jane");
        actualStudent.setId(123L);
        actualStudent.setLastName("Doe");
        assertEquals("jane.doe@example.org", actualStudent.getEmail());
        assertEquals("Jane", actualStudent.getFirstName());
        assertEquals(123L, actualStudent.getId().longValue());
        assertEquals("Doe", actualStudent.getLastName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Student#Student(String, String, String)}
     *   <li>{@link Student#setEmail(String)}
     *   <li>{@link Student#setFirstName(String)}
     *   <li>{@link Student#setId(Long)}
     *   <li>{@link Student#setLastName(String)}
     *   <li>{@link Student#getEmail()}
     *   <li>{@link Student#getFirstName()}
     *   <li>{@link Student#getId()}
     *   <li>{@link Student#getLastName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Student actualStudent = new Student("Jane", "Doe", "jane.doe@example.org");
        actualStudent.setEmail("jane.doe@example.org");
        actualStudent.setFirstName("Jane");
        actualStudent.setId(123L);
        actualStudent.setLastName("Doe");
        assertEquals("jane.doe@example.org", actualStudent.getEmail());
        assertEquals("Jane", actualStudent.getFirstName());
        assertEquals(123L, actualStudent.getId().longValue());
        assertEquals("Doe", actualStudent.getLastName());
    }
}

