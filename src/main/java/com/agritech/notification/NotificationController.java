package com.agritech.notification;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // HEALTH CHECK
    @GetMapping("/health")
    public String health() {
        return "Notification API is running";
    }

    // GET all notifications
    @GetMapping
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    // GET notifications for one supplier
    @GetMapping("/supplier/{supplierId}")
    public List<Notification> getBySupplier(@PathVariable String supplierId) {
        return notificationRepository
                .findBySupplierIdOrderByCreatedAtDesc(supplierId);
    }

    // CREATE notification
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {

        if (notification.getId() == null || notification.getId().isBlank()) {
            notification.setId(UUID.randomUUID().toString());
        }

        if (notification.getCreatedAt() == null) {
            notification.setCreatedAt(java.time.LocalDateTime.now());
        }

        notification.setRead(false);

        return notificationRepository.save(notification);
    }

    // MARK notification as read
    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable String id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification not found"));

        notification.setRead(true);

        return notificationRepository.save(notification);
    }
}