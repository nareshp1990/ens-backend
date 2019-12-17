package com.ens.service.user;

import com.ens.domain.entity.user.User;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.user.UserRequest;
import com.ens.domain.payload.user.UserResponse;
import com.ens.service.IService;

public interface UserService extends IService<User> {

    User createUser(UserRequest userRequest);

    PagedResponse<User> getAllUsers(int page, int size);

    User getUserById(Long userId);

    void deleteUserById(Long userId);

    User updateUser(Long userId, UserRequest updateRequest);

    User updateUserFCMKey(Long userId, String fcmKey);

    UserResponse login(String mobileNumber, String password);

}
