package tech.chillo.avis_utilisateurs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.chillo.avis_utilisateurs.entity.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
