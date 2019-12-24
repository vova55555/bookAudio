package DTO;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;

public class User {
    private String login;
    private String password = ConfigFactory.create(ConfigProperties.class).valid_password();

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
