package model.prank;

import model.mail.Person;

import java.util.ArrayList;
import java.util.List;

public class Prank {

    private Person fakeSender;
    private List<Person> victimsTo = new ArrayList<>();
    private List<Person> victimsCc = new ArrayList<>();
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

    public List<Person> getVictimsCc() {
        return new ArrayList<>(victimsCc);
    }

    public void addVictimsCc(List<Person> victimsCc) {
        this.victimsCc.addAll(victimsCc);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
