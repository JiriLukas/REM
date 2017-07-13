package structures;

import java.io.ByteArrayOutputStream;

/**
 * Class User
 * contains information about actual user.
 * */
public class User {

    private int     userID;
    private String  login;
    private String  password;
    private String  name;
    private String  lastName;
    private ByteArrayOutputStream avatar;


    /**
     * Constructor
     * @param login  - user login
     * @param password - user password
     * @param name - user name
     * @param lastName - user last name
     */
    public User(String login, String password, String name, String lastName) {
        this.userID = 0;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }


    /* Getters */
    public int getUserID() {
        return userID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public ByteArrayOutputStream getAvatar() {
        return avatar;
    }

    /* Setters */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatar(ByteArrayOutputStream avatar) {
        this.avatar = avatar;
    }
}
