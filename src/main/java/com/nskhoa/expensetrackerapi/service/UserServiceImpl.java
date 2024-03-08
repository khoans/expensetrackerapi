package com.nskhoa.expensetrackerapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nskhoa.expensetrackerapi.entity.User;
import com.nskhoa.expensetrackerapi.entity.UserModel;
import com.nskhoa.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import com.nskhoa.expensetrackerapi.exceptions.ResourceNotFoundException;
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

    @Override
    public User readUser(Long id) {
        if (id == null) {
            throw new ResourceNotFoundException("User id can not be null");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for the id " + id));
    }

    @Override
    public User update(User user, Long id) {
        if (id == null) {
            throw new ResourceNotFoundException("User Not Found for the id " + id);
        }
        User existingUser = readUser(id);
        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
        return userRepository.save(existingUser);

    }

    @Override
    public void delete(Long id) {
        User user = readUser(id);
        if (user == null) {
            throw new ResourceNotFoundException("User Not Found for the id " + id);
        }
        userRepository.delete(user);
    }
}
