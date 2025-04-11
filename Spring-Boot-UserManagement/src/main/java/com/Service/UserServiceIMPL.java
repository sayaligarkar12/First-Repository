package com.Service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.UserDTO;
import com.Model.User;
import com.Repo.UserRepo;

@Service
public class UserServiceIMPL implements UserService {
	Logger logger = LoggerFactory.getLogger(UserServiceIMPL.class);
	@Autowired
	private UserRepo ur;

	@Override
	@Transactional
	public void registerUserInService(User user) {

		if (user != null) {
			ur.save(user);
			System.out.println("Success");
		} else {
			System.out.println("User is null");
		}

	}

	@Override
	public UserDTO getSingleUserByID(int id) {

		UserDTO ud = new UserDTO();

		User user = ur.findById(id).get();

		if (user != null) {

			ud.setUname(user.getUname());
			ud.setUPhonenumber(user.getUPhonenumber());
			ud.setUaddress(user.getUaddress());
			ud.setGender(user.getGender());
			return ud;
		} else {

			ud.setMsg("user id is invalid");
			return ud;
		}

	}

	@Override
	public User getUserById(int id) {

		User user1 = ur.findById(id).get();
		if (user1 != null) {
			return user1;
		} else {
			return user1;
		}
	}

	@Override
	public User updateUserDetails(User user) {

		User updatedUser = ur.save(user);
		return updatedUser;
	}

	@Override
	public int delete(int id) {
		if (ur.existsById(id)) {
			ur.deleteById(id);
			logger.info("User deleted", id);
			return id;
		} else {

			return id;
		}

	}
}
