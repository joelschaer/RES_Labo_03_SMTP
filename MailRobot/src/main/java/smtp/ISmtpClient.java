package smtp;

import model.mail.Mail;
import java.io.IOException;

public interface ISmtpClient {
    public void sendMessage(Mail message)throws IOException;
}
