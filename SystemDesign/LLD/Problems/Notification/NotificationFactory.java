package SystemDesign.LLD.Problems.Notification;

public class NotificationFactory {
    public Notification getNotification(NotificationType type) {
        switch (type) {
            case SMS:
                return new SMSNotification(type);
            case EMAIL:
                // return new EmailNotification(type);
            case PUSH:
                // return new PushNotification(type);
            default:
                throw new IllegalArgumentException("Invalid notification type");
        }
    }
}
