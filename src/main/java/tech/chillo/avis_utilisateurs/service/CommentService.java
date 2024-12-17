package tech.chillo.avis_utilisateurs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.chillo.avis_utilisateurs.entity.Comment;
import tech.chillo.avis_utilisateurs.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository ;

	public List<Comment> getAllComments(){
		List<Comment> comments =  commentRepository.findAll();
		return comments ; 
	}
	
	public Optional<Comment> getCommentById(Integer id) {
		return commentRepository.findById(id);
	}
	
	public Comment addComment(Comment comment) {
		System.out.println("comment ");
		return commentRepository.save(comment);
	}
	
	public Comment updateComment(Comment comment) {
	    return commentRepository.save(comment);
	}
	
	public void deleteComment(Integer id) {
		commentRepository.deleteById(id);
	}
	
}

