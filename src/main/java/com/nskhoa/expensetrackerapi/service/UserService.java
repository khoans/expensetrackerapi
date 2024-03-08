package com.nskhoa.expensetrackerapi.service;

import com.nskhoa.expensetrackerapi.entity.User;
import com.nskhoa.expensetrackerapi.entity.UserModel;

public interface UserService {
    User createUser(UserModel user);

    User readUser(Long id);

    User update(User user, Long id);

    void delete(Long id);
}
