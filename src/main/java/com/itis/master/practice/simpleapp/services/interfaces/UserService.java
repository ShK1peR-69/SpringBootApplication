package com.itis.master.practice.simpleapp.services.interfaces;

import com.itis.master.practice.simpleapp.entitites.User;

import java.util.List;

/*****
 * @author Igor Astafyev
 * June, 2019
 *****/

public interface UserService {

    void saveNewUser(User user);

    List<User> getAllUsers();

    void updateUserApproval(String key);

}