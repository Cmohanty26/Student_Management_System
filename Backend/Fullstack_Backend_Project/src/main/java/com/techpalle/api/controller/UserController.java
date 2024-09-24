package com.techpalle.api.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.techpalle.api.exception.UserNotFoundException;
import com.techpalle.api.model.User;
import com.techpalle.api.repository.UserRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin("http://localhost:3000")
@Controller
public class UserController {
@Autowired
private UserRepository userRepository;
@PostMapping("/user")
User newUser(@RequestBody User newUser) {
	return userRepository.save(newUser);

    
    
}
@GetMapping("/users")
List<User>getAllUsers(){
	return userRepository.findAll();
}
@GetMapping("/user/{id}")
User getUserById(@PathVariable Long id) {
return userRepository.findById(id)
		     .orElseThrow(()-> new UserNotFoundException(id));

}
@PutMapping("/user/{id}")
User updateUser(@RequestBody User newUser,@PathVariable Long id) {
	return userRepository.findById(id)
			  .map(user ->{
		user.setUsername(newUser.getUsername());
		user.setName(newUser.getName());
		user.setEmail(newUser.getEmail());
		return userRepository.save(user);
	}).orElseThrow(()-> new UserNotFoundException(id));

}

//delete the data
@DeleteMapping("/user/{id}")
String deleteUser(@PathVariable Long id) {
	if(!userRepository.existsById(id)) {
		throw new UserNotFoundException(id);
	}
	userRepository.deleteById(id);
	return "user with id:"+ id + " has been delete success.";
}


}
