package com.itis.master.practice.simpleapp.services;

import com.itis.master.practice.simpleapp.entitites.User;
import com.itis.master.practice.simpleapp.repositories.UserRepository;
import com.itis.master.practice.simpleapp.services.interfaces.UserService;
import com.itis.master.practice.simpleapp.util.Mailing;
import com.itis.master.practice.simpleapp.util.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*****
 * @author Igor Astafyev
 * June, 2019
 *****/

@Service
public class UserServiceImpl implements UserService {

    private final Mailing mailing;
    private final Methods additionalMethods;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Mailing mailing, Methods additionalMethods) {
        this.userRepository = userRepository;
        this.mailing = mailing;
        this.additionalMethods = additionalMethods;
    }


    @Override
    @Transactional
    public void saveNewUser(User user) {
        user.setPassword(additionalMethods.hashPass(user.getPassword()));
        user.setApproved(false);
        user.setRole("ROLE_USER");
        user.setValidkey(additionalMethods.createKey(user.getEmail()));
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
}