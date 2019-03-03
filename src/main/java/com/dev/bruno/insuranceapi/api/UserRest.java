package com.dev.bruno.insuranceapi.api;

import com.dev.bruno.insuranceapi.domain.User;
import com.dev.bruno.insuranceapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRest {

    private final UserService userService;

    @Autowired
    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User get(@PathVariable(value = "id") Long id) {
        return userService.findOne(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "id") Long id, @Validated @RequestBody User user) {
        userService.update(id, user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User insert(@Validated @RequestBody User user) {
        return userService.insert(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> list() {
        return userService.findAll();
    }
}
