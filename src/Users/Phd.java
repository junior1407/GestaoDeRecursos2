package Users;

public class Phd extends User {

    public Phd(String name, String cpf) {
        super(name, cpf);
        setPermission(Permission.GRADUATE);
    }
}
