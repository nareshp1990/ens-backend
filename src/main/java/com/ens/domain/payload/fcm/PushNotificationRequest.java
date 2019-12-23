package com.ens.domain.payload.fcm;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationRequest {

    private String title;
    private String message;
    private String imageUrl;
    private String topic;
    private String token;
    private Map<String,String> data;

    public PushNotificationRequest(String title, String messageBody, String topicName) {
        this.title = title;
        this.message = messageBody;
        this.topic = topicName;
    }

    public PushNotificationRequest(String title, String message, String topic,
            Map<String, String> data) {
        this.title = title;
        this.message = message;
        this.topic = topic;
        this.data = data;
    }

    public PushNotificationRequest(String title, String message, String imageUrl,
            String topic, Map<String, String> data) {
        this.title = title;
        this.message = message;
        this.imageUrl = imageUrl;
        this.topic = topic;
        this.data = data;
    }
}
