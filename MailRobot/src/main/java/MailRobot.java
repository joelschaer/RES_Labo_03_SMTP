
import smtp.SmtpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailRobot {

    final static Logger LOG = Logger.getLogger(MailRobot.class.getName());

    public static void main(String[] args) {
        SmtpClient smtp = new SmtpClient("localhost", 2525);
    }
}
