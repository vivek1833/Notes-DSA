package SystemDesign.LLD.Problems.Notification;

public class SMSNotification implements Notification {

    private NotificationType type;

    @Override
    public NotificationType getType() {
        return this.type;
    }

    public SMSNotification(NotificationType type) {
        this.type = type;
    }

    @Override
    public void sendNotification(Template template, String message) {
        System.out.println("Sending SMS notification");
        template.formatTemplate(message);
    }
}
