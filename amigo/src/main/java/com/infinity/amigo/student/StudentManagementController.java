package com.infinity.amigo.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/version1/students")
public class StudentManagementController {

/*	private List<Student> STUDENTS=Arrays.asList(
			new Student(1,"James Bond"),
			new Student(2,"Jack Bower"),
			new Student(3,"Janet Pond")
			);*/
	
	private List<Student> STUDENTS = new ArrayList<Student>(Arrays.asList(
			new Student(1,"James Bond"),
			new Student(2,"Jack Bower"),
			new Student(3,"Janet Pond")
			));
	
	@GetMapping(path="/all")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMINTRAINEE','ROLE_ADMIN')")
	public List<Student> getAllStudents(){
		return STUDENTS;
	}
	
	@PostMapping(path="/add")
	@PreAuthorize("hasAuthority('student:read')")
	public void registerStudent(@RequestBody Student student) {
		System.out.println(student);
		STUDENTS.add(student);
		STUDENTS.stream().forEach(System.out::println);
	}
	
	@DeleteMapping(path="/del/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("id: "+studentId);
		STUDENTS.removeIf(student -> student.getStudentId().equals(studentId));
		STUDENTS.stream().forEach(System.out::println);
	}
	
	@PutMapping(path="update/{studentId}/{name}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @PathVariable("name") String name) {
		System.out.println("id: "+studentId+", name: "+name);
		STUDENTS.removeIf(student -> student.getStudentId().equals(studentId));
		Student newStudent= new Student(studentId,name);
		//STUDENTS.set(studentId, newStudent);
		STUDENTS.add(newStudent);
		STUDENTS.stream().forEach(System.out::println);
	}
}
