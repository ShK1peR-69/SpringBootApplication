package com.itis.master.practice.simpleapp.services;

import com.itis.master.practice.simpleapp.entitites.Role;
import com.itis.master.practice.simpleapp.entitites.User;
import com.itis.master.practice.simpleapp.repositories.UserRepository;
import com.itis.master.practice.simpleapp.services.interfaces.UserService;
import com.itis.master.practice.simpleapp.util.Mailing;
import com.itis.master.practice.simpleapp.util.UtilMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/*****
 * @author Igor Astafyev
 * June 2019
 *****/

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private Mailing mailing;
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void saveNewUser(User user) {
		User userFromDB = userRepository.findByEmail(user.getEmail());

		if (userFromDB != null) {
			throw new UsernameNotFoundException("User already exists!");
		}

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setApproved(false);
		user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
		user.setValidkey(UtilMethods.createKey(user.getEmail()));
		userRepository.saveAndFlush(user);
		mailing.sendMail(user.getEmail(), user.getFio());
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public void updateUserApproval(String key) {
		userRepository.updateUserApprovedByValidkey(key);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return user;
	}
}