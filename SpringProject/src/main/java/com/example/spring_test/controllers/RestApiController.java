package com.example.spring_test.controllers;

import com.example.spring_test.models.User;
import com.example.spring_test.repository.UserRepository;
import com.example.spring_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

class UserRegistrationData {
    public String nickname;
    public String login;
    public String password;
}

class UserLoginData {
    public String login;
    public String password;
}

@RestController
@RequestMapping("/api")
public class RestApiController {
    @Autowired private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody UserRegistrationData userRegistrationData) {
        System.out.println("Post registration");
        if (userService.isRegisteredLogin(userRegistrationData.login)) {
            System.out.println("Уже зареган");
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.CONFLICT);
        } else {
            System.out.println("Не зареган - регаем");
            // ...
            System.out.println(userRegistrationData.login + " " + userRegistrationData.password + " " + userRegistrationData.nickname);
            userService.addUser(
                    userRegistrationData.nickname,
                    userRegistrationData.login,
                    userRegistrationData.password
            );
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginData userLoginData) {
        System.out.println("Post login");
        User loginedUser = userService.findByLoginAndPassword(userLoginData.login, userLoginData.password);

        if (loginedUser != null) {
            System.out.println("Логиним");
            Map<String, Long> map = new HashMap<>();
            map.put("id", loginedUser.getId());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            System.out.println("Ошибка в данных логина");
            Map<String, Boolean> map = new HashMap<>();
            map.put("error", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
}
