package at.myrecipes.cookBookApi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String description;
    @ElementCollection
    private Map<String, String> socials;
    private String picture;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setSocials(String socialMedia, String profileLink) {
        socials.put(socialMedia, profileLink);
    }
}
