package inno;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="logins")
public class Logins{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private java.sql.Timestamp access_date;
    private long user_id;
    private String application;

    @Override
    public String toString() {
        return "Logins{" +
                "id=" + id +
                ", access_date=" + access_date +
                ", user_id=" + user_id +
                ", application='" + application + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public Timestamp getAccess_date() {
        return access_date;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getApplication() {
        return application;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccess_date(Timestamp access_date) {
        this.access_date = access_date;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}