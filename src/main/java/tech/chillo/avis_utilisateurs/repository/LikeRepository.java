package tech.chillo.avis_utilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.chillo.avis_utilisateurs.entity.Likes;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Integer> {

	@Query("SELECT COUNT(l) FROM Likes l WHERE l.project.id = :projectId")
    Long countLikesByProjectId(@Param("projectId") Long projectId);
}