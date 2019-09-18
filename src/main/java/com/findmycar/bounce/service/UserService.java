package com.findmycar.bounce.service;

import com.findmycar.bounce.entity.User;
import com.findmycar.bounce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find user by id
     * @param id User id
     * @return User object
     * @throws EntityNotFoundException if a user has not been found
     */
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Cannot find user with id %s", id)));
    }
}
