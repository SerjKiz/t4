package inno;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Data {
    private String login;
    private String lastname;
    private String firstname;
    private String secondName;
    private Timestamp date;
    private String appType;
    private String error;

    public Data(String s) {
        try{
            String[] words = s.split(" ");
            int size = words.length;
            login = words[0];
            lastname = words[1];
            firstname = words[2];
            secondName = words[3];
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            date = Timestamp.valueOf(LocalDateTime.parse(words[4] + " " + words[5], dateFormat));
            appType = words[6];
            error = null;
        } catch( Exception e){
            error = e.getMessage();
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "login='" + login + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", date='" + date + '\'' +
                ", appType='" + appType + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondName() {
        return secondName;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getAppType() {
        return appType;
    }

    public String getError() {
        return error;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return Objects.equals(getLogin(), data.getLogin()) && Objects.equals(getLastname(), data.getLastname()) && Objects.equals(getFirstname(), data.getFirstname()) && Objects.equals(getSecondName(), data.getSecondName()) && Objects.equals(getDate(), data.getDate()) && Objects.equals(getAppType(), data.getAppType()) && Objects.equals(getError(), data.getError());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getLastname(), getFirstname(), getSecondName(), getDate(), getAppType(), getError());
    }
}