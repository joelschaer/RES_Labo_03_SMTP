package config;

import model.mail.Person;

import java.util.List;

public interface IConfigurationManager {
    public void loadEmails(String filename);
    public void loadMessages(String filename);
    public String getSmtpServerAddress();
    public int getSmtppServerPort();
    public int getnumberOfGroups();
    public List<Person> getWitnessesToCC();
    public List<Person> getPersonList();
    public List<String> getMessageList();
}
