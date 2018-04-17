import model.mail.Group;
import model.mail.Mail;
import model.mail.Person;
import smtp.SmtpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import config.ConfigurationManager;

public class MailRobot {

  final static Logger LOG = Logger.getLogger(MailRobot.class.getName());

    public static void main(String[] args) {
	    ConfigurationManager config = new ConfigurationManager();
        config.loadEmails("./config/victims.RES.utf8");
        config.loadMessages("./config/messages.utf8");
        //SmtpClient smtp = new SmtpClient(config);

    }
}
