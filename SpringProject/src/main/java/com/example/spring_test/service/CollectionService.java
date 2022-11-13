package com.example.spring_test.service;

import com.example.spring_test.models.Collection;
import com.example.spring_test.repository.CollectionRepository;
import com.example.spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired private UserService userService;

    public void createCollection(String name, Double limitAmount, Long user_id) {
        Collection collection = new Collection();
        collection.setLimitAmount(limitAmount);
        collection.setName(name);
        collection.setUser_id(userService.findById(user_id));
        collectionRepository.save(collection);
    }
    public List<Collection> findCollectionsByUserId(Long id) {
        return collectionRepository.findAll().stream().filter((collection -> Objects.equals(collection.getUser_id().getId(), id))).toList();
    }

    public Collection findCollectionByName(String name, Long user_id) {
        Optional<Collection> coll = collectionRepository.findAll().stream()
                .filter((collection -> Objects.equals(collection.getName(), name) && Objects.equals(collection.getUser_id().getId(), user_id)))
                .findFirst();
        if (coll.isPresent()) {
            return coll.get();
        } else {
            return null;
        }
    }
}
