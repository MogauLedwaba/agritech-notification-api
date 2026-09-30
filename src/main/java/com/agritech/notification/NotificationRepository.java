package com.agritech.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {

    List<Notification> findBySupplierIdOrderByCreatedAtDesc(String supplierId);
}