package serialPack.user;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private boolean authorization = false;
    private boolean registration = false;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean getAuthorization() { return authorization; }
    public boolean getRegistration() { return registration; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setAuthorization(boolean authorization) { this.authorization = authorization; }
    public void setRegistration(boolean registration) { this.registration = registration; }
}
