package com.nskhoa.expensetrackerapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nskhoa.expensetrackerapi.entity.User;
import com.nskhoa.expensetrackerapi.entity.UserModel;
import com.nskhoa.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import com.nskhoa.expensetrackerapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel uModel) {
        if (userRepository.existsByEmail(uModel.getEmail())) {
            throw new ItemAlreadyExistsException("User with email " + uModel.getEmail() + " already exists");
        }
        User newUser = new User();
        BeanUtils.copyProperties(uModel, newUser);
        return userRepository.save(newUser);
    }
}