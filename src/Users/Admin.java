package Users;

public class Admin extends User {
    public Admin(String name, String cpf) {
        super(name, cpf);
        setPermission(Permission.ADMIN);
    }
}
