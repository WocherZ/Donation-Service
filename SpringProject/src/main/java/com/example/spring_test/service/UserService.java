package com.example.spring_test.service;

import com.example.spring_test.models.User;
import com.example.spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean isRegisteredLogin(String login) {
        return userRepository.findAll().stream().anyMatch((user) -> Objects.equals(user.getLogin(), login));
    }

    public void addUser(String nickname, String login, String password) {
        User newUser = new User();
        newUser.setNickname(nickname);
        newUser.setLogin(login);
        newUser.setPassword(password);
        userRepository.save(newUser);
    }

    public User findByLoginAndPassword(String login, String password) {
        Optional<User> foundUser = userRepository.findAll().stream().filter((user) -> Objects.equals(user.getLogin(), login) && Objects.equals(user.getPassword(), password)).findAny();
        return foundUser.orElse(null);
    }
}
