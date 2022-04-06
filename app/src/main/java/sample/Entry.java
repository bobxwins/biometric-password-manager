package sample;

import java.io.Serializable;

import javafx.scene.control.TextField;

public class Entry implements Serializable {


    Entry(String titelString, String usernameString, String urlString, String passwordString,String noteString) {
        this.titel=new TextField(titelString);
        this.username=new TextField(usernameString);
        this.url=new TextField(urlString);
        this.password=new TextField(passwordString);
        this.notes=new TextField(noteString);

    }
    TextField titel, username, url, password, notes;
    public TextField getTitel() {
        return titel;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getUrl() {
        return url;
    }

    public TextField getPassword() {
        return password;
    }

    public TextField getNotes() {
        return notes;
    }


}
