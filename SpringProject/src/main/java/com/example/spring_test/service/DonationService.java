package com.example.spring_test.service;

import com.example.spring_test.models.Donation;
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
}
