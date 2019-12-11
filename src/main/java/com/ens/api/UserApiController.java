package com.ens.api;

import com.ens.domain.entity.user.User;
import com.ens.domain.payload.ApiResponse;
import com.ens.domain.payload.PagedResponse;
import com.ens.domain.payload.user.UserRequest;
import com.ens.service.user.UserService;
import com.ens.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(value = "user service", description = "The user service API", tags = {"user"})
@RestController
@RequestMapping("/v1/api/users")
@Slf4j
public class UserApiController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "create user", tags = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest){

        User user = userService.createUser(userRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User Created Successfully"));
    }

    @ApiOperation(value = "update user", tags = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable UUID userId, @RequestBody UserRequest userRequest){

        User user = userService.updateUser(userId,userRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User Updated Successfully"));
    }

    @ApiOperation(value = "get all users", tags = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "user's response", response = PagedResponse.class)})
    @GetMapping
    public ResponseEntity<PagedResponse> getAllUsers(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return ResponseEntity.ok(userService.getAllUsers(page,size));
    }

    @ApiOperation(value = "get user by id", tags = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/userId")
    public ResponseEntity<User> getUser(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @ApiOperation(value = "delete user", tags = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new ApiResponse(true, "User Deleted Successfully"));
    }

    @ApiOperation(value = "update user fcm key", tags = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @PatchMapping("/{userId}/fcmkey/update")
    public ResponseEntity<User> updateUserFCMKey(@PathVariable UUID userId, @RequestParam String fcmKey){
        return ResponseEntity.ok(userService.updateUserFCMKey(userId,fcmKey));
    }

}
