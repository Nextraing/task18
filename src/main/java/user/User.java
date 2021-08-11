package user;

import utils.PropertiesLoader;

public class User {

    private String loginName;
    private String password;
    private String firstName;
    private String lastName;
    private String postalCode;

    public User() {

        PropertiesLoader propertiesLoader = new PropertiesLoader();

        this.loginName = propertiesLoader.getUserProperty("user.username");
        this.password = propertiesLoader.getUserProperty("user.password");
        this.firstName = propertiesLoader.getUserProperty("user.firstname");
        this.lastName = propertiesLoader.getUserProperty("user.lastname");
        this.postalCode = propertiesLoader.getUserProperty("user.postalcode");
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

    @Override
    public String toString() {
        return "User {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
