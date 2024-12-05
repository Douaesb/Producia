package com.prod.producia.service;

import com.prod.producia.entity.User;

public interface UserService {

    User save(User user);

    boolean existsByUsername(String username);
}
