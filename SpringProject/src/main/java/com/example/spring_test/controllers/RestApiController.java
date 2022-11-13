package com.example.spring_test.controllers;

import com.example.spring_test.models.Collection;
import com.example.spring_test.models.Donation;
import com.example.spring_test.models.User;
import com.example.spring_test.repository.UserRepository;
import com.example.spring_test.service.CollectionService;
import com.example.spring_test.service.DonationService;
import com.example.spring_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

class CollectionData {
    public String name;
    public Double limitAmount;
}

@RestController
@RequestMapping("/api")
public class RestApiController {
    @Autowired private UserService userService;

    @Autowired private DonationService donationService;

    @Autowired private CollectionService collectionService;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody UserRegistrationData userRegistrationData) {
        if (userService.isRegisteredLogin(userRegistrationData.login)) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.CONFLICT);
        } else {
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
        User loginedUser = userService.findByLoginAndPassword(userLoginData.login, userLoginData.password);

        if (loginedUser != null) {
            Map<String, Long> map = new HashMap<>();
            map.put("id", loginedUser.getId());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            Map<String, Boolean> map = new HashMap<>();
            map.put("error", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/user/data/{id}")
    public ResponseEntity<?> donationsData(@PathVariable Long id) {
        List<Donation> donationsList = donationService.findAllDonationsById(id);
        return new ResponseEntity<>(donationsList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> userData(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsersList(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/goals")
    public ResponseEntity<?> getUserGoals(@PathVariable Long id) {
        return new ResponseEntity<>(collectionService.findCollectionsByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/donate/{key}")
    public ResponseEntity<?> userDonated(@PathVariable String key, @RequestBody Map<String, String> receivedData) {
        // ...
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/info")
    public ResponseEntity<?> getUserDescription(@PathVariable Long id) {
        Map<String, String> map = userService.getUserDescription(id);
        if (map != null) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/user/{id}/info")
    public ResponseEntity<?> updateUserDescription(@PathVariable Long id, @RequestBody Map<String, String> receivedData) {
        String aboutUser = receivedData.get("aboutUser");
        String streamContent = receivedData.get("streamContent");
        String channelDescription = receivedData.get("channelDescription");
        User user = userService.findById(id);
        user.setAboutUser(aboutUser);
        user.setStreamContent(streamContent);
        user.setChannelDescription(channelDescription);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add_goal/{id}")
    public ResponseEntity<?> createGoal(@PathVariable Long id, @RequestBody CollectionData collectionData) {
        collectionService.createCollection(collectionData.name, collectionData.limitAmount, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
