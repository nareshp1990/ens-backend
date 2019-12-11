package com.ens.service;

import com.ens.domain.entity.user.User;
import com.ens.exception.BadRequestException;
import com.ens.exception.ResourceNotFoundException;
import com.ens.repo.user.UserRepository;
import com.ens.util.AppConstants;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private UserRepository userRepository;

    public void validatePageNumberAndSize(int page, int size) {

        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }

    }

    public User validateUser(UUID userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

}
