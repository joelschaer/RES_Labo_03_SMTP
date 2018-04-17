import model.mail.Group;
import model.mail.Mail;
import model.mail.Person;
import model.prank.Prank;
import model.prank.PrankGenerator;
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

        PrankGenerator pranks = new PrankGenerator(config);
        List<Prank> prankList = pranks.generatesPranks();

        for(Prank prank: prankList){
            String fakeSenderAddress = prank.getFakeSender().getAddress();

            List<Person> victimsTo = prank.getVictimsTo();
            List<String> victimsToAddresses = new ArrayList<>();
            for(Person victim: victimsTo){
                victimsToAddresses.add(victim.getAddress());
            }

            List<Person> witnessesToCc = prank.getVictimsTo();
            List<String> witnessesToCcAddresses = new ArrayList<>();
            for(Person witnesse: witnessesToCc){
                witnessesToCcAddresses.add(witnesse.getAddress());
            }

            String message = prank.getMessage();

            Mail mailPrank = new Mail(fakeSenderAddress, victimsToAddresses, witnessesToCcAddresses, prank.getMessage());
            
        }

        //SmtpClient smtp = new SmtpClient(config);


    }
}
