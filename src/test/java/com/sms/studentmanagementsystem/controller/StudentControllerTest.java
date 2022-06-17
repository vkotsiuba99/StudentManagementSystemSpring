package com.sms.studentmanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sms.studentmanagementsystem.entity.Student;
import com.sms.studentmanagementsystem.repository.StudentRepository;
import com.sms.studentmanagementsystem.service.StudentService;
import com.sms.studentmanagementsystem.service.impl.StudentServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link StudentController#createStudentForm(org.springframework.ui.Model)}
     */
    @Test
    void testCreateStudentForm() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/new");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.view().name("create_student"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("create_student"));
    }

    /**
     * Method under test: {@link StudentController#listStudents(org.springframework.ui.Model)}
     */
    @Test
    void testListStudents() {
        StudentRepository studentRepository = mock(StudentRepository.class);
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());
        StudentController studentController = new StudentController(new StudentServiceImpl(studentRepository));
        assertEquals("students", studentController.listStudents(new ConcurrentModel()));
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentController#saveStudent(Student)}
     */
    @Test
    void testSaveStudent() throws Exception {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");
        when(this.studentService.saveStudent((Student) any())).thenReturn(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }

    /**
     * Method under test: {@link StudentController#saveStudent(Student)}
     */
    @Test
    void testSaveStudent2() throws Exception {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");
        when(this.studentService.saveStudent((Student) any())).thenReturn(student);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/students");
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }

    /**
     * Method under test: {@link StudentController#updateStudent(Long, Student, org.springframework.ui.Model)}
     */
    @Test
    void testUpdateStudent() throws Exception {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setFirstName("Jane");
        student1.setId(123L);
        student1.setLastName("Doe");
        when(this.studentService.updateStudent((Student) any())).thenReturn(student1);
        when(this.studentService.getStudentById((Long) any())).thenReturn(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/students/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }

    /**
     * Method under test: {@link StudentController#updateStudent(Long, Student, org.springframework.ui.Model)}
     */
    @Test
    void testUpdateStudent2() throws Exception {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setFirstName("Jane");
        student1.setId(123L);
        student1.setLastName("Doe");
        when(this.studentService.updateStudent((Student) any())).thenReturn(student1);
        when(this.studentService.getStudentById((Long) any())).thenReturn(student);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/students/{id}", 123L);
        postResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }

    /**
     * Method under test: {@link StudentController#createStudentForm(org.springframework.ui.Model)}
     */
    @Test
    void testCreateStudentForm2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/new", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.view().name("create_student"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("create_student"));
    }

    /**
     * Method under test: {@link StudentController#deleteStudent(Long)}
     */
    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(this.studentService).deleteStudentById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }

    /**
     * Method under test: {@link StudentController#deleteStudent(Long)}
     */
    @Test
    void testDeleteStudent2() throws Exception {
        doNothing().when(this.studentService).deleteStudentById((Long) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/students/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/students"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/students"));
    }

    /**
     * Method under test: {@link StudentController#deleteStudent(Long)}
     */
    @Test
    void testDeleteStudent3() throws Exception {
        when(this.studentService.getAllStudents()).thenReturn(new ArrayList<>());
        doNothing().when(this.studentService).deleteStudentById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/{id}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.view().name("students"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("students"));
    }

    /**
     * Method under test: {@link StudentController#deleteStudent(Long)}
     */
    @Test
    void testDeleteStudent4() throws Exception {
        when(this.studentService.getAllStudents()).thenReturn(new ArrayList<>());
        doNothing().when(this.studentService).deleteStudentById((Long) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/students/{id}", "", "Uri Vars");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.view().name("students"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("students"));
    }

    /**
     * Method under test: {@link StudentController#editStudentForm(Long, org.springframework.ui.Model)}
     */
    @Test
    void testEditStudentForm() throws Exception {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");
        when(this.studentService.getStudentById((Long) any())).thenReturn(student);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/edit/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.view().name("edit_student"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("edit_student"));
    }

    /**
     * Method under test: {@link StudentController#editStudentForm(Long, org.springframework.ui.Model)}
     */
    @Test
    void testEditStudentForm2() throws Exception {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(123L);
        student.setLastName("Doe");
        when(this.studentService.getStudentById((Long) any())).thenReturn(student);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/students/edit/{id}", 123L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.view().name("edit_student"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("edit_student"));
    }
}

