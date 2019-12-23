package com.ens.api;

import com.ens.domain.payload.ApiResponse;
import com.ens.domain.payload.fcm.PushNotificationRequest;
import com.ens.service.fcm.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/notifications")
public class NotificationApiController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity sendNotification(@RequestBody PushNotificationRequest request){

        if (request == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false,"Request cannot be empty"));
        }

        if (request.getData() == null) {
            notificationService.sendNotificationWithoutData(request);
        }else {
            notificationService.sendNotification(request);
        }

        return ResponseEntity.ok(new ApiResponse(true,"Notification sent successfully"));
    }

}
