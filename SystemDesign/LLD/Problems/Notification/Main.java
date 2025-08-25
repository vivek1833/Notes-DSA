package SystemDesign.LLD.Problems.Notification;

public class Main {
    public static void main(String[] args) {
        
        String message = "You have been selected!";

        NotificationFactory factory = new NotificationFactory();
        Notification notification = factory.getNotification(NotificationType.SMS);
        Template template = new SMSTemplate();

        notification.sendNotification(template, message);
    }
}
