package smtp;

import model.mail.Mail;
import java.io.IOException;

public interface ISmtpClient {
    public void connect();
    public void diconnect();
    public void sendMessage(Mail mail);

}
