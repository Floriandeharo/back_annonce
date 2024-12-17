package tech.chillo.avis_utilisateurs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.chillo.avis_utilisateurs.entity.Type;
import tech.chillo.avis_utilisateurs.service.TypeService;

@RestController
@RequestMapping("/types")
public class TypeController {

	@Autowired
    private TypeService typeService;

    @GetMapping
    public List<Type> getAllTypes(){
		return typeService.getAllTypes();
	}
    
    @GetMapping("/{id}")
	public Optional<Type> getTypeById( @PathVariable Integer id) {
    	System.out.println("id "+ id);
		return typeService.getTypeById(id);
	}
	
    @PostMapping
    public ResponseEntity<Type> addType(@RequestBody Type type) {
        Type addedType = typeService.addType(type);
        return ResponseEntity.ok(addedType); 
    }
	
    @PutMapping
    public ResponseEntity<Type> updateType(@RequestBody Type type) {
        Type updatedType = typeService.updateType(type);
        return ResponseEntity.ok(updatedType);
    }
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Integer id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
