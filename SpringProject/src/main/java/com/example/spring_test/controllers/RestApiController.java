package com.example.spring_test.controllers;

import com.example.spring_test.models.Collection;
import com.example.spring_test.models.Donation;
import com.example.spring_test.models.User;
import com.example.spring_test.repository.UserRepository;
import com.example.spring_test.service.CollectionService;
import com.example.spring_test.service.DonationService;
import com.example.spring_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep;
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

class DonationData {
    public String userNickname;
    public String text;
    public Double amount;
    public String goalName;
}

@RestController
@RequestMapping("/api")
public class RestApiController {
    @Autowired private UserService userService;

    @Autowired private DonationService donationService;

    @Autowired private CollectionService collectionService;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody UserRegistrationData userRegistrationData) {
        System.out.println("/registration");
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
        System.out.println("/login");
        User loginedUser = userService.findByLoginAndPassword(userLoginData.login, userLoginData.password);
        if (loginedUser != null) {
            System.out.println("loginedUser");
            Map<String, Long> map = new HashMap<>();
            map.put("id", loginedUser.getId());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            System.out.println("error");
            Map<String, Boolean> map = new HashMap<>();
            map.put("error", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/user/data/{id}")
    public ResponseEntity<?> donationsData(@PathVariable Long id) {
        System.out.println("/user/data/{id}");
        List<Donation> donationsList = donationService.findAllDonationsById(id);
        return new ResponseEntity<>(donationsList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> userData(@PathVariable Long id) {
        System.out.println("/user/{id}");
        User user = userService.findById(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        System.out.println("/users");
        return new ResponseEntity<>(userService.getUsersList(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/goals")
    public ResponseEntity<?> getUserGoals(@PathVariable Long id) {
        System.out.println("get /user/{id}/goals");
        return new ResponseEntity<>(collectionService.findCollectionsByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/user/{id}/goals")
    public ResponseEntity<?> createUserGoal(@PathVariable Long id, @RequestBody CollectionData collectionData) {
        System.out.println("post /user/{id}/goals");
        collectionService.createCollection(collectionData.name, collectionData.limitAmount, id);
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    @PostMapping("/donate/{id}")
    public ResponseEntity<?> userDonated(@PathVariable Long id, @RequestBody DonationData donationData) {
        System.out.println("/donate/{id}");
        Collection collection = collectionService.findCollectionByName(donationData.goalName, id);
        User user = userService.findById(id);
        donationService.createDonation(
                donationData.userNickname,
                donationData.text,
                donationData.amount,
                collection,
                user
        );
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/info")
    public ResponseEntity<?> getUserDescription(@PathVariable Long id) {
        System.out.println("get /user/{id}/info");
        Map<String, String> map = userService.getUserDescription(id);
        if (map != null) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/user/{id}/info")
    public ResponseEntity<?> updateUserDescription(@PathVariable Long id, @RequestBody Map<String, String> receivedData) {
        System.out.println("post /user/{id}/info");
        String aboutUser = receivedData.get("aboutUser");
        String streamContent = receivedData.get("streamContent");
        String channelDescription = receivedData.get("channelDescription");
        User user = userService.findById(id);
        user.setAboutUser(aboutUser);
        user.setStreamContent(streamContent);
        user.setChannelDescription(channelDescription);
        userService.saveUpdatesUser(user);
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

    @PostMapping("/add_goal/{id}")
    public ResponseEntity<?> createGoal(@PathVariable Long id, @RequestBody CollectionData collectionData) {
        System.out.println("/add_goal/{id}");
        collectionService.createCollection(collectionData.name, collectionData.limitAmount, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
