package config;

import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Parsing des
 * @author
 */
public class ConfigurationManager implements IConfigurationManager {

    private String smtpServerAddress;
    private int smtpServerPort;
    private int numberOfGroups;
    private String witnessesToCC;

    private List<Person> personList;

    private List<String> messageList;

    public ConfigurationManager(){

        // load properties from file
        Properties prop = new Properties();
        InputStream in = null;

        try{
            in = new FileInputStream("./config/config.properties");

            prop.load(in);

            smtpServerAddress = prop.getProperty("smtpServerAddress");
            smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
            witnessesToCC = prop.getProperty("witnessesToCC");

        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void loadEmails(String filename){
        //load emails from list
        personList = new ArrayList<>();
        BufferedReader in = null;

        try{
            in = new BufferedReader(new FileReader(new File(filename)));

            // create à list of person according to the email addresses
            String firstname;
            String lastname;
            String line;
            while((line = in.readLine()) != null){
                line = line.toLowerCase();
                firstname = line.split("\\.")[0];
                lastname = line.split("\\.")[1].split("@")[0];

                Person person = new Person(firstname, lastname, line);

                personList.add(person);
            }

        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadMessages(String filename){
        messageList = new ArrayList<>();
        BufferedReader in = null;
        try{
            in = new BufferedReader(new FileReader(new File(filename)));

            StringBuilder message = new StringBuilder();
            String line;
            while((line = in.readLine()) != null) {
                if(line.equals("==")){
                    messageList.add(message.toString());
                    message.delete(0, message.length());
                    continue;
                }
                message.append(line + "\n");
            }

            // add last message to list
            messageList.add(message.toString());

        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}