package com.sms.studentmanagementsystem.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sms.studentmanagementsystem.entity.Student;
import com.sms.studentmanagementsystem.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentServiceImplTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    /**
     * Method under test: {@link StudentServiceImpl#getAllStudents()}
     */
    @Test
    void testGetAllStudents() {
        ArrayList<Student> studentList = new ArrayList<>();
        when(this.studentRepository.findAll()).thenReturn(studentList);
        List<Student> actualAllStudents = this.studentServiceImpl.getAllStudents();
        assertSame(studentList, actualAllStudents);
        assertTrue(actualAllStudents.isEmpty());
        verify(this.studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentServiceImpl#saveStudent(Student)}
     */
    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");
        when(this.studentRepository.save((Student) any())).thenReturn(student);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setFirstName("Jane");
        student1.setId(123L);
        student1.setLastName("Doe");
        assertSame(student, this.studentServiceImpl.saveStudent(student1));
        verify(this.studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#getStudentById(Long)}
     */
    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");
        Optional<Student> ofResult = Optional.of(student);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(student, this.studentServiceImpl.getStudentById(123L));
        verify(this.studentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#updateStudent(Student)}
     */
    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");
        when(this.studentRepository.save((Student) any())).thenReturn(student);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setFirstName("Jane");
        student1.setId(123L);
        student1.setLastName("Doe");
        assertSame(student, this.studentServiceImpl.updateStudent(student1));
        verify(this.studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#deleteStudentById(Long)}
     */
    @Test
    void testDeleteStudentById() {
        doNothing().when(this.studentRepository).deleteById((Long) any());
        this.studentServiceImpl.deleteStudentById(123L);
        verify(this.studentRepository).deleteById((Long) any());
    }
}

