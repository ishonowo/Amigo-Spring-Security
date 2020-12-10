package com.infinty.amigo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("/")
public class TemplateController {
	
	@GetMapping(path="/login")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMINTRAINEE','ROLE_ADMIN')")
	public String getLoginView() {
		return "login";
	}

	@GetMapping(path="courses")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMINTRAINEE','ROLE_ADMIN')")
	public String getCoursesView() {
		return "courses";
	}

}
