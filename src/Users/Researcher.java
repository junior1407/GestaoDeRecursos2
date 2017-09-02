package Users;

public class Researcher extends User {
    public Researcher(String name, String cpf) {
        super(name, cpf);
        setPermission(Permission.RESEARCHER);
    }
}
