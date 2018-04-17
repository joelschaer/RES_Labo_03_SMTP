package model.mail;

import java.util.ArrayList;
import java.util.List;

public class Mail {

    private String from;
    private List<String> to;
    private List<String> cc;
    private String body;

    public Mail(String from, List<String> to, List<String> cc, String body) {
        this.from = from;
        this.to = new ArrayList<>(to);
        this.cc = new ArrayList<>(cc);
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return new ArrayList<>(to);
    }

    public void setTo(List<String> to) {
        this.to = new ArrayList<>(to);
    }

    public List<String> getCc() {
        return new ArrayList<>(cc);
    }

    public void setCc(List<String> cc) {
        this.cc = new ArrayList<>(cc);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
