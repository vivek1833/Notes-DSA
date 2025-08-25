package SystemDesign.LLD.Problems.Notification;

public class SMSTemplate implements Template{
    @Override
    public void formatTemplate(String data) {
        System.out.println("Hi there, a new SMS has been sent: " + data);   
    }
}
