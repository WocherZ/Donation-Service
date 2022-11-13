package com.example.spring_test.service;

import com.example.spring_test.models.Collection;
import com.example.spring_test.models.Donation;
import com.example.spring_test.models.User;
import com.example.spring_test.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public List<Donation> findAllDonationsById(Long id) {
        List<Donation> donations = new ArrayList<>();
        donations = donations.stream().filter((donation -> Objects.equals(donation.getUser_id().getId(), id))).toList();
        return donations;
    }

    public void createDonation(String userNickName, String text, Double amount, Collection collection, User user_id) {
        Donation donation = new Donation();
        donation.setText(text);
        donation.setAmount(amount);
        donation.setUserNickname(userNickName);
        donation.setCollection_id(collection);
        donation.setUser_id(user_id);
        donationRepository.save(donation);
    }
}
