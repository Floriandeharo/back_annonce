package tech.chillo.avis_utilisateurs.service;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.chillo.avis_utilisateurs.entity.Project;
import tech.chillo.avis_utilisateurs.repository.LikeRepository;
import tech.chillo.avis_utilisateurs.repository.ProjectRepository;



@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository ;
	
	@Autowired
	private LikeRepository likeRepository;

    public Page<Project> getAllProjects(Pageable pageable) {
    	Page<Project> projects = projectRepository.findAll(pageable);   	
    	for(Project project : projects) {
    		Long nbrLikes = getAllLikes(project.getId());
    		project.setNumberOfLikes(nbrLikes);
    	}
    	return projects;  
    }
	
	public Optional<Project> getProjectById(Integer id) {
	    Optional<Project> projectOptional = projectRepository.findById(id);
	    return projectOptional.map(project -> {
	    	System.out.println("project.getID() "+ project.getId());
	        Long nbrLikes = getAllLikes(project.getId());
	        System.out.println("nbrLikes "+ nbrLikes);
	        project.setNumberOfLikes(nbrLikes);
	        return project;
	    });
	}
	
	public Project updateProject(Project project) {
	    return projectRepository.save(project);
	}
	
	
	public void deleteProject(Integer id) {
		projectRepository.deleteById(id);
	}
	
	 public Long getAllLikes(Long id) {
		 	Long nbr = likeRepository.countLikesByProjectId(id);
		        return nbr ;
	    }
}

