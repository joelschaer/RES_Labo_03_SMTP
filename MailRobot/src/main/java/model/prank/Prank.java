package model.prank;

import model.mail.Person;

import java.util.ArrayList;
import java.util.List;

public class Prank {

    private Person fakeSender;
    private List<Person> victimsTo = new ArrayList<>();
    private List<Person> witnessesToCc = new ArrayList<>();
    private String message;

    public Person getFakeSender() {
        return fakeSender;
    }

    public void setFakeSender(Person fakeSender) {
        this.fakeSender = fakeSender;
    }

    public List<Person> getVictimsTo() {
        return new ArrayList<>(victimsTo);
    }

    public void addVictimsTo(List<Person> victimsTo) {
        this.victimsTo.addAll(victimsTo);
    }

    public List<Person> getWitnessesToCc() {
        return new ArrayList<>(witnessesToCc);
    }

    public void setWitnessesToCc(List<Person> witnessesToCc) {
        this.witnessesToCc.addAll(witnessesToCc);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
