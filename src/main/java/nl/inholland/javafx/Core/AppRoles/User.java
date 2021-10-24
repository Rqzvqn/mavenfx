package nl.inholland.javafx.Core.AppRoles;

public class User {
    protected String username;
    protected String password;
    protected RolesEnum role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public RolesEnum getPermission() {
        return role;
    }

    public User(String username, String password, RolesEnum Normal) {
        this.username = username;
        this.password = password;
        this.role = RolesEnum.Normal;
    }
}
