package tech.chillo.avis_utilisateurs.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Type")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer type_id;
	private String name;
	
	public Integer getId() {
		return type_id;
	}
	
	public void setId(Integer type_id) {
		this.type_id = type_id;
	} 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
}