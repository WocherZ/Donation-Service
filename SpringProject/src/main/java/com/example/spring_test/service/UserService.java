package com.example.spring_test.service;

import com.example.spring_test.models.User;
import com.example.spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<User> getUsersList() {

        return userRepository.findAll();
    }

    public Map<String, String> getUserDescription(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("nickname", user.get().getNickname());
            resultMap.put("aboutUser" , user.get().getAboutUser());
            resultMap.put("streamContent", user.get().getStreamContent());
            resultMap.put("channelDescription", user.get().getChannelDescription());
            return resultMap;
        } else {
            return null;
        }
    }

    public void saveUpdatesUser(User user) {
        userRepository.save(user);
    }


}
