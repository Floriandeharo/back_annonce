package tech.chillo.avis_utilisateurs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.chillo.avis_utilisateurs.entity.Likes;
import tech.chillo.avis_utilisateurs.service.LikeService;


@RestController
@RequestMapping("/likes")
public class LikeController {

	@Autowired
	private LikeService likeService ;
	
	@GetMapping
    public List<Likes> getAllLikes(){
		return likeService.getAllLikes();
	}
	
	@GetMapping("/{id}")
	public Optional<Likes> getLikesById(@PathVariable Integer id){
		return likeService.getLikesById(id);
	}
	

	
}