package tech.chillo.avis_utilisateurs.entity;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Table;

@Entity
@Table(name="Likes")
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer like_id;
	
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project project;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Utilisateur user;
    
    private Boolean liked;
    
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	

	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project; 
	}
	
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user; 
	}	


	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getLiked() {
		return liked;
	}

	public void setLiked(Boolean liked) {
		this.liked = liked;
	}
}
