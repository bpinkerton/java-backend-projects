package com.revature.service;

import com.revature.exception.ResourceNotFoundException;
import com.revature.model.User;
import com.revature.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public User createUser(User user){
        return userRepository.create(user).orElseThrow(RuntimeException::new);
    }

    public User getUserById(Integer id){
        return userRepository.getById(id).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
    }

    public List<User> getAllUsers(){
        return userRepository.getAll();
    }

    public User updateUserById(User user, Integer id){
        return userRepository.updateById(user, id).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
    }

    public User deleteUserById(Integer id){
        return userRepository.deleteById(id).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
    }

}
