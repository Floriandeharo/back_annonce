package tech.chillo.avis_utilisateurs.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.chillo.avis_utilisateurs.entity.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	@Query("SELECT p FROM Project p LEFT JOIN FETCH p.comments")
    Page<Project> findAllWithComments(Pageable pageable);
	

	
}