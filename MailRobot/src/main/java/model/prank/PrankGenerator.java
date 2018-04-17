package model.prank;

import config.IConfigurationManager;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.List;

public class PrankGenerator {

    private IConfigurationManager config;

    public PrankGenerator(IConfigurationManager configurationManager) {
        this.config = configurationManager;
    }

    /**
     * Il faut implémenter les fonctional requirements dans cette classe
     * @return
     */
    public List<Prank> generatesPranks(){
        List<Prank> pranks = new ArrayList<>();

        // get list of victims from the properties file and creating sending groups.
        int nbOfGroups = config.getnumberOfGroups();
        Group[] groups = new Group[nbOfGroups];

        List<Person> victims = config.getPersonList();
        int nbVictims = victims.size();

        // minimum of victim emails in the list has to be 3;
        if(nbVictims < 3){
            System.err.print("There should be minimum 3 victims in email list");
            return pranks;
        }

        // minimum of victims in a group #3
        int nbInGroup = Math.max(3, nbVictims/nbOfGroups);

        // random attribution of victims in each group.
        // each victim only once in each group
        for(int i = 0; i < nbOfGroups; i++){
            groups[i] = new Group();
            for(int j = 0; j < nbInGroup; j++){
                int rand = (int)(Math.random() * nbVictims);
                Person victim = victims.get(rand);
                if(victims.contains(victim)){
                    j--;
                }
                else{
                    groups[i].addMembers(victim);
                }
            }
        }

        // for each group create a Prank
        for(Group group : groups){
            Prank prank = new Prank();
            List<Person> groupMembers = group.getMembers();

            // first victim will be the fake sender
            prank.setFakeSender(groupMembers.get(0));
            groupMembers.remove(0);

            // the other members of the group will be the recipients to
            prank.addVictimsTo(groupMembers);

            // choose random message to send for the prank
            List<String> messages = config.getMessageList();
            int nbMessages = messages.size();
            int rand = (int)(Math.random() * nbMessages);
            prank.setMessage(messages.get(rand));

            // witnesses to cc to the prank
            prank.setWitnessesToCc(config.getWitnessesToCC());

            pranks.add(prank);
        }

        return pranks;
    }
}
