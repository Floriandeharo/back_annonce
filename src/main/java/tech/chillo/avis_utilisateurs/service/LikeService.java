package tech.chillo.avis_utilisateurs.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.chillo.avis_utilisateurs.entity.Likes;
import tech.chillo.avis_utilisateurs.repository.LikeRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepository;
	
	public List<Likes> getAllLikes(){
		List<Likes> likes = likeRepository.findAll();
		return likes ; 
	}
	
	public Optional<Likes> getLikesById(Integer id) {
		return likeRepository.findById(id);
	}
	
	public Likes addLikes(Likes likes) {
		return likeRepository.save(likes);
	}
	
	public Likes updateLikes(Likes likes) {
	    return likeRepository.save(likes);
	}
	
	public void deleteLikes(Integer id) {
		likeRepository.deleteById(id);
	}
}
