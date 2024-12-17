package tech.chillo.avis_utilisateurs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.chillo.avis_utilisateurs.entity.Project;
import tech.chillo.avis_utilisateurs.service.ProjectService;


@RestController
@CrossOrigin(origins = "**", allowedHeaders = "*") 
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired 
	private  ProjectService projectService; 

	@GetMapping
	public Page<Project> getAllProjects(Pageable pageable) {
		System.out.print("---- la ------");
	    return projectService.getAllProjects(pageable);
	}
	
	@GetMapping("/{id}")
	public Optional<Project> getProjectById(@PathVariable Integer id){
		return projectService.getProjectById(id);
	}
	
	
	
	
}
