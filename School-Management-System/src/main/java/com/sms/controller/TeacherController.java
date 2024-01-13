package com.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sms.entity.Teacher;
import com.sms.service.TeacherService;

@Controller
public class TeacherController {

	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		super();
		this.teacherService = teacherService;
	}

	// handler method to handle list teachers and return mode and view
	@GetMapping("/teachers")
	public String listTeachers(Model model) {
		model.addAttribute("teachers", teacherService.getAllTeachers());
		return "teachers";
	}

	@GetMapping("/teachers/new")
	public String createTeacherForm(Model model) {
		// create teacher object to hold teacher form data
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "createteacher";
	}

	@PostMapping("/teachers")
	public String saveTeachers(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.saveTeacher(teacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/edit/{id}")
	public String editTeacherForm(@PathVariable Long id, Model model) {
		model.addAttribute("teacher", teacherService.getTeacherById(id));
		return "editteacher";
	}

	@PostMapping("/teachers/{id}")
	public String updateTeacher(@PathVariable Long id, @ModelAttribute("teacher") Teacher teacher, Model model) {
		// get teacher from database by id
		Teacher existingTeacher = teacherService.getTeacherById(id);
		existingTeacher.setId(id);
		existingTeacher.setName(teacher.getName());
		existingTeacher.setEmail(teacher.getEmail());
		existingTeacher.setMobileNo(teacher.getMobileNo());
		existingTeacher.setQualification(teacher.getQualification());

		// save updated teacher object
		teacherService.updateTeacher(existingTeacher);
		return "redirect:/teachers";
	}

	// handler method to handle delete teacher request
	@GetMapping("/teachers/{id}")
	public String deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacherById(id);
		return "redirect:/teachers";
	}

}
