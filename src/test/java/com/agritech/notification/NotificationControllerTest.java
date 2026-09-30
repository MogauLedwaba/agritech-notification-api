package com.agritech.notification;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    @Test
    void healthShouldReturnRunningMessage() {
        NotificationRepository repository = mock(NotificationRepository.class);
        NotificationController controller = new NotificationController(repository);

        String result = controller.health();

        assertEquals("Notification API is running", result);
    }

    @Test
    void getNotificationsShouldReturnNotifications() {
        NotificationRepository repository = mock(NotificationRepository.class);

        Notification notification = new Notification();
        notification.setId("test-1");
        notification.setSupplierId("supplier-1");
        notification.setMessage("Test notification");

        when(repository.findAll()).thenReturn(List.of(notification));

        NotificationController controller = new NotificationController(repository);

        List<Notification> result = controller.getNotifications();

        assertEquals(1, result.size());
        assertEquals("test-1", result.get(0).getId());

        verify(repository).findAll();
    }

    @Test
    void getBySupplierShouldReturnSupplierNotifications() {
        NotificationRepository repository = mock(NotificationRepository.class);

        Notification notification = new Notification();
        notification.setId("test-1");
        notification.setSupplierId("supplier-1");
        notification.setMessage("Supplier notification");

        when(repository.findBySupplierIdOrderByCreatedAtDesc("supplier-1"))
                .thenReturn(List.of(notification));

        NotificationController controller = new NotificationController(repository);

        List<Notification> result =
                controller.getBySupplier("supplier-1");

        assertEquals(1, result.size());
        assertEquals("supplier-1", result.get(0).getSupplierId());

        verify(repository)
                .findBySupplierIdOrderByCreatedAtDesc("supplier-1");
    }

    @Test
    void createNotificationShouldGenerateIdAndSaveNotification() {
        NotificationRepository repository = mock(NotificationRepository.class);

        Notification notification = new Notification();
        notification.setSupplierId("supplier-1");
        notification.setMessage("New notification");

        when(repository.save(any(Notification.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        NotificationController controller = new NotificationController(repository);

        Notification result =
                controller.createNotification(notification);

        assertNotNull(result.getId());
        assertNotNull(result.getCreatedAt());
        assertFalse(result.isRead());

        verify(repository).save(notification);
    }

    @Test
    void markAsReadShouldChangeReadStatus() {
        NotificationRepository repository = mock(NotificationRepository.class);

        Notification notification = new Notification();
        notification.setId("notification-1");
        notification.setSupplierId("supplier-1");
        notification.setMessage("Test");
        notification.setRead(false);

        when(repository.findById("notification-1"))
                .thenReturn(java.util.Optional.of(notification));

        when(repository.save(any(Notification.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        NotificationController controller = new NotificationController(repository);

        Notification result =
                controller.markAsRead("notification-1");

        assertTrue(result.isRead());

        verify(repository).findById("notification-1");
        verify(repository).save(notification);
    }
}
