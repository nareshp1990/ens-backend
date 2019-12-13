package com.ens.service.user;

import com.ens.domain.entity.user.User;
import com.ens.domain.entity.user.UserProfile;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.user.UserRequest;
import com.ens.domain.payload.user.UserResponse;
import com.ens.exception.AuthException;
import com.ens.exception.BadRequestException;
import com.ens.repo.user.UserRepository;
import com.ens.service.ValidationService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findOne(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pgbl) {
        return userRepository.findAll(pgbl);
    }

    @Override
    public User createUser(UserRequest userRequest) {

        userRepository.findByMobileNumber(userRequest.getMobileNumber()).ifPresent(user -> { throw new BadRequestException(); });

        User userEntity = getUserEntity(userRequest);

        return save(userEntity);
    }

    @Override
    public PagedResponse<User> getAllUsers(int page, int size) {

        validationService.validatePageNumberAndSize(page, size);

        // Retrieve Polls
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<User> users = findAll(pageable);

        return new PagedResponse<>(users.getContent(), users.getNumber(),
                users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast());
    }

    @Override
    public User getUserById(UUID userId) {
        return validationService.validateUser(userId);
    }

    @Override
    public void deleteUserById(UUID userId) {
        validationService.validateUser(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(UUID userId, UserRequest updateRequest) {

        User user = validationService.validateUser(userId);

        return user;
    }

    @Override
    public User updateUserFCMKey(UUID userId, String fcmKey) {

        User user = validationService.validateUser(userId);

        user.setFcmRegistrationKey(fcmKey);

        return save(user);
    }

    @Override
    public UserResponse login(String mobileNumber, String password) {

        User user = userRepository.findByMobileNumberAndPassword(mobileNumber, password).orElseThrow(() -> new AuthException("UserId or Password incorrect"));

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        userResponse.setMobileNumber(user.getMobileNumber());
        userResponse.setProfileImageUrl(user.getProfileImageUrl());
        userResponse.setFcmRegistrationKey(user.getFcmRegistrationKey());
        userResponse.setGender(user.getUserProfile().getGender());
        userResponse.setDateOfBirth(user.getUserProfile().getDateOfBirth());
        userResponse.setCountry(user.getUserProfile().getCountry());
        userResponse.setState(user.getUserProfile().getState());
        userResponse.setDistrict(user.getUserProfile().getDistrict());
        userResponse.setArea(user.getUserProfile().getArea());

        return userResponse;
    }


    private User getUserEntity(UserRequest userRequest) {

        User user = new User();

        user.setEmail(userRequest.getEmail());
        user.setUserName(userRequest.getUserName());
        user.setMobileNumber(userRequest.getMobileNumber());
        user.setFcmRegistrationKey(userRequest.getFcmRegistrationKey());
        user.setPassword(userRequest.getPassword());
        user.setProfileImageUrl(userRequest.getProfileImageUrl());

        UserProfile userProfile = new UserProfile();
        userProfile.setGender(userRequest.getGender());
        userProfile.setDateOfBirth(userRequest.getDateOfBirth());

        user.setUserProfile(userProfile);
        userProfile.setUser(user);

        return user;
    }
}
