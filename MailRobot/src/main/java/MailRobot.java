import model.mail.Mail;
import model.mail.Person;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import config.ConfigurationManager;

public class MailRobot {

  final static Logger LOG = Logger.getLogger(MailRobot.class.getName());

    public static void main(String[] args) {

        if(args.length < 2){
            System.out.println("Missing arguments");
            System.out.println("Usage : MailRobot <victimlist.utf8> <messages.utf8>");
            return;
        }
        if(args.length > 2){
            System.out.println("Only two arguments are needed");
            System.out.println("Usage : MailRobot <victimlist.utf8> <messages.utf8>");
            return;
        }

        String victims = args[0];
        String messages = args[1];

        File victimPath = new File(victims);
        if(!victimPath.exists()){
            System.out.println("Victims file doesn't exists");
            return;
        }


        File messagePath = new File(messages);
        if(!messagePath.exists()) {
            System.out.println("Message file doesn't exists");
            return;
        }

        // load configuration, victims and messages
	    ConfigurationManager config = new ConfigurationManager();
        config.loadEmails(victims);
        config.loadMessages(messages);

        // generate pranks
        // puts together random vicims and a random message defining a prank
        PrankGenerator pranks = new PrankGenerator(config);
        List<Prank> prankList = pranks.generatesPranks();

        List<Mail> mailList = new ArrayList<>();

        // create mails out of the prank list
        for(Prank prank: prankList){
            String fakeSenderAddress = prank.getFakeSender().getAddress();

            List<Person> victimsTo = prank.getVictimsTo();
            List<String> victimsToAddresses = new ArrayList<>();
            for(Person victim: victimsTo){
                victimsToAddresses.add(victim.getAddress());
            }

            List<Person> witnessesToCc = prank.getWitnessesToCc();
            List<String> witnessesToCcAddresses = new ArrayList<>();
            for(Person witnesse: witnessesToCc){
                witnessesToCcAddresses.add(witnesse.getAddress());
            }

            String message = prank.getMessage();

            mailList.add(new Mail(fakeSenderAddress, victimsToAddresses, witnessesToCcAddresses, message));
        }

        SmtpClient smtp = new SmtpClient(config.getSmtpServerAddress(), config.getSmtppServerPort());

        smtp.connect();

        // sending the defined emails for the prank
        for(Mail mail: mailList){
            smtp.sendMessage(mail);
        }

        smtp.diconnect();

    }
}
