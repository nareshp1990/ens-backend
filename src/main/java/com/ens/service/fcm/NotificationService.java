package com.ens.service.fcm;

import com.ens.domain.payload.fcm.PushNotificationRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    public void sendNotification(PushNotificationRequest request) {

        Message message = Message.builder()
                .setTopic(request.getTopic())
                .putAllData(request.getData())
                .setNotification(new Notification(request.getTitle(), request.getMessage(), request.getImageUrl()))
                .build();
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.debug("### Firebase Notification Response : {}", response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            log.error("{}", e);
        }

    }

    public void sendNotificationWithoutData(PushNotificationRequest request) {

        Message message = Message.builder()
                .setTopic(request.getTopic())
                .setNotification(new Notification(request.getTitle(), request.getMessage(), request.getImageUrl()))
                .build();
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.debug("### Firebase Notification Response : {}", response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            log.error("{}", e);
        }

    }


}
