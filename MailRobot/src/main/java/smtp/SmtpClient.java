package smtp;

import model.mail.Mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {

    private final static Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public SmtpClient(String hostname, int port) {

        // initiates connection to the smtp server
        try {
            socket = new Socket(hostname, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // We receive the Hello from the server
            String command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : " + command);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(Mail mail) {

        // construction of the mail depanding on the specification in mail and sending it
        try {
            out.print("MAIL FROM: " + mail.getFrom() + "\r\n");
            out.flush();

            String command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : " + command);

            for (String to : mail.getTo()) {
                out.print("RCPT TO: " + to + "\r\n");
                out.flush();

                command = in.readLine();
                LOG.log(Level.INFO, "SERVER SEND : " + command);
            }

            for (String cc : mail.getCc()) {
                out.print("RCPT TO: " + cc + "\r\n");
                out.flush();

                command = in.readLine();
                LOG.log(Level.INFO, "SERVER SEND : " + command);
            }

            out.print("DATA\r\n");
            out.flush();

            command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : " + command);

            out.print("From: " + mail.getFrom() + "\r\n");
            out.flush();

            out.print("To: ");
            for (String to : mail.getTo()) {
                out.print(to + ",");
            }
            out.print("\r\n");
            out.flush();

            out.print("Cc: ");
            for (String cc : mail.getCc()) {
                out.print(cc + ",");
            }
            out.print("\r\n");
            out.flush();

            // utf8 encoding for the mail body
            out.print("Content-Type: text/plain; charset=utf-8\r\n");
            out.flush();

            out.print(mail.getBody() + "\r\n");
            out.flush();

            out.print(".\r\n");
            out.flush();

            command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : " + command);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(){

        // opening a connection to the server
        try{
            // WE send our EHLO
            out.print("EHLO kitty\r\n");
            out.flush();

            // WE receive the 250- commands
            // we stop when when get the 250 espace
            String command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : " + command);

            while (command.contains("250-")) {
                command = in.readLine();
                LOG.log(Level.INFO, "SERVER SEND : " + command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void diconnect() {
        try {
            out.print("QUIT\r\n");
            out.flush();

            String command = in.readLine();
            LOG.log(Level.INFO, "SERVER SEND : " + command);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
