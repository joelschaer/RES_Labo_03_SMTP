package model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private List<Person> members = new ArrayList<>();

    public void addMembers(Person person) {
        members.add(person);
    }

    public List<Person> getMembers() {
        return new ArrayList<>(members);
    }

    public boolean contains(Person person){
        return members.contains(person);

    }
}
