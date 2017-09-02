package Users;

public class Master extends User {
    public Master(String name, String cpf) {
        super(name, cpf);
        setPermission(Permission.GRADUATE);
    }
}
