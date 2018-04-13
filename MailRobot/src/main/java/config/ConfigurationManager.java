package config;

import sun.rmi.server.InactiveGroupException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public ConfigurationManager(){

        // load properties from file
        Properties prop = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("config.config.properties");

            smtpServerAddress = prop.getProperty("smtpServerAddress");
            smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
            witnessesToCC = prop.getProperty("witnessesToCC");

        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}