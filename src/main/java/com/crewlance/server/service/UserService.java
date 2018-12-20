package com.crewlance.server.service;

import com.crewlance.server.error.ResourceNotFoundException;
import com.crewlance.server.model.User;
import com.crewlance.server.repository.UserRepository;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService extends BaseService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public String delete(User user) {
        userRepository.delete(user);
        return user.getId();
    }

    public List<User> findAll() {
        return Seq.seq(userRepository.findAll()).toList();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find user with id: %s", id)));
    }
}
