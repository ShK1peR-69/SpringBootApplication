package com.itis.master.practice.simpleapp.services;

import com.itis.master.practice.simpleapp.entitites.User;
import com.itis.master.practice.simpleapp.repositories.UserRepository;
import com.itis.master.practice.simpleapp.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*****
 * @author Igor Astafyev
 * June, 2019
 *****/

@Service
@Repository
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveNewUser(User user) {
        userRepository.saveAndFlush(user);
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
