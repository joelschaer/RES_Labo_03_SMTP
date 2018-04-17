package config;

import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    private List<Person> witnessesToCc;

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
            String witnesses = prop.getProperty("witnessesToCC");

            // extract and create the witnesses list
            witnessesToCc = new ArrayList<>();
            List<String> witnessesList = Arrays.asList(witnesses.split("\\s*,\\s*"));
            /*while(!witnessesList.equals("")){
                String witness = witnessesList.split(",")[0];
                if(witnessesList.split(",")[1] == null){
                    witnessesList = "";
                }
                else{
                    witnessesList = witnessesList.split(",")[1];
                }*/

            for(String witness: witnessesList){
                String firstname = witness.split("\\.")[0];
                String lastname = witness.split("\\.")[1].split("@")[0];

                witnessesToCc.add(new Person(firstname, lastname, witness));
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
                if(line.equals(""))
                    continue;
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

    public String getSmtpServerAddress(){
        return smtpServerAddress;
    }
    public int getSmtppServerPort(){
        return smtpServerPort;
    }
    public int getnumberOfGroups(){
        return numberOfGroups;
    }

    public List<Person> getWitnessesToCC(){
        return new ArrayList<>(witnessesToCc);
    }
    public List<Person> getPersonList(){
        return new ArrayList<>(personList);
    }
    public List<String> getMessageList(){
        return new ArrayList<>(messageList);
    }
}