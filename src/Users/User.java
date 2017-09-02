package Users;

/**
 * Created by Aluno IC on 01/09/2017.
 */
public abstract class User {

    private String name;
    private String cpf;
    private Permission permission;

    public User() {
    }

    public User(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
