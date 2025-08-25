package SystemDesign.LLD.Problems.Notification;


public interface Notification {
    public NotificationType getType();
    public void sendNotification(Template template, String message);
} 