package nl.inholland.javafx.Core.AppRoles;

public class Admin extends User {
    public Admin(String username, String password, RolesEnum Admin) {
        super(username, password, RolesEnum.Normal);
        this.role = RolesEnum.Admin;
    }
}
