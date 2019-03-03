package com.dev.bruno.insuranceapi.service;

import com.dev.bruno.insuranceapi.domain.User;
import com.dev.bruno.insuranceapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames={"users"})
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @CacheEvict(allEntries = true)
    public User insert(User userToInsert) {
        User newUser = new User();
        updateEntity(newUser, userToInsert);
        return userRepository.save(newUser);
    }

    @CacheEvict(allEntries = true)
    public User update(Long id, User userToUpdate) {
        User user = findOne(id);
        updateEntity(user, userToUpdate);
        return userRepository.save(user);
    }

    @CacheEvict(allEntries = true)
    public void delete(Long id) {
        User user = findOne(id);
        userRepository.delete(user);
    }

    @Cacheable
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Cacheable
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    private void updateEntity(User currentUser, User user) {
        BeanUtils.copyProperties(user, currentUser, "prices", "id");
    }
}
