package com.Service;

import com.DTO.UserDTO;
import com.Model.User;

public interface UserService {

	public void registerUserInService(User user);

	public UserDTO getSingleUserByID(int id);

	public User updateUserDetails(User user);

	User getUserById(int id);

	public int delete(int id);
}
