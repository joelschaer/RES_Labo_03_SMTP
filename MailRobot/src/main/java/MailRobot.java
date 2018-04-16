import config.ConfigurationManager;

public class MailRobot {
    public static void main(String[] args){
        ConfigurationManager config = new ConfigurationManager();
        config.loadEmails("./config/victims.RES.utf8");

        config.loadMessages("./config/messages.utf8");

        System.out.println("pause");
    }
}
