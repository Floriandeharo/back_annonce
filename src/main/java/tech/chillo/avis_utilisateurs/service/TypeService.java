package tech.chillo.avis_utilisateurs.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.chillo.avis_utilisateurs.entity.Type;
import tech.chillo.avis_utilisateurs.repository.TypeRepository;

@Service
public class TypeService {
	
	@Autowired
    private TypeRepository typeRepository;

	public List<Type> getAllTypes(){
		List<Type> types =  typeRepository.findAll();
		return types ; 
	}
	
	public Optional<Type> getTypeById(Integer id) {
		return typeRepository.findById(id);
	}
	
	public Type addType(Type type) {
		return typeRepository.save(type);
	}
	
	public Type updateType(Type type) {
	    return typeRepository.save(type);
	}
	
	public void deleteType(Integer id) {
	    typeRepository.deleteById(id);
	}
	
}
