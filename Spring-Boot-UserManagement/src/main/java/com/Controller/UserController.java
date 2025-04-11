package com.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.DTO.UserDTO;
import com.Model.User;
import com.Service.UserService;

@RestController
@RequestMapping("/exponent/api")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService us;

	@PostMapping(value ="/reg")
	
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		System.out.println(user);

		us.registerUserInService(user);

		return new ResponseEntity("User added", HttpStatus.OK);

	}

	@GetMapping("/getUsingID/{id}")
	public ResponseEntity<?> getUsingID(@PathVariable("id") int id) {
		UserDTO ud = us.getSingleUserByID(id);
		return new ResponseEntity(ud, HttpStatus.OK);
	}
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<?> getEmpById(@PathVariable("id") int id) {
		User user = us.getUserById(id);

		if (user != null) {
			return new ResponseEntity(user, HttpStatus.OK);
		} else {
			return new ResponseEntity("User is  null", HttpStatus.OK);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUserDetails(@RequestBody User user) {
		
		User userUpdated = us.updateUserDetails(user);
		
		return new ResponseEntity(userUpdated, HttpStatus.OK);

	}
	 @DeleteMapping("/deleteUser/{id}")
	    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
	        logger.info("delete", id);
	        try {
	            int Deleted = us.delete(id);
	            logger.info("User updated", Deleted);
	            return new ResponseEntity(Deleted, HttpStatus.OK);
	        } catch (Exception e) {
	            logger.error("Invalid  ", id, e.getMessage());
	            return new ResponseEntity("Invalid userid", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	 }
}