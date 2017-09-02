package Users;

public class Undergraduate extends User {

    public Undergraduate(String name, String cpf) {
        super(name, cpf);
        setPermission(Permission.GRADUATE);
    }
}
