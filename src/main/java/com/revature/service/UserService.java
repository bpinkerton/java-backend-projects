package com.revature.service;

import com.revature.model.User;
import com.revature.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public User createUser(User user){
        return userRepository.create(user).orElseThrow(RuntimeException::new);
    }

    public User getUserById(Integer id){
        return userRepository.getById(id).orElseThrow(RuntimeException::new);
    }

    public List<User> getAllUsers(){
        return userRepository.getAll();
    }

    public User updateUserById(User user, Integer id){
        return userRepository.updateById(user, id).orElseThrow(RuntimeException::new);
    }

    public User deleteUserById(Integer id){
        return userRepository.deleteById(id).orElseThrow(RuntimeException::new);
    }

}
