package Users;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public class Professor extends User {
    public Professor(String name, String cpf) {
        super(name, cpf);
        setPermission(Permission.PROFESSOR);
    }
}
