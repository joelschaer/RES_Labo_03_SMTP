package smtp;

import model.mail.Mail;

public interface ISmtpClient {
    public void connect();
    public void diconnect();
    public void sendMessage(Mail mail);

}
