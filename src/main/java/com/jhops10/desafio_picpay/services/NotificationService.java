package com.jhops10.desafio_picpay.services;

import com.jhops10.desafio_picpay.clients.NotificationClient;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification() {
        notificationClient.sendNotification();
    }
}
