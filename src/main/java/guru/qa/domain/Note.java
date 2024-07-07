package guru.qa.domain;

import java.util.UUID;

public class Note {

    private UUID id;
    private String username;
    private String text;

    public Note(String username, String text) {
        this.username = username;
        this.text = text;
    }

    public Note() {

    }

    public String username() {
        return username;
    }

    @Override
    public String toString() {
        return text;
    }

    public UUID getId() {
        return id;
    }

    public Note setId(UUID id) {
        this.id = id;
        return this;
    }

    public Note setText(String text) {
        this.text = text;
        return this;
    }

    public Note setUserName(String username) {
        this.username = username;
        return this;
    }
}
