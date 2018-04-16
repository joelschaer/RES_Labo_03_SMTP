package smtp;

import model.mail.Mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {

    private final static Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIn;

    public SmtpClient(String host, int port) {

        try {
            Socket echoSocket = new Socket(host, port);
            PrintWriter out =
                    new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                    new BufferedReader(
                            new InputStreamReader(System.in));

            // We receive the HELLO from the server
            String command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : "+ command);

            // WE send our HELO

            out.print("EHLO kitty\r\n");
            out.flush();

            // WE receive the 250- commands
            // we stop when when get the 250 espace

            command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : "+ command);

            while(command.contains("250-")){
                command = in.readLine();
                LOG.log(Level.INFO, "SERVER SEND : "+ command);
            }


            out.print("MAIL FROM: yann.lederrey@heig-vd.ch\r\n");
            out.flush();

            command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : "+ command);

            out.print("RCPT TO: joel.schar@heig-vc.ch\r\n");
            out.flush();

            command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : "+ command);

            out.print("DATA\r\n");
            out.flush();

            command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : "+ command);

            out.print("Subject: Test\n my message.\r\n.\r\n");
            out.flush();

            command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : "+ command);

            out.print("QUIT\r\n");
            out.flush();

            command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : "+ command);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(Mail message) {

    }
}
