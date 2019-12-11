package com.ens.service.user;

import com.ens.domain.entity.user.User;
import com.ens.domain.payload.KeyValueHolder;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.user.UserRequest;
import com.ens.service.IService;
import java.util.List;
import java.util.UUID;

public interface UserService extends IService<User> {

    User createUser(UserRequest userRequest);

    PagedResponse<User> getAllUsers(int page, int size);

    User getUserById(UUID userId);

    void deleteUserById(UUID userId);

    User updateUser(UUID userId, UserRequest updateRequest);

    User updateUserFCMKey(UUID userId, String fcmKey);

}
