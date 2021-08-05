package user;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static utils.Log.LOG;

public class User {

    private String loginName;
    private String password;
    private String firstName;
    private String lastName;
    private String postalCode;

    public User() {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("./src/main/resources/user.properties"));
        } catch (IOException ioException) {
            LOG.error("Problem with file: ", ioException);
        }

        this.loginName = properties.getProperty("user.username");
        this.password = properties.getProperty("user.password");
        this.firstName = properties.getProperty("user.firstname");
        this.lastName = properties.getProperty("user.lastname");
        this.postalCode = properties.getProperty("user.postalcode");
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
