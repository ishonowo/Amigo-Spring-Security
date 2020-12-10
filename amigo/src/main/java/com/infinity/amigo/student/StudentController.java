package com.infinity.amigo.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/version1/students")
public class StudentController {
	
	private static final List<Student> STUDENTS=Arrays.asList(
			new Student(1,"James Bond"),
			new Student(2,"Jack Bower"),
			new Student(3,"Janet Pond")
			);
	
	
	@GetMapping(path="/{studentId}")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMINTRAINEE','ROLE_ADMIN')")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream().filter(student-> studentId.equals(student.getStudentId()))
				.findFirst().orElseThrow(()->new IllegalStateException("Student with id:"+studentId+" not found."))
				;
	}

}
