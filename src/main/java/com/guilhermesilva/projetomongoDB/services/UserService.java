package com.guilhermesilva.projetomongoDB.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermesilva.projetomongoDB.domain.User;
import com.guilhermesilva.projetomongoDB.dto.UserDTO;
import com.guilhermesilva.projetomongoDB.repository.UserRepository;
import com.guilhermesilva.projetomongoDB.services.exceptions.ObjectNotFound;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll(); 
	}
	
	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFound("object not found"));
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
	
	public void insert(User obj) {
		userRepository.save(obj);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
}
