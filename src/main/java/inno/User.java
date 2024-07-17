package inno;

import javax.persistence.*;

@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String username;
    private String fio;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFio() {
        return fio;
    }
}